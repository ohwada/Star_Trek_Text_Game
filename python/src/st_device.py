#
# STAR TREK Text Game
# 2017-05-01 K.OHWADA
#

import random	

## class
class Device():


	
	DEVICES = [ "L.R. SENSOR",   "S.R. SENSOR", "PHASER CNTRL", "TORPEDO TUBES", "SHIELD CNTRL", "DAMAGE CNTRL", "WARP ENGINE", "IMPULSE ENGINE", "COMPUTER" ]
	 
	SIZE_D = 9
	
	# 30 %
	DAMEGE_PROBABILITY = 0.3
	
# very easy : 50 %
	SHORT_SENSOR_REPAIR_PROBABILITY = 0.5
		
	DEVICE_LONG_SENSOR = 0
	DEVICE_SHORT_SENSOR = 1
	DEVICE_PHASER = 2
	DEVICE_TORPEDO = 3
	DEVICE_SHIELD = 4
	DEVICE_DAMAGE = 5
	DEVICE_WARP = 6
	DEVICE_IMPULSE = 7
	DEVICE_COMPUTER = 8 

# damege level : 0 , pluse: normal  minus: dameged
	d_arr = []
	
	def __init__(self):
		pass				
### def end

	def setup(self):
		self.d_arr = [0 for i in range(self.SIZE_D)]	
### def end

	def print_damege_report(self):
		print
		print "DAMAGE CONTROL REPORT"
		for i in range(self.SIZE_D):
			status = "Normal" if self.is_available(i) else "Dameged"
			msg = self.DEVICES[i] + " : " + status
			print msg
### for i
### def end

	def is_available(self, id):
		ret = True if ( self.d_arr[id] >= 0 ) else False
		return ret
### def end

	def check_available(self, id):
		ret = False
		if ( self.is_available(id) ) :
			ret = True
		else:
			msg = self.DEVICES[id] + " dameged"
			print msg
### if end			
		return ret
### def end	

	def change_available(self):
		
		### 0 - 8
		id = int((self.SIZE_D -1)*random.random() )
		
	### 1 - 6
		level = int(5*random.random() ) + 1
		
		if ( random.random() <  self.DAMEGE_PROBABILITY ):
			self.d_arr[id] -= level
		else:
			self.d_arr[id] += level
			
# repair specially
# because player can do nothing
# when SR Sensor breaks
		if ( random.random() <  	self.SHORT_SENSOR_REPAIR_PROBABILITY ):
			self.d_arr[	self.DEVICE_SHORT_SENSOR ] = 0			
### def end			


	def damege_all(self):	
		self.set_available_all( -1)	
### def end	

	def repair_all(self):	
		self.set_available_all( 0 )	
### def end

	def set_available_all(self, value):	
			for i in range(self.SIZE_D):
				self.d_arr[i] = value
### def end
				
### class end