package com.nklymok.util;

import com.nklymok.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.List;

/**
 * The class that provides counting sort logic by Person body temperature
 */
public class SortingUtil {

    public static ObservableList<Person> personCountingSort(ObservableList<Person> persons) {
        int n = persons.size();

        // The output character array that will have sorted arr
        Person[] output = new Person[n];

        // Create a count array to store count of individual
        // characters and initialize count array as 0
        int count[] = new int[100];
        for (int i = 0; i < 100; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[persons.get(i).getTemperature()];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i < 100; ++i)
            count[i] += count[i - 1];

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[persons.get(i).getTemperature()] - 1] = persons.get(i);
            --count[persons.get(i).getTemperature()];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i)
            persons.set(i, output[i]);

        return FXCollections.observableArrayList(persons.sorted(Comparator.comparing(Person::getSex)));
    }

}
