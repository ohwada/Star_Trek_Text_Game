/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

// scanLong
// setNumQuadrantEnterprise

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

               public  static final int WARP_STAY = 0;
     public  static final int WARP_SUCCESS = 1; 
          public  static final int WARP_OUT = 2;

    private Quadrant[][] mQuadrants;


 public int pos_x =0;   
 public int pos_y =0;  
 
    public int num_klingon = 0;
    public int num_starbase = 0;
    public int num_star = 0;      
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


    /**
     * getQuadrantEnterprise
      * @ return Quadrant
     */   
   public  Quadrant getQuadrantEnterprise( ) {
     return mQuadrants[pos_x][pos_y];
  } // end of getQuadrantEnterprise


 /**
 * setNumQuadrantEnterprise
 * @ param int k
 * @ param int starbase
 * @ param int star
 */
 public void setNumQuadrantEnterprise( int k, int b, int s ) {
    mQuadrants[pos_x][pos_y].setNum( k, b, s );
} // setNumQuadrantEnterprise


public void countNum() {
    int k =0;
    int b = 0;
    int s=0;
    
    for ( int i=0; i<8; i++ ) {
            for ( int j=0; j<8; j++ ) {
     k += mQuadrants[i][j].num_klingon ;
        b += mQuadrants[i][j].num_starbase ;
           s += mQuadrants[i][j].num_star ;
} } // for i,j
 
    this.num_klingon = k;
     this.num_starbase = b;
         this.num_star = s;
            
} // countNum

    /**
     * scanLong
     */
   public  String[][] scanLong() {

       String[][] map = new String[8][8];
        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                String str = "*** ";
                // near Enterprise
                if ((i >= pos_x - 1) && (i <= pos_x + 1) &&
                        (j >= pos_y - 1) && (j <= pos_y + 1)) {
                    str = mQuadrants[i][j].getString3Degit() + " ";
     
                    // // exist Enterprise
                    if ((i == pos_x) && (j == pos_y)) {
                        str = "E" + str;
                    }} // if end

                    map[i][j] = str;
       
     
            } } // for end

                    return map;

        } // end of scanLong 

 public int moveLong ( int course ) {
    log_d( "warp " + course );
    int[] delta = Course.getDelta( course );
    int xd = delta[0];
        int yd = delta[1];
        int xx = pos_x + xd;
int yy = pos_y + yd;
    log_d( "xy " + xx + "," + yy );
    if ( ( xx<0 ) || ( xx>7 ) ||  ( yy<0 ) || ( yy>7 ) ) {
        return WARP_OUT;
    } else {
        pos_x = xx;
        pos_y = yy;
        return WARP_SUCCESS ; 
    } // if
    } //moveLong
 
 
 public int moveShort ( int code ) { 
 if (( code == QMap.MOVE_OUT_LEFT ) && ( pos_x >0 ) )  {
    pos_x --;  
          return WARP_SUCCESS ;   
    } else if ( ( code == QMap.MOVE_OUT_RIGHT ) && ( pos_x < 7 ) )  {
    pos_x ++; 
            return WARP_SUCCESS ; 
    } else if   ( ( code == QMap.MOVE_OUT_UP ) && ( pos_y >0 ) )  {
    pos_y --;  
            return WARP_SUCCESS ; 
        } else if ( ( code == QMap.MOVE_OUT_DOWN ) && ( pos_y < 7 ) )  {
    pos_y ++;  
            return WARP_SUCCESS ; 
           
    } // if
return WARP_STAY;
} //  moveShort  

                /**
                 * log_d
                 */
            private static void log_d ( String str ) {
                if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + str);
            } // end of log_d

} // end of clss


