import javax.sound.midi.*;
import java.io.*;

public class Infos{

	public static void main( String[] args ){
        String fileName = args[0];

		File midiFile = new File( fileName );
		System.out.println( "Sequence file name: " + fileName );
		// Using MidiSystem, convert the file to a sequence.
		try{
			Sequence sequence = MidiSystem.getSequence( midiFile );
			if (sequence != null) {
				// Print sequence information
				System.out.println( " length: " + 
								   sequence.getTickLength() + " ticks" );
				System.out.println( " duration: " + 
								   sequence.getMicrosecondLength() + 
								   " micro seconds" );
			}
		}
		catch(InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        }
		catch(IOException ioe) {
            System.out.println("I/O Error!");
        } 
	}
}