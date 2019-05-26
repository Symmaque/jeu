import java.util.Scanner;
class Plateau{
	private final char [][] monde;
	private final Personnages j1;
	private final Personnages j2;
	
	public Plateau(Personnages joueur1, Personnages joueur2){
		this.monde= new char[20][20];
		for(int i=0; i<20; i++){
			for(int j=0; j<20;j++){
				this.monde[i][j]='-';
			}
		}
		for(int i=5; i<15; i++){
			for(int j=5; j<15;j++){
				this.monde[i][j]=' ';
			}
		}
		this.monde[joueur1.getX()][joueur1.getY()]='1';
		this.monde[joueur2.getX()][joueur2.getY()]='2';

		this.j1= joueur1;
		this.j2= joueur2;

	}
	
	public void bougeJoueur(Personnages joueurquibouge, Personnages joueurfixe){
		Scanner sc= new Scanner(System.in);
		System.out.println("Ou voulez vous aller, haut (0), bas (1), droite (2), gauche (3), rester (4) ? ");
		int a= sc.nextInt();
		boolean mvtpossible= true;
		if(a==0){  //haut
				if(this.monde[joueurquibouge.getX()-1][joueurquibouge.getY()]==this.monde[joueurfixe.getX()][joueurfixe.getY()]){
					mvtpossible=false;
				}
			} else if(a==1){   //bas
				if(this.monde[joueurquibouge.getX()+1][joueurquibouge.getY()]==this.monde[joueurfixe.getX()][joueurfixe.getY()]){
					mvtpossible=false;
				}
			} else if(a==2){
				if(this.monde[joueurquibouge.getX()][joueurquibouge.getY()-1]==this.monde[joueurfixe.getX()][joueurfixe.getY()]){
					mvtpossible=false;
				}
			} else if(a==3){
				if(this.monde[joueurquibouge.getX()][joueurquibouge.getY()+1]==this.monde[joueurfixe.getX()][joueurfixe.getY()]){
					mvtpossible=false;
				}
			}
		this.monde[joueurquibouge.getX()][joueurquibouge.getY()]=' ';
		
		joueurquibouge.bouge(mvtpossible, a);
		miseajourPlateau(joueurquibouge);
	}

	public void attaqueJoueur(Personnages attaquant, Personnages defenseur,int protection, int contre) {

		boolean attaqueNonEsquivee = ((Math.random() - ((Math.pow(attaquant.getDexterite(), 1/3)-Math.pow(defenseur.getDexterite(), 1/3)) / Math.pow((0.8*attaquant.getDexterite()) + defenseur.getDexterite(), 1/2))) > 0.65);
		boolean attaqueReussie = ((porteesuffisante(attaquant, defenseur)) && (attaqueNonEsquivee));



		defenseur.perteVie(attaquant.attaquer(attaqueReussie), protection);
		if(contre!=0) {
			System.out.println(defenseur.getNom() + " a contre-attaqué.");
			attaquant.perteVie(contre, 0);
			System.out.println(attaquant.getNom() + " a maintenant " + attaquant.getVie() + " de vie.");
		}
	}

	public void soignerJoueur(Personnages joueur){
		joueur.soigner((int)(Math.random()*joueur.getRace().getBonusvie())+5);
		System.out.println(joueur.getNom() + " a maintenant " + joueur.getVie() + " de vie.");
	}
	
	public void miseajourPlateau(Personnages joueurquibouge){
		if (this.j1.equals(joueurquibouge)){
			this.monde[joueurquibouge.getX()][joueurquibouge.getY()]='1';
		} else{
			this.monde[joueurquibouge.getX()][joueurquibouge.getY()]='2';
		}
	}


	public boolean porteesuffisante(Personnages attaquant, Personnages defenseur){

		switch(attaquant.getClasse().getNom()){
			case "Archer":
				return attaquant.porteeArcher(attaquant.getX(),attaquant.getY(),defenseur.getX(), defenseur.getY());


			case "Paladin":
				return attaquant.porteePaladin(attaquant.getX(),attaquant.getY(),defenseur.getX(), defenseur.getY());


			case "Guerrier":
				return attaquant.porteeGuerrier(attaquant.getX(),attaquant.getY(),defenseur.getX(), defenseur.getY());


			case "Magicien":
				return attaquant.porteeMagicien(attaquant.getX(),attaquant.getY(),defenseur.getX(), defenseur.getY());


			default:
				return false;
		}

	}

	public void affichePortee(Personnages attaquant){
		switch(attaquant.getClasse().getNom()){
			case "Archer":
				for(int i=5; i<15; i++) {
					for (int j = 5; j < 15; j++) {
						if (attaquant.porteeArcher(attaquant.getX(),attaquant.getY(),i,j)) {
							this.monde[i][j] = '+';
						}

					}
				}
				break;

			case"Paladin":
				for(int i=5; i<15; i++) {
					for (int j = 5; j < 15; j++) {
						if (attaquant.porteePaladin(attaquant.getX(),attaquant.getY(),i,j)) {
							this.monde[i][j] = '+';
						}

					}
				}
				break;

			case "Guerrier":
				for(int i=5; i<15; i++) {
					for (int j = 5; j < 15; j++) {
						if (attaquant.porteeGuerrier(attaquant.getX(),attaquant.getY(),i,j)) {
							this.monde[i][j] = '+';
						}

					}

				}

				break;

			case "Magicien":
				for(int i=5; i<15; i++) {
					for (int j = 5; j < 15; j++) {
						if (attaquant.porteeMagicien(attaquant.getX(),attaquant.getY(),i,j)) {
							this.monde[i][j] = '+';
						}

					}
				}
				break;

		}
	}

	public void desaffichePortee(Personnages joueur1, Personnages joueur2){
		for(int i=5; i<15; i++){
			for(int j=5; j<15;j++){
				this.monde[i][j]=' ';
			}
		}
		this.monde[joueur1.getX()][joueur1.getY()]='1';
		this.monde[joueur2.getX()][joueur2.getY()]='2';

	}


	public char[][] getPlateau(){

		return this.monde;
	}

}
