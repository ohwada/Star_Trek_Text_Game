//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Course = function () {	

	
	this.COURSE_DELTA = [ [0,1], [-1,1], [-1,0], [-1,-1], [0,-1], [1, -1], [1,0], [1,1] ];
	
	this.COURSE_RIGHT = 1;
	this.COURSE_UP_RIGHT = 2;
	this.COURSE_UP = 3;
	this.COURSE_UP_LEFT = 4;
	this.COURSE_LEFT = 5;
	this.COURSE_DOWN_LEFT = 6;
	this.COURSE_DOWN = 7;
	this.COUR_DOWN_RIGHT = 8;
	
	} // constructor										
	
	Course.prototype.get_delta = function (course) {
		var delta = [0,0]
		if (( course >= 1 ) && ( course <= 8 )) {
			delta = this.COURSE_DELTA[course-1];
		}
		return delta
		
	} // get_delta
		

Course.prototype.get_course = function (x_orig, y_orig, x, y) {	

		var ret = 0;
		var abs_x = Math.abs( x_orig - x );
		var abs_y = Math.abs( y_orig - y );
		
		if (( x_orig == x ) && ( y_orig < y )) {
			ret = this.COURSE_RIGHT;
			 
		} else if (( x_orig == x ) && ( y_orig > y )) {
			ret = thiis.COURSE_LEFT;
			
		} else if (( y_orig == y ) && ( x_orig < x )) {				
			ret = this.COURSE_DOWN;
			
		} else if (( y_orig == y ) && ( x_orig > x )) {				
			ret = this.COURSE_UP	
		} else if (( abs_x == abs_y ) && ( x_orig > x ) && ( y_orig > y )) {		
			ret = this.COURSE_UP_LEFT;
			
		} else if (( abs_x == abs_y ) && ( x_orig > x ) &&  ( y_orig < y )) {	
			ret = this.COURSE_UP_RIGHT;
			
		} else if (( abs_x == abs_y ) && ( x_orig < x ) && ( y_orig > y )) {		
			ret = self.COURSE_DOWN_LEFT	;
								
		} else if (( abs_x == abs_y ) && ( x_orig < x ) && ( y_orig < y )) {		
			ret = this.COURSE_DOWN_RIGHT;
			
	} // if
		 
		return ret;

} //get_course
