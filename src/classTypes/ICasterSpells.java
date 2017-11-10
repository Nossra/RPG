package classTypes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Battle.ITargeting;
import models.Enemy;
import models.Player;

public interface ICasterSpells extends ITargeting {
	// MANACOSTS
	static int FB_MANA_COST = 20;
	static int CL_MANA_COST = 30;
	static int PO_MANA_COST = 12;
	static int HW_MANA_COST = 15;

	// MULTIPLIERS
	double FB_DAMAGE_MULTIPLIER = 2.5;
	double CL_DAMAGE_MULTIPLIER = 1.75;
	double HW_MULTIPLIER = 3.0;

	String[] printCaster = {
			"Fire Ball, " + FB_MANA_COST + " MP\n   Hurl a ball of fire at the target for "
					+ (int) (FB_DAMAGE_MULTIPLIER * 100) + "% damage. Sets the target on fire.\n",
			"Chain Lightning, " + CL_MANA_COST + " MP\n   AoE attack that hits a main target for "
					+ (int) (CL_DAMAGE_MULTIPLIER * 100)
					+ "% damage, then bounces up to two times for lesser damage each time.\n",
			"Polymorph, " + PO_MANA_COST
					+ " MP\n   Transforms one opponent into a sheep, making that target unable to do anything for two rounds.\n",
			"Healing Wind, " + HW_MANA_COST + " MP\n   Heal a target for " + (int) (HW_MULTIPLIER * 100)
					+ "% of the caster's damage.\n" };
	Scanner sc = new Scanner(System.in);
	Random rnd = new Random();

	// BURNING DOT EFFECT? OR HIGHER CRIT DAMAGE?
	default void fireBall(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws InterruptedException {
		offensiveTarget(player, enemyTeam);
		if (player.getInput() == 5) { //5 because it goes -1 in top (the actual input is 6.
			useAbility(player, enemyTeam, playerTeam);
		} else {
			player.setMana(player.getMana() - FB_MANA_COST);
			int miss = rnd.nextInt(100) + 1;
			if (miss <= player.getMissChance()) { 
				missChance();
			} else {			
				int crit = rnd.nextInt(100) + 1;
				if (crit < player.getCriticalChance()) critChance(player);
				enemyTeam.get(player.getInput()).setHealth(
						(int) (enemyTeam.get(player.getInput()).getHealth() - (player.getDamage() * FB_DAMAGE_MULTIPLIER)));
				System.out.println(player.getName() + "'s Fire Ball dealt " + (player.getDamage() * FB_DAMAGE_MULTIPLIER)
						+ " to " + enemyTeam.get(player.getInput()).getName() + ", HP: "
						+ enemyTeam.get(player.getInput()).getHealth() + "/"
						+ enemyTeam.get(player.getInput()).getBaseHealth() + "\n");
			}
		}
	}

	default void chainLightning(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam)
			throws InterruptedException {
		double dmgReduction = CL_DAMAGE_MULTIPLIER;
		offensiveTarget(player, enemyTeam);
		if (player.getInput() == 5) { // 5 because it goes -1 in top (the actual input is 6.
			useAbility(player, enemyTeam, playerTeam);
		} else {
			player.setMana(player.getMana() - CL_MANA_COST);
			for (int i = 0; i < enemyTeam.size();) {
				int miss = rnd.nextInt(100) + 1;
				if (miss <= player.getMissChance()) {
					missChance();
				} else {
					int crit = rnd.nextInt(100) + 1;
					if (crit < player.getCriticalChance())
						critChance(player);
					if ((player.getInput() + i) >= enemyTeam.size()) {
						player.setInput(0);
						i = 0;
					}
					enemyTeam.get(player.getInput() + i)
							.setHealth((int) (enemyTeam.get(player.getInput() + i).getHealth()
									- (player.getDamage() * dmgReduction)));
					System.out.print(
							player.getName() + "'s Chain Lightning dealt " + player.getDamage() * dmgReduction
									+ " to " + enemyTeam.get(player.getInput() + i).getName()
									+ ", HP: " + enemyTeam.get(player.getInput() + i).getHealth() + "/"
									+ enemyTeam.get(player.getInput() + i).getBaseHealth() + "\n");
					System.out.println();
					player.setDamage(player.getBaseDamage());
					i++;
				}
				dmgReduction -= 0.25;
				if (dmgReduction == 1) {
					break;
				}
			}
		}
	}

	default void polymorph(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws InterruptedException {
		System.out.println("maaaah");
	}

	default void healingWind(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws InterruptedException {
		
		System.out.println("Who do you want to heal?");
		defensiveTarget(player, playerTeam);
		if (player.getInput() == 5) { //5 because it goes -1 in top (the actual input is 6.
			useAbility(player, enemyTeam, playerTeam);
		} else {
			player.setMana(player.getMana() - HW_MANA_COST);
			for (int i = 0; i < playerTeam.size(); i++) {
				if (player.getInput() == i) {
					playerTeam.get(i).setHealth((int) (player.getDamage() * HW_MULTIPLIER));
					if (playerTeam.get(i).getHealth() > playerTeam.get(i).getBaseHealth()) {
						playerTeam.get(i).setHealth(playerTeam.get(i).getBaseHealth());
					}
					System.out.println(player.getName() + " healed " + playerTeam.get(i).getName() + " for "
							+ (int)(player.getDamage() * HW_MULTIPLIER) + " health.\n");
				}
			} 
		}
	}

	default void printCasterAbilities(Player player) {
		System.out.println("CHOOSE ABILITY");
		for (int i = 0; i < printCaster.length; i++) {
			System.out.println((i + 1) + ". " + printCaster[i]);
		}
		System.out.println("6. Go back.");
		player.setInput(Integer.parseInt(sc.nextLine()));
	}
}
