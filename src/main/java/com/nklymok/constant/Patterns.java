package com.nklymok.constant;

import java.util.regex.Pattern;

/**
 * This class provides RegEx patterns to quickly validate user inputs.
 */
public class Patterns {
    public static final Pattern name = Pattern.compile("^[a-zA-Z0-9]*$");
    public static final Pattern number = Pattern.compile("^\\d*$");
    public static final Pattern doubleValue = Pattern.compile("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?");
}
