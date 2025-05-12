package pl.edu.pja.tpo09.Models.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BMIDto {
    private double weight;
    private double height;
    private int bmi;
    private String type;

    @JsonIgnore
    private double bmiDouble;

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