package es.cesguiro.handler;

import es.cesguiro.property.PropertyUtil;

public class CategoryHandler {

    public static final String RESOURCE = "categories";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;

}
