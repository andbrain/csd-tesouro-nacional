package com.csd.tesouronacional;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.csd.tesouronacional.database.DataLayer;
import com.csd.tesouronacional.database.TituloDBHelper;
import com.csd.tesouronacional.model.Titulo;
import com.csd.tesouronacional.parser.TesouroParser;

public class TesouroNacionalActivity extends Activity {
	public static TituloDBHelper helper;
	private Spinner yearSpinner;
	private EditText taxaRoi;
	private EditText minCompra;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        System.out.println("Activity Created!!");
        setContentView(R.layout.activity_tesouro_nacional);
        
        DataLayer.instance.init(getApplicationContext());
        
		List<Titulo> list = null;

		try {
			list = TesouroParser.parseTesouroURL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Titulo titulo : list) {
			System.out.println(titulo.getLetra());
			//DataLayer.instance.insertTitulo(titulo);
		}

        yearSpinner = (Spinner) findViewById(R.id.ano);
        taxaRoi = (EditText) findViewById(R.id.minRoi);
        minCompra = (EditText) findViewById(R.id.minCompra);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tesouro_nacional, menu);
        return true;
    }
}
