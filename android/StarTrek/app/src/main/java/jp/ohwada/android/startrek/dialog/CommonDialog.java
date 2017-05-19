/**
 * STAR TREK
 * 2017-05-01 K.OHWADA
 */
 
package jp.ohwada.android.startrek.dialog; 

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import jp.ohwada.android.startrek.Constant;

import jp.ohwada.android.startrek.R;

/**
 * Common Dialog
 */
public class CommonDialog extends Dialog {

   // debug
       protected static final boolean D = Constant.DEBUG;
    protected String TAG_SUB = "CommonDialog";
 
    // constant
    private static final float WIDTH_RATIO_FULL = 0.98f;
    private static final float WIDTH_RATIO_HALF = 0.5f;

    // object
       private Activity mActivity;
private Resources mResources;
private String mPackageName; 


    private Point mDisplaySize;
    private DisplayMetrics mDisplayMetrics;
    
     protected View mView;   
protected TextView mTextViewTitle;
         
    /**
     * === Constructor ===
     * @param Context context
     */ 	
    public CommonDialog( Context context ) {
               
        super( context );
        log_d("CommonDialog1");
        initDisplayParam();
    } // CommonDialog


    /**
     * === Constructor ===
     * @param Context context
     * @param int theme
     */ 
    public CommonDialog( Context context, int theme ) {
        super( context, theme ); 
                log_d("CommonDialog2");
        initDisplayParam();
    } // CommonDialog

 protected void setCommonTitle( String title ) {
    if ( mTextViewTitle != null ) {
    mTextViewTitle.setText( title );  
    }  // if
} // setTitle


 protected void setCommonTitle( int res_id ) {
        if ( mTextViewTitle != null ) {
    mTextViewTitle.setText( res_id );
    }  // if    
} // setTitle
    /**
     * setActivity
     */
    protected void setActivity(Activity activity) {
         log_d("setActivity");      
        mActivity  = activity;
        mResources = activity.getResources();
        mPackageName = activity.getPackageName();
    } // setActivity
    
     protected void setView( View view ) { 
              log_d("setView");  
     mView=  view;
     } // setView 

     
       protected void setCommonContentView( int res_id ) {
    mView = getLayoutInflater().inflate( res_id, null );
            setContentView(  mView );
    } //intinflate
    
         
     protected int getIdentifier( String name ) {
    return mResources.getIdentifier(name, "id", mPackageName ); 
}// getIdentifier


     protected  void setNoTitle() {
    requestWindowFeature( Window.FEATURE_NO_TITLE );
    } // setNotitle
    
    /**
     * === onWindowFocusChanged ===
     */ 
    @Override
    public void onWindowFocusChanged( boolean hasFocus ) {
        super.onWindowFocusChanged( hasFocus );

        if ( mView == null ) return;
        // enlarge width, if screen is small

        int width = calcWidth( WIDTH_RATIO_HALF );			
        if ( mView.getWidth() < width ) {
            setLayoutWidth( width );
        } // if
    } // onWindowFocusChanged


    /**
     * === cancel ===
     */ 
    @Override
    public void cancel() {
        log_d("cancel");
        super.cancel();
    } // cancel


    /**
     * === dismiss ===
     */ 
    @Override
    public void dismiss() {
        log_d("dismiss");
        super.dismiss();
    } // dismiss


    /**
     * initDisplayParam
     */ 
    protected void initDisplayParam() {
        WindowManager wm = (WindowManager)
            getContext().getSystemService( Context.WINDOW_SERVICE );
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize( size );
        mDisplaySize = size;
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        mDisplayMetrics = metrics;
     } // initDisplayParam


    /**
     * setMessage
     * @param String str
     */ 
    protected void setMessage( String str ) {
    // dummy
    } //setMessage
    
    protected void createTextViewTitle() {
          mTextViewTitle  = (TextView) mView.findViewById( R.id.TextView_dialog_title );  
    } //  createTextViewTitle
    
    /**
     * createButtonClose
     */ 
    protected void createButtonClose() {
        log_d( "createButtonClose" );

        if ( mView == null ) {
            log_d("mView == null");
            return;
        }// if
        
        Button btn = (Button) mView.findViewById( R.id.Button_dialog_close );

        if ( btn == null ) {
                    log_d("btn == null");
                    return;
    }/// if
    
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                log_d(" close onClick");
                dismiss();
            }
        });
        
    } // createButtonClose


    /**
     * createButtonYes
     * @param int res_id
     */ 
    protected void createButtonYes( int res_id ) {
        // dummy
    } //createButtonYes


    /**
     * createButtonNo
     * @param int res_id
     */ 
    protected void createButtonNo( int res_id ) {
    // dummy
    } //  createButtonNo


    /**
     * procClickYes
     */ 
    protected void procClickYes() {
        // dummy
    } // procClickYes


    /**
     * procClickYes
     */ 
    protected void procClickNo() {
        // dummy
    } // procClickNo


    /**
     * setLayoutWidth
     * @param int res_id
     */ 
    protected void setLayoutWidthFromDimension( int res_id ) {
        int width = (int) adjustWidth( getDimension( res_id ));
        setLayoutWidth( width );
    } // setLayoutWidthFromDimension


    /**
     * setLayoutWidth
     */ 
    protected void setLayoutWidthHalf() {
        setLayoutWidth( calcWidth( WIDTH_RATIO_HALF ) );
    } // setLayoutWidthHalf


    /**
     * setLayoutWidth
     */ 
    protected void setLayoutWidthFull() {
        setLayoutWidth( calcWidth( WIDTH_RATIO_FULL ) );
    } // setLayoutWidthFull


    /**
     * setLayoutWidth
     * @param int width
     */  
    protected void setLayoutWidth( int width ) {
        getWindow().setLayout( width, ViewGroup.LayoutParams.WRAP_CONTENT );
    } // setLayoutWidth




    protected void setLayoutDisplaySize(  ) {
       int width = (int) mDisplaySize.x;
              int height = (int) mDisplaySize.y;
              log_d(" setLayoutDisplaySize " + width + " x " + height );
        getWindow().setLayout( width, height);
    
    //   heightViewGroup.LayoutParams.MATCH_PARENT );
    } // setLayoutWidth
    /**
     * calcWidth
     * @param float ratio
     * @return int
     */ 
    protected int calcWidth( float ratio ) {
        int width = (int)( mDisplaySize.x * ratio );
        return width;
    } // calcWidth


    /**
     * adjustWidth
     * @param float width
     * @return int
     */
    protected int adjustWidth( float width ) {
        int min_width = calcWidth( WIDTH_RATIO_HALF );
        int max_width = calcWidth( WIDTH_RATIO_FULL );
        if ( width < min_width ) {
            width = min_width;
        } // if
        
        if ( width > max_width ) {
            width = max_width;
        } // if
        
        return (int)width;
    } // adjustWidth


    /**
     * getDimension
     * @param int res_id
     * @return float
     */
    protected float getDimension( int res_id ) {
        return getContext().getResources().getDimension( res_id );
    } // getDimension


    /**
     * setGravity
     * show on the top of screen
     */ 
    protected void setGravityTop() {
        getWindow().getAttributes().gravity = Gravity.TOP;
    } // setGravityTop



    /**
     * setGravity
     * show on the lower of screen
     */
    protected void setGravityBottom() {
        // show on the lower of screen. 
        getWindow().getAttributes().gravity = Gravity.BOTTOM;
    } // setGravityBottom


    /**
     * getString
     * @param int res_id 
     * @return String
     */
    protected String getString( int res_id ) {
        return getContext().getResources().getString( res_id );
    } // getString


    /**
     * toast_short
     * @param int res_id 
     */

    //protected void toast_short( int res_id ) {
     //   ToastMaster.makeText(getContext(), res_id, Toast.LENGTH_SHORT).show();
   // } // toast_short



    /**
     * log_d
     */
    protected void log_d( String str ) {
        if (Constant.DEBUG) Log.d( Constant.TAG, TAG_SUB + " " + str );
    	
    }// log_d
  
    
    }// CommonDialog