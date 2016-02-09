package com.dubitlimited;

import java.util.Locale;

public class MainClass {

    private static final String MESSAGE_BUNDLE_NAME = "MessageBundle";
    private static final String GREETING = "greeting";

    public static void main(String[] args) {
        Localizer localizer = new Localizer(MESSAGE_BUNDLE_NAME);

        String message = localizer.getMessage(new Locale("en", "US"), GREETING, "Stefan");

        System.out.println(message);
    }
}
