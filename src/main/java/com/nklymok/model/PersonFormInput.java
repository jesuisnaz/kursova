package com.nklymok.model;

import java.time.LocalDate;

/**
 * This record is used to get information from the form and validate it.
 * Later this record is used to create an instance of Person.
 */
public record PersonFormInput(String firstName, String lastName, LocalDate birthDate, Sex sex, String temperature,
                              String hemoglobinLevel) {
    public Person toPerson() {
        return Person.builder()
                .firstName(firstName.trim())
                .lastName(lastName.trim())
                .sex(sex)
                .temperature(Integer.parseInt(temperature))
                .hemoglobinLevel(Double.parseDouble(hemoglobinLevel))
                .birthDate(birthDate)
                .build();
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

        /**
         * This class implements Builder pattern, one of the common 23 patterns defined by GoF.
         * It is used to conveniently create an instance of a class step by step, even if class fields are final.
         */
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
