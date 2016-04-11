import java.util.Arrays;

abstract class Gitarre extends Instrument{

	private String[] saiten;
	private final int buende;

	public Gitarre( String hersteller, String modell, String[] saiten, int buende ) throws IllegalArgumentException {
		super( hersteller, modell, saiten.length );
		if( buende > 0 && buende < 25 ){
			this.saiten = saiten;
			this.buende = buende;
		}
		else{
			throw new IllegalArgumentException("Unzulaessige Bundanzahl.");
		}
	}
	
	public Gitarre( String hersteller, String modell, String[] saiten, int buende, String farbe ) throws IllegalArgumentException {
		super( hersteller, modell, saiten.length, farbe );
		if( buende > 0 && buende < 25 ){
			this.saiten = saiten;
			this.buende = buende;
		}
		else{
			throw new IllegalArgumentException("Unzulaessige Bundanzahl.");
		}
	}
	
	public String getHersteller(){
		return super.getHersteller();
	}
	
	public String getModell(){
		return super.getModell();
	}
	
	public int getSaitenzahl(){
		return super.getSpielkomponenten();
	}
	
	public String getFarbe(){
		return super.getFarbe();
	}
	
	public String[] getSaiten(){
		return saiten;
	}
	
	public int getBuende(){
		return buende;
	}
	
	public String getSaite( int i ) throws ArrayIndexOutOfBoundsException {
		if( i > 0 && i < this.getSaitenzahl() ){
			return this.saiten[i];
		}
		else{
			throw new ArrayIndexOutOfBoundsException("Das Argument muss > Null und < Anzahl an Saiten sein.");
		}
	}
	
	public void setFarbe( String farbe2 ){
		super.setFarbe( farbe2 );
	}
	
	@Override
	public int anzahlSpielbareNoten(){
		return this.getSaitenzahl() * this.getBuende();
	}
	
	public abstract String tonerzeugung();
}