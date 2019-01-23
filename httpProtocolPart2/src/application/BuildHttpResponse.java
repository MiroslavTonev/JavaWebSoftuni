package application;

import application.contracts.HttpRequest;
import application.contracts.HttpResponse;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class BuildHttpResponse {

    private HttpResponse response;
    private HttpRequest request;
    private UrlValidator validUrls;

    public BuildHttpResponse(HttpRequest request, UrlValidator validUrls) {
        this.request = request;
        this.validUrls = validUrls;
    }

    public HttpResponse getResponse(){
        response = new HttpResponseImpl();
        int status = 200;
        String responseBody = "";

        String authorizationHeader = "";
        if(request.getHeaders().keySet().contains("Authorization")){
            byte[] bytes = Base64.getDecoder().decode(request.getHeaders().get("Authorization").replace("Basic ", ""));
            authorizationHeader = new String(bytes, StandardCharsets.UTF_8);
        }

        if(!validUrls.getValidUrls().contains(request.getRequestUrl())){
            status = 404;
            responseBody = "The requested functionality was not found.";

        }
        if(!request.getHeaders().keySet().contains("Authorization")){
            status = 401;
            responseBody = "You are not authorized to access the requested functionality.";
        }
        if(request.getMethod().toLowerCase().equals("post") && request.getBodyParameters().size() == 0 ){
            status = 400;
            responseBody = "There was an error with the requested functionality due to malformed request.";
        }

        String first = "";
        String second = "";
        String third = "";
        int counter = 1;
        for (Map.Entry<String, String> kvp : request.getBodyParameters().entrySet()) {
            switch(counter){
                case 1 :
                    first  = kvp.getValue();
                    break;
                case 2:
                    second = kvp.getKey() + " - " + kvp.getValue();
                    break;
                case 3:
                    third = kvp.getKey() + " - " + kvp.getValue();
                    break;
            }

            counter++;
        }

        if(responseBody.equals("")){
            //Greetings Pesho! You have successfully created Yum with quantity – 50, price – 10.
            responseBody = String.format("Greetings %s! You have successfully created %s with %s, %s.",
                    authorizationHeader,first, second, third);

        }

        this.response.setStatusCode(status);
        this.response.setContent(responseBody);


        for (Map.Entry<String,String> kvp : request.getHeaders().entrySet()) {
            this.response.addHeaders(kvp.getKey(), kvp.getValue());
        }

        return response;
    }



}
