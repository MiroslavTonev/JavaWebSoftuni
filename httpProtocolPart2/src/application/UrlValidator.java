package application;

import application.contracts.HttpRequest;

import java.util.List;

public class UrlValidator {
    private List<String> validUrls;

    public UrlValidator() {

    }

    public List<String> getValidUrls() {
        return validUrls;
    }

    public void setValidUrls(List<String> validUrls) {
        this.validUrls = validUrls;
    }
}
