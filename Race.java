class Race{
	private String nom;
    private int numeroRace;
	private int bonusforce;
	private int bonusintelligence;
	private int bonusvie;
	private int bonusdexterite;
	// Elfes, hommes, nains,Orques
    
	public Race(int choix){
		this.numeroRace = choix;
        switch(choix){
            case 1:
            this.nom = "Elfe";
            System.out.println("Vous combattez avec un "+this.nom);
            break;
            
            case 2:
            this.nom = "Homme";
            System.out.println("Vous combattez avec un "+this.nom);
            break;
            
            case 3:
            this.nom = "Nain";
            System.out.println("Vous combattez avec un "+this.nom);            
            break;
            
            case 4:
            this.nom = "Orque";
            System.out.println("Vous combattez avec un "+this.nom);
            break;
        }
        
        
	}
	public String getNom(){
		return this.nom;
	}
    
    
    
}

