package classTypes;

public class Caster extends Job implements ICasterSpells {
	
	public Caster(String name) {
		this.setName(name);
		this.setBaseDamage(1000);
		this.setDamage(1000);
		this.setBaseHealth(200);
		this.setHealth(200);
		this.setArmor(0);
		this.setSpeed(2);
		this.setCriticalChance(100);
		this.setMissChance(0);
		this.setMana(75);
		this.setBaseMana(75);
	}
}
