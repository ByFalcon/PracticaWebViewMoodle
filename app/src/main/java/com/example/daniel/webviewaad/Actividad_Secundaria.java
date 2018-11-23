package com.example.daniel.webviewaad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Actividad_Secundaria extends AppCompatActivity {

    private String user;
    private String pass;
    private WebView webView;
    private String urlMoodle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad__secundaria);

        webView = findViewById(R.id.webView);
        //recogemos los datos de la primera actividad
        Intent i = getIntent();
        //le damos el valor de los datos recogidos a las variables de esta activity
        user = i.getStringExtra("usuario");
        pass = i.getStringExtra("contra");
        //habilitamos javaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //cargamos la url
        urlMoodle = "http://www.juntadeandalucia.es/averroes/centros-tic/18700098/moodle2/login/index.php";
        webView.loadUrl(urlMoodle);
        webView.setWebViewClient(new WebViewClient(){
            int contador = 0;
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                final String javaScript = ""+
                        "var boton = document.getElementById('loginbtn');" +
                        "var usuario = document.getElementById('username');"+
                        "var contra = document.getElementById('password');"+
                        "usuario.value = '"+user+"';"+
                        "contra.value = '"+pass+"';"+
                        "boton.click();";
                webView.loadUrl("javascript: " + javaScript);

                Log.v("URL", url);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(url.equalsIgnoreCase(urlMoodle)){
                    setResult(Actividad_Principal.RESULT_CANCELED);
                    finish();
                }
            }
        });
    }

}
