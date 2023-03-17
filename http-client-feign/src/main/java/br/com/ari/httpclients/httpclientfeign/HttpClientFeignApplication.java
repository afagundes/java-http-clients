package br.com.ari.httpclients.httpclientfeign;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class HttpClientFeignApplication {

	private final HttpClientFeign httpClientFeign;

	public HttpClientFeignApplication(HttpClientFeign httpClientFeign) {
		this.httpClientFeign = httpClientFeign;
	}

	@Bean
	public ApplicationRunner run() {
		return args -> {
			httpClientFeign.simpleListUsers();
			httpClientFeign.listUsers();
			httpClientFeign.createNewUser();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HttpClientFeignApplication.class, args);
	}

}
