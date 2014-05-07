package com.csd.tesouronacional.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csd.tesouronacional.database.TituloDBHelper.TituloEntry;
import com.csd.tesouronacional.model.Titulo;
import com.csd.tesouronacional.parser.TesouroParser;

public class DataLayer {
	public static final DataLayer instance = new DataLayer();

	private TituloDBHelper helper;

	private DataLayer() {
	}

	public void init(Context context) {
		helper = new TituloDBHelper(context);

		new Thread(new Runnable() {

			@Override
			public void run() {
				List<Titulo> list = null;
				try {
					list = TesouroParser.parseTesouroURL();
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (Titulo titulo : list) {
					insertTitulo(titulo);
				}

			}
		}).start();

		readTitulos();
	}

	public long insertTitulo(Titulo titulo) {
		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TituloEntry.COLUMN_NAME_ID, titulo.getId());
		values.put(TituloEntry.COLUMN_NAME_LETRA, titulo.getLetra());
		values.put(TituloEntry.COLUMN_NAME_NUMERO, titulo.getNumero());
		values.put(TituloEntry.COLUMN_NAME_VENCIMENTO,
				titulo.getFormattedVencimento());
		values.put(TituloEntry.COLUMN_NAME_TX_COMPRA, titulo.getTaxaCompra());
		values.put(TituloEntry.COLUMN_NAME_PRECO_COMPRA,
				titulo.getPrecoCompra());

		return db.insert(TituloEntry.TABLE_NAME, "", values);
	}

	public List<Titulo> readTitulos() {
		ArrayList<Titulo> list = new ArrayList<Titulo>();
		SQLiteDatabase db = helper.getReadableDatabase();

		String[] columns = { TituloEntry._ID, TituloEntry.COLUMN_NAME_ID,
				TituloEntry.COLUMN_NAME_LETRA, TituloEntry.COLUMN_NAME_NUMERO,
				TituloEntry.COLUMN_NAME_VENCIMENTO,
				TituloEntry.COLUMN_NAME_TX_COMPRA,
				TituloEntry.COLUMN_NAME_TX_COMPRA

		};

		String groupBy = TituloEntry.COLUMN_NAME_ID;
		String filter = TituloEntry.COLUMN_NAME_ID + ">" + Integer.toString(0);
		String orderBy = TituloEntry.COLUMN_NAME_TX_COMPRA + " DESC";
		Cursor c = db.query(false, TituloEntry.TABLE_NAME, columns, null, null,
				groupBy, filter, orderBy, null);

		while (c.moveToNext()) {
			Titulo titulo = new Titulo();

			titulo.setId(c.getLong(1));
			titulo.setLetra(c.getString(2));
			titulo.setNumero(c.getString(3));
			titulo.setVencimento(c.getString(4));
			titulo.setTaxaCompra(c.getDouble(5));
			titulo.setPrecoCompra(c.getDouble(6));

			System.out.println("Título::" + titulo.getLetra());
			System.out.println("Vencimento::" + titulo.getVencimento());
			System.out.println("Taxa::" + titulo.getTaxaCompra());

			list.add(titulo);
		}

		return list;
	}

	public void createDatabase() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(TituloDBHelper.SQL_CREATE_ENTRIES);
	}

	public void dropDatabase() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(TituloDBHelper.SQL_DELETE_TITULOS);
	}
}
