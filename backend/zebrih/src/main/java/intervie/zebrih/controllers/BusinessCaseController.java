package intervie.zebrih.controllers; // Sleduji, že controller máš ve složce controllers, super!

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/business-cases")
@CrossOrigin(origins = "*")
public class BusinessCaseController {

    // Nastavíme produkované médium na APPLICATION_JSON_VALUE, aby frontend hned věděl, že mu jde JSON
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBusinessCases() {
        try {
            // 1. Načteme soubor z resources
            ClassPathResource resource = new ClassPathResource("data.json");
            
            // 2. Přečteme ho přímo jako text (String) v kódování UTF-8
            String jsonContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            
            // 3. Pošleme textový JSON rovnou frontendu
            return ResponseEntity.ok(jsonContent);
            
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Chyba při načítání souboru data.json: " + e.getMessage() + "\"}");
        }
    }
}