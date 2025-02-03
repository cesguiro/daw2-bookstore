package es.cesguiro.handler;

import es.cesguiro.property.PropertyUtil;

public class GenreHandler {

    public static final String RESOURCE = "genres";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;
}
