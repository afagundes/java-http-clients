package br.com.ari.httpclients;

import br.com.ari.httpclients.dto.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Java11HttpClient {

    public void listUsers() {
        System.out.println("\nListing users using Java 11 HttpClient:");

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(ExampleUtils.USER_API)).GET().build();

        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();
            System.out.println("HTTP status: " + statusCode);

            System.out.println("Users returned in request: ");
            List<User> users = ExampleUtils.toList(response.body());
            users.forEach(System.out::println);

            System.out.println("Headers:");
            response.headers().map().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewUser() {
        System.out.println("\nCreating a new user using Java 11 HttpClient:");

        User user = ExampleUtils.buildUser();
        HttpRequest.BodyPublisher userPublisher = HttpRequest.BodyPublishers.ofString(ExampleUtils.toJson(user));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(ExampleUtils.USER_API))
                .POST(userPublisher)
                .setHeader("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();
            System.out.println("HTTP status: " + statusCode);

            User createdUser = ExampleUtils.toObject(response.body());
            System.out.println("Created new user: " + createdUser);

            System.out.println("Headers:");
            response.headers().map().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewUserAsync() {
        System.out.println("\nCreating a new user asynchronously using Java 11 HttpClient:");

        User user = ExampleUtils.buildUser();
        HttpRequest.BodyPublisher userPublisher = HttpRequest.BodyPublishers.ofString(ExampleUtils.toJson(user));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(ExampleUtils.USER_API))
                .POST(userPublisher)
                .setHeader("Content-Type", "application/json")
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(response -> {
                    System.out.println("Http status: " + response.statusCode());
                    System.out.println("Headers:");
                    response.headers().map().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
                    return response;
                })
                .thenApply(HttpResponse::body)
                .thenApply(ExampleUtils::toObject)
                .thenAccept(createdUser -> System.out.println("New user created asynchronously: " + createdUser))
                .join();
    }

}
