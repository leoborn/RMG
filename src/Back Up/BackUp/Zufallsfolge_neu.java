import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Zufallsfolge_neu implements Noten{
	
	public static Map<Integer, Note> notenzahlen = new HashMap<Integer, Note>();
	static{
		notenzahlen.put(4, a0);
		notenzahlen.put(6, b0);
		notenzahlen.put(8, h0);
		notenzahlen.put(10, c1);
		notenzahlen.put(12, cis1);
		notenzahlen.put(14, d1);
		notenzahlen.put(16, dis1);
		notenzahlen.put(18, e1);
		notenzahlen.put(20, f1);
		notenzahlen.put(22, fis1);
		notenzahlen.put(24, g1);
		notenzahlen.put(26, gis1);
		notenzahlen.put(28, a1);
		notenzahlen.put(30, b1);
		notenzahlen.put(32, h1);
		notenzahlen.put(34, c2);
		notenzahlen.put(36, cis2);
		notenzahlen.put(38, d2);
		notenzahlen.put(40, dis2);
		notenzahlen.put(42, e2);
		notenzahlen.put(44, f2);
		notenzahlen.put(46, fis2);
		notenzahlen.put(48, g2);
		notenzahlen.put(50, gis2);
		notenzahlen.put(52, a2);
		notenzahlen.put(54, b2);
		notenzahlen.put(56, h2);
		notenzahlen.put(58, c3);
		notenzahlen.put(60, cis3);
		notenzahlen.put(62, d3);
		notenzahlen.put(64, dis3);
		notenzahlen.put(66, e3);
		notenzahlen.put(68, f3);
		notenzahlen.put(70, fis3);
		notenzahlen.put(72, g3);
		notenzahlen.put(74, gis3);
		notenzahlen.put(76, a3);
		notenzahlen.put(78, b3);
		notenzahlen.put(80, h3);
		notenzahlen.put(82, c4);
		notenzahlen.put(84, cis4);
		notenzahlen.put(86, d4);
		notenzahlen.put(88, dis4);
		notenzahlen.put(90, e4);
		notenzahlen.put(92, f4);
		notenzahlen.put(94, fis4);
		notenzahlen.put(96, g4);
		notenzahlen.put(98, gis4);
		notenzahlen.put(100, a4);
		notenzahlen.put(102, b4);
		notenzahlen.put(104, h4);
		notenzahlen.put(106, c5);
		notenzahlen.put(108, cis5);
		notenzahlen.put(110, d5);
		notenzahlen.put(112, dis5);
		notenzahlen.put(114, e5);
		notenzahlen.put(116, f5);
		notenzahlen.put(118, fis5);
		notenzahlen.put(120, g5);
		notenzahlen.put(122, gis5);
		notenzahlen.put(124, a5);
		notenzahlen.put(126, b5);
		notenzahlen.put(128, h5);
		notenzahlen.put(130, c6);
		notenzahlen.put(132, cis6);
		notenzahlen.put(134, d6);
		notenzahlen.put(136, dis6);
		notenzahlen.put(138, e6);
		notenzahlen.put(140, f6);
		notenzahlen.put(142, fis6);
		notenzahlen.put(144, g6);
		notenzahlen.put(146, gis6);
		notenzahlen.put(148, a6);
		notenzahlen.put(150, b6);
		notenzahlen.put(152, h6);
		notenzahlen.put(154, c7);
		notenzahlen.put(156, cis7);
		notenzahlen.put(158, d7);
		notenzahlen.put(160, dis7);
		notenzahlen.put(162, e7);
		notenzahlen.put(164, f7);
		notenzahlen.put(166, fis7);
		notenzahlen.put(168, g7);
		notenzahlen.put(170, gis7);
		notenzahlen.put(172, a7);
		notenzahlen.put(174, b7);
		notenzahlen.put(176, h7);
		notenzahlen.put(178, c8);
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