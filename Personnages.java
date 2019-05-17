class Personnages{
	private String nom;
	private Race race;
	private Classe classe;
	private int force;
	private int dexterite; 
	private int vie; 
	private int viemax;
	private int intelligence;
	private int x;
	private int y;
	private int attaque;
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
	public int getViemax(){
		return viemax;
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
	
	public void perteVie(int degats){
		this.vie= this.vie - degats;
	}
	
	public void soigner(int heal){
		int difference=this.viemax-this.vie;
		if(difference>0){
			if(heal>=difference && this.getIntelligence()>= (int)(Math.random()*60)){
				this.vie+= difference;
			} else{
				this.vie+= heal;
			}
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





	public String toString(){
		String s= "Carte du personnage: Nom: "+ this.nom+ " ,Race: "+this.race.getNom()+" ,classe: "+this.classe.getNom()+" ,attribut: "+this.force+" en force, "+this.dexterite+" en dexterite, "+this.intelligence+" en intelligence, "+this.vie+" en vie.";
		return s;
	}
	
}
