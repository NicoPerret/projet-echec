package fr.echec.classe.jeu;

// affichage temps restant si defaite au temps

public class Chrono {

	private final long nanoSecondsPerSecond = 1000000000;
	private final long nanoSecondsPerMinute = 60000000000L;
	private long stopWatchStartTime = 0;
	private long stopWatchStopTime = 0;
	private boolean stopWatchRunning = false;
	private long tempsRestant = 600000000000l;
	private long temps = 0;
	private long increment = 0;
	private boolean defaiteTemps = false;

	public long getTemps() {
		return temps;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

	public boolean isDefaiteTemps() {
		return defaiteTemps;
	}

	public void setDefaiteTemps(boolean defaiteTemps) {
		this.defaiteTemps = defaiteTemps;
	}

	public long getIncrement() {
		return increment;
	}

	public void setIncrement(long increment) {
		this.increment = increment * 1000000000;
	}


	public long getElapsedSeconds() {
		long elapsedTime;

		if (stopWatchRunning)
			elapsedTime = (System.nanoTime() - stopWatchStartTime);
		else
			elapsedTime = (stopWatchStopTime - stopWatchStartTime);

		return elapsedTime / nanoSecondsPerSecond;
	}

	public long getElapsedMinutes() {
		long elapsedTime;
		if (stopWatchRunning)
			elapsedTime = (System.nanoTime() - stopWatchStartTime);
		else
			elapsedTime = (stopWatchStopTime - stopWatchStartTime);

		return elapsedTime / nanoSecondsPerMinute;
	}

	public long getStopWatchStartTime() {
		return this.stopWatchStartTime;
	}

	public void getAffichageTempsRestant(long tempsRestant) {
		System.out.println("Temps restant " + tempsRestant / nanoSecondsPerMinute + " mintutes et "
				+ (tempsRestant / nanoSecondsPerSecond) % 60 + " secondes");
	}

	public long getTempsRestant() {
		return tempsRestant;
	}

	// ENTRER NOMBRE DE MINUTES ET NBR SECONDES
	public void setTempsRestant(long tempsRestantMin, long tempsRestantSec) {
		this.tempsRestant = tempsRestantMin * 60000000000l + tempsRestantSec * 1000000000;
	}

	public void runnig() {

		this.temps = System.nanoTime() - this.getStopWatchStartTime();
		if (this.temps > this.tempsRestant) {
			this.setDefaiteTemps(true);
			this.stop();
			this.tempsRestant = 0;
			System.out.println("Perdu au temps");
		}
		
	}

	public void start() {
		this.stopWatchStartTime = System.nanoTime();
		this.stopWatchRunning = true;

	}
	public void stop() {
		this.stopWatchStopTime = System.nanoTime();
		this.stopWatchRunning = false;
		this.tempsRestant =this.tempsRestant - this.temps + this.increment;
	}

	public Chrono (long tempsRestantMin,long tempsRestantSec, long increment){
		this.setTempsRestant(tempsRestantMin, tempsRestantSec);
		this.setIncrement(increment);
	}
}

// EXEMPLE D'UTILISATION
//
// public static void main(String[] args) {
//
//Chrono chronoj1 = new Chrono(3,0,2);
//
////// Tour du J1
//
//chronoj1.start();
//
//for (int i = 0; i < 200000000l; i++) {
//
//	chronoj1.runnig();
//
//	if (chronoj1.isDefaiteTemps() == true) {
//		break;
//	}
//}
//
//chronoj1.stop();
//chronoj1.getAffichageTempsRestant(chronoj1.getTempsRestant());
//
//}