package behavioral.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class PourcentageClassesAvecPlusDeMethodes {
	
   List<String> classes10methods;
   int maxMethods, maxClasses;
	 

public PourcentageClassesAvecPlusDeMethodes (double totalClassesNbr, double totalMethodsNbr) {
       double doubleNumber = totalClassesNbr*10.0/100;
       maxClasses = (int) doubleNumber;
       if (maxClasses == 0 || (doubleNumber - maxClasses) >= 0.5 ) maxClasses += 1;

       doubleNumber = totalMethodsNbr*10.0/100;
       maxMethods = (int) doubleNumber;
       if (maxMethods == 0 || (doubleNumber - maxMethods) >= 0.5 ) maxMethods += 1;
   }
   

public List<String> getClasses10Methods() {
       classes10methods =  new ArrayList<>();
       Map<String, MethodDeclaration[]> methods = Visitor.getMethods();

       int max = 0;
       String name = "";
       for (int i = 1; i <= maxClasses; i++) {
           for (var entry : methods.entrySet()) {
               if (max <= entry.getValue().length && !name.equals(entry.getKey()) ) {
                   max = entry.getValue().length;
                   name = entry.getKey();
               }
           }
           classes10methods.add(name);
           max = 0;
           methods.remove(name);
       }
       //classes10methods.forEach(classeName-> System.out.println(classeName));
       return classes10methods;
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
