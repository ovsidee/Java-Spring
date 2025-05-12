package pl.edu.pja.tpo09.Models.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BMRDto {
    private String gender;
    private double weight;
    private double height;
    private int age;
    private int bmr;

    @JsonIgnore
    private double bmrDouble;

    public BMRDto(String gender, double weight, double height, int age, double bmrDouble) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmrDouble = bmrDouble;
        this.bmr = (int) bmrDouble;
    }

    public String getGender() {
        return gender;
    }
    public double getWeight() {
        return weight;
    }
    public double getHeight() {
        return height;
    }
    public int getAge() {
        return age;
    }
    public int getBmr() {
        return bmr;
    }

    @JsonIgnore
    public String getBmrKcalPlainText() {
        return bmr + "kcal";
    }
}