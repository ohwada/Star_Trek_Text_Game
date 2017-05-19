/**
 * STAR TREK
 * 2017-05-01 K.OHWADA
 */

package jp.ohwada.android.startrek.dialog; 

import android.app.Activity;
import android.content.pm.PackageManager;
// import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import jp.ohwada.android.busmap.view.CommonDialog;
//import jp.ohwada.android.busmap.view.SearchView;

import jp.ohwada.android.startrek.R;

/**
 * Course Dialog
 */
public class CourseDialog extends CommonDialog {
   
private static final int COURSE_SIZE = 8;

//    private Activity mActivity;

// view    
private TextView mTextViewTitle;

                   private Button[] mButtonCourses = new Button[COURSE_SIZE];



   // callback 
    private OnChangedListener mListener;  

    /*
     * callback interface
     */    
    public interface OnChangedListener {
        void onCourseClick( int course );
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
    public CourseDialog( Activity activity ) {
        super( activity );
        intDialog(activity);
    }

    /**
     * === Constructor ===
     * @param Activity activity
     * @param int theme
     */ 
    public CourseDialog( Activity activity, int theme ) {
        super( activity, theme ); 
        intDialog(activity);
    }

    /**
     * intDialog
     */
    private void intDialog(Activity activity) {
        TAG_SUB = "CourseDialog" ;
                setActivity( activity ); 
  //      mActivity  = activity;
  
    }
	
    /**
      * === create ===
      */
    @Override	 	
    public void create() {
        log_d("create");
        TAG_SUB = "CourseDialog";

        setNoTitle();
        setCommonContentView( R.layout.dialog_course );
        initView(  mView );
        
        createButtonClose() ;
        setLayoutWidthFull();
        setGravityBottom();

 
	






    } // create
 
    
public void setTitle( String title ) {
    mTextViewTitle.setText( title );    
} // setTitle


public void setTitle( int res_id ) {
    mTextViewTitle.setText( res_id );    
} // setTitle

private void initView( View view ) {
    
                      mTextViewTitle = (TextView) view.findViewById( R.id.TextView_dialog_title );
                          
                  // course
                                for ( int k = 0; k < COURSE_SIZE; k ++ ) {
                  final int k1 = k+1;
                      String name = "Button_dialog_course_" + Integer.toString(k1)  ;
                               
                           int id = getIdentifier(name );
                                     Button btn = (Button) view.findViewById( id );
                                   log_d( name + id + btn );
                      if ( btn != null ) {
                       mButtonCourses[k] = btn;
                      
                        mButtonCourses[k].setOnClickListener( new View.OnClickListener() {                 
                        @Override
                   public void onClick( View v) {
                       notifyCourseClick( k1 );
                       
                   }
               });
               
     } // if   btn          
  } // for
  

               
   
      
} // initView



// public void setTitle ( String title ){
// mTextViewTitle.setText( title );
// } // etTitle






    /**
     * cancel
     */


    /**
     * destroy
     */
    public void destroy() {
        log_d("destroy");
    } // destroy
    




  


    /**
     * notifyButtonClick
     */
    private void notifyCourseClick( int course ) {
        if ( mListener != null ) {
            mListener.onCourseClick( course );
        } // if 
    } // notifyCourseButtonClick






	
} // class
