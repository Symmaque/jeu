import java.util.Scanner;
class Jeu{
	public static void main (String [] arg){
		Scanner sc= new Scanner(System.in);


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
				System.out.println("Que voulez vous faire ? se deplacer, se soigner, attaquer, se proteger ? ");
				switch (sc.nextLine()) {
					case "se deplacer":
						p.bougeJoueur(joueur1, joueur2);
						affichePlateau(p);
						action--;
						break;
					case "se soigner":
						p.getPersonnage1().soigner(joueur1.getClasse().getbonusvie());
						affichePlateau(p);
						action--;
						break;
					case "attaquer":
					case "se proteger":
						affichePlateau(p);
						action--;
						break;
				}
			}
			action=2;
			while(action != 0){
				System.out.println("Que voulez vous faire ? se deplacer, se soigner, attaquer, se proteger ? ");
				switch (sc.nextLine()) {
					case "se deplacer":
						p.bougeJoueur(joueur2, joueur1);
						affichePlateau(p);
						action -= 1;
						break;
					case "se soigner":
						p.getPersonnage2().soigner(joueur2.getClasse().getBonusvie());
						affichePlateau(p);
						action -= 1;
						break;
					case "attaquer":
					case "se proteger":
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
		int point= 24;
		int force=0;
		int dexterite=0;
		int intelligence=0;
		int vie=0;
			System.out.println("Joueur "+ordre+" Entrez votre nom: ");
			String nom= sc.nextLine();
			System.out.println(" Choississez une classe");
			System.out.println("Paladins(1)");
			System.out.println("Guerriers(2)");
			System.out.println("Archers(3)");
			System.out.println("Magiciens(4)");
			//Classe classe= new Classe(sc.nextLine());
			System.out.println(" Voulez-vous jouer avec ");
			System.out.println("un Elfe(1)");
		    System.out.println("un Homme(2)");
		    System.out.println("un Nain(3)");
		    System.out.println("un Orque(4)");

		switch(sc.nextInt()){
			case 1:
				race = new Elfe();
				System.out.println(nom+" combat avec un "+race.getNom());
				break;

			case 2:
				race = new Homme();
				System.out.println(nom+" combat avec un "+race.getNom());
				break;

			case 3:
				race = new Nain();
				System.out.println(nom+" combat avec un "+race.getNom());
				break;

			case 4:
				race = new Orque();
				System.out.println(nom+" combat avec un "+race.getNom());
				break;
			default:
				race = null;
				System.out.println("Vous n'avez pas de race");
		}


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
