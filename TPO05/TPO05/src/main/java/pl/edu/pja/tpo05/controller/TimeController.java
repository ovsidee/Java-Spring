package pl.edu.pja.tpo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TimeController {

    @RequestMapping("/current-time")
    @ResponseBody
    public String time(@RequestParam(name = "zone", required = false, defaultValue = "Europe/Warsaw") String timeZone,
                       @RequestParam(name = "format", required = false, defaultValue = "HH:mm:ss.SSSS yyyy/MM/dd") String format) {

        String normalizedFormat = makeAppropriateFormat(format);
        String warning = "";

        try {
            DateTimeFormatter.ofPattern(normalizedFormat);
        } catch (IllegalArgumentException e) {
            warning = "Warning: Invalid format: \"" + normalizedFormat +  "\".Falling back to the default format.<br>";
            normalizedFormat = "HH:mm:ss.SSSS yyyy/MM/dd";
        }

        ZonedDateTime zonedTime;
        try {
            zonedTime = ZonedDateTime.now(ZoneId.of(timeZone));
        } catch (Exception e) {
            warning += "Warning: Invalid time zone provided: \"" + timeZone + "\". Defaulting to system time zone.<br>";
            zonedTime = ZonedDateTime.now();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(normalizedFormat);
        return warning + "<strong>Current Time:</strong> " + zonedTime.format(formatter);
    }

    @RequestMapping("/current-year")
    @ResponseBody
    public String year() {
        return "<strong>Current Year: " + String.valueOf(LocalDateTime.now().getYear()) + "</strong>";
    }

    public String makeAppropriateFormat(String format) {
        return format
                .replaceAll("DD", "dd")
                .replaceAll("YYYY", "yyyy")
                .replaceAll("hh", "HH");
    }
}
