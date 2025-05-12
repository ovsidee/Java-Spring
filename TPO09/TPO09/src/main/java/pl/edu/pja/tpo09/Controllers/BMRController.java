package pl.edu.pja.tpo09.Controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo09.Models.DTO.BMRDto;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/BMR")
public class BMRController {

    @GetMapping(value = "/man", produces = {MediaType.APPLICATION_JSON_VALUE,
                                               MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> calculateBMROnWeightHeightAge(@RequestParam double weight,
                                                           @RequestParam double height,
                                                           @RequestParam int age,
                                                           @RequestHeader("Accept") String accept) {
        if (weight <= 0 || height <= 0 || age <= 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Reason", "invalid data, weight, height and age parameters must be positive numbers");
            return ResponseEntity.status(499).headers(headers).build();
        }

        double bmrValue = calculateBmrForMan(weight, height, age);
        BMRDto responseToBeSent = new BMRDto("man", weight, height, age, bmrValue);

        if (Objects.equals(accept, MediaType.TEXT_PLAIN_VALUE))
            return ResponseEntity.ok(responseToBeSent.getBmrKcalPlainText());
         else return ResponseEntity.ok(responseToBeSent);
    }

    private double calculateBmrForMan(double weight, double height, int age) {
        return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
    }

}