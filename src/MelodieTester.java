import javax.sound.midi.*;
import java.io.*;

public class MelodieTester implements Notenmaterial{
	
	public static void main ( String[] args ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {

		Note[] mel = new Note[]{a3, h3, c4, d4, e4, f4, g4, c5, a4};
		Melodie mell = new Melodie(mel);
		
		mell.playMelodie();
		
		Note[] gdura = new Note[] {g3, h3, g4};
		Akkord g5 = new Dreiklang("DES", 3, gdura);
		
		Note[] na = new Note[] {d4, d4, d4, g4, g4, a4, a4, d5, h4, g4, g4, h4, g4, e4, c5, a4, ges4};
		String[] b1 = new String[] {"s", "a.", "s", "v", "v", "v", "v", "v.", "a", "a.", "s", "a.", "s", 
			"v", "h", "a.", "s"};
		Melodie m = new Melodie(na, b1);
		
		System.out.println("m.toString() = " + m.toString() + "\n");
		System.out.println("m.length() = " + m.length() );
		System.out.println("m.getTakt() = " + m.getTakt());
		System.out.println("m.getRhythmuswert(6) = " + m.getRhythmuswert(6));
		System.out.println("m.getNote(0) = " + m.getNote(0) + "\n");
		
		//System.out.println("m.playMelodie()");
		//m.playMelodie();
		
		
		System.out.println("\nm.appendNote(g5, 'g')");
		m.appendNote(g5, "g");
		System.out.println("m.toString() = " + m.toString());
		System.out.println("m.playMelodie()");
		m.playMelodie();
		
		
		System.out.println("m.transposeUpBy( 4 )");
		m.transposeUpBy(4);
		System.out.println("m.toString() = " + m.toString());
		System.out.println("m.playMelodie()");
		m.playMelodie();
		 
	
		/**
		System.out.println("m.krebs()");
		m.krebs();
		System.out.println("m.toString() = " + m.toString());
		System.out.println("m.playMelodie()");
		m.playMelodie();
		 
		
		
		System.out.println("m.transposeOctaveUp()");
		m.transposeOctaveUp();
		System.out.println("m.toString() = " + m.toString() + "\n");
		//System.out.println("m.playMelodie()");
		//m.playMelodie();
		
		System.out.println("\nm.transposeOctaveDown()");
		m.transposeOctaveDown();
		System.out.println("m.transposeOctaveDown()");
		m.transposeOctaveDown();
		System.out.println("m.transposeOctaveDown()");
		m.transposeOctaveDown();
		System.out.println("m.toString() = " + m.toString() + "\n");
		 
		m.playMelodie();
		
		**/
		
		
		
		
		
		
		
		/**
		GitarrenNote gn1 = new GitarrenNote("e", 2, "E", 0);
		GitarrenNote gn2 = new GitarrenNote("g", 2, "E", 3);
		GitarrenNote gn3 = new GitarrenNote("a", 2, "E", 5);
		GitarrenNote gn4 = new GitarrenNote("b", 2, "E", 6);
		GitarrenNote gn5 = new GitarrenNote("h", 2, "E", 7);
		
		System.out.println("gn1.getName() = " + gn1.getName());
		System.out.println("gn1.getOktave() = " + gn1.getOktave());
		System.out.println("gn1.getPosition() = " + gn1.getPosition());
		System.out.println("gn1.toString() = " + gn1.toString() + "\n");
		
		GitarrenNote[] gnm = new GitarrenNote[] {gn1, gn1, gn2, gn1, gn3, gn4, gn5, gn1, gn1, gn2, gn1, gn5, gn4, gn3};
		String[] rhy= new String[] {"achtel.", "sechzehntel", "achtel", "viertel", "achtel", "achtel", "achtel", "achtel.", 
			"sechzehntel", "achtel", "viertel", "achtel", "achtel", "achtel"};
		Melodie m3 = new Melodie(gnm, rhy);
		
		m3.playMelodie();
		**/
		
		/**
		 Note[] na1 = new Note[] {e, c, h, a, d, fis, g, h, a, c};
		 String t = "4/4";
		 String[] b = new String[] {"achtel", "achtel.", "sechzehntel", "halbe", "achtel", 
		 "achtel", "achtel", "viertel", "viertel", "viertel."};
		 Melodie m1 = new Melodie(na1, t, b);		
		 
		 System.out.println("\nm1.toString() = " + m1.toString());
		 System.out.println("m1.playMelodie()");
		 m1.playMelodie();
		 System.out.println("m.equals(m1) = " + m.equals(m1) + "\n");
		 **/
	}
}