import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Note implements Serializable, Notenmaterial{
	
	private final String name;
	private final int oktave;
	private String instrument;
	protected String pathToFile;
	
	public Note( String name, int oktave, String instrument ) throws IllegalArgumentException {
		if( Arrays.asList( zulnamen ).contains( name ) && Arrays.asList( zulinstrumente ).contains( instrument ) ) {
			if( oktave >= 0 && oktave <= 8 ){
				this.name = name;
				this.oktave = oktave;
				this.instrument = instrument;
				//this.pathToFile = "/Users/leoborn/Desktop/Noten/MIDI/" + instrument + "/" + name + oktave + ".mid";
                this.pathToFile = "../MIDI/" + instrument + "/" + name + oktave + ".mid";
			}
			else{
				throw new IllegalArgumentException("Diese Note kann auf einem Instrument nicht gespielt werden.");
			}
		}
		else if( namensmap.containsKey(name) && Arrays.asList( zulinstrumente ).contains( instrument ) ){
			String neuname = namensmap.get(name);
			this.oktave = oktave;
			this.instrument = instrument;
			this.name = name;
			if( name.equals("ces") ){
				this.pathToFile = "../MIDI/" + instrument + "/" + neuname + (oktave - 1) + ".mid";
			}
			else if( name.equals("his") ){
				this.pathToFile = "../MIDI/" + instrument + "/" + neuname + (oktave + 1) + ".mid";
			}
			else{
				this.pathToFile = "../MIDI/" + instrument + "/" + neuname + oktave + ".mid";
			}
		}
		else{
			throw new IllegalArgumentException("Es muessen ein korrekter Notenname "+
												"(dt. Denotation, Kleinschrift) und ein korrektes Instrument angegeben werden.");
		}
	}
	
	public Note( String name, int oktave ){
		this( name, oktave, "Klavier" );
	}

	public String getName(){
		return name;
	}
	
	public int getOktave(){
		return oktave;
	}
	
	public String getInstrument(){
		return instrument;
	}
	
	public void setInstrument( String instrument ) throws IllegalArgumentException {
		if( Arrays.asList( zulinstrumente ).contains( instrument ) ){
			if( namensmap.containsKey(this.getName()) ){
				String neuname = namensmap.get(this.getName());
				this.instrument = instrument;
				this.setPathToFile("../MIDI/" + instrument + "/" + neuname + this.getOktave() + ".mid");
			}
			else{
				this.instrument = instrument;
				this.setPathToFile("../MIDI/" + instrument + "/" + this.toString() + ".mid");
			}
		}
		else{
			throw new IllegalArgumentException("Das Instrument gibt es nicht.");
		}
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
		else if( this.getInstrument().equals("Klavier") ){
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
				Note transNote = new Note( nname1, altOktave, "Klavier" );
				return transNote;
			}
		}
		else if( this.getInstrument().equals("Gitarre") ){
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
			if( altOktave == 6 && (!nname1.equals("c") || !nname1.equals("cis") || !nname1.equals("d") || !nname1.equals("dis") || !nname1.equals("e")) ){
				return this;
			}
			else{
				Note transNote = new Note( nname1, altOktave, "Gitarre" );
				return transNote;
			}
		}
		else{
			return this;
		}
	}
	
	public Note transposeDownBy( int i ){
		if( i < 0 ){
			return this;
		}
		else if( this.getInstrument().equals("Klavier") ){
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
				Note transNote = new Note( nname1, altOktave, "Klavier" );
				return transNote;
			}
		}
		else if( this.getInstrument().equals("Gitarre") ){
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
			if( altOktave < 2 ){
				return this;
			}
			else{
				Note transNote = new Note( nname1, altOktave, "Gitarre" );
				return transNote;
			}
		}
		else{
			return this;
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
	
	public void playWith( String instrument ) throws MidiUnavailableException, InvalidMidiDataException, IOException {
		this.setInstrument(instrument);
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
	
	public void playWith( String instrument, long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException{
		this.setInstrument(instrument);
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
		result = 31 * result + this.getInstrument().hashCode();
		result = 31 * result * this.getOktave();
		return result;
	}
	
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof Note ){
			Note neu = (Note) o;
			if( this.getName().equals(neu.getName()) && this.getOktave() == neu.getOktave() && this.getInstrument().equals(neu.getInstrument())){
				erg = true;
			}
		}
		return erg;
	}
}