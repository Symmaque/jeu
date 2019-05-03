abstract class Race{
	protected String nom;
	protected final static  String NOM_1 = "Elfe";
    protected final static  String NOM_2 = "Homme";
    protected final static  String NOM_3 = "Nain";
    protected final static  String NOM_4 = "Orque";
	protected int bonusforce;
	protected int bonusintelligence;
	protected int bonusvie;
	protected int bonusdexterite;
    
	public Race(){
	    this.nom = null;
	    this.bonusdexterite =0;
	    this.bonusforce = 0;
	    this.bonusintelligence =0;
	    this.bonusvie = 0;

        
	}
	public String getNom(){
		return this.nom;
	}
	public int getBonusforce(){
		return this.bonusforce;
	}

	public int getBonusintelligence() {
		return bonusintelligence;
	}

	public int getBonusvie() {
		return bonusvie;
	}

	public int getBonusdexterite() {
		return bonusdexterite;
	}
}

