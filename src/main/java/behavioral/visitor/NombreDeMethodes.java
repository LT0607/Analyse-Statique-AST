package behavioral.visitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import behavioral.visitor.MethodDeclarationVisitor;

public class NombreDeMethodes {

	 static CompilationUnit parse;
	 
	 public static MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
	
	 public NombreDeMethodes (CompilationUnit parse) {
	    	NombreDeMethodes.parse = parse;
			parse.accept(visitor);
	    }
	 
	 public double NombreTotalDeMethodes () {
		    double NbrMethode = 0;
			for (MethodDeclaration node : NombreDeMethodes.visitor.getMethods()) {
				//System.out.println("la méthode parsée est : " + node.getName());
				NbrMethode++;
			}
			return NbrMethode;
		   
		}
	 
}
