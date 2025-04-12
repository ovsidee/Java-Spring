package pl.edu.pja.tpo06.services;

import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo06.DTO.PersonDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FakeDataService {
    public List<PersonDTO> generateData(int quantity, String lang, List<String> fields) {
        Faker faker = new Faker(new Locale(lang));
        List<PersonDTO> fakeData = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            PersonDTO person = new PersonDTO(faker.name().firstName(),
                                             faker.name().lastName(),
                                             faker.timeAndDate().birthday(18, 80, "YYYY/MM/dd")
            );

            if (fields != null && !fields.isEmpty()) {
                if (fields.contains("address")) person.setAddress(faker.address().fullAddress());
                if (fields.contains("university")) person.setUniversityName(faker.university().name());
                if (fields.contains("country")) person.setCountryOfOrigin(faker.country().name());
                if (fields.contains("username")) person.setUsername(faker.internet().username());
                if (fields.contains("ip")) person.setIp(faker.internet().ipV4Address());
                if (fields.contains("language")) person.setLanguageSpoken(faker.nation().language());
                if (fields.contains("password")) person.setPassword(faker.internet().password());
                if (fields.contains("creditCard")) person.setCreditCard(faker.finance().creditCard());
            }

            fakeData.add(person);
        }
        return fakeData;
    }
}
