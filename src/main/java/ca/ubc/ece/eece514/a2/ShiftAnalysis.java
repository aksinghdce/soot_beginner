package ca.ubc.ece.eece514.a2;

import soot.BodyTransformer;
import soot.Body;
import soot.Unit;
import soot.SootMethod;
import soot.jimple.Stmt;
import java.util.Map;
import soot.jimple.DefinitionStmt;
import soot.jimple.BinopExpr;
import soot.jimple.NumericConstant;
import soot.jimple.IntConstant;
import soot.Value;
import soot.toolkits.graph.BriefUnitGraph;

    /** This analysis prints out a warning
     * for any shift operations with a shift less than zero or greater
     * than 31.
     */
public class ShiftAnalysis extends BodyTransformer {
    /** Implements the analysis for a particular method body */
    @Override
    protected void internalTransform(Body body,
                                     String phaseName,
                                     Map options) {
        SootMethod method = body.getMethod();
        System.out.println("analyzing the body of "
                           + method.getDeclaringClass().getName()
                           + "." + method.getName());
        System.out.println();
        
        BriefUnitGraph bug = new BriefUnitGraph(body);
        
        
        for (Unit unit : body.getUnits()) {
            Stmt stmt = (Stmt) unit;
            if (stmt instanceof DefinitionStmt) {
                DefinitionStmt def = (DefinitionStmt) stmt;
                
                // TODO: write code here.  Pseudo-code and hints are below
                
				// get the right-hand side
                Value rhs = def.getRightOp();
                Value op1, op2;
                System.out.print("\t" + "getRightOp:" + rhs + "\n");
				// test if there is an operator, and test if it is a shift
				// OK to focus just on one of shift left or shift right for this lab
				if (rhs instanceof BinopExpr) {
                    BinopExpr binex = (BinopExpr) rhs;
                    String sym = binex.getSymbol();
                    op1 = binex.getOp1();
                    op2 = binex.getOp2();
                        
                    // test if what we are shifting by is a constant
                    if (op2 instanceof soot.jimple.NumericConstant) {
                        System.out.print("\t" + "op2 is NumericConstant\n");   
                        // test whether the constant is negative
                        IntConstant minusOne = IntConstant.v(-1);
                        IntConstant one = IntConstant.v(1);
                        IntConstant thirtyOne = IntConstant.v(31);
                        NumericConstant ek = (NumericConstant)(IntConstant.v(1));
                        if(((IntConstant)(((NumericConstant)op2).lessThanOrEqual((NumericConstant) minusOne))).equals(ek)) {
                            System.out.print("Return warning <= -1\n");
                            ErrorMessage errMsg = ErrorMessage.NEGATIVE_SHIFT;
                            // emit a warning if so
                            Utils.reportWarning(stmt, errMsg);
                        }

                        if(((IntConstant)(((NumericConstant)op2).greaterThan((NumericConstant) thirtyOne))).equals(ek)) {
                            System.out.print("Return warning > 31\n");
                            ErrorMessage errMsg = ErrorMessage.SHIFT_TOO_LARGE;
                            // emit a warning if so
                            Utils.reportWarning(stmt, errMsg);
                        }
				        // bonus: also test if the constant is >31
                        // (((NumericConstant)op2).lessThanOrEqual((NumericConstant) minusOne))
                    }
                }
				// HINT: NumericConstant.lessThan(...) returns a NumericConstant that is 1 or 0, representing true or false respectively (it's a flawed design...)
				// HINT: you can create an IntConstant for a number n with IntConstant.v(n)
            }
        }
    }

    /** The analysis object instance */
    public static ShiftAnalysis instance() {
        return theInstance;
    }
    
    private static ShiftAnalysis theInstance = new ShiftAnalysis();
}