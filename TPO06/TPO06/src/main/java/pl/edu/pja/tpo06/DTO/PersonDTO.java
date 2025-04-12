package pl.edu.pja.tpo06.DTO;

public class PersonDTO {
    public String firstName;
    public String lastName;
    public String dateOfBirth;

    //additional fields
    public String address;
    public String universityName;
    public String countryOfOrigin;
    public String username;
    public String ip;
    public String languageSpoken;
    public String password;
    public String creditCard;

    public PersonDTO(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // getters and setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public String getUniversityName() {
        return universityName;
    }
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    public String getUsername() {
        return username;
    }
    public String getIp() {
        return ip;
    }
    public String getLanguageSpoken() {
        return languageSpoken;
    }
    public String getPassword() {
        return password;
    }
    public String getCreditCard() {
        return creditCard;
    }
}
