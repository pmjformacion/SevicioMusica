package org.example.serviciomusica;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Socorro extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.socorro);
		mp.prepareAsync();
		mp.start();
	}

}
