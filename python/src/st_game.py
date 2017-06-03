#
# STAR TREK Text Game
# 2017-05-01 K.OHWADA
#
	
## class
class Game():
	
	SUPPLY_ENERGY = 3000
	SUPPLY_TORPEDO = 10
	
	ENERGY_WARP = 10
	
	quadrant = None
	sector = None

	days_left = 0
	days_elapsed = 0
	
	energy = 0
	shield = 0
	torpedo = 0
	is_docked = False
	
### init	
	def __init__(self):
		from st_quadrant import Quadrant
		from st_sector import Sector
		self.quadrant = Quadrant()
		self.sector = Sector()
#		self.game_input = Input()
#		self.device = Device()
				
### def end

	def setup(self):

		self.quadrant.setup_position()
		self.quadrant.setup_q()
		self.quadrant.count_q()
		q = self.quadrant.get_current_q()
		self.sector.setup( q )

		
		self.days_left = 300 + 2 *	self.quadrant.num_klingon
		self.days_elapsed = 0				
		self.energy = self.SUPPLY_ENERGY
		self.torpedo = self.SUPPLY_TORPEDO
		self.shield = 0
						
### def end
			
	def print_long_sensor(self, is_computer_available):
		self.quadrant.print_long_sensor( is_computer_available )
## def


	def print_short_sensor(self):
		self.sector.print_short_sensor()
## def	
		
	def warp(self, course, distance ):
		if ( course <1 ) or (  course > 8 ) or ( distance < 1 ):
			return;

		self.quadrant.warp( course, distance )
		q = self.quadrant.get_current_q()
		self.sector.setup( q )
		self.decrease_energy( self.ENERGY_WARP*distance )
		self.elapse_days( distance )		
### def end	

	def elapse_days(self, d):
		self.days_left -= d
		self.days_elapsed += d
		if ( self.days_left < 0 ):
			self.days_left = 0			
### def end	

	def move(self, course, distance ):
		if ( course < 1) or ( course > 8 ) or ( distance < 1 ):
			return;
			
		self.is_docked = False
		code = self.sector.move( course, distance )
		if ( code == self.sector.C_STARBASE ):
			self.is_docked = True
		self.save_num_q()
		return code
### def end	

	def fire_phaser(self, energy ):
		self.decrease_energy( energy ) 
		self.sector.fire_phaser(energy) 
		self.save_num_q()
### def end	
			
	def fire_torpedo(self, course ):
		if ( course <1 ) or ( course > 8 ):
			return;

		self.sector.fire_torpedo(course)
		self.save_num_q()
### def end	

	def print_galaxy_record(self):
		self.quadrant.print_galaxy_record()
## def

	def print_torpedo_data(self):
		self.sector.print_torpedo_data()
## def

	def fight_back(self):
		list = self.sector.fight_back()
		if ( len(list) == 0):
			return
			
		for param in list:
			x = param[0]
			y  = param[1]
			beam = param[2]
			self.decrease_shield( beam )
			msg = " %d UNIT HIT ON ENTERPRISE AT SECTOR %d , %d ( %d LEFT)"% ( beam, x, y, self.shield )
			print msg
							
### def end	

	def decrease_shield(self, s):
		self.shield -= s
		if ( self.shield < 0 ): 
			self.shield = 0
### def end	
						
	def shield_control(self, energy ):
		self.decrease_energy( energy ) 
		self.shield = energy 
		print "SHIELD = " + str(energy)
### def end

	def decrease_energy(self, energy):
		self.energy -= energy 
		if (self.energy < 0):
			self.energy = 0
### def end

	def save_num_q(self):
		num1 = self.sector.get_num()
		self.quadrant.save_current_q(num1)
		self.quadrant.count_q()

	### def end	
	
	def dockin(self):	
		print "SHIELDS DROPPED FOR DOCKING PURPOSES"
		self.energy = self.SUPPLY_ENERGY
		self.torpedo = self.SUPPLY_TORPEDO
		self.shield = 0	
	### def end	
							
		### class end