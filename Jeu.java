import java.util.Scanner;

class Jeu{
	public static void main (String [] arg) {
		Scanner sc = new Scanner(System.in);
		int action;


		/*Personnages test= new Personnages("moi", "homme", "guerrier", "epee", 10, 1, 2, 4);
		System.out.println(test);*/
		//ajouter info création de personnage
		Personnages joueur1 = Creationpers(1, 9, 5);
		System.out.println(joueur1);
		System.out.println();
		Personnages joueur2 = Creationpers(2, 10, 14);
		System.out.println(joueur2);
		System.out.println();
		Plateau p = new Plateau(joueur1, joueur2, 10);
		affichePlateau(p);


		//ajouter info sur le déroulement du jeu


		if (joueur1.getIntelligence() < joueur2.getIntelligence()) {
			joueur1.setAction(0);                                                                         //le plus intelligent commence
		} else {
			if (2 + (joueur1.getIntelligence() / (2 * joueur2.getIntelligence()))<=5){
				joueur1.setAction(2 + (joueur1.getIntelligence() / (2 * joueur2.getIntelligence())));
			} else{
				joueur1.setAction(4);
			}

		}



		while (joueur1.getVie() > 0 && joueur2.getVie() > 0) {     //tant que les joueurs sont vivants

			//tour du joueur 1

			while ((joueur1.getAction() > 0) && (joueur1.getVie() > 0) && (joueur2.getVie() > 0)) {
				System.out.println(joueur1.getNom() + " avez " + joueur1.getAction() + " points d'action");
				System.out.println(joueur1.getNom() + " Que voulez vous faire ? se deplacer, se soigner, attaquer, se proteger ? ");
				switch (sc.nextLine()) {
					case "se deplacer":
						p.bougeJoueur(joueur1, joueur2);
						joueur1.perteAction(1);
						break;
					case "se soigner":
						p.soignerJoueur(joueur1);
						joueur1.perteAction(1);
						break;
					case "attaquer":

						if(!p.porteesuffisante(joueur1, joueur2)){
							System.out.println(joueur2.getNom()+" n'est pas à votre portée");
							break;
						}

						p.attaqueJoueur(joueur1, joueur2);
						if (joueur2.getVie() > 0) {
							System.out.println(joueur2.getNom() + " a maintenant " + joueur2.getVie() + " de vie.");
						}
						joueur1.perteAction(1);
						break;

					case "se proteger":
						joueur1.perteAction(1);
						break;
				}
				if (joueur2.getVie() > 0) {
					affichePlateau(p);
				}
			}

			//tour du 2eme joueur

			joueur2.setAction(2 + (joueur2.getIntelligence() / (2 * joueur1.getIntelligence())));

			while ((joueur2.getAction() > 0) && (joueur1.getVie() > 0) && (joueur2.getVie() > 0)) {
				System.out.println(joueur2.getNom() + " avez " + joueur2.getAction() + " points d'action");
				System.out.println(joueur2.getNom() + " Que voulez vous faire ? se deplacer, se soigner, attaquer, se proteger ? ");
				switch (sc.nextLine()) {
					case "se deplacer":
						p.bougeJoueur(joueur2, joueur1);
						joueur2.perteAction(1);
						break;
					case "se soigner":
						p.soignerJoueur(joueur2);
						joueur2.perteAction(1);
						break;
					case "attaquer":

						if(!p.porteesuffisante(joueur2, joueur1)){
							System.out.println(joueur1.getNom()+" n'est pas à votre portée");
							break;
						}
						p.attaqueJoueur(joueur2, joueur1);
						if (joueur1.getVie() > 0) {
							System.out.println(joueur1.getNom() + " a maintenant " + joueur1.getVie() + " de vie.");
						}
						joueur2.perteAction(1);
						break;
					case "se proteger":
						joueur2.perteAction(1);
						break;
				}
				if (joueur1.getVie() > 0) {
					affichePlateau(p);
				}


			}

			joueur1.setAction(2 + (joueur1.getIntelligence() / (2*joueur2.getIntelligence())));

		}




		System.out.println("Le jeu est fini");
		if(joueur1.getVie()<joueur2.getVie()) {
			System.out.println(joueur2.getNom() + " a gagné.");
		}else{
			System.out.println(joueur1.getNom()+" a gagné.");
		}


	}


	private static void affichePlateau(Plateau p){

		char [][] plateau= p.getPlateau();
		for(int i=0; i<plateau.length; i++){
			for(int j=0; j<plateau[i].length;j++){
				System.out.print(plateau[i][j]);
			}
			System.out.println();

		}
	}
		
	private static Personnages Creationpers(int ordre, int x, int y){
		Scanner sc= new Scanner(System.in);
		Race race;
		Classe classe;
		int point= 24;
		int force=0;
		int dexterite=0;
		int intelligence=0;
		int vie=0;
			System.out.println("Joueur "+ordre+" Entrez votre nom: ");
			String nom= sc.nextLine();
			System.out.println();
			System.out.println(" Voulez-vous jouer avec ");
			System.out.println("un Elfe(1)");
			System.out.println("un Homme(2)");
			System.out.println("un Nain(3)");
			System.out.println("un Orc(4)");

			switch(sc.nextInt()){
				case 1:
					race = new Elfe();
					break;

				case 2:
					race = new Homme();
					break;

				case 3:
					race = new Nain();
					break;

				case 4:
					race = new Orc();
					break;

				default:
					race = null;

			}
			System.out.println(nom+" combat avec un "+race.getNom());
			System.out.println();

			System.out.println(" Choississez une classe");
			System.out.println("Paladins(1)");
			System.out.println("Guerriers(2)");
			System.out.println("Archers(3)");
			System.out.println("Magiciens(4)");

			switch(sc.nextInt()){
				case 1:
					classe = new Paladin();
					break;

				case 2:
					classe = new Guerrier();
					break;

				case 3:
					classe = new Archer();
					break;

				case 4:
					classe = new Magicien();
					break;

				default:
					classe = null;

			}
			System.out.println(nom+" combat avec un "+classe.getNom());
			System.out.println();
			System.out.println("Vous avez 24 points à repartir dans les attributs max 10");
			do{
				System.out.println("Il vous reste: "+point+". Combien de point en force? ");
				int a=sc.nextInt();
				if(a<=point && force+a<=10){ 
					force=force+ a;
					point= point-a;
				}
				System.out.println("Il vous reste: "+point+". Combien de point en dexterité? ");
				a=sc.nextInt();
				if(a<=point && dexterite+a<=10){ 
					dexterite=dexterite+ a;
					point= point-a;
				}
				System.out.println("Il vous reste: "+point+". Combien de point en intelligence? ");
				a=sc.nextInt();
				if(a<=point && intelligence+a<=10){ 
					intelligence=intelligence+ a;
					point= point-a;
				}
				System.out.println("Il vous reste: "+point+". Combien de point en vie? ");
				a=sc.nextInt();
				if(a<=point && vie+a<=10){ 
					vie=vie + a;
					point= point-a;
				}
			} while(point >0);
			return new Personnages(nom, race, classe, force, dexterite, vie, intelligence, x, y);
			
		
	}
}
