package models;

public class Enemy extends Unit {
	
	public Enemy() {
		this.setCriticalChance(25);
		this.setMissChance(10);
	}
	
	private int experienceDrop;
	
	public void printStats() {
		System.out.println(
				"Name: " + this.getName() + "\n" +
				"HP: " + this.getHealth() + "/" + this.getBaseHealth() + "\n" +
				"Damage: " + this.getBaseDamage() + "\n" +
				"Critical chance: " + this.getCriticalChance() + "%\n" + 
				"Miss chance: " + this.getMissChance() + "%\n"
				);
	}
	
	public int getExperienceDrop() {
		return experienceDrop;
	}

	public void setExperienceDrop(int experienceDrop) {
		this.experienceDrop = experienceDrop;
	}
	
}
