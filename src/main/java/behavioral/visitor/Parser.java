package behavioral.visitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
//import visitors.TypeDeclarationVisitor;

public class Parser {
	
	
	public static final String projectPath = "C:\\Users\\Lydia\\eclipse-workspace\\project1";
	public static final String projectSourcePath = projectPath + "\\src";
	public static final String jrePath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\rt.jar";
	
	 static NombreDeClasse nombre_de_classe;
	 static NombreDeMethodes nombre_de_methodes;
	 static NombreDePackages nombre_de_packages;
	 static NombreAttributs nombre_d_attributs;
	 static PourcentageClassesAvecPlusDeMethodes pourcentage_classes_methodes;
	 static PourcentageClassesAvecPlusAttributs pourcentage_classes_attributs;
	 static X_Methodes x_methodes;
	 static GraphCall graph;
	
	 //***tp2
	 static Couplage couplage;
	 static Couplage2 couplage2;
	 //********
	 Set<String> setLink = new HashSet<String>();
	 
	 public static Visitor visitor = new Visitor();
	 
	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);
		
		
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			//System.out.println(content);

		    CompilationUnit parse = parse(content.toCharArray());
		     parse.accept(visitor);
		     nombre_de_classe = new NombreDeClasse(parse);
		     nombre_de_methodes = new NombreDeMethodes(parse);
		     nombre_de_packages = new NombreDePackages(parse);
		     nombre_d_attributs = new NombreAttributs (parse);
		     pourcentage_classes_methodes = new PourcentageClassesAvecPlusDeMethodes (nombre_de_classe.NombreTotalDeClasses(), nombre_de_methodes.NombreTotalDeMethodes());
		     pourcentage_classes_attributs = new PourcentageClassesAvecPlusAttributs (nombre_de_classe.NombreTotalDeClasses(), nombre_de_methodes.NombreTotalDeMethodes());
		     x_methodes = new X_Methodes();
		     graph = new GraphCall();
		   //*** Graphe d'appel
		   //System.out.println("node");
		     graph.collectGraphData( parse);  
		   //*** Couplage
		    
		     couplage = new Couplage();
		     couplage2 = new Couplage2();
		     couplage2.CallG(parse);
		     
		}
		
		//**** Réponse à la question 1
		//System.out.println("");
		//System.out.println("Réponse à la question 1");
		//System.out.println("Le nombre total de classes est "+ NombreDeClasse.NombreTotalDeClasses());
		
		//**** Réponse à la question 3
		//System.out.println("******************************************************");
		//System.out.println("");
		//System.out.println("Réponse à la question 3");
		//System.out.println("Le nombre total de méthodes est "+ nombre_de_methodes.NombreTotalDeMethodes());
		 
		//** Réponse à la question 4 
		//System.out.println("******************************************************");
		//System.out.println("");
		//System.out.println("Réponse à la question 4");
	    //System.out.println("Le nombre total de packages est "+ nombre_de_packages.NombreTotalDePackages());
		
		//** Réponse à la question 5	
	    //System.out.println("******************************************************");
	    //System.out.println("");
		//System.out.println("Réponse à la question 5");
		double nombre_moyen_methodes = 0;
		nombre_moyen_methodes = nombre_de_methodes.NombreTotalDeMethodes() / NombreDeClasse.NombreTotalDeClasses();
		//System.out.println("nombre moyen de methodes : "+ nombre_moyen_methodes);
		
		//** Réponse à la question 7
		//System.out.println("******************************************************");
		//System.out.println("");
		//System.out.println("Réponse à la question 7");
		//System.out.println("Le nombre total d'attributs est "+ NombreAttributs.NombreTotalAttributs());
		
		double nombre_moyen_attributs = 0;
		nombre_moyen_attributs = NombreAttributs.NombreTotalAttributs() / NombreDeClasse.NombreTotalDeClasses();
		//System.out.println("Le nombre moyen d'attributs par classe est "+ nombre_moyen_attributs);
		
		
		//Afficher la liste des methodes par classe
	     /*  int totalMethodsNbr =0;
		   for (String key : visitor.getMethods().keySet())
	            totalMethodsNbr +=  visitor.getMethods().get(key).length;
	        for (var entry : visitor.getMethods().entrySet()) {
	            System.out.println("key : "+entry.getKey()+"\n");
	            for (MethodDeclaration method : entry.getValue()) {
	                System.out.println(method.getName().toString());
	            }
	        }*/
	     
	    // Afficher la liste des attributs par classe    
		// System.out.println("attributs=========================================================");	
	     //   visitor.getParentVariables();
	        
	    //** Réponse à la question 8    
		//System.out.println("******************************************************");
		//System.out.println("");
		//System.out.println("Réponse à la question 8");
	    //System.out.println("Le 10% des classes qui ont le plus de méthodes : "+ pourcentage_classes_methodes.getClasses10Methods());   
	    
	   
	   //** Réponse à la question 9    
	    //System.out.println("******************************************************");
	    //System.out.println("");
	    //System.out.println("Réponse à la question 9");
	    //System.out.println("Le 10% des classes qui ont le plus d'attributs "+ pourcentage_classes_attributs.getClasses10Attirbutes());
	        
	   //** Réponse à la question 11
	   // System.out.println("******************************************************");
	   //System.out.println("");
	   //System.out.println("Réponse à la question 9");
	        int n = 8;
	   //System.out.println("Les classes possédant plus de "+n+" methodes"+ x_methodes.getClassesXMethods(n));  
	    
	   //***** Couplage
	        Set<String> s = new HashSet<String>();
	        Set<String> s2 = new HashSet<String>();
		      
		     s2 = couplage2.RetourSet();
		     System.out.println(s2);
		     double toutes = couplage.ToutesRelations(s2);
		     double deux = couplage.CoupleRelation(s2, "AA", "BB");
		     System.out.println("Nombre de toutes les relations "+ toutes);  
		     System.out.println("Nombre de relations entre AA et BB "+ deux);
	         System.out.println("Couplage entre AA et BB "+ couplage.CalculCouplage(deux, toutes));
	         
	         couplage.GraphPond(s2);
	}

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				// System.out.println(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}

	// create AST
	private static CompilationUnit parse(char[] classSource) {
		ASTParser parser = ASTParser.newParser(AST.JLS4); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		parser.setUnitName("");
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);
		
		return (CompilationUnit) parser.createAST(null); // create and parse
	}

	// navigate method information
	public static void printMethodInfo(CompilationUnit parse) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods()) {
			System.out.println("Method name: " + method.getName()
					+ " Return type: " + method.getReturnType2());
		}

	}
       
	

	// navigate variables inside method
	public static void printVariableInfo(CompilationUnit parse) {

		MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
		parse.accept(visitor1);
		for (MethodDeclaration method : visitor1.getMethods()) {

			VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
			method.accept(visitor2);

			for (VariableDeclarationFragment variableDeclarationFragment : visitor2
					.getVariables()) {
				System.out.println("variable name: "
						+ variableDeclarationFragment.getName()
						+ " variable Initializer: "
						+ variableDeclarationFragment.getInitializer());
			}

		}
	}

	static void printClass(CompilationUnit cu) {
		TypeDeclarationVisitor visitor = new TypeDeclarationVisitor();
		cu.accept(visitor);
		for (TypeDeclaration node : visitor.getTypes()) {
			System.out.println("Class name : "+node.getName()+" at line " +	cu.getLineNumber(node.getStartPosition()));
		}
	}
	

	// navigate method invocations inside method
		public static void printMethodInvocationInfo(CompilationUnit parse) {

			MethodDeclarationVisitor visitor1 = new MethodDeclarationVisitor();
			parse.accept(visitor1);
			for (MethodDeclaration method : visitor1.getMethods()) {

				MethodInvocationVisitor visitor2 = new MethodInvocationVisitor();
				method.accept(visitor2);

				for (MethodInvocation methodInvocation : visitor2.getMethodsInvocation()) {
					System.out.println("method " + method.getName() + " invoc method "
							+ methodInvocation.getName());
				}

			}
		}

}
