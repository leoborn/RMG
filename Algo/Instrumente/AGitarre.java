import java.util.Arrays;

public class AGitarre extends Gitarre{

	public AGitarre( String hersteller, String modell, String[] saiten, int buende ){
		super( modell, saitenzahl, saiten, buende );
	}
	
	public AGitarre( String hersteller, String modell, String[] saiten, int buende, String farbe ){
		super( modell, saitenzahl, saiten, buende, farbe );
	}
	
	public String getHersteller(){
		return super.getHersteller();
	}
	
	public String getModell(){
		return super.getModell();
	}
	
	public int getSaitenzahl(){
		return super.getSaitenzahl();
	}
	
	public String[] getSaiten(){
		return super.getSaiten();
	}
	
	public int getBuende(){
		return super.getBuende();
	}
	
	public String getFarbe(){
		return super.getFarbe();
	}
	
	public String getSaite( int i ){
		return super.getSaite( i );
	}
	
	public void setFarbe( String farbe2 ){
		super.setFarbe( farbe2 );
	}
	
	public int anzahlSpielbareNoten(){
		return super.anzahlSpielbareNoten();
	}
	
	@Override
	public String tonerzeugung(){
		return "Die A-Gitarre erzeugt ihre Toene durch einen Resonanzkoerper.";
	}
	
	@Override
	public String toString(){
		return "A-Gitarre: " + this.getHersteller() + " " + this.getModell() + 
		"\nFarbe: " + this.getFarbe() + 
		"\nStimmung: " + Arrays.toString(this.getSaiten()) + 
		"\nTonerzeugung: " + this.tonerzeugung();
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		result = 31 * result + this.getHersteller().hashCode();
		result = 31 * result + this.getModell().hashCode();
		result = 31 * result * this.getSaitenzahl();
		result = 31 * result * this.getBuende();
		return result;
	}
	
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof AGitarre ){
			AGitarre neu = (AGitarre) o;
			if( this.getHersteller().equals(neu.getHersteller()) && this.getModell().equals(neu.getModell()) ){
				erg = true;
			}
		}
		return erg;
	}
}