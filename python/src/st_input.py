#
# STAR TREK TEXT GAME
# 2017-05-01 K.OHWADA
#

class Input():
	
	def __init__(self):
		pass				
### def end


	def input_command(self):
		cmd = 0
		while(True):
			print
			print " COMMAND"
			print " 0 = CANCEL"
			print " 1 = LONG RANGE SENSOR SCAN"
			print " 2 = SHORT RANGE SENSOR SCAN"
			print " 3 = FIRE PHASERS"
			print " 4 = FIRE PHOTON TORPEDOES"
			print " 5 = SHIELD CONTROL"
			print  " 6 = DAMAGE CONTROL REPORT"
			print " 7 = WARP ENGINE"
			print " 8 = IMPULSE ENGINE"
			print " 9 = CALL ON LIBRARY COMPUTER"
			cmd = input( 'command(1-9) >' )
			if ( cmd >=0 ) and ( cmd <=9 ):
				break
### while
		return cmd
### def	

	def input_computer_command(self):	
		func = 0
		print
		print "FUNCTIONS AVAILABLE FROM COMPUTER"
		print " 0 = CANCEL"
		print " 1 = STATUS REPORT"
		print	 " 2 = CUMULATIVE GALACTIC RECORD"
		print " 3 = PHOTON TORPEDO DATA"
		func = input(' commsnd(1-3) >')
		return func
### def	
	
	def input_course(self):
		course = 0
		while(True):
			print	
			print " COURSE"
			print " 0 = CANCEL"
			print " 1 = LEFT"
			print " 2 = UP LEFT"
			print " 3 = UP"
			print " 4 = UP RIGHT"
			print " 5 = RIGHT"
			print " 6 = DOWN RIGHT"
												
			print " 7 = DOWN"
			print " 8 = DOWN LEFT"
			print
			print " 4 3 2"
			print " 5 E 1"
			print " 6 7 8"
			course = input('course(1-8) >')
			if ( course >= 0  ) and ( course <=8 ):
				break
			### while
		return  course
### def

	def input_distance(self):
		distance = 0
		while(True):
			print " please enter DISTANCE"
			distance = input('distance(1-8)>')
			if ( distance >=0 ) and ( distance <=8 ):
				break
### while

		return distance
### def

	def input_phaser_energy(self,energy):
		e = 0
		print "PHASERS LOCKED ON TARGET"
		msg =  "ENERGY AVAILABLE= %d " % energy
		print msg
		print "ENTER NUMBER OF UNITS TO FIRE"
		e= input( 'energy>')
		return e
		### def
		
	def input_shield_energy(self,energy, shield):
		s = 0
		msg1 = "YOU HAVE %5d UNITS OF ENERGY" % energy
		msg2 = "YOUR SHIELDS HAVE %d UNITS LEFT" % shield
		print msg1
		print msg2			
		print "ENTER NUMBER OF UNITS FOR SHIELD"	
		s = input('nergy>')
		return s
### def


### class