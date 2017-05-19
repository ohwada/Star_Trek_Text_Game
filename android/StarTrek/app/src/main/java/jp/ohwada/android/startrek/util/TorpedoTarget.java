/**
  * STAR TREK
 * 2017-05-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek.util; 

	import  java.lang.Math;
	
/*
 * Coordinate
 */ 
public class TorpedoTarget {
    public int code = 0;
    public int x = 0;
    public int y = 0;
    public int course = 0;
    
    /**
     * constructor
     */
    public TorpedoTarget( int _code, int _x, int _y, int _course ) {
       this.code = _code;
       this.x = _x;
              this.y = _y;
              this.course = _course;
    } // public

} //  end of  class

