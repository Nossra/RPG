package Battle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import classTypes.Archer;
import classTypes.Caster;
import classTypes.Fighter;
import classTypes.IArcherSpells;
import classTypes.ICasterSpells;
import classTypes.IFighterSpells;
import models.IPlayerModels;
import models.Enemy;
import models.IEnemyModels;
import models.Player;

public interface IPlayerOptions extends IPlayerModels, IEnemyModels {
	Scanner sc = new Scanner(System.in);
	Random rnd = new Random();
	

	default void classOption(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {
		if (player instanceof Archer) {
			Archer archer = (Archer) player;
			if (archer.isCharging() == true) {
				archer.fireAS(player, enemyTeam, playerTeam);
			} else {
				chooseOption(player, enemyTeam, playerTeam);
			}
		} else if (player instanceof Caster) {
			chooseOption(player, enemyTeam, playerTeam);
		} else if (player instanceof Fighter) {
			chooseOption(player, enemyTeam, playerTeam);
		}
	}
	
	default void chooseOption(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {
		System.out.println("What do you want to do?");
		for (int i = 0; i < options.length; i++) {
			System.out.println((i+1) + ". " + options[i]);
		}
		player.setInput(0);	
		if (player.getInput() >= 5 && player.getInput() <= 6) {
			System.out.println("Only choose between the given numbers! Try again!\n");
			chooseOption(player, enemyTeam, playerTeam);
		} else {
			if (player.getInput() == 1) {
				attackEnemy(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 2) {
				useAbility(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 3) {
				defend(player, enemyTeam);
			} else if (player.getInput() == 4) {
				analyze(player, enemyTeam, playerTeam);
			}
		}		
	}
	
	default void attackEnemy(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws  Exception {
		/* Method for attacking the enemy
		 * Also includes calculation for crit or miss chance.
		 */
		printTargets(player, enemyTeam);
		player.setInput(-1);
		if (player.getInput() == 5) {
			chooseOption(player, enemyTeam, playerTeam);
		} else {
			int miss = rnd.nextInt(100) + 1;
			if (miss <= player.getMissChance()) {
			missChance();
			} else {
				int crit = rnd.nextInt(100) + 1;
				if (crit <= player.getCriticalChance()) critChance(player);
				enemyTeam.get(player.getInput()).setHealth(enemyTeam.get(player.getInput()).getHealth() - player.getDamage());
				System.out.println(
						player.getName() + " attacked " +  enemyTeam.get(player.getInput()).getName() + 
						" for " + (int)player.getDamage() + " damage. HP: " +
						enemyTeam.get(player.getInput()).getHealth() + "/" + enemyTeam.get(player.getInput()).getBaseHealth() + "\n");
				player.setDamage(player.getBaseDamage());
			}
		}
	}	
	
	default void useAbility(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {		
		if (player instanceof Archer) {
			Archer a = (Archer) player;
			a.printArcherAbilities(a);
			if (player.getInput() == 1) {
				if (player.getMana() < IArcherSpells.AS_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else a.aimedShot(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 2) {
				if (player.getMana() < IArcherSpells.TS_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else a.trickShot(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 6) {
				chooseOption(player, enemyTeam, playerTeam);
			}
		} else if (player instanceof Caster) {
			Caster c = (Caster) player;
			c.printCasterAbilities(c);
			if (player.getInput() == 1) {
				if (player.getMana() < ICasterSpells.FB_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else c.fireBall(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 2) {
				if (player.getMana() < ICasterSpells.CL_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else c.chainLightning(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 3) {
				if (player.getMana() < ICasterSpells.PO_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else c.polymorph(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 4) {
				if (player.getMana() < ICasterSpells.HW_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else c.healingWind(player, enemyTeam, playerTeam);
			} else if (player.getInput() == 6) {
				chooseOption(player, enemyTeam, playerTeam);
			}
		} else if (player instanceof Fighter) {
			Fighter f = (Fighter) player;
			f.printFighterAbilities(f);
			if (player.getInput() == 1) {
				if (player.getMana() < IFighterSpells.BD_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else f.buffDamage(playerTeam);
			} else if (player.getInput() == 2) {
				if (player.getMana() < IFighterSpells.S_MANA_COST) notEnoughMana(player, enemyTeam, playerTeam); else f.stun();
			} else if (player.getInput() == 6) {
				chooseOption(player, enemyTeam, playerTeam);
			} 
		}
	}
	
	default void defend(Player currentPlayer, ArrayList<Enemy> enemyTeam) {
		System.out.println("You take a defensive stance. Your incoming damage taken is reduced depending on your armor.\n");
	}	
	
	default void analyze(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {
		//Prints out the targets statistics.
		//Also reverts back to chooseOption so it doesn't consume the round
		printTargets(player, enemyTeam);
		player.setInput(0);
		if (player.getInput() == 6) {
			chooseOption(player, enemyTeam, playerTeam);
		} else {
			enemyTeam.get(player.getInput()-1).printStats();
			TimeUnit.SECONDS.sleep(2);
			chooseOption(player, enemyTeam, playerTeam);
		}
	}
	
	default void notEnoughMana(Player player, ArrayList<Enemy> enemyTeam, ArrayList<Player> playerTeam) throws Exception {
		System.out.println(" - NOT ENOUGH MANA - \n");
		TimeUnit.SECONDS.sleep(1);
		 useAbility(player, enemyTeam, playerTeam);
	}
}
