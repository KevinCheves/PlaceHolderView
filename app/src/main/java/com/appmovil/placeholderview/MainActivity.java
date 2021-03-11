package com.appmovil.placeholderview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appmovil.placeholderview.WebService.Asynchtask;
import com.appmovil.placeholderview.WebService.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    GalleryImage Adaptador = new GalleryImage(this,"" );

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php",
               datos, MainActivity.this,MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        
    }
}