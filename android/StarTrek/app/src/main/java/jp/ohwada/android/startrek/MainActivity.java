/**
 * STAR TREK
 * 2017-03-01 K.OHWADA
 */


 // mTextViewReport
 
//   btnCmd7
   
  // Button btnCmd4
// cmd1 ok
package jp.ohwada.android.startrek;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
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

import jp.ohwada.android.startrek.dialog.*; 

/**
/* MainActivity
*/
public class MainActivity extends Activity {
   
   // debug
    private static final boolean D = Constant.DEBUG;
    private static final String TAG_SUB = "MainActivity";
   
   // UI 
      private static final int SIZE_X = 8;
      private static final int SIZE_Y = 8;
      
 private Game mGame;
 

    
/**
/* == onCreate ==
*/
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
      
         setContentView( R.layout.activity_main ); 
         
                 mGame = new Game( this );
                 
 TextView tvMapTitle = (TextView) findViewById( R.id.TextView_map_title );
mGame.setTextViewMapTitle( tvMapTitle );

TextView tvReportTitle = (TextView) findViewById( R.id.TextView_report_title );
mGame.setTextViewReportTitle( tvReportTitle );

 TextView tvReport = (TextView) findViewById( R.id.TextView_report ); 
 mGame.setTextViewReport( tvReport );
 
                TextView[][]  tvMap = new TextView[SIZE_X][SIZE_Y];  
                
           for (int i=0; i<SIZE_X; i++ ) {
           for (int j=0; j<SIZE_Y; j++ ) {

               String name = "TextView_map_" + Integer.toString(i) + "_" + Integer.toString(j) ;
               int id = getResources().getIdentifier( name, "id",
               getPackageName() );
                       TextView tv = (TextView)          findViewById(id);
                       log_d( name + id + tv );
                       if (tv != null ) {
                              tvMap[i][j] = tv;
                        } // if tv

}} // for i j end

mGame.setTextViewMap( tvMap );
    
                
       Button btnCmdList = (Button) findViewById( R.id.Button_cmd_list );
   btnCmdList.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v) {
                    log_d( "  btnCmdList onClick" );
                       mGame.showCommandDialog();
                   }
               });  // setOnClickListener


     mGame.startNewGame();               
      
              
      

                           
           } // end of onCreate
           


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


    /**
     * === onCreateOptionsMenu ===
     */
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        log_d( "onCreateOptionsMenu" );
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
 
 
  
    /**
     * === onOptionsItemSelected ===
     */
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        log_d("onOptionsItemSelected");
        int id = item.getItemId();
        if (id == R.id.menu_about) {
            showAboutDialog();
            
                   } else if ( id == R.id.menu_usage ) {
                       startBrawser( Constant.URL_USAGE );
                       
                             } else if (id == R.id.menu_new_game) {  
                 mGame.startNewGame();
                 
                   } else if (id == R.id.menu_cmd_list) {
            mGame.showCommandDialog();
        } // if

        return true;
    } // onOptionsItemSelected


    /**
     * showOptionDialog
     */

    
    /**
     * showAboutDialog
     */
    private void showAboutDialog() {

        log_d("showAboutDialog");
        AboutDialog dialog = new AboutDialog(this);
        dialog.create();
        dialog.show();

    } // showAboutDialog


    /**
     * startBrawser
     */
    private void startBrawser( String url ) {
        if (( url == null )|| url.equals("") ) return;
        Uri uri = Uri.parse( url );
        Intent intent = new Intent( Intent.ACTION_VIEW, uri );
        startActivity(intent);
    } // startBrawser


} // end of MainActivity