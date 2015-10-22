package br.com.myquimica.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyQuimicaDatabaseHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "myquimica";
	private static final int DATABASE_VERSION = 1;
	
	
	private static final String JOGADOR_CREATE = 
			"CREATE TABLE jogador(" +
				"id INTEGER PRIMARY KEY," +	
				"nome TEXT," +
				"pontos INTEGER" +
				");";
	
	private static final String LOG_CREATE = 
			"CREATE TABLE log(" +
				"id INTEGER PRIMARY KEY," +
				"jogador_id INTEGER," +
				"log TEXT," +
				"FOREIGN KEY(jogador_id) REFERENCES jogador(id)" +
				");";
	
	private static final String PERFIL_CREATE = 
			"CREATE TABLE perfil(" +
				"id INTEGER PRIMARY KEY," +
				"jogador_id INTEGER," +
				"ati_ref TEXT," +
				"sen_int TEXT," +
				"vis_ver TEXT," +
				"seq_glo TEXT," +
				"FOREIGN KEY(jogador_id) REFERENCES jogador(id)" +
				");";
	
	
	
	public MyQuimicaDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(JOGADOR_CREATE);
		database.execSQL(LOG_CREATE);
		database.execSQL(PERFIL_CREATE);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS jogador;");
		database.execSQL("DROP TABLE IF EXISTS log;");
		database.execSQL("DROP TABLE IF EXISTS perfil;");
		onCreate(database);
		
	}

}
