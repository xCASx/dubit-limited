package com.dubitlimited;

public class MainClass {

  public static void main(String[] args) {

    Localiser l = new Localiser();

    l.add("en", "greeting", "Hello, #{0}!");
    System.out.println(l.find("en", "greeting", "Stefan"));
  }
}

import java.util.HashMap;

public class Localiser {

    private HashMap<String, HashMap<String, String>> translations;

    public Localiser() {
        this.translations = new HashMap<String, HashMap<String, String>>();
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
}
