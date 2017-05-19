/**
 * STAR TREK
 * 2017-05-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek.dialog; 

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import jp.ohwada.android.startrek.R;

/**
 * Comand Dialog
 */
public class CommandDialog extends CommonDialog {

private static final int CMD_SIZE = 9;

// view    
private TextView mTextViewTitle;

                   private Button[] mButtonCmds = new Button[CMD_SIZE];
                    
                   
   // callback 
    private OnChangedListener mListener;  

    /*
     * callback interface
     */    
    public interface OnChangedListener {
        void onCmdClick( int cmd );
    } // interface


    /*
     * callback
     */ 
    public void setOnChangedListener( OnChangedListener listener ) {
        mListener = listener;
    }


    /**
     * === Constructor ===
     * @param Activity activity
     */ 	
    public CommandDialog( Activity activity ) {

        super( (Context)activity );

                log_d("CommandDialog1");

        intDialog(activity);
    }

    /**
     * === Constructor ===
     * @param Activity activity
     * @param int theme
     */ 
    public CommandDialog( Activity activity, int theme ) {

        super( (Context)activity, theme );

        log_d("CommandDialog2");
        

        intDialog(activity);
    }

    /**
     * intDialog
     */
    private void intDialog(Activity activity) {
        TAG_SUB = "ComandDialog" ;
                log_d("intDialog");
        setActivity( activity );        
 
    }
	
    /**
      * === create ===
      */
    @Override	 	
    public void create() {
        log_d("create");
    
       setNoTitle();
        
   setCommonContentView(  R.layout.dialog_command );
        initView(  mView );
        
        createButtonClose() ;
        setLayoutDisplaySize();
       setGravityTop();
       
    } // create

private void initView( View view ) {
    
    log_d(" initView");
    
                          
                  // command
         //          Button[] btnCmds = new Button[CMD_SIZE]; 
         
                  for ( int k=0; k<CMD_SIZE; k ++ ) {
                  final int k1 = k+1;
                      String name = "Button_dialog_cmd_" + Integer.toString(k1)  ;
            
                           int id = getIdentifier(name );
                                     
//               int id = mResources.getIdentifier(name, "id", mPackageName );  

                      Button btn = (Button) view.findViewById( id );
                                   log_d( name + id + btn );
                      if ( btn != null ) {
                     mButtonCmds[k] =  btn;  
   mButtonCmds[k].setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v ) {
                       notifyCmdClick(k1);
                       
                   } // if onClick
               });
    } // if   btn          
  } // for
    
} // initView



    /**
     * notifyButtonClick
     */
    private void notifyCmdClick( int cmd ) {
        
        log_d( "notifyCmdButtonClick " + cmd );
        
        if ( mListener != null ) {
            mListener.onCmdClick( cmd );
        } // if 
        
    } // 




	
} // class
