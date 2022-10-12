package behavioral.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class NombreDeLignes {

 CompilationUnit parse;	 
 public static MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
 
 public NombreDeLignes (CompilationUnit parse) {
 	this.parse = parse;
		parse.accept(visitor);
 }
 
 public static double NombreTotalDeLignes () {
	 double NbrLignes = 0;
	

	return NbrLignes;
}
 
	
}
