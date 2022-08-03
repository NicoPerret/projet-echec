package fr.echec.classe.jeu;



class Chrono {

	private final long nanoSecondsPerSecond = 1000000000;
	private final long nanoSecondsPerMinute = 60000000000L;
	private long stopWatchStartTime = 0;
	private long stopWatchStopTime = 0;
	private boolean stopWatchRunning = false;


	public void start() {
		this.stopWatchStartTime = System.nanoTime();
		this.stopWatchRunning = true;

	}

	public void stop() {
		this.stopWatchStopTime = System.nanoTime();
		this.stopWatchRunning = false;
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

	public void getTempsRestant(long tempsRestant) {
		System.out.println("Temps restant " + tempsRestant / nanoSecondsPerMinute + " mintutes et "
				+ (tempsRestant / nanoSecondsPerSecond) % 60 + " secondes");
	}

	
}

						// EXEMPLE D'UTILISATION 



//public static void main(String[] args) {
//
//	Chrono chronoj1 = new Chrono();
//	Chrono chronoj2 = new Chrono();
//	long nanoSecondsPerSecond = 1000000000;
//	long nanoSecondsPerMinute = 60000000000L;
//	long tempsRestantJ1 = 600000000000L;
//	long tempsRestantJ2 = 600000000000L;
//	long temps = 0;
//
//	// Tour du J1
//	chronoj1.start();
//	for(int i = 0 ; i<2000000; i++) {
//
//		temps = System.nanoTime() - chronoj1.getStopWatchStartTime();
//
//		if (temps > tempsRestantJ1) {
//			System.out.println("Perdu au temps");
//			break;
//		}
//	}
//	chronoj1.stop();
//	tempsRestantJ1 -= temps;
//	chronoj1.getTempsRestant(tempsRestantJ1);
//
//	// Tour du J2
//
//	chronoj2.start();
//	while (true) {
//		
//		temps = System.nanoTime() - chronoj2.getStopWatchStartTime();
//		
//		if (temps > tempsRestantJ2) {
//			System.out.println("Perdu au temps");
//			break;
//		}
//	}
//	chronoj2.stop();
//	tempsRestantJ2 -= temps;
//	chronoj1.getTempsRestant(tempsRestantJ2);
//}
