import java.util.HashMap;
//Ibrahim Alshubaily
public class FrequencyCounter {	
	final HashMap<Character, Integer> mapCharsFrequences(String theContent) {
		HashMap<Character, Integer> mapOfCharsAndFrequences = new HashMap<>();
		for (char c : theContent.toCharArray()) {
			if (mapOfCharsAndFrequences.containsKey(c)){
				mapOfCharsAndFrequences.put(c, mapOfCharsAndFrequences.get(c)+1);
			} else {
				mapOfCharsAndFrequences.put(c, 1);
			}
		}
		return mapOfCharsAndFrequences;
	}	
}
