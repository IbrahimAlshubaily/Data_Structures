import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
//Ibrahim Alshubaily
public class HuffmanStepsHandler {


	final  void buildSingleNodes(HashMap <Character, Integer> TheCharsAndFrequences, myNode [] theSingleNodes) {
		int i = 0;
		for (Entry<Character, Integer> e : TheCharsAndFrequences.entrySet()){
			theSingleNodes [i] =  new myNode(e.getKey(), e.getValue(), null, null);
	        i++;
		}
	}
	 final  myNode mergeIntoOneTree(myNode[] theSingleNodes) {
			MyPriorityQueue myPQ = new MyPriorityQueue();
			for (myNode n : theSingleNodes) {
				myPQ.add(n);
			}
			while(myPQ.getSize() != 1) {
				myNode L = myPQ.pop();
				myNode R = myPQ.pop();
				myPQ.add(new myNode('\0', L.getFrequency()+R.getFrequency() , L, R));
			}
			return myPQ.pop();
		}

		 final  void getPaths(myNode theNode, String[] theCharsPaths, String thePath) {
				if (theNode.isChar()) {
					theCharsPaths [theNode.getChar()] = thePath;
		        } else {
		        	getPaths(theNode.getRight(), theCharsPaths, thePath+'1');
		        	getPaths(theNode.getLeft(), theCharsPaths, thePath+'0');
		        }
		}
		
		 final  void setupCodes(String theContent, String [] thePathsOfChars, HashMap<Character, String> theCodes) {
			for (char c : theContent.toCharArray()) {
				if(!theCodes.containsKey(c)){
					theCodes.put(c, thePathsOfChars[c]);
				}		
			}
		}

		 final String setUpBits(String theContent, HashMap<Character, String> codes) {
				StringBuilder sb = new StringBuilder();
				for (char c : theContent.toCharArray()) {
						sb.append(codes.get(c));
				}
				return sb.toString();
		}
		 final  void decode(String theContent, String theBits, String[] thePathsOfChars) throws FileNotFoundException {
			HashMap <String, Character> letters = new HashMap<>();
			for (char c : theContent.toCharArray()) {
				if(!letters.containsKey(c))
					letters.put(thePathsOfChars[c], c);
			}
			StringBuilder words = new StringBuilder();
			StringBuilder path = new StringBuilder();
			for (char c : theBits.toCharArray()) {
				path.append(c);
				if(letters.containsKey(path.toString())){
					words.append(letters. get(path.toString()));
					path.delete(0, path.length());
				}	
			}
			try(  PrintWriter out = new PrintWriter( "./WAP-Decoded.txt" )  ){
				    out.print( words.toString() );
			}
			
			
		}

	
}
