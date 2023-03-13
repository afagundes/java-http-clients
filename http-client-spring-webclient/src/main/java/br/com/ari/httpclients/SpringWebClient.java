package br.com.ari.httpclients;

import br.com.ari.httpclients.dto.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

public class SpringWebClient {

    public void listUsers() {
        System.out.println("\nListing users using Spring WebFlux:");

        WebClient webClient = WebClient.create(ExampleUtils.USER_API);
        ResponseEntity<List<User>> response = webClient.get().retrieve().toEntityList(User.class).block();

        Objects.requireNonNull(response);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("HTTP status: " + statusCode.value());
        System.out.println("Is status code 2xx successful? " + statusCode.is2xxSuccessful());

        System.out.println("Users returned in request: ");
        List<User> users = Objects.requireNonNull(response.getBody());
        users.forEach(System.out::println);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

    public void createNewUser() {
        System.out.println("\nCreating a new user using Spring WebClient:");

        User user = ExampleUtils.buildUser();
        WebClient webClient = WebClient.create(ExampleUtils.USER_API);
        ResponseEntity<User> response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .retrieve()
                .toEntity(User.class)
                .block();

        Objects.requireNonNull(response);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("HTTP status: " + statusCode.value());
        System.out.println("Is status code 2xx successful? " + statusCode.is2xxSuccessful());

        User createdUser = response.getBody();
        System.out.println("Created new user: " + createdUser);

        System.out.println("Headers:");
        response.getHeaders().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
    }

}
