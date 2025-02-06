package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;

public class DefaultPropertyProvider implements PropertyProvider{


    @Override
    public String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key);
        }
        if (value == null) {
            throw new KeyNotFoundException(key);
        }
        return value;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String envValue = System.getenv(key);
        return System.getProperty(key, envValue != null ? envValue: defaultValue);
    }

}
