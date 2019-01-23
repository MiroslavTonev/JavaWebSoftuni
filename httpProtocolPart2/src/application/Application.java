package application;

import application.contracts.HttpRequest;
import application.contracts.HttpResponse;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        UrlValidator validator = new UrlValidator();
        RequestParser parser = new RequestParser(validator);
        HttpRequest request = parser.parse();
        BuildHttpResponse responseBuilder = new BuildHttpResponse(request, validator);
        HttpResponse response = responseBuilder.getResponse();
        request.printCookies();
        System.out.println();
        System.out.println(response);


    }

}
