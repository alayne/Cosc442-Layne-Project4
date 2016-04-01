package edu.towson.cis.cosc442.project4.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		r2 = new Recipe();
		r2.setName("Negative Coffee");
		r2.setPrice(-1);
		r2.setAmtCoffee(-1);
		r2.setAmtMilk(-1);
		r2.setAmtSugar(-1);
		r2.setAmtChocolate(-1);
		
		r3 = new Recipe();
		r3.setName("Bad Coffee");
		r3.setPrice(10);
		r3.setAmtCoffee(6);
		r3.setAmtMilk(1);
		r3.setAmtSugar(2);
		r3.setAmtChocolate(1);
		
		r4 = new Recipe();
		r4.setName("Super Coffee");
		r4.setPrice(10);
		r4.setAmtCoffee(15);
		r4.setAmtMilk(15);
		r4.setAmtSugar(15);
		r4.setAmtChocolate(15);
	}
	
	
	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}
	//Testing adding negative values for a recipe. 
	public void testCreateRecipe1() {
		
		
		int sum = r2.getAmtChocolate() + r2.getAmtCoffee() + r2.getAmtMilk() + r2.getAmtSugar() + r2.getPrice();
		assertEquals(0, sum);
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	// Adding good inventory
	public void testAddInventory1() { 
		boolean added;
		added = cm.addInventory(1, 3, 0, 1);
		assertTrue(added);
		
	
	}
	// Test adding bad inventory
	public void testAddInventory2() { 
		boolean added;
		i.setChocolate(-1);
		i.setCoffee(-1);
		i.setMilk(-1);
		i.setSugar(-1);
		int sum = i.getChocolate() + i.getCoffee() + i.getMilk() + i.getSugar();
		
		assertEquals(0,sum);
	}
	//Testing the toString method of inventory
	public void  testAddInventory3() {
		String output;
		output = i.toString();
		System.out.print(output);
		
		assertEquals("Coffee: " + i.getCoffee() + System.getProperty("line.separator") +
				"Milk: " + i.getMilk() + System.getProperty("line.separator") +
				"Sugar: " + i.getSugar() + System.getProperty("line.separator") +
				"Chocolate: " + i.getChocolate() + System.getProperty("line.separator"), output);
		
	}
	//Tests using the CoffeeMakers addInventory method to add bad inventory
	public void testAddInventory4() { 
		boolean added;
		added = cm.addInventory(0, 0, 0, 0);
		
		assertTrue(added);
	}
	
	// Making sure the inventory numbers are updated after adding inventory
	public void testCheckInventory() {
		
		int sum = 0;
		
		cm.addInventory(1, 3, 1, 1);
		i = cm.checkInventory();
		sum = i.getChocolate() + i.getCoffee() + i.getMilk() + i.getSugar();
		
		assertEquals(66, sum);
		
	}
	// test purchasing a beverage and getting the correct change back
	public void testPurchaseBeverage1() {
		cm.addRecipe(r1);
		
		int change  = cm.makeCoffee(r1, 50);
		
		assertEquals(0, change);
		
	}
	//Test to make sure inventory is updated after purchasing beverage
	public void testPurchaseBeverage2() {
		
		cm.addRecipe(r3);
		cm.makeCoffee(r3, 50);
		i = cm.checkInventory();
		int inventory = i.getChocolate() + i.getCoffee() + i.getMilk() + i.getSugar();
		assertEquals(50, inventory);
		
	}
	// Tests purchasing a beverage is the exact amount of inventory needed is there
	public void testPurchaseBeverage3() {
		
		cm.addRecipe(r4);
		int moneyBack = cm.makeCoffee(r4, 10);
		assertEquals(0, moneyBack);
		
	}
	//Tests ability to return the correct recipe given the name of the recipe
	public void testGetRecipeName() {
		
		cm.addRecipe(r1);
		Recipe testRecipe = cm.getRecipeForName("Coffee");
		
		assertEquals(r1, testRecipe);
	}
	
	
	
}