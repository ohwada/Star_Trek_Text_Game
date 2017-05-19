/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */
 
// counterAttack()

 // mTextViewReport
 
 // procShield()
 
 // add remaining_days, but not use
//        judgeWin();

 // firePhaser
// trace -> Coordinate

package jp.ohwada.android.startrek; 

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
	import java.lang.Math;

import jp.ohwada.android.startrek.util.*;
import jp.ohwada.android.startrek.dialog.*;
	
/*
 * Game
 */ 
public class Game {

   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "Game";
     private static final boolean DEBUG_DEVICE_AVAILABLE = false;
     
      private static final int SIZE_X = 8;
      private static final int SIZE_Y = 8;
            
 // device   
       private static final int DEVICE_LONG_SENSOR = 0; 
private static final int DEVICE_SHORT_SENSOR = 1;
                  private static final int DEVICE_TORPEDO = 2;  
            private static final int DEVICE_PHASER = 3;     
      private static final int DEVICE_IMPULSE = 4;     
      private static final int DEVICE_WARP = 5;
      private static final int DEVICE_SHIELD = 6;
      private static final int DEVICE_COMPUTER = 7;
            private static final int MAX_DEVICE = 8;
            
// command               
     private static final int CMD_NONE = 0;
      private static final int CMD_LONG_SENSOR = 1; 
private static final int CMD_SHORT_SENSOR = 2;
private final int CMD_PHASER = 3;
                  private static final int CMD_TORPEDO = 4;  
                  private static final int CMD_SHIELD = 5;
                          private static final int CMD_DAMEGE = 6;
    private static final int CMD_IMPULSE = 7;
      private static final int CMD_WARP = 8;

      private static final int CMD_COMPUTER = 9;
      
     // computer sub command      
      private static final int CMD_STATUS = 10;
              private static final int CMD_GALAXY_MAP = 11;            private static final int CMD_TORPEDO_DATA = 12;  
          
          
          private static final int MAP_MODE_NONE = 0;             
private static final int MAP_MODE_LONG = 1;  
private static final int MAP_MODE_SHORT = 2;  
private static final int MAP_MODE_GALAXY = 3;  
private static final int MAP_MODE_TORPEDO_DATA = 4; 


// days
  private static final int  DAYS_PER_CMD = 1;
    private static final int  DAYS_WARP = 1;
    
  // engy
  private static final int ENEGY_PER_CMD =1;
  
  //     private static final int ENERGY＿SUPPLY = 3000;
                 
                     private static final int ENERGY_PHASER = 100;  
                         private static final int ENERGY_WARP = 10;  
                    
                     private static final int ENERGY_SHIELD = 200;
              
        // TORPEDO       
//       private static final int TORPEDO＿SUPPLY = 10;

          private static final int TORPEDO_FIRE = 1;
       
         private static final int SHIELD_LOW = 200;
         
               private static final double DEVICE_AVAILABLE_PROBABILITY_FAIL = 0.9; // 10 %
                 private static final double DEVICE_AVAILABLE_PROBABILITY_RECOVER = 0.2; // 80 %

          private static final String LF = "\n";
          
                
 private Activity mActivity;
private Resources mResources;
private String mPackageName;


 Galaxy mGalaxy;
 QMap mQMap;
 
 private TextView mTextViewMapTitle;
 private TextView mTextViewReportTitle; 
 
 private TextView mTextViewReport;
 
    private TextView[][]  mTextViewMap = new TextView[SIZE_X][SIZE_Y];
    
    private CommandDialog mCommandDialog;
        private ComputerDialog mComputerDialog;
    private CourseDialog mCourseDialog;
     private CloseDialog mDialogMission;
          private CloseDialog mDialogWin;
             private YesNoDialog mDialogPhaser;
             
private int  mDaysRemain = 0;
private int mDaysElapse = 0;
private int  mEnergy = 0;
private int  mShield = 0;
private int mTorpedo = 0;

private long mTimeStart = 0;

private int mSupplyEnergy = 0;
private int mSupplyTorpedo = 0;

// device
private boolean[] isDeviceAvailables = new boolean[MAX_DEVICE];

private String[] mDeviceNames = new String[MAX_DEVICE];



        private int mCmd = CMD_NONE;
        
         private int mMapMode = MAP_MODE_NONE;
            


        private boolean                 isDocked = false;
        
    /**
     * == constructor ===
     */
    public Game( Activity activity ) {
        
        mActivity = activity;
        mResources = activity.getResources();
        mPackageName = activity.getPackageName();
//    initView( view );    
     mGalaxy = new Galaxy();
    mQMap = new QMap();
    initDialog();
    initVariable();
    
    } // Game

// =========== 
//  initialize
// =========== 

 /**
  * initView
  */
  private void initView ( View view ) {
 
 log_d( " initView " + view );
    
 mTextViewMapTitle = (TextView) view.findViewById( R.id.TextView_map_title );

 mTextViewReportTitle = (TextView) view.findViewById( R.id.TextView_report_title );

 mTextViewReport = (TextView) view.findViewById( R.id.TextView_report );  
 
            for (int i=0; i<SIZE_X; i++ ) {
           for (int j=0; j<SIZE_Y; j++ ) {

               String name = "TextView_map_" + Integer.toString(i) + "_" + Integer.toString(j) ;
               int id = mResources.getIdentifier( name, "id",  
               mPackageName );
                       TextView tv = (TextView)          view.findViewById(id);
                       log_d( name + id + tv );
                       if (tv != null ) {
                              mTextViewMap[i][j] = tv;
                        } // if

}} // for i j end

  
               
} //  initView

 
 
 
 
 
 
 
 
 
 private void initDialog() {   
 
        mCommandDialog = new CommandDialog( mActivity );
       mCommandDialog.create();
        mCommandDialog.setOnChangedListener(
            new CommandDialog.OnChangedListener() {
                @Override
      public void onCmdClick( int cmd ) {
                          mCommandDialog.dismiss();
                    procCmd( cmd );
            }
    }); // setOnChangedListener
 
    
         mComputerDialog = new ComputerDialog( mActivity );
       mComputerDialog.create();
        mComputerDialog.setOnChangedListener(
            new  ComputerDialog.OnChangedListener() {
                @Override
      public void onCmdClick( int cmd ) {
                              mComputerDialog.dismiss();
                    procCmd( cmd );
            } // onCmdClick
    }); // setOnChangedListener
       
       
       mCourseDialog = new CourseDialog( mActivity );
       mCourseDialog.create();
        mCourseDialog.setOnChangedListener(
            new CourseDialog.OnChangedListener() {
                @Override
      public void onCourseClick( int course ) {
                                         mCourseDialog.dismiss();
                    procCourse( course );
            }
    }); // setOnChangedListener
  
  

      mDialogPhaser = new YesNoDialog( mActivity );
            mDialogPhaser.create();
        mDialogPhaser.setTitle( R.string.cmd3 );
                mDialogPhaser.setNo( R.string.dialog_button_cansel );
              mDialogPhaser.setOnChangedListener(
            new YesNoDialog.OnChangedListener() {
                @Override
      public void onYesClick( ) {
                mDialogPhaser.dismiss();
        firePhaser();
            }
            
                            @Override
      public void onNoClick(  ) {
             mDialogPhaser.dismiss();
            }
            
    }); // setOnChangedListener
    
    
    
    } // initDialog


private void initVariable() {
    mDeviceNames[ DEVICE_LONG_SENSOR] = "LONG RENGE SENSOR";
        mDeviceNames[ DEVICE_SHORT_SENSOR] = "SHORT RENGE SENSOR";
        mDeviceNames[ DEVICE_TORPEDO] = "TORPEDO TUBE";
                mDeviceNames[ DEVICE_PHASER] = "PHASER";
                                mDeviceNames[ DEVICE_IMPULSE] = "IMPULSE ENGINE"; 
                                                                mDeviceNames[ DEVICE_WARP] = "WARP ENGINE"; 
                                                 mDeviceNames[ DEVICE_SHIELD] = "SHIELD CTRL";  
                                                    mDeviceNames[ DEVICE_COMPUTER] = "COMPUTER";   
  for ( int k=0; k< MAX_DEVICE; k++ ) {
    isDeviceAvailables[k] = true;
  } // for
                                                           
} // init


         
    
            
 // ====================  
  // set UI
// ====================
    
       /**
     * etTextViewMapTitle
     */
         public void setTextViewMapTitle( TextView tv ) {
            mTextViewMapTitle = tv ;
         } // etTextViewM<apTitle
         
                /**
     * setTextViewMap
     */
         public void setTextViewMap( TextView[][] tv ) {
            mTextViewMap = tv ;
         } // etTextViewM<ap
         
            /**
     * setTextViewReportTitle
     */
         public void setTextViewReportTitle( TextView tv ) {
            mTextViewReportTitle = tv;
         } // etTextViewReportTitle
   
            /**
     * etTextViewReport
     */
         public void setTextViewReport( TextView tv ) {
            mTextViewReport = tv;
         } // etTextViewReport
         
 // ====================  
  // startnewGame
// ====================
   
     /**
     * startnewGame
     */
         public void startNewGame() {  
            setup();
            showMission();
         } //startnewGame
         
      
      
          
    /**
     * setup
     */
         private void setup() {
              mGalaxy.setupPosition();
           mGalaxy.setupQuadrants();
           mGalaxy.countNum();
              
   mQMap.setupPosition();
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
           mQMap.setupSectors( q );
           
mDaysElapse = 0;
mDaysRemain = 30 + 2 * mGalaxy.num_klingon;
log_d( "num_klingon " + mGalaxy.num_klingon );
log_d( "num_starbase " + mGalaxy.num_starbase );

              log_d( "remaining_days " + mDaysRemain );
             
        double klingon_per_starbase = mGalaxy.num_klingon / mGalaxy.num_starbase;
             
  mSupplyEnergy = 1000 + (int)( 20 * klingon_per_starbase );
 mSupplyTorpedo =  10 + (int)( 10 * klingon_per_starbase );
           
           log_d( "SupplyEnergy " + mSupplyEnergy );
              log_d( "SupplyTorpedo " + mSupplyTorpedo );
                           
    supply();
         } // setup end

    /**
     * showMission
     */
private void showMission() {
       log_d( "showMission" ); 
    String format = getString( R.string.mission_msg );
    log_d( format );

     String msg = String.format( format, mGalaxy.num_klingon, mDaysRemain, mGalaxy.num_starbase );
       log_d( msg ); 
       
  mDialogMission = new CloseDialog( mActivity );
            mDialogMission.create();
        mDialogMission.setTitle( R.string.mission_title );
        mDialogMission.setMessage( msg );
            mDialogMission.show();
} // showMission

    /**
     * showWin
     */
private void showWin() {
       log_d( "showWin" ); 
    String format = getString( R.string.win_msg );
    log_d( format );

     String msg = String.format( format,  mDaysElapse  );
       log_d( msg ); 
       
  mDialogWin = new CloseDialog( mActivity );
            mDialogWin.create();
        mDialogWin.setTitle( R.string.win_title );
        mDialogWin.setMessage( msg );
            mDialogWin.show();
} // showWin

 // ====================  
  // command
// ====================

/**
* showCommandDialog
 */
 public void showCommandDialog( ) {
   
   log_d( " showCommandDialog" ); 
    mCommandDialog.show();
    
  warningShield();
    
} //showCommandDialog


/**
 * procCmd
 */
public void procCmd( int cmd ) {
        log_d( "Cmd " + cmd );
     mCmd = cmd;
     
     log_d( "remaining_days " + mDaysRemain );
         log_d( " energy " +  mEnergy );

    changeDeviceAvailable();
    
  //  warningShield();

    
    if ( cmd == CMD_LONG_SENSOR ) {
                scanLong();
        
 } else if ( cmd == CMD_SHORT_SENSOR ) {
            scanShort();

          } else if ( cmd == CMD_PHASER ) {
            procPhaser(); 
            
} else if ( cmd == CMD_TORPEDO ) {
    procTorpedo();
     
  
             
    } else if ( cmd ==  CMD_IMPULSE ) {
        procImpulse();
               
    } else if ( cmd == CMD_WARP ) {
       procWarp();

     } else if ( cmd == CMD_SHIELD ) {
        procShield();



         } else if ( cmd == CMD_DAMEGE ) {
        procDamege();
        
          } else if ( cmd == CMD_COMPUTER ) {
        procComputer();  
        
            } else if ( cmd == CMD_STATUS ) {
        procStatus();    
          
         } else if ( cmd == CMD_GALAXY_MAP ) {
        procGalaxyMap();
        
         } else if ( cmd == CMD_TORPEDO_DATA ) {
        procTorpedoData(); 
                   
    } //if cmd
 
 
 
     // judgeWin();
   
//　decreases　in underway
     elapseDays( DAYS_PER_CMD );
     consumeEnergy( ENEGY_PER_CMD);
  
} // procCmd


/**
 * procPhaser
 */
private void procPhaser() {
    
            if ( !check_tost_DeviceAvailable( DEVICE_PHASER, true ) ) {
// mcmd = CMD_NONE;
return;
} // if

        if ( ! check_toast_Energy( ENERGY_PHASER, true ) ) {
            return;
        } // if

mDialogPhaser.show();

} // procPhase


/**
 * procTorpedo
 */
    private void procTorpedo() {
     log_d(" procTorpedo " +  mTorpedo );

        if ( !check_tost_DeviceAvailable( DEVICE_TORPEDO, true ) ) {
mCmd = CMD_NONE;
return;
} // if

         if ( mTorpedo < TORPEDO_FIRE ) {
                toast_short(  "TORPEDO exceed  " ); 
                log_d(  "TORPEDO exceed  " );                 
                mCmd = CMD_NONE;
                return;
        } // if
       
 showCourseDialog( R.string.cmd4 );
} // procTorpedo

/**
 * procImpulse
 */
private void procImpulse() {

        if( !check_tost_DeviceAvailable( DEVICE_IMPULSE, true ) ) {
        mCmd = CMD_NONE; 
        return;
        } // if
        
         showCourseDialog( R.string.cmd7 );
} // procimpulse

/**
 * procWarp
 */
private void procWarp() {

        if( !check_tost_DeviceAvailable( DEVICE_WARP, true ) ) {
        mCmd = CMD_NONE; 
        return;
        } // if
        
                             if ( ! check_toast_Energy( ENERGY_WARP, true ) ) {
           mCmd = CMD_NONE;
           return;
        } // if
        
         showCourseDialog( R.string.cmd8 );
} //  procWarp


/**
 * procComputer
 */
private void procComputer() {

log_d( "procComputer" );
        if( !check_tost_DeviceAvailable( DEVICE_COMPUTER, true ) ) {
        return;
        } // if 

mComputerDialog.show();
log_d( "ComputerDialog.show" );
 
 } //procComputer
 
 
 



/**
 * procStatus
 */
private void procStatus() {
     log_d("procStatus");
     if( !check_tost_DeviceAvailable(  DEVICE_COMPUTER, true ) ) {
        return;
    }
    
     String msg = "";
msg += "NUMBER OF KLINGONS LEFT = " + mGalaxy.num_klingon + LF;
msg += "NUMBER OF STARDATES LEFT = " + mDaysRemain + LF;
msg += "NUMBER OF STARBASES LEFT = " +mGalaxy.num_starbase + LF;
msg+=  "Energy=" +  mEnergy + LF;
msg+=  "Shield=" +  mShield + LF;
msg+=  "NUMBER OF TORPEDO = " +  mTorpedo + LF;
log_d(msg);


mTextViewReportTitle.setText( R.string.report_title_status );
mTextViewReport.setText( msg );
toast_short("STATUS Reported");
    
}// procStatus

/**
 *  procDamege
 */
private void procDamege() {
     log_d("procDamege");
     String msg = "";
       for ( int k=0; k< MAX_DEVICE; k++ ) {
        
        msg += mDeviceNames[k] + ": ";
    if( isDeviceAvailables[k] ) {

        msg += "Normal" + LF;

    } else{
      msg += "Dameged" + LF; 
       
     }  // if
          
  } // for
  log_d( msg ); 
  
  mTextViewReportTitle.setText( R.string.report_title_damege );  
mTextViewReport.setText( msg );
 toast_short("Damege Reported");
     
} // private


/**
 *  procShield
 */
private void procShield() {
 
 log_d("procShield");
    
 
 if ( !check_tost_DeviceAvailable( DEVICE_SHIELD,true )) {
    return;
} // if check_tost_DeviceAvailable

if ( ! check_toast_Energy( ENERGY_SHIELD, true ) ) {
    return;
} // if

consumeEnergy( ENERGY_SHIELD );
mShield = ENERGY_SHIELD;

toast_short("Shield  on ");

} // procShield


















  





/**
 *  scanLong
 */
private void scanLong() {
    
  if ( !check_tost_DeviceAvailable( DEVICE_LONG_SENSOR, true ) ) {
        return;
    } // if
       
     mMapMode = MAP_MODE_LONG;
                  mTextViewMapTitle.setText( R.string.map_title_long );
    String[][] map = mGalaxy.scanLong( isDeviceAvailables[ DEVICE_COMPUTER ] );
    displayMap( map );
    toast_short( "Long Renge Sensor scaned"  );
    
    } // scanLong
    

/**
 *  scanShort
 */    
private void scanShort() {
     
  if ( !check_tost_DeviceAvailable( DEVICE_SHORT_SENSOR, true ) ) {
        return;
    } // if
    
    displayMapShort();
    toast_short( "Short Renge Sensor scaned"  );
    
} // scanShort


/**
 * displayMapShort
 */    
private void displayMapShort() {
//     isShortMap = true; 
          mMapMode = MAP_MODE_SHORT;
              mTextViewMapTitle.setText( R.string.map_title_short );
    String[][] map = mQMap.scanShort();
    displayMap( map );
    
} // displayMapShort


/**
 *  procGalaxyMap
 */  
 private void procGalaxyMap() { 
 
 log_d("procGalaxyMap ");
   if ( !check_tost_DeviceAvailable( DEVICE_COMPUTER,true ) ) {
        return;
    } // if
       
//      isShortMap = false; 
                mMapMode = MAP_MODE_GALAXY;
    mTextViewMapTitle.setText( R.string.map_title_galaxy );
    String[][] map = mGalaxy.getGalaxyMap();
    displayMap( map );
     toast_short( "Galaxy Map displayed"  );  
      
 } // procGalaxyMap
 
 
 /**
 *  procTorpedoData
 */
 private void procTorpedoData() { 
 
 log_d("procTorpedoData ");
   if ( !check_tost_DeviceAvailable( DEVICE_COMPUTER, true ) ) {
        return;
    } // if
    
    TorpedoData td = mQMap.getTorpedoData();
//      isShortMap = true; 
                      mMapMode = MAP_MODE_TORPEDO_DATA;
    mTextViewMapTitle.setText( R.string.map_title_torpedo );
    displayMap( td.map );
    mTextViewReport.setText( td. report );
//     fireAuto( td. list );
 } // procTorpedoData
   
  
  private void fireAuto( List<TorpedoTarget> list ) {
    
    log_d( "fireAuto" );
    if ( mTorpedo < list.size() ) {                toast_short(  "TORPEDO exceed  " );
                log_d(  "TORPEDO exceed  " ); 
                return;
        
    } // if 
    
    for( TorpedoTarget tt: list ) {   
    int course = tt.course;
            if ( ( course >=  Course.COURSE_MIN ) && ( course <= Course.COURSE_MAX ) ) { 
      fireTorpedo ( course );
     } // if     
    } // for
    
} // fireAuto
  
// ==========
// Course
// ==========

/**
 * showCourseDialog
 */
 private void showCourseDialog( int res_id ) { 
  
 mCourseDialog.setTitle( res_id );
    mCourseDialog.show();
      
} //showCourseDialog
 

/**
 * procCourse
 */ 
public void procCourse( int course ) {
      
    log_d( "Course " + course );
    log_d( "mCmd " + mCmd );
    
    
          if ( mCmd == CMD_TORPEDO ) {
                fireTorpedo ( course );
                
            } else if ( mCmd == CMD_IMPULSE ) {
                startInpulse ( course );  
                
        } else if ( mCmd == CMD_WARP ) {
         moveLong ( course );  
                 
                } // if

       mCmd = CMD_NONE;
   //         judgeWin();
    
} // procCourse


/**
 * startInpulse
 */ 
 private void startInpulse( int course ) {
    log_d(  "startInpulse " + course );
        isDocked = false;
  boolean is_klingon_destroy = false;
    toast_short( "moving ... "  );
    Coordinate t = mQMap.startInpulse ( course );
     saveNumQuadrantEnterprise();
     
     if ( t == null ) {
     log_d( "t is null ");
         return;
     }

    if ( t.code == QMap.C_KLINGON_COLLIDE ) {
        // collide to KLINGON
         is_klingon_destroy = true;
    toast_short( "KLINGON destroyed " );
    // damege on ennterprise
    mShield = 0;
         setAllDeviceAvailable( false );
         
    setShortMapBackgroundColor( t.x, t.y, Color.RED );
} else if ( t.code == QMap.C_STARBASE ) {
        toast_short("dock in STARBASE ");
        dockinStarbase();
        
        } else if ( t.code == QMap.C_STAR ) {
        toast_short("landed  STAR ");
        
        } else if ( t.code == QMap.C_ENTERPRISE_MOVE ) {

// redraw map
            if (  mMapMode == MAP_MODE_SHORT ) {
        displayMapShort();
    } // if mMapMode
    
            toast_short("move to Section " + t.x + "," + t.y );

//        } else if ( t.code == QMap.C_OUT ) {
    
//        toast_short(" out of area ");

 } else {
    //        log_d(" out of area " +  t.code );
    int ret = mGalaxy.moveShort( t.code );
    if ( ret == Galaxy.WARP_SUCCESS ) {
        arriveQuadrant();  
    }

    } // if
    
 if ( is_klingon_destroy ) {   
   saveNumQuadrantEnterprise();
       judgeWin();
      } // if
   
 } // Inpulse
 
 
/**
 * moveLong
 */
 private void moveLong( int course ) {
    

     log_d( "warp " + course );       
    toast_short( "warpping ... " );
    int ret =  mGalaxy.moveLong(  course );
    
    //　decreases　in warp
    // elapseOneDay();
 // this.remaining_days --;
 
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

/**
 * arriveQuadrant
 */
private void arriveQuadrant() {
          toast_short("warp to Quadrant " + mGalaxy.pos_x + "," + mGalaxy.pos_y ); 
   mQMap.setupPosition();
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
           mQMap.setupSectors( q );                    
    } // arriveQuadrant


 /**
 * dockinStarbase
 */   
    private void dockinStarbase() {
        isDocked = true;
      supply();       
      // repair
     setAllDeviceAvailable( true );
    } // dockinStarbase
    
// ==========
// battle
// ==========  

/**
 * firePhaser
 */ 
 private void fireTorpedo ( int course ) {
    
log_d( "fireTorpedo " + course );
        if (( course <  Course.COURSE_MIN ) || ( course > Course.COURSE_MAX )) {
                    log_d( " param error " );
            return;
        } // if
        
    toast_short( "fire Torpedo " );        
   List<Coordinate> list = mQMap.fireTorpedo( course );
    
    if ( list.size() == 0 ) {
        log_d("no result");
        return;
    } // if

    // decrement , when fire one
    mTorpedo --;
    if ( mTorpedo <0 ) {
       mTorpedo = 0; 
    } // if
      log_d( "num_torpedo -- " + mTorpedo );
   
   for ( Coordinate t: list ) {
    log_d(  "trace " + t.code + ": " + t.x + ", " + t.y );
     if ( t.code == QMap.C_NONE ) {
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

   saveNumQuadrantEnterprise();
       judgeWin();
   fightBack();
   
} // fireTorpedo

/**
 * firePhaser
 */ 
private void firePhaser() {
          log_d( "fire Phaser"  );
          if( !check_tost_DeviceAvailable( DEVICE_PHASER, true ) ) {
             return;
        } // if
                 
         if ( ! check_toast_Energy( ENERGY_PHASER, true ) ) {
            return;
        } // if
      toast_short( "fire Phaser"  );
 //   List<Coordinate> list = new ArrayList<Coordinate>();
   List<Coordinate> list = mQMap.firePhaser();
   saveNumQuadrantEnterprise();

   for ( Coordinate c: list ) {
    if ( c.code == QMap.C_KLINGON_DESTROY ) {
//        displayklingonDestroy ( c.x, c.y );
 toast_short( "KLINGON destroyed " );
    setShortMapBackgroundColor( c.x, c.y, Color.RED );
    }  // if c.code
    
  } // for
   
   // Do not use icontinuously
// because　game becomes too easy,   
// 50 %
if ( Math.random() > 0.5 ) {
    isDeviceAvailables[ DEVICE_PHASER ] = false;
}  // if

    saveNumQuadrantEnterprise();
    judgeWin();
   fightBack();
   
} // firePhaser

 /**
 * fightBack from Klingons
 */ 
private void fightBack() {
    
         log_d( " fightBack" );
    
     List<Coordinate> list = mQMap.getKlingons();
     for ( Coordinate c: list ) {
       if ( c.code == QMap.C_KLINGON ) { 
       // 50 %
       if ( Math.random()> 0.5 ){
          int beam = (int)( Math.random() * 100 ); 
          if ( mShield > beam ) {
          this.mShield -=  beam;
                } else{
                      this.mShield -=  0;
    } // if  mShield 
    
          String msg =  beam + " UNIT HIT ON ENTERPRISE AT SECTOR " + c.x + "," + c.y + " "  + mShield + "LEFT" ;
          toast_short(msg);
          log_d(msg);
 
        } // if Math.random
        } // if c.code
        
    } // for

// damege if break shield
if ( mShield <=  0 ) {
            mShield =  0;
    setAllDeviceAvailable( false );
} // if mShield
 
} //   fightBack


 
  /**
 * saveNumQuadrantEnterprise
 */    
private void saveNumQuadrantEnterprise() {
    int k = mQMap.num_klingon ;
    int b = mQMap.num_starbase;
     int s = mQMap.num_star;
mGalaxy.setNumQuadrantEnterprise( k, b, s );
mGalaxy.countNum(); 

}

  /**
 * judgeWin
 */  
private void judgeWin() {
    
       if ( mGalaxy.num_klingon == 0 ) {
        showWin();
        
               } else if ( mQMap.num_klingon == 0 ) {
               String msg  =  "all klingon are destryed in  Quadrant " + mGalaxy.pos_x + " , "+ mGalaxy.pos_y;
                toast_short( msg );
    } // if
    
} // jugdeWin





// ===========
// map
// ===========

/**
 *   displayMap
 */
    private void displayMap( String [][] map ) {
        for ( int i =0; i<SIZE_X; i++ ) {
                    for ( int j =0; j<SIZE_Y; j++ ) {
                        String msg = i + "," + j + " " + map[i][j];
                        log_d( msg );
                        mTextViewMap[i][j].setText(map[i][j]);
                        mTextViewMap[i][j].setBackgroundColor(Color.TRANSPARENT);
                    }} // for i j
                    
    } // displayMap
    
/**
 *   setShortMapBackgroundColor
 */    
private void setShortMapBackgroundColor( int x, int y, int color ) {
    
//if ( isShortMap ) {
    if  (( mMapMode == MAP_MODE_SHORT ) || ( mMapMode == MAP_MODE_TORPEDO_DATA ) ) {
mTextViewMap[x][y].setBackgroundColor( color );
} // if

} // etShortMapBackgroundColor

// ====================
//  DeviceAvailable
// ====================

/**
 *   check_tost_DeviceAvailable
 */
private boolean check_tost_DeviceAvailable( int id, boolean is_toast ) {
 log_d( "check_tost_DeviceAvailable" + id );
 
 if (DEBUG_DEVICE_AVAILABLE ) {
    return true;
 } 
 
    if ( isDeviceAvailables[id] ) {
        return true;
    } else {
    String msg = mDeviceNames[id] + " is dameged";
        log_d( msg );
     if ( is_toast ) {
    toast_short (msg );
    } // if is_toast

    return false;
} // if isDeviceAvailables

} //  check_tost_DeviceAvailable

/**
 *  setAllDeviceAvailable
 */
private void setAllDeviceAvailable( boolean b ) {

log_d( "setDeviceAvailable " + b );
for( int k=0; k<MAX_DEVICE; k++ ) {
     isDeviceAvailables[k] = b;
     
} // for

} // setAllDeviceAvailable

/**
 *  changeDeviceAvailable
 */
private void changeDeviceAvailable() {

log_d("changeDeviceAvailable ");
for( int k=0; k<MAX_DEVICE; k++ ) {



    if ( isDeviceAvailables[k] ) {
        // randomly fails,  if  normal
        if ( Math.random() > DEVICE_AVAILABLE_PROBABILITY_FAIL ) {
   isDeviceAvailables[k] = false;
    } // if random
} else {
  // randomly recover, if  failure
        if (Math.random() > DEVICE_AVAILABLE_PROBABILITY_RECOVER ) {

            isDeviceAvailables[k] = true;
        } // if random

    } // if is_available_devices

} // for k

} // changeDeviceAvailable


// ====================
//  Energy
// ====================

/**
 *  supply
 */
 private void supply(){
 
mEnergy =  mSupplyEnergy;
mTorpedo = mSupplyTorpedo;

} // supply

/**
 *  warningShield
 */
private void warningShield() {
   Quadrant q =   mGalaxy.getQuadrantEnterprise( );
    if ( q.num_klingon > 0 ) {
     if ( mShield < SHIELD_LOW ) {
        toast_short( "SHIELD_is LOW" );
    } // if
    } // if
       
} // warning

/**
 *  elapseDays
 */
private void elapseDays( int days ) {
  mDaysElapse += days;
 mDaysRemain -= days;
   if ( mDaysRemain < 0 ) {
    mDaysRemain = 0;
} 
} //ElapseOneDay

/**
 *  decheck_toast_Energy
 */
private boolean check_toast_Energy( int energy, boolean is_toast ) {
    
if ( mEnergy >  energy ) {
return true;

} else {
    if (is_toast) {
            toast_short(  "ENERGY insufficient " ); 
    }
            return false;
} // if 
} // decrementEnergy


/**
 *  consumeEnegy
 */
private void  consumeEnergy( int energy ) {
    mEnergy -= energy;
     if ( mEnergy < 0 ) {
      mEnergy = 0;
     }
} // decreseEnegy





    /**
     * getString
     * @param int res_id 
     * @return String
     */
    private String getString( int res_id ) {
        return mResources.getString( res_id );
    } // getString
    
    
       /**
     * toast short
     */       
    private void toast_short( String msg ) {
        ToastMaster.makeText( mActivity, msg, Toast.LENGTH_SHORT).show();
    } // toast_short
    
        /**
     * toast short
     */       
    private void toast_short(  int res_id ) {
        ToastMaster.makeText( mActivity, res_id, Toast.LENGTH_SHORT).show();
    } // toast_short
 
    /**
     * log_d
     */
    private static void log_d(String str) {
        
        if (Constant.DEBUG) Log.d(Constant.TAG, TAG_SUB + " " + str);
    } // og_d end

} // clss end
