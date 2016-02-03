package com.dubitlimited;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainClass {

    private static final String MESSAGE_BUNDLE_NAME = "MessageBundle";

    public static void main(String[] args) {
        Localizer localizer = new Localizer(MESSAGE_BUNDLE_NAME);

        String message = localizer.getMessage(new Locale("en", "US"), "greeting", "Stefan");

        System.out.println(message);
    }
}

/**
 * This class provides the ability to work with localized messages.
 */
class Localizer {

    private final String resourceBundleName;
    private HashMap<Locale, ResourceBundle> resourceBundles = new HashMap<>();

    public Localizer(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    /**
     * Get localized formatted message from resource bundle.
     * @param locale - current locale
     * @param msgCode - message code
     * @param parameters - dynamic part of message
     * @return localized formatted string
     */
    public String getMessage(Locale locale, String msgCode, Object... parameters) {
        ResourceBundle resourceBundle = resourceBundles.get(locale);
        if (resourceBundle == null) {
            resourceBundle = createNewBundle(locale);
            resourceBundles.put(locale, resourceBundle);
        }
        return MessageFormat.format(resourceBundle.getString(msgCode), parameters);
    }

    /**
     * Create new resource bundle for given locale
     * @param locale - current locale
     * @return new resource bundle
     */
    private ResourceBundle createNewBundle(Locale locale) {
        return ResourceBundle.getBundle(resourceBundleName, locale);
    }
}
