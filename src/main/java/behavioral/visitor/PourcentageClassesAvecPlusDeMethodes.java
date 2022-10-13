package behavioral.visitor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class PourcentageClassesAvecPlusDeMethodes {
	
	CompilationUnit parse;
	 
	 public static TypeDeclarationVisitor visitor = new TypeDeclarationVisitor();
	 
	 
   public PourcentageClassesAvecPlusDeMethodes (CompilationUnit parse) {
   	this.parse = parse;
		parse.accept(visitor);
   }
	
	private static Map<TypeDeclaration, Integer> classesNumberOfMethods(CompilationUnit parse) {
		TypeDeclarationVisitor typeDeclarationVisitor = new TypeDeclarationVisitor();
		parse.accept(typeDeclarationVisitor);

		HashMap<TypeDeclaration, Integer> res = new HashMap<>();

		for (TypeDeclaration typeDeclaration : typeDeclarationVisitor.getTypes()) {
			
				res.put(typeDeclaration, typeDeclaration.getMethods().length);
		}
		return res;
	}

}
