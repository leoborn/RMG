import javax.sound.midi.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

//Implementiert einen zufallsbasierten Kompositionsalgorithmus, der eine Melodie vorgegebener Laenge (Anzahl Noten) generiert
//Jede Note wird aus zwei Primzahlen, die addiert eine gerade Zahl ergeben, ermittelt.
//Die erste dieser Primzahlen ist der Notenwert.
public class RMG1 implements Notenmaterial{
	
	//Zuordnung von geraden Zahlen auf Noten
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
	
	//Zuordnung der Primzahlen auf Notenwerte
	public static Map<Integer, String> notenwerte = new HashMap<Integer, String>();
	static{
		notenwerte.put(2, "g.");
		notenwerte.put(3, "g");
		notenwerte.put(5, "g.3");
		notenwerte.put(7, "g3");
		notenwerte.put(11, "h..");
		notenwerte.put(13, "h.");
		notenwerte.put(17, "h");
		notenwerte.put(19, "h..3");
		notenwerte.put(23, "h.3");
		notenwerte.put(29, "h3");
		notenwerte.put(31, "v..");
		notenwerte.put(37, "v.");
		notenwerte.put(41, "v");
		notenwerte.put(43, "v..3");
		notenwerte.put(47, "v.3");
		notenwerte.put(53, "v3");
		notenwerte.put(59, "v..5");
		notenwerte.put(61, "v.5");
		notenwerte.put(67, "v5");
		notenwerte.put(71, "a..");
		notenwerte.put(73, "a.");
		notenwerte.put(79, "a");
		notenwerte.put(83, "a..3");
		notenwerte.put(89, "a.3");
		notenwerte.put(97, "a3");
		notenwerte.put(101, "a..5");
		notenwerte.put(103, "a.5");
		notenwerte.put(107, "a5");
		notenwerte.put(109, "s..");
		notenwerte.put(113, "s.");
		notenwerte.put(127, "s");
		notenwerte.put(131, "s..3");
		notenwerte.put(137, "s.3");
		notenwerte.put(139, "s3");
		notenwerte.put(149, "s..5");
		notenwerte.put(151, "s.5");
		notenwerte.put(157, "s5");
		notenwerte.put(163, "z..");
		notenwerte.put(167, "z.");
		notenwerte.put(173, "z");
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
	
	public static int fib( int a ){
		if( a==1 || a==2 ){
			return 1;
		}
		else{
			return fib(a-1) + fib(a-2);
		}
	}
	
	public static void randomisierung( int n ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {
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
		
		int number;
		BufferedReader b = new BufferedReader(new FileReader("Material/zahl.txt"));
		try{ 
			number = Integer.parseInt( b.readLine() );
			number++;
			BufferedWriter bw = new BufferedWriter(new FileWriter("Material/zahl.txt"));
			bw.write( ""+number );
			bw.close();
			Melodie random = new Melodie( noten, rhythmus );
			random.saveTo( "RandomMel" + number );
			System.out.println(random.toString());
			random.playMelodie();
		}
		catch(NumberFormatException e){
			System.out.println("Das ist gar keine Zahl!"+e);
		}
		b.close();
	}
	
	public static void randomisierungMitChords( int n ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {
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
				boolean interrupt = false;
				int c = myRandom(0, ls2);
				int d = s2[c];
				int erg = b + d;
				if( erg < 179 && erg % 2 == 0){
					for( int kk = 1; kk < 20; kk++ ){
						if( z + 1 == fib(kk) && z + 1 != 1 ){
							Note znote = notenzahlen.get( erg );
							Akkord zakkord = new Dreiklang( znote );
							noten[z] = zakkord;
							String zrhy = notenwerte.get( b );
							rhythmus[z] = zrhy;
							interrupt = true;
							break;
						}
					}
					if( interrupt == false ){
						Note znote = notenzahlen.get( erg );
						noten[z] = znote;
						String zrhy = notenwerte.get( b );
						rhythmus[z] = zrhy;
						break;
					}
					else{
						break;
					}
				}
				else{
					continue;
				}
			}
			z++;
		}
		
		int number;
		BufferedReader b = new BufferedReader(new FileReader("Material/zahlChord.txt"));
		try{ 
			number = Integer.parseInt( b.readLine() );
			number++;
			BufferedWriter bw = new BufferedWriter(new FileWriter("Material/zahlChord.txt"));
			bw.write( ""+number );
			bw.close();
			Melodie random = new Melodie( noten, rhythmus );
			random.saveTo( "RandomMelMitChords" + number );
			System.out.println(random.toString());
			random.playMelodie();
		}
		catch(NumberFormatException e){
			System.out.println("Das ist gar keine Zahl!"+e);
		}
		b.close();
	}
	
	public static void main( String[] args ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {
		if( args.length == 1 && args[0].matches( "-help" ) ){
			System.out.println("");
			System.out.println( "Welcome to the Random Melody Generator (RMG)" );
			System.out.println("----------------------------------------------");
			System.out.println("");
			System.out.println("You can use it in two ways:\n1. Generate a melody without chords by executing the program with only one argument.\n   This argument should be the number of notes you wish the melody to have.");
			System.out.println("   An example execution looks like this: java RMG1 20");
			System.out.println("");
			System.out.println("2. Generate a melody with chords by providing two arguments. The first argument, \"+Chords\", is to indicate that you wish chords to be included.");
			System.out.println("   The second is again the number of total notes (including chords) you wish the melody to have.");
			System.out.println("   Be advised that you cannot decide where chords will be played. But maybe you will be able to spot the pattern by which they are generated?");
			System.out.println("   An example execution thus looks like this: java RMG1 +Chords 20");
		}
		else if( args.length == 1 ){
			randomisierung( Integer.parseInt( args[0] ) );
		}
		else if( args.length == 2 ){
			if( args[0].matches( "\\+Chords" ) ){
				randomisierungMitChords( Integer.parseInt( args[1] ) );
			}
			else{
				System.err.println("Bitte geben Sie die Argumente korrekt an.");
			}
		}
		else{
			System.err.println("Bitte geben Sie die Anzahl der Noten als Argument ein.");
		}
	}
}