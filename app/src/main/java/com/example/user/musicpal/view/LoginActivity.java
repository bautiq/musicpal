package com.example.user.musicpal.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicpal.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEdit;
    private EditText passEdit;
    private Button buttonIngresar;
    private Button buttonRegistrarse;
    private TextView textViewError;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private LoginButton loginButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);/*
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(LoginActivity.this, ActivityMain.class);
        emailEdit = findViewById(R.id.edit_text_email);
        passEdit = findViewById(R.id.edit_text_password);
        buttonIngresar = findViewById(R.id.button_ingresar);
        buttonRegistrarse = findViewById(R.id.button_registrarse);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                loguearFirebaseConFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                textViewError.setText("Error al loguearse con facebook, intentelo denuevo");
                textViewError.setVisibility(View.VISIBLE);
            }
        });

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEdit.getText().toString().equals("") || passEdit.getText().toString().equals("")) {
                    textViewError.setText("Usted no ingreso nada en el campo de email o contraseña");
                    textViewError.setVisibility(View.VISIBLE);
                    return;
                }
                loguearNativamente(emailEdit.getText().toString(), passEdit.getText().toString());
            }
        });

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEdit.getText().toString().equals("") || passEdit.getText().toString().equals("")) {
                    textViewError.setText("Usted no ingreso nada en el campo de email o contraseña");
                    textViewError.setVisibility(View.VISIBLE);
                    return;
                }
                registrarUsuario(emailEdit.getText().toString(), passEdit.getText().toString());
            }
        });
        chequearSiEstaLogueado();
    }

    private void registrarUsuario(String s, String s1) {
        buttonIngresar.setVisibility(View.INVISIBLE);
        buttonRegistrarse.setVisibility(View.INVISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(s, s1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    buttonIngresar.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                    finish();
                } else {
                    buttonRegistrarse.setVisibility(View.VISIBLE);
                    buttonIngresar.setVisibility(View.VISIBLE);
                    textViewError.setText("Error al registrarse, porfavor asegurese de que escribio un email valido");
                    textViewError.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(intent);
            finish();
        }
        if (AccessToken.getCurrentAccessToken() != null) {
            loginButton.setVisibility(View.INVISIBLE);
            startActivity(intent);
            finish();
        }
    }

    private void loguearNativamente(String s, String s1) {
        firebaseAuth.signInWithEmailAndPassword(s, s1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(intent);
                    finish();
                } else {
                    buttonRegistrarse.setVisibility(View.VISIBLE);
                    buttonIngresar.setVisibility(View.VISIBLE);
                    textViewError.setText("Error al loguearse, revise si escribio bien el email y password");
                    textViewError.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void loguearFirebaseConFacebook(AccessToken accessToken) {
        loginButton.setVisibility(View.INVISIBLE);
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Autentificacion fallida, Intentelo Nuevamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
   */ }
}
