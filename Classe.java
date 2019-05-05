abstract class Classe {
	protected String nom;
	protected String NOM_1 = "Paladin";
	protected String NOM_2 = "Guerrier";
	protected String NOM_3 = "Archer";
	protected String NOM_4 = "Magicien";
	protected boolean attaqueEpee;
	protected boolean attaqueLance;
	protected boolean attaqueArc;
	protected boolean attaqueFeu;

	public Classe() {
		this.nom = null;
		this.attaqueEpee = false;
		this.attaqueLance = false;
		this.attaqueArc = false;
		this.attaqueFeu = false;

	}


	public String getNom() {
		return this.nom;
	}

	public boolean getAttaqueEpee() {

		return this.attaqueEpee;
	}

	public boolean getAttaqueLance() {

		return this.attaqueLance;
	}

	public boolean getAttaqueArc() {

		return this.attaqueArc;
	}

	public boolean getAttaqueFeu() {
		return this.attaqueFeu;
	}
}