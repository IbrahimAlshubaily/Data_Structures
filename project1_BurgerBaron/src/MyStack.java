//Mark Allen Weiss Linked list code example guided me through this.
//Ibrahim Alshubaily

public class MyStack {

	/**The top of the stack*/
	private MyListNode <Object> myTop; 
	
	/**The size of the stack*/
	private int mySize = 0;
	
	/**The number of patties in this stack.*/
	private int numberOfPatties = 1;
	
	/**Initializes an empty stack*/
	MyStack () {
		myTop = null;
	}
	
	/**Reports if the stack is empty or not.*/
	boolean isEmpty () {
		return myTop == null;		
	}
	
	/**Adds list node to the top of the stack.*/
	void push (String theObject) {
		myTop = new MyListNode <Object>(theObject, myTop);
		mySize++;
	}
	
	public MyListNode<Object> getTop() {
		return myTop;
	}
	
	/**Reports if the stack contains a certain ingredient or not
	 * 
	 * @param theIngrediant the ingredient
	 * @return if the ingredient is in the stack or not.
	 */
	boolean contains(String theIngrediant){

		if (myTop== null) return false;
		MyListNode<Object> runner = myTop;
		if (myTop.myElement.equalsIgnoreCase(theIngrediant)) return true;
		
		while (runner.myNext!=null){
			if (runner.myElement.equalsIgnoreCase(theIngrediant)) return true;
			
			runner = runner.myNext;
		}
		return false;
	}
	/**
	 * 
	 * @param thePosition
	 * @param theIngrediant
	 */
	void insertAt (int thePosition, String theIngrediant) {
		if (thePosition == 0) {
			push(theIngrediant);
		} else {
			int i = 0;
			MyListNode<Object> runner = myTop;
			//System.err.println(runner.myElement);
			while (i < thePosition-1 && runner.myNext!=null) {
				runner = runner.myNext;
				i++;
			}		
			runner.myNext = new MyListNode <Object>(theIngrediant, runner.myNext);
			mySize++;
		}
	}
	
	void removeAt (int thePosition) {
		if (thePosition == 0) {
			pop();
		} else {
			int i = 0;
			MyListNode<Object> runner = myTop;
			while (i < thePosition-1 && runner.myNext!=null) {
				runner = runner.myNext;
				i++;
			}		
			runner.myNext = runner.myNext.myNext;
		}
		mySize--;
	}
	
	String getElementAt (int thePosition) {
		MyListNode<Object> runner = myTop;
		int i = 0;
		while (runner.myNext != null
				&& i  < thePosition ) {
			runner = runner.myNext;
			i++;
		}
		return runner.myElement;
	}
	
	void setElementAt (int thePosition, String theElement) {
		MyListNode<Object> runner = myTop;
		int i = 0;
		while (runner.myNext != null && i  < thePosition ) {
			runner = runner.myNext;
			i++;
		}
		runner.myElement = theElement ;		
	}
	
	Object pop() {
		Object returnValue = myTop.myElement;
		myTop = myTop.myNext;
		mySize--;
		return returnValue;
	}
	
	Object peek(){
		if (isEmpty()) return "The Stack is Empty";
		return myTop.myElement;
	}
	
	int size(){
		return mySize;
	}

	public String toString() {
		
		if (isEmpty()) {
			return "The Stack is empty";
		}
		
		StringBuilder returnValue = new StringBuilder();
		MyListNode<Object> runner = myTop;
		returnValue.append('['+ myTop.myElement);
		while (runner.myNext != null) {
			runner = runner.myNext;
			returnValue.append(", "+ runner.myElement);
		}
		returnValue.append(']');
		return returnValue.toString();		
	}
	
	 
	//
	//Reference: ListNode<AnyType> code example.
	/**
	 * Inner class represents a List node
	 * @author Ibrahim Alshubaily
	 * Guided by the book code example
	 * @param <anyType>
	 */
	class MyListNode <anyType>  {
		
		String myElement;
		MyListNode <Object> myNext;
		
		MyListNode (String theElement) {
			this (theElement, null);
		}
		MyListNode(String theElement, MyListNode<Object> next) {
			myElement = theElement;
			myNext = next;
		}
	}


	public void setContent(String[] sortedResult, Burger theBurger, String patty) {
		int pattiesToAdd = 0;
		for (int i = sortedResult.length-1; i >= 0; i--) {
			
			if ( (sortedResult[i].equalsIgnoreCase("Beef")
					||sortedResult[i].equalsIgnoreCase("Chicken")
					||sortedResult[i].equalsIgnoreCase("Veggie")
					) && (theBurger.getContent().contains("Chicken")
							||theBurger.getContent().contains("Beef")
							||theBurger.getContent().contains("Veggie")) ) {
				pattiesToAdd++;

			} else {
				
				if (sortedResult[i].equalsIgnoreCase("TBun")||sortedResult[i].equalsIgnoreCase("BBun"))sortedResult[i]="Bun";
				theBurger.getContent().push(sortedResult[i]);
			}
		}
		for (int i =0; i < pattiesToAdd; i++) {
			theBurger.getContent().addPatty(patty);
		}
		
	}
	void addPatty(String thePatty) {
		 MyStack.MyListNode<Object> runner = myTop;
		 int position = 1;
		 while (runner.myNext != null 				
				&&( !runner.myNext.myElement.equalsIgnoreCase("Mozzarella") 
				&& !runner.myNext.myElement.equalsIgnoreCase("Pepperjack")  	
			  	&& !runner.myNext.myElement.equalsIgnoreCase("Cheddar")
				&& !runner.myNext.myElement.equalsIgnoreCase("Chicken")
				&& !runner.myNext.myElement.equalsIgnoreCase("Beef")
				&& !runner.myNext.myElement.equalsIgnoreCase("Veggie")) ){
			position ++;
			runner = runner.myNext;
		 }
		 if (numberOfPatties < 3) {
			insertAt(position, thePatty);
			numberOfPatties ++;
		}
	}
}
