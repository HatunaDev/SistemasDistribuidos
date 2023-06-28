package com.example.teste13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class TelaRecuperacaoSenha extends AppCompatActivity {

    private TextInputLayout email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_recuperacao_senha);

        email = findViewById(R.id.EMAIL);
    }

    public void RestaurarSenha(View v){

        String Email = email.getEditText().getText().toString();

        auth = DbConfig.FireAuth();

        auth.sendPasswordResetEmail(Email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        Toast.makeText(TelaRecuperacaoSenha.this, "EMAIL DE RECUPERACAO ENVIADO",Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(TelaRecuperacaoSenha.this, "EMAIL NAO CADASTRADO",Toast.LENGTH_SHORT).show();

                    }

                }

        });

    }
    public void voltarTelaInicial(View v){

        Intent voltar = new Intent(this, MainActivity.class);

        startActivity(voltar);

    }

}