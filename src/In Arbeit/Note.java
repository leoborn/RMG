import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Note implements Serializable, Material{
	
	private final String name;
	private final int oktave;
	private String instrument;
	private int midizahl;
	
	public String[] zulnamen = new String[]{"c", "cis", "d", "dis", "e", "f", 
	"fis", "g", "gis", "a", "b", "h"};
	
	private static final Map<String, String> namensmap = new HashMap<String, String>();
	static{
			namensmap.put("his", "c");
			namensmap.put("des", "cis");
			namensmap.put("es", "dis");
			namensmap.put("fes", "e");
			namensmap.put("eis", "f");
			namensmap.put("ges", "fis");
			namensmap.put("as", "gis");
			namensmap.put("ais", "b");
			namensmap.put("ces", "h");
		}
	
	public Note( String name, int oktave) throws IllegalArgumentException {
		if( Arrays.asList( zulnamen ).contains( name ) ) {
			if( oktave >= 0 && oktave <= 8 ){
				this.name = name;
				this.oktave = oktave;
				this.midizahl = zahlenmap.get(name + oktave);
			}
			else{
				throw new IllegalArgumentException("Diese Note kann auf einem Instrument nicht gespielt werden.");
			}
		}
		else if( namensmap.containsKey(name) ){
			String neuname = namensmap.get(name);
			this.oktave = oktave;
			this.name = name;
			this.midizahl = zahlenmap.get(neuname + oktave);
		}
		else{
			throw new IllegalArgumentException("Es muessen ein korrekter Notenname "+
											   "(dt. Denotation, Kleinschrift) und ein korrektes Instrument angegeben werden.");
		}
	}
	
	public String getName(){
		return name;
	}
	
	public int getOktave(){
		return oktave;
	}
	
	public int getMIDIZahl(){
		return midizahl;
	}
	/**
	public Note transposeUpBy( int i ){
		if( this.getInstrument().equals("Klavier") ){
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
		if( this.getInstrument().equals("Klavier") ){
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
	**/
	
	public void play() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		try{
			//****  Create a new MIDI sequence with 24 ticks per beat  ****
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);
			
			//****  Obtain a MIDI track from the sequence  ****
			Track t = s.createTrack();
			
			//****  General MIDI sysex -- turn on General MIDI sound set  ****
			byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
			SysexMessage sm = new SysexMessage();
			sm.setMessage(b, 6);
			MidiEvent me = new MidiEvent(sm,(long)0);
			t.add(me);
			
			//****  set tempo (meta event)  ****
			MetaMessage mt = new MetaMessage();
			byte[] bt = {0x07, (byte)0xa1, 0x20};
			mt.setMessage(0x51 ,bt, 3);
			me = new MidiEvent(mt,(long)0);
			t.add(me);
			
			//****  set track name (meta event)  ****
			mt = new MetaMessage();
			String TrackName = new String("midifile track");
			mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
			me = new MidiEvent(mt,(long)0);
			t.add(me);
			
			//****  set omni on  ****
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7D,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			//****  set poly on  ****
			mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7F,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			//****  set instrument to Piano  ****
			mm = new ShortMessage();
			mm.setMessage(0xC0, 0x00, 0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			//****  note on - middle C  ****
			mm = new ShortMessage();
			mm.setMessage(0x90,(byte) this.getMIDIZahl(),0x60);
			me = new MidiEvent(mm,(long)1);
			t.add(me);
			
			//****  note off - middle C - 120 ticks later  ****
			mm = new ShortMessage();
			mm.setMessage(0x80,(byte) this.getMIDIZahl(),0x40);
			me = new MidiEvent(mm,(long)144);
			t.add(me);
			
			//****  set end of track (meta event) 19 ticks later  ****
			mt = new MetaMessage();
			byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, (long)144);
			t.add(me);

			try{
				Sequencer sequencer = MidiSystem.getSequencer();
				sequencer.setSequence(s);
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
		}
		catch(Exception e){
			System.out.println("Exception caught " + e.toString());
		}
	}
	
	public void play( long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException{
		try{
			//****  Create a new MIDI sequence with 24 ticks per beat  ****
			Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);
			
			//****  Obtain a MIDI track from the sequence  ****
			Track t = s.createTrack();
			
			//****  General MIDI sysex -- turn on General MIDI sound set  ****
			byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
			SysexMessage sm = new SysexMessage();
			sm.setMessage(b, 6);
			MidiEvent me = new MidiEvent(sm,(long)0);
			t.add(me);
			
			//****  set tempo (meta event)  ****
			MetaMessage mt = new MetaMessage();
			byte[] bt = {0x07, (byte)0xa1, 0x20};
			mt.setMessage(0x51 ,bt, 3);
			me = new MidiEvent(mt,(long)0);
			t.add(me);
			
			//****  set track name (meta event)  ****
			mt = new MetaMessage();
			String TrackName = new String("midifile track");
			mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
			me = new MidiEvent(mt,(long)0);
			t.add(me);
			
			//****  set omni on  ****
			ShortMessage mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7D,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			//****  set poly on  ****
			mm = new ShortMessage();
			mm.setMessage(0xB0, 0x7F,0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			//****  set instrument to Piano  ****
			mm = new ShortMessage();
			mm.setMessage(0xC0, 0x00, 0x00);
			me = new MidiEvent(mm,(long)0);
			t.add(me);
			
			//****  note on - middle C  ****
			mm = new ShortMessage();
			mm.setMessage(0x90,(byte) this.getMIDIZahl(),0x60);
			me = new MidiEvent(mm,(long)1);
			t.add(me);
			
			//****  note off - middle C - 120 ticks later  ****
			mm = new ShortMessage();
			mm.setMessage(0x80,(byte) this.getMIDIZahl(),0x40);
			me = new MidiEvent(mm,(long)(1+ende));
			t.add(me);
			
			//****  set end of track (meta event) 19 ticks later  ****
			mt = new MetaMessage();
			byte[] bet = {}; // empty array
			mt.setMessage(0x2F,bet,0);
			me = new MidiEvent(mt, (long)(1+ende));
			t.add(me);
			
			try{
				Sequencer sequencer = MidiSystem.getSequencer();
				sequencer.setSequence(s);
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
		}
		catch(Exception e){
			System.out.println("Exception caught " + e.toString());
		}
	}
	
	/**
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
	**/
	 
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