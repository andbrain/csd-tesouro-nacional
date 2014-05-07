package com.csd.tesouronacional.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TituloDBHelper extends SQLiteOpenHelper {
	
	public static abstract class TituloEntry implements BaseColumns {
        public static final String TABLE_NAME = "titulos";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LETRA = "letra";
        public static final String COLUMN_NAME_NUMERO = "numero";
        public static final String COLUMN_NAME_VENCIMENTO = "vencimento";
        public static final String COLUMN_NAME_TX_COMPRA = "tx_compra";
        public static final String COLUMN_NAME_PRECO_COMPRA = "preco_compra";
    }
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String REAL_TYPE = " REAL";
	private static final String COMMA_SEP = ",";
	
	public static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + TituloEntry.TABLE_NAME + " (" +
	    		TituloEntry._ID + " INTEGER PRIMARY KEY," +
	    		TituloEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
	    		TituloEntry.COLUMN_NAME_LETRA + TEXT_TYPE + COMMA_SEP +
	    		
	    		TituloEntry.COLUMN_NAME_NUMERO + TEXT_TYPE + COMMA_SEP +
	    		TituloEntry.COLUMN_NAME_VENCIMENTO + TEXT_TYPE + COMMA_SEP +
	    		TituloEntry.COLUMN_NAME_TX_COMPRA + REAL_TYPE + COMMA_SEP +
	    		TituloEntry.COLUMN_NAME_PRECO_COMPRA + REAL_TYPE  +
	    " )";
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "titulos.db";
	
	public static final String SQL_DELETE_TITULOS =
		    "DROP TABLE IF EXISTS " + TituloEntry.TABLE_NAME;
	
	public TituloDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		System.out.println("TituloDBHelper");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
		System.out.println("onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		
	}
}
