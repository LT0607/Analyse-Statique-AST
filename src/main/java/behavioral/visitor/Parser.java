package behavioral.visitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
//import visitors.TypeDeclarationVisitor;

public class Parser {
	
	
	public static final String projectPath = "C:\\Users\\Lydia\\eclipse-workspace\\tp";
	public static final String projectSourcePath = projectPath + "\\src";
	public static final String jrePath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\rt.jar";
	
	 static NombreDeClasse nombre_de_classe;
	 static NombreDeMethodes nombre_de_methodes;
	 static NombreDePackages nombre_de_packages;
	 static NombreAttributs nombre_d_attributs;
	 
	public static void main(String[] args) throws IOException {

		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);
		
		
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			//System.out.println(content);

		    CompilationUnit parse = parse(content.toCharArray());
		    
		     nombre_de_classe = new NombreDeClasse(parse);
		     nombre_de_methodes = new NombreDeMethodes(parse);
		     nombre_de_packages = new NombreDePackages(parse);
		     nombre_d_attributs = new NombreAttributs (parse);
			// print methods info
			//printMethodInfo(parse);
			
			
			// print variables info
			//printVariableInfo(parse);
			
			//print method invocations
			//printMethodInvocationInfo(parse);

			//printClass(parse);

		}
		
		//**** R�ponse � la question 1
		//System.out.println("Le nombre total de classes est "+ NombreDeClasse.NombreTotalDeClasses());
		
		//**** R�ponse � la question 3
		//System.out.println("Le nombre total de m�thodes est "+ nombre_de_methodes.NombreTotalDeMethodes());
		 
		//** R�ponse � la question 5	
		double nombre_moyen_methodes = 0;
		nombre_moyen_methodes = nombre_de_methodes.NombreTotalDeMethodes() / NombreDeClasse.NombreTotalDeClasses();
		//System.out.println("nombre moyen de methodes : "+ nombre_moyen_methodes);
		
		//** R�ponse � la question 4 
	    //System.out.println("Le nombre total de packages est "+ nombre_de_packages.NombreTotalDePackages());
		
		//** R�ponse � la question 7
		//System.out.println("Le nombre total d'attributs est "+ NombreAttributs.NombreTotalAttributs());
		double nombre_moyen_attributs = 0;
		nombre_moyen_attributs = NombreAttributs.NombreTotalAttributs() / NombreDeClasse.NombreTotalDeClasses();
		//System.out.println("Le nombre moyen d'attributs par classe est "+ nombre_moyen_attributs);
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

				for (MethodInvocation methodInvocation : visitor2.getMethods()) {
					System.out.println("method " + method.getName() + " invoc method "
							+ methodInvocation.getName());
				}

			}
		}

}