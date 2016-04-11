abstract class Instrument{
	
	private final String hersteller;
	private final String modell;
	private final int spielkomponenten;
	private String farbe;
	
	public Instrument( String hersteller, String modell, int spielkomponenten ){
		this.hersteller = hersteller;
		this.modell = modell;
		this.spielkomponenten = spielkomponenten;
		this.farbe = "Schwarz";
	}
	
	public Instrument( String hersteller, String modell, int spielkomponenten, String farbe ){
		this.hersteller = hersteller;
		this.modell = modell;
		this.spielkomponenten = spielkomponenten;
		this.farbe = farbe;
	}
	
	public String getHersteller(){
		return hersteller;
	}
	
	public String getModell(){
		return modell;
	}
	
	public int getSpielkomponenten(){
		return spielkomponenten;
	}
	
	public String getFarbe(){
		return farbe;
	}
	
	public void setFarbe( String farbe2 ){
		this.farbe = farbe2;
	}
	
	public abstract int anzahlSpielbareNoten();
	
	public abstract String tonerzeugung();
}