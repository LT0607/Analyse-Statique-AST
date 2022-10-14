package behavioral.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class X_Methodes {
	  
	List<String> classesXmethods =  new ArrayList<>();

	Map<String, MethodDeclaration[]> methods = Visitor.getMethods();

	 public List<String> getClassesXMethods(int x) {
	        for (var entry : methods.entrySet()) if (x <= entry.getValue().length) classesXmethods.add(entry.getKey());
	        //classesXmethods.forEach(classeName-> System.out.println(classeName));
	        return classesXmethods;
	    }
}
