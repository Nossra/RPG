package Battle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Enemy;
import models.IPlayerModels;
import models.Player;

public interface ITargeting extends IPlayerModels, IPlayerOptions {
	Scanner sc = new Scanner(System.in);
	Random rnd = new Random();
	
	default void offensiveTarget(Player player, ArrayList<Enemy> enemyTeam) throws NumberFormatException, InterruptedException {
		printTargets(player, enemyTeam);
		player.setInput(-1);
	}
	
	default void defensiveTarget(Player player, ArrayList<Player> playerTeam) throws NumberFormatException, InterruptedException {
		for (int i = 0; i < playerTeam.size(); i++) {
			System.out.println((i + 1) + ". " + playerTeam.get(i).getName() + ", HP: "
					+ playerTeam.get(i).getHealth() + "/" + (int)playerTeam.get(i).getBaseHealth());
		}
		System.out.println("6. Go back");
		player.setInput(-1);

	}
}
