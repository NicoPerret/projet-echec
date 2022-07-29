package fr.echec.classe.jeu;

import java.util.Timer;
import java.util.TimerTask;

public class Chrono {

Timer chrono = new Timer();
	
public void decompte() {

	chrono.schedule(new TimerTask() {
		
		int temps = 160;
		@Override
		public void run() {
			 int tempsMinute = temps / 60; 
			 int tempsSecond = temps % 60;
			 
			 System.out.println(tempsMinute + " : " + tempsSecond );
			
			if (temps == 0) {
				System.out.println("Défaite au temps");
				cancel();
			}
			temps--;
			
		}
	}, 1000, 1000);
}
}
