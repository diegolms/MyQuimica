package br.com.myquimica.models;

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
//		Mistura m = new Mistura(10,80,90,"�gua","HHO","H2O","2 �tomos de um elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 6A", "DICA2","A �GUA � uma das subst�ncias mais\nabundantes em nosso planeta e pode ser\nencontrada em tr�s estados f�sicos:\ns�lido, l�quido, e gasoso");
//		Mistura m1 = new Mistura(10,65,90,"Cloreto de S�dio","NaCl","NaCl","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 7A","DICA2","O CLORETO DE S�DIO popularmente\nconhecido como sal ou sal de cozinha,\n� uma subst�ncia largamente utilizada\nformada na propor��o de um �tomo de\ncloro para cada �tomo de s�dio");
//		Mistura m2 = new Mistura(10,80,65,"�cido Sulf�rico", "HHSOOOO","H2SO4","2 �tomos de um elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 6A\n+\n4 �tomos de um elemento da Fam�lia 6A","DICA2","O �CIDO SULF�RICO � um �cido\nmineral forte. � sol�vel na �gua em\nqualquer concentra��o");
//		Mistura m3 = new Mistura(15,80,90,"Cloreto de Pot�ssio", "KCl","KCl","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 7A","DICA2","O CLORETO DE POT�SSIO tamb�m �\nusado na culin�ria. Junto com o cloreto\nde s�dio � vendido comercialmente como\nsal light, com baixo teor de s�dio");
//		Mistura m4 = new Mistura(10,30,90,"Cloreto de C�lcio", "CaClCl","CaCl2","1 Elemento da Fam�lia 2A\n+\n2 �tomos de um elemento da Fam�lia 7A","DICA2","O CLORETO DE C�LCIO � um sal que\nse apresenta no estado s�lido �\ntemperatura ambiente.Tem muitas\naplica��es comuns como em salmoura\npara m�quinas de refrigera��o, de p� e\ngelo nas estradas, e no cimento");
//		Mistura m5 = new Mistura(10,50,30,"Bicarbonato de S�dio", "NaHCOOO","NaHCO3","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 4A\n+\n3 Elementos da Fam�lia 6A","DICA2","O BICARBONATO DE S�DIO de s�dio\nou hidrogenocarbonato de s�dio, �\ncomposto de f�rmula NaHCO3, � um\ns�lido cristalino de cor branca,\nsol�vel em �gua, com um sabor\nligeiramente alcalino.");
//		Mistura m6 = new Mistura(10,40,65,"Nitrato de Pot�ssio", "KNOOO'","KNO3","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 5A\n+\n3 �tomos de um elemento da Fam�lia 6A","DICA2","O NITRATO DE POT�SSIO �\nutilizado pelas ind�strias de alimentos que\nproduzem carnes defumadas e embutidos\n(salsichas, lingui�as, etc.) a fim de evitar\na prolifera��o de bact�ria causadora do\nbotulismo, que causa uma intoxica��o\nalimentar grave");
//		Mistura m7 = new Mistura(10,100,65,"Sulfato de C�lcio", "CaSOOOO","CaSO4","1 Elemento da Fam�lia 2A\n+\n1 Elemento da Fam�lia 6A\n+\n4 �tomos de um elemento da Fam�lia 6A","DICA2","O SULFATO DE C�LCIO � mat�ria\nprima para fabrica��o de giz");
//		Mistura m8 = new Mistura(10,50,65,"Carbonato de S�dio", "NaNaCOOO","Na2CO3","2 �tomos de um elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 4A\n+\n3 �tomos de um elemento da Fam�lia 6A","DICA2", "O CARBONATO DE S�DIO � usado em\nfotografia, em limpezas, no controle do\npH da �gua, no tratamento t�xtil, como\naditivo alimentar, na fabrica��o de\nvidros, sab�o, tintas, papel, corantes\ne no tratamento da �gua de piscinas");
//		Mistura m9 = new Mistura(15,90,65,"Clorof�rmio", "CHClClCl","CHCl3","1 Elemento da Fam�lia 4A\n+\n1 Elemento da Fam�lia 1A\n+\n3 �tomos de um elemento da Fam�lia 7A","DICA2","O CLOROF�RMIO � usado ilegalmente\npor in�meras pessoas, e � popularmente\nconhecido como �lan�a perfume");
//		Mistura m10 = new Mistura(15,50,90,"Cloreto de Magn�sio", "MgClCl","MgCl2","1 Elemento da Fam�lia 2A\n+\n2 �tomos de um elemento da Fam�lia 7A","DICA2","O CLORETO DE MAGN�SIO � usado\npara diversos fins, como por exemplo:\nna culin�ria, para prepara��o de tofu a\npartir do leite de soja, na medicina, com\nfins terap�uticos, ou mesmo na ind�stria,\ncomo anti-congelante");
//		Mistura m11 = new Mistura(10,80,65,"�cido Carbox�lico", "COOH","COOH","1 Elemento da Fam�lia 4A\n+\n2 �tomos de um elemento da Fam�lia 6A\n+\n1 Elemento da Fam�lia 1A","DICA2","Na qu�mica org�nica, �CIDOS\nCARBOX�LICOS s�o �cidos org�nicos\ncaracterizados pela presen�a do grupo\ncarboxila.");
//		Mistura m12 = new Mistura(10,23,65,"Hidr�xido de S�dio", "NaOH","NaOH","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 6A\n+\n1 Elemento da Fam�lia 1A","DICA2","O HIDR�XIDO DE S�DIO � usado\ncomo anti�cido, para tratar a acidez do\nest�mago porque ele tem o poder de\nneutralizar os excessos do �cido\nclor�drico do suco g�strico. Tamb�m �\nmuito usado nas receitas de culin�ria\ncomo fermento qu�mico, para ser\nutilizado no cozimento de p�es, bolos etc.");
//		Mistura m13 = new Mistura(10,80,90,"Per�xido de Hidrog�nio", "HHOO","H2O2","2 �tomos de um elemento da Fam�lia 1A\n+\n2 �tomos de um elemento da Fam�lia 6A","DICA2","O PER�XIDO DE HIDROG�NIO que,\n em solu��o aquosa, � conhecido\ncomercialmente como �gua oxigenada");
		Mistura m = new Mistura(10,80,90,"�gua","HHO","H2O","2 �tomos de um elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 6A", "2 Subst�ncias Simples","A �GUA � uma das subst�ncias mais abundantes em nosso planeta e pode ser encontrada em tr�s estados f�sicos: s�lido, l�quido, e gasoso");
		Mistura m1 = new Mistura(10,65,90,"Cloreto de S�dio","NaCl","NaCl","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 7A","1 Halog�nio\n+\n1 Metal Alcalino","O CLORETO DE S�DIO popularmente conhecido como sal ou sal de cozinha, � uma subst�ncia largamente utilizada formada na propor��o de um �tomo de cloro para cada �tomo de s�dio");
		Mistura m2 = new Mistura(10,80,65,"�cido Sulf�rico", "HHSOOOO","H2SO4","2 �tomos de um elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 6A\n+\n4 �tomos de um elemento da Fam�lia 6A","2 Substancias Simples\n+\n1 N�o-Metal\n+\n4 N�o-Metais","O �CIDO SULF�RICO � um �cido mineral forte. � sol�vel na �gua em qualquer concentra��o");
		Mistura m3 = new Mistura(10,80,90,"Cloreto de Pot�ssio", "KCl","KCl","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 7A","1 Metal Alcalino\n+\n1 Halog�nio","O CLORETO DE POT�SSIO tamb�m � usado na culin�ria. Junto com o cloreto de s�dio � vendido comercialmente como sal light, com baixo teor de s�dio");
		Mistura m4 = new Mistura(10,30,90,"Cloreto de C�lcio", "CaClCl","CaCl2","1 Elemento da Fam�lia 2A\n+\n2 �tomos de um elemento da Fam�lia 7A","1 Metal Alcalino Terroso\n+\n2 Halog�nios","O CLORETO DE C�LCIO � um sal que se apresenta no estado s�lido � temperatura ambiente.Tem muitas aplica��es comuns como em salmoura para m�quinas de refrigera��o, de p� e gelo nas estradas, e no cimento");
		Mistura m5 = new Mistura(10,50,30,"Bicarbonato de S�dio", "NaHCOOO","NaHCO3","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 4A\n+\n3 Elementos da Fam�lia 6A","1 Metal Alcalino\n+\n1 Substancia Simples\n+\n1 N�o-Metal\n+\n3 N�o-Metais","O BICARBONATO DE S�DIO de s�dio ou hidrogenocarbonato de s�dio, � composto de f�rmula NaHCO3, � um s�lido cristalino de cor branca, sol�vel em �gua, com um sabor ligeiramente alcalino.");
		Mistura m6 = new Mistura(10,40,65,"Nitrato de Pot�ssio", "KNOOO","KNO3","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 5A\n+\n3 �tomos de um elemento da Fam�lia 6A","DICA2","O NITRATO DE POT�SSIO � utilizado pelas ind�strias de alimentos que produzem carnes defumadas e embutidos (salsichas, lingui�as, etc.) a fim de evitar a prolifera��o de bact�ria causadora do botulismo, que causa uma intoxica��o alimentar grave");
		Mistura m7 = new Mistura(10,100,65,"Sulfato de C�lcio", "CaSOOOO","CaSO4","1 Elemento da Fam�lia 2A\n+\n1 Elemento da Fam�lia 6A\n+\n4 �tomos de um elemento da Fam�lia 6A","DICA2","O SULFATO DE C�LCIO � mat�ria\nprima para fabrica��o de giz");
		Mistura m8 = new Mistura(10,50,65,"Carbonato de S�dio", "NaNaCOOO","Na2CO3","2 �tomos de um elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 4A\n+\n3 �tomos de um elemento da Fam�lia 6A","DICA2", "O CARBONATO DE S�DIO � usado em fotografia, em limpezas, no controle do pH da �gua, no tratamento t�xtil, como aditivo alimentar, na fabrica��o de vidros, sab�o, tintas, papel, corantes e no tratamento da �gua de piscinas");
		Mistura m9 = new Mistura(10,90,65,"Clorof�rmio", "CHClClCl","CHCl3","1 Elemento da Fam�lia 4A\n+\n1 Elemento da Fam�lia 1A\n+\n3 �tomos de um elemento da Fam�lia 7A","DICA2","O CLOROF�RMIO � usado ilegalmente por in�meras pessoas, e � popularmente conhecido como �lan�a perfume");
		Mistura m10 = new Mistura(10,50,90,"Cloreto de Magn�sio", "MgClCl","MgCl2","1 Elemento da Fam�lia 2A\n+\n2 �tomos de um elemento da Fam�lia 7A","DICA2","O CLORETO DE MAGN�SIO � usado para diversos fins, como por exemplo: na culin�ria, para prepara��o de tofu a partir do leite de soja, na medicina, com fins terap�uticos, ou mesmo na ind�stria, como anti-congelante");
		Mistura m11 = new Mistura(10,80,65,"�cido Carbox�lico", "COOH","COOH","1 Elemento da Fam�lia 4A\n+\n2 �tomos de um elemento da Fam�lia 6A\n+\n1 Elemento da Fam�lia 1A","DICA2","Na qu�mica org�nica, �CIDOS CARBOX�LICOS s�o �cidos org�nicos caracterizados pela presen�a do grupo carboxila.");
		Mistura m12 = new Mistura(10,23,65,"Hidr�xido de S�dio", "NaOH","NaOH","1 Elemento da Fam�lia 1A\n+\n1 Elemento da Fam�lia 6A\n+\n1 Elemento da Fam�lia 1A","1 Metal Alcalino\n+\n1 N�o-Metal\n+\n1 Subst�ncia Simples","O HIDR�XIDO DE S�DIO � usado como anti�cido, para tratar a acidez do est�mago porque ele tem o poder de neutralizar os excessos do �cido\nclor�drico do suco g�strico. Tamb�m � muito usado nas receitas de culin�ria como fermento qu�mico, para ser utilizado no cozimento de p�es, bolos etc.");
		Mistura m13 = new Mistura(10,80,90,"Per�xido de Hidrog�nio", "HHOO","H2O2","2 �tomos de um elemento da Fam�lia 1A\n+\n2 �tomos de um elemento da Fam�lia 6A","1 elemento est� na fam�lia IA\n+\n1 elemento est� na familia VIA","O PER�XIDO DE HIDROG�NIO que, em solu��o aquosa, � conhecido comercialmente como �gua oxigenada");
		Mistura m14 = new Mistura(15,50,65,"Hidr�xido De C�lcio", "CaOHOH", " Ca(OH)2","1 elemento est� na fam�lia 2A\n+\n1 elemento est� na fam�lia 6A\n+\n1 elemento est� na fam�lia IA","DICA2","Tamb�m conhecido como cal hidratada, cal apagada, ou ainda cal extinta. Apresenta-se quando puro como um s�lido branco e inodoro." );
		Mistura m15 = new Mistura(10,50,65, "Carbonato De C�lcio", "CaCOOO", "CaCO3", "1 elemento est� na fam�lia 2A\n+\n1 elemento est� na fam�lia 4A\n+\n1 elemento est� na fam�lia VIA", "DICA2", "� o principal componente de rochas como os calc�rios. Tem caracter�sticas alcalinas, ou seja, � um sal com caracter�sticas b�sicas que aumenta pH das solu��es aquosas");
		Mistura m16 = new Mistura(10,80,90, "Cloreto De Alum�nio", "AlClClCl", "AlCl3", "1 elemento est� na fam�lia 3A\n+\n1 elemento est� na fam�lia 6A", "DICA2","O cloreto de alum�nio � usado comercialmente como um catalisador no craqueamento do petr�leo, � tamb�m usado como catalisador em rea��es org�nicas.");
		//Mistura m14 = new Mistura(1,1,1,"�cido Fosf�rico", "HHHPOOOO", "H3PO4", "1 elemento est� na fam�lia IA + 1 elemento est� na fam�lia VA + 1 elemento est� na fam�lia VIA", "DICA2", "� o �cido de f�sforo mais importante. Dentre os �cidos minerais, pode ser considerado um �cido mais fraco.");
		//Mistura m15 = new Mistura(1,1,1,"Hidr�xido De Am�nia", "NHHHHOH", "NH4OH","1 elemento est� na fam�lia VIA + 1 elemento est� na fam�lia IA + 1 elemento est� na fam�lia VIA","DICA2","� uma base sol�vel e fraca, nocivo quando ingerido, inalado e absorvido pela pele. A inala��o pode causar dificuldades na v�tima, como: Edemas pulmonar e na garganta, espasmos, pneumonia qu�mica." );
		//Mistura m17 = new Mistura(1,1,1,"�cido Ac�tico", "HHHHCCOO", "H4C2O2", "1 elemento est� encontrado na fam�lia IA + 1 elemento est� na fam�lia VA + 1 elemento est� na fam�lia VIA", "DICA2", "� utilizado para a obten��o da acetona, preparados farmac�uticos como a aspirina, acetato de alum�nio e outros acetatos." );
		//Mistura m18 = new Mistura(1,1,1,"�cido C�trico", "CCCCCCCHHHHHHHHOOOOOOO", "C6H8O7", "1 elemento est� na fam�lia IVA + 1 elemento est� na fam�lia IA + 1 elemento est� na fam�lia VIA","DICA2","� um acido org�nico fraco, que se pode encontrar nos citrinos, � usado como conservante natural, dando um sabor �cido e refrescante na prepara��o de alimentos e de bebidas.");
		
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
		misturas.add(m14);
		misturas.add(m15);
		misturas.add(m16);
		
			
		
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
