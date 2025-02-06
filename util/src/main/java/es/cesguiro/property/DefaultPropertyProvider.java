package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.LoadPropertiesFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * DefaultPropertyProvider is an implementation of {@link PropertyProvider} that retrieves properties from three sources:
 * system properties (System.getProperty), environment variables (System.getenv), and a properties file (application.properties).
 * <p>
 * The class will attempt to retrieve a property in the following order:
 * <ol>
 *   <li>System.getProperty</li>
 *   <li>System.getenv()</li>
 *   <li>application.properties loaded during initialization</li>
 * </ol>
 * If the property is not found in any of these sources, a {@link KeyNotFoundException} will be thrown.
 *
 * @author: César Guijarro
 * @date: 2025-02-06
 * @version: 1.0
 */
public class DefaultPropertyProvider implements PropertyProvider{

    private final Properties properties = new Properties();
    private final String propertiesFile = "application.properties";

    /**
     * Constructor that loads the properties file during initialization.
     * This file must be located in the classpath as {@code application.properties}.
     */
    public DefaultPropertyProvider() {
        this.loadProperties();
    }

    /**
     * Loads the properties file from the classpath.
     * @throws  {@link AppFileNotFoundException} if the properties file cannot be found or loaded.
     */
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

    /**
     * {@inheritDoc}
     * <p>
     * In this implementation, the property is resolved in the following order:
     * <ol>
     *   <li>From system properties using {@link System#getProperty(String)}</li>
     *   <li>If not found, from environment variables using {@link System#getenv(String)}</li>
     *   <li>If still not found, from the "application.properties" file on the classpath</li>
     * </ol>
     * </p>
     */
    @Override
    public String getProperty(String key) {
        if (key == null || key.isEmpty()) {
            throw new KeyNotFoundException("Key cannot be null or empty");
        }
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key);
        }
        if (value == null) {
            if (!properties.containsKey(key)) {
                throw new KeyNotFoundException(key);
            }
            value = properties.getProperty(key);
        }
        return value;
    }


    /**
     * {@inheritDoc}
     * <p>
     * In this implementation, the property is resolved in the following order:
     * <ol>
     *   <li>From system properties using {@link System#getProperty(String)}</li>
     *   <li>If not found, from environment variables using {@link System#getenv(String)}</li>
     *   <li>If still not found, from the "application.properties" file on the classpath</li>
     * </ol>
     * If the property cannot be found in any of these sources, the default value is returned.
     * </p>
     */
    @Override
    public String getProperty(String key, String defaultValue) {
        String envValue = System.getenv(key);
        return System.getProperty(key, envValue != null ? envValue: properties.getProperty(key, defaultValue));
    }

}
