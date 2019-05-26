abstract class Classe {
	String nom;
	int attaque;
	String arme;
	final String NOM_1 = "Paladin";
	final String NOM_2 = "Guerrier";
	final String NOM_3 = "Archer";
	final String NOM_4 = "Magicien";


	Classe() {
		this.nom = null;
		this.arme = null;
		this.attaque = 0;

	}


	public String getNom() {
		return this.nom;
	}


	public String getArme() {
		return this.arme;
	}
}