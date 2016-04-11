public class Klavier extends Instrument{

	private int tasten;
	private int oktaven; //Anzahl der spielbaren Oktaven.
	
	public Klavier( String hersteller, String modell, int tasten, int oktaven ) throws IllegalArgumentException {
		super( hersteller, modell, tasten );
		if( oktaven <= tasten/10 ){
			this.oktaven = oktaven;
		}
		else{
			throw new IllegalArgumentException("Die Anzahl der Tasten laesst nicht so viele Oktaven zu.");
		}
	}
	
	public Klavier( String hersteller, String modell, int tasten, int oktaven, String farbe ) throws IllegalArgumentException {
		super( hersteller, modell, tasten, farbe );
		if( oktaven <= tasten/10 ){
			this.oktaven = oktaven;
		}
		else{
			throw new IllegalArgumentException("Die Anzahl der Tasten laesst nicht so viele Oktaven zu.");
		}
	}
	
	public String getHersteller(){
		return super.getHersteller();
	}
	
	public String getModell(){
		return super.getModell();
	}
	
	public int getTastenzahl(){
		return super.getSpielkomponenten();
	}
	
	public String getFarbe(){
		return super.getFarbe();
	}
	
	public int getOktavenzahl(){
		return oktaven;
	}
	
	public void setFarbe( String farbe2 ){
		super.setFarbe( farbe2 );
	}
	
	@Override
	public String tonerzeugung(){
		return "Das Klavier erzeugt ihre Toene durch Schwingung von Saiten, die durch Betaetigen der Tasten hervorgerufen wird.";
	}
	
	@Override
	public int anzahlSpielbareNoten(){
		return this.getTastenzahl();
	}

	@Override
	public String toString(){
		return "Klavier: " + this.getHersteller() + " " + this.getModell() + 
		"\nFarbe: " + this.getFarbe() + 
		"\nTasten: " + this.getTastenzahl() + 
		"\nOktaven: " + this.getOktavenzahl() + 
		"\nTonerzeugung: " + this.tonerzeugung();
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		result = 31 * result + this.getHersteller().hashCode();
		result = 31 * result + this.getModell().hashCode();
		result = 31 * result * this.getTastenzahl();
		result = 31 * result * this.getOktavenzahl();
		return result;
	}
	
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof Klavier ){
			Klavier neu = (Klavier) o;
			if( this.getHersteller().equals(neu.getHersteller()) && this.getModell().equals(neu.getModell()) ){
				erg = true;
			}
		}
		return erg;
	}
}