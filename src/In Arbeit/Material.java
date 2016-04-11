import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

interface Material{

	public Map<String, Integer> zahlenmap = 
	Collections.unmodifiableMap(new HashMap<String, Integer>() {
		{
			put("c4", 60);
			put("cis4", 61);
			put("d4", 62);
			put("dis4", 63);
			put("e4", 64);
			put("f4", 65);
			put("fis4", 66);
			put("g4", 67);
			put("gis4", 68);
		}
	});

}