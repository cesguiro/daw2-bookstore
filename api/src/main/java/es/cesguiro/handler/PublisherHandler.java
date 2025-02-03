package es.cesguiro.handler;

import es.cesguiro.property.PropertyUtil;

public class PublisherHandler {

    public static final String RESOURCE = "publishers";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;
}
