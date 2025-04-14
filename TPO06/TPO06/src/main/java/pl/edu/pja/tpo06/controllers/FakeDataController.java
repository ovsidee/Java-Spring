package pl.edu.pja.tpo06.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo06.DTO.PersonDTO;
import pl.edu.pja.tpo06.services.FakeDataService;

import java.util.*;

@Controller
public class FakeDataController {
    public final FakeDataService fakeDataService;

    public FakeDataController(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @GetMapping("/genPersonalData")
    public String showForm() {
        return "genPersonalData";
    }

    @PostMapping("/genPersonalData")
    public String genPersonalData(@RequestParam Integer quantity,
                                  @RequestParam String lang,
                                  @RequestParam(required = false) List<String> fields,
                                  Model model) {
        if (quantity <= 0 ) {
            model.addAttribute("error", "Quantity must be greater than zero.");
            return "genPersonalData";
        } else {
            List<PersonDTO> fakeData = fakeDataService.generateData(quantity, lang, fields);
            List<String> headers = getLocalizedHeaders(lang, fields);

            model.addAttribute("fakeData", fakeData);
            model.addAttribute("headers", headers);
        }

        return "genPersonalData";
    }

    public  List<String>  getLocalizedHeaders(String lang, List<String> fields) {
        Map<String, String> tableNameHeaders = new HashMap<>();
        switch (lang) {
            case "de" -> {
                tableNameHeaders.put("firstName", "Vorname");
                tableNameHeaders.put("lastName", "Nachname");
                tableNameHeaders.put("dateOfBirth", "Geburtsdatum");
                tableNameHeaders.put("address", "Adresse");
                tableNameHeaders.put("university", "Universität");
                tableNameHeaders.put("country", "Herkunftsland");
                tableNameHeaders.put("username", "Benutzername");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Gesprochene Sprache");
                tableNameHeaders.put("password", "Passwort");
                tableNameHeaders.put("creditCard", "Kreditkarte");
            }
            case "pl" -> {
                tableNameHeaders.put("firstName", "Imię");
                tableNameHeaders.put("lastName", "Nazwisko");
                tableNameHeaders.put("dateOfBirth", "Data urodzenia");
                tableNameHeaders.put("address", "Adres");
                tableNameHeaders.put("university", "Nazwa uczelni");
                tableNameHeaders.put("country", "Kraj pochodzenia");
                tableNameHeaders.put("username", "Nazwa użytkownika");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Język");
                tableNameHeaders.put("password", "Hasło");
                tableNameHeaders.put("creditCard", "Karta kredytowa");
            }
            case "es" -> {
                tableNameHeaders.put("firstName", "Nombre");
                tableNameHeaders.put("lastName", "Apellido");
                tableNameHeaders.put("dateOfBirth", "Fecha de nacimiento");
                tableNameHeaders.put("address", "Dirección");
                tableNameHeaders.put("university", "Universidad");
                tableNameHeaders.put("country", "País de origen");
                tableNameHeaders.put("username", "Nombre de usuario");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Idioma");
                tableNameHeaders.put("password", "Contraseña");
                tableNameHeaders.put("creditCard", "Tarjeta de crédito");
            }
            case "ru" -> {
                tableNameHeaders.put("firstName", "Имя");
                tableNameHeaders.put("lastName", "Фамилия");
                tableNameHeaders.put("dateOfBirth", "Дата рождения");
                tableNameHeaders.put("address", "Адрес");
                tableNameHeaders.put("university", "Университет");
                tableNameHeaders.put("country", "Страна происхождения");
                tableNameHeaders.put("username", "Имя пользователя");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Язык");
                tableNameHeaders.put("password", "Пароль");
                tableNameHeaders.put("creditCard", "Кредитная карта");
            }
            case "fr" -> {
                tableNameHeaders.put("firstName", "Prénom");
                tableNameHeaders.put("lastName", "Nom");
                tableNameHeaders.put("dateOfBirth", "Date de naissance");
                tableNameHeaders.put("address", "Adresse");
                tableNameHeaders.put("university", "Université");
                tableNameHeaders.put("country", "Pays d'origine");
                tableNameHeaders.put("username", "Nom d'utilisateur");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Langue");
                tableNameHeaders.put("password", "Mot de passe");
                tableNameHeaders.put("creditCard", "Carte de crédit");
            }
            case "it" -> {
                tableNameHeaders.put("firstName", "Nome");
                tableNameHeaders.put("lastName", "Cognome");
                tableNameHeaders.put("dateOfBirth", "Data di nascita");
                tableNameHeaders.put("address", "Indirizzo");
                tableNameHeaders.put("university", "Università");
                tableNameHeaders.put("country", "Paese di origine");
                tableNameHeaders.put("username", "Nome utente");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Lingua");
                tableNameHeaders.put("password", "Password");
                tableNameHeaders.put("creditCard", "Carta di credito");
            }
            case "sv" -> {
                tableNameHeaders.put("firstName", "Förnamn");
                tableNameHeaders.put("lastName", "Efternamn");
                tableNameHeaders.put("dateOfBirth", "Födelsedatum");
                tableNameHeaders.put("address", "Adress");
                tableNameHeaders.put("university", "Universitet");
                tableNameHeaders.put("country", "Ursprungsland");
                tableNameHeaders.put("username", "Användarnamn");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Språk");
                tableNameHeaders.put("password", "Lösenord");
                tableNameHeaders.put("creditCard", "Kreditkort");
            }
            case "tr" -> {
                tableNameHeaders.put("firstName", "İsim");
                tableNameHeaders.put("lastName", "Soyisim");
                tableNameHeaders.put("dateOfBirth", "Doğum tarihi");
                tableNameHeaders.put("address", "Adres");
                tableNameHeaders.put("university", "Üniversite");
                tableNameHeaders.put("country", "Ülke");
                tableNameHeaders.put("username", "Kullanıcı adı");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Dil");
                tableNameHeaders.put("password", "Şifre");
                tableNameHeaders.put("creditCard", "Kredi kartı");
            }
            case "uk" -> {
                tableNameHeaders.put("firstName", "Ім’я");
                tableNameHeaders.put("lastName", "Прізвище");
                tableNameHeaders.put("dateOfBirth", "Дата народження");
                tableNameHeaders.put("address", "Адреса");
                tableNameHeaders.put("university", "Університет");
                tableNameHeaders.put("country", "Країна походження");
                tableNameHeaders.put("username", "Ім’я користувача");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Мова");
                tableNameHeaders.put("password", "Пароль");
                tableNameHeaders.put("creditCard", "Кредитна картка");
            }
            case "hy" -> {
                tableNameHeaders.put("firstName", "Անուն");
                tableNameHeaders.put("lastName", "Ազգանուն");
                tableNameHeaders.put("dateOfBirth", "Ծննդյան ամսաթիվ");
                tableNameHeaders.put("address", "Հասցե");
                tableNameHeaders.put("university", "Համալսարան");
                tableNameHeaders.put("country", "Ծագման երկիր");
                tableNameHeaders.put("username", "Օգտվողի անուն");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Լեզու");
                tableNameHeaders.put("password", "Գաղտնաբառ");
                tableNameHeaders.put("creditCard", "Վարկային քարտ");
            }
            default -> {
                tableNameHeaders.put("firstName", "First Name");
                tableNameHeaders.put("lastName", "Last Name");
                tableNameHeaders.put("dateOfBirth", "Date of Birth");
                tableNameHeaders.put("address", "Address");
                tableNameHeaders.put("university", "University Name");
                tableNameHeaders.put("country", "Country of Origin");
                tableNameHeaders.put("username", "Username");
                tableNameHeaders.put("ip", "IP");
                tableNameHeaders.put("language", "Language Spoken");
                tableNameHeaders.put("password", "Password");
                tableNameHeaders.put("creditCard", "Credit Card");
            }
        }

        List<String> result = new ArrayList<>();
        result.add(tableNameHeaders.get("firstName"));
        result.add(tableNameHeaders.get("lastName"));
        result.add(tableNameHeaders.get("dateOfBirth"));

        if (fields != null) {
            for (String field : fields) {
                result.add(tableNameHeaders.getOrDefault(field, field));
            }
        }

        return result;
    }
}
