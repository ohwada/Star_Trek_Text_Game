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
 * YesNo Dialog
 */
public class YesNoDialog extends CommonDialog {

    private Activity mActivity;
 Resources mResources;
String mPackageName; 

// view 
   
private TextView mTextViewTitle;
private TextView mTextViewMessage;


                   private Button mButtonYes;
                    private Button mButtonNo;                  
                    
                   
   // callback 
    private OnChangedListener mListener;  

    /*
     * callback interface
     */    
    public interface OnChangedListener {
        void onYesClick();
                void onNoClick();
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
    public YesNoDialog( Activity activity ) {

        super( (Context)activity );

                log_d("esNoDialog1");

        intDialog(activity);
    }

    /**
     * === Constructor ===
     * @param Activity activity
     * @param int theme
     */ 
    public YesNoDialog( Activity activity, int theme ) {

        super( (Context)activity, theme );

        log_d("esNoDialog2");
        

        intDialog(activity);
    }


public void setTitle( String title ) {
    if ( mTextViewTitle != null ) {
    mTextViewTitle.setText( title );  
    }  // if
} // setTitle


public void setTitle( int res_id ) {
        if ( mTextViewTitle != null ) {
    mTextViewTitle.setText( res_id );
    }  // if    
} // setTitle

public void setMessage( String msg ) {
            if ( mTextViewMessage != null ) {
    mTextViewMessage.setText( msg ); 
        }  // if   
} // setYes

public void setYes( String yes) {
            if ( mButtonYes != null ) {
    mButtonYes.setText( yes ); 
            }  // if     
} // setYes

public void setYes( int res_id ) {
                if ( mButtonYes != null ) {
    mButtonYes.setText( res_id );  
            }  // if        
} // setYes

public void setNo( String no ) {
                    if ( mButtonNo != null ) {
    mButtonNo.setText( no );
                }  // if 
} // setNo

public void setNo( int res_id ) {
                        if ( mButtonNo != null ) {
    mButtonNo.setText( res_id );
                    }  // if 
} // setNo

    /**
     * intDialog
     */
    private void intDialog(Activity activity) {
        
        TAG_SUB = "ComandDialog" ;
                log_d("intDialog");
                setActivity( activity ); 
                        
   //     mActivity  = activity;
   //     mResources = activity.getResources();
   //     mPackageName = activity.getPackageName();
   
    }
	
    /**
      * === create ===
      */
    @Override	 	
    public void create() {
        log_d("create");
        TAG_SUB = "CommandDialog";
        View view = getLayoutInflater().inflate( R.layout.dialog_yes_no, null );

        setNoTitle();
    //requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView( view );
        initView(  view );
        
       // createButtonClose() ;
//        setLayoutWidthFull();

        // setLayoutDisplaySize();
      //  setGravityTop();

 
	






    } // create

private void initView( View view ) {
    
    log_d(" initView");
    
          mTextViewTitle  = (TextView) view.findViewById( R.id.TextView_dialog_title );  
          
          mTextViewMessage  = (TextView) view.findViewById( R.id.TextView_dialog_message );  
      
              
        mButtonYes = (Button) view.findViewById( R.id.Button_dialog_yes );        
   mButtonYes.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v ) {
                       notifyYesClick();
                       
                   } // if onClick
               });

          mButtonNo = (Button) view.findViewById( R.id.Button_dialog_no );        
   mButtonNo.setOnClickListener( new View.OnClickListener() {
                   @Override
                   public void onClick( View v ) {
                       notifyNoClick();
                       
                   } // if onClick
               });  
} // initView



    /**
     * notifyButtonClick
     */
    private void notifyYesClick() {
        
        log_d( "notifyYesClick ");
        
        if ( mListener != null ) {
            mListener.onYesClick( );
        } // if 
    } // notifyCourseButtonClick

    /**
     * notifyButtonClick
     */
    private void notifyNoClick() {
        
        log_d( "notifynoClick ");
        
        if ( mListener != null ) {
            mListener.onNoClick( );
        } // if 
    } // notifyCourseButtonClick


	
} // class
