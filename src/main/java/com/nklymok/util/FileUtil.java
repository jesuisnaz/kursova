package com.nklymok.util;

import com.nklymok.model.Person;
import com.nklymok.model.Sex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.nklymok.constant.Constants.IMPORT_SPLIT_DELIM;
import static com.nklymok.constant.Constants.SPLIT_DELIM;

public class FileUtil {

    public static boolean serializePeople(List<Person> people, File outputFile) {
        String serializedPeople = people.stream()
                .map(Person::serializableFormat)
                .collect(Collectors.joining(System.lineSeparator()));
        try (PrintWriter out = new PrintWriter(outputFile)) {
            out.println(serializedPeople);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static List<Person> deserializePeople(File inputFile) {
        List<Person> people = new ArrayList<>();
        try (Scanner in = new Scanner(inputFile)) {
            in.useDelimiter(System.lineSeparator());
            in.forEachRemaining(line -> people.add(parsePerson(line)));
            return people;
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private static Person parsePerson(String line) {
        String[] fields = line.split(IMPORT_SPLIT_DELIM);
        System.out.println(Arrays.toString(fields));
        return Person.builder()
                .firstName(fields[0])
                .lastName(fields[1])
                .birthDate(LocalDate.parse(fields[2]))
                .sex(Sex.valueOf(fields[3]))
                .temperature(Integer.parseInt(fields[4]))
                .hemoglobinLevel(Double.parseDouble(fields[5]))
                .build();
    }
}
