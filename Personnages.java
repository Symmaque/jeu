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
	
	public Personnages(String nom, Race race,Classe classe, int force, int dexterite, int vie, int intelligence, int x, int y){
		this.nom=nom;
		this.race=race;
		this.classe=classe;
		this.force=force;
		this.dexterite=dexterite;
		this.vie=vie;
		this.viemax = vie;
		this.intelligence=intelligence;
		this.x=x;
		this.y=y;
	}
	
	public void bouge(boolean mvtpossible, String a){
		if(mvtpossible){
			if(a.equals("haut") && (this.getX()-1)<=14){
				this.x--;
			} else if(a.equals("bas") && (this.getX()+1)>=5){
				this.x++;
			} else if(a.equals("gauche") && (this.getY()-1)>=5){
				this.y--;
			} else if(a.equals("droite") && (this.getY()+1)<=14){
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
		return this.viemax;
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
	
	public void perteVie(int degat){
		this.vie= this.vie - degat;
	}
	
	public void soigner(int heal){
		int difference=this.viemax-this.vie;
		if(this.vie<this.viemax){
			if(heal>=difference){
				this.vie=this.viemax;
			} else{
				this.vie=this.vie + heal;
			}
		}
	}
	
	public String toString(){
		String s= "Carte du personnage: Nom: "+ this.nom+ " ,Race: "+this.race.getNom()+" ,classe: "+this.classe.getNom()+" ,attribut: "+this.force+"force,"+this.dexterite+"dexterite"+this.intelligence+"intelligence"+this.vie+"vie";
		return s;
	}
	
}
