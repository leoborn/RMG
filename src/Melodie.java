import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.regex.*;
import java.util.Map;
import java.util.HashMap;

public class Melodie implements Serializable, Notenmaterial{
	
	private Note[] noten;
	private String takt;
	private String[] rhythmus;
	
	//Zuordnung von Notenwerten auf Ticks (zur Begrenzung der Spiellaenge einzelner Noten)
	private static final Map<String, Integer> tickmap = new HashMap<String, Integer>();
	static{
		tickmap.put("g.", 5760);
		tickmap.put("g", 3840);
		tickmap.put("g.3", 3840);
		tickmap.put("g3", 2560);
		tickmap.put("h..", 3360);
		tickmap.put("h.", 2880);
		tickmap.put("h", 1920);
		tickmap.put("h..3", 2240);
		tickmap.put("h.3", 1920);
		tickmap.put("h3", 1280);
		tickmap.put("v..", 1680);
		tickmap.put("v.", 1440);
		tickmap.put("v", 960);
		tickmap.put("v..3", 1120);
		tickmap.put("v.3", 960);
		tickmap.put("v3", 640);
		tickmap.put("v..5", 1344);
		tickmap.put("v.5", 1152);
		tickmap.put("v5", 768);
		tickmap.put("a..", 840);
		tickmap.put("a.", 720);
		tickmap.put("a", 480);
		tickmap.put("a..3", 560);
		tickmap.put("a.3", 480);
		tickmap.put("a3", 320);
		tickmap.put("a..5", 672);
		tickmap.put("a.5", 576);
		tickmap.put("a5", 384);
		tickmap.put("s..", 420);
		tickmap.put("s.", 360);
		tickmap.put("s", 240);
		tickmap.put("s..3", 280);
		tickmap.put("s.3", 240);
		tickmap.put("s3", 160);
		tickmap.put("s..5", 336);
		tickmap.put("s.5", 288);
		tickmap.put("s5", 192);
		tickmap.put("z..", 210);
		tickmap.put("z.", 180);
		tickmap.put("z", 120);
	}
	
	public Melodie( Note[] melody, String t, String[] r ) throws IllegalArgumentException {
		if( melody.length == r.length ){
			boolean erg = true;
			for( String n : r ){
				if( !tickmap.containsKey( n ) ){
					erg = false;
					break;
				}
			}
			if( erg == true ){
				setTakt(t);
				this.noten = melody;
				this.rhythmus = r;
			}
			else{
				throw new IllegalArgumentException("Mindestens ein ungueltiger Rhythmuswert.");
			}
		}
		else{
			throw new IllegalArgumentException("Rhythmuswerte muessen in Anzahl mit Noten uebereinstimmen.");
		}
	}
	
	public Melodie( Note[] melody, String[] r ){
		this( melody, "4/4", r );
	}
	
	public Melodie( Note[] melody, String t ){
		this( melody, t, defaultRhythm( melody ) );
	}
	
	public Melodie( Note[] melody ){
		this( melody, "4/4", defaultRhythm( melody ) );
	}
	
	public static String[] defaultRhythm( Note[] melody ){
		String[] erg = new String[melody.length];
		for( int i = 0; i < erg.length; i++ ){
			erg[i] = "a";
		}
		return erg;
	}
	
	public int length(){
		int erg = 0;
		for( Note x : this.noten ){
			erg++;
		}
		return erg;
	}
	
	public Note[] getNoten(){
		return noten;
	}
	
	public String[] getRhythmus(){
		return rhythmus;
	}
	
	public String getTakt(){
		return takt;
	}
	
	public void setTakt( String t ) throws IllegalArgumentException {
		if( t.matches("^\\d(\\d)?/\\d(\\d)?$") ){		
			this.takt = t;
		}
		else{
			throw new IllegalArgumentException("Ungueltige Taktangabe.");
		}
	}
	
	public Note getNote( int i ) throws ArrayIndexOutOfBoundsException {
		if( i < 0 || i >= this.noten.length ){
			throw new ArrayIndexOutOfBoundsException("Das Argument muss > Null und < Laenge der Melodie sein.");
		}
		else{
			return this.noten[i];
		}
	}
	
	public void setNote( int i, Note n ) throws ArrayIndexOutOfBoundsException {
		if( i < 0 || i >= this.noten.length ){
			throw new ArrayIndexOutOfBoundsException("Das Argument muss > Null und < Laenge der Melodie sein.");
		}
		else{
			this.noten[i] = n;
		}
	}
	
	public String getRhythmuswert( int i ) throws ArrayIndexOutOfBoundsException {
		if( i < 0 || i >= this.rhythmus.length ){
			throw new ArrayIndexOutOfBoundsException("Das Argument muss > Null und < Laenge der Rhythmuswerte sein.");
		}
		else{
			return this.rhythmus[i];
		}
	}
	
	public void setRhythmuswert( int i, String rw ) throws ArrayIndexOutOfBoundsException {
		if( i < 0 || i >= this.rhythmus.length ){
			throw new ArrayIndexOutOfBoundsException("Das Argument muss > Null und < Laenge der Rhythmuswerte sein.");
		}
		else{
			this.rhythmus[i] = rw;
		}
	}
	
	public void appendNote( Note zusatz, String rhyz ) throws IllegalArgumentException {
		if( tickmap.containsKey( rhyz ) ){
			int n = this.noten.length;
			Note[] tmp = this.noten.clone();
			this.noten = new Note[n + 1];
			for( int i = 0; i < n; i++ ){
				this.noten[i] = tmp[i];
			}
			this.noten[n] = zusatz;
			
			int rhy = this.rhythmus.length;
			String[] tmp2 = this.rhythmus.clone();
			this.rhythmus = new String[rhy + 1];
			for( int j = 0; j < rhy; j++ ){
				this.rhythmus[j] = tmp2[j];
			}
			this.rhythmus[rhy] = rhyz;
		}
		else{
			throw new IllegalArgumentException("Der Rhythmuswert ist ungueltig.");
		}
	}
	
	public void appendNote( Note zusatz ) throws IllegalArgumentException{
		this.appendNote( zusatz, "a" );
	}
	
	public void krebs(){
		Note tmp;
		float laenge = this.noten.length/2;
		int d = this.noten.length - 1;
		for( int i = 0; i <= laenge - 1; i++ ){
			tmp = this.noten[i];
			this.noten[i] = this.noten[d - i];
			this.noten[d - i] = tmp;
		}
		
		String tmp2;
		float laenge2 = this.rhythmus.length/2;
		int e = this.rhythmus.length - 1;
		for( int j = 0; j <= laenge2 - 1; j++ ){
			tmp2 = this.rhythmus[j];
			this.rhythmus[j] = this.rhythmus[e - j];
			this.rhythmus[e - j] = tmp2;
		}
	}
	
	@Override
	public String toString(){
		String repr = "";
		for( int i = 0; i < this.noten.length; i++ ){
			String note = this.noten[i].toString();
			repr = repr + " " + note;
		}
		
		repr = repr + " /";
		for( int j = 0; j < this.rhythmus.length; j++ ){
			String rhythw = this.rhythmus[j];
			repr = repr + " " + rhythw;
		}
		return repr.trim();
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		for( Note x : this.noten){
			result = 31 * result + x.getName().hashCode();
		}
		result = 31 * result + takt.hashCode();
		for( String y : this.rhythmus){
			result = 31 * result + y.hashCode();
		}
		return result;
	}
	
	@Override
	public boolean equals( Object o ){
		boolean erg = true;
		if( o instanceof Melodie ){
			Melodie neu = (Melodie) o;
			if( this.noten.length != neu.noten.length || this.rhythmus.length != neu.rhythmus.length ){
				return false;
			}
			for( int i = 0; i < this.noten.length; i++ ){
				if( this.noten[i].equals(neu.noten[i]) != true || this.rhythmus[i].equals(neu.rhythmus[i]) != true ){
					erg = false;
					break;
				}
			}
		}
		return erg;
	}
	
	public void transposeOctaveUp(){
		this.transposeUpBy( 12 );
	}
	
	public void transposeOctaveDown(){
		this.transposeDownBy( 12 );
	}
	
	public void transposeUpBy( int i ) throws IllegalArgumentException{
		if( i == 0 ){
		}
		else if( i > 0 ){
			boolean erg = true;
			for( Note n : this.noten ){
				String neuname = n.getName();
				if(	namensmap.containsKey(neuname) ){
					neuname = namensmap.get(neuname);
				}
				int pos = Arrays.asList(zulnamen).indexOf(neuname);
				int noktave = n.getOktave();
				if( (pos + i) > 11 ){
					noktave++;
				}
				if( noktave == 8 && !n.getName().equals("c") ){
					erg = false;
					break;
				}
			}
			if( erg == true ){
				for( int j = 0; j < this.noten.length; j++ ){
					Note alteNote = this.noten[j];
					if( alteNote instanceof Note ){
						Note transNote = alteNote.transposeUpBy( i );
						this.setNote( j, transNote );
					}
					else if( alteNote instanceof Dreiklang ){
						Note transAkkord = alteNote.transposeUpBy( i );
						this.setNote( j, transAkkord );
					}
					else if( alteNote instanceof Vierklang ){
						Note transAkkord = alteNote.transposeUpBy( i );
						this.setNote( j, transAkkord );
					}
				}
			}
		}
		else{
			throw new IllegalArgumentException("Ungueltige Transposition.");
		}
	}
	
	public void transposeDownBy( int i ) throws IllegalArgumentException{
		if( i == 0 ){
		}
		else if( i > 0 ){
			boolean erg = true;
			for( Note n : this.noten ){
				String neuname = n.getName();
				if(	namensmap.containsKey(neuname) ){
					neuname = namensmap.get(neuname);
				}
				int pos = Arrays.asList(zulnamen).indexOf(neuname);
				int noktave = n.getOktave();
				if( (pos - i) < 0 ){
					noktave--;
				}
				if( noktave == 0 && !n.getName().equals("a") || noktave == 0 && !n.getName().equals("b") || noktave == 0 && !n.getName().equals("h") ){
					erg = false;
					break;
				}
			}
			if( erg == true ){
				for( int j = 0; j < this.noten.length; j++ ){
					Note alteNote = this.noten[j];
					if( alteNote instanceof Note ){
						Note transNote = alteNote.transposeDownBy( i );
						this.setNote( j, transNote );
					}
					else if( alteNote instanceof Dreiklang ){
						Note transAkkord = alteNote.transposeDownBy( i );
						this.setNote( j, transAkkord );
					}
				}
			}
		}
		else{
			throw new IllegalArgumentException("Ungueltige Transposition.");
		}
	}
	
	public void playMelodie() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		for( int i = 0; i < this.noten.length; i++ ){
			Note note = this.noten[i];
			String rhy = this.getRhythmuswert(i);
			long ende = (long) tickmap.get( rhy );
			note.play( ende );
		}
	}
	
	public void saveTo( String datei ){
		try{ 
			BufferedWriter bw = new BufferedWriter(new FileWriter("../Output/" + datei + ".txt"));
			bw.write( this.toString() );
			bw.close();
			try{
				FileOutputStream fileOut = new FileOutputStream("../Output/" + datei + ".ser");
				ObjectOutputStream out = new ObjectOutputStream( fileOut );
				out.writeObject( this );
				out.close();
				fileOut.close();
			}
			catch(IOException i){
				i.printStackTrace();
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}