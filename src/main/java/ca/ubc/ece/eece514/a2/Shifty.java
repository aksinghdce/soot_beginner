package ca.ubc.ece.eece514.a2;

    /** Test input for ShiftAnalysis
     */
public class Shifty {
    public static void main(String[] args) {
        int a = 0x10101010;
        int b = a << 5;     // a good shift
        int c = a >> 32;    // a bad shift
        int d = a >>> -1;    // a bad shift
        int result = a + b + c + d; // combine the values
        System.out.println(result); // print them out so the computations are not dead code
    }
}
