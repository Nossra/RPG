package classTypes;

public class Archer extends Job implements IArcherSpells {
	protected boolean aiming;
	
	public Archer(String name) {
		this.setName(name);
		this.setBaseDamage(135);
		this.setDamage(135);
		this.setBaseHealth(300);
		this.setHealth(300);
		this.setArmor(1);
		this.setSpeed(4);
		this.setCriticalChance(40);
		this.setMissChance(20);
		this.setMana(50);
		this.setBaseMana(50);
	}

	public boolean isAiming() {
		return aiming;
	}

	public void setAiming(boolean aiming) {
		this.aiming = aiming;
	}

}
