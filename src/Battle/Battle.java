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
		int exp = expPool();
		while (this.isFighting()) {
			for (int i = 0; i < turnOrder.size(); i++) {
				if (turnOrder.get(i).getPlayable() == true) {
					Player player = (Player) turnOrder.get(i);
					playerTurn(i);
					if (getEnemies().isEmpty()) {
						System.out.println("The player wins this fight!\n");
						TimeUnit.SECONDS.sleep(2);	
						player.gainExperience((Player) turnOrder.get(i), getEnemies(), getPlayers(), exp);
						for (int j = 0; j < getPlayers().size(); j++) {
							getPlayers().get(j).setCharging(false);
						}
						
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
	public int expPool() {
		int expPool = 0;
		for (int i = 0; i < getEnemies().size(); i++) {
			expPool += getEnemies().get(i).getExperienceDrop();
		}
		return expPool;
	}

	public void playerTurn(int i) throws InterruptedException {
		Player player = (Player) turnOrder.get(i);
		printStatus();
		System.out.println(player.getName().toUpperCase() + "'S TURN! (" + player.getClass().getSimpleName().toUpperCase() + ")");
		classOption(player, getEnemies(), getPlayers());
		TimeUnit.SECONDS.sleep(2);
		for (int j = 0; j < getEnemies().size(); j++) {
			if (getEnemies().get(j).getHealth() < 1) {
				int tempEnemy = getEnemies().get(j).getId();
				System.out.println(" -- " + getEnemies().get(j).getName() + " HAS DIED. --\n");
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
		System.out.println(currentEnemy.getName().toUpperCase() + "'S TURN!\n");
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
		String header = "%-15s %-10s %-10s%n";
		String valuesFormat = "%-15s %-10s %-10s%n";
		System.out.printf(header, "NAME", "HP", "MP");
		for (int i = 0; i < getPlayers().size(); i++) {
			String hp = getPlayers().get(i).getHealth() + "/" + getPlayers().get(i).getBaseHealth();
			String mp = getPlayers().get(i).getMana() + "/" + getPlayers().get(i).getBaseMana();
			System.out.printf(valuesFormat,
					player.get(i).getName(), hp, mp);
		}
		System.out.println();
		String enemyHeader = "%-15s %-7s%n";
		String enemyValues = "%-15s %-7s%n";
		System.out.printf(enemyHeader, "NAME", "HP");
		for (int i = 0; i < getEnemies().size(); i++) {
			String hp = getEnemies().get(i).getHealth() + "/" + getEnemies().get(i).getBaseHealth();
			System.out.printf(enemyValues, getEnemies().get(i).getName(), hp);
		}
		System.out.println();
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
