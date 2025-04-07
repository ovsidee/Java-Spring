package pl.edu.pja.tpo05.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NumbersController {
    public String allDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-_=+[]{}|;:<>,.?/";

    @PostMapping("/nums")
    @ResponseBody
    public String convertNumber(@RequestParam String valueToConvert,
                                @RequestParam int fromBase,
                                @RequestParam int toBase) {

        if (fromBase < 2 || fromBase > 100 || toBase < 2 || toBase > 100) {
            return "<strong>Error: fromBase or toBase outside the allowed range.</strong>";
        }

        String validChars = allDigits.substring(0, fromBase);
        for (char c : valueToConvert.toCharArray()) {
            if (validChars.indexOf(c) == -1) {
                return "<strong>Error: Invalid character '" + c + "' for base " + fromBase + ".</strong>";
            }
        }

        try {
            long decimal = baseToDecimal(valueToConvert, fromBase);
            String converted = decimalToBase(decimal, toBase);
            return "<strong> " + converted + " </strong>";
        } catch (Exception e) {
            return "<strong>Error: Conversion failed.</strong>";
        }
    }

    public long baseToDecimal(String value, int base) {
        long result = 0;
        for (char c : value.toCharArray()) {
            int digitValue = allDigits.indexOf(c);
            result = result * base + digitValue;
        }
        return result;
    }

    public String decimalToBase(long value, int base) {
        if (value == 0) return "0";
        StringBuilder stringBuilder = new StringBuilder();
        while (value > 0) {
            stringBuilder.insert(0, allDigits.charAt((int) (value % base)));
            value /= base;
        }
        return stringBuilder.toString();
    }
}
//9Z -> from36 -> to10 = 359 res
//2A -> from16 -> to10 = 42 res