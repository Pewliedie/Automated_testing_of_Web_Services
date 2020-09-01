package com.epam.cdp.kzta2020.common;

public class Configuration {
    private String baseURI;
    private String jsonPhBaseURI;
    private String expectedContentHeader;

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getJsonPhBaseURI() {
        return jsonPhBaseURI;
    }

    public void setJsonPhBaseURI(String jsonPhBaseURI) {
        this.jsonPhBaseURI = jsonPhBaseURI;
    }

    public String getExpectedContentHeader() {
        return expectedContentHeader;
    }

    public void setExpectedContentHeader(String expectedContentHeader) {
        this.expectedContentHeader = expectedContentHeader;
    }
}
