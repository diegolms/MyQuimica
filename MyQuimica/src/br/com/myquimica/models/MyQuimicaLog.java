package br.com.myquimica.models;

public class MyQuimicaLog {
	private long id;
	private Jogador jogador_id;
	private String log;
	
	
	
	
	public MyQuimicaLog(long id, Jogador jogador_id, String log) {
		this.id = id;
		this.jogador_id = jogador_id;
		this.log = log;
	}
	
	public MyQuimicaLog(Jogador jogador_id, String log) {
		this.jogador_id = jogador_id;
		this.log = log;
	}
	
	public MyQuimicaLog(Jogador jogador_id) {
		this.jogador_id = jogador_id;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public Jogador getJogador_id() {
		return jogador_id;
	}
	public void setJogador_id(Jogador jogador_id) {
		this.jogador_id = jogador_id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	
}
