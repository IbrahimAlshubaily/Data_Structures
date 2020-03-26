import java.io.IOException;
import java.util.HashMap;
//Ibrahim Alshubaily
public class CodingTree {
	
	private static final boolean DEBUG = true;
	HashMap<Character, String> codes = new HashMap<>();
	
	String bits = "";
	String msg;
	CodingTree(String theContent) throws IOException{		
		callHuffmanSteps(theContent, new FrequencyCounter().mapCharsFrequences(theContent));
 	}
	
	private void callHuffmanSteps(String theContent, HashMap<Character, Integer> CharsAndFrequences) throws IOException {
		
		HuffmanStepsHandler huffman = new HuffmanStepsHandler();
		
		myNode [] singleNodes= new myNode[CharsAndFrequences.size()];
		huffman.buildSingleNodes(CharsAndFrequences, singleNodes);
		
		myNode mergedTree = huffman.mergeIntoOneTree(singleNodes);
		
		String [] pathsOfChars = new String[256];
		huffman.getPaths(mergedTree, pathsOfChars, "");
			
		huffman.setupCodes(theContent, pathsOfChars, codes);
		bits = huffman.setUpBits(theContent, codes);
		if (DEBUG){
			huffman.decode(theContent, bits, pathsOfChars);
		}
		
	}


	public HashMap<Character, String> getCodes() {
		return codes;
	}
	public String getBits() {
		return bits;
	}
}
