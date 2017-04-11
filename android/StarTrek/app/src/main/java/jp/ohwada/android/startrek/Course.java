/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

package jp.ohwada.android.startrek; 

import android.util.Log;

	import  java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/*
 * Course
 */ 
public class Course {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Course";
    
      
    /**
     * Course
     */
    public Course() {
    // dummy
    } // end of Course

public static int[] getDelta( int course ) {
    log_d( "getDelta " + course );
int xd = 0;
int yd = 0;
// up
if ( course == 1 ) {
  xd = -1;
 yd = 0; 
// up right
} else if ( course == 2 ) {
  xd = -1;
 yd = 1;  
//  right
} else if ( course == 3 ) {
  xd = 0;
 yd = 1;  
// down right
} else if ( course == 4 ) {
  xd = 1;
 yd = 1; 
// down 
} else if ( course == 5 ) {
  xd = 1;
 yd = 0;  
// down left
// down left
} else if ( course == 6 ) {
  xd = 1;
 yd = -1; 
// left
} else if ( course == 7 ) {
  xd = 0;
 yd = 1;  
// up left 
} else if ( course == 8 ) {
  xd = -1;
 yd = -1; 
} // if
int[] ret = new int[2];
log_d( "delta " +xd + ", "+ yd );
ret[0] = xd;
ret[1] = yd;
return ret;
} // getdelta
                /**
                 * log_d
                 */
            private static void log_d ( String msg ) {
                if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + msg );
            } // end of log_d

} // end of class 