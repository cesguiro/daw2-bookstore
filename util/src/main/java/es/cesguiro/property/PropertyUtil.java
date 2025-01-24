package es.cesguiro.property;


import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.PropertyUtilException;

public class PropertyUtil {

    private static PropertyUtil instance;

    public static final String DEFAULT_PROPERTIES_FILE = System.getProperty("default.property.file", "application.properties");
    private final String propertiesFile;
    private final PropertyProvider propertyProvider;


    private PropertyUtil(String propertiesFile, PropertyProvider propertyProvider) {
        if (propertyProvider == null) {
            throw new PropertyUtilException("Property provider is required");
        }
        if (propertiesFile == null || propertiesFile.isEmpty()) {
            throw new PropertyUtilException("Property file name is required");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader.getResource(propertiesFile) == null) {
            throw new AppFileNotFoundException("Property file '" + propertiesFile + "' not found in classpath/resources");
        }
        this.propertyProvider = propertyProvider;
        this.propertiesFile = propertiesFile;
    }

    public static synchronized PropertyUtil getInstance() {
        return getInstance(DEFAULT_PROPERTIES_FILE);
    }

    public static synchronized PropertyUtil getInstance(PropertyProvider propertyProvider) {
        return getInstance(DEFAULT_PROPERTIES_FILE, propertyProvider);
    }

    public static synchronized PropertyUtil getInstance(String propertiesFile) {
        if (instance == null) {
            instance = new PropertyUtil(propertiesFile, new DefaultPropertyProvider(propertiesFile));
        } else if (!instance.propertiesFile.equals(propertiesFile)) {
            throw new PropertyUtilException("PropertyUtil already initialized with a different property file: " + instance.propertiesFile);
        }
        return instance;
    }

    public static synchronized PropertyUtil getInstance(String propertiesFile, PropertyProvider propertyProvider) {
        if (instance == null) {
            instance = new PropertyUtil(propertiesFile, propertyProvider);
        } else if (!instance.propertiesFile.equals(propertiesFile)) {
            throw new PropertyUtilException("PropertyUtil already initialized with a different property file: " + instance.propertiesFile);
        }
        return instance;
    }

    public String getProperty(String key) {
        return propertyProvider.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        String value = propertyProvider.getProperty(key);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Resets the singleton instance (used for testing).
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

}