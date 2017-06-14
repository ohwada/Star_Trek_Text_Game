//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Game = function () {
	
	this.quadrant = new Quadrant();
	this.sector = new Sector();

	this.days_left = 0;
	this.days_elapsed = 0;
	
	this.energy = 0;
	this.shield = 0;
	this.torpedo = 0;
	this.is_docked = false;

} // constructor


Game.prototype.setup = function () {

		this.quadrant.setup_position();
		this.quadrant.setup_q();
		this.quadrant.count_q();
		var q  = this.quadrant.get_current_q();
		this.sector.setup_s( q );

		
		this.days_left = 300 + 2 *	this.quadrant.num_klingon ;
		
		this.days_elapsed = 0 ;				
		this.energy = 3000 ; 
		this.torpedo = 10 ;
		this.shield = 0 ;
						
} // setup

Game.prototype.long_sensor = function (is_computer_available) {
				
		return this.quadrant.get_long_map( is_computer_available );

} // long_sensor


Game.prototype.short_sensor = function () { 

		return this.sector.get_short_map();
		
} // short_sensor	


Game.prototype.galaxy_record = function () {
				
		return this.quadrant.get_galaxy_map();

} // galaxy_record


Game.prototype.warp = function (course) {
//	alert("game warp");
		if (( course <1 ) || (  course > 8 ) ) {
			return;
		} // if
		
		this.quadrant.warp( course );
		var q = this.quadrant.get_current_q();
		this.sector.setup_s( q );
		this.decrease_energy( 20 );
		this.elapse_days( 1 );
		var msg = "arrive at Quadrant " +  this.quadrant.qx + " , " +this.quadrant.qy;
		alert(msg);		
} // warp


Game.prototype.elapse_days = function (d) { 

		this.days_left -= d;
		this.days_elapsed += d;
		if ( this.days_left < 0 ) {
			this.days_left = 0 ;			
		} // if
		
} // elapse_days


Game.prototype.move = function (course) { 

		if (( course < 1) || ( course > 8 ) ) {
			return;
		} // if
		
		this.is_docked = false ;
		
		var code = this.sector.move( course );  // todo
		
		if ( code == this.sector.C_STARBASE ) {
			self.is_docked = true ;
	} // if
			this.decrease_energy( 10 );	
		this.save_num_q();
		return code ;

} // move


Game.prototype.fire_phaser = function (e) { 

		this.decrease_energy(e);
		 
		var list = this.sector.fire_phaser();  // todo
		
		this.save_num_q();
		
		return list;
		
} // fire_phaser 


Game.prototype.fire_torpedo = function (course) { 			
		if (( course <1 ) && ( course > 8 )) {
			return;
		} // if
		
		var list = this.sector.fire_torpedo(course);
		this.save_num_q();
		
		return list;
		
} // fire_torpedo


Game.prototype.galaxy_record = function () { 

		return this.quadrant.get_galaxy_map();
} // galaxy_record


Game.prototype.torpedo_data = function () { 

	alert("game torpedo_data");

		return this.sector.torpedo_data();
		
} // torpedo_data		


Game.prototype.fight_back = function () { 

		return this.sector.fight_back();
		
} // fight_back		


Game.prototype.decrease_shield = function (s) { 

		this.shield -= s;
		if ( this.shield < 0 ){ 
			this.shield = 0;
	} // if
} // decrease_shield

		
Game.prototype.shield_control = function (e) { 						
		this.decrease_energy(e ); 
		this.shield = e; 
//		alert( "shield on" );

} // shield_control		


Game.prototype.decrease_energy = function (e) { 

		this.energy -= e ;
		if ( this.energy < 0) {
			this.energy = 0;
	} // if	
				
} // decrease_nergy
		 			

Game.prototype.save_num_q = function () { 

		var num = this.sector.get_num();
		this.quadrant.save_current_q(num);
		this.quadrant.count_q();

} // save_num_q
	
	
	Game.prototype.dockin = function () { 	
		this.energy = 3000;
		this.torpedo = 10;
		this.shield = 0	;

} // dockin
