package com.example.pc.petcoursera;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView mensaje;
    Button boton;
    EditText texto1,texto2;
    FirebaseAuth firebase;
    ProgressDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje=(TextView)findViewById(R.id.textView);
        texto1=(EditText) findViewById(R.id.editText);
        texto2=(EditText) findViewById(R.id.editText2);
        boton=(Button) findViewById(R.id.button);
        firebase=FirebaseAuth.getInstance();
        dialogo= new ProgressDialog(this);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });



    }


    public void registrar(){

      String email= texto1.getText().toString().trim();
      String contraseña= texto2.getText().toString().trim();

      if(email.isEmpty()){

          Toast.makeText(this,"ingrese email",Toast.LENGTH_SHORT).show();
          return;
      }

        if(contraseña.isEmpty()){

            Toast.makeText(this,"ingrese contraseña",Toast.LENGTH_SHORT).show();
            return;
        }

        dialogo.setMessage("Registrando usuario.....");
        dialogo.show();

        firebase.createUserWithEmailAndPassword(email,contraseña)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this ,"registro exitoso",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(MainActivity.this ,"no se pudo registrar",Toast.LENGTH_SHORT).show();
                        }

                       dialogo.cancel();
                    }
                });
    }


}
