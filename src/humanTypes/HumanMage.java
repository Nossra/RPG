package humanTypes;

import java.util.Random;
import Enemies.Human;

public class HumanMage extends Human implements IHumanSpells {
	Random rnd = new Random();
	protected int damage = rnd.nextInt(5) + 60;
	protected int health = rnd.nextInt(50) + 250;

	public HumanMage() {
		this.setName("Human Mage");
		this.setBaseHealth(health);
		this.setHealth(health);
		this.setArmor(0);
		this.setBaseDamage(damage);
		this.setDamage(damage);
		this.setSpeed(1);
	}
}
