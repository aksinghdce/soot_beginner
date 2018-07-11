/**
 * 
 */
package ca.ubc.ece.eece514.a2;

import java.util.List;

import soot.Unit;
import soot.ValueBox;
import soot.jimple.FieldRef;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.FlowSet;
import soot.toolkits.scalar.ForwardFlowAnalysis;

/**
 * @author aksin
 *
 */
public class UnreadForwardFlowAnalysis_ByAmit extends ForwardFlowAnalysis<Unit, FlowSet<String>> {


	public UnreadForwardFlowAnalysis_ByAmit(DirectedGraph<Unit> briefUnitGraph) {
		super(briefUnitGraph);
		// TODO Auto-generated constructor stub	
		doAnalysis();
	}

	@Override
	protected void flowThrough(FlowSet<String> IN, Unit UB, FlowSet<String> OUT) {
		// TODO Auto-generated method stub
		//Clear OUT
		OUT.clear();
		
		//Get the Field names that are defined at this Node (Stmt)
		FlowSet<String> definedFieldNames = getFieldNamesFromValueBoxes(UB.getDefBoxes());
		
		//Get field names that are used in this Node (Stmt)
		FlowSet<String> usedFieldNames = getFieldNamesFromValueBoxes(UB.getDefBoxes());
		
		IN.difference(usedFieldNames, OUT);
		
		OUT.union(definedFieldNames);
	}

	@Override
	protected void copy(FlowSet<String> arg0, FlowSet<String> arg1) {
		// TODO Auto-generated method stub
		arg0.copy(arg1);
	}

	@Override
	protected void merge(FlowSet<String> arg0, FlowSet<String> arg1, FlowSet<String> arg2) {
		// TODO Auto-generated method stub
		arg0.union(arg1, arg2);
	}

	@Override
	protected FlowSet<String> newInitialFlow() {
		// TODO Auto-generated method stub
		return new ArraySparseSet<String>();
	}
	
	@Override
	protected FlowSet<String> entryInitialFlow() {
		// TODO Auto-generated method stub
		return new ArraySparseSet<String>();
	}
	
	private FlowSet<String> getFieldNamesFromValueBoxes(List<ValueBox> valueboxes) {
		return valueboxes
				.stream()
				.map(param -> param.getValue())
				.filter(val -> val instanceof FieldRef)
				.map(val -> ((FieldRef) val).getField().getName())
				.collect(ArraySparseSet::new, ArraySparseSet::add, ArraySparseSet::copy);
	}

}
