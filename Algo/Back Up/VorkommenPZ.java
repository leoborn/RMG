public class VorkommenPZ{

	public static void vorkommen( int[] pz ){
		for( int n : pz ){
			if( n < 152 ){
				int c = 1;
				for( int i : pz ){
					int erg = n + i;
					if( erg < 304 && erg % 2 == 0  ){
						c += 1;
					}
				}
				int nerg = (c * 2) - 2;
				System.out.println("Das Vorkommen von " +n+ " betraegt: " +nerg);
			}	
			else{
				int c = 0;
				for( int i : pz ){
					int erg = n + i;
					if( erg < 304 && erg % 2 == 0 ){
						c += 1;
					}
				}
				int nerg2 = c * 2;
				System.out.println("Das Vorkommen von " +n+ " betraegt: " +nerg2);
			}
		}
	}
	
	public static void main( String[] args ){
		int[] s = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293};
		vorkommen( s );
	}
}