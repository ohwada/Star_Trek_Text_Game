#
# STAR TREK Text Game
# 2017-05-01 K.OHWADA
#
	
## class
class Course():
	
	COURSE_DELTA = [ [0,1], [-1,1], [-1,0], [-1,-1], [0,-1], [1, -1], [1,0], [1,1] ]
	
	COURSE_RIGHT = 1
	COURSE_UP_RIGHT = 2
	COURSE_UP = 3
	COURSE_UP_LEFT = 4
	COURSE_LEFT = 5
	COURSE_DOWN_LEFT = 6
	COURSE_DOWN = 7
	COURSE_DOWN_RIGHT = 6
																		
										
	def __init__(self):
		pass				
### def end
	
	def get_course_delta(self, course):
		delta = [0,0]
		if ( course >= 1 ) and ( course <= 8 ):
			delta = self.COURSE_DELTA[course-1]
		return delta
### def end
	
	def get_course(self, x_orig, y_orig, x, y):
		ret = 0
		abs_x = abs( x_orig - x )
		abs_y = abs( y_orig - y )
		if ( x_orig == x ) and ( y_orig < y ):
			ret = self.COURSE_RIGHT 
		elif ( x_orig == x ) and ( y_orig > y ):
			ret = self.COURSE_LEFT
		elif ( y_orig == y ) and ( x_orig < x ):				
			ret = self.COURSE_DOWN
		elif ( y_orig == y ) and ( x_orig > x ):				
			ret = self.COURSE_UP	
		elif ( abs_x == abs_y ) and ( x_orig > x ) ( y_orig > y ) :		
			ret = self.COURSE_UP_LEFT
		elif ( abs_x == abs_y ) and ( x_orig > x ) ( y_orig < y ):	
			ret = self.COURSE_UP_RIGHT
		elif ( abs_x == abs_y ) and ( x_orig < x ) ( y_orig > y ) :		
			ret = self.COURSE_DOWN_LEFT						
		elif ( abs_x == abs_y ) and ( x_orig < x ) ( y_orig < y ) :		
			ret = SELF.COURSE_DOWN_RIGHT	 
		return ret
### def end	
								
### class end