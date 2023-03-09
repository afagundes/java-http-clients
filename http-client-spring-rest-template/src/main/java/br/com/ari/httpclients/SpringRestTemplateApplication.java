package br.com.ari.httpclients;

public class SpringRestTemplateApplication {

    public static void main(String[] args) {
        SpringRestTemplate springRestTemplate = new SpringRestTemplate();
        springRestTemplate.listUsers();
        springRestTemplate.createNewUser();
        springRestTemplate.createNewUserUsingExchangeMethod();
    }

}
