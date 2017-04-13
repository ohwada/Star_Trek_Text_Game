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
 * QMap
 */ 
public class QMap {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "QMap";
    
    public static final int C_NONE = 0;
      public static final int C_ENTERPRISE = 1; 
    public static final int C_STARBASE = 2;
    public static final int C_KLINGON = 3;
    public static final int C_STAR = 4;
       public static final int C_OUT = 11; 
       public static final int MOVE_OUT_LEFT = 21; 
        public static final int MOVE_OUT_RIGHT = 22; 
               public static final int MOVE_OUT_UP = 23; 
                      public static final int MOVE_OUT_DOWN = 24; 
                            
 public int pos_x = 0;   
 public int pos_y = 0;  
     
     public int num_klingon = 0;
    public int num_starbase = 0;
    public int num_star = 0;
    
    private int[][] mSectors;

      
    /**
     * QMap
     */
    public QMap() {

// clear 
    mSectors = new int[8][8];

        for ( int i = 0; i < 8; i++) {
            for ( int j = 0; j < 8; j++) {
                mSectors[i][j] = C_NONE;
        }} // for i j end      
log_map() ;
    } // end of QMap

    /**
     * setupPosition
     */     
         public void setupPosition() {

             this.pos_x = (int) (Math.random() * 7);
             this.pos_y = (int) (Math.random() * 7);

         } // end of setupPosition

    /**
     * setupSectors
     */

    public void setupSectors( Quadrant q) {
   log_d( "setupSectors" );
    this.num_klingon = q.num_klingon;
    this.num_starbase = q.num_starbase;
    this.num_star = q.num_star;
   log_d(  "klingon "  + this.num_klingon );
      log_d(  "starbase "  + this.num_starbase );
         log_d(  "star "  + this.num_star );
        
        // ENTERPRISE 
             mSectors[pos_x][pos_y] = C_ENTERPRISE;
    log_d( "ENTERPRISE " + pos_x + ", " + pos_y );

// STARBASE
        for ( int k = 0; k < this.num_starbase; k++ ) {
            int x = (int) (Math.random() * 7);
             int y = (int) (Math.random() * 7); 
                       if ( mSectors[x][y] == C_NONE ) {
             mSectors[x][y] = C_STARBASE;
             log_d( "STARBASE " + x + ", " + y ); 
             } // if
          } // for

// klingon
        for  (int k = 0; k < this.num_klingon; k++ ) {
            int x = (int) (Math.random() * 7);
             int y = (int) (Math.random() * 7); 
                       if ( mSectors[x][y] == C_NONE ) {
             mSectors[x][y] = C_KLINGON;
                        log_d( "KLINGON " + x + ", " + y ); 
          } // if
          } // for
          
         // STAR
        for (int k = 0; k < this.num_star; k++) {
            int x = (int) (Math.random() * 7);
             int y = (int) (Math.random() * 7); 
                       if ( mSectors[x][y] == C_NONE ) {
             mSectors[x][y] = C_STAR;
          } // if
          } // for
          
//          log_map() ;
          
    } // end of setupsetupSectors
 
    /**
     * scanShort
     */
   public  String[][] scanShort( ) {

       String[][] map = new String[8][8];
    //   String str = "  ";
       for ( int i=0; i<8; i++ ) {
          for ( int j=0; j<8; j++ ) { 
           map[i][j] =  "  ";
           if ( mSectors[i][j] == C_ENTERPRISE ) {
 //           str = "E ";
                        map[i][j] = "E ";
            log_d( "E " + i + "," + j );
            }  else if ( mSectors[i][j] == C_STARBASE ) {
         //   str = "B ";
                        map[i][j] = "B ";
        }  else if ( mSectors[i][j] == C_KLINGON ) {
  //          str = "K ";
             map[i][j] = "K ";
                        log_d( "K " + i + "," + j );
                   }  else if ( mSectors[i][j] == C_STAR ) {
   //         str = "* ";
               map[i][j] = "* ";
        } // if
//        map[i][j] = str;
}} // for i j             
          return map;

        } // end of scanLong 


 public Trace startInpulse ( int course ) {
    log_d( "startInpulse " + course );
    Trace ret;
    int[] delta = Course.getDelta( course );
    int xd = delta[0];
        int yd = delta[1];
        int xx = pos_x + xd;
int yy = pos_y + yd;
    log_d( "xy " + xx + "," + yy );
    if ( xx<0 ) {
           ret =  new Trace( C_OUT, xx, yy );
           log_d( C_OUT + ":"+xx + ","+yy );
           return ret;
//        break;
    } else if ( xx>7 ) {
           ret =  new Trace( C_OUT, xx, yy );
                    log_d( C_OUT + ":"+xx + ","+yy );
                      return ret;
//        break;
     } else if ( yy<0 ) {
                     ret =  new Trace( C_OUT, xx, yy );
                    log_d( C_OUT + ":"+xx + ","+yy );
                      return ret;
//        break;
     } else if ( yy>7 ) {
                   ret =  new Trace( C_OUT, xx, yy );
                      log_d( C_OUT + ":"+xx + ","+yy );
           return ret;
//        break;
   } else if ( mSectors[xx][yy] == C_KLINGON ) {
        mSectors[xx][yy] = C_NONE;
                          ret =  new Trace( C_KLINGON, xx, yy );
                     log_d( C_KLINGON + ":"+xx + ","+yy );
           return ret;
    
      }  else if ( mSectors[xx][yy] == C_STARBASE ) {
                          ret =  new Trace( C_STARBASE, xx, yy );
           return ret;   
 } else if ( mSectors[xx][yy] == C_NONE ) {
    // move 

          mSectors[pos_x][pos_y] = C_NONE;
     mSectors[xx][yy] = C_ENTERPRISE;
       this.pos_x = xx;
     this.pos_y = yy;                   ret =  new Trace( C_NONE, xx, yy );
     log_d( C_NONE + ":"+xx + ","+yy );
           return ret;   
} // if
     return null; // dummy
} // startInpulse


public List<Trace> fireTorpedoe( int course ) {

    log_d( "fireTorpedoe " + course  );
        int[] delta = Course.getDelta( course );
    int xd = delta[0];
        int yd = delta[1];
        
List<Trace>  list  = new ArrayList<Trace>();

int xx = pos_x;
int yy = pos_y;

while (true) {

   xx += xd;
    yy += yd;
    
             if ( xx>=0 && xx<=7 &&  yy>=0 && yy<=7) {

                 if (mSectors[xx][yy] == C_NONE) {
                     list.add(new Trace(C_NONE, xx, yy));

                 } else if (mSectors[xx][yy] == C_KLINGON) {
                     // klingon destroy
                     mSectors[xx][yy] = C_NONE;
                     list.add(new Trace(C_KLINGON, xx, yy));
                     break;

                 } else if (mSectors[xx][yy] == C_STARBASE) {
                     // destroy starbase
                     mSectors[xx][yy] = C_NONE;
                     list.add(new Trace(C_STARBASE, xx, yy));
                     break;

                 } else if (mSectors[xx][yy] == C_STAR) {
                     // can not destroy star
                     list.add(new Trace(C_STAR, xx, yy));
                     break;

                 } // mSectors

             } else if ( xx<0 ) {
            list.add( new Trace( C_OUT, xx, yy ) );
        break;
        
    } else if ( xx>7 ) {
                list.add( new Trace( C_OUT, xx, yy ) );
        break;
        
     } else if ( yy<0 ) {
          list.add( new Trace( C_OUT, xx, yy ) );
        break;
        
     } else if ( yy>7 ) {
        list.add( new Trace( C_OUT, xx, yy ) );
        break;
   
        } // if 
               
} // while

 return list;

} // fire


private void log_map() {
    log_d( " log_map" );
         for ( int i=0; i<8; i++ ) {
          for ( int j=0; j<8; j++ ) {
            log_d( i + ", " + j + ": " +  mSectors[i][j] );   
} } // for i j
} // log_map

                /**
                 * log_d
                 */
            private static void log_d ( String msg ) {
                if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + msg );
            } // end of log_d

} // end of class QMap