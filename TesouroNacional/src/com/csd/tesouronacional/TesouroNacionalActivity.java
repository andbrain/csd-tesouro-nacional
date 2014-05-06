package com.csd.tesouronacional;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.csd.tesouronacional.database.DataLayer;
import com.csd.tesouronacional.database.TituloDBHelper;

public class TesouroNacionalActivity extends Activity {
	public static TituloDBHelper helper;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesouro_nacional);
        
        DataLayer.instance.init(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tesouro_nacional, menu);
        return true;
    }
}
