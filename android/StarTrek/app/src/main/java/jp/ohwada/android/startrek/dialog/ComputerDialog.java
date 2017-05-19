/**
 * STAR TREK
 * 2017-05-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek.dialog; 

import android.app.Activity;
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
public class ComputerDialog extends CommonDialog {

// view    
private TextView mTextViewTitle;
                   
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
    public  ComputerDialog( Activity activity ) {
        super( activity );
              log_d("ComputerDialog1");
        intDialog( activity );
    }

    /**
     * === Constructor ===
     * @param Activity activity
     * @param int theme
     */ 
    public ComputerDialog( Activity activity, int theme ) {
        super( activity, theme ); 
                   log_d("ComputerDialog2");     
        intDialog( activity );

    }

    /**
     * intDialog
     */
    private void intDialog( Activity activity ) {
        TAG_SUB = "ComputerDialog" ;
                        log_d("intDialog"); 
                setActivity( activity ); 
   
    }
	
    /**
      * === create ===
      */
    @Override	 	
    public void create() {
        log_d("create");
        View view = getLayoutInflater().inflate( R.layout.dialog_computer, null );
     
     setNoTitle();
         
        setContentView( view );
        initView(  view );
        
        createButtonClose() ;

        setLayoutDisplaySize();
       setGravityTop();

    } // create

private void initView( View view ) {
    
     log_d(" initView");   
                          
                  // command
                  
                      Button    btnCmd10 = (Button) view.findViewById( R.id.Button_dialog_cmd_10 );    
                      if (  btnCmd10 != null ) {        
   btnCmd10.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v ) {
                       notifyCmdClick(10);                      
                   } // if onClick
               }); 
                       } // if btnCmd10
                       
                          Button    btnCmd11 = (Button) view.findViewById( R.id.Button_dialog_cmd_11 );  
                                          if (  btnCmd11 != null ) {              
   btnCmd11.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v ) {
                       notifyCmdClick(11);                      
                   } // if onClick
               });            
            } // if btnCmd11
            
                      Button    btnCmd12 = (Button) view.findViewById( R.id.Button_dialog_cmd_12 );    
                                                           if (  btnCmd12 != null ) {           
   btnCmd12.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v ) {
                       notifyCmdClick(12);                      
                   } // if onClick
               }); 
              } // if btnCmd12  
    
} // initView



    /**
     * notifyButtonClick
     */
    private void notifyCmdClick( int cmd ) {
        
        log_d( "notifyCmdClick " + cmd );
        
        if ( mListener != null ) {
            mListener.onCmdClick( cmd );
        } // if 
        
    } // 




	
} // class
