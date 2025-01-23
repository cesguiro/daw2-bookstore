package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.LoadPropertyFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefaultPropertyProvider implements PropertyProvider{


    private final Properties properties = new Properties();

    public DefaultPropertyProvider(String filename) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new AppFileNotFoundException(filename);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new LoadPropertyFileException(filename, e);
        }
    }

    @Override
    public String getProperty(String key) {
        if (key == null || key.isEmpty()) {
            throw new KeyNotFoundException("Key cannot be null or empty");
        }
        if (!properties.containsKey(key)) {
            throw new KeyNotFoundException(key);
        }
        return properties.getProperty(key);
    }

}
