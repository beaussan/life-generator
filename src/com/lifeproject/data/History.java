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
		HistoryByte h1 = new HistoryByte("Il était une fois l'histoire de {surname} {name} un{\\e} jeune {race}, ");
		HistoryByte h17 = new HistoryByte("Dans une contr�e lointaine vivait {surname} {name} un{\\e} {race}, ");
		HistoryByte h2 = new HistoryByte("{fils\\fille} de noble en quête de gloire et de pouvoir suite à la déchéance qu'a connu{\\e} sa famille suite à la révolte de leur peuple. ");
		HistoryByte h3 = new HistoryByte("qui fût trouvé{\\e} sous le cadavre de sa mère et adopté{\\e} par la femme d'un mercenaire. Néanmoins, celle-ci mourut également par la peste, l'enfant dût alors prendre les armes pour subsister. ");
		HistoryByte h4 = new HistoryByte("né{\\e} d'une union sans amour d'un bandit et d'une villageoise, l'enfant ne connut pas son père et ne connut que les sévices de sa mère qui voyait en {lui\\elle} l'image de son aggresseur, {surname} dû fuguer très jeune, ");
		HistoryByte h5 = new HistoryByte("formé{\\e} très tôt à l'usage des armes par son père, {surname} ne connut pas d'éducation à proprement dire. "); 
		HistoryByte h6 = new HistoryByte("{il\\elle} rejoignit alors une troupe de bandit et commença à répandre la mort et la terreur dans les villages qu'{il\\elle} rencontrait. ");
		HistoryByte h7 = new HistoryByte("{surname} décida de rejoindre une troupe de mercenaire et survecût en dépit de son jeune âge à de nombreuses batailles. ");
		HistoryByte h8 = new HistoryByte("Son nom devint un synonyme de terreur pour les rangs ennemis mais également un synonyme d'espoir pour ses compagnons.");
		HistoryByte h9 = new HistoryByte("On contait partout les exploits d'un{\\e} mercenaire que l'on croyait immortel{\\le} mais dont nul ne connaissait le nom ni le visage.");
		HistoryByte h10 = new HistoryByte("L'éducation que {surname} reçût depuis son plus jeune âge, lui avait permis d'obtenir de bonnes connaissances en économie et en commerce, {surname} se décida donc à devenir marchand, ");
		HistoryByte h11 = new HistoryByte("et fut capable d'amasser une fortune colossale, et de retrouver une partie de son pouvoir passé. ");
		HistoryByte h12 = new HistoryByte("mais fut incapable de réunir une véritable fortune et resta dès lors un{\\e} éternel{\\le} anonyme. ");
		HistoryByte h13 = new HistoryByte("Ce côté ignorant influenca ses choix de vie de manière violente et sanglante, ");
		HistoryByte h15 = new HistoryByte("la haine lui traversait l'esprit, apprise par son père. Il n'était pas bon de croiser son chemin : femmes, enfants et nobles étaient les plus vulnérables face à cette terreur.");
		HistoryByte h16 = new HistoryByte("{il\\elle} rejoignit une guilde de pillards et brigands assoiffés de sang, dont le seul but était de déstabiliser le monde, {surname} gravit petit à petit les échelons de celle-ci et devint plus tard le maître de la guilde en assassinant son prédécesseur. Plus personne ne semblait pouvoir arrêter {surname} {name}.");
		debut.add(h1);
		debut.add(h17);
		h1.add(h2);
		h1.add(h3);
		h1.add(h4);
		h1.add(h5);
		h2.add(h7);
		h4.add(h16);
		h4.add(h7);
		h4.add(h6);
		h17.add(h2);
		h17.add(h3);
		h17.add(h5);
		h17.add(h7);
		h2.add(h10);
		h5.add(h13);
		h13.add(h15);
		h13.add(h16);
		h10.add(h11);
		h10.add(h12);
		h3.add(h7);
		h3.add(h6);
		h7.add(h8);
		h7.add(h9);
	}

	public String getHistory(Humain hum){
    	StringBuilder sb = new StringBuilder();
    	Random r = new Random();
    	HistoryByte hb = debut.get(r.nextInt(debut.size()));
		List<HistoryByte> ls;
		hum.resetHistoryBytes();
    	do {
    		sb.append(hb.getStoryFor(hum));
			ls = hb.getPossibleOutcome();
			hum.addHB(hb);
			if (ls.isEmpty()){
				break;
			}
    		hb = ls.get(r.nextInt(ls.size()));
		} while (!ls.isEmpty());
    	
    	
    	return sb.toString();
    }

	private static History instance;
}
