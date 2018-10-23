package worms;

public class Utils {

	/** 
	  * Sine table
	  * Values represent sine for each degree between 0 and 90. Values have
	  * been multiplied by 65536 and rounded.
	  */
	private static int[] sineTable = {
	    0,  1143,  2287,  3429,  4571,  5711,  6850,  7986,  9120, 10252, // 0 to 9   degrees
	11380, 12504, 13625, 14742, 15854, 16961, 18064, 19160, 20251, 21336, // 10 to 19 degrees
	22414, 23486, 24550, 25606, 26655, 27696, 28729, 29752, 30767, 31772, // 20 to 29 degrees
	32767, 33753, 34728, 35693, 36647, 37589, 38521, 39440, 40347, 41243, // 30 to 39 degrees
	42125, 42995, 43852, 44695, 45525, 46340, 47142, 47929, 48702, 49460, // 40 to 49 degrees
	50203, 50931, 51643, 52339, 53019, 53683, 54331, 54963, 55577, 56175, // 50 to 59 degrees
	56755, 57319, 57864, 58393, 58903, 59395, 59870, 60326, 60763, 61183, // 60 to 69 degrees
	61583, 61965, 62328, 62672, 62997, 63302, 63589, 63856, 64103, 64331, // 70 to 79 degrees
	64540, 64729, 64898, 65047, 65176, 65286, 65376, 65446, 65496, 65526, // 80 to 89 degrees
	65536 };															  // 90       degrees
	 
	/** 
	  * sin return sine of a degree between 0 and 90. Values have
	  * been multiplied by 65536 and rounded.
	  * @param degree between 0 and 90
	  * @return sine multiplied by 65536 and rounded.
	  */
	public static int sin(int degree){
		return (sineTable[degree%90]);
	}
	
	/** 
	  * cos return cosine of a degree between 0 and 90. Values have
	  * been multiplied by 65536 and rounded.
	  * @param degree between 0 and 90
	  * @return cosine multiplied by 65536 and rounded.
	  */
	public static int cos(int degree) {
		return (sineTable[90-degree%90]);
	}
	
	public static int pow(int pValue, int pPotencia){
		if (pPotencia < 0) return -1;
		if (pPotencia ==0) return 1;
		for (int i = 1; i < pPotencia; i++) {
			pValue*=pValue;			
		}
		return pValue;
	}
	
	/**
	 * Square root implemented with integer math, ie the argument and the
	 * result are both integers.
	 * The result of SQRT(x), where x is an integer >= 0, is identical
	 * to the result of the expression (int)java.lang.Math.sqrt(x)for J2SE
	 *
	 * @param	integer x	Note:  for x<0 SQRT(x) returns 0!
	 * 
	 * The method SQRT was borrowed from mathME 
	 */
	public static int SQRT(int x){
		// The method DebugME can not be used in a static method and so we
		// have retained some trace System.out.println for debugging
		/*
		 * First, find the power of 100, nd, such that x/nd yields the one,
		 * if x has odd number of digits, or two most significant digits of x.
		 */
		int nd = 0;
		int n = x;
		int t = 1;
		while (n > 0){
			nd =t;
			t *= 100;
			n =x / t;
		}
		/*
		 * Second, consider the most significant digits of x starting with
		 * x/nd and stepping nd, dividing by 100, then adding pairs of
		 * digits until the least significant digits are done.
		 */
		int d ; // will contain the most significant digits of the answer
		t = 0;
		int iter = 0;		// just to keep track of innermost loop
		while (nd > 0){
			n = x / nd;
			d = 10 * t;
			/* In the for loop we step d until its square is larger than
			 * x. We save the prevous value in t, which thus is our answer:
			 * the largest number whose square is less than x.
			 */
			for (int i = d; i < d+10; i++){
				iter++;
				if (pow(i,2) > n) break;
				t = i;
			}	// end for
			nd /= 100;
		}
		return t;
	}	// end SQRT


}
