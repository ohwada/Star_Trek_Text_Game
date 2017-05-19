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

//import jp.ohwada.android.busmap.view.CommonDialog;

/**
 *  Close Dialog
 */
public class CloseDialog extends CommonDialog {

// view 

private TextView mTextViewMessage;
                                                   
   // callback 
    //private OnChangedListener mListener;

    /*
     * callback
     */ 
    //public void setOnChangedListener( OnChangedListener listener ) {
    //    mListener = listener;
    //}


    /**
     * === Constructor ===
     * @param Activity activity
     */ 	
    public CloseDialog( Activity activity ) {

        super( (Context)activity );

                log_d("esNoDialog1");

        intDialog(activity);
    }

    /**
     * === Constructor ===
     * @param Activity activity
     * @param int theme
     */ 
    public CloseDialog( Activity activity, int theme ) {

        super( (Context)activity, theme );

        log_d("esNoDialog2");
        

        intDialog(activity);
    }


public void setTitle( String title ) {
    setCommonTitle(  title );
} // setTitle


public void setTitle( int res_id ) {
        setCommonTitle( res_id );
} // setTitle

public void setMessage( String msg ) {
            if ( mTextViewMessage != null ) {
    mTextViewMessage.setText( msg ); 
        }  // if   
} // setYes



    /**
     * intDialog
     */
    private void intDialog(Activity activity) {
        
        TAG_SUB = "CloseDialog" ;
                log_d("intDialog");
                setActivity( activity );    
    }
	
    /**
      * === create ===
      */
    @Override	 	
    public void create() {
        log_d("create");
        TAG_SUB = "CommandDialog";
              
               setNoTitle();
setCommonContentView( R.layout.dialog_close );
 initView(  mView );
 
                
     createTextViewTitle();    
        createButtonClose() ;
        
//        setLayoutWidthFull();

        // setLayoutDisplaySize();
      //  setGravityTop();

 
	






    } // create

private void initView( View view ) {
    
    log_d(" initView");
    
//          mTextViewTitle  = (TextView) 
// view.findViewById( R.id.TextView_dialog_title );  
          
          mTextViewMessage  = (TextView) view.findViewById( R.id.TextView_dialog_message );  
       
} // initView
	
} // class
