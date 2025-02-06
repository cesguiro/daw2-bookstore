package es.cesguiro.property;


import es.cesguiro.exception.PropertyUtilException;

public class PropertyUtil {

    private static PropertyProvider propertyProvider = new DefaultPropertyProvider();

    private PropertyUtil() {
        throw new PropertyUtilException("Utility class");
    }

    public static PropertyProvider getPropertyProvider() {
        return PropertyUtil.propertyProvider;
    }

    public static void setPropertyProvider(PropertyProvider propertyProvider) {
        if (propertyProvider == null) {
            throw new PropertyUtilException("Property provider is required");
        }
        PropertyUtil.propertyProvider = propertyProvider;
    }

    public static String getProperty(String key) {
        return getPropertyProvider().getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return getPropertyProvider().getProperty(key, defaultValue);
    }

}