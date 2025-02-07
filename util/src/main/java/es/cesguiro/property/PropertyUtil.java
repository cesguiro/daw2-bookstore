package es.cesguiro.property;


import es.cesguiro.exception.PropertyUtilException;

public class PropertyUtil {

    private static PropertyProvider propertyProvider;

    private PropertyUtil() {
        throw new PropertyUtilException("Utility class");
    }

    public static PropertyProvider getPropertyProvider() {
        if (propertyProvider == null) {
            PropertyUtil.propertyProvider = new DefaultPropertyProvider();
        }
        return PropertyUtil.propertyProvider;
    }

    public static void setPropertyProvider(PropertyProvider propertyProvider) {
        if (propertyProvider == null) {
            throw new PropertyUtilException("Property provider is required");
        }
        PropertyUtil.propertyProvider = propertyProvider;
    }

    /**
     * Reset the property provider to null. This method is intended for testing purposes only.
     */
    public static void resetPropertyProvider() {
        PropertyUtil.propertyProvider = null;
    }
}