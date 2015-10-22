package br.com.myquimica.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyQuimicaDatabaseHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "myquimica";
	private static final int DATABASE_VERSION = 4;
	
	
	private static final String JOGADOR_CREATE = 
			"CREATE TABLE jogador(" +
				"id INTEGER PRIMARY KEY," +	
				"nome TEXT," +
				"pontos INTEGER" +
				");";	
	
	
	
	public MyQuimicaDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(JOGADOR_CREATE);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS jogador;");
		onCreate(database);
		
	}

}
