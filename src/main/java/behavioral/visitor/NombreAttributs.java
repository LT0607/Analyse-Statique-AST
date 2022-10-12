package behavioral.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class NombreAttributs {
	
     CompilationUnit parse;
	 public static TypeDeclarationVisitor visitor = new TypeDeclarationVisitor();
	 
	 public NombreAttributs(CompilationUnit parse) {
		 this.parse = parse;
		 parse.accept(visitor);
	 }
	 
	 public static double NombreTotalAttributs () {
		 double NbrAttributs = 0;
		 for (TypeDeclaration node : visitor.getTypes()) {
			 NbrAttributs = node.getFields().length;
		 }
		 return NbrAttributs;
	 }
	 
	 
}
