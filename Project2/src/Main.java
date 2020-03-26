//Ibrahim Alshubaily
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		//String content1 = new String(Files.readAllBytes(Paths.get("Anna.txt"))); 
		//CodingTree ct = new CodingTree(content1);
		long start = System.currentTimeMillis();
		String content2 = new String(Files.readAllBytes(Paths.get("WarAndPeace.txt"))); 
		CodingTree ct = new CodingTree(content2);
		writeCodesFile(ct.getCodes().toString());
		writeCompressedFile(content2, ct.getCodes());
		long end  = System.currentTimeMillis();
		produceOutPut(start, end);
	}
	/*
	Expected output: 
	Uncompressed file size: 3291623 bytes 
	Compressed file size: 1875127 bytes 
	Compression ratio: 56% 
	Running Time: 3543 milliseconds 
	*/
	private static void produceOutPut(long start, long end) {
		File before = new File("./WarAndPeace.txt");
		File after = new File("./compressed.txt");
		double bef = before.length();
		double aft = after.length();
		System.out.println("Uncompressed file size: "+ (int)bef+" bytes");
		System.out.println("Compressed file size: "+ (int)aft+" bytes");
		int ratio =(int) (100*(aft/bef));
		System.out.println("Compression ratio: "+ ratio+ "%");
		System.out.println("Running Time: "+ (end-start) +" milliseconds");
	}
	
	private static void writeCompressedFile(String theContent, HashMap<Character, String> codes) throws FileNotFoundException {
		BinaryStdOut bin = new BinaryStdOut();
		for (int i = 0; i < theContent.length(); i++){
			for (int j = 0; j < codes.get(theContent.charAt(i)).length(); j++ ){
				if (codes.get(theContent.charAt(i)).charAt(j)==('0')) bin.write(false);
				else if (codes.get(theContent.charAt(i)).charAt(j)==('1')) bin.write(true);
			}  
        }
	}

	private static void writeCodesFile(String theCodes) {
		try(  PrintWriter out = new PrintWriter( "./codes.txt" )  ){
		    out.print( theCodes);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
}