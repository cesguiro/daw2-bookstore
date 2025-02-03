package es.cesguiro.property;

public interface PropertyProvider {

    String getProperty(String key);
    String getProperty(String key, String defaultValue);
}
