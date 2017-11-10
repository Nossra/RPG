package models;

public interface IEnemyModels {
		
	default  void enemyCritChance(Unit currentEnemy) {
		System.out.print(" - CRITICAL HIT! - ");
		currentEnemy.setDamage(currentEnemy.getBaseDamage()*2);
	}
	
	default void enemyMissChance(Unit currentEnemy) {
		System.out.println(" - MISS! - \n");
	}
}
