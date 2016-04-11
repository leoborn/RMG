import javax.sound.midi.*;
import java.io.*;

public class ChordTester implements Material{

	public static void main( String[] args ) throws MidiUnavailableException, InvalidMidiDataException, IOException {
		
		Note[] na = new Note[] {e3, g3, h3};
		Akkord em = new Dreiklang("E-Moll", 3, na );
		
		Note[] na2 = new Note[] {e3, g3, c4};
		Akkord em2 = new Dreiklang("Em6", 3, na2 );
		
		Note[] na3 = new Note[] {e3, g3, h3, e4};
		Akkord em3 = new Vierklang("Em6", 3, na3 );
		
		Akkord edef = new Dreiklang(e3);
		Akkord edef2 = new Vierklang(e3);
		
		System.out.println( em.toString() );
		System.out.println( em.getPathToFiles() );
		long erg = 4000;
		//em.play( erg );
		
		System.out.println( em2.toString() );
		//em2.play();
		
		System.out.println( em3.toString() );
		//em3.play();
		
		//edef.play();
		edef2.play();
		
		
		/**
		Note[] ge = new Note[] {g2, h2, g3};
		Akkord gem = new Dreiklang("E-Moll", 3, ge );
		
		Note[] ge2 = new Note[] {g2, c3, e3};
		Akkord gem2 = new Dreiklang("E-Moll", 3, ge2 );
		
		Note[] gmela = new Note[] {e2, g2, c3, h2, gem, gem, a2, c3, h2, gem2, gem2, g2, h2, c3, h2, g2, c3, e3, c3, g2};
		String[] rhyt = new String[] {"achtel", "achtel", "achtel", "achtel", "viertel", "viertel", "achtel", "achtel", "viertel", "viertel", "halbe",
			"sechzehntel", "sechzehntel", "sechzehntel", "sechzehntel", "sechzehntel", "sechzehntel", "sechzehntel", "sechzehntel", "halbe"};
		Melodie gmel = new Melodie(gmela, rhyt, "Gitarre");
		
		gmel.playMelodie();
		 **/
	}


}