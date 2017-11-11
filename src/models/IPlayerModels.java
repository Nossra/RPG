package models;

import java.util.ArrayList;
import java.util.Scanner;

public interface IPlayerModels {
	String[] options = { "Attack", "Use Ability", "Defend", "Analyze" };
	Scanner sc = new Scanner(System.in);
	
	default void printTargets(Player currentPlayer, ArrayList<Enemy> enemy) {
		System.out.println("\nChoose which target to " + options[currentPlayer.getInput()-1]);
		String enemyHeader = "%-2s %-15s %-7s%n";
		String enemyValues = "%-2s %-15s %-7s%n";
		System.out.printf(enemyHeader, "Nr", "NAME", "HP");
		for (int i = 0; i < enemy.size(); i++) {
			String hp = enemy.get(i).getHealth() + "/" + enemy.get(i).getBaseHealth();
			System.out.printf(enemyValues, (i+1), enemy.get(i).getName(), hp);
		}
		System.out.println();
		System.out.println("6. Go back");
	}
	
	default void missChance() {
		System.out.println(" - MISS! -\n");
	}
	
	default void critChance(Player currentPlayer) {
		System.out.print(" - CRITICAL HIT! - ");
		currentPlayer.setDamage(currentPlayer.getBaseDamage()*2);
	}
	
	
}
