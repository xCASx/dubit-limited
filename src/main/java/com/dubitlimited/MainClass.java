package com.dubitlimited;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainClass {

    private static final String MESSAGE_BUNDLE_NAME = "MessageBundle";

    public static void main(String[] args) {
        Localiser l = new Localiser(MESSAGE_BUNDLE_NAME);

        String message = l.getMessage(new Locale("en", "US"), "greeting", "Stefan");

        System.out.println(message);
    }
}

/**
 * This class provides the ability to work with localized messages.
 */
class Localiser {

    private HashMap<Locale, ResourceBundle> resourceBundles = new HashMap<>();
    private String resourceBundleName;

    public Localiser(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    /**
     * Get localized formatted message from resource bundle.
     * @param locale - current locale
     * @param msgCode - message code
     * @param parameter - dynamic part of message
     * @return localized formatted string
     */
    public String getMessage(Locale locale, String msgCode, String parameter) {
        ResourceBundle resourceBundle = resourceBundles.get(locale);
        if (resourceBundle == null) {
            resourceBundle = createNewBundle(locale);
            resourceBundles.put(locale, resourceBundle);
        }

        return resourceBundle.getString(msgCode).replace("#{0}", parameter);
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
