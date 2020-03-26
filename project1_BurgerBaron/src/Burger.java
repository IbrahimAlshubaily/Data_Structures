import java.util.HashMap;
//Ibrahim Alshubaily
/**
 * The Burger class take in orders then store, sort and display them.
 * 
 * @author Ibrahim Alshubaily
 */
public class Burger {

	/**A stack of list nodes that stores the burger ingredients*/
	private MyStack content = new MyStack();
	
	/**Indicates the wanted type of patty for this burger.*/
	private String pattyType = "Beef";
	
	/**Indicates the number of patties this burger contains.*/
	private int numberOfPatties = 1;
	
	
	/**
	 * Constructs a baron burger if theWorks is true, and a plain burger otherwise.
	 * @param theWorks indicates wither the burger is a baron burger(True) or a plain burger(false)
	 */
	Burger (boolean theWorks) {

		if (theWorks) {
			HashMap <Integer, String> burgerIngrediants =  getIngrediants();
			for (int i =  burgerIngrediants.size()-1; i >= 0; i--) {
				content.push(burgerIngrediants.get(i));
			}	
		} else {
			content.push("TBun");
			content.push("Beef");
			content.push("BBun");
		}		
	}
	
	/**
	 * Changes all instance of patty to the specified type.
	 * @param pattyType the type of the wanted patty
	 */
	public void changePatties(String thePattyType) {
		pattyType = thePattyType;
		MyStack.MyListNode<Object> runner = content.getTop();
		while (runner.myNext != null) {
			if (runner.myElement.equals("Beef")
					||runner.myElement.equals("Chicken")
					||runner.myElement.equals("Veggie") ) runner.myElement = thePattyType;
			
			runner = runner.myNext;
		}
	}
	
	/**
	 * Adds a patty to the burger, up to three patties.
	 */
	public void addPatty() {
//		System.err.println("adding -- : "+pattyType);
		content.addPatty(pattyType);
	}
	
	/**
	 * Removes a patty from the burger, minimum is one patty.
	 */
	public void removePatty() {
		if (numberOfPatties > 1){
			MyStack.MyListNode<Object> runner = content.getTop();
			while (runner.myNext != null) {
				if (runner.myNext.myElement.equals(pattyType)) {
					runner.myNext = runner.myNext.myNext;
					numberOfPatties --;
					break;
				}
				runner = runner.myNext;
			}
		}
	}
	
	/**
	 * Adds all ingredients of the specified category to this burger.
	 * @param theCategory the Category to add
	 */
	public void addCategory(final String theCategory) { 
		MyStack.MyListNode<Object> runner = getType(theCategory).getTop();
		while (runner.myNext != null) {
			if (!content.contains(runner.myElement)) content.push(runner.myElement);
			runner =  runner.myNext;
		}
		//to cover the last element.
		if (!content.contains(runner.myElement))content.push(runner.myElement);
	}

	/**
	 * Removes all ingredients of the specified category to this burger.
	 * @param theCategory the Category to remove
	 */
	public void removeCategory(final String theCategory) { 
		
		MyStack.MyListNode<Object> runner = getType(theCategory).getTop();
		if (runner!= null) {
			if ( content.contains(runner.myElement)) 
				removeIngredient(runner.myElement);
			
			while (runner.myNext != null) {
				if (content.contains(runner.myElement)) {
					removeIngredient(runner.myElement);				
				}
				runner = runner.myNext;
			}
			//To make sure we cover the last element.
			if (content.contains(runner.myElement)) {
				removeIngredient(runner.myElement);				
			}
		}
	}
	
	/**
	 * Adds the specified ingredient to this burger.
	 * @param theIngredient the ingredient to add
	 */
	public void addIngredient (final String theIngredient) {
		if (getIngrediants().containsValue(theIngredient)){
			if (!content.contains(theIngredient) 
					|| theIngredient.equalsIgnoreCase("beef")
					|| theIngredient.equalsIgnoreCase("chicken")
					|| theIngredient.equalsIgnoreCase("Veggie")  ) {
				content.push(theIngredient);
			}
		}
	}
	
	
	/**
	 * Removes the specified ingredient from this burger.
	 * @param theIngredient the ingredient to remove
	 */
	public void removeIngredient (final String theIngredient) {
		if (content.peek().equals(theIngredient)) {
			content.pop();
		} else {
			int p =1;
			MyStack.MyListNode<Object> runner = content.getTop();
			while(runner.myNext!= null) {
				if (runner.myNext.myElement.equalsIgnoreCase(theIngredient)) {
					content.removeAt(p); 
				}
				runner = runner.myNext;
				p++;
			}
			//Covers the last element
			if (runner.myElement.equalsIgnoreCase(theIngredient)) {
				content.removeAt(p);
			}
		}	
	}
	
	/**Builds a hash-map that indicates the proper order of each ingredient.*/
	HashMap<String, Integer> getPosition() {
		HashMap <String, Integer> order = new HashMap<>();
		order.put("Pickle", 0);
		order.put("TBun", 1);
		order.put("Mayonnaise", 2);
		order.put("Baron-Sauce", 3);
		order.put("Lettuce", 4);
		order.put("Tomato", 5);
		order.put("Onions", 6);
		order.put("Pepperjack", 7);
		order.put("Mozzarella", 8);
		order.put("Cheddar", 9);
		order.put("Veggie", 10);
		order.put("Chicken", 10);
		order.put("Beef", 10);
		order.put("Mushrooms", 11);
		order.put("Mustard", 12);
		order.put("Ketchup", 13);
		order.put("BBun", 14);
		return order;
	}

	/**Builds a hash-map that consist of the baron burger ingredients.*/
	HashMap <Integer, String> getIngrediants() {
		HashMap <Integer, String> order = new HashMap<>();
		order.put(0, "Pickle");
		order.put(1, "TBun");
		order.put(2, "Mayonnaise");
		order.put(3, "Baron-Sauce");
		order.put(4, "Lettuce");
		order.put(5, "Tomato");
		order.put(6, "Onions");
		order.put(7, "Pepperjack");
		order.put(8,"Mozzarella");
		order.put(9, "Cheddar");
		order.put(10, "Veggie");		
		order.put(10, "Chicken");
		order.put(10, "Beef");
		order.put(11, "Mushrooms");
		order.put(12, "Mustard");
		order.put(13, "Ketchup");
		order.put(14, "BBun");
		return order;	
	}
	
	/**
	 * Returns the burger content stack
	 * @return  the burger content stack
	 */
	public MyStack getContent() {
		return content;
	}

	/**
	 * Returns the number of patties in this burger.
	 * @return the number of patties in this burger
	 */
	public int getNumberOfPatties() {
		return numberOfPatties;
	}
	/**Sets the number of patties.
	 * @param theNumberOfPatties the Number Of Patties
	 */
	public void setNumberOfPatties(int theNumberOfPatties) {
		numberOfPatties = theNumberOfPatties;
	}
	/**
	 * Returns a Stack of the all ingredients of the specified category.
	 * 
	 * @param type the category
	 * @return a list of the specified category
	 */
	MyStack getType(String theCategory){	
		if (theCategory.equalsIgnoreCase("Cheese")) {
			return getCheese();
		} else if (theCategory.equalsIgnoreCase("Sauce")) {
			return getSauce();
		} else if (theCategory.equalsIgnoreCase("Veggies")) {
			return getVeggies();
		} else {
			return new MyStack();
		}
	}
	
	/**
	 * Returns a stack of all types of cheese.
	 * @return a stack of all types of cheese
	 */
	MyStack getCheese() {
		MyStack cheese = new MyStack();
		cheese.push("Cheddar");
		cheese.push("Mozzarella");
		cheese.push("Pepperjack");
		return cheese;
	}
	
	/**
	 * Returns a stack of all types of sauce.
	 * @return a stack of all types of sauce
	 */
	MyStack getSauce() {
		MyStack sauce = new MyStack();
		sauce.push("Ketchup");
		sauce.push("Mustard");
		sauce.push("Mayonnaise");
		sauce.push("Baron-Sauce");
		return sauce;
	}
	
	/**
	 * Returns a stack of all types of veggies.
	 * @return a stack of all types of veggies
	 */
	MyStack getVeggies() {
		MyStack veggies = new MyStack();
		veggies.push("Lettuce");
		veggies.push("Tomato");
		veggies.push("Onions");
		veggies.push("Pickle");
		veggies.push("Mushrooms");
		return veggies;
	}	

	/**
	   * An Insertion sort that places the burger ingrediendt in order.
	   * @return a sorted stack of this burger ingredients.
	   */
		//I used the code from the book as a guide.
		public MyStack insertionSort() {
	    	String [] a = new String[content.size()];
	    	HashMap<String, Integer> positions = getPosition();
	    	for (int i = 0; i < content.size(); i++ ) {
	    		a[i] = content.getElementAt(i);
	    	}
	    	content = new MyStack();
	        for( int p = 1; p < a.length; p++ ) {
	            String tmp = a[ p ];
	            int j = p;
				for( ; j > 0 && positions.get(tmp) 
	            		<= positions.get(a[ j - 1 ]); j-- )
					a[ j ] = a[ j - 1 ];
	                
	            a[ j ] = tmp;
	        }
	        new MyStack().setContent(a, this, pattyType);
	        return content;
	    }
		
		/**
		 * Returns a sorted string representation of this burger.
		 * @Returns a sorted string representation of this burger
		 */
		public String toString () {
			return insertionSort().toString();
		}
	}