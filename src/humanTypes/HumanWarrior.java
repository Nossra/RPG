package humanTypes;

import java.util.Random;

import Enemies.Human;

public class HumanWarrior extends Human {
	Random rnd = new Random();
	protected int damage = rnd.nextInt(15) + 120;
	protected int health = rnd.nextInt(50) + 450;

	public HumanWarrior() {
		this.setName("Human Warrior");
		this.setHealth(health);
		this.setBaseHealth(health);
		this.setArmor(5);
		this.setBaseDamage(damage);
		this.setDamage(damage);
		this.setSpeed(2);
	}

}
