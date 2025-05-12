package pl.edu.pja.tpo09.Models.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BMIDto {
    public double weight;
    public double height;
    public int bmi;
    public String type;

    @JsonIgnore
    public double bmiDouble;

    public BMIDto(double weight, double height, double bmiDouble, String typeOfIndex) {
        this.weight = weight;
        this.height = height;
        this.bmiDouble = bmiDouble;
        this.bmi = (int) bmiDouble;
        this.type = typeOfIndex;
    }

    public double getWeight() {
        return weight;
    }
    public double getHeight() {
        return height;
    }
    public int getBmi() {
        return bmi;
    }
    public String getType() {
        return type;
    }

    @JsonIgnore
    public String getBMIDoublePlainText() {
        return String.format("%.2f", bmiDouble);
    }
}