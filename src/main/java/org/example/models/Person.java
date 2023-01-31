package org.example.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Person {
    private int id;
    @NotEmpty(message = "Full name required")
    @Size(min = 3, message = " Wrong full name ")
    private String fullName;
    @Min(value = 1920, message = "Too small year input")
    @Max(value = 2023, message = "Impossible to input date from the future")
    private int yearOfBirth;


    public Person() {
    }

    public Person( String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}
