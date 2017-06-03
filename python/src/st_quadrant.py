#
# STAR TREK TEXT GAME
# 2017-05-01 K.OHWADA
#

import random


### class	
class Quadrant():
	
	course = None
	
	SIZE_X = 8
	SIZE_Y = 8
	
# easy	
	SETUP_KLINGON = 3	
	
# moderate	
#	SETUP_KLINGON = 5
	
	SETUP_STAR = 5
	# 50 %
	SETUP_PROBABILITY_STARBASE = 0.5
		
	qx =0
	qy =0
	
	# 8x8 Quadrants : klingons, starbases, stars	
	q_arr = []
	
	# 8x8 glaxy record
	g_arr = []

	num_klingon = 0
	num_starbase = 0
	num_star = 0		

	def __init__(self):
		from st_course import Course 
		self.course = Course()
### def end

	def setup_position(self):
		### 1 - 6
		self.qx = int(( self.SIZE_X - 3)*random.random() ) + 1
		self.qy = int(( self.SIZE_Y - 3)*random.random() ) + 1
		

	def setup_q(self):
		self.q_arr = [[ [0,0,0] for j in range(self.SIZE_X)] for i in range(self.SIZE_Y)]
		self.g_arr = [[ "***" for j in range(self.SIZE_Y)] for i in range(self.SIZE_X)]
		
		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):
				# setup 3x3 area
				if ( i >= self.qx - 1 ) and ( i <= self.qx + 1 ) and ( j >= self.qy - 1 ) and ( j <= self.qy + 1 ):
					self.q_arr[i][j][0] = int( self.SETUP_KLINGON * random.random() )
					# starbaase 50 %
					self.q_arr[i][j][1] = 1 if (random.random() < self.SETUP_PROBABILITY_STARBASE ) else 0
					self.q_arr[i][j][2] =  int(self.SETUP_STAR * random.random() )												
### def end

### get_current_q
	def get_current_q(self):
		return self.q_arr[self.qx][self.qy]		
### def end	
	
### save_current_q
	def save_current_q(self, q):
		self.q_arr[self.qx][self.qy][0]	 = q[0]
		self.q_arr[self.qx][self.qy][1]	 = q[1]
		self.q_arr[self.qx][self.qy][2]	 = q[2]
				
### def end	

### ount_q
	def count_q(self):
		k = 0
		b = 0
		s = 0
		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):				
				k += self.q_arr[i][j][0]
				b += self.q_arr[i][j][1]	
				s += self.q_arr[i][j][2]
### for i j  end

		self.num_klingon = k
		self.num_starbase = b
		self.num_star = s										
### def end	


	
	def print_long_sensor( self, is_computer_available ): 
		map = self.get_map_long(is_computer_available)
		print
		print " LONG RENGE SENSOR"
		self.print_map(map)


### def end	

	def get_map_long(self, is_computer_available):
		map = [[ "" for j in range(self.SIZE_Y)] for i in range(self.SIZE_X)]
		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):
				mark = "***"	
				# scan 3x3 area
				if ( i >= self.qx - 1 ) and ( i <= self.qx + 1 ) and ( j >= self.qy - 1 ) and ( j <= self.qy + 1 ):
					info = str(self.q_arr[i][j][0]) + str(self.q_arr[i][j][1]) + str(self.q_arr[i][j][2])
					if (is_computer_available):
						self.g_arr[i][j] = info
					mark = info		
				if (  i == self.qx ) and (  j == self.qy ):
					mark = "E" + mark
### if end	
				map[i][j] = mark
			### for i j end	
		return map
### def end	




	def print_map(self,map):
		self.print_header_row()
		for i in range(self.SIZE_X):
			print i,
			for j in range(self.SIZE_Y):
				mark = map[i][j]
				print mark,
			### for j end
			print
		### for i end			
		msg =  " at Quadrant %d , %d " % (self.qx, self.qy)
		print msg		
### def end
				
	def print_header_row(self):
		print " ",
		for j in range(self.SIZE_Y):
			mark = " " + str(j) + " "
			print mark,
		print	

### def end	
			
	def print_galaxy_record(self):
		print
		print "COMPUTER RECORD OF GALAXY "
		map = self.get_map_galaxy()
		self.print_map(map) 
		
### def end	


	def get_map_galaxy(self):
		map = [[ "" for j in range(self.SIZE_Y)] for i in range(self.SIZE_X)]
		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):
				mark = self.g_arr[i][j]
				if (  i == self.qx ) and (  j == self.qy ):
					mark = "E" + mark
				map[i][j] = mark
### for i j	
		return map
### def end
					
	def warp(self, course, distance): 
		print "warp"
		d = self.course.get_course_delta(course)
		x = self.qx + d[0 ]*distance
		y = self.qy + d[1]*distance
		if ( x >= 0 ) and ( x <= 7 ) and ( y >= 0 ) and ( y <= 7 ):
			self.qx = x
			self.qy = y
		else:
			self.setup_position()	
### if end
		self.arrive()
	### def end	
	def arrive(self):
		msg = "arive at Quadrant %d , %d" % ( self.qx, self.qy )
		print msg 
	### def end	
						
	def print_q(self): 
		print "print_q"
		for i in range(8):
			print "i=",
			print i,
			print ":",
			for j in range(8):
				print "j=",
				print j,
				print ":",
				print self.q_arr[i][j],
		### def end
		
		### class end