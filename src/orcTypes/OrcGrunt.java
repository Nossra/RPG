package orcTypes;

import java.util.Random;

import Enemies.Orc;

public class OrcGrunt extends Orc {
	Random rnd = new Random();
	protected int damage = rnd.nextInt(25) + 150;
	protected int health = rnd.nextInt(50) + 600;

	public OrcGrunt() {
		this.setName("Orc Grunt");
		this.setHealth(health);
		this.setBaseHealth(health);
		this.setArmor(8);
		this.setBaseDamage(damage);
		this.setDamage(damage);
		this.setSpeed(1);
	}
}
