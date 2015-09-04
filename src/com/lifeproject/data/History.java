package com.lifeproject.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class History {
    private static History instance;

	public static History getInstance() {
		if (null == instance) {
			instance = new History();
		}
		return instance;
	}

    List<HistoryByte> debut = new ArrayList<HistoryByte>();

	private History() {
        final HistoryByte empty = new HistoryByte("");
		HistoryByte h1 = new HistoryByte("Il était une fois l'histoire de {surname} {name} un{\\e} jeune {race}, ");
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
		HistoryByte h15 = new HistoryByte("la haine lui traversait l'esprit, apprise par son père. {Il\\Elle} n'était pas bon de croiser son chemin : femmes, enfants et nobles étaient les plus vulnérables face à cette terreur.");
		HistoryByte h16 = new HistoryByte("{il\\elle} rejoignit une guilde de pillards et brigands assoiffés de sang, dont le seul but était de déstabiliser le monde, {surname} gravit petit à petit les échelons de celle-ci et devint plus tard {le\\la} maître de la guilde en assassinant son prédécesseur. Plus personne ne semblait pouvoir arrêter {surname} {name}.");

		debut.add(h1);
        h1.add(h2, h3, h4, h5);
        h2.add(h7, h10);
        h3.add(h6, h7);
        h5.add(h13);
        h13.add(h15, h16);
        h10.add(h11, h12);
        h7.add(h8, h9);

        HistoryByte hb1 = new HistoryByte("Dans un pays fort lointain, {name} {surname}, ");
        HistoryByte hb2 = new HistoryByte("{fils\\fille} d'un noble marchand, ");
        HistoryByte hb3 = new HistoryByte("n'ayant pas eu le courage de rejoindre l'armée, dû s'enfuir à travers champs pour arriver dans une famille ");
        HistoryByte hb4 = new HistoryByte("de sage, {il\\elle} vécut là-bas durant des années. Quelques années plus tard, parti{\\e} pour rejoindre la ville de Rivemorte pour rejoindre le temple de la vertu.");
        HistoryByte hb5 = new HistoryByte("Dans ce temple {il\\elle} rejoint l'ordre des Paladins pour devenir un{\\e} grand{\\e} guerri{er\\ère} et un{\\e} grand{\\e} guérisseu{r\\se}.");
        HistoryByte hb6 = new HistoryByte("Dans ce temple, {il\\elle} se rendit compte de la mascarade en place, voulut se révolter, {il\\elle} partit vagabonder dans les plaines. {Il\\Elle} décida quelques mois après de retrouver ses parents dans sa ville natale de Lairouge. {Il\\Elle} découvrit que ses parents ");
        HistoryByte hb7 = new HistoryByte("de paysan. {Il\\Elle} vécut à la ferme durant de nombreuses années. Un jour {il\\elle} revint dans sa ville natale de Lairouge et découvrit que ses parents");
        HistoryByte hb8 = new HistoryByte("étaient devenus pauvres et {il\\elle} a décidé{\\e} de leur porter en aide en travaillant dur, et devenant marchand à son tour. {Il\\Elle} est devenu{\\e} l'un{\\e} des marchand{s\\es} les plus renommé{s\\es} de la ville de  Lairouge et de tout le royaume.");
        HistoryByte hb9 = new HistoryByte("ont profité de la guerre pour s'enrichir et devenir les maîtres de la ville, achetant toute autorité. Ils avaient même le désir de renommer la ville en {name}....");
        HistoryByte hb10 = new HistoryByte("{surname} ne supporta pas ce changement et décida de partir pour ne plus jamais les revoir. {Il\\Elle} est parti{\\e} dans la ville de Ormfranc, la capitale du royaume pour recommencer sa vie… ");
        HistoryByte hb11 = new HistoryByte("{surname} décida de s'exiler et de vivre tel un{\\e} hermite jusqu'au jour où une troupe de barbares arriva et {le\\la} prit en tant qu'esclave. Jusqu'au jour où {il\\elle} essaya de se rebeller...");
        HistoryByte hb12 = new HistoryByte("ayant dû quitter ses parents durant la grande guerre, dût devenir un{\\e} guerri{er\\ère}.");
        HistoryByte hb13 = new HistoryByte("Après de longs mois d'entraînement, {il\\elle} partit en guerre pour prouver son honneur.");
        HistoryByte hb14 = new HistoryByte("Durant de nombreuses batailles {il\\elle} gagna en notoriété et en grade dans l'armée. {Il\\Elle} fut connu{\\e} dans tout le royaume comme {un\\une} guerri{er\\ère} honorable et redoutable.");
        HistoryByte hb15 = new HistoryByte("Horrifi{é\\ée} par la guerre, {il\\elle} déserta la guerre, partit en plein champ de bataille, il fut recherch{é\\ée} dans tout le royaume pour avoir causé la mort de tout son régiment. {Il\\Elle} essaya de retrouver ses parents mais ne pouvant pas approcher une ville, essaya de ne pas se faire remarquer jusqu'au jour où {il\\elle} fut contacté{\\e} par un habitant des sous-terrains et lui proposa de devenir un garde dans ce monde à part.");
        HistoryByte hb16 = new HistoryByte("Il décida de devenir infirmier pour pouvoir aider le plus de gens possibles. ");
        HistoryByte hb17 = new HistoryByte("Il intégra les rangs de l'armée personelle du roi et fut connu{\\e} dans toute la contrée. ");
        HistoryByte hb18 = new HistoryByte("horrifié{\\e} par les blessures de la guerre, {il\\elle} décida d'ouvrir un centre de soins.");

        debut.add(hb1);
        hb1.add(hb2);
        hb2.add(hb3, hb12);
        hb3.add(hb4, hb7);
        hb4.add(hb5, hb6);
        hb5.add(hb16, empty);
        hb6.add(hb8, hb9);
        hb7.add(hb8, hb8);
        hb9.add(hb10, hb11);
        hb12.add(hb13,hb16);
        hb13.add(hb14,hb15);
        hb16.add(hb17,hb18);

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
}
