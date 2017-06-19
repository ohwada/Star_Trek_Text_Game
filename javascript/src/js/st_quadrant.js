//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Quadrant = function () {
	 
	 this.course = new Course();
	 
	 // 8x8 Quadrant
	 this.q_arr= [];
	 
	 // galsxy record
	 this.g_arr= [];
	 
this.qx = 0;
this.qy = 0;

this.num_klingon = 0;
this.num_starbase = 0;
this.num_star = 0;
			
}

Quadrant.prototype.setup_position = function () {
	// 1 - 6
	this.qx = Math.floor( 5 *　Math.random() ) + 1 ;
	this.qy = Math.floor( 5 *　Math.random() ) + 1;
	
	// alert( "setup_position" + this.qx + ","+ this.qy);
	
} // setup_position


Quadrant.prototype.setup_q = function () {
	
var i = 0;
var j = 0;


for(i=0;i < 8;i++){
			this.q_arr[i] = [];
			this.g_arr[i] = [];
			
	for(j=0;j < 8;j++){
				this.q_arr[i][j] = [];
		this.q_arr[i][j][0] = 0;
		this.q_arr[i][j][1] = 0;
		this.q_arr[i][j][2] = 0;
		this.g_arr[i][j] = "***";

// setup 3x3 area		
 		if (( i >= this.qx - 1) && ( i <= this.qx + 1) && ( j >= this.qy - 1) && ( j <= this.qy + 1)) {
 			
 			// enterprise or 50 %
			if (((i == this.qx) && (j == this.qy)) || (Math.random() < 0.5) ) {
				
		// klingon 1 - 3		
		this.q_arr[i][j][0] = Math.floor( 2 *　Math.random() ) + 1 ;
		
		// starbase 0 or 1, 50 %
		this.q_arr[i][j][1] = ( Math.random() < 0.5 ) ? 1: 0;
		
		// star 1 - 5
		this.q_arr[i][j][2] = Math.floor( 4 *　Math.random() ) + 1 ;
		
		} // if enterprise or 50 %
} // if3x3 area

}} // for i j

} // setup_q

	
Quadrant.prototype.scan = function (is_computer_available) {
	
var ret = "LONG RENGE SENSOR <br />\n"	
var map = this.get_long_map(is_computer_available);
ret += this.build_map( map );

	return ret;
	
} // scan


Quadrant.prototype.galaxy_record = function () {
	
var ret = "CUMULATIVE GALACTIC RECORD <br />\n"	;
var map = this.get_galaxy_map();
ret += this.build_map( map );

	return ret;
	
} // galaxy_record


Quadrant.prototype.get_long_map = function ( is_computer_available ) {
	
var i = 0;
var j = 0;

var map = [] ;
var mark = "";
var info = "";
	

for(i=0;i < 8;i++){
	map[i] = [] ;
	for(j=0;j < 8;j++){
		mark = "***" ;
		
		// scam 3x3 area
 		if (( i >= this.qx - 1) && ( i <= this.qx + 1) && ( j >= this.qy - 1) && ( j <= this.qy + 1)) {

		info = String(this.q_arr[i][j][0] ) + String(this.q_arr[i][j][1] ) + String(this.q_arr[i][j][2] ) ;
		mark = info;
		
		//copy to galaxy record
		if ( is_computer_available ) {
			this.g_arr[i][j] = info;
		} // if is_computer_available
		
} // if scam 3x3 area

// enterprise
	  	if ( (i == this.qx) && (j== this.qy) ) {
	  		mark = "E" + mark;
	  	} // if enterprise
	  	
	  	
	  	map[i][j] = mark;
	  	
}} // for   i j

	return map;
	
} // get_long_map


Quadrant.prototype.get_galaxy_map = function () {
	
var i = 0;
var j = 0;

var map = [] ;
var mark = "";

	
for(i=0;i < 8;i++){
	map[i] = [] ;
	
	for(j=0;j < 8;j++){
		mark = this.g_arr[i][j];


// enterprise
	  	if ( (i == this.qx) && (j== this.qy) ) {
	  		mark = "E" + mark;
	  	} // if enterprise
	  	
	  	
	  	map[i][j] = mark;
	  	
}} // for   i j

	return map;
	
} // get_galaxy_map




Quadrant.prototype.build_map = function (map) {
		
var i = 0;
var j = 0;

var ret = "";

// render
ret += "&nbsp;&nbsp;";
for(j=0;j < 8;j++){
		ret += "&nbsp;" + String(j) + "&nbsp;";
}
ret += "<br/>\n";

for(i=0;i < 8;i++){
	ret += String(i) + "&nbsp;";
	for(j=0;j < 8;j++){


	  	ret += map[i][j] + "&nbsp;";
} // for  j
	ret += "<br/>\n";
		  	
} // for i
ret += "at Quadrant " + String(this.qx) + " , " + String(this.qy) + "<br/>\n";
	return ret;
	
} // bild_map


Quadrant.prototype.count_q = function () {
	
var i = 0;
var j = 0;

var k = 0;
var b = 0;
var s = 0;


for(i=0;i < 8;i++){
	for(j=0;j < 8;j++){
		k += this.q_arr[i][j][0];
		b += this.q_arr[i][j][1];
		s += this.q_arr[i][j][2];
	}} // for i j
	
	this.num_klingon = k;
		this.num_starbase = b;
			this.num_star = s;
			
	} // count			
	
	
	Quadrant.prototype.get_current_q = function () {
	//		alert( this.qx + ","+ this.qy);
		var param = this.q_arr[this.qx][this.qy];
		return param;
	} // get_current_q	
	
	
		Quadrant.prototype.save_current_q = function (param) {
		this.q_arr[this.qx][this.qy][0] = param[0];
		this.q_arr[this.qx][this.qy][1] = param[1];
		this.q_arr[this.qx][this.qy][2] = param[2];
						
	} // save_current_q	
	
	Quadrant.prototype.warp = function (course) {
		if (( course <1 ) || (  course > 8 ) ) {
			return;
		} // if
			
	//	alert("warp ...");
	
	var d = this.course.get_delta(course);
		
				// distance is 1
		var x = this.qx + d[0];
		var y = this.qy + d[1];	

 		if (( x >= 0) && ( x < 8) && ( y >= 0 ) && ( y < 8 )) {			
			this.qx = x;
			this.qy = y;
			return true;
				
		} else { 
		
			this.setup_position();
			return false;
			
		} // if
			
		} // warp	