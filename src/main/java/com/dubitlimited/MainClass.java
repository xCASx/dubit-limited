package com.dubitlimited;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainClass {

  public static void main(String[] args) {

    Localiser l = new Localiser();

    l.add("en", "greeting", "Hello, #{0}!");
    System.out.println(l.find("en", "greeting", "Stefan"));
  }
}

class Localiser {

    private HashMap<String, HashMap<String, String>> translations;

    private HashMap<Locale, ResourceBundle> resourceBundles = new HashMap<>();
    private String resourceBundleName;

    public Localiser() {
        this.translations = new HashMap<String, HashMap<String, String>>();
    }

    public Localiser(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    public void add(String locale, String key, String value) {
        if(!translations.containsKey(locale)) {
            translations.put(locale, new HashMap<String, String>());
        }

        translations.get(locale).put(key, value);
    }

    public String find(String locale, String key, String parameter) {
        return translations.get(locale).get(key).replace("#{0}", parameter);
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
