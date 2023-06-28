package com.example.teste13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class MainActivity extends AppCompatActivity {

    private TextInputLayout email;
    private TextInputLayout senha;

    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.EMAIL);
        senha = findViewById(R.id.SENHA);

    }
    public void login(View v){

        //Pega a referencia da janela de texto

        String Email = email.getEditText().getText().toString();
        String Senha = senha.getEditText().getText().toString();

        if(!Email.isEmpty()){

            if(!Senha.isEmpty()){

                usuario = new Usuario();

                usuario.setEmail(Email);
                usuario.setSenha(Senha);

                moedaTela();

            }else{

                Toast.makeText(this, "Preencha a senha",Toast.LENGTH_SHORT).show();

            }

        }else{

            Toast.makeText(this, "Preencha o nome",Toast.LENGTH_SHORT).show();

        }

    }

    public void cadastrar(View v){

        Intent cadastro = new Intent(this, TelaCadastro.class);

        startActivity(cadastro);

    }

    public void recuperarSenha(View v){

        Intent recuperar = new Intent(this, TelaRecuperacaoSenha.class);

        startActivity(recuperar);

    }

    public void moedaTela(){

        auth = DbConfig.FireAuth();

        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    MostrarMoeda();

                }else{

                    try {

                        throw task.getException();

                    }catch ( FirebaseAuthInvalidUserException i){

                        Toast.makeText(MainActivity.this, "EMAIL NAO CADASTRADO",Toast.LENGTH_SHORT).show();

                    }catch ( FirebaseAuthInvalidCredentialsException i){

                        Toast.makeText(MainActivity.this, "SENHA ERRADA",Toast.LENGTH_SHORT).show();

                    }catch (Exception e){

                        Toast.makeText(MainActivity.this, "DEU RUIM",Toast.LENGTH_SHORT).show();

                    }

                }

            }

        });

    }

    public void MostrarMoeda(){

        Intent moeda = new Intent(this, TelaMoeda.class);

        startActivity(moeda);

    }

}