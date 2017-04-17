/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */
 
 // add remaining_days, but not use
//        judgeWin();

 // firePhaser
// trace -> Coordinate

package jp.ohwada.android.startrek; 

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
	import java.lang.Math;
	
/*
 * Game
 */ 
public class Game {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Game";
    
    
     private static final int CMD_NONE = 0;
      private static final int CMD_LONG_SENSOR = 1; 
private static final int CMD_SHORT_SENSOR = 2;
                  private static final int CMD_TORPEDO = 3;  
            private static final int CMD_PHASER = 4;     
      private static final int CMD_IMPULSE = 5;     
      private static final int CMD_WARP = 6;


       private static final int ENERGY＿SUPPLY = 3000;
       private static final int TORPEDO＿SUPPLY = 10;
         private static final int SHIELD_LOW = 200;
       
 private Activity mActivity;

 Galaxy mGalaxy;
 QMap mQMap;
 
 private TextView mTextViewTitle;

    private TextView[][]  mTextViewMap = new TextView[8][8];


private int  remaining_days = 0;
private int  energy = 0;
private int  shield = 0;
private int num_torpedo = 0;


private boolean is_available_long_sensor = true;
private boolean is_available_short_sensor = true;
private boolean is_available_torpedo = true;
private boolean is_available_phaser = true;
private boolean is_available_impulse = true;
private boolean is_available_warp = true;


        private int mCmd = CMD_NONE;
        
        private boolean isFire = false;
 private boolean   isImpulse = false;
 //private boolean                        isWarp = false;
                private boolean isShortMap = false;    


        private boolean                 isDocked = false;
        
    /**
     * == constructor ===
     */
    public Game( Activity activity ) {
        mActivity = activity;
     mGalaxy = new Galaxy();
    mQMap = new QMap();
    
    } // Game end
         
         public void setTextViewTitle( TextView tv ) {
    mTextViewTitle = tv;
            }
   
   public void setTextViewMap(  TextView[][] tvmap ) {
    mTextViewMap = tvmap;
   }     
    /**
     * setup
     */
     
         public void setup() {
              mGalaxy.setupPosition();
           mGalaxy.setupQuadrants();
           mGalaxy.countNum();
              
   mQMap.setupPosition();
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
           mQMap.setupSectors( q );

remaining_days = 30 + 2 * mGalaxy.num_klingon;
log_d( "num_klingon " +mGalaxy.num_klingon );
log_d( "remaining_days " +remaining_days );

    supply();
         } // setup end



 private void supply(){
         
energy =  ENERGY＿SUPPLY;
num_torpedo = TORPEDO＿SUPPLY;

} // supply



public void procCmd( int cmd ) {
        log_d( "Cmd " + cmd );
//    toast_short( "Cmd " + cmd );
 //    mCmd = CMD_NONE;
     mCmd = cmd;
 //    isImpulse = false;                    
//     isWarp = false;
 
    equipment();
    warning();
    
    if ( cmd == CMD_LONG_SENSOR ) {
        scanLong();
        
 } else if ( cmd == CMD_SHORT_SENSOR ) {
        scanShort();
        
} else if ( cmd == CMD_TORPEDO ) {
//        isFire = true;
    log_d( "set fire " );
    
            } else if ( cmd == CMD_PHASER ) {
            firePhaser(); 
             
    } else if ( cmd ==  CMD_IMPULSE ) {
   //                 isImpulse = true;
    log_d( "set mpulse " ); 
              
    } else if ( cmd == CMD_WARP) {
//                    isWarp = true;
    log_d( "set warp " );
    
    } //if 
     judgeWin();
     
} // procCmd


private void warning() {
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
    if ( q.num_klingon > 0 ) {
     if ( shield < SHIELD_LOW ) {
        toast_short( "SHIELD_is LOW" );
    } // if
    } // if
       
} // warning

private void equipment() {
    
    if ( is_available_long_sensor ) {
        // 20 %
        if ( Math.random() > 0.8 ) {
     is_available_long_sensor = false;
     log_d( " long_sensor = false " );
} // random

} else {
            // 80 %
        if ( Math.random() > 0.2 ) {
     is_available_long_sensor = true;
       log_d( " long_sensor = true " );
} // random

} //  is_available

    if ( is_available_short_sensor ) {
        // 20 %
        if ( Math.random() > 0.8 ) {
     is_available_short_sensor = false;
      log_d( " lshort_sensor = false " );
     } // random
     
} else {
            // 80 %
        if ( Math.random() > 0.2 ) {
     is_available_short_sensor = true;
      log_d( " lshort_sensor = true " );
} // random

} //  is_available

    if ( is_available_torpedo ) {
        // 20 %
        if ( Math.random() > 0.8 ) {
     is_available_torpedo = false;
           log_d( " torpedo = false " );
     } // random
     
} else {
            // 80 %
        if ( Math.random() > 0.2 ) {
     is_available_torpedo = true;
                log_d( " torpedo = true " );
} // random
} // is_available

    if ( is_available_phaser ) {
        // 20 %
        if ( Math.random() > 0.8 ) {
     is_available_phaser = false;
                log_d( " phaser = false " );
     } // random

} else {
            // 80 %
        if ( Math.random() > 0.2 ) {
     is_available_phaser = true;
            log_d( " phaser = true " );
} // random

} // is_available

    if ( is_available_impulse ) {
        // 20 %
        if ( Math.random() > 0.8 ) {
     is_available_impulse = false;
            log_d( " impulse = false " );
          } // random
} else {
            // 80 %
        if ( Math.random() > 0.2 ) {
     is_available_impulse = true;
     log_d( " impulse = true " );
          } // random
          
} //  is_available


    if ( is_available_warp ) {
        // 20 %
        if ( Math.random() > 0.8 ) {
      is_available_warp = false;
          log_d( " warp = false " );
     } // random

} else {
            // 80 %
        if ( Math.random() > 0.2 ) {
     is_available_warp = true;
        log_d( " warp = true " );
          } // random
          
} // ifis_available



} //  equipment()



private void scanLong() {
     isShortMap = false; 
    mTextViewTitle.setText( "Long Renge Sensor" );
    String[][] map = mGalaxy.scanLong();
    displayMap( map );
    }
    
private void scanShort() {
     isShortMap = true; 
    mTextViewTitle.setText( "Short Renge Sensor" );
    String[][] map = mQMap.scanShort();
    displayMap( map );
    }
 
 private void fireTorpedoe ( int course ) {
    toast_short( "fire " + course );
   List<Coordinate> list = mQMap.fireTorpedoe( course );
    saveNumQuadrantEnterprise();
    
   for ( Coordinate t: list ) {
    log_d(  "trace " + t.code + ": " + t.x + ", " + t.y );
     if ( t.code == QMap.C_NONE ) {
    toast_short( "trace " + t.x + "," + t.y );
           setShortMapBackgroundColor( t.x, t.y, Color.YELLOW); 

} else if ( t.code == QMap.C_STAR ) {
    toast_short( " cannot destroy STAR" );
        setShortMapBackgroundColor( t.x, t.y, Color.YELLOW ); 
break;

} else if ( t.code == QMap.C_KLINGON_DESTROY ) {
    toast_short( "KLINGON destroyed " );
    setShortMapBackgroundColor( t.x, t.y, Color.RED );
break; 

} else if ( t.code == QMap.C_STARBASE_DESTROY ) {
    toast_short( "STARBASE destroyed " );
        setShortMapBackgroundColor( t.x, t.y, Color.RED ); 
break;

} else if ( t.code == QMap.C_OUT ) {
    toast_short( "miss" );
break;
} // if
} // for 
} // fire



/**
 * firePhaser
 */ 
private void firePhaser() {
          log_d( "fire Phaser"  );
      toast_short( "fire Phaser"  );
 //   List<Coordinate> list = new ArrayList<Coordinate>();
   List<Coordinate> list = mQMap.firePhaser();
   saveNumQuadrantEnterprise();


   for ( Coordinate c: list ) {
    if ( c.code == QMap.C_KLINGON_DESTROY ) {
//        displayklingonDestroy ( c.x, c.y );
 toast_short( "KLINGON destroyed " );
    setShortMapBackgroundColor( c.x, c.y, Color.RED );
    } // if
   } // for
} // firePhaser



    private void displayMap( String [][] map ) {
        for ( int i =0; i<8; i++ ) {
                    for ( int j =0; j<8; j++ ) {
                        mTextViewMap[i][j].setText(map[i][j]);
                        mTextViewMap[i][j].setBackgroundColor(Color.TRANSPARENT);
                    }} // for i j
    } // displayMap

public void procCourse( int n ) {
    toast_short( "Course " + n );
            if ( isFire ) {
                fireTorpedoe (n);
         } else if ( isImpulse ) {
                startInpulse (n);  
        } else if ( mCmd == CMD_WARP ) {
         moveLong (n);          
                } // if
       isFire = false;  
       isImpulse = false;
       mCmd = CMD_NONE;
            judgeWin();
    
} // procCourse

 private void startInpulse( int course ) {
    toast_short( "Inpulse " + course );
    Coordinate t = mQMap.startInpulse ( course );
     saveNumQuadrantEnterprise();
     
     if ( t == null ) {
     log_d( "t is null ");
         return;
     }

    if ( t.code == QMap.C_KLINGON_DESTROY ) {
    toast_short( "KLINGON destroyed " );
    setShortMapBackgroundColor( t.x, t.y, Color.RED );
} else if ( t.code == QMap.C_STARBASE ) {
        toast_short("dock in STARBASE ");
        dockinStarbase();
        
        } else if ( t.code == QMap.C_STAR ) {
        toast_short("landed  STAR ");
        
        } else if ( t.code == QMap.C_ENTERPRISE_MOVE ) {

// redraw map
            if ( isShortMap ) {
        scanShort();
    }
            toast_short("move to " + t.x + "," + t.y );

//        } else if ( t.code == QMap.C_OUT ) {
    
//        toast_short(" out of area ");

 } else {
    //        log_d(" out of area " +  t.code );
    int ret = mGalaxy.moveShort( t.code );
    if ( ret == Galaxy.WARP_SUCCESS ) {
        arriveQuadrant();  
    }

    } // if

 } // Inpulse

 private void moveLong( int course ) {
    toast_short( "warp " + course );
    int ret =  mGalaxy.moveLong(  course );
    if ( ret == Galaxy.WARP_SUCCESS ) {
                arriveQuadrant();  
   //         String msg = "warp SUCCESS " + mGalaxy.pos_x + "," + mGalaxy.pos_y;    
 //   log_d( msg );
    log_d( " warp SUCCESS ");
    //  toast_short( msg  );
   } else  if ( ret == Galaxy.WARP_OUT ) {
    log_d( " warp OUT ");  
         //  toast_short( "warp OUT "  );  
                        mGalaxy.setupPosition();
                  arriveQuadrant();                  
  //                                  String msg = "warp  OUT " + mGalaxy.pos_x + "," + mGalaxy.pos_y; 
//      toast_short( msg  );
          } // if

    String msg = "warp to " + mGalaxy.pos_x + "," + mGalaxy.pos_y;    
    log_d( msg );
} // moveLong


private void arriveQuadrant() {
          toast_short("arraive at " + mGalaxy.pos_x + "," + mGalaxy.pos_y ); 
   mQMap.setupPosition();
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
           mQMap.setupSectors( q );                    
    } // arriveQuadrant

    
    private void dockinStarbase() {
        isDocked = true;
      supply();
    } // dockinStarbase
    
private void saveNumQuadrantEnterprise() {
    int k = mQMap.num_klingon ;
    int b = mQMap.num_starbase;
     int s = mQMap.num_star;
mGalaxy.setNumQuadrantEnterprise( k, b, s );
mGalaxy.countNum(); 

}

private void judgeWin() {
       if ( mGalaxy.num_klingon == 0 ) {
        toast_short( " You WIN" );
    } // if
} // jugdeWin

private void setShortMapBackgroundColor( int x, int y, int color ) {
if ( isShortMap ) {
mTextViewMap[x][y].setBackgroundColor( color );
} // if
} // etShortMapBackgroundColor

// Color.TRANSPARENT

       /**
     * toast short
     */       
    private void toast_short( String msg ) {
        ToastMaster.makeText( mActivity, msg, Toast.LENGTH_SHORT).show();
    }
 
 
    /**
     * log_d
     */
    private static void log_d(String str) {
        
        if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + str);
    } // og_d end

} // clss end
