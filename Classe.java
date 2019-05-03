class Classe {
	private String nom;
	private int attaqueEpee;
	private int attaqueLance;
	private int attaqueArc;
	private int attaqueFeu;

	public Classe() {
		this.nom = null;
		this.attaqueEpee = 0;
		this.attaqueLance = 0;
		this.attaqueArc = 0;
		this.attaqueFeu = 0;

	}


	public String getNom() {
		return this.nom;
	}

	public int getAttaqueEpee() {

		return this.attaqueEpee;
	}

	public int getAttaqueLance() {

		return this.attaqueLance;
	}

	public int getAttaqueArc() {

		return this.attaqueArc;
	}

	public int getAttaqueFeu() {
		return this.attaqueFeu;
	}
}