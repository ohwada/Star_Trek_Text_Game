#
# STAR TREK Text Game
# 2017-05-01 K.OHWADA
#
	
## class
class Main():
	
	ENERGY_YELLOW = 300
	SHIELD_LOW = 200
	
	game = None	
	game_input = None
	device = None
	course = None
		
	def __init__(self):
		from st_game import Game
		from st_input import Input
		from st_device import Device
		from st_course import Course
		self.game = Game()
		self.game_input = Input()
		self.device = Device()
		self.course = Course()
### def end

	def start_game(self):
		print
		print "STAR TREK TEXT GAME"
		print
		self.game.setup()
		self.device.setup()
		self.print_mission()
		self.command_loop()
### def end

	
	def print_mission(self):
		print
		msg = "YOU MUST DESTROY %d KLINGONS IN %d STARDATES WITH %d STARBASES"  % ( self.game.quadrant.num_klingon, self.game.days_left,  self.game.quadrant.num_starbase )
 		print msg
 		print
## def 

	def command_loop(self):
		while(True):
			self.device.change_available()
			self.warning_shield()
			cmd = self.game_input.input_command()
			if (cmd == 1):
			# long reng sensor
				self.cmd_long_sensor()
						
			elif (cmd == 2):	
			# short reng sensor	
				self.cmd_short_sensor()
				
			elif (cmd == 3):
			# fire phaser
				self.cmd_phaser()
			
				continue
			elif (cmd == 4):
			# fire torpedo
				self.cmd_torpedo()
								
			elif (cmd == 5):	
			# shield	 control
				self.cmd_shield()
				
			elif (cmd == 6):	
			# damege report	
				self.cmd_damege()
			
			elif (cmd == 7):	
			# warp engine
				self.cmd_warp()
				
			elif (cmd == 8):	
			# impulse engine
				self.cmd_impulse()
									
			elif (cmd == 9):	
				self.cmd_status()
				
			elif (cmd == 10):
				self.game.print_torpedo_data()
			
			elif (cmd == 11):
				self.game.print_galaxy_record()
### if end
	
			if ( self.check_win() ):
				break	

## def

					
	def warning_shield(self):
		# kingon
		if ( self.game.sector.num_klingon > 0 ):
			print
	 		print "COMBAT AREA      CONDITION RED"
			if ( self.game.shield < self.SHIELD_LOW ):
 				print "   SHIELDS DANGEROUSLY LOW"
	### def
	
	
	def cmd_long_sensor(self):
		if not self.device.check_available(self.device.DEVICE_LONG_SENSOR):
			return
			
		self.game.print_long_sensor( self.device.is_available( self.device.DEVICE_COMPUTER ) )
## def

	def cmd_short_sensor(self):
		if not self.device.check_available(self.device.DEVICE_SHORT_SENSOR):
			return
			
		self.game.print_short_sensor()
		self.print_short_sensor_report()
## def	

	def cmd_phaser(self):
		if not self.device.check_available(self.device.DEVICE_PHASER):
			return
				
		e = self.game_input.input_phaser_energy( self.game.energy )
		self.game.fire_phaser(e)
		self.fight_back()
## def			

	def cmd_torpedo(self):
		if not self.device.check_available(self.device.DEVICE_TORPEDO):
			return
			
		if ( self.game.torpedo < 1 ) :
	 		print "ALL PHOTON TORPEDOES EXPENDED"
	 		return
	 		
		course = self.game_input.input_course()	
		if (course == 0):
			return
			
		self.game.fire_torpedo(course)
		self.fight_back()
## def		

	def cmd_shield(self):	
		if not self.device.check_available(self.device.DEVICE_SHIELD):
			return
			
		e = self.game_input.input_shield_energy( self.game.energy, self.game.shield )
		self.game.shield_control( e )
## def

	def cmd_damege(self):
		if not self.device.check_available(self.device.DEVICE_DAMAGE):
			return
			
		self.device.print_damege_report()
## def

	def cmd_warp(self):	
		if not self.device.check_available(self.device.DEVICE_WARP):
			return
			
		course = self.game_input.input_course()	
		if  (course == 0 ):
			return
			
		distance = self.game_input.input_distance()	
		if ( distance == 0 ):
			return
			
		self.game.warp(course, distance)
## def

	def cmd_impulse(self):
		is_available = True
		if not self.device.check_available(self.device.DEVICE_IMPULSE):
			is_available = False
			distance = 1
			print "MAXIMUM FAXTOR IS 1"
			
		course = self.game_input.input_course()	
		if ( course == 0) :
			return
			
		if is_available :	
			distance = self.game_input.input_distance()	
			if ( distance == 0 ):
				return
						
		code = self.game.move(course, distance)
		if ( code == self.game.sector.C_KLINGON ):
		# damege
			self.game.shield = 0
			self.device.damege_all()	
							
		elif ( code == self.game.sector.C_STARBASE ):
		# repair	
			self.game.dockin()
			self.device.repair_all()
			
		elif ( code == self.game.sector.MOVE_OUT_UP ):
			self.game.warp( self.course.COURSE_UP, 1 )
			
		elif ( code == self.game.sector.MOVE_OUT_DOWN ):
			self.game.warp( self.course.COURSE_DOWN, 1 )
			
		elif ( code == self.game.sector.MOVE_OUT_LEFT ):
			self.game.warp( self.course.COURSE_LEFT, 1 )
			
		elif ( code == self.game.sector.MOVE_OUT_RIGHT ):
			self.game.warp( self.course.COURSE_RIGHT, 1 )
		
## def	

	
													
	def check_win(self):
		ret = False
		if ( self.game.quadrant.num_klingon <= 0 ) :
			ret = True
			self.print_win()
		return ret
## def
    
	def print_win(self):                                         
		print "THE LAST KLINGON BATTLE CRUISER IN THE GALAXY HAS BEEN DESTROYED"
		print "THE FEDERATION HAS BEEN SAVED !!!"
		msg = "YOUR ACTUAL TIME OF MISSION = %d STARDATTES" % self.game.days_elapsed
		print msg		
	### def
	
	def cmd_status(self):
		if not self.device.check_available(self.device.DEVICE_COMPUTER):
			return
		 
		print
		print "STATUS REPORT"
		msg1 = "NUMBER OF KLINGONS LEFT = %d" % self.game.quadrant.num_klingon
		msg2 = "NUMBER OF STARDATES LEFT = %d " % self.game.days_left
		msg3 = "NUMBER OF STARBASES LEFT = %d " % self.game.quadrant.num_starbase
		print msg1
		print msg2
		print msg3						
### def end
					
	def print_short_sensor_report(self): 
		msg1 = "STARDATE %d" % self.game.days_left
		msg2 = "CONDITION " + self.get_condition()
		msg3 = "QUADRANT %d , %d " % ( self.game.quadrant.qx, self.game.quadrant.qy )
		msg4 = "SECTOR %d , %d " % ( self.game.sector.sx, self.game.sector.sy )
		msg5 = "ENERGY %d " % self.game.energy
		msg6 = "PHOTON TORPEDOES %d " % self.game.torpedo
		msg7 = "SHIELDS %d " % self.game.shield
		msg8 =  "COMPUTER ACTIVE" if ( self.device.is_available( self.device.DEVICE_COMPUTER ) )  else "COMPUTER DISABLED"	

		print	
		print msg1
		print msg2
		print msg3
		print msg4
		print msg5
		print msg6
		print msg7
		print msg8
											
### def end

	def get_condition(self):
		ret = "GREEN"
		k = self.game.sector.num_klingon			
		if ( k>0):
			ret = "RED"
			
		elif ( self.game.energy < self.ENERGY_YELLOW ):
			ret = "YELLOW"
			
		elif ( self.game.is_docked ):
					ret = "DOCKED"	
					
		return ret
### def end			

	def fight_back(self):
		self.game.fight_back()
		if ( self.game.shield <= 0):
		# damege	
			self.device.damege_all()				
### def end	
						


	
### class end