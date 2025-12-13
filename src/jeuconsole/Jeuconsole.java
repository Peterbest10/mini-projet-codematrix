package jeuconsole;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Jeuconsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         System.out.println("----------Bienvenue dans le jeu de CodeMatix-----------");
         
         Scanner sc =new Scanner(System.in);
         System.out.println("Entrer votre nom d'utilisateur: ");
         String nom=sc.nextLine();
         
         System.out.println("Entrer le nom de votre 1er personnage");
         String person1=sc.nextLine();
         
         System.out.println("Entrer le nom de votre 2em personnage");
         String person2=sc.nextLine();
         
         System.out.println("Entrer le nom de votre 3em personnage");
         String person3=sc.nextLine();
         
         Personnage personnage1=new Personnage(person1,100,20);
         System.out.println(personnage1.getNom()+" " +personnage1.getVie()+"fjjjj");
         

    
    }
    
}
