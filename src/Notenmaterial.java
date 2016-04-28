import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


//Jede Klasse, die dieses Interface implementiert, hat automatisch die wichtigsten Noten zur Verfuegung.
//Das erleichtert den Kompositionsprozess etwas, weil die Noten nicht mehr instanziiert werden muessen
interface Notenmaterial{
	
	//enthaelt die zulaessigen Namen fuer die Noten als Strings
	public String[] zulnamen = new String[]{"c", "cis", "d", "dis", "e", "f", 
	"fis", "g", "gis", "a", "b", "h"};
	
	//Zuordnung fuer enharmonische Verwechslung
	public Map<String, String> namensmap = 
	Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("his", "c");
			put("des", "cis");
			put("es", "dis");
			put("fes", "e");
			put("eis", "f");
			put("ges", "fis");
			put("as", "gis");
			put("ais", "b");
			put("ces", "h");
		}
	});
	
	//die wichtigsten Noten
	public Note a0 = new Note("a", 0);
	public Note ais0 = new Note("ais", 0);
	public Note b0 = new Note("b", 0);
	public Note h0 = new Note("h", 0);
	public Note his0 = new Note("his", 0);
	public Note ces1 = new Note("ces", 1);
	public Note c1 = new Note("c", 1);
	public Note cis1 = new Note("cis", 1);
	public Note des1 = new Note("des", 1);
	public Note d1 = new Note("d", 1);
	public Note dis1 = new Note("dis", 1);
	public Note es1 = new Note("es", 1);
	public Note e1 = new Note("e", 1);
	public Note eis1 = new Note("eis", 1);
	public Note fes1 = new Note("fes", 1);
	public Note f1 = new Note("f", 1);
	public Note fis1 = new Note("fis", 1);
	public Note ges1 = new Note("ges", 1);
	public Note g1 = new Note("g", 1);
	public Note gis1 = new Note("gis", 1);
	public Note as1 = new Note("as", 1);
	public Note a1 = new Note("a", 1);
	public Note ais1 = new Note("ais", 1);
	public Note b1 = new Note("b", 1);
	public Note h1 = new Note("h", 1);
	public Note his1 = new Note("his", 1);
	public Note ces2 = new Note("ces", 2);
	public Note c2 = new Note("c", 2);
	public Note cis2 = new Note("cis", 2);
	public Note des2 = new Note("des", 2);
	public Note d2 = new Note("d", 2);
	public Note dis2 = new Note("dis", 2);
	public Note es2 = new Note("es", 2);
	public Note e2 = new Note("e", 2);
	public Note eis2 = new Note("eis", 2);
	public Note fes2 = new Note("fes", 2);
	public Note f2 = new Note("f", 2);
	public Note fis2 = new Note("fis", 2);
	public Note ges2 = new Note("ges", 2);
	public Note g2 = new Note("g", 2);
	public Note gis2 = new Note("gis", 2);
	public Note as2 = new Note("as", 2);
	public Note a2 = new Note("a", 2);
	public Note ais2 = new Note("ais", 2);
	public Note b2 = new Note("b", 2);
	public Note h2 = new Note("h", 2);
	public Note his2 = new Note("his", 2);
	public Note ces3 = new Note("ces", 3);
	public Note c3 = new Note("c", 3);
	public Note cis3 = new Note("cis", 3);
	public Note des3 = new Note("des", 3);
	public Note d3 = new Note("d", 3);
	public Note dis3 = new Note("dis", 3);
	public Note es3 = new Note("es", 3);
	public Note e3 = new Note("e", 3);
	public Note eis3 = new Note("eis", 3);
	public Note fes3 = new Note("fes", 3);
	public Note f3 = new Note("f", 3);
	public Note fis3 = new Note("fis", 3);
	public Note ges3 = new Note("ges", 3);
	public Note g3 = new Note("g", 3);
	public Note gis3 = new Note("gis", 3);
	public Note as3 = new Note("as", 3);
	public Note a3 = new Note("a", 3);
	public Note ais3 = new Note("ais", 3);
	public Note b3 = new Note("b", 3);
	public Note h3 = new Note("h", 3);
	public Note his3 = new Note("his", 3);
	public Note ces4 = new Note("ces", 4);
	public Note c4 = new Note("c", 4);
	public Note cis4 = new Note("cis", 4);
	public Note des4 = new Note("des", 4);
	public Note d4 = new Note("d", 4);
	public Note dis4 = new Note("dis", 4);
	public Note es4 = new Note("es", 4);
	public Note e4 = new Note("e", 4);
	public Note eis4 = new Note("eis", 4);
	public Note fes4 = new Note("fes", 4);
	public Note f4 = new Note("f", 4);
	public Note fis4 = new Note("fis", 4);
	public Note ges4 = new Note("ges", 4);
	public Note g4 = new Note("g", 4);
	public Note gis4 = new Note("gis", 4);
	public Note as4 = new Note("as", 4);
	public Note a4 = new Note("a", 4);
	public Note ais4 = new Note("ais", 4);
	public Note b4 = new Note("b", 4);
	public Note h4 = new Note("h", 4);
	public Note his4 = new Note("his", 4);
	public Note ces5 = new Note("ces", 5);
	public Note c5 = new Note("c", 5);
	public Note cis5 = new Note("cis", 5);
	public Note des5 = new Note("des", 5);
	public Note d5 = new Note("d", 5);
	public Note dis5 = new Note("dis", 5);
	public Note es5 = new Note("es", 5);
	public Note e5 = new Note("e", 5);
	public Note eis5 = new Note("eis", 5);
	public Note fes5 = new Note("fes", 5);
	public Note f5 = new Note("f", 5);
	public Note fis5 = new Note("fis", 5);
	public Note ges5 = new Note("ges", 5);
	public Note g5 = new Note("g", 5);
	public Note gis5 = new Note("gis", 5);
	public Note as5 = new Note("as", 5);
	public Note a5 = new Note("a", 5);
	public Note ais5 = new Note("ais", 5);
	public Note b5 = new Note("b", 5);
	public Note h5 = new Note("h", 5);
	public Note his5 = new Note("his", 5);
	public Note ces6 = new Note("ces", 6);
	public Note c6 = new Note("c", 6);
	public Note cis6 = new Note("cis", 6);
	public Note des6 = new Note("des", 6);
	public Note d6 = new Note("d", 6);
	public Note dis6 = new Note("dis", 6);
	public Note es6 = new Note("es", 6);
	public Note e6 = new Note("e", 6);
	public Note eis6 = new Note("eis", 6);
	public Note fes6 = new Note("fes", 6);
	public Note f6 = new Note("f", 6);
	public Note fis6 = new Note("fis", 6);
	public Note ges6 = new Note("ges", 6);
	public Note g6 = new Note("g", 6);
	public Note gis6 = new Note("gis", 6);
	public Note as6 = new Note("as", 6);
	public Note a6 = new Note("a", 6);
	public Note ais6 = new Note("ais", 6);
	public Note b6 = new Note("b", 6);
	public Note h6 = new Note("h", 6);
	public Note his6 = new Note("his", 6);
	public Note ces7 = new Note("ces", 7);
	public Note c7 = new Note("c", 7);
	public Note cis7 = new Note("cis", 7);
	public Note des7 = new Note("des", 7);
	public Note d7 = new Note("d", 7);
	public Note dis7 = new Note("dis", 7);
	public Note es7 = new Note("es", 7);
	public Note e7 = new Note("e", 7);
	public Note eis7 = new Note("eis", 7);
	public Note fes7 = new Note("fes", 7);
	public Note f7 = new Note("f", 7);
	public Note fis7 = new Note("fis", 7);
	public Note ges7 = new Note("ges", 7);
	public Note g7 = new Note("g", 7);
	public Note gis7 = new Note("gis", 7);
	public Note as7 = new Note("as", 7);
	public Note a7 = new Note("a", 7);
	public Note ais7 = new Note("ais", 7);
	public Note b7 = new Note("b", 7);
	public Note h7 = new Note("h", 7);
	public Note his7 = new Note("his", 7);
	public Note ces8 = new Note("ces", 8);
	public Note c8 = new Note("c", 8);
	
}