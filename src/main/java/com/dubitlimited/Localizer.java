package com.dubitlimited;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class provides the ability to work with localized messages.
 * Contains map (inner cache) with lazy bundles loading.
 */
class Localizer {

    private final String resourceBundleName;
    private HashMap<Locale, ResourceBundle> resourceBundles = new HashMap<>();
    private ResourceBundle defaultResourceBundle;

    /**
     * Constructor for Localizer class
     * @param resourceBundleName - name of resource bundle
     * @throws IllegalArgumentException if resource bundle name is {@code null}
     */
    public Localizer(String resourceBundleName) {
        if (resourceBundleName == null) {
            throw new IllegalArgumentException("Resource bundle name cannot be null");
        }

        this.resourceBundleName = resourceBundleName;
    }

    /**
     * Get localized formatted message from resource bundle.
     * @param locale - current locale
     * @param msgCode - message code
     * @param parameters - dynamic part of message
     * @return localized formatted string
     * @throws IllegalArgumentException if message code is {@code null}
     */
    public String getMessage(Locale locale, String msgCode, Object... parameters) {
        if (msgCode == null) {
            throw new IllegalArgumentException("Message code cannot be null");
        }

        ResourceBundle resourceBundle = getResourceBundle(locale);
        return MessageFormat.format(resourceBundle.getString(msgCode), parameters);
    }

    /**
     * Get resource bundle for given locale
     * @param locale - current locale
     * @return localized resource bundle, or default bundle if locale is {@code null}
     */
    private ResourceBundle getResourceBundle(Locale locale) {
        ResourceBundle resourceBundle;
        if (locale == null) {
            if (defaultResourceBundle == null) {
                defaultResourceBundle = createDefaultBundle();
            }
            resourceBundle = defaultResourceBundle;
        } else {
            resourceBundle = resourceBundles.get(locale);
            if (resourceBundle == null) {
                resourceBundle = createNewBundle(locale);
                resourceBundles.put(locale, resourceBundle);
            }
        }
        return resourceBundle;
    }

    /**
     * Create new resource bundle for given locale
     * @param locale - current locale
     * @return new resource bundle
     */
    private ResourceBundle createNewBundle(Locale locale) {
        return ResourceBundle.getBundle(resourceBundleName, locale);
    }

    /**
     * Create default resource bundle
     * @return new resource bundle
     */
    private ResourceBundle createDefaultBundle() {
        return ResourceBundle.getBundle(resourceBundleName);
    }
}
