package com.energy.autovideo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.energy.autovideo.AutoVideoActivity;;

public class BReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// Accion a realizar al inicir el móvil
		
		Intent i = new Intent(context, AutoVideoActivity.class);  
		// aqui llama a la clase que comienza la app
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
