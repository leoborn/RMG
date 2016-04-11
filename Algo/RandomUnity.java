// Dies hier ist die leicht geänderte Version.

import java.util.Arrays;

public class RandomUnity{

	public static int myRandom(int low, int high){
		return (int) (Math.random() * (high - low) + low);
	}
	
	public static int tempo(){
		int temp = myRandom(100, 151);
		return temp;
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
	
	public static void randomisierung( int n ){
		System.out.println(n+ " Notenzahlen, mitsamt den entsprechenden PZZ: \n");
		
		int[] pz = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293};
		int[] verteilungf1 = {1, 6, 10, 14, 21, 25, 33, 37, 43, 53, 57, 66, 71, 75, 80, 89, 99, 102, 108, 112, 115, 120, 123, 132, 141, 147, 150, 148, 151, 149, 160, 161, 164, 166, 168, 170, 167, 174, 167, 167, 167, 169, 173, 174, 165, 167, 157, 151, 146, 148, 135, 131, 132, 113, 108, 93, 87, 87, 71, 63, 64, 28};
		int[] vorkommenpzz = {2, 122, 122, 122, 120, 120, 120, 120, 116, 114, 114, 110, 108, 108, 106, 104, 104, 104, 100, 98, 98, 94, 92, 92, 90, 90, 90, 86, 86, 82, 78, 76, 74, 74, 70, 70, 66, 66, 62, 60, 58, 58, 56, 56, 52, 52, 46, 42, 40, 40, 36, 34, 34, 28, 26, 22, 20, 20, 16, 14, 14, 6};
		
		int[] s = lacreator( pz, verteilungf1 );
		int[] s2 = lacreator( pz, vorkommenpzz );
		
		int ls = s.length;
		int ls2 = s2.length;
		
		int z = 0;
		while ( z < n ){
			int a = myRandom(0, ls);
			int b = s[a];
			for( int count = 0; count < 10000; count++){
				int c = myRandom(0, ls2);
				int d = s2[c];
				int erg = b + d;
				if( erg < 304 && erg % 2 == 0){
					int[] ergebnis = {b, d};
					String PZZ = Arrays.toString( ergebnis );
					System.out.println( erg+ " = " +PZZ+ "\t\t1.NW = " +b);
					//System.out.println( erg );
					break;
				}
				else{
					continue;
				}
			}
			z++;
		}
	}
	
	public static void main( String[] args ){
		if( args.length == 1 ){
			int geschw = tempo();
			System.out.println("Das Tempo des Stueckes soll " +geschw+ " bpm betragen. \n");
			randomisierung( Integer.parseInt( args[0] ) );
		}
		else{
			System.err.println("Bitte geben Sie die Anzahl der Noten als Argument ein.");
		}
	}
}