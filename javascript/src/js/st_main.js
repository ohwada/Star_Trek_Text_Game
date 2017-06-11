//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Main  = function () {	

	
	this.game = new Game();	
	this.device = new Device();
	this.course = new Course();	
				this.cmd = 0;	
} // constractor


Main.prototype.start_game = function () {

		this.game.setup();
		this.device.setup();
		this.alert_mission();
		
} // start_game


Main.prototype.alert_mission = function () {	


		var msg = "=== MISSION === \n\n";
		msg += "YOU MUST DESTROY " + this.game.quadrant.num_klingon + " KLINGONS IN " + this.game.days_left + " STARDATES WITH " + this.game.quadrant.num_starbase + " STARBASES";
		alert(msg);
		
} // mission


Main.prototype.proc_cmd = function (cmd) {	

			this.cmd = cmd;
			this.device.change_available();
			this.warning_shield();

			if (cmd == 1) {
			// long reng sensor
				this.cmd_long_sensor();
						
			} else if (cmd == 2) {	
			// short reng sensor	
				this.cmd_short_sensor();
				
			} else if (cmd == 3) {
			// fire phaser
				this.cmd_phaser();
			
			} else if (cmd == 4) {
			// fire torpedo
				this.cmd_torpedo();
								
			} else if (cmd == 5) {	
			// shield	 control
				this.cmd_shield();
				
			} else if (cmd == 6) {	
			// damege report	
				this.cmd_damege();
			
			} else if (cmd == 7) {	
			// warp engine
				this.cmd_warp();
				
			} else if (cmd == 8) {	
			// impulse engine
				this.cmd_impulse();
									
			} else if (cmd == 9) {	
				this.cmd_status();
				
			} else if (cmd == 10) {
				this.cmd_torpedo_data();
			
			} else if (cmd == 11) {
				this.cmd_galaxy_record();
} // if end
	
			this.check_win();
					
} // proc_cmd


Main.prototype.proc_course = function (course) {
	
//	alert("course "+ course);
//		alert("cmd "+ this.cmd );

	 if (this.cmd == 4) {
			// fire torpedo
				this.proc_torpedo(course);
					
		} else if (this.cmd == 7) {	
			// warp engine
				this.proc_warp(course);
				
			} else if (this.cmd == 8) {	
			// impulse engine
				this.proc_impulse(course);	
	 
	 	} // if
	 	
	 		this.cmd = 0;
			this.check_win();
				 	
} //proc_course		


	Main.prototype.cmd_torpedo_data = function () {	
				var msg = this.game.torpedo_data();
						 $("#report").html(msg);
						 
	} // cmd_torpedo_data
	

	Main.prototype.cmd_galaxy_record = function () {		
					var map = this.game.galaxy_record();
					$("#map1").html(map);
					
			} // md_galaxy_record
							
							 				
	Main.prototype.warning_shield = function () {			
	
		// kingon
		if (( this.game.sector.num_klingon > 0 ) && ( this.game.shield < 200 )) {
		
 				alert( "SHIELDS DANGEROUSLY LOW" );
		} // if
		
		} // warning_shield		
	
	
		Main.prototype.cmd_long_sensor = function () {

		if ( !this.device.check_available(this.device.DEVICE_LONG_SENSOR)) {
			return;
	} // if	
	
		var is_computer_available = this.device.is_available( this.device.DEVICE_COMPUTER );

	
		var map = this.game.long_sensor( is_computer_available );
			 $("#map1").html(map);
			 
} // cmd_long_sensor


		Main.prototype.cmd_short_sensor = function () {

		if ( ! this.device.check_available(this.device.DEVICE_SHORT_SENSOR) ) {
			return;
	} // if	
			
		var msg =this.game.short_sensor();
		msg += this.short_sensor_report();
		$("#map2").html(msg);
		
} // cmd_short_sensor	


		Main.prototype.cmd_phaser = function () {
		if(! this.device.check_available(this.device.DEVICE_PHASER)) {
			return;
			} // if
				
		if ( this.game.energy < 500 ) {	
	var msg = "require	 500 unit of energy to fire phaser";
	alert (msg);
		return;
		} // if
		
		this.game.fire_phaser(500);
		
		this.fight_back();
		
		

// do not to use consecutively
// because the game becomes easy,
		this.device.damege_phaser();
		
		
} // cmd_phaser
		
			
Main.prototype.cmd_torpedo = function () {

		if ( ! this.device.check_available(this.device.DEVICE_TORPEDO)) {
			return;
	} // if	
			
		if ( this.game.torpedo < 1 ) {
	 		var msg = "ALL PHOTON TORPEDOES EXPENDED";
	 		alert(msg);
	 		return;
	 } // if
	 
	 alert("please select course");	
	 
	} // 	cmd_torpedo
	
		
Main.prototype.proc_torpedo = function (course) {	 					
		var code = this.game.fire_torpedo(course);
						if ( code == this.game.sector.C_KLINGON ) {
							
					alert( "KLINGON DESTROYED ");
					
				} else if ( code == this.game.sector.C_STARBASE ) {
					alert( "STAR BASE DESTROYED" );					
					} else if ( code == this.game.sector.C_STAR )	{		
					alert( "YOU CAN'T DESTROY STARS" );			
					} else if ( code == this.game.sector.TORPEDO_OUT )	{							
							  		alert( "TORPEDO MISSED" );
			} // if		  		
							  		
		this.fight_back();
		
	} // roc_torpedo	
	
	
		Main.prototype.cmd_shield = function () {	
	
		if ( !this.device.check_available(this.device.DEVICE_SHIELD)) {
			return;
	} // if	
			
			if ( this.game.energy < 200 ) {	
	var msg = "require	 200 unit of energy to shield";
	allert (msg);
		} // if		

		this.game.shield_control( 200 );
		
} // cmd_shield


	Main.prototype.cmd_damege = function () {

		if (! this.device.check_available(this.device.DEVICE_DAMAGE)) {
			return;
	} // if	
			
		var msg = this.device.damege_report();
		 $("#report").html(msg);
		 
} // cmd_damege


	Main.prototype.cmd_warp = function () {

		if ( ! this.device.check_available(this.device.DEVICE_WARP) ) {
			return;
	} // if	
	
			if ( this.game.energy < 20 ) {	
	var msg = "require	 20 unit of energy to warp";
	allert (msg);
		} // if	
			
		 alert("please select course");
		 
} // cmd_warp	 
			

	Main.prototype.proc_warp = function (course) {			
		
//		alert("proc_warp");
	
		this.game.warp(course);
		
} // proc_warp


	Main.prototype.cmd_impulse = function () {

		 this.device.check_available(this.device.DEVICE_IMPULSE);
		 
			 alert("please select course");
			 		
	} // md_impulse
	
	
	Main.prototype.proc_impulse = function (course) {		
		
		var msg = "";				
		var code = this.game.move(course);
			if ( code == this.game.sector.MOVE_TO ) {
				msg = "move to " + String(this.game.sector.sx) + " , " + String(this.game.sector.sy);
				alert( msg );			
				
		} else if ( code == this.game.sector.C_KLINGON ) {
			alert ( "onflicted with KLINGON" );
		// damege
			this.game.shield = 0;
			this.device.damege_all();	
							
		} else if ( code == this.game.sector.C_STARBASE ) {
				alert( "docked in STARBASE" );
		// repair	
			this.game.dockin();
			this.device.repair_all();
			
			} else if ( code == this.game.sector.C_STAR )	{
					alert( "landed on STAR" );
		
		} else if ( code == this.game.sector.MOVE_OUT_UP ) {
			this.game.warp( this.course.COURSE_UP, 1 );
			
		} else if ( code == this.game.sector.MOVE_OUT_DOWN ) {
			this.game.warp( this.course.COURSE_DOWN, 1 );
			
		} else if ( code == this.game.sector.MOVE_OUT_LEFT ){
			this.game.warp( this.course.COURSE_LEFT, 1 );
			
		} else if ( code == this.game.sector.MOVE_OUT_RIGHT ) {
			this.game.warp( this.course.COURSE_RIGHT, 1 );
			
		} // if
		
} // proc_impulse

	

	
Main.prototype.check_win = function (course) {			
		var ret = false;
		if ( this.game.quadrant.num_klingon <= 0 ) {
			ret = true;
			this.alert_win();
	} // if
		
		return ret;
		
} // check_win
		

Main.prototype.alert_win = function (course) {	    
            var msg ="=== CONGRATULATIONS === \n\"                             
		var msg = "THE LAST KLINGON BATTLE CRUISER IN THE GALAXY HAS BEEN DESTROYED \n";
		msg += "THE FEDERATION HAS BEEN SAVED !!!\n";
		msg += "YOUR ACTUAL TIME OF MISSION = " + this.game.days_elapsed + " STARDATTES\n"; 
		alert( msg );
				
} // alert_win
	
	
	Main.prototype.cmd_status = function () {

		if ( ! this.device.check_available(this.device.DEVICE_COMPUTER) ) {
			return;
	} // if	
		 
	var msg = "STATUS REPORT<br/>\n";
		msg += "NUMBER OF KLINGONS LEFT = " + this.game.quadrant.num_klingon + "<br/>\n"; 	
		msg += "NUMBER OF STARDATES LEFT = " +  this .game.days_left + "<br/>\n"; 
		msg += "NUMBER OF STARBASES LEFT = " + this.game.quadrant.num_starbase + "<br/>\n";

		 $("#report").html(msg);
		 
} //cmd_status		 					


	Main.prototype.short_sensor_report = function () {					

		var msg = "STARDATE " + this.game.days_left + "<br/>\n"; 
		msg += "CONDITION " + this.get_condition() + "<br/>\n"; 

		msg += "QUADRANT " + this.game.quadrant.qx  + " , " + this.game.quadrant.qy + "<br/>\n"; 

		msg += "SECTOR " + this.game.sector.sx   + " , " + this.game.sector.sy + "<br/>\n";
		msg += "ENERGY " + this.game.energy + "<br/>\n";
		msg += "PHOTON TORPEDOES " + this.game.torpedo + "<br/>\n";
		msg += "SHIELDS " + this.game.shield + "<br/>\n"; 
		msg +=  ( this.device.is_available( this.device.DEVICE_COMPUTER ) )? "COMPUTER ACTIVE" : "COMPUTER DISABLED" ;
			msg +=	"<br/>\n";
			
	return msg;
	
} // short_sensor_report	
	 

	Main.prototype.get_condition = function () {	

		var ret = "GREEN";
		var k = this.game.sector.num_klingon ;
					
		if ( k>0) {
			ret = "RED";
				
		} else if ( this.game.energy < 300 ) {
			ret = "YELLOW";
			
		} else if ( this.game.is_docked ) {
					ret = "DOCKED";	
	} // if
						
		return ret;
}  // get_condition
		
					
Main.prototype.fight_back = function () {

		this.game.fight_back();
		
		if ( this.game.shield <= 0) {
		// damege	
			this.device.damege_all();
			} // if					

} // fight_back
						
