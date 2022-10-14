package behavioral.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PourcentageClassesAvecPlusAttributs {

	List<String> classes10attributes;
	int maxMethods, maxClasses;
	
	public PourcentageClassesAvecPlusAttributs (double totalClassesNbr, double totalMethodsNbr) {
        double doubleNumber = totalClassesNbr*10.0/100;
        maxClasses = (int) doubleNumber;
        if (maxClasses == 0 || (doubleNumber - maxClasses) >= 0.5 ) maxClasses += 1;

        doubleNumber = totalMethodsNbr*10.0/100;
        maxMethods = (int) doubleNumber;
        if (maxMethods == 0 || (doubleNumber - maxMethods) >= 0.5 ) maxMethods += 1;
    }
	
	public List<String> getClasses10Attirbutes() {
	        Map<String, List<String>> attributes = Visitor.getParentVariables();
	        classes10attributes =  new ArrayList<>();

	        int max = 0;
	        String name = "";
	        for (int i = 1; i <= maxClasses; i++) {
	            for (var entry : attributes.entrySet()) {
	                if (max < entry.getValue().size()) {
	                    max = entry.getValue().size();
	                    name = entry.getKey();
	                }
	            }
	            classes10attributes.add(name);
	            max = 0;
	            attributes.remove(name);
	        }
	        //classes10attributes.forEach(classeName-> System.out.println(classeName));

	        return classes10attributes;
	    }
	
}
