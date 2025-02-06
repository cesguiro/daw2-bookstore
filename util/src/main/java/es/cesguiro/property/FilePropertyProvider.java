package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.LoadPropertiesFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilePropertyProvider implements PropertyProvider {

    private final Properties properties = new Properties();
    private final String propertiesFile;

    public FilePropertyProvider(String propertiesFile) {
        if (propertiesFile == null || propertiesFile.isEmpty()) {
            throw new AppFileNotFoundException("Properties file cannot be null or empty");
        }
        this.propertiesFile = propertiesFile;
        this.loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                throw new AppFileNotFoundException(propertiesFile);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new LoadPropertiesFileException(propertiesFile, e);
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

    @Override
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


}
