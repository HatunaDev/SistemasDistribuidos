package com.example.teste13;

import com.google.firebase.auth.FirebaseAuth;

public class DbConfig {

    private static FirebaseAuth auth;

    public static FirebaseAuth FireAuth(){

        if( auth == null){

            auth =FirebaseAuth.getInstance();

        }

        return auth;

    }

}
