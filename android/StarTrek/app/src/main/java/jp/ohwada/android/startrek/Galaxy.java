/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

package jp.ohwada.android.startrek; 

import android.util.Log;

	import  java.lang.Math;
	
/*
 * Galaxy
 */ 
public class Galaxy {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Galaxy";

    private Quadrant[][] mQuadrants;



 public int pos_x =0;   
 public int pos_y =0;  
      
    /**
     * Galaxy
     */
    public Galaxy() {
    
    mQuadrants = new Quadrant[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
//                mQuadrants[i][j] = new Quadrant();
        }} // for end      

    } // end of Galaxy

    /**
     * setupPosition
     */
     
         public void setupPosition() {
             this.pos_x = (int) (Math.random() * 7);
             this.pos_y = (int) (Math.random() * 7);

         } // end of setupPosition

    /**
     * setupQuadrants
     */

    public void setupQuadrants() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                              mQuadrants[i][j] = new Quadrant( true );
            //    int klingons = (int) (Math.random() * 5);
             //    int bases = (int) (Math.random() * 1);
             //   int stars = (int) (Math.random() * 5);
 
            //    mQuadrants[i][j].setNum(klingons, bases, stars);
            
            }} // for end

    } // end of setupQuadrants
   
   public  Quadrant getQuadrantEnterprise( ) {
     return mQuadrants[pos_x][pos_y];
  } // end of getQuadrantEnterprise



    /**
     * scanLong
     */
   public  String[][] scanLong( ) {

       String[][] map = new String[8][8];
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                String str = "*** ";
                // near Enterprise
                if ((i >= pos_x - 1) && (i <= pos_x + 1) &&
                        (j >= pos_y - 1) && (j <= pos_y + 1)) {
                    str = mQuadrants[i][j].getString3Degit();
     
                    // // exist Enterprise
                    if ((i == pos_x) && (j == pos_y)) {
                        str = "E" + str;
                    }} // if end

                    map[i][j] = str;
       
     
            } } // for end

                    return map;

        } // end of scanLong 



                /**
                 * log_d
                 */
            private static void log_d ( String str ) {
                if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + str);
            } // end of log_d

} // end of clss


