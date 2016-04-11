import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Zufallsfolge implements Noten{
	
	public static Map<Integer, Note> notenzahlen = new HashMap<Integer, Note>();
	static{
		notenzahlen.put(4, a0);
		notenzahlen.put(6, new KlavierNote("b", 0));
		notenzahlen.put(8, new KlavierNote("h", 0));
		notenzahlen.put(10, new KlavierNote("c", 1));
		notenzahlen.put(12, new KlavierNote("cis", 1));
		notenzahlen.put(14, new KlavierNote("d", 1));
		notenzahlen.put(16, new KlavierNote("dis", 1));
		notenzahlen.put(18, new KlavierNote("e", 1));
		notenzahlen.put(20, new KlavierNote("f", 1));
		notenzahlen.put(22, new KlavierNote("fis", 1));
		notenzahlen.put(24, new KlavierNote("g", 1));
		notenzahlen.put(26, new KlavierNote("gis", 1));
		notenzahlen.put(28, new KlavierNote("a", 1));
		notenzahlen.put(30, new KlavierNote("b", 1));
		notenzahlen.put(32, new KlavierNote("h", 1));
		notenzahlen.put(34, new KlavierNote("c", 2));
		notenzahlen.put(36, new KlavierNote("cis", 2));
		notenzahlen.put(38, new KlavierNote("d", 2));
		notenzahlen.put(40, new KlavierNote("dis", 2));
		notenzahlen.put(42, new KlavierNote("e", 2));
		notenzahlen.put(44, new KlavierNote("f", 2));
		notenzahlen.put(46, new KlavierNote("fis", 2));
		notenzahlen.put(48, new KlavierNote("g", 2));
		notenzahlen.put(50, new KlavierNote("gis", 2));
		notenzahlen.put(52, new KlavierNote("a", 2));
		notenzahlen.put(54, new KlavierNote("b", 2));
		notenzahlen.put(56, new KlavierNote("h", 2));
		notenzahlen.put(58, new KlavierNote("c", 3));
		notenzahlen.put(60, new KlavierNote("cis", 3));
		notenzahlen.put(62, new KlavierNote("d", 3));
		notenzahlen.put(64, new KlavierNote("dis", 3));
		notenzahlen.put(66, new KlavierNote("e", 3));
		notenzahlen.put(68, new KlavierNote("f", 3));
		notenzahlen.put(70, new KlavierNote("fis", 3));
		notenzahlen.put(72, new KlavierNote("g", 3));
		notenzahlen.put(74, new KlavierNote("gis", 3));
		notenzahlen.put(76, new KlavierNote("a", 3));
		notenzahlen.put(78, new KlavierNote("b", 3));
		notenzahlen.put(80, new KlavierNote("h", 3));
		notenzahlen.put(82, new KlavierNote("c", 4));
		notenzahlen.put(84, new KlavierNote("cis", 4));
		notenzahlen.put(86, new KlavierNote("d", 4));
		notenzahlen.put(88, new KlavierNote("dis", 4));
		notenzahlen.put(90, new KlavierNote("e", 4));
		notenzahlen.put(92, new KlavierNote("f", 4));
		notenzahlen.put(94, new KlavierNote("fis", 4));
		notenzahlen.put(96, new KlavierNote("g", 4));
		notenzahlen.put(98, new KlavierNote("gis", 4));
		notenzahlen.put(100, new KlavierNote("a", 4));
		notenzahlen.put(102, new KlavierNote("b", 4));
		notenzahlen.put(104, new KlavierNote("h", 4));
		notenzahlen.put(106, new KlavierNote("c", 5));
		notenzahlen.put(108, new KlavierNote("cis", 5));
		notenzahlen.put(110, new KlavierNote("d", 5));
		notenzahlen.put(112, new KlavierNote("dis", 5));
		notenzahlen.put(114, new KlavierNote("e", 5));
		notenzahlen.put(116, new KlavierNote("f", 5));
		notenzahlen.put(118, new KlavierNote("fis", 5));
		notenzahlen.put(120, new KlavierNote("g", 5));
		notenzahlen.put(122, new KlavierNote("gis", 5));
		notenzahlen.put(124, new KlavierNote("a", 5));
		notenzahlen.put(126, new KlavierNote("b", 5));
		notenzahlen.put(128, new KlavierNote("h", 5));
		notenzahlen.put(130, new KlavierNote("c", 6));
		notenzahlen.put(132, new KlavierNote("cis", 6));
		notenzahlen.put(134, new KlavierNote("d", 6));
		notenzahlen.put(136, new KlavierNote("dis", 6));
		notenzahlen.put(138, new KlavierNote("e", 6));
		notenzahlen.put(140, new KlavierNote("f", 6));
		notenzahlen.put(142, new KlavierNote("fis", 6));
		notenzahlen.put(144, new KlavierNote("g", 6));
		notenzahlen.put(146, new KlavierNote("gis", 6));
		notenzahlen.put(148, new KlavierNote("a", 6));
		notenzahlen.put(150, new KlavierNote("b", 6));
		notenzahlen.put(152, new KlavierNote("h", 6));
		notenzahlen.put(154, new KlavierNote("c", 7));
		notenzahlen.put(156, new KlavierNote("cis", 7));
		notenzahlen.put(158, new KlavierNote("d", 7));
		notenzahlen.put(160, new KlavierNote("dis", 7));
		notenzahlen.put(162, new KlavierNote("e", 7));
		notenzahlen.put(164, new KlavierNote("f", 7));
		notenzahlen.put(166, new KlavierNote("fis", 7));
		notenzahlen.put(168, new KlavierNote("g", 7));
		notenzahlen.put(170, new KlavierNote("gis", 7));
		notenzahlen.put(172, new KlavierNote("a", 7));
		notenzahlen.put(174, new KlavierNote("b", 7));
		notenzahlen.put(176, new KlavierNote("h", 7));
		notenzahlen.put(178, new KlavierNote("c", 8));
	}
	
	public static Map<Integer, String> notenwerte = new HashMap<Integer, String>();
	static{
		notenwerte.put(2, "ganze.");
		notenwerte.put(3, "ganze");
		notenwerte.put(5, "ganze.3");
		notenwerte.put(7, "ganze3");
		notenwerte.put(11, "halbe..");
		notenwerte.put(13, "halbe.");
		notenwerte.put(17, "halbe");
		notenwerte.put(19, "halbe..3");
		notenwerte.put(23, "halbe.3");
		notenwerte.put(29, "halbe3");
		notenwerte.put(31, "viertel..");
		notenwerte.put(37, "viertel.");
		notenwerte.put(41, "viertel");
		notenwerte.put(43, "viertel..3");
		notenwerte.put(47, "viertel.3");
		notenwerte.put(53, "viertel3");
		notenwerte.put(59, "viertel..5");
		notenwerte.put(61, "viertel.5");
		notenwerte.put(67, "viertel5");
		notenwerte.put(71, "achtel..");
		notenwerte.put(73, "achtel.");
		notenwerte.put(79, "achtel");
		notenwerte.put(83, "achtel..3");
		notenwerte.put(89, "achtel.3");
		notenwerte.put(97, "achtel3");
		notenwerte.put(101, "achtel..5");
		notenwerte.put(103, "achtel.5");
		notenwerte.put(107, "achtel5");
		notenwerte.put(109, "sechzehntel..");
		notenwerte.put(113, "sechzehntel.");
		notenwerte.put(127, "sechzehntel");
		notenwerte.put(131, "sechzehntel..3");
		notenwerte.put(137, "sechzehntel.3");
		notenwerte.put(139, "sechzehntel3");
		notenwerte.put(149, "sechzehntel..5");
		notenwerte.put(151, "sechzehntel.5");
		notenwerte.put(157, "sechzehntel5");
		notenwerte.put(163, "zweiunddreissigstel..");
		notenwerte.put(167, "zweiunddreissigstel.");
		notenwerte.put(173, "zweiunddreissigstel");
	}
	
	public static int myRandom(int low, int high){
		return (int) (Math.random() * (high - low) + low);
	}
	
	public static int[] lacreator( int[] pz, int[] anzahl ){
		if( pz.length == anzahl.length ){
			int zzahl = 0;
			for( int el : anzahl ){
				zzahl = zzahl + el;
			}
			int[] erg = new int[zzahl];
			int m = 0;
			for(int i = 0 ; i < pz.length; i++ ){
				int zerg = pz[i];
				int zahl = anzahl[i];
				int c = 0;
				while( c < zahl ){
					erg[m] = zerg;
					++m;
					++c;
				}
			}
			return erg;
		}
		else{
			int[] idiot = {3, 1, 4};
			return idiot;
		}
	}
	
	public static void randomisierung( int n ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {
		//System.out.println(n+ " Notenzahlen, mitsamt den entsprechenden PZZ: \n");
		
		int[] pz = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173};
		int[] verteilungf1 = {1, 6, 10, 13, 21, 24, 31, 34, 40, 49, 51, 61, 66, 67, 73, 77, 86, 88, 94, 96, 95, 95, 95, 102, 102, 101, 103, 102, 98, 96, 89, 92, 82, 76, 67, 60, 55, 41, 33, 17};
		int[] vorkommenpzz = {2, 78, 78, 76, 76, 74, 72, 72, 70, 68, 66, 66, 64, 62, 62, 58, 58, 58, 56, 54, 52, 48, 46, 46, 42, 40, 40, 38, 36, 34, 28, 28, 24, 22, 18, 16, 14, 10, 8, 4};
		
		int[] s = lacreator( pz, verteilungf1 );
		int[] s2 = lacreator( pz, vorkommenpzz );
		
		int ls = s.length;
		int ls2 = s2.length;
		
		Note[] noten = new Note[n];
		String[] rhythmus = new String[n];
		
		int z = 0;
		while ( z < n ){
			int a = myRandom(0, ls);
			int b = s[a];
			for( int count = 0; count < 10000; count++){
				int c = myRandom(0, ls2);
				int d = s2[c];
				int erg = b + d;
				if( erg < 179 && erg % 2 == 0){
					int[] ergebnis = {b, d};
					String PZZ = Arrays.toString( ergebnis );
					System.out.println( erg+ " = " +PZZ+ "\t\t1.NW = " +b);
					//System.out.println( erg );
					Note znote = notenzahlen.get( erg );
					noten[z] = znote;
					String zrhy = notenwerte.get( b );
					rhythmus[z] = zrhy;
					break;
				}
				else{
					continue;
				}
			}
			z++;
		}
		
		Melodie random = new Melodie( noten, rhythmus );
		random.playMelodie();
	}
	
	public static void main( String[] args ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {
		if( args.length == 1 ){
			randomisierung( Integer.parseInt( args[0] ) );
		}
		else{
			System.err.println("Bitte geben Sie die Anzahl der Noten als Argument ein.");
		}
	}
}