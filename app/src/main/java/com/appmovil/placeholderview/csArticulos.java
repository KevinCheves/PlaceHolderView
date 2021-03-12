package com.appmovil.placeholderview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@NonReusable
@Animate(Animate.CARD_TOP_IN_DESC)
@Layout(R.layout.articulos_item)
public class csArticulos {
    @View(R.id.txtTitle)
    public TextView title;
    @View(R.id.txtAutores)
    public TextView autores;
    @View(R.id.txturlpdf)
    public TextView urlpdf;
    @View(R.id.btnPdf)
    public TextView pdf;
    @Position
    int position;
    public String mtitle, mautores, mpdf, mhtml;
    public Context mContext;
    public PlaceHolderView mPlaceHolderView;

    public csArticulos(Context context, PlaceHolderView placeHolderView, String title, String autores, String pdf) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mtitle = title;
        mautores = autores;
        mpdf = pdf;
    }

    @Resolve
    protected void onResolved() {
        title.setText(mtitle);
        autores.setText(mautores);
        urlpdf.setText(mpdf);
    }
    @Click(R.id.btnPdf)
    protected void onImageViewClick3(){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        try {
            URL url = new URL(urlpdf.getText().toString());
            //establecemos la conexión con el destino
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //establecemos el método jet para nuestra conexión
            //el método setdooutput es necesario para este tipo de conexiones
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //por último establecemos nuestra conexión y cruzamos los dedos 😛
            urlConnection.connect();

            //vamos a establecer la ruta de destino para nuestra descarga
            //para hacerlo sencillo en este ejemplo he decidido descargar en
            //la raíz de la tarjeta SD
            File SDCardRoot = Environment.getDownloadCacheDirectory();

            //vamos a crear un objeto del tipo de fichero
            //donde descargaremos nuestro fichero, debemos darle el nombre que
            //queramos, si quisieramos hacer esto mas completo
            //cogeríamos el nombre del origen
            File file = new File(SDCardRoot,"ejemplo.txt");

            //utilizaremos un objeto del tipo fileoutputstream
            //para escribir el archivo que descargamos en el nuevo
            FileOutputStream fileOutput = new FileOutputStream(file);

            //leemos los datos desde la url
            InputStream inputStream = urlConnection.getInputStream();

            //obtendremos el tamaño del archivo y lo asociaremos a una
            //variable de tipo entero
            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;

            //creamos un buffer y una variable para ir almacenando el
            //tamaño temporal de este
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            //ahora iremos recorriendo el buffer para escribir el archivo de destino
            //siempre teniendo constancia de la cantidad descargada y el total del tamaño
            //con esto podremos crear una barra de progreso
            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
            }
            //cerramos
            fileOutput.close();

//y gestionamos errores
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
