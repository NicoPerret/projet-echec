package fr.echec.demowebsocket;

import java.util.List;

public class Coords {
	private List<String> coup;

	public List<String> getCoup() {
		return coup;
	}

	public void setCoup(List<String> coup) {
		this.coup = coup;
	}

	public Coords() {

	}

	public Coords(List<String> coups) {
		this.coup = coups;

	}

}
