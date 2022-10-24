package com.nklymok.model;

import java.time.LocalDate;

public class PersonFormInput {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Sex sex;
    private final String temperature;
    private final String hemoglobinLevel;

    public Person toPerson() {
        return Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .sex(sex)
                .temperature(Integer.parseInt(temperature))
                .hemoglobinLevel(Double.parseDouble(hemoglobinLevel))
                .birthDate(birthDate)
                .build();
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

    public String getTemperature() {
        return temperature;
    }

    public String getHemoglobinLevel() {
        return hemoglobinLevel;
    }

    public PersonFormInput(String firstName, String lastName, LocalDate birthDate, Sex sex, String temperature, String hemoglobinLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.temperature = temperature;
        this.hemoglobinLevel = hemoglobinLevel;
    }

    public static PersonFormInputBuilder builder() {
        return new PersonFormInputBuilder();
    }

    public static class PersonFormInputBuilder {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Sex sex;
        private String temperature;
        private String hemoglobinLevel;

        public PersonFormInput build() {
            return new PersonFormInput(firstName, lastName, birthDate, sex, temperature, hemoglobinLevel);
        }

        public PersonFormInputBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonFormInputBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonFormInputBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonFormInputBuilder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public PersonFormInputBuilder temperature(String temperature) {
            this.temperature = temperature;
            return this;
        }

        public PersonFormInputBuilder hemoglobinLevel(String hemoglobinLevel) {
            this.hemoglobinLevel = hemoglobinLevel;
            return this;
        }
    }
}
