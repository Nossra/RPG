package humanTypes;

public interface IHumanSpells {
	default void fireBall() {
		System.out.println("Fireball");
	}
	
	default void chainLightning() {
		System.out.println("Chain lightning");
	}
	
	default void polymorph() {
		System.out.println("Polymorph");
	}
	
	default void healingWind() {
		System.out.println("Healing wind");
	}

}
