package classTypes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Battle.ITargeting;
import models.IPlayerModels;
import models.Enemy;
import models.IEnemyModels;
import models.Player;
import models.Unit;

public interface IFighterSpells extends ITargeting {
	String[] printFighter = { "Buff Damage", "Stun" };
	
	//MANACOSTS
	int BD_MANA_COST = 15;
	int S_MANA_COST = 20;
	
	
	
	//CURRENTLY DOESNT RESET DAMAGE AFTER TWO ROUNDS
	default void buffDamage(ArrayList<Player> playerTeam) {
		System.out.println("Doubles party members damage for two rounds.");
		for (Player players : playerTeam) {
			players.setDamage((int) (players.getBaseDamage() * 1.25));
		}		
	}
	
	default void stun(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {
		offensiveTarget(player, enemyTeam);
		if (player.getInput() == 5) { 
			useAbility(player, enemyTeam, playerTeam);
		} else {
			player.setMana(player.getMana() - S_MANA_COST);
			int miss = rnd.nextInt(100) + 1;
			if (miss <= player.getMissChance()) {
				missChance();
			} else {
				enemyTeam.get(player.getInput()).setStunned(true);
				enemyTeam.get(player.getInput()).setControlled(2);
				System.out.println(enemyTeam.get(player.getInput()).getName() + " got stunned for two rounds!\n");
			}
		}
	}
	
	default void printFighterAbilities(Player player) {
		System.out.println("CHOOSE ABILITY");
		for (int i = 0; i < printFighter.length; i++) {
			System.out.println((i+1) + ". " + printFighter[i]);
		}
		System.out.println("6. Go back.");
		player.setInput(0);
	}
	
	default void notEnoughMana(Player player) throws InterruptedException {
		System.out.println("Not enough mana!");
		TimeUnit.SECONDS.sleep(1);
		printFighterAbilities(player);
	}
}
