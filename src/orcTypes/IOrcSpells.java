package orcTypes;

public interface IOrcSpells {
	default void corruption() {
		System.out.println("Corruption");
	}
	
	default void fireBreath() {
		System.out.println("Fire Breath");
	}
}
