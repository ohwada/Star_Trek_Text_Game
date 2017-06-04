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
			print "== SELECT COMMAND =="
			print " 1 = L.R. SENSOR SCAN     2 = S.R. SENSOR SCAN"
			print " 3 = FIRE PHASERS         4 = FIRE PHOTON TORPEDOES"
			print " 5 = SHIELD CNTRL         6 = DAMAGE REPORT"
			print " 7 = WARP ENGINE          8 = IMPULSE ENGINE"	
			print " CALL ON LIBRARY COMPUTER"			
			print " 9 = STATUS REPORT       10 = PHOTON TORPEDO DATA"
			print " 11 = CUMULATIVE GALACTIC RECORD"	
			print
			cmd = input( 'command(1-11) >' )
			if ( cmd >=0 ) and ( cmd <=11 ):
				break
### while
		return cmd
### def	

		
	def input_course(self):
		course = 0
		while(True):
			print	
			print " === SELECT COURSE ==="
			print
			print " 4 3 2"
			print " 5 E 1"
			print " 6 7 8"
			print
			course = input('course(1-8) >')
			if ( course >= 0  ) and ( course <=8 ):
				break
			### while
		return  course
### def

	def input_distance(self):
		distance = 0
		while(True):
			print
			print " === ENTER DISTANCE ==="
			print
			distance = input('distance(1-8)>')
			if ( distance >=0 ) and ( distance <=8 ):
				break
### while

		return distance
### def

	def input_phaser_energy(self,energy):
		e = 0
		print
		print "PHASERS LOCKED ON TARGET"
		msg =  "ENERGY AVAILABLE= %d " % energy
		print msg
		print "ENTER NUMBER OF UNITS TO FIRE"
		print
		e= input( 'energy>')
		return e
		### def
		
	def input_shield_energy(self,energy, shield):
		s = 0
		msg1 = "YOU HAVE %5d UNITS OF ENERGY" % energy
		msg2 = "YOUR SHIELDS HAVE %d UNITS LEFT" % shield
		print
		print msg1
		print msg2			
		print "ENTER NUMBER OF UNITS FOR SHIELD"
		print	
		s = input('nergy>')
		return s
### def


### class