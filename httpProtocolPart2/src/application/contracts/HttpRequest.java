package application.contracts;

import application.HttpCookie;

import java.util.LinkedHashMap;
import java.util.List;

public interface HttpRequest {

    LinkedHashMap<String, String> getHeaders();

    LinkedHashMap<String, String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeaders(String headers, String value);

    void addBodyParameters(String parameter, String value);

    boolean isResource();

    void setCookies(List<HttpCookie> httpCookies);

    void printCookies();
}
