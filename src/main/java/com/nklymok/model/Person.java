package com.nklymok.model;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nklymok.constant.Constants.SPLIT_DELIM;

public class Person {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Sex sex;
    private final Integer temperature;
    private final Double hemoglobinLevel;

    public Person() {
        this.firstName = "";
        this.lastName = "";
        birthDate = LocalDate.now();
        temperature = 0;
        sex = Sex.MALE;
        hemoglobinLevel = 0d;
    }

    public Person(Person p) {
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.sex = p.sex;
        this.hemoglobinLevel = p.hemoglobinLevel;
        this.temperature = p.temperature;
        this.birthDate = p.birthDate;
    }

    public Person(String firstName, String lastName, LocalDate birthDate, Sex sex, Integer temperature, Double hemoglobinLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.temperature = temperature;
        this.hemoglobinLevel = hemoglobinLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Double getHemoglobinLevel() {
        return hemoglobinLevel;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public String serializableFormat() {
        return String.join(SPLIT_DELIM, firstName, lastName, birthDate.toString(), sex.toString(), temperature.toString(), hemoglobinLevel.toString());
    }

    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Sex sex;
        private Integer temperature;
        private Double hemoglobinLevel;

        public Person build() {
            return new Person(firstName, lastName, birthDate, sex, temperature, hemoglobinLevel);
        }

        public PersonBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonBuilder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public PersonBuilder temperature(Integer temperature) {
            this.temperature = temperature;
            return this;
        }

        public PersonBuilder hemoglobinLevel(Double hemoglobinLevel) {
            this.hemoglobinLevel = hemoglobinLevel;
            return this;
        }
    }

}
