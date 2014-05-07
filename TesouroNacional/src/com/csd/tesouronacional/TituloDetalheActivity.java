package com.csd.tesouronacional;

import com.csd.tesouronacional.model.Titulo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class TituloDetalheActivity extends Activity {

	//private  myGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_titulo_detalhe);
//		myGrid = (GridView) findViewById(R.id.gridView1);
//		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, numbers);
// 
//		gridView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.titulo_detalhe, menu);
		return true;
	}

}
