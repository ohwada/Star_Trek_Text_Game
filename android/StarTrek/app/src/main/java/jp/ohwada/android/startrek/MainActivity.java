/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */

// cmd1 ok
package jp.ohwada.android.startrek;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
/* MainActivity
*/
public class MainActivity extends Activity {
   
   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "MainActivity";
    

 private Game mGame;
 
private TextView mTextViewTitle;

    private TextView[][]  mTextViewMap = new TextView[8][8];

/**
/* == onCreate ==
*/
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
    mGame = new Game( this );
  mGame.setup();
      
    // UI
 mTextViewTitle = (TextView) findViewById( R.id.TextView_title );

 mGame.setTextViewTitle( mTextViewTitle );

  // map UI
           for (int i=0; i<8; i++ ) {
           for (int j=0; j<8; j++ ) {

               String name = "TextView_map_" + Integer.toString(i) + "_" + Integer.toString(j) ;
               int id = getResources().getIdentifier(name, "id", getPackageName() );
                       mTextViewMap[i][j] = (TextView)          findViewById(id);

}} // for i j end
 
 mGame.setTextViewMap( mTextViewMap );

       Button btnCmd1 = (Button) findViewById( R.id.Button_cmd_1 );
   btnCmd1.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCmd(1);
                   }
               }); 
    


              Button btnCmd2 = (Button) findViewById( R.id.Button_cmd_2 );
   btnCmd2.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCmd(2);
                   }
               }); 
               
                  Button btnCmd3 = (Button) findViewById( R.id.Button_cmd_3 );
   btnCmd3.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCmd(3);
                   }
               }); 
  
      Button btnCmd5 = (Button) findViewById( R.id.Button_cmd_5 );
   btnCmd5.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCmd(5);
                   }
               });   
                        
              // course
                      Button btnCourse1 = (Button) findViewById( R.id.Button_course_1 );
   btnCourse1.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(1);
                   }
               });
  
                      Button btnCourse2 = (Button) findViewById( R.id.Button_course_2 );
   btnCourse2.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(2);
                   }
               });    
  
                        Button btnCourse3 = (Button) findViewById( R.id.Button_course_3 );
   btnCourse3.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(3);
                   }
               });               
         
                               Button btnCourse4 = (Button) findViewById( R.id.Button_course_4 );
   btnCourse4.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(4);
                   }
               });   
                                     Button btnCourse5 = (Button) findViewById( R.id.Button_course_5 );
   btnCourse5.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(5);
                   }
               }); 
               
      
              
          
                                    Button btnCourse6 = (Button) findViewById( R.id.Button_course_6 );
   btnCourse6.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(6);
                   }
               });       
               
                                     Button btnCourse7 = (Button) findViewById( R.id.Button_course_7 );
   btnCourse7.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(7);
                   }
               });   
               
                                     Button btnCourse8 = (Button) findViewById( R.id.Button_course_8 );
   btnCourse8.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                       mGame.procCourse(8);
                   }
               });  
                          
           } // end of onCreate
           
private void procCmd( int n ) {
    toast_short( "cmd " + n );
}

private void procCourse( int n ) {
    toast_short( "course " + n );
}

        /*
** scan long renge
*/    
    
/**
/* displayMap
*/

      /**
     * toast short
     */       
    private void toast_short( String msg ) {
        ToastMaster.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
    /**
     * log_d
     */
    private  void log_d( String msg ) {
        
        if ( Constant.DEBUG ) Log.d( Constant.TAG, TAG_SUB + " " + msg );
    } // log_d end


} // end of MainActivity