package intervie.zebrih.controllers;

import intervie.zebrih.entities.PersonDto;
import intervie.zebrih.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "*")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPeople(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "team", required = false) String team,
            @RequestParam(value = "territory", required = false) String territory) {

        // Předáváme Integer (může být null, pokud frontend parametry nepošle)
        List<PersonDto> filteredPeople = personService.getFilteredPeople(search, year, month, team, territory);
        return ResponseEntity.ok(filteredPeople);
    }
}