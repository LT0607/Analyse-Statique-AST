package behavioral.visitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Couplage {
	public static List <String> ListeClasses = new ArrayList<String>();

	public static Set<String> setLink = new HashSet<String>();
	
	//Calcul le nombre de toutes les relations entre les couples de méthodes de n'importe quelles deux classes de l'application
	public int ToutesRelations(Set<String> set) {
		int taille = set.size();
		return taille;
		
	}
	
	//Calcul le nombre de relation entre les méthodes de deux classes
	public int CoupleRelation(Set<String> set, String A, String B ) {
		
		String[] spl;
		int compteur = 0;
		
		
		 List<String> res = new ArrayList<>();
	        for (String i : set) {
	            if (i.contains(A+"->"+B) || i.contains(B+"->"+A)) {
	              //  res.add(i);
	                compteur++;
	            }
	        }
	        //System.out.println(res);
	        return compteur;
	    }
	
	  //Calcul le couplage
	public double CalculCouplage(double relations_deux, double relations_toutes) {
		double nbr = relations_deux/relations_toutes;
		double res = Math.round(nbr * 100.0) / 100.0;
		return res;
	}
	
	public void GraphPond(Set<String> set){

		System.out.println("\n****Graphe de couplage pondéré****\n");
		//List<String> res = new ArrayList<>();
        for (String i : set) {
        	String[] spl = null;
        	spl = i.split("->");
              // res.add(i);
        	String p1 = spl[0];
        	String[] p = spl[1].split("\r?\n|\r");
        	String p2 = p[0];
        	double n = CalculCouplage(CoupleRelation(set, p1, p2),ToutesRelations(set));
        	//System.out.println("**p1** " + p1);   
        	//System.out.println("**p2** " + p2);  
        	
            System.out.println(p1+"-"+n+"->"+p2+"\n");
            
        }
		
	}
	
}
