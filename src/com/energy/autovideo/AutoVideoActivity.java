package com.energy.autovideo;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

public class AutoVideoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
        	//Me espero 20 segundos a que el SmartTV se inicie (la tarjeta SD o el USB tardan unos segundos en leer)
			Thread.sleep(9000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //ruta para smart tv dongle
        String ruta="/mnt/sdcard/Movies";
        
        //búsqueda en SD Externa
        ArrayList myList = new ArrayList<String>();  
        File file = new File(ruta);       
        File list[] = file.listFiles();
        
        //si no existe la carpeta en la SD, busco en USB
        if (list == null)
        {
        	ruta="/mnt/sda/Movies";
        	myList = new ArrayList<String>();  
            file = new File(ruta);       
            list = file.listFiles();
        }      
        
        //si no existe la carpeta en la SD, busco en USB (Dongle)
        if (list == null)
        {
        	ruta="/mnt/sda1/Movies";
        	myList = new ArrayList<String>();
            file = new File(ruta);
            list = file.listFiles();
        }
        //mejora para búsqueda en SD interna
        if (list == null)
        {
        	ruta="/mnt/sdcard/Movies";
        	myList = new ArrayList<String>();  
            file = new File(ruta);       
            list = file.listFiles();
        }
        //si tampoco hay archivos en el el USB, cierro aplicación
        if (list == null)
        {
        	Toast.makeText(this.getBaseContext(), "No se han encontrado archivos de video en SD ni USB", Toast.LENGTH_LONG).show();
        	this.finish();
        }
        else
        {
	        for( int i=0; i< list.length; i++)
	        {
	            myList.add(list[i].getName());
	        }
	        
	        int i=0;
	        String video = myList.get(i).toString();
	        //anti-temporales
	        while (video.contains("Thumbs.db") || video.contains("DS_Store"))
	        {
	        	i++;
	        	video = myList.get(i).toString();
	        }
	        vidopen(video, ruta);
        }
       
    }
    


    public void vidopen(String video, String ruta){

	    Uri uri = Uri.parse(ruta+"/"+video);
	    Intent intent = new Intent(Intent.ACTION_VIEW);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
	    intent.setData(uri);
	    intent.setDataAndType(uri, "video/*");
	    startActivity(intent);
    }
}