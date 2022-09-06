package fr.echec.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public boolean countFrequencies(ArrayList<String> list) {
		// hashmap to store the frequency of element

		Map<String, Integer> hm = (Map<String, Integer>) new HashMap();

		for (String i : list) {
			Integer j = hm.get(i);
			hm.put(i, (j == null) ? 1 : j + 1);
		}

		// displaying the occurrence of elements in the arraylist
		for (Map.Entry<String, Integer> val : hm.entrySet()) {
			
			if (val.getValue() >= 3) {
				return true;
			}
		}
		return false;
	}
}
