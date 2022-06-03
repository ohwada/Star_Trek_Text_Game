cpp How to Play
===============

You control the Enterprise and destroy the Klingons

Your battlefield is not the whole galaxy, <br/>
it is a certain Quadrant <br/>

### Win or Lose

Win: <br/>
You destroy all Klingons within the deadline of the mission <br/>

Lose: <br/>
if exceed the deadline of the mission. <br/>
or if the shield disappears and the Enterprise is destroyed, <br/>

### Screen

on the right, <br/>
the status report is displayed <br/>
below, <br/>
some  messages are displayed. <br/>

<img src="https://raw.githubusercontent.com/ohwada/Star_Trek_Text_Game/master/cpp/doc/screenshot_cpp_cmd_torpedo.png" width="300" />  <br/>
on the left <br/>
the Quadrant map is displayed <br/>
and Enterprise(E), Klingons(K), StarBase(B), Stars(*) are displayed.  <br/>

###  Equipment of the Enterprise

Energy : <br/>
consumed when moving Enterprise or launching Torpedoes <br/>
even if it is zero, You can move Enterprise <br/>
You can refill at the Base <br/>

Shields : <br/>
protect the Enterprise <br/>
decreased due to collision with Klingons or attack from Klingons <br/>
if it is zero, the Game is over <br/>
You can refill from Energy <br/>

Photon Torpedos ;
destroy Klingons <br/>
occasionally fail to launch <br/>
You can supply at the Base <br/>

TorpedoTubes : <br/>
launch Photon Torpedos <br/>
 occasionally breaks down <br/>
at that time, You can repair at the Base <br/>

### How to Control the Enterprise

There is a Move mode and a Command mode <br/>
In Command mode, the Arrow Key is invalid and cannot be moved <br/>

Arrow Key  [↑] [↓] [←] [→] : move the Enterprise <br/>

[SPACE] : go to Command mode <br/>
    You can use the following commands  <br/>

[t] : enter the angle and fire a Torpedo <br/>

[s] :enter the amount of Energy to refill Shields <br/>

[z] : exit Command Mode  <br/>
    or undock from the Base <br/>

###  move Enterprise

Behavior when colliding　<br/>

Klingons : <br/>
destroy Klingon if the Enterprise has 200 or more Shields　<br/>
damage Enterprise and reduces Shields <br/>

Base : <br/>
Move mode is canceled, <br/>
equipment is replenished <br/>

[z] : undock and go to Move mode <br/>

Stars and borders : <br/>
You can't move on. <br/>
plese detour, <br/>

### fire Torpedo

 Launch : <br/>
enter the angle and launch <br/>

the angle is counterclockwise <br/>
 to the right : 0 degrees <br/>
 to the top : 90 degrees  <br/>
 to the left : 180 degrees <br/>
 to the bottom : 270 degrees  <br/>

occasionally failed to launch <br/>'

Action: <br/>
proceed until go out of the area <br/>
or until hit Klingons, Base, or Stars <br/>
if hit, that will be destroyed <br/>

the trajectory is displayed as @ <br/>

<img src="https://raw.githubusercontent.com/ohwada/Star_Trek_Text_Game/master/cpp/doc/screenshot_cpp_trajectory.png" width="300" />  <br/>

 ###  Operation of Klingon

move randomly <br/>
even in Command mode <br/>

randomly counterattack from each Kilingon <br/>
display as below <br/>
hit 123 fromK(4, 5) <br/>

and educet Shields <br/>

### Feature of the the Base

You can supply energy and torpedoes. <br/>
 not supply shields <br/>
You can repair the TorpedoTubes. <br/>
The Base protect the Enterprise  and the Enterprise not damaged  <br/>
 You cannot fire torpedoes while docking at the Base  <br/>

### Decrease in StarDates

 move The Enterprise : 2 dates  <br/>
fire Torpedo : 1 date  <br/>
supply at the Base : 10 dates  <br/>

if 40 days or less <br/>
display the following message <br/>
Warning: there are no Dates left <br/>

### Reduction of Shields 

collide with the Klingon : random value about 150 points <br/> 

counterattack from Klingon : random value about 100 points<br/>

if 200 points or less <br/>
display the following message <br/>
Warning: Shields Dangerously Low <br/>

You can refill from Energy <br/>

### Consumption of Energy

 move The Enterprise : 10 points <br/>

fire a Torpedo :  1points <br/>

 refresh of  Status Report :  1points <br/>

refill Shield : decrease by that amount. <br/>

You can refill at the Base <br/>

