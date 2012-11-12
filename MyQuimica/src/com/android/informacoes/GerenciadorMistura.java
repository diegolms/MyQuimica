package com.android.informacoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GerenciadorMistura {
	private List<Mistura> misturas;
	private List<Mistura> misturasSelecionadas;
	private int totalMisturas;
	

	public GerenciadorMistura(){
		misturas = new LinkedList<Mistura>();
		misturasSelecionadas = new LinkedList<Mistura>();
	}


	public void carregarMisturas() throws IOException{
//		Mistura m = new Mistura(10,80,90,"Água","HHO","H2O","2 Átomos de um elemento da Família 1A\n+\n1 Elemento da Família 6A", "DICA2","A ÁGUA é uma das substâncias mais\nabundantes em nosso planeta e pode ser\nencontrada em três estados físicos:\nsólido, líquido, e gasoso");
//		Mistura m1 = new Mistura(10,65,90,"Cloreto de Sódio","NaCl","NaCl","1 Elemento da Família 1A\n+\n1 Elemento da Família 7A","DICA2","O CLORETO DE SÓDIO popularmente\nconhecido como sal ou sal de cozinha,\né uma substância largamente utilizada\nformada na proporção de um átomo de\ncloro para cada átomo de sódio");
//		Mistura m2 = new Mistura(10,80,65,"Ácido Sulfúrico", "HHSOOOO","H2SO4","2 Átomos de um elemento da Família 1A\n+\n1 Elemento da Família 6A\n+\n4 Átomos de um elemento da Família 6A","DICA2","O ÁCIDO SULFÚRICO é um ácido\nmineral forte. É solúvel na água em\nqualquer concentração");
//		Mistura m3 = new Mistura(15,80,90,"Cloreto de Potássio", "KCl","KCl","1 Elemento da Família 1A\n+\n1 Elemento da Família 7A","DICA2","O CLORETO DE POTÁSSIO também é\nusado na culinária. Junto com o cloreto\nde sódio é vendido comercialmente como\nsal light, com baixo teor de sódio");
//		Mistura m4 = new Mistura(10,30,90,"Cloreto de Cálcio", "CaClCl","CaCl2","1 Elemento da Família 2A\n+\n2 Átomos de um elemento da Família 7A","DICA2","O CLORETO DE CÁLCIO É um sal que\nse apresenta no estado sólido à\ntemperatura ambiente.Tem muitas\naplicações comuns como em salmoura\npara máquinas de refrigeração, de pó e\ngelo nas estradas, e no cimento");
//		Mistura m5 = new Mistura(10,50,30,"Bicarbonato de Sódio", "NaHCOOO","NaHCO3","1 Elemento da Família 1A\n+\n1 Elemento da Família 1A\n+\n1 Elemento da Família 4A\n+\n3 Elementos da Família 6A","DICA2","O BICARBONATO DE SÓDIO de sódio\nou hidrogenocarbonato de sódio, é\ncomposto de fórmula NaHCO3, é um\nsólido cristalino de cor branca,\nsolúvel em água, com um sabor\nligeiramente alcalino.");
//		Mistura m6 = new Mistura(10,40,65,"Nitrato de Potássio", "KNOOO'","KNO3","1 Elemento da Família 1A\n+\n1 Elemento da Família 5A\n+\n3 Átomos de um elemento da Família 6A","DICA2","O NITRATO DE POTÁSSIO É\nutilizado pelas indústrias de alimentos que\nproduzem carnes defumadas e embutidos\n(salsichas, linguiças, etc.) a fim de evitar\na proliferação de bactéria causadora do\nbotulismo, que causa uma intoxicação\nalimentar grave");
//		Mistura m7 = new Mistura(10,100,65,"Sulfato de Cálcio", "CaSOOOO","CaSO4","1 Elemento da Família 2A\n+\n1 Elemento da Família 6A\n+\n4 Átomos de um elemento da Família 6A","DICA2","O SULFATO DE CÁLCIO É matéria\nprima para fabricação de giz");
//		Mistura m8 = new Mistura(10,50,65,"Carbonato de Sódio", "NaNaCOOO","Na2CO3","2 Átomos de um elemento da Família 1A\n+\n1 Elemento da Família 4A\n+\n3 Átomos de um elemento da Família 6A","DICA2", "O CARBONATO DE SÓDIO é usado em\nfotografia, em limpezas, no controle do\npH da água, no tratamento têxtil, como\naditivo alimentar, na fabricação de\nvidros, sabão, tintas, papel, corantes\ne no tratamento da água de piscinas");
//		Mistura m9 = new Mistura(15,90,65,"Clorofórmio", "CHClClCl","CHCl3","1 Elemento da Família 4A\n+\n1 Elemento da Família 1A\n+\n3 Átomos de um elemento da Família 7A","DICA2","O CLOROFÓRMIO é usado ilegalmente\npor inúmeras pessoas, e é popularmente\nconhecido como “lança perfume");
//		Mistura m10 = new Mistura(15,50,90,"Cloreto de Magnésio", "MgClCl","MgCl2","1 Elemento da Família 2A\n+\n2 Átomos de um elemento da Família 7A","DICA2","O CLORETO DE MAGNÉSIO é usado\npara diversos fins, como por exemplo:\nna culinária, para preparação de tofu a\npartir do leite de soja, na medicina, com\nfins terapêuticos, ou mesmo na indústria,\ncomo anti-congelante");
//		Mistura m11 = new Mistura(10,80,65,"Ácido Carboxílico", "COOH","COOH","1 Elemento da Família 4A\n+\n2 Átomos de um elemento da Família 6A\n+\n1 Elemento da Família 1A","DICA2","Na química orgânica, ÁCIDOS\nCARBOXÍLICOS são ácidos orgânicos\ncaracterizados pela presença do grupo\ncarboxila.");
//		Mistura m12 = new Mistura(10,23,65,"Hidróxido de Sódio", "NaOH","NaOH","1 Elemento da Família 1A\n+\n1 Elemento da Família 6A\n+\n1 Elemento da Família 1A","DICA2","O HIDRÓXIDO DE SÓDIO é usado\ncomo antiácido, para tratar a acidez do\nestômago porque ele tem o poder de\nneutralizar os excessos do ácido\nclorídrico do suco gástrico. Também é\nmuito usado nas receitas de culinária\ncomo fermento químico, para ser\nutilizado no cozimento de pães, bolos etc.");
//		Mistura m13 = new Mistura(10,80,90,"Peróxido de Hidrogênio", "HHOO","H2O2","2 Átomos de um elemento da Família 1A\n+\n2 Átomos de um elemento da Família 6A","DICA2","O PERÓXIDO DE HIDROGÊNIO que,\n em solução aquosa, é conhecido\ncomercialmente como água oxigenada");
		Mistura m = new Mistura(10,80,90,"Água","HHO","H2O","2 Átomos de um elemento da Família 1A\n+\n1 Elemento da Família 6A", "DICA2","A ÁGUA é uma das substâncias mais abundantes em nosso planeta e pode ser encontrada em três estados físicos: sólido, líquido, e gasoso");
		Mistura m1 = new Mistura(10,65,90,"Cloreto de Sódio","NaCl","NaCl","1 Elemento da Família 1A\n+\n1 Elemento da Família 7A","DICA2","O CLORETO DE SÓDIO popularmente conhecido como sal ou sal de cozinha, é uma substância largamente utilizada formada na proporção de um átomo de cloro para cada átomo de sódio");
		Mistura m2 = new Mistura(10,80,65,"Ácido Sulfúrico", "HHSOOOO","H2SO4","2 Átomos de um elemento da Família 1A\n+\n1 Elemento da Família 6A\n+\n4 Átomos de um elemento da Família 6A","DICA2","O ÁCIDO SULFÚRICO é um ácido mineral forte. É solúvel na água em qualquer concentração");
		Mistura m3 = new Mistura(10,80,90,"Cloreto de Potássio", "KCl","KCl","1 Elemento da Família 1A\n+\n1 Elemento da Família 7A","DICA2","O CLORETO DE POTÁSSIO também é usado na culinária. Junto com o cloreto de sódio é vendido comercialmente como sal light, com baixo teor de sódio");
		Mistura m4 = new Mistura(10,30,90,"Cloreto de Cálcio", "CaClCl","CaCl2","1 Elemento da Família 2A\n+\n2 Átomos de um elemento da Família 7A","DICA2","O CLORETO DE CÁLCIO É um sal que se apresenta no estado sólido à temperatura ambiente.Tem muitas aplicações comuns como em salmoura para máquinas de refrigeração, de pó e gelo nas estradas, e no cimento");
		Mistura m5 = new Mistura(10,50,30,"Bicarbonato de Sódio", "NaHCOOO","NaHCO3","1 Elemento da Família 1A\n+\n1 Elemento da Família 1A\n+\n1 Elemento da Família 4A\n+\n3 Elementos da Família 6A","DICA2","O BICARBONATO DE SÓDIO de sódio ou hidrogenocarbonato de sódio, é composto de fórmula NaHCO3, é um sólido cristalino de cor branca, solúvel em água, com um sabor ligeiramente alcalino.");
		Mistura m6 = new Mistura(10,40,65,"Nitrato de Potássio", "KNOOO","KNO3","1 Elemento da Família 1A\n+\n1 Elemento da Família 5A\n+\n3 Átomos de um elemento da Família 6A","DICA2","O NITRATO DE POTÁSSIO É utilizado pelas indústrias de alimentos que produzem carnes defumadas e embutidos (salsichas, linguiças, etc.) a fim de evitar a proliferação de bactéria causadora do botulismo, que causa uma intoxicação alimentar grave");
		Mistura m7 = new Mistura(10,100,65,"Sulfato de Cálcio", "CaSOOOO","CaSO4","1 Elemento da Família 2A\n+\n1 Elemento da Família 6A\n+\n4 Átomos de um elemento da Família 6A","DICA2","O SULFATO DE CÁLCIO É matéria\nprima para fabricação de giz");
		Mistura m8 = new Mistura(10,50,65,"Carbonato de Sódio", "NaNaCOOO","Na2CO3","2 Átomos de um elemento da Família 1A\n+\n1 Elemento da Família 4A\n+\n3 Átomos de um elemento da Família 6A","DICA2", "O CARBONATO DE SÓDIO é usado em fotografia, em limpezas, no controle do pH da água, no tratamento têxtil, como aditivo alimentar, na fabricação de vidros, sabão, tintas, papel, corantes e no tratamento da água de piscinas");
		Mistura m9 = new Mistura(10,90,65,"Clorofórmio", "CHClClCl","CHCl3","1 Elemento da Família 4A\n+\n1 Elemento da Família 1A\n+\n3 Átomos de um elemento da Família 7A","DICA2","O CLOROFÓRMIO é usado ilegalmente por inúmeras pessoas, e é popularmente conhecido como “lança perfume");
		Mistura m10 = new Mistura(10,50,90,"Cloreto de Magnésio", "MgClCl","MgCl2","1 Elemento da Família 2A\n+\n2 Átomos de um elemento da Família 7A","DICA2","O CLORETO DE MAGNÉSIO é usado para diversos fins, como por exemplo: na culinária, para preparação de tofu a partir do leite de soja, na medicina, com fins terapêuticos, ou mesmo na indústria, como anti-congelante");
		Mistura m11 = new Mistura(10,80,65,"Ácido Carboxílico", "COOH","COOH","1 Elemento da Família 4A\n+\n2 Átomos de um elemento da Família 6A\n+\n1 Elemento da Família 1A","DICA2","Na química orgânica, ÁCIDOS CARBOXÍLICOS são ácidos orgânicos caracterizados pela presença do grupo carboxila.");
		Mistura m12 = new Mistura(10,23,65,"Hidróxido de Sódio", "NaOH","NaOH","1 Elemento da Família 1A\n+\n1 Elemento da Família 6A\n+\n1 Elemento da Família 1A","DICA2","O HIDRÓXIDO DE SÓDIO é usado como antiácido, para tratar a acidez do estômago porque ele tem o poder de neutralizar os excessos do ácido\nclorídrico do suco gástrico. Também é muito usado nas receitas de culinária como fermento químico, para ser utilizado no cozimento de pães, bolos etc.");
		Mistura m13 = new Mistura(10,80,90,"Peróxido de Hidrogênio", "HHOO","H2O2","2 Átomos de um elemento da Família 1A\n+\n2 Átomos de um elemento da Família 6A","DICA2","O PERÓXIDO DE HIDROGÊNIO que, em solução aquosa, é conhecido comercialmente como água oxigenada");
		misturas.add(m);
		misturas.add(m1);
		misturas.add(m2);
		misturas.add(m3);
		misturas.add(m4);
		misturas.add(m5);
		misturas.add(m6);
		misturas.add(m7);
		misturas.add(m8);
		misturas.add(m9);
		misturas.add(m10);
		misturas.add(m11);
		misturas.add(m12);
		misturas.add(m13);
		/*	BufferedReader in = new BufferedReader(new FileReader(this.caminho));
		String str;
		while (in.ready()) {
			str = in.readLine();
			String[] informacoes = str.split(",");
			misturas.put(Integer.parseInt(informacoes[0]), new Mistura(Integer.parseInt(informacoes[1]),
					(informacoes[2]),
					informacoes[3], 
					informacoes[4]));
		}
		in.close();*/
	}




	public List<Mistura> getMisturas() {
		return misturas;
	}


	public List<Mistura> getMisturasSelecionadas() {
		return this.misturasSelecionadas;
	}

	public Mistura gerarMistura(){
		int t = 0 + (int)(Math.random() * misturas.size());
		Mistura misturaGerada = misturas.get(t);
		return misturaGerada;
	}

	public void adicionarMisturaSelecionada(Mistura m){
		misturasSelecionadas.add(m);

	}

	public boolean verificarMisturaRepetida(Mistura m){
		if(misturasSelecionadas.contains(m)){
			return true;
		}
		else{
			return false;
		}
	}

	
	public int totalMisturas(){
		return this.totalMisturas = misturasSelecionadas.size();
	}



}
