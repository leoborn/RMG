public class InstrumentenTest{
	
	public static void main( String[] args ){
		String[] normal = new String[] {"E", "A", "D", "G", "H", "e"};
		String[] drop = new String[] {"D", "A", "D", "G", "H", "e"};
		String[] zweiHums = new String[] {"Humbucker", "Humbucker"};
		EGitarre ibanez = new EGitarre("Ibanez", "RG321", normal, 24, "Schwarz", zweiHums, false );
		EGitarre ibanez2 = new EGitarre("Ibanez", "RG321", drop, 24, zweiHums, false );
		EGitarre gibson = new EGitarre("Gibson", "Les Paul", normal, 22, "Rot", zweiHums, false );
		
		System.out.println("ibanez.toString() =\n" + ibanez.toString() + "\n");
		System.out.println("ibanez2.toString() =\n" + ibanez2.toString() + "\n");
		System.out.println("ibanez2.setFarbe('Weiss')");
		ibanez2.setFarbe("Weiss");
		System.out.println("ibanez2.toString() =\n" + ibanez2.toString() + "\n");
		System.out.println("ibanez.equals(ibanez2) = " + ibanez.equals(ibanez2) + "\n");
		
		System.out.println("gibson.toString() =\n" + gibson.toString() + "\n");
		System.out.println("ibanez.equals(gibson) = " + ibanez.equals(gibson + "\n"));
		
		System.out.println("gibson.anzahlSpielbareNoten() = " + gibson.anzahlSpielbareNoten());
	}

}