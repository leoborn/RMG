public class Mittel2{

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
		//return var;
		double stdev = Math.sqrt( var );
		return stdev;
	}
	
	public static void main( String[] args ){
		int[] pz = {1, 2, 3, 4, 5};
		double arm = arithMittel( pz );
		System.out.println( arithMittel( pz ));
		System.out.println( stDev( arm, pz ));
	}
	
}