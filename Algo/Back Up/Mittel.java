public class Mittel{

	public static double arithMittel( int[] list ){
		int ls = list.length;
		double erg = 0.0d;
		for( int i : list ){
			erg = erg + i;
		}
		double nerg = erg/ls;
		return nerg;
	}

	public static double stDev( double am , int[] list2 ){
		int ls2 = list2.length;
		double zerg = 0.0d;
		for( int i : list2 ){
			zerg = zerg + Math.pow((i - am), 2);
		}
		double var = zerg/ls2;
		double stdev = Math.sqrt( var );
		return stdev;
	}
	
	public static void main( String[] args ){
		int[] pz = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293};
		double arm = arithMittel( pz );
		System.out.println( arithMittel( pz ));
		System.out.println( stDev( arm, pz ));
	}
	
}