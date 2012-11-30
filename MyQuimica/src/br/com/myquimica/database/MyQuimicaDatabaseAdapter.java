package br.com.myquimica.database;

import java.util.ArrayList;
import java.util.List;

import br.com.myquimica.core.Constants;
import br.com.myquimica.models.Jogador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyQuimicaDatabaseAdapter {
	private static final String JOGADOR = "jogador";
	
	private SQLiteDatabase database;
	private MyQuimicaDatabaseHelper dbHelper;
	private Context context;
	
	public MyQuimicaDatabaseAdapter(Context context) {
		this.context = context;
	}

	public MyQuimicaDatabaseAdapter open() throws SQLException {
		dbHelper = new MyQuimicaDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public List<Jogador> buscarListaJogadores() {
		Cursor cursor = database.query(JOGADOR,
				new String[] { "id", "nome", "pontos"},null,null, null, null, null);

		if (cursor.moveToFirst()) {
			List<Jogador> jogadores = new ArrayList<Jogador>();
			do {
				Jogador jogador = new Jogador(
						cursor.getInt(0), //id
						cursor.getString(1),//nome 
						cursor.getInt(2));//pontos, 
						
				jogadores.add(jogador);
			} while (cursor.moveToNext());
			cursor.close();
			return jogadores;
		}
		if (cursor != null)
			cursor.close();
		return null;
	}
	
	public long criarJogador(Jogador j){
		try{
			ContentValues values = new ContentValues();
			values.put("nome", j.getNome());
			values.put("pontos", j.getPontos());
			return database.insert(JOGADOR, null, values);

		}catch(Exception e){
			Log.e(Constants.TAG, e.getMessage(), e);
		}
		return -1;

	}
	

}
