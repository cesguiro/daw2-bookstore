package es.cesguiro.handler.impl;

import es.cesguiro.handler.CategoryHandler;
import es.cesguiro.property.PropertyUtil;

public class CategoryHandlerImpl implements CategoryHandler {

    public static final String RESOURCE = "categories";
    public static final String RESOURCE_PATH = PropertyUtil.getProperty("app.api.path") + "/" + RESOURCE;

}
