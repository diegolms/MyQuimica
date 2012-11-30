package br.com.myquimica.core;

import java.util.List;

import android.content.Context;
import br.com.myquimica.database.MyQuimicaDatabaseAdapter;
import br.com.myquimica.models.Jogador;

public class FController {
	private static FController instance = null;
	

	private FController(){

	}

	public static FController getInstance(){
		if(instance == null)
			instance = new FController();
		return instance;

	}
	
	public List<Jogador> buscarListaJogadores(Context context){
		MyQuimicaDatabaseAdapter dbHelper = new MyQuimicaDatabaseAdapter(context);
		dbHelper.open();
		List<Jogador> jogadores = dbHelper.buscarListaJogadores();
		dbHelper.close();
		return jogadores;
	}
	
	public synchronized void insertMandado(Jogador j, Context context) {
		MyQuimicaDatabaseAdapter dbHelper = new MyQuimicaDatabaseAdapter(context);
		dbHelper.open();
		dbHelper.criarJogador(j);
		dbHelper.close();
	}
}

