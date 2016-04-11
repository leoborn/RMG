import java.util.Arrays;

public class EGitarre extends Gitarre{

	private final String[] tonabnehmer;
	private final boolean vibratohebel;

	public EGitarre( String hersteller, String modell, String[] saiten, int buende, String[] tonabnehmer , boolean vh ){
		super( hersteller, modell, saiten, buende );
		this.tonabnehmer = tonabnehmer;
		this.vibratohebel = vh;
	}
	
	public EGitarre( String hersteller, String modell, String[] saiten, int buende, String farbe, String[] tonabnehmer , boolean vh ){
		super( hersteller, modell, saiten, buende, farbe );
		this.tonabnehmer = tonabnehmer;
		this.vibratohebel = vh;
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
	
	public String[] getTonabnehmer(){
		return tonabnehmer;
	}
	
	public boolean getVibratohebel(){
		return vibratohebel;
	}
	
	public String getBesserVibratohebel(){
		if( this.vibratohebel == true ){
			return "Diese Gitarre hat einen Vibratohebel.";
		}
		else{
			return "Diese Gitarre hat keinen Vibratohebel.";
		}
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
		return "Die E-Gitarre erzeugt ihre Toene elektrisch.";
	}
	
	@Override
	public String toString(){
		return "E-Gitarre: " + this.getHersteller() + " " + this.getModell() + 
		"\nFarbe: " + this.getFarbe() + 
		"\nStimmung: " + Arrays.toString(this.getSaiten()) + 
		"\nTonabnehmer: " + Arrays.toString(this.getTonabnehmer()) + 
		"\nVibratohebel: " + this.getVibratohebel() +
		"\nTonerzeugung: " + this.tonerzeugung();
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		result = 31 * result + this.getHersteller().hashCode();
		result = 31 * result + this.getModell().hashCode();
		result = 31 * result * this.getSaitenzahl();
		result = 31 * result + this.getTonabnehmer().hashCode();
		result = 31 * result * this.getBuende();
		return result;
	}
	
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof EGitarre ){
			EGitarre neu = (EGitarre) o;
			if( this.getHersteller().equals(neu.getHersteller()) && this.getModell().equals(neu.getModell()) ){
				erg = true;
			}
		}
		return erg;
	}
}