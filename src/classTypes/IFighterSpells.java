package classTypes;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import models.IPlayerModels;
import models.IEnemyModels;
import models.Player;

public interface IFighterSpells extends IEnemyModels, IPlayerModels {
	String[] printFighter = { "Buff Damage", "Stun" };
	
	//MANACOSTS
	static int BD_MANA_COST = 15;
	static int S_MANA_COST = 20;

	//CURRENTLY DOESNT RESET DAMAGE AFTER TWO ROUNDS
	default void buffDamage(ArrayList<Player> playerTeam) {
		System.out.println("Doubles party members damage for two rounds.");
		for (Player players : playerTeam) {
			players.setDamage((int) (players.getBaseDamage() * 1.25));
		}		
	}
	
	default void stun() {
		System.out.println("Stun your opponent for 1 round");
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
