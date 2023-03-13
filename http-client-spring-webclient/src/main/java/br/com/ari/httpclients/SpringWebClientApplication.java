package br.com.ari.httpclients;

public class SpringWebClientApplication {

    public static void main(String[] args) {
        SpringWebClient springWebClient = new SpringWebClient();
        springWebClient.listUsers();
        springWebClient.createNewUser();
    }

}
