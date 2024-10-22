package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import buyers.Customer;
import foods.Burger;
import player.User;

public class Main {

	ArrayList<Customer> buyers = new ArrayList<>();
	ArrayList<User> player = new ArrayList<>();
	ArrayList<Burger> menu = new ArrayList<Burger>();
	
	Scanner sc = new Scanner(System.in);
	
	int score = 0;
	
	public void how_to_play() {
		util.cls();
		System.out.println("HOW TO PLAY");
		System.out.println("Rave Kitchen");
		System.out.println("=".repeat(110));
		System.out.println("Welcome, Grand Chef! This is a special mission for YOU yes YOU, manage my burger joint, complete the orders");
		System.out.println("accurately and quickly. you'll switch between output and input modes.");
		System.out.println("=".repeat(110));
		System.out.println("1. Output Mode (View orders and inventory)");
		System.out.println("  - View active orders, inventory, and current score");
		System.out.println("  - Monitor customer names, burger requests, timers, and potential rewawrds");
		System.out.println("  - Watch out for VIP orders - higher rewards!");
		System.out.println("  - Keep an eye on your inventory");
		System.out.println("=".repeat(110));
		System.out.println("2. Input mode (Process orders)");
		System.out.println("  - Assemble burgers based on customer requests");
		System.out.println("  - Follow recipes exactly (e.g.,  'Cheeseburger' = Bun + Patty + Cheese");
		System.out.println("  - Corrent assembly = Points and rewards");
		System.out.println("=".repeat(110));
		System.out.println("3. Scoring and Gameplay Tips");
		System.out.println("  - Base reward for each completed order");
		System.out.println("  - Bonus rewards for VIP orders");
		System.out.println("  - Avoid expired orders");
		System.out.println("  - Manage your time and inventory wisely");
		System.out.println("=".repeat(110));
		System.out.println("4. Exiting the Game");
		System.out.println("  - Type 'exit' during Output Mode to leave");
		System.out.println("  - Return to main menu or switch modes anytime");
		System.out.println("=".repeat(110));
		System.out.println("Good luck, Chef!");
		System.out.println();
		System.out.println();
		System.out.println("press enter to continue");		
		sc.nextLine();
	}
	
	public void readScore() {
		String file = "score.txt";
		Scanner scanner;
		try {
			scanner = new Scanner(new File(file));
			String score = scanner.nextLine();
			System.out.println("score : " + score);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readIngredients() {
		String file = "inventory.txt";
		try {
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split("#");
				if(split.length == 2) {
					String ingredient = split[0];
					int total = Integer.parseInt(split[1]);
					System.out.printf("| %15s | %10s |\n", ingredient, total);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private volatile boolean enterPressed = false;
	
	public void eater() {
		Random rand = new Random();
		ArrayList<String> randomname = new ArrayList<String>();
		randomname.add("Alice");
		randomname.add("Bob");
		randomname.add("Charlie");
		randomname.add("Diana");
		randomname.add("Eve");
		randomname.add("Frank");
		randomname.add("grace");
		randomname.add("hank");
		int ind = rand.nextInt(8);
		int pick = rand.nextInt(4);
		String name_random = randomname.get(ind);
		Burger order = menu.get(pick); 
		
		//No#CustomerName#RecipeName#TotalReward#Time 
		// ["Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hank"].
		
		String filePath = "restaurant.txt"; // Path to your input file
        String tempFilePath = "temp_orders.txt"; // Temporary file to hold updated orders

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] orderDetails = line.split("#");
                String orderNumber = orderDetails[0];
                String customerName = orderDetails[1];
                String burgerType = orderDetails[2];
                String rewardPoints = orderDetails[3];
                int timeToPrepare = Integer.parseInt(orderDetails[4]); // Parse time as an integer

                timeToPrepare--;
                
                String updatedOrder = String.join("#", orderNumber, customerName, burgerType, rewardPoints, String.valueOf(timeToPrepare));
                bw.write(updatedOrder);
                bw.newLine();

                System.out.println("Order Number: " + orderNumber);
                System.out.println("Customer: " + customerName);
                System.out.println("Burger: " + burgerType);
                System.out.println("Reward Points: " + rewardPoints);
                System.out.println("Updated Time to Prepare: " + timeToPrepare);
                System.out.println("====================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }

        try {
            new java.io.File(filePath).delete();
            new java.io.File(tempFilePath).renameTo(new java.io.File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	public void game_loop() {
		int time = 0;
		// i dont understand how the code below thats similar to _kbhit works like the -> i dont understand 
		Thread inputThread = new Thread(() -> {
		    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
		        System.out.println("Press Enter to return to menu...");
		        String input = br.readLine();
		        if (input != null && input.isEmpty()) {
		            enterPressed = true;
		            System.out.println("Enter pressed, exiting loop...");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		});

        inputThread.start();
		while(!enterPressed) {
			try {
				Thread.sleep(1000);
				time++;
				util.cls();
				System.out.println("#1 raving Restaurant");
				System.out.println("=".repeat(46));
				eater();
//				System.out.printf("| %3s | %15s | %10s | %5s |\n", "No.", "Order","Reward","Time");
//				System.out.printf("| %3s | %15s | %10s | %5s |\n","1", "burger", "10000","3");
//				System.out.printf("| %3s | %15s | %10s | %5s |\n","1", "burger", "10000","3");
//				System.out.printf("| %3s | %15s | %10s | %5s |\n","1", "burger", "10000","3");
				System.out.println("=".repeat(46));
				System.out.println("");
				System.out.println("");
				System.out.println("====== Current Inventory =======");
				readIngredients();
//				System.out.printf("| %15s | %10s |\n", "Ingredient", "quantity");
				System.out.println("================================");
				System.out.printf("%d", time);
				System.out.println("");
				System.out.printf("Current Score: $%d\n", score);
				readScore();
				System.out.println("");
				System.out.println("Select an order to process or press enter to return to menu");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Game loop ended, returning to menu...");
		main_menu();
	}
	//BaseReward = 5 + (Number Of Ingredients × 2)
	//Reward = BaseReward + Customer’s Additional Reward
	public void input_mode() {
		while(true) {
			util.cls();
			readIngredients();
			System.out.println("Enter command (e.g., 'process 1' to process order 1, 'restock' to restock inventory, 'exit' to quit)");
			System.out.print(">> ");
			String choice = sc.nextLine();
			if(choice.equals("process 1")) {
				// this is not the finished code this is how i would problaby do it
//				private boolean bun;
//				private boolean bacon;
//				private boolean lettuce;
//				private boolean tomato;
//				private boolean onion;
//				private boolean pickle;
//				private boolean cheese;
//				private boolean patty;
//				private boolean bun2;
				util.cls();
				System.out.print("Type the ingredients (bun, bacon, lettuce, tomato, onion, pickle, cheese, patty, bun2) [input : bun patty bun2]: ");
				String make = sc.nextLine();
				String[] ingredients = make.split(" ");
				Burger temp = new Burger("Cheeseburger", false, false, false, false, false, false, false, false, false);
		        for (String i : ingredients) {
		            if(i.equals("bun")) {
		            	temp.setBun(true);
		            } else if(i.equals("bacon")) {
		            	temp.setBacon(true);
		            } else if(i.equals("lettuce")) {
		            	temp.setLettuce(true);
		            } else if(i.equals("tomato")) {
		            	temp.setTomato(true);
		            } else if(i.equals("onion")) {
		            	temp.setOnion(true);
		            } else if(i.equals("pickle")) {
		            	temp.setPickle(true);
		            } else if(i.equals("cheese")) {
		            	temp.setCheese(true);
		            } else if(i.equals("patty")) {
		            	temp.setPatty(true);
		            } else if(i.equals("bun2")) {
		            	temp.setBun2(true);
		            }
		        }
		        if(temp.equals(menu.get(0))) {
		        	System.out.println("correct");
//		        	int base = 5 + numberofingredients
//		        	int total = base + customer
		        }
		        
			} else if(choice.equals("Restock")) {
				if(score < 30) {
					System.out.println("Need more money!");
				} else {
					
				}
			} else if(choice.equals("exit"))break;
		}
	}
	
	public void output_mode() {
		util.cls();
		String name;
		while(true) {
			System.out.print("Please enter your name (Cannot be empty) : ");
			name = sc.nextLine();
			if(name.isEmpty())System.out.println("Name cannot be empty!");
			if(name.length() > 0)break;
		}
		game_loop();
	}
	
	public void main_menu(){
		while(true) {
			util.cls();
			System.out.println("Rave Kitchen");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Main Menu:");
			System.out.println("1. Output Mode (View orders and inventory)");
			System.out.println("2. Input Mode (Process orders)");
			System.out.println("3. How to Play");
			System.out.println("4. exit");
			System.out.print(">> ");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1)output_mode();
			if(choice == 2)input_mode();
			if(choice == 3)how_to_play();
			if(choice == 4)break;
		}
	}
	
//	private boolean bun;
//	private boolean bacon;
//	private boolean lettuce;
//	private boolean tomato;
//	private boolean onion;
//	private boolean pickle;
//	private boolean cheese;
//	private boolean patty;
//	private boolean bun2;
	
	public Main() {
		Burger Cheeseburger = new Burger("Cheeseburger", 	true, false, false, false, false, false, false, true, true);
		Burger Baconburger = new Burger("Baconburger", true, true, true, true, false, false, false, true, true);
		Burger Veggieburger = new Burger("Veggieburger", true, false, true, true, true, true, false, false, true);
		Burger Deluxeburger = new Burger("Deluxeburger", true, true, true, true, true, true, true, true, true);
		User temp = new User("Renko", 0);
		player.add(temp);
		menu.add(Cheeseburger);
		menu.add(Baconburger);
		menu.add(Veggieburger);
		menu.add(Deluxeburger);
		main_menu();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
