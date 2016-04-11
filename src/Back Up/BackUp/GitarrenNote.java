import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;

public class GitarrenNote extends Note{	//Klasse GitarrenNote ist eine Unterklasse von Note
	
	private String saite;	//Saite, auf der die Note gespielt wird, z.B. "A"
	private int bund;		//Bund, in dem die Note gespielt wird, z.B. "10"
	
	//Konstruktor fuer eine Instanz der Klasse GitarrenNote
	public GitarrenNote( String name, int oktave, String saite, int bund ){
		super( name, checkOktave(oktave) );
		setPosition( saite, bund );		//ruft Methode setPosition mit den Argumenten saite und bund auf
		this.setPathToFile( this.getPathToFile() + "Gitarre/" + this.getName() + this.getOktave() + ".mid" );
	}
	
	public GitarrenNote( String name, int oktave ){
		super( name, checkOktave(oktave) );
		setPosition( "E", 2 );		//ruft Methode setPosition mit den Argumenten saite und bund auf
		this.setPathToFile( this.getPathToFile() + "Gitarre/" + this.getName() + this.getOktave() + ".mid" );
	}
	
	//ueberprueft, ob die Oktave mind. 2 ist
	//(da alle Noten der Oktave 1 fuer eine Gitarre zu tief sind)
	public static int checkOktave( int oktave ) throws IllegalArgumentException {
		if( oktave > 1 && oktave < 8 ){
			return oktave;
		}
		else{
			throw new IllegalArgumentException("Diese Note kann auf einer Gitarre nicht gespielt werden.");
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
		int altPos = Arrays.asList(zulaessig).indexOf(this.getName());
		if( (altPos + i) > 11 ){
			altOktave++;
		}
		int pos = (altPos + 60 + i)%12;
		String nname1 = zulaessig[pos];
		if( altOktave == 6 && (!nname1.equals("c") || !nname1.equals("cis") || !nname1.equals("d") || !nname1.equals("dis") || !nname1.equals("e")) ){
			return this;
		}
		else{
			Note transNote = new GitarrenNote( nname1, altOktave );
			return transNote;
		}
	}
	
	@Override
	public Note transposeDownBy( int i ){
		int altOktave = this.getOktave();
		int altPos = Arrays.asList(zulaessig).indexOf(this.getName());
		if( (altPos - i) < 0 ){
			altOktave--;
		}
		int pos = (altPos + 60 - i)%12;
		String nname1 = zulaessig[pos];
		if( altOktave < 2 ){
			return this;
		}
		else{
			Note transNote = new GitarrenNote( nname1, altOktave );
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
	
	public String getSaite(){
		return saite;
	}
	
	public int getBund(){
		return bund;
	}
	
	public String getPosition(){
		return saite + bund;
	}
	
	//weist den Instanzvariablen saite und bund (Position auf dem Griffbrett) die Werte s und b zu
	public void setPosition( String s, int b ) throws IllegalArgumentException {
		if( b >= 0 && b <= 24 ){
			this.saite = s;
			this.bund = b;
		}
		else{
			throw new IllegalArgumentException("Das Argument muss in [0,24] liegen.");
		}
	}	
	
	//gibt eine Repraesentation der GitarrenNote bestehend aus dem Namen, der Oktave und der Position aus
	@Override
	public String toString(){
		return this.getName() + this.getOktave() + " (" + this.getPosition() + ")";
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		result = 31 * result + this.getName().hashCode();
		result = 31 * result * this.getOktave();
		result = 31 * result + this.saite.hashCode();
		result = 31 * result * this.bund;
		return result;
	}
	
	//vergleicht die GitarrenNoteninstanz mit einer anderen GitarrenNoten/Objektinstanz
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof GitarrenNote ){
			GitarrenNote neu = (GitarrenNote) o;
			if( this.getPosition().equals(neu.getPosition()) || 
			   this.getName().equals(neu.getName()) && this.getOktave() == neu.getOktave() ){
				erg = true;
			}
		}
		return erg;
	}
}