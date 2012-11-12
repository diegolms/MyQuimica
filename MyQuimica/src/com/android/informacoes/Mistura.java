package com.android.informacoes;

public class Mistura {
	private int pontos;
	private String nome;
	private String composicaoMistura;
	private String misturaVisual;
	private String dica;
	private String informacao;
	private String dica2;
	private int eixoYInfo;
	private int eixoYDica;
	
	
	public Mistura(int pontos,int eixoYInfo, int eixoYDica,	 String nome, String composicaoMistura,String misturaVisual,String dica,String dica2,String informacao) {
		this.pontos = pontos;
		this.nome = nome;
		this.composicaoMistura = composicaoMistura;
		this.misturaVisual = misturaVisual;
		this.dica = dica;
		this.dica2 = dica;
		this.informacao=informacao;
		this.eixoYInfo = eixoYInfo;
		this.eixoYDica = eixoYDica;
		
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getComposicaoMistura() {
		return composicaoMistura;
	}
	public void setComposicaoMistura(String composicaoMistura) {
		this.composicaoMistura = composicaoMistura;
	}
	public String getMisturaVisual() {
		return misturaVisual;
	}
	public void setMisturaVisual(String misturaVisual) {
		this.misturaVisual = misturaVisual;
	}
	
	public String getInformacao(){
		return this.informacao;
	}
		
	public String getDica(){
		return this.dica;
	}
	public int getEixoYDica() {
		return this.eixoYDica;
	}
	
	public int getEixoYInfo(){
		return this.eixoYInfo;
	}
	
	
}
