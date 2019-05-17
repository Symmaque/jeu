import java.util.Scanner;
class Plateau{
	private char [][] monde;
	private Personnages j1;
	private Personnages j2;
	private int nbrrocher; 
	
	public Plateau(Personnages joueur1, Personnages joueur2, int rocher){
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
		for(int i=0; i<rocher;i++){
			this.monde[5+((int)Math.random()*9)][5+((int)Math.random()*9)]='O';
		}                                                                              //beug à corriger.
		
		this.j1= joueur1;
		this.j2= joueur2;
		
		this.monde[2][2]=(char) (j1.getVie()/10);
		this.monde[2][3]=(char) (j1.getVie()%10);
		this.monde[2][5]='/';
		this.monde[2][6]=(char) (j1.getViemax()/10);
		this.monde[2][7]=(char) (j1.getViemax()%10);
		this.monde[2][12]=(char) (j2.getVie()/10);
		this.monde[2][13]=(char) (j2.getVie()%10);
		this.monde[2][14]='/';
		this.monde[2][15]=(char) (j2.getViemax()/10);
		this.monde[2][16]=(char) (j2.getViemax()%10);
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
		miseajourPlateau(joueurquibouge, joueurfixe);
	}

	public void attaqueJoueur(Personnages joueurquiattaque, Personnages joueurquisedefend) {
		boolean attaqueReussie;
		// definir un booleen porteeSuffisante et ne pas oublier de l'inclure ds la fonction personnage.attaquer
		//porteesuffisante(joueurquiattaque.getClasse().getNom(), joueurquiattaque.getX(), joueurquiattaque.getY(), joueurquisedefend.getX(), joueurquisedefend.getY());



		attaqueReussie = ((Math.random() - ((Math.pow(joueurquiattaque.getDexterite(), 1/3)-Math.pow(joueurquisedefend.getDexterite(), 1/3)) / Math.pow((0.8*joueurquiattaque.getDexterite()) + joueurquisedefend.getDexterite(), 1/2))) > 0.5);


		joueurquisedefend.perteVie(joueurquiattaque.attaquer(attaqueReussie));

	}

	public void soignerJoueur(Personnages joueur){
		joueur.soigner(joueur.getRace().getBonusvie()+10);
		System.out.println(joueur.getNom() + " a maintenant " + joueur.getVie() + " de vie.");
	}
	
	public void miseajourPlateau(Personnages joueurquibouge, Personnages joueurfixe){
		if (this.j1.equals(joueurquibouge)){
			this.monde[joueurquibouge.getX()][joueurquibouge.getY()]='1';
		} else{
			this.monde[joueurquibouge.getX()][joueurquibouge.getY()]='2';
		}
	}

	/*
	public boolean porteesuffisante(String nomClasse, int x1, int y1, int x2, int y2){

		switch(nomClasse){
			case "Archer":
				if x1

		}



	}

	 */
	public char[][] getPlateau(){
		return this.monde;
	}
	
	public Personnages getPersonnage1(){
		return this.j1;
	}
	
	public Personnages getPersonnage2(){
		return this.j2;
	}
}
