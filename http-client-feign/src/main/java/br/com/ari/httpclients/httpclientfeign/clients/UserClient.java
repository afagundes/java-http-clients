package br.com.ari.httpclients.httpclientfeign.clients;

import br.com.ari.httpclients.ExampleUtils;
import br.com.ari.httpclients.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="userClient", url = ExampleUtils.USER_API)
public interface UserClient {

    @GetMapping
    List<User> simpleListUsers();

    @GetMapping
    ResponseEntity<List<User>> listUsers();

    @PostMapping
    ResponseEntity<User> createNewUser(User user);

}
