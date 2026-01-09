package com.javaredo.controllers;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.javaredo.AppContext;
import com.javaredo.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegistrationController {
    SceneManager sm;
    HttpClient client;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public RegistrationController(AppContext ctx,SceneManager manager){
        this.client = HttpClient.newHttpClient();
        this.sm = manager;
    }

    @FXML
    private void registerNewUserRequest() throws IOException, InterruptedException{
        // if (username.getAccessibleText() == null || password.getAccessibleText() == null){
        //     return ;
        // }

        //TRYOUT

        String payload = """
                {
                    "username":"%s",
                    "password":"%s"
                }
                """.formatted(this.username.getText(),this.password.getText());

        System.out.println(payload);
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create("http://localhost:8080/api/auth/register"))
        .header("Content-Type","application/json")
        .POST(BodyPublishers.ofString(payload))
        .build();

        HttpResponse<String> response= client.send(request,BodyHandlers.ofString());

        System.out.println(response.statusCode());
    }

    @FXML
    public void goToLogInPage(){
        sm.showLogIn();
    }
}