public class VorkommenPZAlt{

	public static void vorkommen( int[] pz ){
		for( int n : pz ){
			int c = 1;
			for( int i : pz ){
				int erg = n + i;
				if( erg < 179 && erg % 2 == 0  ){
					c += 1;
				}
			}
			int nerg = (c * 2) - 2;
			//System.out.println("Das Vorkommen von " +n+ " betraegt: " +nerg);
			System.out.println( n );
		}
	}
	
	public static void main( String[] args ){
		int[] s = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173};
		vorkommen( s );
	}
}