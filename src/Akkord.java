import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;

abstract class Akkord extends Note{

	private final Note[] noten;
	protected String chordName;
	
	public Akkord( String name, int oktave, String instrument, Note[] noten ) throws IllegalArgumentException {
		super( cleanName(name), oktave, instrument );
		boolean erg = true;
		for( Note n : noten ){
			if( n instanceof Akkord ){
				erg = false;
				break;
			}
		}
		if( erg == true ){
			this.noten = noten;
			this.chordName = name;
			this.setPathToFile("Es gibt keine spezifische Datei fuer einen Akkord.");
		}
		else{
			throw new IllegalArgumentException("Ein Akkord darf keinen Akkord enthalten.");
		}
	}
	
	public Akkord( String name, int oktave, Note[] noten ){
		this( name, oktave, "Klavier", noten );
	}
	
	public static String cleanName( String n ){
		if( n.length() == 1 ){
			return n.toLowerCase();
		}
		else if( n.length() == 2 ){
			if( Arrays.asList(zulnamen).contains(n.toLowerCase()) || namensmap.containsKey(n.toLowerCase()) ){
				return n.toLowerCase();
			}
			else{
				return n.substring(0,1).toLowerCase();
			}
		}
		else{
			if( Arrays.asList(zulnamen).contains(n.substring(0,3).toLowerCase()) || namensmap.containsKey(n.substring(0,3).toLowerCase()) ){
				return n.toLowerCase();
			}
			else if( Arrays.asList(zulnamen).contains(n.substring(0,2).toLowerCase()) || namensmap.containsKey(n.substring(0,2).toLowerCase()) ){
				return n.substring(0,2).toLowerCase();
			}
			else{
				return n.substring(0,1).toLowerCase();
			}
		}
	}
	
	public String getName(){
		return super.getName();
	}
	
	public int getOktave(){
		return super.getOktave();
	}
	
	public String getChordName(){
		return chordName;
	}
	
	public void setChordName( String cname ){
		this.chordName = cname;
	}
	
	public String getInstrument(){
		return super.getInstrument();
	}
	
	@Override
	public void setInstrument( String instrument ){
		for( Note n : this.noten ){
			n.setInstrument( instrument );
		}
	}
	
	public String getPathToFile(){
		return super.getPathToFile();
	}
	
	public void setPathToFile( String path ){
		super.setPathToFile( path );
	}
	
	public String getPathToFiles(){
		String combined = "";
		for( Note n : noten ){
			combined = combined + "\n" + n.getPathToFile();
		}
		return combined.trim();
	}
	
	public Note[] getNoten(){
		return noten;
	}
	
	@Override
	public String toString(){
		String repr = "";
		for( Note n : noten ){
			repr = repr + " " + n.toString();
		}
		return this.getChordName() + " (" + repr.trim() + ")";
	}
	
	@Override
	public int hashCode(){
		int result = 42;
		result = 31 * result + this.getChordName().hashCode();
		for( Note n : this.getNoten() ){
			result = 31 * result + n.getName().hashCode();
			result = 31 * result * n.getOktave();
		}
		return result;
	}
	
	public abstract Note transposeUpBy( int i );
	
	public abstract Note transposeDownBy( int i );
	
	@Override
	public abstract void play() throws MidiUnavailableException, InvalidMidiDataException, IOException;
	
	@Override
	public abstract void play( long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException;
	
	@Override
	public abstract void playWith( String instrument ) throws MidiUnavailableException, InvalidMidiDataException, IOException;
	
	@Override
	public abstract void playWith( String instrument, long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException;

	public abstract boolean equals( Object o );
}