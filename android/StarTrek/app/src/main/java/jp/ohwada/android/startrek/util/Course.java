/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

package jp.ohwada.android.startrek.util; 

import android.util.Log;

	import  java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import jp.ohwada.android.startrek.Constant;

/*
 * Course
 */ 
public class Course {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Course";
    
          public static final int COURSE_MIN = 1; 
          public static final int COURSE_MAX = 8; 
                    
         private static final int COURSE_NONE = 0;  
      private static final int COURSE_RIGHT = 1; 
        private static final int COURSE_UP_RIGHT = 2; 
             private static final int COURSE_UP = 3; 
               private static final int COURSE_UP_LEFT = 4; 
                             private static final int COURSE_LEFT = 5; 
                                         private static final int COURSE_DOWN_LEFT = 6;          
             private static final int COURSE_DOWN = 7; 
                       private static final int COURSE_DOWN_RIGHT = 8;       
    /**
     * Course
     */
    public Course() {
    // dummy
    } // end of Course
   
    /**
     * getDelta
     * @ param int course
     * @ return int[] delta  
     */
public static int[] getDelta( int course ) {
    log_d( "getDelta " + course );
int xd = 0;
int yd = 0;
// right
if ( course == COURSE_RIGHT ) {
  xd = 0;
 yd = 1; 
// up right
} else if ( course == COURSE_UP_RIGHT ) {
  xd = -1;
 yd = 1;  
//  up
} else if ( course == COURSE_UP ) {
  xd = -1;
 yd = 0;  

 // up left
} else if ( course == COURSE_UP_LEFT ) {
  xd = -1;
 yd = -1;  
// left 
} else if ( course == COURSE_LEFT ) {
  xd = 0;
 yd = -1;  
// down left
} else if ( course ==  COURSE_DOWN_LEFT ) {
  xd = 1;
 yd = -1;
 // down
} else if ( course == COURSE_DOWN ) {
  xd = 1;
 yd = 0;  
// down right 
} else if ( course == COURSE_DOWN_RIGHT ) {
  xd = 1;
 yd = 1; 
} // if
int[] ret = new int[2];
log_d( "delta " +xd + ", "+ yd );
ret[0] = xd;
ret[1] = yd;
return ret;
} // getdelta


    /**
     * getCourse
     * @ param int orig_x. int orig_y, int x, int y
     * @ return int course 
     */
public static int getCourse( int orig_x, int orig_y, int x, int y ) {
   
    log_d( "getCourse " + orig_x + ","+ orig_y + ","+ x + ","+ y );
   int ret = COURSE_NONE;
   
    if( orig_x == x ){
        
        if( orig_y < y ) {
  ret = COURSE_RIGHT;  
  } else {
  ret = COURSE_LEFT;            
        } // if
        
           } else if( orig_y == y ){ 
            
         if( orig_x < x ) {
  ret = COURSE_DOWN;  
  } else {
  ret = COURSE_UP;            
        } // if          
           
   } else if( Math.abs( orig_x - x ) == Math.abs( orig_y - y ) ){        

         if(( orig_x > x )&&( orig_y > y )) {
  ret = COURSE_UP_LEFT;            
 } else        if(( orig_x > x )&&( orig_y < y )) {
  ret = COURSE_UP_RIGHT; 
  
 } else          if(( orig_x < x )&&( orig_y > y )) {
  ret = COURSE_DOWN_LEFT;  
            
 } else        if(( orig_x > x )&&( orig_y < y )) {
  ret = COURSE_DOWN_RIGHT;   
       
  } // if 
        
       } // if 
      return ret;
       
    } // getCourse
    
        
                /**
                 * log_d
                 */
            private static void log_d ( String msg ) {
                if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + msg );
            } // end of log_d

} // end of class 