/**
  * STAR TREK
 * 2016-11-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek; 

	import  java.lang.Math;
	
/*
 * Quadrant
 */ 
public class Quadrant {
    public int num_klingon = 0;
    public int num_starbase = 0;
    public int num_star = 0;

    /**
     * constructor
     */
    public Quadrant() {
        // dummy
    }

    /**
     * constructor
     */
    public Quadrant(boolean is_random) {
        if (is_random) {
            int k = (int) (Math.random() * 5);
            int b = (int) (Math.random() * 1);
            int s = (int) (Math.random() * 5);
            setNum(k, b, s);
        } // if end
    } // end ofQuadrant

    /**
     * constructor
     */
    public Quadrant(int k, int b, int s) {
        setNum(k, b, s);
    } // end of Quadrant

    /**
     * setNum
     */
    public void setNum(int k, int b, int s) {
        this.num_klingon = k;
        this.num_starbase = b;
        this.num_star = s;
    } // end of setNum

    /**
     * getString3Degit
     */
    public String getString3Degit() {
        // 3 degit
        String str = Integer.toString(this.num_klingon) + Integer.toString(this.num_starbase) + Integer.toString(this.num_star);
        return str;
    } // end of getString3Degit

} //  end of Quadrant class

