package com.appmovil.placeholderview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appmovil.placeholderview.WebService.Asynchtask;
import com.appmovil.placeholderview.WebService.WebService;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ArticulosActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        String b = bundle.getString("id");
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" +bundle.getString("id"),
                datos, ArticulosActivity.this,ArticulosActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        PlaceHolderView mGalleryView = (PlaceHolderView)findViewById(R.id.galleryViewart);
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject comment=  JSONlista.getJSONObject(i);
            JSONObject results = JSONlista.getJSONObject(i);
            JSONArray JSONlista2 = results.getJSONArray("authors");
            JSONArray pdfs = results.getJSONArray("galeys");
            JSONObject pdfd=  pdfs.getJSONObject(i);
            String ListaAutores = "";
            for(int j=0; j< JSONlista2.length();j++){
                JSONObject aut=  JSONlista2.getJSONObject(j);
                ListaAutores = ListaAutores + aut.getString("nombres").toString()+", ";
            }
            mGalleryView
                   .addView(new csArticulos(this.getApplicationContext(), mGalleryView,comment.getString("title").toString(),ListaAutores,pdfd.getString("UrlViewGalley").toString()));
        }
    }
}