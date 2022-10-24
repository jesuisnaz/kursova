package com.nklymok.constant;

/**
 * This class provides constants used in the program.
 * All values and literals are moved here to avoid so-called "Magic values".
 * "Magic values" are values that appear in code and are not described properly,
 *  which may cause confusion among developers.
 */
public class Constants {

    public static final String SPLIT_DELIM = "|";
    public static final String IMPORT_SPLIT_DELIM = "\\|";

    public static final Integer MIN_TEMPERATURE = 20;
    public static final Integer MAX_TEMPERATURE = 50;
    public static final Double MIN_HB = 0.5D;
    public static final Double MAX_HB = 100D;
    public static final int MAX_AGE = 130;
    public static final int MAX_INPUT_LEN = 100;

    public static final class FX {
        public static final String PRIMARY =  "primary";
    }

    public static final class LogTemplate {
        public static final String GREETING = "---%s--- Так триматись!\n";
    }

    public static final class Info {
        public static final String EXPORT_001 = "Successfully exported data!";
        public static final String IMPORT_001 = "Successfully imported data!";
        public static final String ABOUT = "Created by Nazarii Klymok, PZ-22 | 2022-10-25";
    }

    public static final class Error {
        public static final String EXPORT_001 = "Could not export data! Check for writing permissions.";
        public static final String IMPORT_001 = "Could not import data! Check reading permissions, data format, or if file is not empty.";

        public static final String VALIDATION_001 = "First name must not be empty and must contain less than 100 alphanumeric characters!";
        public static final String VALIDATION_002 = "Last name must not be empty and must contain less than 100 alphanumeric characters!";
        public static final String VALIDATION_003 = "Birth date must be in format YYYY-MM-DD, in the past and not more than %d years ago!".formatted(MAX_AGE);
        public static final String VALIDATION_004 = "Sex must not be empty and must be male or female!";
        public static final String VALIDATION_005 = "Temperature must be a number, above %d and below %d!".formatted(MIN_TEMPERATURE, MAX_TEMPERATURE);
        public static final String VALIDATION_006 = "Hemoglobin must be a number, above %f and below %f ".formatted(MIN_HB, MAX_HB);
    }

}
