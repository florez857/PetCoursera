package com.example.pc.petcoursera;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    FloatingActionButton botonFloat;

    //listview

    ListView lista;
    SwipeRefreshLayout swipe;
    ArrayAdapter<String> adaptador;

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

        String [] datos= new String[6];
        datos[0]="uno";
        datos[0]="dos";
        datos[0]="tres";
        datos[0]="cuatro";
        datos[0]="cinco";
        datos[0]="seis";

        lista=(ListView) findViewById(R.id.lista);
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adaptador);

        botonFloat=(FloatingActionButton)findViewById(R.id.BotonFlotante);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
         botonFloat.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Snackbar.make(view ,"mensaje mostrado en el snack", Snackbar.LENGTH_LONG)
                         .setAction("accion", new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 Log.e("mensaje","snack bar");
                             }
                         })
                         .show();

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
