package com.example.daniel.webviewaad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Actividad_Principal extends AppCompatActivity {

    private EditText editText;
    private EditText editText2;
    private TextView textView;
    private Button button;
    private String user;
    private String pass;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private int acceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad__principal);

        button = findViewById(R.id.button);
        editText2 = findViewById(R.id.editText2);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        //Creamos las preferencias compartidas
        prefs = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        //Creamos el editor de las preferencias
        editor = prefs.edit();

        user = prefs.getString("user", "");
        pass = prefs.getString("pass", "");

        /*Intent acc = getIntent();
        acceso = acc.getIntExtra("acceso", 0);*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //introducimos las variables en
                editor.putString("user", editText.getText().toString());
                editor.putString("pass", editText2.getText().toString());
                editor.commit();

                user = prefs.getString("user", "");
                pass = prefs.getString("pass", "");

                Intent intent = new Intent(getApplicationContext(), Actividad_Secundaria.class);
                intent.putExtra("usuario", user);
                intent.putExtra("contra", pass);
                startActivity(intent);
            }
        });

        if(!user.equals("") && !pass.equals("") /*&& acceso != 1*/) {
            user = prefs.getString("user", "");
            pass = prefs.getString("pass", "");

            Intent intent = new Intent(getApplicationContext(), Actividad_Secundaria.class);
            intent.putExtra("usuario", user);
            intent.putExtra("contra", pass);
            startActivityForResult(intent, 0);
        }/*else if(acceso == 1){
            textView.setText("Los datos no son correctos");
        }*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0){
            if(resultCode == RESULT_CANCELED){
                textView.setText("Los datos no son correctos");
            }
            if (resultCode == RESULT_OK){
                textView.setText("Los datos son correctos");
            }
        }
    }
}
