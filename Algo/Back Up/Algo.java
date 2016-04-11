import java.math.*;

public class Algo{

	public static void main( String[] args ){
		for( int x = 1; x < 7; x++ ){
			for( int y = 0; y < 25; y++ ){
				long erg = (long) Math.pow(2.0, x) * (long) Math.pow(3.0, y);
				System.out.println( erg );
			}
		}
	}

}