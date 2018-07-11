package ca.ubc.ece.eece514.a2;

import soot.BodyTransformer;
import soot.Body;
import soot.Unit;
import soot.Scene;
import soot.SootField;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.toolkits.graph.BriefUnitGraph;
import soot.util.Chain;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Iterables;

import java.util.HashSet;
import java.util.List;

import soot.jimple.DefinitionStmt;
import soot.jimple.FieldRef;
import soot.Value;

    /** This analysis identified fields that are declared but never
     * used, at least within the code being analyzed.
     */
public class UnreadAnalysis extends BodyTransformer {
    @Override
    protected void internalTransform(Body body,
                                     String phaseName,
                                     Map options) {
        // TODO: implement me
        
        // HINT: build up a set (use java.util.HashSet) of all
        // fields that are read.  Keep the list in a variable of the
        // analysis so you can combine reads of fields in all methods.
    	graph = new BriefUnitGraph(body);
    	UnreadForwardFlowAnalysis_ByAmit analysis = new UnreadForwardFlowAnalysis_ByAmit(graph);
    	System.out.print("\t YOLO:" + analysis.getFlowAfter(Iterables.getLast(graph)));
    }

    public void identifyUnreadFields() {
        // TODO: implement me
        
        // HINT: Start with Scene.v() and get the classes and then the
        // fields in the program, then look at the data previously
        // gathered when the analysis infrastructure
        // called internalTransform() to see if they were all read.
    	
    	Chain<SootClass> chainOfClasses = Scene.v().getApplicationClasses();
    	for (SootClass sc : chainOfClasses) {
    		List<SootMethod> sm = sc.getMethods();
    		for(SootMethod smm : sm) {
    			//Body bd = (Body) smm.getActiveBody();
    		}
    	}
    }
    
    // TODO: you probably need to add some data structure to keep
    // track of the fields that have been read across multiple
    // method bodies
    private BriefUnitGraph graph;
    private static UnreadAnalysis theInstance = new UnreadAnalysis();
    public static UnreadAnalysis instance() {
        return theInstance;
    }
}
