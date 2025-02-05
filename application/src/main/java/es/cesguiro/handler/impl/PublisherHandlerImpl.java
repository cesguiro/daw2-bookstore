package es.cesguiro.handler.impl;

import es.cesguiro.handler.PublisherHandler;
import es.cesguiro.property.PropertyUtil;

public class PublisherHandlerImpl implements PublisherHandler {

    public static final String RESOURCE = "publishers";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;
}
