package br.com.myquimica.models;

public class Jogador {
	private long id;
	private String nome;
	private int pontos;
	
	
	public Jogador(String nome){
		this.nome = nome;
	}
	
	public Jogador(int pontos){
		this.pontos = pontos;
	}
	
	public Jogador(String nome, int pontos){
		this.nome = nome;
		this.pontos = pontos;
	
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos += pontos;
	}
	
	public Jogador(int id, String nome, int pontos){
		this.nome = nome;
		this.pontos = pontos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
