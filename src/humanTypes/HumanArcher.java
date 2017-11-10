package humanTypes;

import java.util.Random;

import Enemies.Human;

public class HumanArcher extends Human {
	Random rnd = new Random();
	protected int damage = rnd.nextInt(25) + 90;
	protected int health = rnd.nextInt(50) + 350;

	public HumanArcher() {
		this.setName("Human Archer");
		this.setBaseHealth(health);
		this.setHealth(health);
		this.setArmor(3);
		this.setBaseDamage(damage);
		this.setDamage(damage);
		this.setSpeed(3);
	}
}
