package models;

import java.util.ArrayList;
import java.util.Scanner;

public interface IPlayerModels {
	String[] options = { "Attack", "Use Ability", "Defend", "Analyze" };
	Scanner sc = new Scanner(System.in);
	
	default void printTargets(Player currentPlayer, ArrayList<Enemy> enemyTeam) {
		System.out.println("\nChoose which target to " + options[currentPlayer.getInput()-1]);
		for (int i = 0; i < enemyTeam.size(); i++) {
			System.out.println(
					(i+1) + ". " + enemyTeam.get(i).getName() + ", HP: " + 
					(int)enemyTeam.get(i).getHealth() + "/" + (int)enemyTeam.get(i).getBaseHealth()
					);
		}
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
