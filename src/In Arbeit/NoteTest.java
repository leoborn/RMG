import javax.sound.midi.*;
import java.io.*;

public class NoteTest{

	public static void main( String[] args ) throws MidiUnavailableException, InvalidMidiDataException, IOException {
		Note c4 = new Note("c", 4);
		c4.play();
		
		Note cis4 = new Note("cis", 4);
		Note d4 = new Note("d", 4);
		Note dis4 = new Note("dis", 4);
		Note f4 = new Note("f", 4);

		long erg = (long) 100;
		cis4.play( erg );
		
		Note[] ar = new Note[]{c4, cis4, d4, dis4, f4};
		String[] rhy = new String[]{"a", "a", "a", "a", "v"};
		Melodie2 mel = new Melodie2(ar, rhy);
		mel.playMelodie();
	}

}