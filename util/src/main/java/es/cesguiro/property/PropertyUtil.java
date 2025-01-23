package es.cesguiro.property;

public class PropertyUtil {
    private static PropertyUtil instance;

    private final String PROPERTY_FILE = "application.properties";
    private PropertyProvider propertyProvider = new DefaultPropertyProvider(PROPERTY_FILE);

    public static synchronized PropertyUtil getInstance() {
        if (instance == null) {
            instance = new PropertyUtil();
        }
        return instance;
    }

    public String getProperty(String key) {
        return propertyProvider.getProperty(key);
    }

    /**
     * Used for testing purposes to inject a custom PropertyProvider.
     */
    public void setPropertyProvider(PropertyProvider propertyProvider) {
        if (propertyProvider == null) {
            throw new IllegalArgumentException("Property provider cannot be null");
        }
        this.propertyProvider = propertyProvider;
    }

    /**
     * Resets the singleton instance (used for testing).
     */
    public static synchronized void resetInstance() {
        instance = null;
    }
}