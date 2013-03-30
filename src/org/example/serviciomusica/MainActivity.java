package org.example.serviciomusica;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** 
		 * códgio añadido por el curso de Android. También debe crear una 
		 * nueva clase llamada ServicioMusica que será un Servicio
		 */
		
		Button arrancar = (Button) findViewById(R.id.boton_arrancar);
		arrancar.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				startService(new Intent(MainActivity.this, ServicioMusica.class));
			}
			
		});
		
		Button detener = (Button) findViewById(R.id.boton_detener);
		detener.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				stopService(new Intent(MainActivity.this, ServicioMusica.class));
			}
			
		});
		

		Button socorro = (Button) findViewById(R.id.boton_socorro);
		socorro.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				Notification notificacion = new Notification (R.drawable.sirena, "¡SOCORRO!", (System.currentTimeMillis()+20000));
				Intent i = new Intent(MainActivity.this,Socorro.class);
				PendingIntent intencionPendiente = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
				//PendingIntent intencionPendiente = PendingIntent.getActivity(this, 0, new Intent(this,Socorro.class), 0);
				notificacion.setLatestEventInfo(MainActivity.this, "Solicitando ayuda", "información adicional", intencionPendiente);
				// sonido de la notifación
				//notificacion.sound = Uri.parse("file://path//file.mp3");
				notificacion.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
				//notificacion.defaults |= Notification.DEFAULT_SOUND;
				
				// poner vibración a la notificacion
				// definir patrónd de vibración
				long[] vibracion = {0,50,0,50,0,50,0,150,0,150,0,150, 0,50,0,50,0,50};
				notificacion.vibrate = vibracion;	// necesita permiso en el fichero MANIFEST
						
				nm.notify(1, notificacion);

			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
