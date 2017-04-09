/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

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
 
 private Activity mActivity;

 Galaxy mGalaxy;
 QMap mQMap;
 
 private TextView mTextViewTitle;

    private TextView[][]  mTextViewMap = new TextView[8][8];

        private boolean isFire = false;
 private boolean   isImpulse = false;
                private boolean isShortMap = false;    
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
   
   mQMap.setupPosition();
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
           mQMap.setupSectors( q );

         } // setup end
          
     
           
public void procCmd( int cmd ) {
    toast_short( "Cmd " + cmd );
     isImpulse = false;
    if ( cmd == 1) {
        scanLong();
 } else if ( cmd == 2) {
        scanShort();
} else if ( cmd == 3) {
        isFire = true;
    log_d( "set fire " );
    } else if ( cmd == 5) {
                    isImpulse = true;
    log_d( "set mpulse " );
    } //if 
} // procCmd

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
   List<Trace> list = mQMap.fireTorpedoe(course);
   for ( Trace t: list ) {
    log_d(  "trace " + t.code + ": " + t.x + ", " + t.y );
if ( t.code == QMap.C_KLINGON ) {
    toast_short( "KLINGON destroyed " );
    setShortMapBackgroundColor( t.x, t.y, Color.RED );
break; 
} else if ( t.code == QMap.C_STARBASE ) {
    toast_short( "STARBASE destroyed " );
        setShortMapBackgroundColor( t.x, t.y, Color.RED ); 
break;
} else if ( t.code == QMap.C_STAR ) {
    toast_short( "STAR destroyed " );
        setShortMapBackgroundColor( t.x, t.y, Color.RED ); 
break;
} else if ( t.code == QMap.C_OUT ) {
    toast_short( "out of area" );
break;
} else if ( t.code == QMap.C_NONE ) {
    toast_short( "trace " + t.x + "," + t.y );
           setShortMapBackgroundColor( t.x, t.y, Color.YELLOW); 
} // if
} // for 
} // fire


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
                } // if
       isFire = false;  
       isImpulse = false;
} // procCourse

 private void startInpulse( int course ) {
    toast_short( "Inpulse " + course );
    Trace t = mQMap.startInpulse ( course );
    if ( t.code == QMap.C_KLINGON ) {
    toast_short( "KLINGON destroyed " );
    setShortMapBackgroundColor( t.x, t.y, Color.RED );
} else if ( t.code == QMap.C_STARBASE ) {
        toast_short("dock in STARBASE ");
        dockinStarbase();
        } else if ( t.code == QMap.C_OUT ) {
        toast_short(" out of area ");
    } // if
    if ( isShortMap ) {
        scanShort();
    }
 } // Inpulse


    private void dockinStarbase() {
      // dummy
    } //

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
