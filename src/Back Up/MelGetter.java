import javax.sound.midi.*;
import java.io.*;

public class MelGetter implements Material{
	
	public static Melodie getMel( int i ){
		Melodie m = null;
		try{
			FileInputStream fileIn = new FileInputStream("/Users/leoborn/Desktop/Noten/src/Dateien/RandomMel"+i+".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			m = (Melodie) in.readObject();
			in.close();
			fileIn.close();
		}
		catch(IOException io){
			io.printStackTrace();
		}
		catch(ClassNotFoundException c){
			System.out.println("Melodie class not found.");
			c.printStackTrace();
		}
		return m;
	}
	
	public static void main( String[] args ) throws MidiUnavailableException, 
	InvalidMidiDataException, IOException {
		if( args.length == 1 && Integer.parseInt( args[0] ) > 0 ){
			Melodie m = getMel(Integer.parseInt( args[0] ));
			m.playMelodie();
		}
		else{
			System.err.println("Bitte geben Sie den Index der Melodie als Argument ein.");
		}
	}

}