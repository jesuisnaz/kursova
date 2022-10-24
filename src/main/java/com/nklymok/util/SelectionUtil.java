package com.nklymok.util;

import com.nklymok.model.Person;
import com.nklymok.model.Sex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Selection queries used to implement the logic required by tasks 1-7.
 */
public class SelectionUtil {

    private static final Double NORMAL_HB_LOW_BOUND_MALE = 13.2D;
    private static final Double NORMAL_HB_HIGH_BOUND_MALE = 16.6D;

    private static final Double NORMAL_HB_LOW_BOUND_FEMALE = 11.6D;
    private static final Double NORMAL_HB_HIGH_BOUND_FEMALE = 15D;

    private static final Integer NORMAL_T_LOW_BOUND = 35;
    private static final Integer NORMAL_T_HIGH_BOUND = 37;

    public static ObservableList<Person> findBelow28WithNormalTemperature(ObservableList<Person> people) {
        return people.stream()
                .filter(person -> getPersonAge(person) < 28)
                .filter(SelectionUtil::hasNormalTemperature)
                .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public static ObservableList<Person> findOldestFemalesWithSameNameAndNormalHb(ObservableList<Person> people) {
        List<String> femaleNames = people.stream()
                .filter(person -> person.getSex().equals(Sex.FEMALE))
                .map(Person::getFirstName)
                .toList();
        return people.stream()
                .filter(SelectionUtil::hasNormalHemoglobin)
                .filter(p -> Collections.frequency(femaleNames, p.getFirstName()) > 1)
                .collect(Collectors.groupingBy(Person::getFirstName))
                .values().stream()
                .flatMap(females -> females.stream().min(Comparator.comparing(Person::getBirthDate)).stream())
                .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public static ObservableList<Person> selectOlderThan40WithHigherHbAndLowerTemperature(ObservableList<Person> people) {
        return people.stream()
                .filter(person -> getPersonAge(person) > 40)
                .filter(SelectionUtil::hasHigherHemoglobin)
                .filter(SelectionUtil::hasLowerTemperature)
                .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private static Integer getPersonAge(Person person) {
        LocalDate birthDate = person.getBirthDate();
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public static ObservableList<Person> selectYoungestMalesWithNormalTemperatureAndHigherHemoglobin(ObservableList<Person> people) {
        ObservableList<Person> males = people.stream()
                .filter(person -> person.getSex().equals(Sex.MALE))
                .filter(SelectionUtil::hasNormalTemperature)
                .filter(SelectionUtil::hasHigherHemoglobin)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        Optional<LocalDate> youngest = males.stream()
                .map(Person::getBirthDate)
                .min(LocalDate::compareTo);
        if (youngest.isEmpty()) {
            return FXCollections.observableArrayList();
        }
        return males.stream()
                .filter(p -> p.getBirthDate().equals(youngest.get()))
                .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public static ObservableList<Person> selectFemalesWithHigherHemoglobin(ObservableList<Person> people) {
        return people.stream()
                .filter(p -> p.getSex().equals(Sex.FEMALE))
                .filter(SelectionUtil::hasHigherHemoglobin)
                .sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private static boolean hasNormalTemperature(Person p) {
        Integer temp = p.getTemperature();
        return NORMAL_T_LOW_BOUND <= temp && temp <= NORMAL_T_HIGH_BOUND;
    }

    private static boolean hasNormalHemoglobin(Person person) {
        Double hb = person.getHemoglobinLevel();
        if (person.getSex().equals(Sex.MALE)) {
            return NORMAL_HB_LOW_BOUND_MALE <= hb && hb <= NORMAL_HB_HIGH_BOUND_MALE;
        }
        return NORMAL_HB_LOW_BOUND_FEMALE <= hb && hb <= NORMAL_HB_HIGH_BOUND_FEMALE;
    }

    private static boolean hasLowerTemperature(Person p) {
        return p.getTemperature() < NORMAL_T_LOW_BOUND;
    }

    private static boolean hasHigherHemoglobin(Person p) {
        if (p.getSex().equals(Sex.MALE)) {
            return p.getHemoglobinLevel() > NORMAL_HB_HIGH_BOUND_MALE;
        }
        return p.getHemoglobinLevel() > NORMAL_HB_HIGH_BOUND_FEMALE;
    }

    private static boolean hasLowerHemoglobin(Person p) {
        if (p.getSex().equals(Sex.MALE)) {
            return p.getHemoglobinLevel() < NORMAL_HB_LOW_BOUND_MALE;
        }
        return p.getHemoglobinLevel() < NORMAL_HB_LOW_BOUND_FEMALE;
    }
}
