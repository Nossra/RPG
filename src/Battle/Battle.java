package Battle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import models.Enemy;
import models.Player;
import models.Unit;

public class Battle implements IPlayerOptions, IEnemyOptions {
	protected boolean fighting = true;
	protected ArrayList<Player> player;
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Unit> turnOrder;
	static Scanner sc = new Scanner(System.in);
	
	//Constructor for battle that takes the player team and the enemy team.
	//The enemy team is created in the Patrole class which is then used in the battle class.
	public Battle(ArrayList<Player> playerTeam, ArrayList<Enemy> enemyTeam) throws InterruptedException {
		this.player = playerTeam;
		this.enemies = enemyTeam;
		turnOrder = new ArrayList<Unit>();
		for (Player players : player) { turnOrder.add(players); }
		for (Unit enemy : enemies) { turnOrder.add(enemy); }
		System.out.println("! ! B A T T L E ! ! ");
		TimeUnit.SECONDS.sleep(1);
	}
	
	public void battle() throws InterruptedException {
		int exp = expPool(getEnemies());
		while (this.isFighting()) {
			for (int i = 0; i < turnOrder.size(); i++) {
				if (turnOrder.get(i).getPlayable() == true) {
					Player player = (Player) turnOrder.get(i);
					playerTurn(i);
					if (getEnemies().isEmpty()) {
						System.out.println("The player wins this fight!\n");
						TimeUnit.SECONDS.sleep(2);	
						player.gainExperience((Player) turnOrder.get(i), getEnemies(), getPlayers(), exp);
						this.setFighting(false);
						break;
					}
				} else if (turnOrder.get(i).getPlayable() == false) {
					enemyTurn(i);
					if (getPlayers().isEmpty()) {
						System.out.println("YOU DIED\n");
						TimeUnit.SECONDS.sleep(2);
						this.setFighting(false);
						break;
					}
				}
			}			
		}
	}
	
	//Amount of experience the players can earn from the battle.
	public int expPool(ArrayList<Enemy> enemyTeam) {
		int expPool = 0;
		for (int i = 0; i < enemyTeam.size(); i++) {
			expPool += enemyTeam.get(i).getExperienceDrop();
		}
		return expPool;
	}

	public void playerTurn(int i) throws InterruptedException {
		boolean dying = false;
		Player player = (Player) turnOrder.get(i);
		printStatus();
		System.out.println(player.getName().toUpperCase() + "'S TURN! (" + player.getClass().getSimpleName().toUpperCase() + ")");
		chooseOption(player, getEnemies(), getPlayers());
		TimeUnit.SECONDS.sleep(2);
		for (int j = 0; j < getEnemies().size(); j++) {
			if (getEnemies().get(j).getHealth() < 1) {
				int tempEnemy = getEnemies().get(j).getId();
				System.out.println(" -- " + getEnemies().get(j).getClass().getSimpleName().toUpperCase() + " HAS DIED. --\n");
				TimeUnit.SECONDS.sleep(1);
				getEnemies().remove(j);
				TimeUnit.SECONDS.sleep((long) 0.1);
				//Removes the unit from the turnorder by its ID to remove the correct one.
				for (int x = 0; x < turnOrder.size(); x++) {
					if (turnOrder.get(x).getId() == tempEnemy) {
						TimeUnit.SECONDS.sleep((long) 0.1);
						turnOrder.remove(x);  
					}	
				}
			} 
		}
	}
	
	public void enemyTurn(int i) throws InterruptedException {
		Enemy currentEnemy = (Enemy) turnOrder.get(i);
		System.out.println(currentEnemy.getClass().getSimpleName().toUpperCase() + "'S TURN!\n");
		TimeUnit.SECONDS.sleep(1);
		enemyAI(currentEnemy);
		TimeUnit.SECONDS.sleep(2);
		for (int j = 0; j < getPlayers().size(); j++) {
			if (getPlayers().get(j).getHealth() < 1) {
				int currentPlayer = getPlayers().get(j).getId();
				System.out.println(" -- " + getPlayers().get(j).getName().toUpperCase() + " HAS DIED. --\n");
				getPlayers().remove(j);
				//Removes the unit from the turnorder by its ID to remove the correct one.
				for (int x = 0; x < turnOrder.size(); x++) {
					if (turnOrder.get(x).getId() == currentPlayer) {
						turnOrder.remove(x);
					}	
				}
			}
		}
	}
			
	 public void enemyAI(Enemy currentEnemy) {
		 attackPlayer(getPlayers(), currentEnemy);
	 }
	 
	 public void printStatus() {
		 for (int i = 0; i < player.size(); i++) {
			 System.out.println(
					 player.get(i).getName().toUpperCase() + " | LVL: " + player.get(i).getLevel()+ " | HP: " + (int) player.get(i).getHealth() + "/" + (int) player.get(i).getBaseHealth() +
					 " | MP: " + player.get(i).getMana() + "/" + player.get(i).getBaseMana() + "   ");
		 }
		 System.out.println("\n VS \n");

		 for (int j = 0; j < enemies.size(); j++) {
			 System.out.println(
					 enemies.get(j).getName() + " | HP: " + 
							 (int) enemies.get(j).getHealth() + "/" + (int) enemies.get(j).getBaseHealth() + "    "
					 );
		 }
		 System.out.println("\n");
	 }
	
	public boolean isFighting() {
		return fighting;
	}

	public void setFighting(boolean fighting) {
		this.fighting = fighting;
	}
	
	public ArrayList<Player> getPlayers() {
		return player;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}
