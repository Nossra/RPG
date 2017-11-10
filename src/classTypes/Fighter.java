package classTypes;

public class Fighter extends Job implements IFighterSpells {
	
	public Fighter(String name) {
		this.setName(name);
		this.setBaseDamage(110);
		this.setDamage(110);
		this.setBaseHealth(400);
		this.setHealth(400);
		this.setArmor(4);
		this.setSpeed(3);
		this.setCriticalChance(30);
		this.setMissChance(10);
		this.setMana(50);
		this.setBaseMana(50);
	}
}
