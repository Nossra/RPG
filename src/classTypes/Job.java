package classTypes;

import java.util.Scanner;

import models.Player;

public abstract class Job extends Player {
	
	static Scanner sc = new Scanner(System.in);
	
	//Method to print jobs to choose from. Will only be used once. Static is therefore not a problem (but needed for it to be usable in an abstract class)
	static public void printJobs() {
		String[] jobs = { "Fighter", "Caster", "Archer" };
		System.out.println("Choose your class.");
		for (int i = 0; i< jobs.length; i++) {
			System.out.println((i+1) + ". " + jobs[i]);
		}
		System.out.println();
	}
	
	//Create an object of the children to Job as a playable character. 
	//Also lets you set the name for your character with the setName method.
	//Will also only be used once. Static is therefore not a problem. (also necessary since the class is abstract)
	static public Job createCharacter() {
		int input = Integer.parseInt(sc.nextLine());
		System.out.println("Choose name:");
		String name = sc.nextLine();
		if (input == 1) {
			Fighter f = new Fighter(name);
			f.playerBoost();
		//	f.printStats();
			return f;
		} else if (input == 2) {
			Caster c = new Caster(name);
			c.playerBoost();
		//	c.printStats();
			return c;
		} else if (input == 3) {
			Archer a = new Archer(name);
			a.playerBoost();
		//	a.printStats();
			return a;
		}
		return null;
	}
	
	public void playerBoost() {
		int playerBoost = 20;
		int playerCritBoost = 10;
		int playerMissBoost = 5;
		
		this.setDamage(this.getDamage() + playerBoost);
		this.setBaseDamage(this.getBaseDamage() + playerBoost);
		this.setHealth(this.getHealth() + playerBoost);
		this.setBaseHealth(this.getBaseHealth() + playerBoost);
		this.setMana(this.getMana() + playerBoost);
		this.setBaseMana(this.getBaseMana() + playerBoost);
		this.setCriticalChance(this.getCriticalChance() + playerCritBoost);
		this.setMissChance(this.getMissChance() - playerMissBoost);
	}
	
	static public void characterSheet() {
		System.out.println("");
	}
}
