package com.flywhales.progression;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	ProgressionView pv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pv = (ProgressionView) findViewById(R.id.progressionview);
		
		
	}

}
