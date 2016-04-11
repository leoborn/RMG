import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;

public class KlavierNote extends Note{
	
	private static final String[] zulnoten = new String[]{"c", "cis", "d", "dis", "e", "f", 
															"fis", "g", "gis", "a", "b", "h"};
	
	//Konstruktor fuer eine Instanz der Klasse KlavierNote
	public KlavierNote( String name, int oktave ) throws IllegalArgumentException {
		super( name, checkOktave(oktave) );
		this.setPathToFile( this.getPathToFile() + "Klavier/" + this.toString() + ".mid" );
	}
	
	public static int checkOktave( int oktave ) throws IllegalArgumentException {
		if( oktave >= 0 && oktave <= 8 ){
			return oktave;
		}
		else{
			throw new IllegalArgumentException("Diese Note kann auf einem Klavier nicht gespielt werden.");
		}
	}
	
	//manche der von der Oberklasse geerbten Methoden werden nicht ueberschrieben
	//daher reicht es, die Methoden der Oberklasse ueber 'super' aufzurufen
	public String getName(){
		return super.getName();
	}
	
	public int getOktave(){
		return super.getOktave();
	}
	
	public String getPathToFile(){
		return super.getPathToFile();
	}
	
	@Override
	public Note transposeUpBy( int i ){
		int altOktave = this.getOktave();
		int altPos = Arrays.asList(zulnoten).indexOf(this.getName());
		if( (altPos + i) > 11 ){
			altOktave++;
		}
		int pos = (altPos + 60 + i)%12;
		String nname1 = zulnoten[pos];
		if( altOktave == 8 && !nname1.equals("c") ){
			return this;
		}
		else{
			Note transNote = new KlavierNote( nname1, altOktave );
			return transNote;
		}
	}
	
	@Override
	public Note transposeDownBy( int i ){
		int altOktave = this.getOktave();
		int altPos = Arrays.asList(zulnoten).indexOf(this.getName());
		if( (altPos - i) < 0 ){
			altOktave--;
		}
		int pos = (altPos + 60 - i)%12;
		String nname1 = zulnoten[pos];
		if( altOktave == 0 && (!nname1.equals("a") || !nname1.equals("b") || !nname1.equals("h")) ){
			return this;
		}
		else{
			Note transNote = new KlavierNote( nname1, altOktave );
			return transNote;
		}
	}
	
	@Override
	protected void setPathToFile( String path ){
		this.pathToFile = path;
	}
	
	public void play() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		super.play();
	}
	
	public void play( long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException{
		super.play( ende );
	}
	
	//gibt eine Repraesentation der Note bestehend aus dem Namen und der Oktavenangabe aus
	@Override
	public String toString(){
		return this.getName() + this.getOktave();
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		result = 31 * result + this.getName().hashCode();
		result = 31 * result * this.getOktave();
		return result;
	}
	
	//vergleicht die Noteninstanz mit einer anderen Noten/Objektinstanz
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof KlavierNote ){
			KlavierNote neu = (KlavierNote) o;
			if( this.getName().equals(neu.getName()) && this.getOktave() == neu.getOktave() ){
				erg = true;
			}
		}
		return erg;
	}
}