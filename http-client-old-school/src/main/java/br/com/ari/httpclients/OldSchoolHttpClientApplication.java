package br.com.ari.httpclients;

public class OldSchoolHttpClientApplication {

    public static void main(String[] args) {
        OldSchoolHttpClient oldSchoolHttpClient = new OldSchoolHttpClient();
        oldSchoolHttpClient.listUsers();
        oldSchoolHttpClient.createNewUser();
    }

}
