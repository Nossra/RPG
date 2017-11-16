package classTypes;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Battle.ITargeting;
import models.Enemy;
import models.Player;

public interface IArcherSpells extends ITargeting {
	// MANACOSTS
	static int AS_MANA_COST = 15;
	static int TS_MANA_COST = 10;
	
	// MULTIPLIERS
	double AS_DAMAGE_MULTIPLIER = 2.5;
	double TS_DAMAGE_MULTIPLIER = 0.75;
	
	String[] printArcher = {
			"Aimed Shot, " + AS_MANA_COST + " MP\n   Aimed shot: Deals " + (int)(AS_DAMAGE_MULTIPLIER*100) + "% damage but takes one round to aim.\n",
			"Trick Shot, " + TS_MANA_COST + " MP\n   Trick Shot: Ricochet the arrow to hit all targets for " + (int)(TS_DAMAGE_MULTIPLIER*100) + "% damage.\n" };
	Random rnd = new Random();

	default void aimedShot(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {
		offensiveTarget(player, enemyTeam);
		if (player.getInput() == 5) { // 5 because it goes -1 in top (the actual input is 6.
			useAbility(player, enemyTeam, playerTeam);
		} else {
			((Archer) player).setCharging(true);
			player.setTargeting(enemyTeam.get(player.getInput()).getId());
			System.out.println(player.getName() + " targets " + enemyTeam.get(player.getInput()).getName() + " for a heavy attack!");
		}
	}
	
	default void fireAS(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws InterruptedException {
		while (((Archer) player).isCharging() == true) {
			int miss = rnd.nextInt(100) + 1;
			if (miss <= player.getMissChance()) {
				missChance();
			} else {
				int crit = rnd.nextInt(100) + 1;
				if (crit < player.getCriticalChance()) critChance(player);
				for (int i = 0; i < enemyTeam.size(); i++) {
					if (enemyTeam.get(i).getId() == player.getTargeting()) {
						enemyTeam.get(i).setHealth((int) (-player.getDamage() * AS_DAMAGE_MULTIPLIER));
						System.out.println(
								player.getName() + "'s Aimed Shot dealt " + player.getDamage() * AS_DAMAGE_MULTIPLIER
										+ " to " + enemyTeam.get(i).getName() + ", HP: "
										+ enemyTeam.get(i).getHealth() + "/" + 
										enemyTeam.get(i).getBaseHealth());
						System.out.println();
						player.setDamage(player.getBaseDamage());
					}
				}			
			}
			((Archer) player).setCharging(false);
		}
	}

	default void trickShot(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
			throws Exception {
		
		offensiveTarget(player, enemyTeam);
		if (player.getInput() == 5) { // 5 because it goes -1 in top (the actual input is 6.
			useAbility(player, enemyTeam, playerTeam);
		} else {
			player.setMana(player.getMana() - TS_MANA_COST);
			for (int i = 0; i < enemyTeam.size(); i++) {
				int miss = rnd.nextInt(100) + 1;
				if (miss <= player.getMissChance()) {
					missChance();
				} else {
					int crit = rnd.nextInt(100) + 1;
					if (crit < player.getCriticalChance())
						critChance(player);
					enemyTeam.get(i).setHealth(
							(int) (enemyTeam.get(i).getHealth() - (player.getDamage() * TS_DAMAGE_MULTIPLIER)));
					System.out.println(
							player.getName() + "'s Trick Shot dealt " + player.getDamage() * TS_DAMAGE_MULTIPLIER
									+ " to " + enemyTeam.get(i).getName() + ", HP: "
									+ enemyTeam.get(i).getHealth() + "/" + enemyTeam.get(i).getBaseHealth());
					System.out.println();
					player.setDamage(player.getBaseDamage());
				}
			}
		}
	}
	
	default void printArcherAbilities(Player player) throws InterruptedException {
		System.out.println("CHOOSE ABILITY");
		for (int i = 0; i < printArcher.length; i++) {
			System.out.println((i + 1) + ". " + printArcher[i]);
		}
		System.out.println("6. Go back.");
		player.setInput(0);
	}
	
	default void notEnoughMana(Player player) throws InterruptedException {
		System.out.println("Not enough mana!");
		TimeUnit.SECONDS.sleep(1);
		printArcherAbilities(player);
	}
}
