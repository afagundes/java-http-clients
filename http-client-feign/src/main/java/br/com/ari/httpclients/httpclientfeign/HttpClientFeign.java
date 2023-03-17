package br.com.ari.httpclients.httpclientfeign;

import br.com.ari.httpclients.ExampleUtils;
import br.com.ari.httpclients.dto.User;
import br.com.ari.httpclients.httpclientfeign.clients.UserClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class HttpClientFeign {

    private final UserClient userClient;

    public HttpClientFeign(UserClient userClient) {
        this.userClient = userClient;
    }

    public void simpleListUsers() {
        System.out.println("\nListing users using OpenFeign client:");
        List<User> users = userClient.simpleListUsers();

        System.out.println("Users returned in request: ");
        users.forEach(System.out::println);
    }

    public void listUsers() {
        System.out.println("\nListing users using OpenFeign client:");
        ResponseEntity<List<User>> response = userClient.listUsers();

        int statusCode = response.getStatusCode().value();
        System.out.println("HTTP status: " + statusCode);

        System.out.println("Users returned in request: ");
        List<User> users = Objects.requireNonNull(response.getBody());
        users.forEach(System.out::println);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

    public void createNewUser() {
        System.out.println("\nCreating a new user using OpenFeign client:");

        User user = ExampleUtils.buildUser();
        ResponseEntity<User> response = userClient.createNewUser(user);

        int statusCode = response.getStatusCode().value();
        System.out.println("HTTP status: " + statusCode);

        User createdUser = response.getBody();
        System.out.println("Created new user: " + createdUser);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

}
