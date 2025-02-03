package es.cesguiro.handler;

import es.cesguiro.property.PropertyUtil;

public class AuthorHandler {

    public static final String RESOURCE = "authors";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;
}
