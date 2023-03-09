package br.com.ari.httpclients;

public class Java11HttpClientApplication {

    public static void main(String[] args) {
        Java11HttpClient java11HttpClient = new Java11HttpClient();
        java11HttpClient.listUsers();
        java11HttpClient.createNewUser();
        java11HttpClient.createNewUserAsync();
    }

}
