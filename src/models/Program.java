package models;
import java.util.ArrayList;

import Battle.Battle;
import Battle.Patrole;
import classTypes.Archer;
import classTypes.Caster;
import classTypes.Fighter;
import classTypes.Job;

public class Program {
	/*
	 * KNOWN BUGS
	 * If you kill several enemies at once, both of them wont get removed from the game.
	 * It doesnt tell you both died either.
	 * Enemy crit doesnt always yield twice the damage (HumanArcher was affected, was the first attacker out of 5.
	 * AIMED SHOT bugs when the intended target dies before the shot hits. It immediately kills the next target.
	 */	
	
	public static void main(String[] args) throws Exception {
		Job.printJobs();
		Player player = Job.createCharacter();
		Archer anna = new Archer("Anna");
		Fighter claire = new Fighter("Claire");
		Caster herald = new Caster("Herald");
		ArrayList<Player> playerTeam = new ArrayList<Player>();
		playerTeam.add(player);
		playerTeam.add(anna);
		playerTeam.add(claire);
		playerTeam.add(herald);
		System.out.println();
		Player.printTeamStatus(playerTeam);
		
		System.out.println(
				"\n\nWelcome to this RPG Adventure!\n\n"
				);
		
		Patrole enemyTeam = new Patrole();
		Battle b = new Battle(playerTeam, enemyTeam.patroleType());
		b.battle();
		Patrole et2 = new Patrole();
		Battle b1 = new Battle(playerTeam, et2.patroleType());
		b1.battle();
		Patrole et3 = new Patrole();
		Battle b2 = new Battle(playerTeam, et3.patroleType());
		b2.battle();
		
	}
}
	