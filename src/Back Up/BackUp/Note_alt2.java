import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;

abstract class Note implements Noten{
	
	private final String name;	//Oberflaechenform, z.B. "cis'"
	private final int oktave;	//Okatvenangabe, z.B. 2
	protected String pathToFile;	//Pfad zur MIDI-Datei
	
	public Note( String name, int oktave ) throws IllegalArgumentException {
		if( Arrays.asList( zulaessig ).contains( name ) ) {
			this.name = name;
			this.oktave = oktave;
			this.pathToFile = "/Users/leoborn/Desktop/Noten/MIDI/";
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

	public abstract Note transposeUpBy( int i );
	
	public abstract Note transposeDownBy( int i );
	
	protected abstract void setPathToFile( String path );
	
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
            System.out.println("I/O Error!");
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
            System.out.println("I/O Error!");
        }
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals( Object o );
	
}