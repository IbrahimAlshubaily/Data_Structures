
public class MyPriorityQueue {
	
	myNode [] theContainer;
	int totalCapacity = 10;
	int size = 0;
	public MyPriorityQueue() {
		theContainer = new myNode [totalCapacity];
	}
	
	void add(myNode theNode) {
		
		if (size == totalCapacity-2) {
			myNode [] tmp = theContainer;
			totalCapacity*=2;
			theContainer = new myNode[totalCapacity];
			for (int i = 0 ; i < tmp.length; i++) {
				theContainer [i] = tmp [i];
			}
		}
		theContainer[this.size] = theNode;
		if (size > 1) {			
			for (int i = this.size-1; i > 0; i --){
				while ( theContainer[i].getFrequency() > (theContainer[i+1].getFrequency()) ) {
					myNode tmp = theContainer[i];
					theContainer[i] = theContainer[i+1];
					theContainer[i+1] = tmp;
				}
			}
		}
		size ++;
	}
	myNode pop () {
		if (size <= 0) return null;
		myNode result = theContainer[0];
		theContainer[0] = theContainer[this.size-1];
		for (int i = 0; i < this.size-1; i++) {
			while (theContainer[i].getFrequency() > theContainer[i+1].getFrequency()) {
				myNode tmp = theContainer[i];
				theContainer[i] = theContainer[i+1];
				theContainer[i+1] = tmp;
			}
		}
		size--;
		return result;
	}
	int getSize(){
		return size;
	}
}
