package es.cesguiro.handler.impl;

import es.cesguiro.handler.GenreHandler;
import es.cesguiro.property.PropertyUtil;

public class GenreHandlerImpl implements GenreHandler {

    public static final String RESOURCE = "genres";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;
}
