/**
  * STAR TREK
 * 2016-11-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek.util; 

import android.util.Log;
	import  java.lang.Math;

import jp.ohwada.android.startrek.Constant;

/*
 * Quadrant
 */ 
public class Quadrant {
    
       // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Quadrant";
    
        private int NUM_KLINGON = 5;
            private int NUM_STAR = 5;
               private double NUM_PROBABILITY＿STARBASE = 0.6 ; // 60 %
                        
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
    public Quadrant( boolean is_random ) {

        if (is_random) {
            int k = (int) ( Math.random() * NUM_KLINGON );
            // 10 %
            int b = ( Math.random() < NUM_PROBABILITY＿STARBASE ) ? 1: 0;
            int s = (int) ( Math.random() * NUM_STAR );
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
        log_d( "setNum k=" +k +" b="+ b +" s="+ s );
        this.num_klingon = k;
        this.num_starbase = b;
        this.num_star = s;
    } // end of setNum

    /**
     * getString3Degit
     */
    public String getString3Degit() {
        
        log_d( "getString3Degit " );
        // 3 degit
        String str = Integer.toString(this.num_klingon) + Integer.toString(this.num_starbase) + Integer.toString(this.num_star);
        log_d( str );
        return str;
    } // end of getString3Degit
    
    /**
     * log_d
     */
    private static void log_d(String str) {
        
        if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + str);
    } // og_d end
    
} //  end of Quadrant class

