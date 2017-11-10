package Battle;
import java.util.ArrayList;
import java.util.Random;
import orcTypes.*;
import humanTypes.*;
import models.Enemy;

public class Patrole {
	Random rnd = new Random();
	private int numberOfEnemies;
	protected ArrayList<Enemy> enemyTeam = new ArrayList<Enemy>();
	
	/*
	 * The class needs no specific constructor because theres no variables that needs to be to be declared after initialization.
	 * It randoms out the type of patrol you face (orcs or humans so far) and then runs the method for creating that type of patrol
	 */
	
	public ArrayList<Enemy> patroleType() {
		int patrolType = rnd.nextInt(2);
 		if (patrolType == 0) {
 			humanPatrole();			
		} else if (patrolType == 1) {
			orcPatrole();
		}
		return enemyTeam;
	}
	
	//Method to create the patrole of humans. It randoms the number of enemies (3 or 4) and then randoms what Orc enemy it wants to create
	public ArrayList<Enemy> humanPatrole() {
		numberOfEnemies = rnd.nextInt(3) + 3;
		for (int i = 0; i<numberOfEnemies; i++) {
			
			int addEnemy = rnd.nextInt(3); 
			if (addEnemy == 0) {
				enemyTeam.add(new HumanArcher());
			} else if (addEnemy == 1) {
				enemyTeam.add(new HumanMage());
			} else if (addEnemy == 2)  {
				enemyTeam.add(new HumanWarrior());
			}	
		}
		return enemyTeam;
	}
	
	//Method to create the patrole of Orcs. It randoms the number of enemies (2 or 3) and then randoms what human enemy it wants to create
	public ArrayList<Enemy> orcPatrole() {
		numberOfEnemies = rnd.nextInt(3) + 2;
		for (int i = 0; i<numberOfEnemies; i++) {
			int addEnemy = rnd.nextInt(2); 
			if (addEnemy == 0) {
				enemyTeam.add(new OrcGrunt());
			} else if (addEnemy == 1) {
				enemyTeam.add(new OrcWarlock());
			}
		}
		return enemyTeam;
	}
}
