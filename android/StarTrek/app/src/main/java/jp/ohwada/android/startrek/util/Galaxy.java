/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

package jp.ohwada.android.startrek.util; 

import android.util.Log;

	import  java.lang.Math;

import jp.ohwada.android.startrek.Constant;

/*
 * Galaxy
 */ 
public class Galaxy {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Galaxy";

               private  static final int SIZE_X = 8;
                    private  static final int SIZE_Y = 8;
                    
                  private  static final int INIT_DISTANCE = 1;
                              private  static final int SCAN_DISTANCE = 1;
                                                
               public  static final int WARP_STAY = 0;
     public  static final int WARP_SUCCESS = 1; 
          public  static final int WARP_OUT = 2;
      
          
          private  static final String SCAN_NOT_YET = "*** ";
      
          
    private Quadrant[][] mQuadrants;

    private String[][] mGalaxyMap= new String[SIZE_X][SIZE_Y];


 public int pos_x =0;   
 public int pos_y =0;  
 
    public int num_klingon = 0;
    public int num_starbase = 0;
    public int num_star = 0;  
        
    /**
     * === constructor ===
     */
    public Galaxy() {
        
    initVariable();
    
    mQuadrants = new Quadrant[SIZE_X][SIZE_Y];

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
//                 mQuadrants[i][j] = new Quadrant();
        }} // for end      

    } // end of Galaxy
 
    
    /**
     * initVariable
     */ 
private void  initVariable() {
        mGalaxyMap = new String[SIZE_X][SIZE_Y];
        for ( int i = 0; i < SIZE_X; i++ ) {
            for  ( int j = 0; j < SIZE_Y; j++ ) {
               mGalaxyMap[i][j] =  SCAN_NOT_YET ;
        }} // for i j  
} // initVariable
        
    /**
     * setupPosition
     */    
         public void setupPosition() {
            
            log_d("setupPosition" );
            // 1 to 6
             this.pos_x = (int) (Math.random() * (SIZE_X-3)) + 1;
             this.pos_y = (int) (Math.random() * (SIZE_Y-3)) + 1;

log_d("Position " + pos_x + "," + pos_y );

         } // end of setupPosition

    /**
     * setupQuadrants
     */

    public void setupQuadrants() {
        
        log_d( "setupQuadrants" );
        boolean is_random = false;
        
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                
                      
                                
                          //      init 3x3 area 
                           //     because play time takes too much with 8x8 area
                                    is_random = false;
                                if ( ( i >=pos_x - INIT_DISTANCE  ) &&
                                ( i <= pos_x + INIT_DISTANCE ) &&
                                ( j >= pos_y - INIT_DISTANCE ) &&
                                 ( j <= pos_y + INIT_DISTANCE ) ) {
                                    is_random = true;
                                        log_d( i +"," + j ); 
                                        
                                } // if
                                
                              mQuadrants[i][j] = new Quadrant( is_random );
      
            }} // for i j 
            
        for ( int i = 0; i < SIZE_X; i++ ) {
            for  ( int j = 0; j < SIZE_Y; j++ ) {
               mGalaxyMap[i][j] =  SCAN_NOT_YET ;
        }} // for i j 
        
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

    /**
     * countNum    
     */
public void countNum() {
    
log_d("countNum");
    
    int k =0;
    int b = 0;
    int s=0;
    
    for ( int i=0; i<SIZE_X; i++ ) {
            for ( int j=0; j<SIZE_Y; j++ ) {
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
     * @ param boolean is_computer_available
     * @ return  String[][]
     */
   public  String[][] scanLong( boolean is_computer_available ) {
    
    log_d("scanLong " + is_computer_available);

       String[][] map = new String[SIZE_X][SIZE_Y];
        for(int i=0; i<SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                String str = SCAN_NOT_YET;
                // near Enterprise
                if ((i >= pos_x - SCAN_DISTANCE ) && (i <= pos_x + SCAN_DISTANCE ) &&
                        (j >= pos_y - SCAN_DISTANCE ) && (j <= pos_y + SCAN_DISTANCE )) {
                    String str_3_degit = mQuadrants[i][j].getString3Degit() + " ";
                    str = str_3_degit;
                    log_d( i+ "," + j + " " + str_3_degit );
                    
                    if ( is_computer_available ) {
                        // save
     mGalaxyMap[i][j] = str_3_degit;
     log_d( "save " + i +" , "+ j + " " + str_3_degit );
     
     } // if
      
                    // // exist Enterprise
                    if ((i == pos_x) && (j == pos_y)) {
                        str = "E" + str;
                    }} // if end

                    map[i][j] = str;
       
     
            } } // for end

                    return map;

        } // end of scanLong 
        
        
    /**
     * getGalaxyMap    
     * @ return  String[][]
     */        
 public  String[][] getGalaxyMap( ) {

 log_d("getGalaxyMap ");
       String[][] map = new String[SIZE_X][SIZE_Y];

        for(int i=0; i<SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
        String str =  mGalaxyMap[i][j];
        log_d( i+ "," + j +  " " +  str );
                if ((i == pos_x) && (j == pos_y)) {
                        str = "E" + str;
                    } // if end 
                     
                      map[i][j] =  str;  
                               
  }} // for i j  
  return map;           
} //  getGalaxyMap
  
         
    /**
     * moveLong 
          * @  ( int course  
     * @ return  int 
     */                
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
 
    /**
     * moveShort 
          * @  int code  
     * @ return  int 
     */  
 public int moveShort ( int code ) { 
 if (( code == QMap.MOVE_OUT_LEFT ) && ( pos_x >0 ) )  {
        // warp left
    pos_x --;  
          return WARP_SUCCESS ;   
    } else if ( ( code == QMap.MOVE_OUT_RIGHT ) && ( pos_x < 7 ) )  {
                // warp right
    pos_x ++; 
            return WARP_SUCCESS ; 
    } else if   ( ( code == QMap.MOVE_OUT_UP ) && ( pos_y >0 ) )  {
        // warp up
    pos_y --;  
            return WARP_SUCCESS ; 
        } else if ( ( code == QMap.MOVE_OUT_DOWN ) && ( pos_y < 7 ) )  {
                    // warp dpwn
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


