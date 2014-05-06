package com.csd.tesouronacional;

import com.csd.tesouronacional.database.DataLayer;
import com.csd.tesouronacional.database.TituloDBHelper;
import com.csd.tesouronacional.database.TituloDBHelper.TituloEntry;
import com.csd.tesouronacional.model.Titulo;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class TesouroNacionalActivity extends Activity {
	public static TituloDBHelper helper;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesouro_nacional);
        
        DataLayer.instance.init(getApplicationContext());
        
        Titulo titulo = new Titulo();
        titulo.setId(2);
        titulo.setLetra("PO");
        
        DataLayer.instance.insertTitulo(titulo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tesouro_nacional, menu);
        return true;
    }
}
