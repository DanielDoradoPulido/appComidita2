package com.example.appcomidita2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginActivity extends AppCompatActivity {

    Button login;
    TextView recuContra,register;
    EditText correo,contraseña;
    String email,password;



    public static final String enviar = "com.example.myfirstApp.MESSAGE";

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mAuth = FirebaseAuth.getInstance();



        correo = findViewById(R.id.editTextCorreo);
        contraseña = findViewById(R.id.editTextContraseña);

        login = findViewById(R.id.buttonLog);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });

        register = findViewById(R.id.textViewCrearCuenta);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegister();
            }
        });

        recuContra = findViewById(R.id.textViewRecuperarContraseña);

        recuContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarContraseña();
            }
        });


    }

    public void login(){

        email = correo.getText().toString();
        password = contraseña.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()){

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();

                        if(user.isEmailVerified())
                            showMain();
                        else
                            showAlert("Error","Comprueba que has validado el correo");
                    }



                    else
                        showAlert("Error","Este correo no tiene cuenta registrada, registrese por favor");

                }
            });

        }
        else{
            Toast.makeText(this,"no estan rellenos los campos",Toast.LENGTH_SHORT).show();
        }
    }



    public void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            showMain();
                            finish();
                        }
                        else{
                            Toast.makeText(loginActivity.this, "error2", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void showRegister(){

        Intent intent = new Intent(getApplicationContext(),registerActivity.class);
        startActivity(intent);





    }

    public void showAlert(String title,String mensaje){

        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(mensaje)
                .setPositiveButton("aceptar",null);

        AlertDialog alerta = builder.create();
        alerta.show();

    }

    public void showMain(){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra(enviar,correo.getText().toString());
        startActivity(intent);

        correo.setText("");
        contraseña.setText("");


    }

    public void recuperarContraseña(){

        Intent intent = new Intent(getApplicationContext(),recuperarContrasena.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();




    }


}