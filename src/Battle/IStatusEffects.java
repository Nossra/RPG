package Battle;

import models.Unit;

public interface IStatusEffects {
	
	default void polymorph(Unit target) {
		System.out.print(target.getName() + ": maah!" + target.getName() + "\nGot healed from " + target.getHealth() + "/" + target.getBaseHealth());
		target.setHealth((int) (target.getHealth() * 1.2));
		System.out.println(" to " + target.getHealth() + "/" + target.getBaseHealth() + " health!\n");
		target.reduceControl();
		if (target.getControlled() == 0) target.setPolymorphed(false);
	}
	
	default void stun(Unit target) {
		System.out.println(target.getName() + " is stunned!");
		target.setDamageTaken(1.3);
		target.reduceControl();
		if (target.getControlled() == 0) {
			target.setDamageTaken(1.0);
			target.setStunned(false);
		}
	}
}
