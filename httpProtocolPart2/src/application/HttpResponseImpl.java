package application;

import application.contracts.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;


public class HttpResponseImpl implements HttpResponse {

    private LinkedHashMap<String,String> headers;
    private int statusCode;
    private String content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
    }

    @Override
    public LinkedHashMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content.getBytes();
    }

    @Override
    public byte[] getBytes() {
        return this.toString().getBytes();
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void addHeaders(String header, String value) {
        this.headers.put(header, value);

    }

    @Override
    public String toString() {

        String authorizationHeader = "";
        if(this.getHeaders().keySet().contains("Authorization")){
            byte[] bytes = Base64.getDecoder().decode(this.getHeaders().get("Authorization").replace("Basic ", ""));
            authorizationHeader = new String(bytes, StandardCharsets.UTF_8);
        }

        String responseBody = "";
        String status = "";
       switch (statusCode){
           case 200:
               status = "OK";
               break;
           case 404:
               status = "Not Found";
               break;
           case 401:
               status = "Unauthorized";
               break;
           case 400:
               status = "Bad Request";
               break;
       }


       StringBuilder responseString = new StringBuilder();
       StringBuilder mandatoryHeader = new StringBuilder();
        for (Map.Entry<String,String> kvp : headers.entrySet()) {
            if(kvp.getKey().equals("Date") || kvp.getKey().equals("Host") || kvp.getKey().equals("Content-Type")){
                mandatoryHeader.append(kvp.getKey() + ": " + kvp.getValue() + System.lineSeparator());
            }

        }


        responseString.append("HTTP/1.1 " + statusCode + " " + status + System.lineSeparator());
        responseString.append(mandatoryHeader);
        responseString.append(System.lineSeparator());
        responseString.append(content + System.lineSeparator());

       return responseString.toString();
    }
}
