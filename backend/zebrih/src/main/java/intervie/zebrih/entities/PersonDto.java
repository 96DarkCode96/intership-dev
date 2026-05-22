package intervie.zebrih.entities;

import java.math.BigDecimal;

public class PersonDto {
    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String gender;
    private String contactInfoEmail;
    
    private int numberOfCases;
    private BigDecimal totalProfit;
    private BigDecimal currentMonthProfit;
    private BigDecimal lastMonthProfit;
    private double trendPercentage;

    // NOVÁ POLE PRO REGION A TÝM
    private String territory;
    private String team;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.contactInfoEmail = person.getContactInfoEmail();
        
        this.numberOfCases = 0;
        this.totalProfit = BigDecimal.ZERO;
        this.currentMonthProfit = BigDecimal.ZERO;
        this.lastMonthProfit = BigDecimal.ZERO;
        this.trendPercentage = 0.0;
        
        this.territory = "Neznámý";
        this.team = "Neznámý";
    }

    // Settery pro nová pole
    public void setTerritory(String territory) { this.territory = territory; }
    public void setTeam(String team) { this.team = team; }

    // Gettery pro nová pole
    public String getTerritory() { return territory; }
    public String getTeam() { return team; }

    // ... zachovej všechny stávající gettery a settery z minula ...
    public void setNumberOfCases(int numberOfCases) { this.numberOfCases = numberOfCases; }
    public void setTotalProfit(BigDecimal totalProfit) { this.totalProfit = totalProfit; }
    public void setCurrentMonthProfit(BigDecimal currentMonthProfit) { this.currentMonthProfit = currentMonthProfit; }
    public void setLastMonthProfit(BigDecimal lastMonthProfit) { this.lastMonthProfit = lastMonthProfit; }
    public void setTrendPercentage(double trendPercentage) { this.trendPercentage = trendPercentage; }
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getContactInfoEmail() { return contactInfoEmail; }
    public int getNumberOfCases() { return numberOfCases; }
    public BigDecimal getTotalProfit() { return totalProfit; }
    public BigDecimal getCurrentMonthProfit() { return currentMonthProfit; }
    public BigDecimal getLastMonthProfit() { return lastMonthProfit; }
    public double getTrendPercentage() { return trendPercentage; }
}