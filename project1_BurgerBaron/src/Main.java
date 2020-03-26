import java.io.*;
//Ibrahim Alshubaily
//This is the original main, I added a few test methods and slightly modified lines[82 - 86]
public class Main {

	public static void main(String[] args) throws IOException {
		FileReader inputStream = null;
		
		try {
			
				inputStream = new FileReader(args[0]);
				BufferedReader bufferedStream = new BufferedReader(inputStream);
				String line;
				int count = 0;
				while ((line = bufferedStream.readLine()) != null) {
					System.out.print("Processing Order "+ count++ + ": ");
					System.out.println(line); // useful for debugging
					parseLine(line);
					System.out.println();
				}
					bufferedStream.close();
		} finally {}
	
		//testParse();	
//		testBurger();
//		testMyStack();
	}
	
	public static void testParse() {
		parseLine("Burger");
		parseLine("Baron Burger");
		parseLine("Double Chicken Burger with Cheese Ketchup");
		parseLine("Triple Veggie Baron Burger with no Veggies Mustard but Onions");
	}
	
	public static void parseLine(String line) {
		String[] words = line.split(" ");
		Burger burger;
		
//		for(int i = 0; i < words.length; i++) {
//			System.out.println(" | "+ words[i]+"|");
//		}
		
		boolean baronBurger = words[0].equals("Baron") || words[Math.min(1, words.length-1)].equals("Baron") || words[Math.min(2, words.length-1)].equals("Baron");
		burger = new Burger(baronBurger);
//		System.out.println(baronBurger);
		if(words[0].equals("Double"))
			burger.addPatty();
		if(words[0].equals("Triple")){
			burger.addPatty();
			burger.addPatty();
		}
		
		boolean chicken = words[0].equals("Chicken")|words[Math.min(1, words.length-1)].equals("Chicken");
		if(chicken) burger.changePatties("Chicken");
		boolean veggie = words[0].equals("Veggie")||words[Math.min(1, words.length-1)].equals("Veggie");
		if(veggie) burger.changePatties("Veggie");
		
		int with = 0;
		int but = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals("with")) with = i;
			if(words[i].equals("but")) but = i;
		}
		
		if(with > 0) {
			int end = words.length;
			if(but > 0) end = but;
			for(int i = with; i < end; i++) {
//				System.out.println(words[i]);
				boolean cat = isCategory(words[i]);
				if(cat){
					if (baronBurger) 
						burger.removeCategory(words[i]); 
						
					else 
						burger.addCategory(words[i]);
				} else {
					if (baronBurger) 
						burger.removeIngredient(words[i]); 
					else {
						if (i < words.length-1){
							//I did this slight modification to pass the wanted word.
							burger.addIngredient(words[i+1]);
						}else 
							burger.addIngredient(words[i]);
					}
				}
			}
		}
		if(but > 0) {
			int end = words.length;
			for(int i = but; i < end; i++) {
				if (baronBurger) 
					burger.addIngredient(words[i]); 
				else 
					burger.removeIngredient(words[i]);
			}
		}

		System.out.println(burger.toString());
	}
	
	public static boolean isCategory(String str) {
		return str.equals("Cheese")||str.equals("Veggies")||str.equals("Sauce");
	}
	
	/** Makes sure the burger handles boundary and strange cases properly.*/
	public static void testBurger(){
		Burger burger = new Burger(false);
		for (int i = 0; i < 5; i++) {
			burger.addPatty();
			burger.addCategory("Veggies");
			burger.addIngredient("Mozzarella");
		}
		System.out.println(burger.toString());	
		Burger burger2 = new Burger(false);
		for (int i = 0; i < 5; i++) {
			burger2.removePatty();
			burger.removeCategory("Cheese");
			burger.removeCategory("Strange Category");
		}
		System.out.println(burger2.toString());		
		
	}
	
	/** Makes sure the burger handles boundary and strange cases properly.*/
	public static void testMyStack(){
		MyStack stack = new MyStack();
		for (int i = 0; i < 5; i++) {
			stack.push(""+i);
		}
		System.out.println("Full stack "+stack);	
		System.out.println("Peek "+stack.peek());
		for (int i = 0; i < 5; i++) {
			stack.pop();
		}
		System.out.println("Full stack "+stack);	
		System.out.println("Peek "+stack.peek());	
		
	}
}
