package intervie.zebrih.services;

import intervie.zebrih.entities.Person;
import intervie.zebrih.entities.PersonDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonService {

    public List<PersonDto> getFilteredPeople(String search, Integer year, Integer month, String team, String territory) {
        List<PersonDto> allPeople = loadPeopleWithStatsFromJson(year, month);

        return allPeople.stream()
                .filter(p -> {
                    if (search == null || search.trim().isEmpty()) return true;
                    String lowerSearch = search.toLowerCase();
                    return (p.getFullName() != null && p.getFullName().toLowerCase().contains(lowerSearch)) ||
                           (p.getFirstName() != null && p.getFirstName().toLowerCase().contains(lowerSearch)) ||
                           (p.getLastName() != null && p.getLastName().toLowerCase().contains(lowerSearch));
                })
                .filter(p -> {
                    if (team == null || team.trim().isEmpty()) return true;
                    return p.getTeam() != null && p.getTeam().equalsIgnoreCase(team.trim());
                })
                .filter(p -> {
                    if (territory == null || territory.trim().isEmpty()) return true;
                    return p.getTerritory() != null && p.getTerritory().equalsIgnoreCase(territory.trim());
                })
                .collect(Collectors.toList());
    }

    private List<PersonDto> loadPeopleWithStatsFromJson(Integer year, Integer month) {
        Map<Long, PersonDto> peopleMap = new HashMap<>();
        
        // Pokud je zadán měsíc, jedeme v měsíčním módu, jinak roční mód
        boolean isMonthly = (year != null && month != null);
        LocalDate referenceDate = isMonthly ? LocalDate.of(year, month, 1) : LocalDate.now();

        try {
            ClassPathResource resource = new ClassPathResource("data.json");
            String jsonContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonContent);
            JsonNode dataNode = root.get("data");

            if (dataNode != null && dataNode.isArray()) {
                for (JsonNode bcNode : dataNode) {
                    BigDecimal caseProfit = bcNode.has("tradingProfit") && !bcNode.get("tradingProfit").isNull() 
                                            ? new BigDecimal(bcNode.get("tradingProfit").asText()) : BigDecimal.ZERO;

                    String foundTerritory = parseTerritory(bcNode);
                    String foundTeam = parseTeam(bcNode);

                    LocalDate date = null;
                    if (bcNode.has("validFrom") && !bcNode.get("validFrom").isNull()) {
                        try { date = LocalDate.parse(bcNode.get("validFrom").asText().substring(0, 10)); } catch (Exception ignored) {}
                    }

                    if (date != null) {
                        aggregateStats(bcNode, peopleMap, caseProfit, date, referenceDate, isMonthly, foundTeam, foundTerritory);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dopočet trendu pro všechny
        for (PersonDto dto : peopleMap.values()) {
            calculateTrend(dto);
        }
        
        return new ArrayList<>(peopleMap.values());
    }

    private void aggregateStats(JsonNode bcNode, Map<Long, PersonDto> peopleMap, BigDecimal profit, 
                                LocalDate date, LocalDate refDate, boolean isMonthly, String team, String territory) {
        if (!bcNode.has("owner") || bcNode.get("owner").isNull()) return;
        
        Person p = mapNodeToPerson(bcNode.get("owner"));
        if (p.getId() == null) return;

        peopleMap.putIfAbsent(p.getId(), new PersonDto(p));
        PersonDto dto = peopleMap.get(p.getId());

        if ("Neznámý".equals(dto.getTeam())) dto.setTeam(team);
        if ("Neznámý".equals(dto.getTerritory())) dto.setTerritory(territory);

        if (isMonthly) {
            LocalDate lastMonthRef = refDate.minusMonths(1);
            if (date.getYear() == refDate.getYear() && date.getMonthValue() == refDate.getMonthValue()) {
                dto.setNumberOfCases(dto.getNumberOfCases() + 1);
                dto.setTotalProfit(dto.getTotalProfit().add(profit));
                dto.setCurrentMonthProfit(dto.getCurrentMonthProfit().add(profit));
            } else if (date.getYear() == lastMonthRef.getYear() && date.getMonthValue() == lastMonthRef.getMonthValue()) {
                dto.setLastMonthProfit(dto.getLastMonthProfit().add(profit));
            }
        } else {
            // Roční trend: Posledních 12m vs 12m předtím
            LocalDate twelveMonthsAgo = refDate.minusMonths(12);
            LocalDate twentyFourMonthsAgo = refDate.minusMonths(24);

            if (date.isAfter(twelveMonthsAgo) && !date.isAfter(refDate)) {
                dto.setNumberOfCases(dto.getNumberOfCases() + 1);
                dto.setTotalProfit(dto.getTotalProfit().add(profit));
                dto.setCurrentMonthProfit(dto.getCurrentMonthProfit().add(profit));
            } else if (date.isAfter(twentyFourMonthsAgo) && !date.isAfter(twelveMonthsAgo)) {
                dto.setLastMonthProfit(dto.getLastMonthProfit().add(profit));
            }
        }
    }

    private void calculateTrend(PersonDto dto) {
        BigDecimal current = dto.getCurrentMonthProfit();
        BigDecimal last = dto.getLastMonthProfit();

        if (last.compareTo(BigDecimal.ZERO) == 0) {
            dto.setTrendPercentage(current.compareTo(BigDecimal.ZERO) == 0 ? 0.0 : 1.0);
        } else {
            // Koeficient (např. 1.15 = 15% růst)
            dto.setTrendPercentage(current.divide(last, 2, RoundingMode.HALF_UP).doubleValue());
        }
    }

    private String parseTerritory(JsonNode bcNode) {
        if (bcNode.has("company") && bcNode.get("company").has("primaryAddress")) {
            JsonNode addr = bcNode.get("company").get("primaryAddress");
            if (addr.has("territory") && addr.get("territory").has("strValue01")) 
                return addr.get("territory").get("strValue01").asText();
        }
        return "Neznámý Region";
    }

    private String parseTeam(JsonNode bcNode) {
        if (bcNode.has("owner") && bcNode.get("owner").has("securityLevel")) {
            return bcNode.get("owner").get("securityLevel").get("name").asText();
        }
        return "Neznámý Tým";
    }

    private Person mapNodeToPerson(JsonNode node) {
        Person p = new Person();
        if (node.has("id")) p.setId(node.get("id").asLong());
        if (node.has("fullName")) p.setFullName(node.get("fullName").asText());
        if (node.has("firstName")) p.setFirstName(node.get("firstName").asText());
        if (node.has("lastName")) p.setLastName(node.get("lastName").asText());
        return p;
    }
}