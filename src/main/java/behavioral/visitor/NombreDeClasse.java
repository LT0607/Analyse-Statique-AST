package behavioral.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class NombreDeClasse {
	
	 CompilationUnit parse;
	 
	 public static TypeDeclarationVisitor visitor = new TypeDeclarationVisitor();
	 
	 
    public NombreDeClasse (CompilationUnit parse) {
    	this.parse = parse;
		parse.accept(visitor);
    }
	public static double NombreTotalDeClasses () {
		 double NbrClass = 0;
		
		for (TypeDeclaration node : visitor.getTypes()) {
		//	System.out.println("la classe parsée est : " + node.getName());
			
			NbrClass++;
		}
		return NbrClass;
	}
	
	
}
