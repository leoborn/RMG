import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Note implements Serializable, Notenmaterial{
	
	private final String name;
	private final int oktave;
	protected String pathToFile;
	
	public Note( String name, int oktave ) throws IllegalArgumentException {
		if( Arrays.asList( zulnamen ).contains( name ) ) {
			if( oktave >= 0 && oktave <= 8 ){
				this.name = name;
				this.oktave = oktave;
                this.pathToFile = "../MIDI/" + name + oktave + ".mid";
			}
			else{
				throw new IllegalArgumentException("Diese Note kann auf einem Instrument nicht gespielt werden.");
			}
		}
		else if( namensmap.containsKey(name) ){
			String neuname = namensmap.get(name);
			this.oktave = oktave;
			this.name = name;
			if( name.equals("ces") ){
				this.pathToFile = "../MIDI/" + neuname + (oktave - 1) + ".mid";
			}
			else if( name.equals("his") ){
				this.pathToFile = "../MIDI/" + neuname + (oktave + 1) + ".mid";
			}
			else{
				this.pathToFile = "../MIDI/" + neuname + oktave + ".mid";
			}
		}
		else{
			throw new IllegalArgumentException("Es muss ein korrekter Notenname "+
												"(dt. Denotation, Kleinschrift) angegeben werden.");
		}
	}
	
	public String getName(){
		return name;
	}
	
	public int getOktave(){
		return oktave;
	}
	
	public String getPathToFile(){
		return pathToFile;
	}

	public void setPathToFile( String path ){
		this.pathToFile = path;
	}
	
	public Note transposeUpBy( int i ){
		if( i < 0 ){
			return this;
		}
		else{
			int altOktave = this.getOktave();
			String neuname = this.getName();
			if(	namensmap.containsKey(neuname) ){
				neuname = namensmap.get(neuname);
			}
			int altPos = Arrays.asList(zulnamen).indexOf(neuname);
			if( (altPos + i) > 11 ){
				altOktave++;
			}
			int pos = (altPos + 60 + i)%12;
			String nname1 = zulnamen[pos];
			if( altOktave == 8 && !nname1.equals("c") ){
				return this;
			}
			else{
				Note transNote = new Note( nname1, altOktave );
				return transNote;
			}
		}
	}
	
	public Note transposeDownBy( int i ){
		if( i < 0 ){
			return this;
		}
		else{
			int altOktave = this.getOktave();
			String neuname = this.getName();
			if(	namensmap.containsKey(neuname) ){
				neuname = namensmap.get(neuname);
			}
			int altPos = Arrays.asList(zulnamen).indexOf(neuname);
			if( (altPos - i) < 0 ){
				altOktave--;
			}
			int pos = (altPos + 60 - i)%12;
			String nname1 = zulnamen[pos];
			if( altOktave == 0 && (!nname1.equals("a") || !nname1.equals("b") || !nname1.equals("h")) ){
				return this;
			}
			else{
				Note transNote = new Note( nname1, altOktave );
				return transNote;
			}
		}
	}
	
	public void play() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		File midiFile = new File(this.pathToFile);
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			Sequence mySeq = MidiSystem.getSequence(midiFile);
            sequencer.setSequence(mySeq);
            sequencer.open();
            sequencer.start();
            while( true ){
                if( sequencer.isRunning() ){
                    try{
                        Thread.sleep(1000);
                    }
					catch( InterruptedException ignore ){
                        break;
                    }
                }
				else{
                    break;
                }
            }
            sequencer.stop();
            sequencer.close();
        }
		catch( MidiUnavailableException mue ){
			System.out.println("Midi device ist nicht verfuegbar.");
		}
		catch( InvalidMidiDataException imde ){
            System.out.println("Midi Daten sind ungueltig.");
        }
		catch( IOException ioe ){
            System.out.println("I/O Error! Da keine MIDI-Datei existiert, kann diese Note auf diesem Instrument nicht gespielt werden.");
        }
	}
	
	public void play( long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException{
		File midiFile = new File(this.pathToFile);
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			Sequence mySeq = MidiSystem.getSequence(midiFile);
            sequencer.setSequence(mySeq);
            sequencer.open();
            sequencer.start();
			
			long erg = sequencer.getTickPosition();
            while( erg < ende ){
                if( sequencer.isRunning() ){
                    try{
                        Thread.sleep(3, 12500);
						erg = sequencer.getTickPosition();
                    }
					catch( InterruptedException ignore ){
                        break;
                    }
                }
				else{
                    break;
                }
            }
            sequencer.stop();
            sequencer.close();
        }
		catch( MidiUnavailableException mue ){
			System.out.println("Midi device ist nicht verfuegbar.");
		}
		catch( InvalidMidiDataException imde ){
            System.out.println("Midi Daten sind ungueltig.");
        }
		catch( IOException ioe ){
			ioe.printStackTrace();
            System.out.println("I/O Error! Da keine MIDI-Datei existiert, kann diese Note auf diesem Instrument nicht gespielt werden.");
        }
	}
	
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
	
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof Note ){
			Note neu = (Note) o;
			if( this.getName().equals(neu.getName()) && this.getOktave() == neu.getOktave() ){
				erg = true;
			}
		}
		return erg;
	}
}