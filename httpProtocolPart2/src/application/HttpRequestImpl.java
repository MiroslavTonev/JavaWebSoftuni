package application;

import application.contracts.HttpRequest;
import java.util.LinkedHashMap;
import java.util.List;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private LinkedHashMap<String,String> headers;
    private LinkedHashMap<String,String> bodyParameters;
    private List<HttpCookie> cookies;


    public HttpRequestImpl() {
        headers = new LinkedHashMap<>();
        bodyParameters = new LinkedHashMap<>();
    }

    @Override
    public LinkedHashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public LinkedHashMap<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeaders(String headers, String value) {
        this.headers.put(headers, value);
    }

    public List<HttpCookie> getCookies() {
        return cookies;
    }

    public void printCookies() {

       cookies.stream()
                .forEach(c -> System.out.println(String.format("%s <-> %s",c.getKey(), c.getValue())));
    }

    @Override
    public void setCookies(List<HttpCookie> cookies) {
        this.cookies = cookies;
    }


    @Override
    public void addBodyParameters(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return false;
    }

    @Override
    public String toString() {
        return "HttpRequestImpl{" +
                "method='" + method + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", headers=" + headers +
                ", bodyParameters=" + bodyParameters +
                ", cookies=" + cookies +
                '}';
    }

}
