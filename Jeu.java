import java.util.Scanner;

class Jeu {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		introduction();

		//ajouter info création de personnage
		Personnages joueur1 = Creationpers(1, 9, 5);
		System.out.println(joueur1);
		System.out.println();
		Personnages joueur2 = Creationpers(2, 10, 14);
		System.out.println(joueur2);
		System.out.println();
		System.out.println("Les héros sont nés. Ils vont maintenant se battre à mort, veuillez entrer dans l'arène");
		System.out.println();
		Plateau p = new Plateau(joueur1, joueur2);
		affichePlateau(p);

		System.out.println("Que le combat commence !");
		System.out.println();

		//ajouter info sur le déroulement du jeu


		if (joueur1.getIntelligence() < joueur2.getIntelligence()) {
			joueur1.setAction(0);                                                                         //le plus intelligent commence
		} else {
			if (2 + (joueur1.getIntelligence() / (2 * joueur2.getIntelligence())) <= 3) {
				joueur1.setAction(2 + (joueur1.getIntelligence() / (2 * joueur2.getIntelligence())));
			} else {
				joueur1.setAction(3);
			}

		}


		int a = 0;  //pourcentage de protection
		int b = 0;  //dégat de la contre attaque
		while (joueursVivants(joueur1, joueur2)) {     //tant que les joueurs sont vivants

			//tour du joueur 1

			while ((joueur1.getAction() > 0) && joueursVivants(joueur1, joueur2)) {
				System.out.println(joueur1.getNom() + " avez " + joueur1.getAction() + " points d'action");
				System.out.println(joueur1.getNom() + " Que voulez vous faire ? se deplacer (1), se soigner (2), attaquer (3), se proteger (4) ? ");
				switch (sc.nextInt()) {
					case 1:
						p.bougeJoueur(joueur1, joueur2);
						joueur1.perteAction(1);
						break;
					case 2:
						p.soignerJoueur(joueur1);
						joueur1.perteAction(1);
						break;
					case 3:

						if (!p.porteesuffisante(joueur1, joueur2)) {
							System.out.println(joueur2.getNom() + " n'est pas à votre portée");
							p.affichePortee(joueur1);
							affichePlateau(p);
							p.desaffichePortee(joueur1, joueur2);
							break;
						}

						p.attaqueJoueur(joueur1, joueur2, a, b);
						a = 0;
						b = 0;
						if (joueur2.getVie() > 0) {
							System.out.println(joueur2.getNom() + " a maintenant " + joueur2.getVie() + " de vie.");
						}
						joueur1.perteAction(1);
						break;

					case 4:
						if (joueur1.getAction() != 1) {
							System.out.println("Vous ne pouvez pas vous proteger maintenant, le joueur ne va pas attaquer avant au moins un tour");
						} else {
							a = joueur1.seproteger();
							System.out.println("Vous vous protégerez de " + a + "% des dégats de la prochaine attaque");
							b = joueur1.contreAttaque(a);
							joueur1.perteAction(1);
						}
						break;
				}
				p.miseajourPlateau(joueur1, joueur2);
				if (joueur2.getVie() > 0) {
					affichePlateau(p);
				}
			}

			//tour du 2eme joueur

			joueur2.setAction(2 + (joueur2.getIntelligence() / (2 * joueur1.getIntelligence())));

			while ((joueur2.getAction() > 0) && joueursVivants(joueur1, joueur2)) {
				System.out.println(joueur2.getNom() + " avez " + joueur2.getAction() + " points d'action");
				System.out.println(joueur2.getNom() + " Que voulez vous faire ? se deplacer (1), se soigner (2), attaquer (3), se proteger (4)? ");
				switch (sc.nextInt()) {
					case 1:
						p.bougeJoueur(joueur2, joueur1);
						joueur2.perteAction(1);
						break;
					case 2:
						p.soignerJoueur(joueur2);
						joueur2.perteAction(1);
						break;
					case 3:

						if (!p.porteesuffisante(joueur2, joueur1)) {
							System.out.println(joueur1.getNom() + " n'est pas à votre portée");
							p.affichePortee(joueur2);
							affichePlateau(p);
							p.desaffichePortee(joueur1, joueur2);
							break;

						}
						p.attaqueJoueur(joueur2, joueur1, a, b);
						a = 0;
						b = 0;
						if (joueur1.getVie() > 0) {
							System.out.println(joueur1.getNom() + " a maintenant " + joueur1.getVie() + " de vie.");
						}
						joueur2.perteAction(1);
						break;
					case 4:
						if (joueur2.getAction() != 1) {
							System.out.println("Vous ne pouvez pas vous proteger maintenant, le joueur ne va pas attaquer avant au moins un tour");
						} else {
							a = joueur2.seproteger();
							System.out.println("Vous vous protégerez de " + a + "% des dégats de la prochaine attaque");
							b = joueur2.contreAttaque(a);
							joueur2.perteAction(1);
						}
						break;
				}

				if (joueur1.getVie() > 0) {

					affichePlateau(p);
				}


			}

			joueur1.setAction(2 + (joueur1.getIntelligence() / (2 * joueur2.getIntelligence())));

		}


		System.out.println("Le jeu est fini");
		if (joueur1.getVie() < joueur2.getVie()) {
			System.out.println(joueur2.getNom() + " a gagné. Le plus fort est sorti vaincqueur de cette formidable confrontation.");
		} else {
			System.out.println(joueur1.getNom() + " a gagné. Le plus fort est sorti vaincqueur de cette formidable confrontation.");
		}
		System.out.println();
		credit();
	}


	private static void affichePlateau(Plateau p) {

		char[][] plateau = p.getPlateau();
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				System.out.print(plateau[i][j]);
			}
			System.out.println();

		}
	}

	private static Personnages Creationpers(int ordre, int x, int y) {
		//fenetre fen1 = new fenetre(ordre);
		//fen1.setTitle("Perso "+ordre);


		//fen1.setVisible(true);
		Scanner sc = new Scanner(System.in);
		Race race;
		Classe classe;
		int point = 24;
		int force = 0;
		int dexterite = 0;
		int intelligence = 0;
		int vie = 0;
		System.out.println("Joueur " + ordre + " Entrez votre nom: ");
		String nom = sc.nextLine();
		System.out.println();
		presentationRace();
		System.out.println(" Voulez-vous jouer avec ");
		System.out.println("un Elfe(1)");
		System.out.println("un Homme(2)");
		System.out.println("un Nain(3)");
		System.out.println("un Orc(4)");

		switch (sc.nextInt()) {
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
		System.out.println(nom + " combat avec un " + race.getNom());
		System.out.println();
		presentationClasse();
		System.out.println(" Choississez une classe");
		System.out.println("Paladins(1)");
		System.out.println("Guerriers(2)");
		System.out.println("Archers(3)");
		System.out.println("Magiciens(4)");

		switch (sc.nextInt()) {
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
		System.out.println(nom + " combat avec un " + classe.getNom());
		System.out.println();
		System.out.println("Pour finir, vous avez 24 points à repartir dans les attributs maximum 10 points en plus par attribut. Choississez bien, votre victoire en dépend.");
		do {
			System.out.println("Il vous reste: " + point + ". Combien de point en force? ");
			int a = sc.nextInt();
			if (a <= point && force + a <= 10) {
				force = force + a;
				point = point - a;
			}
			System.out.println("Il vous reste: " + point + ". Combien de point en dexterité? ");
			a = sc.nextInt();
			if (a <= point && dexterite + a <= 10) {
				dexterite = dexterite + a;
				point = point - a;
			}
			System.out.println("Il vous reste: " + point + ". Combien de point en intelligence? ");
			a = sc.nextInt();
			if (a <= point && intelligence + a <= 10) {
				intelligence = intelligence + a;
				point = point - a;
			}
			System.out.println("Il vous reste: " + point + ". Combien de point en vie? ");
			a = sc.nextInt();
			if (a <= point && vie + a <= 10) {
				vie = vie + a;
				point = point - a;
			}
		} while (point > 0);
		return new Personnages(nom, race, classe, force, dexterite, vie, intelligence, x, y);


	}

	private static boolean joueursVivants(Personnages joueur1, Personnages joueur2) {
		return (joueur1.getVie() > 0 && joueur2.getVie() > 0);
	}

	public static void introduction() {
		System.out.println();
		System.out.println("Bienvenu dans x !");
		System.out.println();
		System.out.println("Vous allez incarner un héros et vous battre à mort pour la gloire et la richesse");
		System.out.println();
		System.out.println("Vous devrez d'abord créer votre combattant en le personnalisant à votre guise pour le rendre le plus performant possible. ");
		System.out.println("Puis, il saura envoyer dans une arène pour combattre le héros de votre adversaire. Le dernier debout gagne.");
		System.out.println();
		System.out.println("Il faut savoir que quatres caractéristiqus définiront votre héros, la force, la dextérité, l'intelligence et la vie. ");
		System.out.println("Chacunes influent sur ses actions et à une importance. Il est donc important de bien les défénir avec les choix qui vous seront proposer suivant votre style de jeu.");
		System.out.println();
		System.out.println("Commençons.");
		System.out.println();
	}

	public static void presentationRace(){
		System.out.println("Il faut choisir une race à votre personnages. Il y en a 4.");
		System.out.println();
		System.out.println("L'elfe est assez équilibré. Il a su acquérir une expérience importante en matière de combat grâce à sa vitalité phénoménale. ");
		System.out.println("Il est très rapide et son sens de la stratégie est sans égal. Il méprise les nains et se fera un plaisir de les ridiculiser.");
		System.out.println("L'homme a su se demarqué des autres races de part son intelligence sans égal. Pour le reste, il est assez équilibré.");
		System.out.println("Il se pense nettement supérieur aux autres races et c'est peu être son plus grand point faible.");
		System.out.println("Le nain, il frappe d'abord et pense après. Un nain est peu malin et préferera toujours une bonne bière au combat. ");
		System.out.println("Néanmoins, ce n'est pas pour rien que les dragons le redoute tant. Son rêve, dominer les elfes.");
		System.out.println("Enfin, l'orc haïe de toutes les autres races. Cet être est incapable de faire la différence entre ses amis et ses ennemis. ");
		System.out.println("Il ne pense qu'à se battre et ne craint aucune douleure. Il est si puissant qu'il ne cherche ni à réfléchir ni à esquiver les coups.");
		System.out.println();
	}

	public static void presentationClasse(){
		System.out.println("Il vous faut maintenant chosir une classe. Il y en a aussi 4.");
		System.out.println();
		System.out.println("Il y a le paladin. Un être caractérisé par son honneur et sa vertu. Il se bas pour le bien et pour le préserver. ");
		System.out.println("Adapte d'un combat rapprocher, il maitrise son épée à la perfection.");
		System.out.println("Le guerrier est né guerrier et mourrera guerrier. Avec sa lance, il enchaîne les batailles pour amasser toujours plus de ");
		System.out.println("récompenses et de gloires et ainsi devenir la vedette de la taverne.");
		System.out.println("L'archer possède une agilité sans égal qui lui pourrait de rester en sécurité loin de sa cible qu'il peut ensuite facilement ");
		System.out.println("abattre d'une flèche avec son arc. On dit qu'il pourrait toucher une pomme à 100m de distances.");
		System.out.println("Le magicien maitrise les éléments à la perfection. ");
		System.out.println("Il prend donc un malin plaisir à jeter des boules de feu sur ses ennemis pour les entendre criéer de douleurs à cause des flammes.");
		System.out.println();
	}
	public static void credit(){
		System.out.println("Il y a eu qu'un vaincqueur évidement mais nous espérons que le jeu vous à plut à vous deux.");
		System.out.println();
		System.out.println("Hésiter pas à rejouer et essayer les diverses configurations;");
		System.out.println();
		System.out.println("                    FIN                       ");
		System.out.println();
		System.out.println("-------------------CREDITS--------------------");
		System.out.println();
		System.out.println("          D'après une idée originale de:      ");
		System.out.println("             Roland Ducros                    ");
		System.out.println("             Aurélien Tournade                ");
		System.out.println("             Benoit Deléglise                 ");
		System.out.println();
		System.out.println("          Programmer par:                     ");
		System.out.println("             Roland Ducros                    ");
		System.out.println("             Aurélien Tournade                ");
		System.out.println("             Benoit Deléglise                 ");
		System.out.println();
		System.out.println("----------------------------------------------");

	}
}