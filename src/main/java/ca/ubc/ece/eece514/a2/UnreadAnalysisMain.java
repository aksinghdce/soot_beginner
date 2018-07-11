package ca.ubc.ece.eece514.a2;

import soot.PackManager;
import soot.Scene;
import soot.Transform;
import soot.toolkits.scalar.ForwardFlowAnalysis;

import java.util.Set;
import java.util.HashSet;

public class UnreadAnalysisMain {
	/** The abbreviated name of the analysis */
    public static final String ANALYSIS_NAME = "jap.unreadanalysis";
    
    /** Runs Soot with UnreadAnalysis available
     */
    public static void main(String[] args) {
        // Inject the analysis tagger into Soot
        PackManager.v().getPack("jap").add(new Transform(ANALYSIS_NAME, UnreadAnalysis.instance()));

        // run Soot with the arguments given
        // Utils.runSoot() keeps Soot from calling System.exit() if we are invoked from JUnit
        // The analysis should record which fields are read
        Utils.runSoot(args);
        
        // Now we can look at all fields in the application, and
        // warn about the ones for which we did not observe any
        // read operations.
        UnreadAnalysis.instance().identifyUnreadFields();
        
        System.out.println("total warnings: " + Utils.getErrors().size());
    }
/*
    @Test
    public void testUnreadAnalysis() {
        Utils.resetErrors();
        main(Utils.getSootArgs(ANALYSIS_NAME, "ca.ubc.ece.eece514.a2.Countdown"));

        Set<ErrorReport> expected = new HashSet<ErrorReport>();
        expected.add(new ErrorReport(ErrorMessage.UNREAD_FIELD,
                "foo"));
        expected.add(new ErrorReport(ErrorMessage.UNREAD_FIELD,
                "m_Date2"));
        Assert.assertEquals(expected, Utils.getErrors());
    }
 */

}
