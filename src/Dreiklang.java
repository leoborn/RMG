import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;

public class Dreiklang extends Akkord{
	
	public Dreiklang( String name, int oktave, Note[] noten ){
		super( name, oktave, checkNoten(noten) );
	}
	
	public Dreiklang( Note n ){
		super( n.getName(), n.getOktave(), constructArray( n ) );
		String notenname = n.getName();
		setChordName( n.getName() + "-Aug" );
	}
	 
	public static Note[] checkNoten( Note[] noten ) throws IllegalArgumentException {
		if( noten.length == 3 ){
			return noten;
		}
		else{
			throw new IllegalArgumentException("Zu viele Noten fuer einen Dreiklang.");
		}
	}
	
	public String getName(){
		return super.getName();
	}
	
	public String getChordName(){
		return super.getChordName();
	}
	
	public void setChordName( String cname ){
		super.setChordName( cname );
	}
	
	public int getOktave(){
		return super.getOktave();
	}
	
	public String getPathToFile(){
		return super.getPathToFile();
	}
	
	public String getPathToFiles(){
		return super.getPathToFiles();
	}
	
	public void setPathToFile( String path ){
		super.setPathToFile( path );
	}
	
	public Note[] getNoten(){
		return super.getNoten();
	}
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public static Note[] constructArray( Note n ){
		int noktave = n.getOktave();
		String neuname = n.getName();
		if(	namensmap.containsKey(neuname) ){
			neuname = namensmap.get(neuname);
		}
		int pos = Arrays.asList(zulnamen).indexOf(neuname);
		int ht = 4;
		int limit = 8;
		if( noktave == 7 && pos > 4 && pos < 11){
			ht = 1;
			limit = 11;
		}
		if( noktave == 8 || noktave == 7 && pos == 11 ){
			noktave = noktave - 1;
		}
		Note[] vka = new Note[3];
		vka[0] = n;
		for( int i = 1; i < 3; i++ ){
			if( pos < limit ){
				pos = pos + ht;
				String nname1 = zulnamen[pos];
				Note n3 = new Note( nname1, noktave );
				vka[i] = n3;
			}
			else{
				pos = (pos + ht)%12;
				String nname2 = zulnamen[pos];
				Note n32 = new Note( nname2, noktave + 1 );
				vka[i] = n32;
			}
		}
		return vka;
	}
	
	public String toString(){
		return super.toString();
	}
	
	public static String limitName( String n ){
		if( n.length() == 1 ){
			return "";
		}
		else if( n.length() == 2 ){
			if( Arrays.asList(zulnamen).contains(n.toLowerCase()) || namensmap.containsKey(n.toLowerCase()) ){
				return "";
			}
			else{
				return n.substring(1,2);
			}
		}
		else{
			if( Arrays.asList(zulnamen).contains(n.substring(0,3).toLowerCase()) || namensmap.containsKey(n.substring(0,3).toLowerCase()) ){
				return n.substring(3, n.length());
			}
			else if( Arrays.asList(zulnamen).contains(n.substring(0,2).toLowerCase()) || namensmap.containsKey(n.substring(0,2).toLowerCase()) ){
				return n.substring(2, n.length());
			}
			else{
				return n.substring(1, n.length());
			}
		}
	}
	
	@Override
	public Note transposeUpBy( int i ){
		boolean erg = true;
		for( Note n : this.getNoten() ){
			String neuname = n.getName();
			if(	namensmap.containsKey(neuname) ){
				neuname = namensmap.get(neuname);
			}
			int altPos = Arrays.asList(zulnamen).indexOf(neuname);
			int noktave = n.getOktave();
			if( (altPos + i) > 11 ){
				noktave++;
			}
			if( noktave == 8 && !n.getName().equals("c")){
				erg = false;
				break;
			}
		}
		if( erg == true ){
			String altName = this.getChordName();
			int altOktave = this.getOktave();
			Note[] dka = new Note[3];
			for( int j = 0; j < 3; j++ ){
				Note alt = this.getNoten()[j];
				Note neu = alt.transposeUpBy( i );
				dka[j] = neu;
			}
			String limit = limitName(altName);
			String neuName = dka[0].getName() + limit;
			int neuOktave = dka[0].getOktave();
			Note transAkkord = new Dreiklang( neuName, neuOktave, dka );
			return transAkkord;
		}
		else{
			return this;
		}
	}
	
	@Override
	public Note transposeDownBy( int i ){
		boolean erg = true;
		for( Note n : this.getNoten() ){
			String neuname = n.getName();
			if(	namensmap.containsKey(neuname) ){
				neuname = namensmap.get(neuname);
			}
			int altPos = Arrays.asList(zulnamen).indexOf(neuname);
			int noktave = n.getOktave();
			if( (altPos + i) < 0 ){
				noktave--;
			}
			if( noktave == 0 && (!n.getName().equals("a") || !n.getName().equals("b") || !n.getName().equals("h")) ){
				erg = false;
				break;
			}
		}
		if( erg == true ){
			String altName = this.getChordName();
			int altOktave = this.getOktave();
			Note[] dka = new Note[3];
			for( int j = 0; j < 3; j++ ){
				Note alt = this.getNoten()[j];
				Note neu = alt.transposeDownBy( i );
				dka[j] = neu;
			}
			String limit = limitName(altName);
			String neuName = dka[0].getName() + limit;
			int neuOktave = dka[0].getOktave();
			Note transAkkord = new Dreiklang( neuName, neuOktave, dka );
			return transAkkord;
		}
		else{
			return this;
		}
	}
	
	@Override
	public void play() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		Note n1 = this.getNoten()[0];
		Note n2 = this.getNoten()[1];
		Note n3 = this.getNoten()[2];
		File midiFile1 = new File(n1.getPathToFile());
		File midiFile2 = new File(n2.getPathToFile());
		File midiFile3 = new File(n3.getPathToFile());
		try{
			Sequencer sequencer1 = MidiSystem.getSequencer();
			Sequence mySeq1 = MidiSystem.getSequence(midiFile1);
			
			Sequencer sequencer2 = MidiSystem.getSequencer();
			Sequence mySeq2 = MidiSystem.getSequence(midiFile2);
			
			Sequencer sequencer3 = MidiSystem.getSequencer();
			Sequence mySeq3 = MidiSystem.getSequence(midiFile3);
			
            sequencer1.setSequence(mySeq1);
            sequencer1.open();
            sequencer1.start();
			
			sequencer2.setSequence(mySeq2);
            sequencer2.open();
            sequencer2.start();
			
			sequencer3.setSequence(mySeq3);
            sequencer3.open();
            sequencer3.start();
            while( true ){
                if( sequencer1.isRunning() && sequencer2.isRunning() && sequencer3.isRunning() ){
                    try{
                        Thread.sleep(5);
                    }
					catch( InterruptedException ignore ){
                        break;
                    }
                }
				else{
                    break;
                }
            }
            sequencer1.stop();
            sequencer1.close();
			
			sequencer2.stop();
            sequencer2.close();
			
			sequencer3.stop();
            sequencer3.close();
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
	public void play( long ende ) throws MidiUnavailableException, InvalidMidiDataException, IOException{
		Note n1 = this.getNoten()[0];
		Note n2 = this.getNoten()[1];
		Note n3 = this.getNoten()[2];
		File midiFile1 = new File(n1.getPathToFile());
		File midiFile2 = new File(n2.getPathToFile());
		File midiFile3 = new File(n3.getPathToFile());
		try{
			Sequencer sequencer1 = MidiSystem.getSequencer();
			Sequence mySeq1 = MidiSystem.getSequence(midiFile1);
			
			Sequencer sequencer2 = MidiSystem.getSequencer();
			Sequence mySeq2 = MidiSystem.getSequence(midiFile2);
			
			Sequencer sequencer3 = MidiSystem.getSequencer();
			Sequence mySeq3 = MidiSystem.getSequence(midiFile3);
			
            sequencer1.setSequence(mySeq1);
            sequencer1.open();
            sequencer1.start();
			
			sequencer2.setSequence(mySeq2);
            sequencer2.open();
            sequencer2.start();
			
			sequencer3.setSequence(mySeq3);
            sequencer3.open();
            sequencer3.start();
			
			long erg = sequencer1.getTickPosition();
            while( erg < ende ){
                if( sequencer1.isRunning() && sequencer2.isRunning() && sequencer3.isRunning() ){
                    try{
                        Thread.sleep(3, 12500);
						erg = sequencer1.getTickPosition();
                    }
					catch( InterruptedException ignore ){
                        break;
                    }
                }
				else{
                    break;
                }
            }
            sequencer1.stop();
            sequencer1.close();
			
			sequencer2.stop();
            sequencer2.close();
			
			sequencer3.stop();
            sequencer3.close();
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
	public boolean equals( Object o ){
		boolean erg = true;
		if( o instanceof Dreiklang ){
			Dreiklang neu = (Dreiklang) o;
			for( int i = 0; i < this.getNoten().length; i++ ){
				if( this.getNoten()[i].equals(neu.getNoten()[i]) != true ){
					erg = false;
					break;
				}
			}
		}
		return erg;
	}
}