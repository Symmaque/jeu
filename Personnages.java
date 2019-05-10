import java.util.Scanner;
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
	
	public Personnages(String nom, Race race,Classe classe, int force, int dexterite, int vie, int intelligence, int x, int y){
		this.nom=nom;
		this.race=race;
		this.classe=classe;
		this.attaque = classe.attaque;
		this.force=force + this.race.getBonusforce() ;
		this.dexterite=dexterite + this.race.getBonusdexterite();
		this.vie=vie+ this.race.getBonusvie();
		this.viemax = this.vie;
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
		int difference=viemax-this.vie;
		if(this.vie<this.viemax){
			if(heal>=difference){
				this.vie=this.viemax;
			} else{
				this.vie=this.vie + heal;
			}
		}
	}

	public int attaquer(boolean attaquePossible){
		if(attaquePossible){
			return this.attaque + this.getForce();
		}
		System.out.println("L'attaque a échoué");
		return 0;
	}


	public String toString(){
		String s= "Carte du personnage: Nom: "+ this.nom+ " ,Race: "+this.race.getNom()+" ,classe: "+this.classe.getNom()+" ,attribut: "+this.force+" en force, "+this.dexterite+" en dexterite, "+this.intelligence+" en intelligence, "+this.vie+" en vie.";
		return s;
	}
	
}
