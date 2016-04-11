import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class GitarrenNote extends Note{
	
	private final String saite;
	private final int bund;
	//private final int zahl;
	
	public GitarrenNote( String name, int oktave, String saite, int bund ) throws IllegalArgumentException {
		super( name, oktave );
		this.saite = saite;
		if( bund >= 0 && bund <= 24 ){
			this.bund = bund;
		}
		//if( zahl > 2 && zahl%2 == 0 ){
		//	this.zahl = zahl;
		//}
		else{
			throw new IllegalArgumentException("Das Argument muss gerade und >= 2 sein.");
		}
	}
	
	public GitarrenNote( String name, String saite, int bund ) throws IllegalArgumentException {
		super( name );
		this.saite = saite;
		this.bund = bund;
		//if( zahl > 2 && zahl%2 == 0 ){
		//	this.zahl = zahl;
		//}
		else{
			throw new IllegalArgumentException("Das Argument muss gerade und >= 2 sein.");
		}
	}
	
	/**
	 public void setName( String b ) throws IllegalArgumentException {
	 if( b.endsWith("'") || this.name.endsWith("'") ){
	 this.name = b;
	 }
	 else{
	 throw new IllegalArgumentException( "Ungueltige Transposition." );
	 }
	 }
	 
	 public void setZahl( int c ) throws IllegalArgumentException {
	 if( c > 0 && c%2 == 0 ){
	 this.zahl = c;
	 }
	 else{
	 throw new IllegalArgumentException( "Das Argument muss gerade und >= 2 sein." );
	 }
	 }
	 **/
	
	public String getName(){
		return super.getName();
	}
	
	public int getOktave(){
		return super.getOktave();
	}
	
	public String getPosition(){
		return "Saite: " + this.saite + ", " + this.bund;
	}
	
	@Override
	public String toString() {
		return this.getName() + " (" + this.getOktave() + ") (" + this.saite + this.bund + ")";
	}
	
	public int hashCode(){
		int result = 42;
		result = 31 * result + super.getName().hashCode();
		result = 31 * result * super.getOktave();
		result = 31 * result * this.zahl;
		return result;
	}
	
	@Override
	public boolean equals( Object o ){
		boolean erg = false;
		if( o instanceof GitarrenNote ){
			GitarrenNote neu = (GitarrenNote) o;
			if( this.getName().equals(neu.getName()) && this.getOktave() == neu.getOktave() && this.zahl == neu.zahl ){
				erg = true;
			}
		}
		return erg;
	}
	
	/**
	public String pzz(){
		int[] pz = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499};
	
		Set<Integer> s = new HashSet<Integer>();
		for( int x : pz ){
			for( int y : pz ){
				int z = x + y;
				if( z == this.zahl ){
					s.add(x);
					s.add(y);
				}
				else{
					continue;
				}
			}
		}
	 
		int groesse = s.size();
		int[] resultat = new int[groesse];
		int count = 0;
		for( Integer notenwert : s ){
			resultat[count] = notenwert;
			count++;
		}
	 
		Arrays.sort( resultat );
		return Arrays.toString( resultat );
	 }
	 **/
}