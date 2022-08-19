package fr.echec.classe.jeu;



class Chrono {

	private final long nanoSecondsPerSecond = 1000000000;
	private final long nanoSecondsPerMinute = 60000000000L;
	private long stopWatchStartTime = 0;
	private long stopWatchStopTime = 0;
	private boolean stopWatchRunning = false;
	private long tempsRestant = 600000000000l;
	private long temps = 0;
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

	// ENTRER NOMBRE DE MINUTES
	public void setTempsRestant(long tempsRestant) {
		this.tempsRestant = tempsRestant * 60000000000l;
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
		this.tempsRestant -= temps;
	}

}

// EXEMPLE D'UTILISATION

// public static void main(String[] args) {
//
//		Chrono chronoj1 = new Chrono();
//
////		// Tour du J1
//
//		chronoj1.start();
//
//		while (true) {
//
//			chronoj1.runnig();
//
//			if (chronoj1.isDefaiteTemps() == true) {
//				break;
//			}
//		}
//
//		chronoj1.stop();
//		chronoj1.getAffichageTempsRestant(chronoj1.getTempsRestant());
//
//	}