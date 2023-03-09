package br.com.ari.httpclients;

import br.com.ari.httpclients.dto.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

public class SpringRestTemplate {

    public void listUsers() {
        System.out.println("\nListing users using Spring RestTemplate:");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(URI.create(ExampleUtils.USER_API), User[].class);

        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("HTTP status: " + statusCode.value());
        System.out.println("Is status code 2xx successful? " + statusCode.is2xxSuccessful());

        System.out.println("Users returned in request: ");
        User[] users = Objects.requireNonNull(response.getBody());
        Arrays.stream(users).forEach(System.out::println);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

    public void createNewUser() {
        System.out.println("\nCreating a new user using Spring RestTemplate:");

        User user = ExampleUtils.buildUser();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.postForEntity(URI.create(ExampleUtils.USER_API), user, User.class);

        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("HTTP status: " + statusCode.value());
        System.out.println("Is status code 2xx successful? " + statusCode.is2xxSuccessful());

        User createdUser = response.getBody();
        System.out.println("Created new user: " + createdUser);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

    public void createNewUserUsingExchangeMethod() {
        System.out.println("\nCreating a new user using Spring RestTemplate's exchange method:");

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<User> request = RequestEntity
                .post(URI.create(ExampleUtils.USER_API))
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer XYZ1234abc")
                .body(ExampleUtils.buildUser());

        ResponseEntity<User> response = restTemplate.exchange(request, User.class);

        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("HTTP status: " + statusCode.value());
        System.out.println("Is status code 2xx successful? " + statusCode.is2xxSuccessful());

        User createdUser = response.getBody();
        System.out.println("Created new user with exchange method: " + createdUser);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

}
