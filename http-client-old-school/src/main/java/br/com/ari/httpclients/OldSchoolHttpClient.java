package br.com.ari.httpclients;

import br.com.ari.httpclients.dto.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class OldSchoolHttpClient {

    public void listUsers() {
        System.out.println("\nListing users using the old school HttpURLConnection:");

        try {
            URL url = new URL(ExampleUtils.USER_API);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            System.out.println("HTTP status: " + responseCode);
            System.out.println("Users returned in request: ");

            List<User> users = ExampleUtils.toList(httpURLConnection.getInputStream());
            users.forEach(System.out::println);

            System.out.println("Headers:");
            httpURLConnection.getHeaderFields().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("You've entered an invalid URL here: " + ExampleUtils.USER_API);
        }
        catch (IOException e) {
            throw new RuntimeException("Error processing request", e);
        }
    }

    public void createNewUser() {
        System.out.println("\nCreating a new user using the old school HttpURLConnection:");
        User user = ExampleUtils.buildUser();

        try {
            URL url = new URL(ExampleUtils.USER_API);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            String userJson = ExampleUtils.toJson(user);

            httpURLConnection.setDoOutput(true);
            try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                outputStream.write(userJson.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("HTTP status: " + responseCode);

            User createdUser = ExampleUtils.toObject(httpURLConnection.getInputStream());
            System.out.println("Created new user: " + createdUser);

            System.out.println("Headers:");
            httpURLConnection.getHeaderFields().forEach((header, value) -> System.out.println(header + " = " + String.join(", ", value)));
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("You've entered an invalid URL here: " + ExampleUtils.USER_API);
        }
        catch (IOException e) {
            throw new RuntimeException("Error processing request", e);
        }
    }

}
