Game Rules of Star Trek Text Game
===============

Star Trek Text Game <br/>
A tribute to the classic game <br/>
https://en.wikipedia.org/wiki/Star_Trek_(text_game)


### Policy to decide Game Rules

I will take advantage of the atmosphere of the original BASIC version,  <br/>

I would like to make the user operation simple and easy to develop. <br/>

The difficulty level of the game is such that you can win if you do it several times. <br/>


### How to Play <br />

You command the Enterprise and destroy the Klingon warships <br/>


## Win or Lose <br />

Win: <br/>

the player destroy all Klingon warships within the deadline of the mission <br/>

the following is displayed. <br/>

> THE LAST KLINGON BATTLE CRUISER IN THE GALAXY HAS BEEN DESTROYED  <br/>
THE FEDERATION HAS BEEN SAVED !!!  <br/>

Lose: <br/>

if exceed the deadline of the mission. <br/>
or if Shields disappears and the Enterprise is destroyed, <br/>
in the simplified version <br/>
it does not judge the player to lose. <br/>


### Configuration of Game Board  <br />

the Galaxy is divided into 64 (8x8) Quadrants. <br/>

there is only one the Enterprise in the Galaxy <br/>

Klingon warships are scattered throughout the  Galaxy. <br/>

one Quadrant is divided into 64 (8x8) sectors.  <br/>

one sector have nothing, <br/>
or have the Enterprise, Klingon warship, the StarBase, or star <br/>

it display in characters. <br/>

E: the Enterprise <br/>
K: Klingon warship <br />
B: StarBase <br />
*: star <br/>


###  Equipment of the Enterprise 


#### (1) Long Range Sensor 

it scan the  Galaxy <br/>
it can not scan all Quadrants at once <br/>
it can scan 8 Quadrants adjacent the Quadrant with the Enterprise. </br>
 up, down, left, right,  diagonally up left,  diagonally up right, diagonally down left, and diagonally down right <br/>

 it display the result of scanning and record the result  in Cumulative Galactic Record <br/>


#### (2) Short Range Sensor  

it scan within the Quadrant <br/>
it display positions of the Enterprise, Klingon warships, StarBase, and stars. in the Quadrant <br/>


#### (3) Warp Engine 

move the Enterprise between Quadrants or within Quadrant  <br/>


####(4) Impulse Engine 

move the Enterprise within Quadrant <br/>


#### (5) Phasers 

fire energy beams and attack Klingon warships. <br/>
unlike photon torpedoes, it emits energy beams in all directions <br/>

the beam is proportional to charged　energy, <br/>
it attenuates according to the distance from the Enterprise to Klingon warship <br/>

Klingon warship has shields <br/>
when the beam hits, decrease shields <br/>
if shields disappears,  it will destroy Klingon warship <br/>

randomly destroy Klingon warships. <br/>

if you are lucky, you can destroy all Klingon warships at once. <br/>


#### (6) Photon Torpedo 

attack Klingon warship. </br>
if hit, it destroys Klingon warship. <br/>


#### (7) Torpedo Tubes :

 launch Photon Torpedos <br/>


####(8) Shields 

it protect the Enterprise from Klingon warships attacking <br/>


#### (8) Damage Report 

it display whether the device is normal or dameged <br/>


#### (9) Computer 

it has the following features


#### (9-1) Status Report  

it can display the number of remaining Klingon warships, in the galaxy  and others <br/>


#### (9-2) Cumulative Galactic Record 

You can summarize the situation of the range scaned by the Long Range Sensor so far.  <br/>
it is not the latest information, <br/>
but the accumulated informations when the Long Renge Sensor scaned. <br/>
this is useful if the Long Renge Sensor breaks down. <br/>

#### (9-3) Photon Torpedo Data 

it display the coordinates of the Klingon warship and the course of the Torpedo . <br/>
You can attack the Klingon warship even if You cannot use the Short Renge Sensor. <br/>


#### (10) Enegy 

it is consumed for the Warp Engines or Shields <br/>


### Failure and Recovery of Equipments <br />

Equipments will randomly break down or recover as the game progresses. <br/>

even if the Short Range Sensor fails, it will recover immediately. <br/>
if the Short Range Sensor breaks down, Game will become a stalemate. <br/>
so the crews will do their best to repair it. (^O^  <br/>


### Feature of the the StarBase

when the Enterprise dock in the StarBase <br/>

display as below <br/>

> docked in STARBASE  <br/>
> SHIELDS DROPPED FOR DOCKING PURPOSES <br/>


You can supply Energy and Photon Torpedoes. <br/>
You can repair the broken equipments. <br/>

it protect the Enterprise and Not damaged by Klingon warships attacking <br/>

the Enterprise drop Shields ,become zero <br/>


### Start of Game <br/>

it place Klingon warships and StarBases in the galaxy. 
it place up to ⒌ Klingon warships and up to one StarBase in one  Quadrant. <br/>

in the simplified version, <br/>
it is not all 8x8 quadrants, but 3x3 Quadrants centered on the Enterprise. <br/>

after the initial settings are completed, 
the following will be displayed. <br/>

> YOU MUST DESTROY x KLINGONS IN xx STARDATES WITH xx STARBASES <br/>


## Progress of Game <br />

the situation changes when the player chices a command. <br/>
it feels like an old RPG. <br/>

if there are Klingon warhips around the Enterprise  <br/>
display a warning is as below <br/>

> COMBAT AREA      CONDITION RED <br/>

and if shields are 200 or less, <br/>
display a warning is as below <br/>

> SHIELDS DANGEROUSLY LOW <br/>


## Command List 

 
#### (1) the Long Range Sensor

the status of Quadrants are displayed 3 digits number. <br/>

the first digit is the number of Klingon warships, the second digit is the number of StarBases, and the third digit is the number of stars. <br/>

for example, "205" means 2 Klingons, no StarBase, 5 stars. <br/>

display as below <br/>

> LONG RENGE SENSOR <br/>
>   0   1   2   3   4   5   6   7  <br/>
> 0 *** *** *** *** *** *** *** ***  <br/>
> 1 *** *** *** *** *** *** *** *** <br/>
> 2 *** *** *** *** *** *** *** ***  <br/>
> 3 *** *** *** 000 213 000 *** ***  <br/>
> 4 *** *** *** 013 E010 000 *** ***  <br/>
> 5 *** *** *** 102 013 000 *** ***  <br/>
> 6 *** *** *** *** *** *** *** ***  <br/>
> 7 *** *** *** *** *** *** *** ***  <br/>
 > at Quadrant 4 , 4  <br/> 


#### (2) the Short Range Sensor

display as below <br/>

> SHORT RENGE SENSOR  <br/>
>  0 1 2 3 4 5 6 7 <br/>
> 0  <br/> 
> 1   E   K B  <br/>
> 2  <br/>
> 3  <br/>
> 4 <br/>
> 5   *  <br/>
> 6           *   <br/>
> 7 <br/>
>  <br/>
> STARDATE 312  <br/>
> CONDITION RED  <br/>
> QUADRANT 4 , 3  <br/>
> SECTOR 1 , 1  <br/>
> ENERGY 2800  <br/>
> PHOTON TORPEDOES 10  <br/>
> SHIELDS 200  <br/>
> COMPUTER ACTIVE  <br/>


#### (3) fire Phasers

You enter the amount of Energy. <br/>

display as below <br/>

> PHASERS LOCKED ON TARGET  <br/>
> ENERGY AVAILABLE= 2800  <br/>
> ENTER NUMBER OF UNITS TO FIRE  <br/>
>   <br/>
> energy>  <br/>


in the simplified version, <br/>
the amount of energy is 100. <br/>

when You fire a phasers , Klingon warships counterattack  <br/>

Phasers are prone to failure and You cannot use continuously. <br/>
if You fire Phasers in rapid succession, <br/>
the game will be easier, <br/>
so limit it. <br/>


#### (4) fire Photon Torpedo 

You enter the course of Torpedo. <br/>

display as below <br/>

>  === SELECT COURSE ===  <br/>
>  <br/>
 > 4 3 2  <br/>
 > 5 E 1  <br/>
 > 6 7 8  <br/>
>
> course(1-8) >  <br/>


the Torpedo goes straight until it hits a Klingon warship, a StarBase , a star, or goes out of range. <br/>
if it is a Klingon warship or a  StarBase, destroy it. <br/>
when it is a star, it cannot be destroyed. <br/>

if it is out of order or when Torpedos runs out, it do not launch. <br/>

display the trajectory of Torpedo as below <br/>

> TORPEDO TRACK:  <br/>
> 1 , 2  <br/>
> 1 , 3  <br/>
> *** KLINGON DESTROYED ***  <br/>


when You fire a  Torpedo,  Klingon warships counterattack  <br/>


#### (5) refill Shields 

You enter the amount of energy. <br/>

display as below  <br/>

> YOU HAVE  2790 UNITS OF ENERGY  <br/>
> YOUR SHIELDS HAVE 161 UNITS LEFT  <br/>
> ENTER NUMBER OF UNITS FOR SHIELD  <br/>
>   <br/>
> nergy>  <br/>


in the simplified version, <br/>
the amount of energy is 200. <br/>


#### (6) Damege Report 

display as below <br/>

> DAMAGE CONTROL REPORT  <br/>
> L.R. SENSOR : Normal  <br/>
> S.R. SENSOR : Normal  <br/>
> PHASER CNTRL : Normal  <br/>
> TORPEDO TUBES : Normal  <br/>
> SHIELD CNTRL : Normal  <br/>
> DAMAGE CNTRL : Normal  <br/>
> WARP ENGINE : Normal  <br/>
> IMPULSE ENGINE : Normal  <br/>
> COMPUTER : Normal  <br/>


#### (7) Warp Engine 

You enter the course, distance and Energy. <br/>

display as below <br/>

 > === SELECT COURSE ===  <br/>
>  <br/>
 > 4 3 2  <br/>
 > 5 E 1  <br/>
 > 6 7 8  <br/>
>
> course(1-8) >  <br/>


in the simplified version, <br/>
the warp distance is 1 block, the warp energy is 10, <br/>
and it reduce deadline by one day <br/>

 when the Enterprise go out of the Quadran, <br/>
the Enterprise will randomly move to some Quadran. <br/>


#### (8) Impulse Engine 

You enter the course and distance. <br/>

display as below <br/>

 > === SELECT COURSE ===  <br/>
>  <br/>
 > 4 3 2  <br/>
 > 5 E 1  <br/>
 > 6 7 8  <br/>
>
> course(1-8) > <br/>


in the simplified version, <br/>
the moving distance is one section. <br/>

if there is a Klingon warship, a StarBase, or a star at the destination,  <br/>
 the Enterprise will stop there. <br/>

if it's a Klingon warship,  <br/>
it destroys the Klingon warship and it damages the Enterprise. <br/>

if it is a StarBase,  <br/>
the Enterprise will dock at StarBase. <br/>

if it's a star,  <br/>
the Enterprise lands on the star, but nothing happens. <br/>

 once outside the Quadrant, <br/>
the Enterprise will move to the adjacent Quadrant. <br/>

 even if the Impulse Engine breaks down, <br/>
the Enterprise can move one section. <br/>


 #### (9) Status Report 

display as below <br/>

> STATUS REPORT  <br/>
> NUMBER OF KLINGONS LEFT = 6  <br/>
> NUMBER OF STARDATES LEFT = 312  <br/>
> NUMBER OF STARBASES LEFT = 7  <br/>


  #### (10) Photon Torpedo Data

display as below <br/>

> SHIP'S & TARGET'S COORDINATES ARE  <br/>
> x= 1 , y= 6 : course= 0  <br/>
> x= 5 , y= 6 : course= 0  <br/>


 #### (11)  Cumulative Galactic Record

display as below <br/>

> COMPUTER RECORD OF GALAXY   <br/>
>   0   1   2   3   4   5   6   7  <br/>
> 0 *** *** *** *** *** *** *** ***  <br/>
> 1 *** *** *** *** *** *** *** ***  <br/>
> 2 *** *** *** 000 000 000 *** ***  <br/>
> 3 *** *** *** 000 E213 000 *** ***  <br/>
> 4 *** *** *** 013 010 000 *** ***  <br/>
> 5 *** *** *** 102 013 000 *** ***  <br/>
> 6 *** *** *** *** *** *** *** ***  <br/>
> 7 *** *** *** *** *** *** *** ***  <br/>
 >at Quadrant 3 , 4  <br/>


### enter Course <br />

in the original BASIC version,  <br/>
the player enter the angle  <br/>

the angle is counterclockwise <br/>
 to the right : 0 degrees <br/>
 to the top : 90 degrees  <br/>
 to the left : 180 degrees <br/>
 to the bottom : 270 degrees  <br/>


in the simplified version, it is as follows  <br/>

the player chice 8 directions from 1-8 <br/>

1 : to Left <br />
2 : to Upper Left <br />
3 : to Up <br />
4 : to Upper Right <br />
5 : to Right <br />
6 :  to Down Right <br />
7 : to Down <br />
8 :  to Down Left <br />

if it display the above as guidance, it will be difficult to see, <br/>
so it display simply as shown below. <br/>

4 3 2 <br />
5 E 1 <br />
6 7 8 <br />


## CounterAttack from Klingon warships  <br/>

if there is a Klingon warship in the Quadran, it becomes a battle field 
and all Klingon ships attack randomly <br/>

the weapon is an Energy beam. <br/>
if it hit in the Enterprise ,  </br>
it will display as follows. </br>

xx UNIT HIT ON ENTERPRISE AT SECTOR xx,xx


the Enterprise is protected by Shields. <br/>

,if Energy beam hit in thhe Enterprise
it reduces shields. <br/>

if shields disappears, the game is over <br/>

