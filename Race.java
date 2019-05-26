abstract class Race{
	String nom;
	final static  String NOM_1 = "Elfe";
    final static  String NOM_2 = "Homme";
    final static  String NOM_3 = "Nain";
    final static  String NOM_4 = "Orc";
	int bonusforce;
	int bonusintelligence;
	int bonusvie;
	int bonusdexterite;
    
	Race(){
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

	public int getBonusdexterite() { return bonusdexterite; }
}

