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
 * QMap
 */ 
public class QMap {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "QMap";
    
         private  static final int SIZE_X = 8;
                    private  static final int SIZE_Y = 8;

         
    public static final int C_NONE = 0;
      public static final int C_ENTERPRISE = 1; 
    public static final int C_STARBASE = 2;
    public static final int C_KLINGON = 3;
    public static final int C_STAR = 4;

  public static final int C_ENTERPRISE_MOVE = 11;
          public static final int C_KLINGON_COLLIDE = 12;
        public static final int C_KLINGON_DESTROY = 13;
                public static final int C_STARBASE_DESTROY = 14;
       public static final int C_OUT = 15; 
       
       public static final int MOVE_OUT_LEFT = 21; 
        public static final int MOVE_OUT_RIGHT = 22; 
               public static final int MOVE_OUT_UP = 23; 
                      public static final int MOVE_OUT_DOWN = 24; 
         
          private static final String LF = "\n";
                                      
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
 
    mSectors = new int[8][8];

clearSectors();

//        for ( int i = 0; i < 8; i++) {
 //           for ( int j = 0; j < 8; j++) {
 //               mSectors[i][j] = C_NONE;
//        }} // for i j end      

  log_map() ;
 
    } // end of QMap

// clear 
//    mSectors = new int[8][8];

             
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
         
clearSectors();
        
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
          
          log_map() ;
          
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


    /**
     * scanShort
     */
   public TorpedoData getTorpedoData( ) {

log_d("getTorpedoData");

     List<TorpedoTarget> list = new ArrayList<TorpedoTarget>();
       String[][] map = new String[SIZE_X][SIZE_Y];
       String report = "TorpedoData" + LF;
       
       String mark = "  ";
    
       for ( int i=0; i<SIZE_X; i++ ) {
          for ( int j=0; j<SIZE_Y; j++ ) { 
          
  mark = "  ";
  
             if ( mSectors[i][j] == C_ENTERPRISE ) {
            mark = "E ";
            
         }  else if ( mSectors[i][j] == C_KLINGON ) {
            int course = Course.getCourse( pos_x, pos_y, i, j );
            list.add( new TorpedoTarget(  C_KLINGON, i, j, course ) );
            mark = "K ";
       String msg = "x= "+ i + ", y= " + j + ", course= " + course + LF;
       report += msg;
       log_d(msg);
           } // if     
       
       map[i][j] = mark;
       
          }} // for i j
          
          
    TorpedoData ret = new TorpedoData( list, map, report );
    return ret;      
 } //    getTorpedoData      
     
          
 public Coordinate startInpulse ( int course ) {
    log_d( "startInpulse " + course );
    Coordinate ret =  new Coordinate( 0, 0, 0 );
    int[] delta = Course.getDelta( course );
    int xd = delta[0];
        int yd = delta[1];
        int xx = pos_x + xd;
int yy = pos_y + yd;
    log_d( "xy " + xx + "," + yy );
    // left
    if ( xx<0 ) {
           ret =  new Coordinate( MOVE_OUT_LEFT, xx, yy );
           log_d( MOVE_OUT_LEFT + ":"+xx + ","+yy );
           return ret;
//        break;

// right
    } else if ( xx>7 ) {
           ret =  new Coordinate( MOVE_OUT_RIGHT, xx, yy );
                    log_d( MOVE_OUT_RIGHT + ":"+xx + ","+yy );
                      return ret;
//        break;

// up
     } else if ( yy<0 ) {
                     ret =  new Coordinate( MOVE_OUT_UP, xx, yy );
                    log_d( MOVE_OUT_UP + ":"+xx + ","+yy );
                      return ret;
//        break;
// down
     } else if ( yy>7 ) {
                   ret =  new Coordinate( MOVE_OUT_DOWN, xx, yy );
                      log_d( MOVE_OUT_DOWN + ":"+xx + ","+yy );
           return ret;
//        break;

   } else if ( mSectors[xx][yy] == C_KLINGON ) {
    decrementKlingon();
        mSectors[xx][yy] = C_NONE;
                          ret =  new Coordinate( C_KLINGON_DESTROY, xx, yy );
                     log_d( C_KLINGON + ":"+xx + ","+yy );
           return ret;
    
      } else if ( mSectors[xx][yy] == C_STARBASE ) {
                          ret =  new Coordinate( C_STARBASE, xx, yy );
           return ret;
              
                 }  else if ( mSectors[xx][yy] == C_STAR ) {
                          ret =  new Coordinate( C_STAR, xx, yy );
           return ret; 
           
 } else if ( mSectors[xx][yy] == C_NONE ) {
    // move 

          mSectors[pos_x][pos_y] = C_NONE;
     mSectors[xx][yy] = C_ENTERPRISE;
       this.pos_x = xx;
     this.pos_y = yy;                   ret =  new Coordinate( C_ENTERPRISE_MOVE, xx, yy );
     log_d( C_NONE + ":"+xx + ","+yy );
           return ret;   
} // if
     return null; // dummy
} // startInpulse


/**
 * fireTorpedoe
 * @ param int course 
 * @ return  List<Coordinate>
 */
public List<Coordinate> fireTorpedo( int course ) {

    log_d( "fireTorpedo " + course  );
    List<Coordinate>  list  = new ArrayList<Coordinate>();
    if (( course <  Course.COURSE_MIN ) || ( course > Course.COURSE_MAX )) {
        log_d( " param error " );
        return list;
    } // if
        
        int[] delta = Course.getDelta( course );
    int xd = delta[0];
        int yd = delta[1];
        


int xx = pos_x;
int yy = pos_y;

// endless loop
while (true) {

   xx += xd;
    yy += yd;
    
             if ( xx>=0 && xx<=7 &&  yy>=0 && yy<=7) {

                 if (mSectors[xx][yy] == C_NONE) {
                     list.add(new Coordinate(C_NONE, xx, yy));

                 } else if (mSectors[xx][yy] == C_KLINGON) {
                     // klingon destroy
                     decrementKlingon();
                     mSectors[xx][yy] = C_NONE;
                     list.add(new Coordinate(C_KLINGON_DESTROY, xx, yy));
                     break;

                 } else if (mSectors[xx][yy] == C_STARBASE) {
                     // destroy starbase
                     decrementStarbase();
                     mSectors[xx][yy] = C_NONE;
                     list.add(new Coordinate(C_STARBASE_DESTROY, xx, yy));
                     break;

                 } else if (mSectors[xx][yy] == C_STAR) {
                     // can not destroy star
                     list.add(new Coordinate(C_STAR, xx, yy));
                     break;

                 } // mSectors

             } else if ( xx<0 ) {
            list.add( new Coordinate( C_OUT, xx, yy ) );
        break;
        
    } else if ( xx>7 ) {
                list.add( new Coordinate( C_OUT, xx, yy ) );
        break;
        
     } else if ( yy<0 ) {
          list.add( new Coordinate( C_OUT, xx, yy ) );
        break;
        
     } else if ( yy>7 ) {
        list.add( new Coordinate( C_OUT, xx, yy ) );
        break;
   
        } // if 
               
} // while

 return list;

} // firefireTorpedoe


/**  
* firePhaser
* @ return List<Coordinate>
 */           
public  List<Coordinate> firePhaser() {
    
    log_d( "firePhaser" );
  List<Coordinate> list = new ArrayList<Coordinate>();
 
  // search Klingon
    for ( int i=0; i<8; i++ ) {
            for ( int j=0; j<8; j++ ) {

        if ( mSectors[i][j]  ==  C_KLINGON ) {
    log_d( "KLINGON " + i + "," + j );
                        // 80 %
            if ( Math.random() > 0.2 ) {

                // destroy KLINGON
                    decrementKlingon();
                    log_d( "KLINGON destroy " + i + "," + j );
                // decrementKlingon();
                mSectors[i][j] = C_NONE;
           list.add( new Coordinate( C_KLINGON_DESTROY, i, j ) );              
            } // if Math.random
            } // mSectors
            
}} // for i,j
 
 return list;
 } // firePhaser



public  List<Coordinate> getKlingons() {


    log_d( "getKlingons" );
  List<Coordinate> list = new ArrayList<Coordinate>();


  // search Klingon
    for ( int i=0; i<8; i++ ) {
        for (int j = 0; j < 8; j++) {

            if (mSectors[i][j] == C_KLINGON) {
                log_d("KLINGON " + i + "," + j);
                list.add( new Coordinate(C_KLINGON, i, j) );
            } // if

        } } // for i j
        return list;


    } // getKlingons


private void decrementKlingon() {
if ( num_klingon >0 ) {
    num_klingon --;
} // if
} //decrementKlingon


private void decrementStarbase() {
if ( num_starbase >0 ) {
    num_starbase --;
} // if
} //decrementStarbase

    /**
     * clearSectors
     */ 
private void clearSectors() {
        for ( int i = 0; i < 8; i++) {
            for ( int j = 0; j < 8; j++) {
                mSectors[i][j] = C_NONE;
        }} // for i j end 
        } //clearSectors


                /**
                 * log_map
                 */
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