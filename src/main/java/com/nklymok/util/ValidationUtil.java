package com.nklymok.util;

import com.nklymok.constant.Constants;
import com.nklymok.constant.Patterns;
import com.nklymok.exception.FormValidationException;
import com.nklymok.model.PersonFormInput;
import com.nklymok.model.Sex;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValidationUtil {
    private static Map<Predicate<PersonFormInput>, FormValidationException> validationMap = new HashMap<>();

    static {
        validationMap.put(ValidationUtil::validateFirstName, new FormValidationException(Constants.Error.VALIDATION_001));
        validationMap.put(ValidationUtil::validateLastName, new FormValidationException(Constants.Error.VALIDATION_002));
        validationMap.put(ValidationUtil::validateBirthDate, new FormValidationException(Constants.Error.VALIDATION_003));
        validationMap.put(ValidationUtil::validateSex, new FormValidationException(Constants.Error.VALIDATION_004));
        validationMap.put(ValidationUtil::validateTemperature, new FormValidationException(Constants.Error.VALIDATION_005));
        validationMap.put(ValidationUtil::validateHemoglobin, new FormValidationException(Constants.Error.VALIDATION_006));
    }

    public static List<FormValidationException> validateFormInput(PersonFormInput formInput) {
        List<FormValidationException> exceptions = new ArrayList<>();
        validationMap.forEach((key, value) -> {
            if (!key.test(formInput)) {
                exceptions.add(value);
            }
        });
        return exceptions;
    }

    private static boolean validateHemoglobin(PersonFormInput formInput) {
        String hemoglobinLevelStr = formInput.getHemoglobinLevel();
        if (!Objects.nonNull(hemoglobinLevelStr) ||
                hemoglobinLevelStr.isBlank() ||
                !Patterns.doubleValue.matcher(hemoglobinLevelStr).matches()) {

            return false;
        }
        double hb = Double.parseDouble(hemoglobinLevelStr);
        return hb > Constants.MIN_HB && hb < Constants.MAX_HB;
    }

    private static boolean validateTemperature(PersonFormInput formInput) {
        String temperatureStr = formInput.getTemperature();
        if (!Objects.nonNull(temperatureStr) ||
                temperatureStr.isBlank() ||
                !Patterns.number.matcher(temperatureStr).matches()) {

            return false;
        }
        int temperature = Integer.parseInt(temperatureStr);
        return temperature > Constants.MIN_TEMPERATURE && temperature < Constants.MAX_TEMPERATURE;
    }

    private static boolean validateSex(PersonFormInput formInput) {
        Sex sex = formInput.getSex();
        return sex != null;
    }

    private static boolean validateBirthDate(PersonFormInput formInput) {
        LocalDate birthDate = formInput.getBirthDate();
        if (birthDate == null) {
            return false;
        }
        LocalDate now = LocalDate.now();
        boolean isPast = now.compareTo(birthDate) >= 0;
        boolean isYoungerThan150 = now.getYear() - birthDate.getYear() < Constants.MAX_AGE;
        return isPast && isYoungerThan150;
    }

    private static boolean validateLastName(PersonFormInput formInput) {
        String lastName = formInput.getLastName();
        return !lastName.isBlank() &&
                lastName.length() < Constants.MAX_INPUT_LEN &&
                Patterns.name.matcher(lastName).matches();
    }

    private static boolean validateFirstName(PersonFormInput formInput) {
        String firstName = formInput.getFirstName();
        return !firstName.isBlank() &&
                firstName.length() < Constants.MAX_INPUT_LEN &&
                Patterns.name.matcher(firstName).matches();
    }


    public static String formattedErrors(List<FormValidationException> exceptions) {
        AtomicInteger row = new AtomicInteger(1);
        return exceptions.stream()
                .map(FormValidationException::getMessage)
                .map(m -> row.getAndIncrement() + ". " + m)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
