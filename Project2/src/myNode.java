//Ibrahim Alshubaily
class myNode implements Comparable<myNode>{
	private final char Char;
	private final int Frequency;
	private final myNode Left;
	private final myNode Right;
	myNode(){
		this('\0', 0, null, null);
	}
	myNode(char theChar, int theFrequency, myNode theLeftChild, myNode theRightChild) {
		Char = theChar;
		Frequency = theFrequency;
		Left = theLeftChild;
		Right = theRightChild;
	}
	@Override
	public int compareTo(myNode theOther) {
		return this.Frequency - theOther.Frequency;
	}
	boolean isChar() {
		return this.Left==null && this.Right==null;
	}
	int getFrequency(){
		return this.Frequency;
	}	
	char getChar() {
		return this.Char;
	}
	myNode getLeft () {
		return Left;
	}
	myNode getRight () {
		return Right;
	}
}
