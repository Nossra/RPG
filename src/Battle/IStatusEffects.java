package Battle;

import models.Unit;

public interface IStatusEffects {
	
	String[] statusFX = { "Polymorph", "Stun" };
	
	int PO_DURATION = 2;
	double PO_HEAL_MODIFIER = 1.2;
	int ST_DURATION = 2;
	double ST_DMG_TAKEN_MODIFIER = 1.3;
	
	default int polymorph(Unit target) {
		int tempDuration = PO_DURATION;
		System.out.print(target.getName() + ": maah! " + target.getName() + " got healed from " + target.getHealth() + "/" + target.getBaseHealth());
		target.setHealth((int) (target.getHealth() * PO_HEAL_MODIFIER));
		System.out.println(" to " + target.getHealth() + "/" + target.getBaseHealth() + " health!\n");
		--tempDuration;
		if (tempDuration == 0) target.setPolymorphed(false);
		return tempDuration;
	}
	
	default int stun(Unit target) {
		int tempDuration = ST_DURATION;
		System.out.println(target.getName() + " is stunned! Damage taken increased by " + (ST_DMG_TAKEN_MODIFIER * 100) +"%!\n");
		target.setDamageTaken(ST_DMG_TAKEN_MODIFIER);
		--tempDuration;
		if (tempDuration == 0) {
			target.setDamageTaken(1.0);
			target.setStunned(false);
		}
		return tempDuration;
	}
}
