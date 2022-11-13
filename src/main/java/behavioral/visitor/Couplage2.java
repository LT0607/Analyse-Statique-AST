package behavioral.visitor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Couplage2 {
	public static Set<String> setLink = new HashSet<String>();

	 void CallG(CompilationUnit cu) {
	    TypeDeclarationVisitor visitorClass = new TypeDeclarationVisitor();
	    cu.accept(visitorClass);

	    for (TypeDeclaration nodeClass : visitorClass.getTypes()) {
	        MethodDeclarationVisitor visitorMethod = new MethodDeclarationVisitor();
	        nodeClass.accept(visitorMethod);

	        String caller;

	        for (MethodDeclaration nodeMethod : visitorMethod.getMethods()) {
	            nodeMethod.resolveBinding();
	            MethodInvocationVisitor visitorMethodInvocation = new MethodInvocationVisitor();
	            nodeMethod.accept(visitorMethodInvocation);

	            caller = nodeClass.getName().toString();
	            
	            for (MethodInvocation methodInvocation : visitorMethodInvocation.getMethodsInvocation()) {

	                String callee;

	                if (methodInvocation.getExpression() != null) {
	                    if (methodInvocation.getExpression().resolveTypeBinding() != null) {
	                        callee = methodInvocation.getExpression().resolveTypeBinding().getName();
	                        setLink.add(caller+"->"+callee+"\n");
	                        
	                    }
	                }
	                else if (methodInvocation.resolveMethodBinding() != null) {
	                    callee = methodInvocation.resolveMethodBinding().getDeclaringClass().getName();
	                    setLink.add(caller+"->"+callee+"\n");
	                    
	                }
	            }
	        }
	    }
	    
	    Iterator<String> iterator = setLink.iterator();
	    while(iterator.hasNext()){
	      String element = (String) iterator.next();
	     // System.out.println(element);
	    }
	 }
	 public Set<String> RetourSet(){
		 return setLink;
	 }
}
