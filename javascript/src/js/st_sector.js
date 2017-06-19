//
// STAR TREK Text Game
// 2017-06-01 K.OHWADA
//

var Sector = function () {

	this.COURSE_ILLEGAL = -1;		
	this.C_NONE = 0;	
	this.C_ENTERPRISE = 1;
	this.C_KLINGON = 2;
	this.C_STARBASE = 3;
	this.C_STAR = 4;
		 	this.MOVE_FROM = 21;
	 	this.MOVE_TO = 22;
 	this.MOVE_OUT_UP = 23;
 	this.MOVE_OUT_DOWN = 24;
 	this.MOVE_OUT_LEFT = 25;
 	this.MOVE_OUT_RIGHT = 26;
 	
 	this.TORPEDO_OUT = 31;
 	
	 this.course = new Course();	
		 	 	 	 		
	this.sx =0;
	this.sy =0;	
	
	// 8 x8 Sectors
	this.s_arr = [];
	
	this.num_kllngon = 0;
	this.num_starbase = 0;
	this.num_star = 0;
	
} // constructor


Sector.prototype.setup_s = function (q) {
	var i = 0;
	var j = 0;
	var k = 0;
	var x = 0;
	var y = 0;
	
		var kingons = q[0];
		var b = q[1];	
		var s = q[2];		
		this.num_klingon = kingons;
		this.num_starbase = b;
		this.num_star = s;
		
		// 1 - 6
			this.sx = Math.floor( 5 *　Math.random() ) + 1 ;
	this.sy = Math.floor( 5 *　Math.random() ) + 1;

for(i=0;i < 8;i++){
			this.s_arr[i] = [];
	for(j=0;j < 8;j++){
				this.s_arr[i][j] = this.C_NONE;	
	}} // for i j
	
					
	// enterprise				
		this.s_arr[this.sx][this.sy] = this.C_ENTERPRISE;
		
			// starbase
for(k=0;k < b;k++){
			// 1 - 6	
			x = Math.floor( 5 *　Math.random() ) + 1 ;
			y = Math.floor( 5 *　Math.random() ) + 1 ;

			if ( this.s_arr[x][y] == this.C_NONE ) {
				this.s_arr[x][y] = this.C_STARBASE;
		} // if	
		
} // for k	
	
	// kingon
	for(k=0;k < kingons;k++){
			// 0 - 7	
			x = Math.floor( 7 *　Math.random() );
			y = Math.floor( 7 *　Math.random() );			

			if ( this.s_arr[x][y] == this.C_NONE ) {
				this.s_arr[x][y] = this.C_KLINGON;
		} // if	
		
	} // for k
	

	// star
		for(k=0;k < s;k++){
			// 0 - 7	
			x = Math.floor( 7 *　Math.random() );
			y = Math.floor( 7 *　Math.random() );
			if ( this.s_arr[x][y] == this.C_NONE ) {
				this.s_arr[x][y] = this.C_STAR;
		} // if
		
	} // for k
											
} // setup_s


Sector.prototype.get_num = function () {
		var ret = [ this.num_klingon, this.num_starbase, this.num_star ] ;
		return ret;
		
} // get_num		


		
Sector.prototype.scan = function () {
	
	var i = 0;
	var j = 0;
	
	var map = "";
	var mark = "";	
	
map ="SHORT RENGE SENSOR <br />\n"
// header
map += "&nbsp;&nbsp;";
for(j=0;j < 8;j++){
		map += + String(j) + "&nbsp;";
}
map += "<br/>\n";
	
	for(i=0;i < 8;i++){
		map += String(i) + "&nbsp;";
		
	for(j=0;j < 8;j++){
		mark = "&nbsp;";	
		if ( this.s_arr[i][j] == this.C_ENTERPRISE ) {
					mark = "E";
					
				} else if ( this.s_arr[i][j] == this.C_KLINGON ) {
					mark = "K";
									
				} else if ( this.s_arr[i][j] == this.C_STARBASE ) {
					mark = "B";
										
				} else if ( this.s_arr[i][j] == this.C_STAR ) {
					mark = "*";
				
			} // if
			map += mark + "&nbsp;";
		
	} // for j
	map += "<br/>\n";	

} // for i
	return map;

} // scan


Sector.prototype.get_short_map = function () {
	
	var i = 0;
	var j = 0;
	
var map = [] ;
	
	var mark = "";	
	
	for(i=0;i < 8;i++){
			map[i] = [] ;
		
	for(j=0;j < 8;j++){
		mark = " ";	
		if ( this.s_arr[i][j] == this.C_ENTERPRISE ) {
					mark = "E";
					
				} else if ( this.s_arr[i][j] == this.C_KLINGON ) {
					mark = "K";
									
				} else if ( this.s_arr[i][j] == this.C_STARBASE ) {
					mark = "B";
										
				} else if ( this.s_arr[i][j] == this.C_STAR ) {
					mark = "*";
				
			} // if
			
			map[i][j] = mark;
		
	}} // for i j

	return map;

} // get_short_map

						
Sector.prototype.torpedo_data = function () {
	
//	alert("torpedo_data");
	var i = 0;
	var j = 0;
	var msg = "";
	var data = "";
	var c = 0 ;
	
	data += "SHIP'S & TARGET'S COORDINATES ARE <br/>\n";
	
	for(j=0;j < 8;j++){	
	for(i=0;i < 8;i++){
		
		if ( this.s_arr[i][j] == this.C_KLINGON ) {
					c = this.course.get_course( this.sx, this.sy, i, j );
					
			 		msg = "x= " + String(i) +" , y= " + String(j) + " : course= " + String(c) + " ";
			 		data += msg + "<br/>\n";
			 		
			} // if
	}} // for i j
		return data;
		
}	//torpedo_data		 	


Sector.prototype.move = function ( course ) {
	
			if (( course <1 ) || (  course > 8 ) ) {
			return this.COURSE_ILLEGAL ;
		} // if
		
	//	alert("move ...");
	
	list = [];				
			 
	//	var code = this.C_NONE ;
		var msg = "";
		
		var d = this.course.get_delta(course );
		
		// distance is 1
		var x = this.sx + d[0] ;
		var y = this.sy + d[1] ;
		
	if ( ( x >= 0 ) && ( x < 8 ) &&  ( y >= 0 ) && ( y < 8 ) ) {
	
			if ( this.s_arr[x][y] == this.C_NONE )	{
				
				// move from, remove current position
				list.push( [ this.MOVE_FROM, this.sx, this.sy ] );
		this.s_arr[this.sx][this.sy] = this.C_NONE ;
		
					// move to	
							list.push( [ this.MOVE_TO, x, y ] );
					//code = this.MOVE_TO ;
					this.s_arr[x][y] = this.C_ENTERPRISE ;
					this.sx = x ;
					this.sy = y ;
		
					
			} else if ( this.s_arr[x][y] == this.C_KLINGON ) {
					// conflct
								list.push( [ this.C_KLINGON, x, y ] );
				 	// destroy				
							this.s_arr[x][y] = this.C_NONE;
							this.decrease_klingon();	
					// code = self.C_KLINGON ;


			
			} else if ( this.s_arr[x][y] == this.C_STARBASE ) {
												list.push( [ this.C_STARBASE, x, y ] );
					// code = this.C_STARBASE ;
				

			} else if ( this.s_arr[x][y] == this.C_STAR )	{
					list.push( [ this.C_STAR, x, y ] );
					// code = this.C_STAR ;
					
			} // s_arr
	
		} else if ( x < 0 ) {
								list.push( [ this.MOVE_OUT_UP, x, y ] );
				// code = this.MOVE_OUT_UP ;
				
		 } else if ( x >= 8 ) {
		 		list.push( [ this.MOVE_OUT_DOWN, x, y ] );
				// code = this.MOVE_OUT_DOWN ;

								
		} else if ( y < 0 ) {
				list.push( [ this.MOVE_OUT_LEFT, x, y ] );
				// code = this.MOVE_OUT_LEFT ;
				
		} 	else if ( y >= 8 ) {
							list.push( [ this.MOVE_OUT_RIGHT, x, y ] );
				// code = this.MOVE_OUT_RIGHT ;
		
		} // if x y
		
		
		return list;
		//return code ; 
	
} // move


	
Sector.prototype.fire_phaser = function () {
		
	//	alert("fire_phaser");	
		
		var list = [];
		var i = 0;
	var j = 0;
	var msg = "";
	
	for(j=0;j < 8;j++){	
	for(i=0;i < 8;i++){	
	// far 30%
	 probability = 0.3;
	 		
	 	if ( this.s_arr[i][j] == this.C_KLINGON ) {	
	
	// near destrroy 50 %
	if (Math.random() < 0.5) {
		this.s_arr[i][j] == this.C_NONE;
		list.push( [ this.C_KLINGON, i,j ] );
		//  msg = "KLINGON DESTROYED at " +String(i) + " , "+String(j);
		//alert( msg	);
			
	} // if random

	} // if C_KLINGON
	
	}} // for i j
	
	return list;
} // fire_phaser



Sector.prototype.fight_back = function () {
	
	var list = [];
	
		var i = 0;
	var j = 0;
	var beam = 0;

	
	for(j=0;j < 8;j++){	
	for(i=0;i < 8;i++){	
	
		  if ( this.s_arr[i][j] == this.C_KLINGON ) {
			// 30 %
				if (Math.random() < 0.3) {		
			beam = this.get_fight_back_beam(i, j);
			list.push([ i,j,beam] );
			} // if random
			
	} //if C_KLINGON
	
}} // for i j

	return list;

} // fight_back
	
				
Sector.prototype.get_fight_back_beam = function (x, y) {
		var distance = this.get_distance( x, y );
		var beam = Math.floor( Math.random() * (200/distance));
		return beam;
			
} // get_fight_back_beam


Sector.prototype.get_distance = function (x, y) {
	var xd = this.sx - x ;
	var yd = this.sy - y ;
	var ret = xd * xd + yd* yd;
	return ret;
	
} // get_distance

	
Sector.prototype.fire_torpedo = function (course) {	
	
	// var code = 	this.C_NONE;
			var list = [];
				
		if (( course <1 ) || (  course > 8 ) ) {
			return  this.COURSE_ILLEGAL ;
		} // if	
 
	//	alert("fire_torpedo");
		

		
		var k = 0;
		d = this.course.get_delta(course);

		var x = this.sx;
		var y = this.sy;
		var xd =  d[0];
		var yd =  d[1];
		
		for(k=0;k < 8;k++) {
			x += xd ;
			y += yd ;
					
			if ( ( x >= 0 ) && ( x < 8 ) &&  ( y >= 0 ) && ( y < 8 ) ) {

					if ( this.s_arr[x][y] == this.C_NONE ) {
		//			// code = 	this.C_NONE;
					list.push( [this.C_NONE, x,y] );	
					continue;
						
				} else if ( this.s_arr[x][y] == this.C_KLINGON ) {
									list.push( [this.C_KLINGON, x,y] );
							// code = 	this.C_KLINGON;
	//	alert("KLINGON");					
					this.decrease_klingon();
		 this.s_arr[x][y] == this.C_NONE;
					break;
					
				} else if ( this.s_arr[x][y] == this.C_STARBASE ) {
						list.push( [this.C_STARBASE, x,y] );
		//			// alert("STARBASE");
											// code = 	this.C_STARBASE;
					this.decrease_starbase();
					this.s_arr[x][y] = this.C_NONE;	
					break;
					
				} else if ( this.s_arr[x][y] == this.C_STAR )	{
								list.push( [this.C_STAR, x,y] );
						// alert("STAR");
						// code = this.C_STAR ;		
					break;
					

				
	} // if s_arr
				
			} else {
					list.push( [this.TORPEDO_OUT, x,y] );
				// alert("OUT");
				// code = this.TORPEDO_OUT;
		  		break;
		  	
		 } // if x y		

	} // for k

	//return code;
	return list;
	
} // fire_torpedo
	
				
Sector.prototype.decrease_klingon = function () {

		this.num_klingon -= 1
		if ( this.num_klingon < 0 ) {
			this.num_klingon = 0;
			} // if			

} // decrese_klingon


Sector.prototype.decrease_starbase = function () {

		this.num_starbase -= 1
		if ( this.num_starbase < 0 ) {
			this.num_starbase = 0 ;
	} // if

} // decrese_starbase
					