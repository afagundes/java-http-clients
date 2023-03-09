package br.com.ari.httpclients;

import br.com.ari.httpclients.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

public class ExampleUtils {

    private ExampleUtils() {}

    public static final String USER_API = "https://jsonplaceholder.typicode.com/users";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static List<User> toList(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {});
        }
        catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }

    public static User toObject(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, User.class);
        }
        catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }

    public static String toJson(User user) {
        try {
            return OBJECT_MAPPER.writeValueAsString(user);
        }
        catch (JsonProcessingException exc) {
            throw new UncheckedIOException(exc);
        }
    }

    public static User buildUser() {
        User.Address address = new User.Address(
                "Rua http 200",
                "apto POST",
                "São Paulo",
                "00200-404",
                new User.Address.Geo("-257422", "25566987"));

        User.Company company = new User.Company(
                "My Great Company",
                "We develop software!",
                "sofware, development, java");

        return new User(null,
                "Archimedes Fagundes Junior",
                "archimedes.junior",
                "archimedes.junior@dev.com",
                address,
                "11 95523-9999",
                "https://todo",
                company);
    }

}
