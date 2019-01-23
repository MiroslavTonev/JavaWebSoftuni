package application;

import application.contracts.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RequestParser {

    private HttpRequest request;
    private BufferedReader reader;
    private UrlValidator validUrl;

    public RequestParser(UrlValidator validUrl) {
        this.validUrl = validUrl;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public HttpRequest parse()  {

        request = new HttpRequestImpl();
        try{
            List<String> validUrls = getValidUrls();
            List<String> requestLines = getRequest();
            String method = getMethod(requestLines.get(0));
            String url = getUrl(requestLines.get(0));
            LinkedHashMap<String, String> headers = getHeaders(requestLines);
            LinkedHashMap<String, String> bodyParameters = getBodyParameters(requestLines);
            LinkedHashMap<String, String> cookies = getCookies(headers);

            request.setMethod(method);
            request.setRequestUrl(url);
            request.setCookies(setRequestCookies(cookies));

            this.validUrl.setValidUrls(validUrls);

            for (Map.Entry<String,String> kvp : headers.entrySet()) {
                request.addHeaders(kvp.getKey(), kvp.getValue());
            }

            for (Map.Entry<String,String> kvp : bodyParameters.entrySet()) {
                request.addBodyParameters(kvp.getKey(), kvp.getValue());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return request;
    }

    private List<HttpCookie> setRequestCookies(LinkedHashMap<String, String> cookies) {
        List<HttpCookie> requestCookies = new ArrayList<>();

        for (Map.Entry<String,String> kvp : cookies.entrySet()) {
            HttpCookie cookie = new HttpCookie(kvp.getKey(), kvp.getValue());
            requestCookies.add(cookie);
        }

        return requestCookies;
    }

    private LinkedHashMap<String,String> getCookies(LinkedHashMap<String, String> headers) {
        LinkedHashMap<String, String> cookies = new LinkedHashMap<>();

        String cookieHeader = headers.get("Cookie");
        List<String> cookiesPairs  = Arrays.asList(cookieHeader.split("; "));


        cookiesPairs.stream()
                .map(h -> h.split("="))
                .forEach(kvp ->{
                    cookies.put(kvp[0], kvp[1]);
                });

        return cookies;

    }

    private static String getMethod(String line){
        return line.split("\\s")[0];
    }


    private List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s"));
    }

    private String getUrl(String line){
        return line.split("\\s")[1];
    }

    private List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null && line.length() > 0){
            request.add(line);
        }

        request.add(System.lineSeparator());

        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }

        return request;
    }

    private LinkedHashMap<String, String> getHeaders(List<String> request) {
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        request.stream()
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(headerKvp -> {
                    headers.put(headerKvp[0], headerKvp[1]);
                });


        return headers;
    }

    private LinkedHashMap<String,String> getBodyParameters(List<String> request) {
        LinkedHashMap<String, String> bodyParameters = new LinkedHashMap<>();

        if(request.get(request.size()-1).equals(System.lineSeparator())){
            return bodyParameters;
        }

        Arrays.stream(request.get(request.size() - 1).split("&"))
                .map(bp -> bp.split("="))
                .forEach(bpkvp ->{
                    bodyParameters.put(bpkvp[0], bpkvp[1]);
                });

        return bodyParameters;
    }
}
