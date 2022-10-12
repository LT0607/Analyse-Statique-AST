package behavioral.visitor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class NombreDePackages {

	CompilationUnit parse;
	 
	 public static TypeDeclarationVisitor visitor = new TypeDeclarationVisitor();
	 public static Set<String> packages = new HashSet<>();
	 
	 
   public NombreDePackages (CompilationUnit parse) {
   	this.parse = parse;
		parse.accept(visitor);
		packages.add(parse.getPackage().getName().getFullyQualifiedName());
	
   }
   

public int NombreTotalDePackages() {
	packages.forEach(i -> System.out.println(i));
    return packages.size();
}
  
}
