class Personnages{
	private final String nom;
	private final Race race;
	private final Classe classe;
	private final int force;
	private final int dexterite;
	private int vie; 
	private final int viemax;
	private final int intelligence;
	private int x;
	private int y;
	private final int attaque;
	private int action;
	
	public Personnages(String nom, Race race,Classe classe, int force, int dexterite, int vie, int intelligence, int x, int y){
		this.nom=nom;
		this.race=race;
		this.classe=classe;
		this.attaque = classe.attaque;
		this.force=force + this.race.getBonusforce() ;
		this.dexterite=dexterite + this.race.getBonusdexterite();
		this.vie=100 + vie + this.race.getBonusvie();
		this.viemax = 100 + vie + this.race.getBonusvie();
		this.intelligence=intelligence+ this.race.getBonusintelligence();
		this.x=x;
		this.y=y;


	}
	
	public void bouge(boolean mvtpossible, int a){
		if(mvtpossible){
			if(a==0 && (this.getX()-1)<=14){
				this.x--;
			} else if(a==1 && (this.getX()+1)>=5){
				this.x++;
			} else if(a==3 && (this.getY()-1)>=5){
				this.y--;
			} else if(a==2 && (this.getY()+1)<=14){
				this.y++;
			}else {
				System.out.println("Vous ne bougez pas");
			}
		} else {
			System.out.println("Vous ne bougez pas");
		}
	}

	
	public int getVie(){
		return this.vie;
	}


	public int getDexterite() {
		return dexterite;
	}

	public int getForce() {
		return force;
	}

	public int getIntelligence() {
		return intelligence;
	}


	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public Classe getClasse(){
		return this.classe;
	}

	public Race getRace() {
		return race;
	}

	public String getNom(){
	    return this.nom;
    }
	
	public void perteVie(int degats, int protection){
		this.vie= this.vie - (degats - degats*(protection/100));
	}
	
	public void soigner(int heal){
		int difference=this.viemax-this.vie;
		int a=(int)(Math.random()*40);
		if(difference>0 && this.getIntelligence()>=a){
			if(heal>=difference ){
				this.vie+= difference;
			} else{
				this.vie+= heal;
			}
		}else{
			System.out.println(this.getNom()+" a renversé sa potion et ne récupère donc pas de vie");
		}
	}

	public void setAction(int pointsAction){
		this.action = pointsAction;
	}

	public int getAction() {
		return action;
	}

	public void perteAction(int pertes){
		this.action = this.action - pertes;
	}

	public int attaquer(boolean attaquePossible){
		if(attaquePossible){
			System.out.println(this.getNom() + " attaque avec " + this.getClasse().getArme());
			return this.attaque + this.getForce();
		}
		System.out.println("L'attaque a été esquivée");
		return 0;
	}

	public boolean porteeArcher(int x1, int y1, int x2, int y2){
		int diffX = Math.abs((x1-x2));
		int diffY = Math.abs((y1-y2));
		return (((4<diffX)&&(diffX<10))||((4<diffY)&&(diffY<10)));
	}

	public boolean porteePaladin(int x1, int y1, int x2, int y2){
		int diffX = Math.abs((x1-x2));
		int diffY = Math.abs((y1-y2));
		return (((diffX<6)&&(diffY == 0))||((diffY<6)&&(diffX == 0)));
	}

	public boolean porteeGuerrier(int x1, int y1, int x2, int y2){
		int diffX = Math.abs((x1-x2));
		int diffY = Math.abs((y1-y2));
		return ((diffX<5)&&(diffY<5));
	}

	public boolean porteeMagicien(int x1, int y1, int x2, int y2){
		int diffX = Math.abs((x1-x2));
		int diffY = Math.abs((y1-y2));
		return (((diffX<7)&&(diffY<7))&&((diffX != 0)||(diffY != 0)));
	}

	public int seproteger(){
		int a= (this.getIntelligence()+this.getDexterite())/2;
		int b=  a/3;
		int c= (int) (Math.random()*a);
		if(c<=b){
			a=b;
		} else {
			a = c;
		}
		return a;
	}
	public int contreAttaque(int a){
		int contre=a%4;
		int b=this.getDexterite();
		int c=(int)(Math.random()*b*1.5);
			if(c>b){
				contre=0;
			}
		return contre;
	}



	public String toString(){
		return "Carte du personnage: Nom: "+ this.nom+ " ,Race: "+this.race.getNom()+" ,classe: "+this.classe.getNom()+" ,attribut: "+this.force+" en force, "+this.dexterite+" en dexterite, "+this.intelligence+" en intelligence, "+this.vie+" en vie.";
	}
	
}
