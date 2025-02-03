package es.cesguiro.property;


import es.cesguiro.exception.PropertyUtilException;

public class PropertyUtil {

    private static PropertyUtil instance;
    private final PropertyProvider propertyProvider;

    private PropertyUtil(PropertyProvider propertyProvider) {
        if (propertyProvider == null) {
            throw new PropertyUtilException("Property provider is required");
        }
        this.propertyProvider = propertyProvider;
    }

    public static synchronized PropertyUtil getInstance() {
        return getInstance(new DefaultPropertyProvider());
    }

    public static synchronized PropertyUtil getInstance(PropertyProvider propertyProvider) {
        if (instance == null) {
            instance = new PropertyUtil(propertyProvider);
        }
        return instance;
    }

    public PropertyProvider getPropertyProvider() {
        return propertyProvider;
    }

    public String getProperty(String key) {
        return propertyProvider.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return propertyProvider.getProperty(key, defaultValue);
    }

    /**
     * Resets the singleton instance (used for testing).
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

}