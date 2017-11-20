package orcTypes;

import java.util.Random;

import Enemies.Orc;

public class OrcWarlock extends Orc implements IOrcSpells {
	Random rnd = new Random();
	protected int damage = rnd.nextInt(5) + 90;
	protected int health = rnd.nextInt(50) + 350;

	public OrcWarlock() {
		this.setName("Orc Warlock");
		this.setHealth(health);
		this.setBaseHealth(health);
		this.setArmor(2);
		this.setBaseDamage(damage);
		this.setDamage(damage);
		this.setSpeed(2);
	}
}
