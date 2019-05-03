import java.util.Scanner;
class Creation{
	public void personnage(){
		Scanner sc= new Scanner(System.in);
		for(int i=1; i<=2; i++){
			System.out.println("Joueur "+i+" Entrez votre nom svp: ");
			String nom= sc.nextLine();
			System.out.println(" Choississez une classe parmis ... ");
			String classe= sc.nextLine();
			System.out.println(" choississez une race parmis ...");
			String race= sc.nextLine();
		}
	}
}
