package models;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public abstract class Player extends Unit implements IPlayerModels {
	
	private int input;
	private int exp;
	private int expCap;
	private int level = 1;
	
	public Player() {
		playableCharacter = true;
		this.setExp(0);
		this.setExpCap(75);
	}
	
	static public void printStats(ArrayList<Player> player) {
		System.out.printf("%45s%n", "PLAYER TEAM STATUS\n");
		String headerFormat = "%-9S %-7s  %-7s  %-8s  %-9s %-8s %-7s  %-7s  %-7s%n";
		String valueFormat = "%-9s %-7s  %-8d %-8s  %-8s  %-7s  %-7d  %-7s  %-7s%n";
		System.out.printf(headerFormat, "NAME", "CLASS", "LEVEL", "XP", "HP", "MP", "DAMAGE", "CRIT", "MISS");
		for (int i = 0; i < player.size(); i++) {
			String xp = player.get(i).getExp() + "/" + player.get(i).getExpCap();
			String hp = player.get(i).getHealth() + "/" + player.get(i).getBaseHealth();
			String mp = player.get(i).getMana() + "/" + player.get(i).getBaseMana();
			String crit = player.get(i).getCriticalChance() + "%";
			String miss = player.get(i).getMissChance() + "%";
			System.out.printf(valueFormat, 
					player.get(i).getName(), player.get(i).getClass().getSimpleName(), player.get(i).getLevel(), xp, 
					hp, mp, player.get(i).getDamage(), crit , miss);
		}
		System.out.println();
	}
	
	public void gainExperience(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam, int exp) throws InterruptedException {
		System.out.println("Your partymembers gained " + exp + " experience!");
		for (int i = 0; i < playerTeam.size(); i++) {
			playerTeam.get(i).setExp(exp);
			System.out.println(playerTeam.get(i).getName() + " XP: " +playerTeam.get(i).getExp() + "/" + playerTeam.get(i).getExpCap());
		}		
		System.out.println();
		if (player.getExp() >= player.getExpCap()) {
			levelUp(playerTeam);
		}
	}
		
	public void levelUp(ArrayList<Player> playerTeam) throws InterruptedException {
		System.out.println(" -- LEVEL UP! --\n");
		TimeUnit.SECONDS.sleep(2);	
		double statMultiplier = 1.2;
		for (int i = 0; i < playerTeam.size(); i++) {	
		//STAT CHANCES
		playerTeam.get(i).incLevel();
		playerTeam.get(i).setDamage((int) (playerTeam.get(i).getDamage() * statMultiplier));
		playerTeam.get(i).setBaseDamage((int) (playerTeam.get(i).getBaseDamage() * statMultiplier));
		playerTeam.get(i).setHealth((int) (playerTeam.get(i).getHealth() * statMultiplier));
		playerTeam.get(i).setBaseHealth((int) (playerTeam.get(i).getBaseHealth() * statMultiplier));
		playerTeam.get(i).setMana((int) (playerTeam.get(i).getMana() * statMultiplier));
		playerTeam.get(i).setBaseMana((int) (playerTeam.get(i).getBaseMana() * statMultiplier));
		//END OF STAT CHANGES
		
		playerTeam.get(i).setExpCap((int) (playerTeam.get(i).getExpCap() + (playerTeam.get(i).getExpCap() * 2.3)));
		}	
		printStats(playerTeam);
		TimeUnit.SECONDS.sleep(2);
		System.out.println();
	}
		
	public int getInput() {
		return input;		
	}
	public void setInput(int input) {
		this.input = input;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = (this.getExp() + exp);
	}
	public int getExpCap() {
		return expCap;
	}
	public void setExpCap(int expCap) {
		this.expCap = expCap;
	}
	public int getLevel() {
		return level;
	}
	public void incLevel() {
		this.level = this.level +1;
	}
}

