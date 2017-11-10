package Battle;

import java.util.ArrayList;
import java.util.Random;

import models.Enemy;
import models.IEnemyModels;
import models.Player;

public interface IEnemyOptions extends IEnemyModels {
	Random rnd = new Random();

	default void attackPlayer(ArrayList<Player> players, Enemy currentEnemy) {
		int attackPlayer = rnd.nextInt(players.size()); // Random out who to hit, that AI though.
		int crit = rnd.nextInt(100) + 1;
		int miss = rnd.nextInt(100) + 1;
		if (crit <= currentEnemy.getCriticalChance()) {
			enemyCritChance(currentEnemy);
		}
		if (miss <= currentEnemy.getMissChance()) {
			enemyMissChance(currentEnemy);
		} else {
			players.get(attackPlayer).setHealth(players.get(attackPlayer).getHealth() - currentEnemy.getBaseDamage());
			System.out.println("The enemy " + currentEnemy.getClass().getSimpleName() + " attacked "
					+ players.get(attackPlayer).getName().toUpperCase() + " for " + (int)currentEnemy.getBaseDamage() + "\n"
					+ players.get(attackPlayer).getName().toUpperCase() + " now has "
					+ (int)players.get(attackPlayer).getHealth() + " health left.\n");
		}
		currentEnemy.setDamage(currentEnemy.getBaseDamage());
	}
}
