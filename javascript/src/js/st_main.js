//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Main  = function () {	

	
	this.game = new Game();	
	this.device = new Device();
	this.course = new Course();	
				this.cmd = 0;	
				this.is_map_short = false;
} // constractor


Main.prototype.start_game = function () {

		this.game.setup();
		this.device.setup();
		this.clear_display();
		this.alert_mission();
		
} // start_game


Main.prototype.alert_mission = function () {	


		var title = "=== MISSION ===";
		var msg = "YOU MUST DESTROY " + this.game.quadrant.num_klingon + " KLINGONS IN " + this.game.days_left + " STARDATES WITH " + this.game.quadrant.num_starbase + " STARBASES";
		this.alert(title,msg);
		
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
	
			if ( !this.check_available(this.device.DEVICE_COMPUTER)) {
			return;
	} // if
	
				var msg = this.game.torpedo_data();
						 $("#report").html(msg);
						 
	} // cmd_torpedo_data
	

	Main.prototype.cmd_galaxy_record = function () {
	
				if ( !this.check_available(this.device.DEVICE_COMPUTER)) {
			return;
	} // if
				
					var map = this.game.galaxy_record();
					
			$("#map1_title").html("CUMULATIVE GALACTIC RECORD");
			this.display_map1(map);
				this.display_map1_footer();
								
			} // md_galaxy_record
							
							 				
	Main.prototype.warning_shield = function () {			
	
		// kingon
		if (( this.game.sector.num_klingon > 0 ) && ( this.game.shield < 200 )) {
		
 				this.error( "SHIELDS DANGEROUSLY LOW" );
		} // if
		
		} // warning_shield		
	
	
		Main.prototype.cmd_long_sensor = function () {

		if ( !this.check_available(this.device.DEVICE_LONG_SENSOR)) {
			return;
	} // if	
	
		var is_computer_available = this.device.is_available( this.device.DEVICE_COMPUTER );

	
		var map = this.game.long_sensor( is_computer_available );
			 
			$("#map1_title").html("LONG RENGE SENSOR");
			this.display_map1(map);
			this.display_map1_footer();
						 
} // cmd_long_sensor


		Main.prototype.cmd_short_sensor = function () {

		if ( ! this.check_available(this.device.DEVICE_SHORT_SENSOR) ) {
			return;
	} // if	
			
		var map =this.game.short_sensor();
		var report = this.short_sensor_report();

			$("#map2_title").html("SHORT RENGE SENSOR");
			this.display_map2(map);
			$("#map2_report").html( report );
				
} // cmd_short_sensor	


		Main.prototype.cmd_phaser = function () {
		if(! this.check_available(this.device.DEVICE_PHASER)) {
			return;
			} // if
				
		if ( this.game.energy < 500 ) {	
	var msg = "require	 500 unit of energy to fire phaser";
	this.alarm(msg);
		return;
		} // if
		
		list = this.game.fire_phaser(500);
		
		var length = list.length;
		if( length == 0 ) {
			// no result
			return;
		} // if
		
		var k= 0;
		var param = []
		var x = 0;
		var y = 0;
		var msg = "";
		
		for (k=0;k<length; k++ ) {
			param = list[k];
			code = param[0];
			x = param[1];		
			y = param[2];
			if ( code == this.game.sector.C_KLINGON ) {
				this.set_map2_backgroundColor( x,y, "red" );
						  msg = "KLINGON DESTROYED at " +String(x) + " , "+String(y);
						  this.info(msg);
					continue;
					
			} // if	
				
		} // for k
		
		this.fight_back();
		
		

// do not to use consecutively
// because the game becomes easy,
		this.device.damege_phaser();
		
		
} // cmd_phaser
		
			
Main.prototype.cmd_torpedo = function () {

		if ( ! this.check_available(this.device.DEVICE_TORPEDO)) {
			return;
	} // if	
			
		if ( this.game.torpedo < 1 ) {
	 		var msg = "ALL PHOTON TORPEDOES EXPENDED";
	 		this.alarm(msg);
	 		return;
	 } // if
	 
	 this.alert("COMMAND", "please select course");	
	 
	} // 	cmd_torpedo
	
		
Main.prototype.proc_torpedo = function (course) {	 					
		var list = this.game.fire_torpedo(course);
		var length = list.length;
		if(length == 0) {
			// no result
			return;
		}
		
		var k = 0;
		var param = [];
		var code = 0;
		var x = 0;
		var y=0;
		
		
		for ( k=0; k<length; k++ ) {
		
			param = list[k];
			code = param[0];
			x = param[1];
			y = param[2];	
			
								if ( code == this.game.sector.C_NONE ) {
						this.set_map2_backgroundColor( x,y, "yellow" );
						this.sleep(1000); // todo
						continue;
									
						} else if ( code == this.game.sector.C_KLINGON ) {
						this.set_map2_backgroundColor( x,y, "red" );		
					this.info( "KLINGON DESTROYED ");
					break;
				
				} else if ( code == this.game.sector.C_STARBASE ) {
								this.set_map2_backgroundColor( x,y, "red" );
					this.info( "STAR BASE DESTROYED" );	
										break;
														
					} else if ( code == this.game.sector.C_STAR )	{	
								this.set_map2_backgroundColor( x,y, "yellow" );	
					this.info( "YOU CAN'T DESTROY STARS" );			break;
					
					} else if ( code == this.game.sector.TORPEDO_OUT )	{							
							  this.info( "TORPEDO MISSED" );
							  	break;
			} // if
					  		
} // for k
				  		
		this.fight_back();
		
	} // roc_torpedo	
	
	
		Main.prototype.cmd_shield = function () {	
	
		if ( !this.check_available(this.device.DEVICE_SHIELD)) {
			return;
	} // if	
			
			if ( this.game.energy < 200 ) {	
	var msg = "require	 200 unit of energy to shield";
	this.alarm(msg);
		} // if		

		this.game.shield_control( 200 );
		this.info( "shield on" );
		
} // cmd_shield


	Main.prototype.cmd_damege = function () {

		if (! this.check_available(this.device.DEVICE_DAMAGE)) {
			return;
	} // if	
			
		var msg = this.device.damege_report();
		 $("#report").html(msg);
		 
} // cmd_damege


	Main.prototype.cmd_warp = function () {

		if ( ! this.check_available(this.device.DEVICE_WARP) ) {
			return;
	} // if	
	
			if ( this.game.energy < 20 ) {	
	var msg = "require	 20 unit of energy to warp";
	this.alarm(msg);
		} // if	
			
		 this.alert("COMMAND", "please select course");
		 
} // cmd_warp	 
			

	Main.prototype.proc_warp = function (course) {			
		
//		alert("proc_warp");
	
		this.game.warp(course);
		this.is_map_short = false;
		
} // proc_warp


	Main.prototype.cmd_impulse = function () {

		 this.check_available(this.device.DEVICE_IMPULSE);
		 
			 this.alert("COMMAND","please select course");
			 		
	} // md_impulse
	
	
	Main.prototype.proc_impulse = function (course) {		
		
		var msg = "";				
		var code = this.game.move(course);
			if ( code == this.game.sector.MOVE_TO ) {
				msg = "move to " + String(this.game.sector.sx) + " , " + String(this.game.sector.sy);
				this.info( msg );			
				
		} else if ( code == this.game.sector.C_KLINGON ) {
			this.info ( "onflicted with KLINGON" );
		// damege
			this.game.shield = 0;
			this.device.damege_all();	
							
		} else if ( code == this.game.sector.C_STARBASE ) {
				this.info( "docked in STARBASE" );
		// repair	
			this.game.dockin();
			this.device.repair_all();
			
			} else if ( code == this.game.sector.C_STAR )	{
					this.info( "landed on STAR" );
		
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
	
	var title = "=== CONGRATULATIONS ===";                            
	var msg = "THE LAST KLINGON BATTLE CRUISER IN THE GALAXY HAS BEEN DESTROYED \n";
		msg += "THE FEDERATION HAS BEEN SAVED !!! \n";
		msg += "YOUR ACTUAL TIME OF MISSION = " + this.game.days_elapsed + " STARDATTES\n"; 
		this.alert( title, msg );
				
} // alert_win
	
	
	Main.prototype.cmd_status = function () {

		if ( ! this.check_available(this.device.DEVICE_COMPUTER) ) {
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

		var list = this.game.fight_back();
			var length = list.length;
		if ( length == 0) {
			// no result
			return;
	} // if
		
		var k = 0;
		var param = [];
		var x = 0;
		var y = 0;
		var beam = 0;
		var msg = "";
		
		for(k=0;k < length;k++){
			
			param = list[k];
			x = param[0];
			y  = param[1];
			beam = param[2];
			this.game.decrease_shield( beam );
			msg =  String(beam) + " UNIT HIT ON ENTERPRISE AT SECTOR " + String(x) + " , " + String(y) + " ( " + String(this.game.shield) + "  LEFT)";
			this.error(msg);
		} // for

		if ( this.game.shield <= 0) {
		// damege	
			this.device.damege_all();
			} // if					

} // fight_back


Main.prototype.display_map1 = function (map) {						
var table = document.getElementById("map1");
var cell;

for (i=0; i<8; i++ ) {
	for ( j=0; j<8; j++ ) {
cell = table.rows[i].cells[j];
cell.innerHTML = map[i][j] + "&nbsp;";

}} // for i j

} // display_map1


Main.prototype.display_map2 = function (map) {						
var table = document.getElementById("map2");
var cell;

for (i=0; i<8; i++ ) {
	for ( j=0; j<8; j++ ) {
cell = table.rows[i].cells[j];
cell.innerHTML = map[i][j] + "&nbsp;";
cell.style.backgroundColor = "white";
}} // for i j

this.is_map_short = true;
} // display_map2



Main.prototype.set_map2_backgroundColor = function ( i,j,color ) {
	
if ( ! this.is_map_short ) {
	return
	} // if		
					
var table = document.getElementById("map2");
var cell = table.rows[i].cells[j];
cell.style.backgroundColor = color;

} // set_map2_backgroundColor


Main.prototype.display_map1_footer = function () {
var msg = "at Quadrant " + String(this.game.quadrant.qx) + " , " + String(this.game.quadrant.qy) ;

		$("#map1_footer").html(msg);
		
} // isplay_map1_footer


Main.prototype.clear_display = function () {

var table1 = document.getElementById("map1");		
var table2 = document.getElementById("map2");
var cell1;
var cell2;

for (i=0; i<8; i++ ) {
	for ( j=0; j<8; j++ ) {
cell1 = table1.rows[i].cells[j];
cell2 = table2.rows[i].cells[j];
cell1.innerHTML = "";
cell2.innerHTML = "";
cell2.style.backgroundColor = "white";
}} // for i j

		$("#map1_title").html("");
		$("#map2_title").html("");		
		$("#map1_footer").html("");
		$("#map2_report").html("");	
		$("#report").html("");	
					
} // clesr_display

Main.prototype.sleep = function (msec) {
const d1 = new Date();
while (true) {
  const d2 = new Date();
  if (d2 - d1 > msec) {
    break;
    } // if
 } // while 
 
 } // sleep

 
  Main.prototype.check_available = function (id) {

		var ret = false;
		var msg = "";
		if ( this.device.is_available(id) ) {
			ret = true;
		} else {
			msg = this.device.DEVICES[id] + " dameged";
			this.alarm( msg );
		
		} // if
					
		return ret ;

} // check_available

 
  Main.prototype.alert = function (title, content) {
 	
	msgBoxImagePath = "./images/"; 
	
			$.msgBox({
				title: title,
				content: content,
				type: "alert",
			});

} // alert


 Main.prototype.info = function (msg) {
 	
	msgBoxImagePath = "./images/"; 
	
	// show 5 sec
			$.msgBox({
				title: "",
				content: msg,
				type: "info",
        autoClose: true,
        timeOut: 5000,
        showButtons: false,
			});

} // info 

 Main.prototype.error = function (msg) {
 	
	msgBoxImagePath = "./images/"; 
	
	// show 3 sec
			$.msgBox({
				title: "",
				content: msg,
				type: "error",
        autoClose: true,
        timeOut: 3000,
        showButtons: false,
			});

} // error


 Main.prototype.alarm = function (msg) {
 	
	msgBoxImagePath = "./images/"; 
	
	// show 5 sec
			$.msgBox({
				title: "",
				content: msg,
				type: "error",
			});

} // alarm