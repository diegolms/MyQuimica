package br.com.myquimica;



public enum Elementos {

	Hidrogênio(Linha.A, Coluna.COLUNA1, "H"),
	Lítio(Linha.B, Coluna.COLUNA1,"Li"),
	Sódio(Linha.C, Coluna.COLUNA1, "Na"),
	Potássio(Linha.D, Coluna.COLUNA1,"K"),
	Rubídio(Linha.E, Coluna.COLUNA1,"Rb"),
	Césio(Linha.F, Coluna.COLUNA1,"Cs"),
	Frâncio(Linha.G, Coluna.COLUNA1,"Fr"),
	
	Berílio(Linha.B, Coluna.COLUNA2,"Be"),
	Magnésio(Linha.C, Coluna.COLUNA2,"Mg"),
	Cálcio(Linha.D, Coluna.COLUNA2,"Ca"),
	Estrôncio(Linha.E, Coluna.COLUNA2,"Sr"),
	Bário(Linha.F, Coluna.COLUNA2,"Ba"),
	Rádio(Linha.G, Coluna.COLUNA2,"Ra"),
	
	Escândio(Linha.D, Coluna.COLUNA3,"Sc"),
	Ítrio(Linha.E, Coluna.COLUNA3,"Y"),
	
	Titânio(Linha.D, Coluna.COLUNA4,"Ti"),
	Zircônio(Linha.E, Coluna.COLUNA4,"Zr"),
	Háfnio(Linha.F, Coluna.COLUNA4,"Hf"),
	Rutherfórdio(Linha.G, Coluna.COLUNA4,"Rf"),
	
	Vanádio(Linha.D, Coluna.COLUNA5,"V"),
	Nióbio(Linha.E, Coluna.COLUNA5,"Nb"),
	Tântalo(Linha.F, Coluna.COLUNA5,"Ta"),
	Dúbnio(Linha.G, Coluna.COLUNA5,"Db"),
	
	Crômio(Linha.D, Coluna.COLUNA6,"Cr"),
	Molibdênio(Linha.E, Coluna.COLUNA6,"Mo"),
	Tungstênio(Linha.F, Coluna.COLUNA6,"W"),
	Seabórgio(Linha.G, Coluna.COLUNA6,"Sg"),
	
	Manganês(Linha.D, Coluna.COLUNA7,"Mn"),
	Tecnécio(Linha.E, Coluna.COLUNA7,"Tc"),
	Rênio(Linha.F, Coluna.COLUNA7,"Re"),
	Bóhrio(Linha.G, Coluna.COLUNA7,"Bh"),
	
	Ferro(Linha.D, Coluna.COLUNA8,"Fe"),
	Rutênio(Linha.E, Coluna.COLUNA8,"Ru"),
	Ósmio(Linha.F, Coluna.COLUNA8,"Os"),
	Hássio(Linha.G, Coluna.COLUNA8,"Hs"),
	
	Cobalto(Linha.D, Coluna.COLUNA9,"Co"),
	Ródio(Linha.E, Coluna.COLUNA9,"Rh"),
	Irídio(Linha.F, Coluna.COLUNA9,"Ir"),
	Meitnério(Linha.G, Coluna.COLUNA9,"Mt"),
	
	Níquel(Linha.D, Coluna.COLUNA10,"Ni"),
	Paládio(Linha.E, Coluna.COLUNA10,"Pd"),
	Platina(Linha.F, Coluna.COLUNA10,"Pt"),
	Darmstádio(Linha.G, Coluna.COLUNA10,"Ds"),
	
	Cobre(Linha.D, Coluna.COLUNA11,"Cu"),
	Prata(Linha.E, Coluna.COLUNA11,"Ag"),
	Ouro(Linha.F, Coluna.COLUNA11,"Au"),
	Roentgênio(Linha.G, Coluna.COLUNA11,"Rg"),
	
	Zinco(Linha.D, Coluna.COLUNA12,"Zn"),
	Cádmio(Linha.E, Coluna.COLUNA12,"Cd"),
	Mercúrio(Linha.F, Coluna.COLUNA12,"Hg"),
	Copernício(Linha.G, Coluna.COLUNA12,"Cn"),
	
	Boro(Linha.B, Coluna.COLUNA13,"B"),
	Alumínio(Linha.C, Coluna.COLUNA13,"Al"),
	Gálio(Linha.D, Coluna.COLUNA13,"Ga"),
	Índio(Linha.E, Coluna.COLUNA13,"In"),
	Tálio(Linha.F, Coluna.COLUNA13,"Tl"),
	
	Carbono(Linha.B, Coluna.COLUNA14,"C"),
	Selício(Linha.C, Coluna.COLUNA14,"Si"),
	Germânio(Linha.D, Coluna.COLUNA14,"Ge"),
	Estanho(Linha.E, Coluna.COLUNA14,"Sn"),
	Chumbo(Linha.F, Coluna.COLUNA14,"Pb"),
	
	Nitrogênio(Linha.B, Coluna.COLUNA15,"N"),
	Fósforo(Linha.C, Coluna.COLUNA15,"P"),
	Arsênio(Linha.D, Coluna.COLUNA15,"As"),
	Antimônio(Linha.E, Coluna.COLUNA15,"Sb"),
	Bismuto(Linha.F, Coluna.COLUNA15,"Bi"),
	
	Oxigênio(Linha.B, Coluna.COLUNA16,"O"),
	Enxofre(Linha.C, Coluna.COLUNA16,"S"),
	Selênio(Linha.D, Coluna.COLUNA16,"Se"),
	TELÚRIO(Linha.E, Coluna.COLUNA16,"Te"),
	POLÔNIO(Linha.F, Coluna.COLUNA16,"Po"),
	
	Flúor(Linha.B, Coluna.COLUNA17,"F"),
	Cloro(Linha.C, Coluna.COLUNA17,"Cl"),
	Bromo(Linha.D, Coluna.COLUNA17,"Br"),
	Iodo(Linha.E, Coluna.COLUNA17,"I"),
	Astato(Linha.F, Coluna.COLUNA17,"At"),
	
	Hélio(Linha.A, Coluna.COLUNA18,"He"),
	Néon(Linha.B, Coluna.COLUNA18,"Ne"),
	Argônio(Linha.C, Coluna.COLUNA18,"Ar"),
	Criptônio(Linha.D, Coluna.COLUNA18,"Kr"),
	Xenônio(Linha.E, Coluna.COLUNA18,"Xe"),
	Radônio(Linha.F, Coluna.COLUNA18,"Rn"),
	
	Lantânio(Linha.A, Coluna.COLUNA2,"La"),
	Cério(Linha.A, Coluna.COLUNA3,"Ce"),
	Praseodímio(Linha.A, Coluna.COLUNA4,"Pr"),
	Neodímio(Linha.A, Coluna.COLUNA5,"Nd"),
	Promécio(Linha.A, Coluna.COLUNA6,"Pm"),
	Samário(Linha.A, Coluna.COLUNA7,"Sm"),
	Európio(Linha.A, Coluna.COLUNA8,"Eu"),
	Gadolínio(Linha.A, Coluna.COLUNA9,"Gd"),
	Térbio(Linha.A, Coluna.COLUNA10,"Tb"),
	Disprósio(Linha.A, Coluna.COLUNA11,"Dy"),
	Hólmio(Linha.A, Coluna.COLUNA12,"Ho"),
	Érbio(Linha.A, Coluna.COLUNA13,"Er"),
	Túlio(Linha.A, Coluna.COLUNA14,"Tm"),
	Itérbio(Linha.A, Coluna.COLUNA15,"Yb"),
	Lutécio(Linha.A, Coluna.COLUNA16,"Lu"),
	
	Actínio(Linha.B, Coluna.COLUNA3,"Ac"),
	Tório(Linha.B, Coluna.COLUNA4,"Th"),
	Protactínio(Linha.B, Coluna.COLUNA5,"Pa"),
	Urânio(Linha.B, Coluna.COLUNA6,"U"),
	Neptúnio(Linha.B, Coluna.COLUNA7,"Np"),
	Plutônio(Linha.B, Coluna.COLUNA8,"Pu"),
	Amerício(Linha.B, Coluna.COLUNA9,"Am"),
	Cúrio(Linha.B, Coluna.COLUNA10,"Cm"),
	Berquélio(Linha.B, Coluna.COLUNA11,"Bk"),
	Califórnio(Linha.B, Coluna.COLUNA12,"Cf"),
	Einstênio(Linha.C, Coluna.COLUNA3,"Es"),
	Férmio(Linha.C, Coluna.COLUNA4,"Fm"),
	Mendelévio(Linha.C, Coluna.COLUNA5,"Md"),
	Nobélio(Linha.C, Coluna.COLUNA6,"No"),
	Laurêncio(Linha.C, Coluna.COLUNA7,"Lr");
		
	
	

	public static final int CARD_WIDTH = 42;
	public static final int CARD_HEIGHT = 42;


	public final Linha mColor;
	public final Coluna mValue;
	public final String formula;

	private Elementos(final Linha pColor, final Coluna pValue, final String formula) {
		this.mColor = pColor;
		this.mValue = pValue;
		this.formula=formula;
	}
	


	
	public int getTexturePositionX() {
		return this.mValue.ordinal() * CARD_WIDTH;
		
	}
	
	public int getTexturePositionY() {
		return this.mColor.ordinal() * CARD_HEIGHT;
		
	}

}
