package com.example.teste13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class TelaCadastro extends AppCompatActivity {

    private TextInputLayout email;
    private TextInputLayout senha;

    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        email = findViewById(R.id.NOVO_EMAIL);
        senha = findViewById(R.id.NOVA_SENHA);

    }

    public void novoCadastro(View v){

        String Email = email.getEditText().getText().toString();
        String Senha = senha.getEditText().getText().toString();

        if(!Email.isEmpty()){

            if(!Senha.isEmpty()){

                usuario = new Usuario();
                usuario.setEmail(Email);
                usuario.setSenha(Senha);

                cadastro();

            }else{

                Toast.makeText(this, "Preencha a senha",Toast.LENGTH_SHORT).show();

            }

        }else{

            Toast.makeText(this, "Preencha o nome",Toast.LENGTH_SHORT).show();

        }

    }

    private void cadastro(){

        auth = DbConfig.FireAuth();

        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(TelaCadastro.this, "SUCESSO",Toast.LENGTH_SHORT).show();

                }else{

                    try {

                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException a){

                        Toast.makeText(TelaCadastro.this, "SENHA FRACA",Toast.LENGTH_SHORT).show();


                    }catch (FirebaseAuthInvalidCredentialsException i){

                        Toast.makeText(TelaCadastro.this, "EMAIL INVALIDO",Toast.LENGTH_SHORT).show();

                    }catch (FirebaseAuthUserCollisionException c){

                        Toast.makeText(TelaCadastro.this, "EMAIL JA CADASTRADO",Toast.LENGTH_SHORT).show();

                    }catch (Exception e){

                        Toast.makeText(TelaCadastro.this, "DEU RUIM",Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }

    public void voltarTelaInicial(View v){

        Intent voltar = new Intent(this, MainActivity.class);
        startActivity(voltar);

    }

}