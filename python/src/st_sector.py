#
# STAR TREK TEXT GAME
# 2017-05-01 K.OHWADA
#

import random

class Sector():
	
	SIZE_X = 8
	SIZE_Y = 8
	SIZE_K = 5
	
# easy
	SETUP_KLINGON_SHIELD = 100
# moderate
	# SETUP_KLINGON_SHIELD = 200

	BEAM_FACTOR_PHASER = 2			
	BEAM_FACTOR_FIGHT_BACK = 2	

	# 50 %
	FIGHT_BACK_PROBABILITY = 0.5		
		
	C_NONE = 0	
	C_ENTERPRISE = 1
	C_KLINGON = 2
	C_STARBASE = 3
	C_STAR = 4
 	MOVE_OUT_UP = 21
 	MOVE_OUT_DOWN = 22
 	MOVE_OUT_LEFT = 23
 	MOVE_OUT_RIGHT = 24
	
	course = None
		 	 	 	 		
	sx =0
	sy =0	
	
	# 8 x8 Sectors
	s_arr = []
	
	# kingon : valid, x, y, shield
	k_arr = []


	
	num_kllngon = 0
	num_starbase = 0
	num_star = 0
		
### init	
	def __init__(self):
		from st_course import Course
		self.course = Course()
### def end

### setup
	def setup(self,q):
		kingons = q[0]
		b = q[1]	
		s = q[2]		
		self.num_klingon = kingons
		self.num_starbase = b
		self.num_star = s
		### 1 - 6
		self.sx = int((self.SIZE_X-3)*random.random() ) + 1
		self.sy = int((self.SIZE_Y-3)*random.random() ) + 1
		
		self.s_arr = [[ 0 for j in range(self.SIZE_Y)] for i in range(self.SIZE_X)]
		self.k_arr = [ [0, 0, 0, 0] for k in range(self.SIZE_K) ]

		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):
				self.s_arr[i][j] = self.C_NONE
	### for i j
					
	### enterprise				
		self.s_arr[self.sx][self.sy] = self.C_ENTERPRISE
		
			### starbase
		for k in range( b ):
			# 1 - 6	
			x = int( (self.SIZE_X-3)*random.random() ) + 1
			y = int( (self.SIZE_Y-3)*random.random() ) + 1

			if ( self.s_arr[x][y] == self.C_NONE ):
				self.s_arr[x][y] = self.C_STARBASE
### if
### for k	
	
	### kingon
		for k in range(kingons):
			# 0 - 7	
			x = int( (self.SIZE_X-1)*random.random() )
			y = int( (self.SIZE_Y-1)*random.random() )
			if ( self.s_arr[x][y] == self.C_NONE ):
				self.s_arr[x][y] = self.C_KLINGON
				if (k < self.SIZE_K):
					self.k_arr[k] = [ 1, x, y, self.SETUP_KLINGON_SHIELD ]
### if
### for n

	### star
		for k in range( s ):
			# 0 - 7	
			x = int( (self.SIZE_X-1)*random.random() )
			y = int( (self.SIZE_Y-1)*random.random() )
			if ( self.s_arr[x][y] == self.C_NONE ):
				self.s_arr[x][y] = self.C_STAR
### if
### for k
											
### def end

	def get_num(self):
		ret = [ self.num_klingon, self.num_starbase, self.num_star ]
		return ret
### def end		

### print_short_sensor
	def print_short_sensor(self): 
		map = self.get_map_short()
		print
		print " SHORT RENGE SENSOR"
		self.print_header_row()	
		for i in range(self.SIZE_X):
			print i,
			for j in range(self.SIZE_Y):
				mark = map[i][j]
				print mark,
			### for j end
			print
		### for i end				
### def end
	
	def get_map_short(self): 
		map = [[ " "  for j in range(8)] for i in range(self.SIZE_Y)]
		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):
				mark = " "	
				if ( self.s_arr[i][j] == self.C_ENTERPRISE ):
					mark = "E"
				elif ( self.s_arr[i][j] == self.C_KLINGON ):
					mark = "K"				
				elif ( self.s_arr[i][j] == self.C_STARBASE ):
					mark = "B"					
				elif ( self.s_arr[i][j] == self.C_STAR ):
					mark = "*"				
				map[i][j] = mark
		return map
### def end

	def print_header_row(self):
		print " ",
		for j in range(self.SIZE_Y):
			mark = str(j)
			print mark,
		print	
### def end				
				
				
	def print_torpedo_data(self):
		print "SHIP'S & TARGET'S COORDINATES ARE"
		for i in range(self.SIZE_X):
			for j in range(self.SIZE_Y):
				if ( self.s_arr[i][j] == self.C_KLINGON ):	
					course = self.course.get_course( self.sx, self.sy, i, j )
			 		msg = "x= %d, y= $d : course= %d " % ( i, j, course )
			 		print msg
			 	
### def end	
	
	
	def move(self, course, distance): 
		print "move"
				# remove current positin
		self.s_arr[self.sx][self.sy] = self.C_NONE 
		code = self.C_NONE
		d = self.course.get_course_delta(course)
		x = self.sx
		y = self.sy
		xd =  d[0]
		yd =  d[1]
		pre_x = x
		pre_y = y			
		for i in range(distance):
			pre_x = x
			pre_y = y	
			x += xd
			y += yd	
		
			if ( x >= 0 ) and ( x < self.SIZE_X ) and  ( y >= 0 ) and ( y < self.SIZE_Y ):
				msg = " %d , %d " % ( x, y )
				print msg	
				if ( self.s_arr[x][y] == self.C_NONE )	:
					code = self.C_NONE
					self.s_arr[x][y] == self.C_ENTERPRISE
					self.sx = x
					self.sy = y
					continue
							
				elif ( self.s_arr[x][y] == self.C_KLINGON )	:
					code = self.C_KLINGON
					print "onflicted with KLINGON"
					self.destroy_klingon(x,y)
					self.sx = pre_x
					self.sy = pre_y
					break
					
				elif ( self.s_arr[x][y] == self.C_STARBASE )	:
					code = self.C_STARBASE
					print "docked in STARBASE"
					self.sx = pre_x
					self.sy = pre_y
					break
					
				elif ( self.s_arr[x][y] == self.C_STAR )	:
					code = self.C_STAR
					print "landed on STAR"
					self.sx = pre_x
					self.sy = pre_y
					break
					
			elif ( x < 0 ):
				code = self.MOVE_OUT_UP
				break
				
			elif ( x >= self.SIZE_X ):
				code = self.MOVE_OUT_DOWN
				break
								
			elif ( y < 0 ):
				code = self.MOVE_OUT_LEFT
				break
				
			elif ( y >= self.SIZE_Y ):
				code = self.MOVE_OUT_RIGHT
				break
## for
		# new positin
		self.s_arr[self.sx][self.sy] = self.C_ENTERPRISE 
		return code											
### def end	

	def fire_phaser(self, energy ):
		print "fire_phaser"	
		for k in range(self.SIZE_K):
			self.phaser_klingon( k, energy )
### def end
			
	def phaser_klingon(self, k, energy ):		
		param = self.k_arr[k]
		valid = param[0]
		x = param[1]
		y = param[2]
		shield  = param[3]
		if ( valid == 0 ):
			return
			
		beam = self.get_phaser_beam(energy, x , y )
	
		shield -= beam
		if ( shield < 0 ):
			shield = 0				
		msg = " %d UNIT HIT ON KLINGON AT SECTOR %d , %d ( %d LEFT  )" % ( beam, x, y, shield ) 
		print msg
		self.k_arr[k][3] = shield
		
		if ( shield <= 0 ):
			print "*** KLINGON DESTROYED ***"
			self.destroy_klingon(x, y)
			
### def end	

	def get_phaser_beam(self, energy, x , y ):
		
		distance = self.get_distance(x , y )
		beam = self.BEAM_FACTOR_PHASER * random.random() * (( energy / self.num_klingon )/ distance ) 
		return int(beam)  
### def end

	def fight_back(self):
		list = []
		for k in range(self.SIZE_K):
			param = self.fight_back_klingon(k)
			if ( len(param) > 0 ):
				list.append( param )
		return list
### def end

	def fight_back_klingon(self,k):
		ret = []
		valid = self.k_arr[k][0]
		x = self.k_arr[k][1]
		y = self.k_arr[k][2]
		sheld = self.k_arr[k][3]
		if valid and ( random.random() < self.FIGHT_BACK_PROBABILITY ) :
			beam = self.get_fight_back_beam( x , y, sheld)
			ret = [x, y, beam]
		return ret
### def end
					
	def get_fight_back_beam(self, x, y, energy ):
		distance = self.get_distance( x, y )
		beam = self.BEAM_FACTOR_FIGHT_BACK * random.random() * (energy/distance)
		return beam
### def end		

	def get_distance(self, x , y ):	
		xd = self.sx - x
		yd = self.sy - y			
		distance = xd * xd + yd * yd	
		return distance
### def end
		
	def fire_torpedo(self, course): 
		print "TORPEDO TRACK:"
		d = self.course.get_course_delta(course)
		k_count = 0
		x = self.sx
		y = self.sy
		xd =  d[0]
		yd =  d[1]
		for i in range(self.SIZE_Y):
			x += xd
			y += yd	
#			msg = " %d , %d" % (x, y)
#			print msg
					
			if ( x >= 0 ) and ( x < self.SIZE_X ) and  ( y >= 0 ) and ( y < self.SIZE_Y ):
				msg = " %d , %d" % (x, y)
				print msg
				
				if ( self.s_arr[x][y] == self.C_KLINGON )	:
					print "*** KLINGON DESTROYED ***"
					self.destroy_klingon(x, y)
					k_count += 1
					break
					
				elif ( self.s_arr[x][y] == self.C_STARBASE )	:
					print "*** STAR BASE DESTROYED"
					self.decrease_starbase()
					self.s_arr[x][y] = self.C_NONE	
					break
					
				elif ( self.s_arr[x][y] == self.C_STAR )	:		
					print "YOU CAN'T DESTROY STARS"
					break
					
				elif ( self.s_arr[x][y] == self.C_NONE )	:		
					continue
							
			else:
		  		print "TORPEDO MISSED"
		  		break
		  			
### if end		  		
		  		return k_count
### def end

	def decrese_klingon(self):
		self.num_klingon -= 1
		if ( self.num_klingon < 0 ):
			self.num_klingon = 0		
### def end

	def decrese_starbase(self):
		self.num_starbase -= 1
		if ( self.num_starbase < 0 ):
			self.num_starbase = 0		
### def end


	def destroy_klingon(self, x, y):
		self.decrese_klingon()
		self.s_arr[x][y] = self.C_NONE
		for k in range(self.SIZE_K):
			if ( self.k_arr[k][1] == x ) and ( self.k_arr[k][2] == y ):
				self.k_arr[k][0] = 0
### def end
							
					
		### class end