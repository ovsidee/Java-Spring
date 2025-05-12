package pl.edu.pja.tpo09.Controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo09.Models.DTO.BMIDto;

@RestController
@RequestMapping("/api/v1/BMI")
public class BMIController {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.TEXT_PLAIN_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> calculateBMIBasedOnWeightHeight(@RequestParam double weight,
                                                             @RequestParam double height,
                                                             @RequestHeader("Accept") String accept) {
        if (weight <= 0 || height <= 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Reason", "invalid data, weight and height parameters must be positive numbers.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).build();
        }

        double bmiValue = weight / Math.pow(height / 100, 2);
        String typeOfIndex = getBMIType(bmiValue);
        BMIDto responseToBeSent = new BMIDto(weight, height, bmiValue, typeOfIndex);

        if (accept.equals(MediaType.TEXT_PLAIN_VALUE))
            return ResponseEntity.ok(responseToBeSent.getBMIDoublePlainText());
        else return ResponseEntity.ok(responseToBeSent);
    }

    private String getBMIType(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obesity";
    }
}