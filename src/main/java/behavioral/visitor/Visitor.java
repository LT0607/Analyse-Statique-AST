package behavioral.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class Visitor extends ASTVisitor {
	
	 public static Map<String, MethodDeclaration[]> methods = new HashMap<>();
	 public static  Map<String, MethodDeclaration[]> getMethods() {return methods;}

	 
	 public boolean visit(TypeDeclaration node) {
	        if (!node.isInterface()) {
	            methods.put(node.getName().toString(), node.getMethods());
	        }
	        return super.visit(node);
	}
	 
	public static List<String> variables = new ArrayList<String>();
	public static List<String> parents = new ArrayList<String>();
	public static Map<String, List<String>> parentVariables = new HashMap<>();

    public boolean visit(VariableDeclarationFragment node) {
	        if (node.getParent().getParent() instanceof TypeDeclaration) {
	            variables.add(node.getName().toString());
	            parents.add( ( (TypeDeclaration) node.getParent().getParent() ).getName().toString() );
	        }
	        return super.visit(node);
	    }
    public int getTotalVariablesNbr() {
        return getVariables().size();
    }

    public List<String> getVariables() {
        return variables;
    }
    public List<String> getParent() {
        return parents;
    }
    public static Map<String, List<String>> getParentVariables() {
        pushParentVariables();
        return parentVariables;
    }

    public static void pushParentVariables () {
        List<List<String>> vars = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (int i = 0; i < variables.size(); i++) {
            temp.add(variables.get(i));
            if (i+1<variables.size())
                if (!parents.get(i).equals(parents.get(i+1))){
                    vars.add(temp);
                    temp = new ArrayList<>();
                }
        }
        vars.add(temp);
        List<String> neww = new ArrayList<>(new LinkedHashSet<>(parents));
        for (int k = 0; k < vars.size(); k++) {
        	System.out.println("++++"+parents.get(k));
            parentVariables.put(neww.get(k), vars.get(k));
        }
         for (var entry : parentVariables.entrySet())
         System.out.println("class : "+entry.getKey()+" --- attributes : "+ entry.getValue()+"\n");
         //for (int k = 0; k < vars.size(); k++)
         //for (int m = 0; m < vars.get(k).size(); m++)
         //System.out.println(k+"-"+vars.get(k).get(m)); 
        
    }

}
