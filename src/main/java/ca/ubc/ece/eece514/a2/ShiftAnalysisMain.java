package ca.ubc.ece.eece514.a2;

import soot.Main;
import soot.PackManager;
import soot.Transform;

import java.util.Set;
import java.util.HashSet;


/** The driver for ShiftAnalysis.
 * Also implements a JUnit test for the analysis
 */
public class ShiftAnalysisMain {
    /** The abbreviated name of the analysis */
    public static final String ANALYSIS_NAME = "jap.shiftanalysis";
    
    /** Runs Soot with ShiftAnalysis available
     */
    public static void main(String[] args) {
        // Inject the analysis tagger into Soot
        PackManager.v().getPack("jap").add(new Transform(ANALYSIS_NAME, ShiftAnalysis.instance()));

        // run Soot with the arguments given
        // Utils.runSoot() keeps Soot from calling System.exit() if we are invoked from JUnit
        Utils.runSoot(args);
        
        System.out.println("total warnings: " + Utils.getErrors().size());
    }

/*
    @Test
    public void testShiftAnalysis() {
        Utils.resetErrors();
        main(Utils.getSootArgs(ANALYSIS_NAME, "ca.ubc.ece.eece514.a2.Shifty"));

        // we expect two errors
        Set<ErrorReport> expected = new HashSet<ErrorReport>();
        expected.add(new ErrorReport(ErrorMessage.NEGATIVE_SHIFT, 10));
        expected.add(new ErrorReport(ErrorMessage.SHIFT_TOO_LARGE, 9));

        // assert failure of the test case if the errors reported
        // by the analysis don't match the expected ones
        Assert.assertEquals(expected, Utils.getErrors());
    }
*/

}
