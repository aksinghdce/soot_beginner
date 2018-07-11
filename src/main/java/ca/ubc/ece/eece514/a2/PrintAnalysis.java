package ca.ubc.ece.eece514.a2;

import soot.BodyTransformer;
import soot.Body;
import soot.Unit;
import soot.SootMethod;
import soot.NormalUnitPrinter;
import soot.jimple.Stmt;
import java.util.Map;

/** Example analysis that just prints the statements in each method body */
public class PrintAnalysis extends BodyTransformer {
    @Override
    protected void internalTransform(Body body,
                                              String phaseName,
                                              Map<String,String> options) {
        NormalUnitPrinter printer = new NormalUnitPrinter(body);
        SootMethod method = body.getMethod();
        System.out.println("printing statements in the body of " + method.getDeclaringClass().getName() + "." + method.getName());
        for (Unit unit : body.getUnits()) {
            Stmt stmt = (Stmt) unit;
            System.out.print("\t" + "Complete Statement:" + stmt + "\n");
            System.out.print("\t" + stmt.getClass() + ": ");
            stmt.toString(printer);
            System.out.println(printer.output());
            printer.output().setLength(0);
        }
    }

    private static PrintAnalysis theInstance = new PrintAnalysis();
    public static PrintAnalysis instance() {
        return theInstance;
    }
}