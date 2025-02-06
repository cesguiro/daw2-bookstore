package es.cesguiro.handler.impl;

import es.cesguiro.handler.AuthorHandler;
import es.cesguiro.property.PropertyUtil;

public class AuthorHandlerImpl implements AuthorHandler {

    public static final String RESOURCE = "authors";
    public static final String RESOURCE_PATH = PropertyUtil.getProperty("app.api.path") + "/" + RESOURCE;
}
