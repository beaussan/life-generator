package com.lifeproject.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class History {
	List<HistoryByte> debut = new ArrayList<HistoryByte>();

	public static History getInstance() {
		if (null == instance) {
			instance = new History();
		}
		return instance;
	}

	private History() {
		HistoryByte h1 = new HistoryByte(
					"Il était une fois l'histoire de {name}, {fils\\fille} de noble en quête de gloire et de pouvoir suite à la déchéance qu'a {connu\\connue} sa famille suite à la révolte de leur peuple.");
		HistoryByte h2 = new HistoryByte(
				"Un enfant trouvé sous le cadavre de sa mère, adopté par la femme d'un mercenaire. Néanmoins, elle mourut également par la peste, l'enfant dût alors prendre les armes pour subsister.");
		HistoryByte h3 = new HistoryByte(
				"Né{\\e} d'une union sans amour d'un bandit et d'une villageoise, l'enfant ne connu pas son père et ne connut que les sévices de sa mère qui voyait en {lui\\elle} l'image de son aggresseur, {name} dû fuguer très jeune, {il\\elle} rejoignit alors une troupe de bandit et commença à répandre la mort et la terreur dans les villages qu'{il\\elle} rencontrait.");
		debut.add(h1);
		h1.add(h2);
		h1.add(h3);
	}

	public String getHistory(Humain hum){
    	StringBuilder sb = new StringBuilder();
    	Random r = new Random();
    	HistoryByte hb = debut.get(r.nextInt(debut.size()));
		List<HistoryByte> ls;
    	do {
    		sb.append(hb.getStoryFor(hum));
			ls = hb.getPossibleOutcome();
			if (ls.isEmpty()){
				break;
			}
    		hb = ls.get(r.nextInt(ls.size()));
		} while (!ls.isEmpty());
    	
    	
    	return sb.toString();
    }

	private static History instance;
}
