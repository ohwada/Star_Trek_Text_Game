//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Device = function () {

	this.DEVICES = [ "L.R. SENSOR",   "S.R. SENSOR", "PHASER CNTRL", "TORPEDO TUBES", "SHIELD CNTRL", "DAMAGE CNTRL", "WARP ENGINE", "IMPULSE ENGINE", "COMPUTER" ];
	
	this.DEBUG_AVAILABLE = true;
		
	this.DEVICE_LONG_SENSOR = 0;
	this.DEVICE_SHORT_SENSOR = 1;
	this.DEVICE_PHASER = 2;
	this.DEVICE_TORPEDO = 3;
	this.DEVICE_SHIELD = 4;
	this.DEVICE_DAMAGE = 5;
	this.DEVICE_WARP = 6;
	this.DEVICE_IMPULSE = 7;
	this.DEVICE_COMPUTER = 8;

	// damege level : 0 , pluse: normal  minus: dameged
	this.d_arr = [];
	
	} // constractor	

		
	Device.prototype.setup = function () {
		var k =0;
		for(k=0;k < 9;k++) {
		this.d_arr[k] = 0;	
	} // for k
	
	} // setup 		


	Device.prototype.damege_report = function () {

		
		var msg = "DAMAGE CONTROL REPORT<br/>\n";
		
				var k =0;
				var status = "";
				
		for(k=0;k < 9;k++) {
			status = (this.is_available(k) ) ? "Normal" : "Dameged";
			msg += this.DEVICES[k] + " : " + status + "<br/>\n" ;
	} // for k

	return msg;
} // damege_report


Device.prototype.is_available = function (id) {
		if ( this.DEBUG_AVAILABLE ) {
			return true;
		} // if
		
		var ret = ( this.d_arr[id] >= 0 ) ? true :  false ;
		return ret;
		
} // is_available		


Device.prototype.check_available = function (id) {

		var ret = false;
		var msg = "";
		if ( this.is_available(id) ) {
			ret = true;
		} else {
			msg = this.DEVICES[id] + " dameged";
			alert( msg );
		
		} // if
					
		return ret ;

} // check_available


	
	Device.prototype.change_available = function () {
		
		// 0 - 8
		var id = Math.floor(8*Math.random() ) ;
		
	// 1 - 6
		var level = Math.floor(5*Math.random() ) + 1 ;
		
		if ( Math.random() <  0.3 ) {
			this.d_arr[id] -= level ;
		} else {
			this.d_arr[id] += level ;
		} // if
			
// repair specially
// because player can do nothing
// when SR Sensor breaks
		if ( Math.random() < 0.3  ) {
			this.d_arr[	this.DEVICE_SHORT_SENSOR ] = 0	;
		} // if
						
} // change_available	
	

	Device.prototype.damege_all = function () {
		this.set_available_all( -1);	
} // damege_all


	Device.prototype.repair_all = function () {	
		this.set_available_all( 0 );	
} // repair_all 


	Device.prototype.set_available_all = function (value) {	

				var k =0;
				
		for(k=0;k < 9;k++){
					this.d_arr[k] = value;
		} // k
								
} // set_available_all



Device.prototype.damege_phaser = function () {	
	
	// 50    5
	if ( Math.random() < 0.5 ) {
this.d_arr[ this.DEVICE_PHASER ] = -1;
 }// if 
								
} // damege_phaser