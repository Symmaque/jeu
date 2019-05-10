import java.util.Scanner;
class Jeu{
	public static void main (String [] arg){
		Scanner sc= new Scanner(System.in);
		System.out.println("Bienvenue dans x, vous avez décidé de vous battre à mort dans une arène pour savoir qui de vous deux est le meilleur. Bien, nous allons tacher de le découvrir");
		System.out.println();
		System.out.println("Pour commencer, vous allez créer vous personnages. Mais réflechissez bien vos choix determineront vos compétences à remporter le combat");
		System.out.println();
		/*Personnages test= new Personnages("moi", "homme", "guerrier", "epee", 10, 1, 2, 4);
		System.out.println(test);*/
		//ajouter info création de personnage
		Personnages joueur1 =Creationpers(1, 9, 5);
		System.out.println(joueur1);
		System.out.println();
		Personnages joueur2 =Creationpers(2, 10, 14);
		System.out.println(joueur2);
		System.out.println();
		Plateau p= new Plateau(joueur1, joueur2, 10);
		affichePlateau(p);


		//ajouter info sur le déroulement du jeu
		int action=2;
		String reponse;
		while(p.getPersonnage1().getVie()>0 && p.getPersonnage2().getVie()>0){
			while(action != 0){
				System.out.println(p.getPersonnage1().getNom()+", que voulez vous faire ? se deplacer (0), se soigner (1), attaquer (2), se proteger (3)? ");
				switch (sc.nextInt()) {
					case 0:
						p.bougeJoueur(joueur1, joueur2);
						affichePlateau(p);
						action--;
						break;
					case 1:
						//p.getPersonnage1().soigner(joueur1.race.bonusvie().getbonusvie());
						affichePlateau(p);
						action--;
						break;
					case 2:
					case 3:
						affichePlateau(p);
						action--;
						break;
				}
			}
			action=2;
			while(action != 0){
				System.out.println(p.getPersonnage2().getNom()+", que voulez vous faire ? se deplacer (0), se soigner (1), attaquer (2), se proteger (3) ? ");
				switch (sc.nextInt()) {
					case 0:
						p.bougeJoueur(joueur2, joueur1);
						affichePlateau(p);
						action -= 1;
						break;
					case 1:
						//p.getPersonnage2().soigner(joueur2.getClasse().getBonusvie());
						affichePlateau(p);
						action -= 1;
						break;
					case 2:
					case 3:
						affichePlateau(p);
						action -= 1;
						break;
				}
			}
			action=2;
		}
		System.out.println("Le jeu est fini");
		
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
			System.out.println(" Voulez-vous jouer avec ");
			System.out.println();
			System.out.println("un Elfe(1)");
			System.out.println("L'elfe a un bon bonus de dexterité et sait se montrer intelligent. Cependant, il est assez faible malgré une longévitée remarquable.");
			System.out.println();
			System.out.println("un Homme(2)");
			System.out.println("L'homme a su devenir un des être les plus intelligent de se monde. Il est assez agile dans tout les domaines mais son égoïsme le perdra sans doute.");
			System.out.println();
			System.out.println("un Nain(3)");
			System.out.println("Le nain ne pense que à l'or et à la bière. Il est très résistant et possède une force colossale. Il sait se montrer ingénieux quand il s'agit de combat mais il préferera toujours la force brute à la finesse.");
		 	System.out.println();
			System.out.println("un Orque(4)");
			System.out.println("L'orque serait capable de dévorer les être de sa propre race sans doute le reflet d'un manque d'éducation. Cependant il pourrait brisser un rocher avec son poing sans trop de problème. Le pire avec les orques ... On n'a jamais eu assez de flèches pour le tuer.");
			System.out.println();
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
					race = new Orque();
					break;

				default:
					race = null;

			}
			System.out.println(nom+" combat avec un "+race.getNom());
			System.out.println();
			System.out.println(" Choississez une classe");
			System.out.println();
			System.out.println("Paladins(1)");
			System.out.println();
			System.out.println("Guerriers(2)");
			System.out.println();
			System.out.println("Archers(3)");
			System.out.println();
			System.out.println("Magiciens(4)");
			System.out.println();

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
