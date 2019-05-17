abstract class Classe {
	protected String nom;
	protected int attaque;
	protected String arme;
	protected String NOM_1 = "Paladin";
	protected String NOM_2 = "Guerrier";
	protected String NOM_3 = "Archer";
	protected String NOM_4 = "Magicien";
	protected boolean [][] portee;

	public Classe() {
		this.nom = null;
		this.arme = null;
		this.attaque = 0;
		this.portee = null;

	}


	public String getNom() {
		return this.nom;
	}


	public String getArme() {
		return this.arme;
	}
}