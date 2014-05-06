package com.csd.tesouronacional.database;

import com.csd.tesouronacional.database.TituloDBHelper.TituloEntry;
import com.csd.tesouronacional.model.Titulo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataLayer {
	public static final DataLayer instance = new DataLayer();
	
	private TituloDBHelper helper;
	
	private DataLayer(){}
	
	public void init (Context context){
		helper = new TituloDBHelper (context);
	}
	
	public void insertTitulo(Titulo titulo){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(TituloEntry.COLUMN_NAME_ID, titulo.getId() );
        values.put(TituloEntry.COLUMN_NAME_LETRA, titulo.getLetra() );
        values.put(TituloEntry.COLUMN_NAME_NUMERO, titulo.getNumero() );
        values.put(TituloEntry.COLUMN_NAME_VENCIMENTO, titulo.getFormattedVencimento());
        values.put(TituloEntry.COLUMN_NAME_TX_COMPRA, titulo.getTaxaCompra());
        values.put(TituloEntry.COLUMN_NAME_PRECO_COMPRA, titulo.getPrecoCompra());
        
        long newRowId = db.insert(TituloEntry.TABLE_NAME, "", values);
    	System.out.println("New row inserted id: " + newRowId);
    }
	
	public Titulo readTitulo (){
		SQLiteDatabase db = helper.getReadableDatabase();
		
		//String [] 
		return null;
	}
}
