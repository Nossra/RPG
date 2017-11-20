package Battle;

import models.Unit;

public interface IStatusEffects {
	
	String[] statusFX = { "Polymorph", "Stun" };
	
	default void polymorph(Unit target) {
		System.out.print(target.getName() + ": maah! " + target.getName() + " got healed from " + target.getHealth() + "/" + target.getBaseHealth());
		target.setHealth((int) (target.getHealth() * 1.2));
		System.out.println(" to " + target.getHealth() + "/" + target.getBaseHealth() + " health!\n");
		if (target.getControlled() == 0) target.setPolymorphed(false);
	}
	
	default void stun(Unit target) {
		System.out.println(target.getName() + " is stunned!\n");
		target.setDamageTaken(1.3);
		if (target.getControlled() == 0) {
			target.setDamageTaken(1.0);
			target.setStunned(false);
		}
	}
}
