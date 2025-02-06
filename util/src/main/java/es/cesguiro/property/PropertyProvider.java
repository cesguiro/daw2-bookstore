package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;

/**
 * The {@code PropertyProvider} interface defines methods for retrieving property values
 * from different sources. Implementations of this interface can retrieve properties
 * from various sources like system properties, environment variables, or configuration files.
 * <p>
 * The purpose of this interface is to abstract the logic of accessing property values
 * from different sources, allowing flexibility in how properties are retrieved
 * depending on the environment and the requirements of the application.
 * </p>
 *
 * <p>
 * Implementing classes should define how they resolve the property values,
 * including which sources they check and in what order.
 * </p>
 *
 * @author César Guijarro
 * @version 1.0
 * @since 2025-02-06
 */public interface PropertyProvider {

    /**
     * Retrieves the property value of the specified property.
     *
     * @param key the property key to look for
     * @return the value associated with the given key
     * @throws {@link KeyNotFoundException} if the key is not found
     */
    String getProperty(String key);

    /**
     * Retrieves the property value of the specified property. If the property is not found, returns the default value.
     * @param key
     * @param defaultValue
     * @return the value associated with the given key or the default value if the key is not found
     */
    String getProperty(String key, String defaultValue);
}
