/**
 * quadrant.cpp
 * 2020-05-01 K.OHWADA
 */

#include <math.h>
#include <time.h>
#include <curses.h>
#include "quadrant.hpp"
#include  "border.h"

const char CHAR_E = 'E';
const char CHAR_B = 'B';
const char CHAR_K = 'K';
const char CHAR_STAR = '*';
const char CHAR_TORPEDO = '@';
const char CHAR_SPACE = ' ';


/**
 * constractor
 */
Quadrant::Quadrant() 
{
     dates = 0;
    energy = 0;
    shields = 0;
    torpedos = 0;
    isTubeAvailable = true;

    e.setChar(CHAR_E);
    b.setChar(CHAR_B); 
}


/**
 * destractor
 */
Quadrant::~Quadrant() 
{  
    // none 
}


/**
 * init
 */
void Quadrant::init() 
{
    isTubeAvailable = true;

// from the second time
// remove the previous element
    for(int  i=0; i<klingons.size(); i++) {
        eraseKilingon(i);
    }

    klingons.clear();

    for(int i=0; i<stars.size(); i++) {
        eraseStar(i);
    }

    stars.clear();

}  // init


/**
 * start
 */
void Quadrant::start(int num_k) 
{
     dates = PER_DATES *  num_k;
    energy = PER_ENERGY  *  num_k;
    shields = PER_SHIELDS  *  num_k;
    torpedos = PER_TORPEDOS *  num_k;

    int num_s = genRand(3, 6);

    init();
    initE();
    initB();
    initKlingons(num_k);
    initStars(num_s);

    clear();
    draw();

}


/**
 * draw
 */
void  Quadrant::draw()
{
    drawBorder();
    drawObject();  
    printReport();

}


/**
 * drawObject
 */
void  Quadrant::drawObject()
{

    e.draw();
    b.draw();   

  for(int i=0; i<klingons.size(); i++){
        klingons[i].draw();
    }

  for(int i=0; i<stars.size(); i++){
        stars[i].draw();
    }

}


/**
 * judgeWinLose
 */
int  Quadrant::judgeWinLose(bool isMsg)
{

    if( klingons.size() == 0){
        return RET_WIN;
    }

    if( ( shields == 0)||( dates == 0) ) {
        return RET_LOSE;
    }

    if(isMsg) {
        clearMsg4();
        if(shields < 200) {
            printMsg4( (char *)"Warning: Shields Dangerously Low");
        } 
        if( dates < 40) {
           printMsg4( (char *)"Warning: there are no Dates left" );
        }
    }    

    return 0;
}


/**
 * initE
 */
    void Quadrant::initE()
{
    int x, y;
    int cnt =0;

    while(1)
    {
        x = randX();
        y = randY();
        cnt ++;

    if(cnt > 10) {
        break;
    }
    if( nearBorder(x, y) ) {
        continue;
    } else{
        break;
    }

} // while

    if( ! nearBorder(x, y) ) {
        e.init(x, y);
    }
}


/**
 * initB
 */
void Quadrant::initB()
{
    int x,y;
    int cnt = 0;

    while(1)
{
   x = randX();
    y = randY();
    cnt++;

    if(cnt > 10) {
        break;
    }

    if( near_e_border(x, y) ) {
        continue;
    } else {
        break;
    } 

}// while

    if( ! near_e_border(x, y) ) {
        b.init(x, y);
    }
}

/**
 * initKlingons
 */
void  Quadrant::initKlingons(int num_k)
{

  int x,y;

for(int i=0; i<num_k; i++)
{

    int cnt = 0;
    while(1)
{
   x = randX();
    y = randY();
    cnt ++;

    if(cnt > 100) {
        break;
    }

    if( near_e_b_k_border(x, y) ) {
        continue;
    } else {
        break;
    }

} // while

    if( ! near_e_b_k_border(x, y) ) {
        Object k;
        k.init(x, y);
        k.setChar(CHAR_K);
        klingons.push_back(k);
    }

} // for

    return;

} // initKlingons


/**
 * matchKlingons
 */
bool Quadrant::matchKlingons(int x, int y)
{
    for(int i=0; i<klingons.size(); i++)
    {
        if( klingons[i].match(x, y) ){
            return true;
        }
    }// for

    return false;
} // matchKlingons


/**
 * nearKlingons
 */
bool Quadrant::nearKlingons(int x, int y)
{
    for(int i=0; i<klingons.size(); i++)
    {
        if( klingons[i].near(x, y) ){
            return true;
        }
    }// for

    return false;
} // nearKlingons



/**
 * initStars
 */
    void  Quadrant::initStars(int num_s)
{

  int x,y;
    
for(int i=0; i<num_s; i++)
{

    int cnt = 0;
    while(1)
{
   x = randX();
    y = randY();
    cnt ++;

    if(cnt > 100) {
        break;
    }
    if( near_e_b_k_s_border(x, y) ) {
        continue;
    } else {
        break;
    }

}// while

    if( ! near_e_b_k_s_border(x, y) ) {
        Object star;
        star.init(x, y);
        star.setChar(CHAR_STAR);
        stars.push_back(star);
    }

} // for

} // initStars


/**
 * near_e_border
 */
bool  Quadrant::near_e_border(int x, int y)
{
    if ( e.near(x, y) ) {
        return true;
    }
    if( nearBorder(x, y) ){
            return true;
    }

    return false;
} // near_e_border

/**
 * near_e_b_border
 */
bool  Quadrant::near_e_b_border(int x, int y)
{
    if ( e.near(x, y) ) {
        return true;
    }
     if ( b.near(x, y) ) {
        return true;
    }
    if( nearBorder(x, y) ){
            return true;
    }

    return false;
} // near_e_b_border


/**
 * near_e_b_k_border
 */
bool  Quadrant::near_e_b_k_border(int x, int y)
{
    if( near_e_b_border(x, y) ) {
        return true;
    }
    if ( nearKlingons(x, y) ) {
        return true;
    }

    return false;
}


/**
 * near_e_b_k_s_border
 */
bool  Quadrant::near_e_b_k_s_border(int x, int y)
{
    if( near_e_b_border(x, y) ) {
        return true;
    }
    if ( nearKlingons(x, y) ) {
        return true;
    }
    if( nearStars(x, y) ) {
        return true;
    }

    return false;
}


/**
 * matchStars
 */
bool Quadrant::matchStars(int x, int y)
{
    for(int i=0; i<stars.size(); i++)
    {
        if( stars[i].match(x, y) ){
            return true;
        }
    }// for

    return false;
} // matchStars


/**
 * nearStars
 */
bool Quadrant::nearStars(int x, int y)
{
    for(int i=0; i<stars.size(); i++)
    {
        if( stars[i].near(x, y) ){
            return true;
        }
    }// for

    return false;
} // nearStars


/**
 * printReport
 */
void Quadrant::printReport()
{
    clearReport();
    int y = RIGHT +2;
    move(1, y);
    printw("Dates: %d ",  dates);
    move(2, y);
    printw("Klingons: %d ", (int)klingons.size() );
    move(3, y);
    printw("Energy: %d ", energy);
    move(4, y);
    printw("Shields: %d ", shields);
    move(5, y);
    printw("Torpedos: %d ",   torpedos);
   move(6, y);
    addstr("Tubes: ");

    if( isTubeAvailable) {
        addstr("Green ");
    } else {
        addstr("RED   ");
    }

    minusEnergy(1);
}


/**
 *  clearReport
 */
void Quadrant::clearReport()
{
    int y = RIGHT +2;
    for(int x=1; x<7; x++) {
        clearLine(x, y, 50);
    }
}


/**
 * moveE
 */
int Quadrant::moveE(int ch)
 {

    int x = 0;
    int y = 0;

    int ex = e.getX();
    int ey = e.getY();

    bool flag_move = true;

switch(ch)
{
    case KEY_UP:
        x = ex - 1;
        y = ey;
        break;
    case KEY_DOWN:
       x = ex + 1;
        y = ey;
        break;
    case KEY_RIGHT:
        x = ex;
        y = ey + 1;
        break;
     case KEY_LEFT:
        x = ex;
        y = ey - 1;
        break;
    default:
        return RET_INVALID;
        break;
    } // swtch

    if( b.match(x,y)) {
        procDock();
        return RET_DOCKED;
    } 

    if( collideKingonsWithE(x, y) ){
        flag_move = false;
    }

    if ( matchStars(x, y) ) {
        flag_move =  false;
    }

      if (matchBorder(x,y)){
            flag_move =  false;
    }
  
    if(flag_move){
        minusEnergy(10);
        minusDays(2);
        e.moveTo(x, y);
        printReport();
    }

    return RET_MOVE;
}


/**
 *  collideKingonsWithE
 * return true: match and not destroy
 */
bool Quadrant::collideKingonsWithE(int x, int y)
{
    bool flag = false; 
    for(int i=0; i<klingons.size(); i++)
    {
        if( klingons[i].match(x, y) ) {
            bool flag_destroy = collideKingonWithE( i,  x, y);
            flag = ! flag_destroy;
        }
    } // for

        return flag;
} //  cllideKingons


/**
 *  collideKingonsWithT
 * return true: destroy false: not
 */
bool Quadrant::collideKingonsWithT(int x, int y)
{

    for(int i=0; i<klingons.size(); i++)
    {
        if( klingons[i].match(x, y) ) {
                eraseKilingon(i);
                addSpace(x, y);
                printMsg3( (char *)"destroy Kingon");
                printReport();
                return true;
        }
    } // for

        return false;
} //  cllideKingons


/**
 *  collideKingonByWithE
 * return true: destroy 
 */
bool Quadrant::collideKingonWithE(int num, int x, int y)
{
        bool flag = false;
        if(shields > 200) {
                eraseKilingon(num);
                addSpace(x, y);
               stateWithE();
                 flag = true;
        } else {
                stateFail();
        }
    
            return flag;         
} // collideKingonByBodyE


/**
 * :eraseKilingon
 */
void Quadrant::eraseKilingon(int num) 
{
  klingons.erase( klingons.begin() + num );
}


/**
 *  collideStarsWithT
 * return true: destroy false: not
 */
bool Quadrant::collideStarsWithT(int x, int y)
{

    for(int i=0; i<stars.size(); i++)
    {
        if( stars[i].match(x, y) ) {
            eraseStar(i);
            addSpace(x, y);
            printMsg3( (char *)"destroy Star" );
            return true;
        }
    } // for

        return false;
} //  collisionStars


/**
 *  eraseStar
 */
void Quadrant::eraseStar(int num)
{
    stars.erase( stars.begin() + num );
}


/**
 * :procDock
 */
void Quadrant::procDock()
{
        minusDays(10);

        energy += PER_ENERGY;
        torpedos +=  PER_TORPEDOS;
        isTubeAvailable = true;

        printReport();
        printMsg1((char *)"Docked in Base");
        printMsg2((char *)"[z] undock");
        return;
}



/**
 * randomMoveK
 */
void Quadrant::randomMoveK()
{

int i;
for(i = 0; i<klingons.size(); i++ ) 
{

    Object k = klingons[i]
;    int kx = k.getX();
    int ky = k.getY();
    int kid = k.getId();

    int x = kx;
    int y = ky;

    int cnt = 0;

while(1)
{
    cnt ++;
    if(cnt > 10){
        break;
    }

    int rnd = genRand(1, 8);

switch(rnd)
{
    case 1:
        // right
        x = kx;
        y = ky + 1;
        break;
    case 2:
        // up right
        x = kx -1;
        y = ky + 1;
        break;
    case 3:
        // up
        x = kx -1;
        y = ky;
        break;
    case 4:
       // up left
        x = kx -1;
        y = ky - 1;
        break;
    case 5:
       // left
        x = kx;
        y = ky - 1;
        break;
    case 6:
       // down left
        x = kx + 1;
        y = ky - 1;
        break;
    case 7:
       // down
        x = kx + 1;
        y = ky;
        break;
    case 8:
       // down right
        x = kx + 1;
        y = ky + 1;
        break;

} // swtch

    if( near_for_move_k(kid, x, y) ) {
           continue;
    }

} // while


        if( ! near_for_move_k(kid, x, y) ) {

            k.moveTo(x, y);
            eraseKilingon(i); //  remove
            klingons.push_back(k); // add
        }
    
} // for


} // randomMoveK


/**
 *  near_for_move_k
 */
bool  Quadrant::near_for_move_k(int id, int x, int y)
{
    if( near_e_b_border(x, y) ) {
        return true;
    }
    if( nearStars(x, y) ) {
        return true;
    }
    if( near_not_myself_klingons( id, x, y) ) {
        return true;
    }

    return false;
}


/**
 *  near_not_myself_klingons
 */
bool  Quadrant::near_not_myself_klingons(int id, int x, int y)
{
    for(int i = 0; i<klingons.size(); i++ ) {

        Object k = klingons[i];

// if myself
// be sure to be near
// so check id and exclude
        if( ( ! k.matchId(id) )&& k.near(x, y) ) {
            return true;
        }

    } // for

return false;
}


/**
 * counterAttackK
 */
void Quadrant::counterAttackK( bool isMsg )
{
    for(int i = 0; i<klingons.size(); i++ ) {

        Object k = klingons[i]
;       int kx = k.getX();
        int ky = k.getY();
    
        if( isOnceInTimes(20) ){
            counterAttackPos(kx, ky, isMsg );
        }

    }// for

return;
}


/**
 *  counterAttackPos
 */
void Quadrant::counterAttackPos(int x, int y, bool isMsg)
{
    int damege = genRand(20, 80);
    minusShield(damege);

    if( isMsg ) {
        clearMsg1();
        move(MSG_X1, 2);
        printw("hit %d from K(%d, %d)", damege, x, y);
        printReport();
    }


}


/**
 *  moveTorpedo
 */
int Quadrant::moveTorpedo(int deg)
{

    if( (deg<0)||(deg>360) ){
            move(MSG_X3, 2);
            printw( (char *)"invalid value : %d", deg);
            return  RET_INVALID;
    }

    minusTorpedo(1);

    if( isOnceInTimes(10) ){
        return RET_T_FAILD;
    }

// a torpedo is fired once a day
    minusDays(1);

// break down
        if( isOnceInTimes(20) ){
            isTubeAvailable = false;
        }

    // degree -> radian
    double rad = deg * (M_PI/180) ;

    int x, y, dx, dy;
    int cnt = 0;

    while(1)
{
        cnt++;
        if(cnt>100) {
            break;
        }

        dx =  (int)( cnt * sin(rad) ) ;
        dy = (int)( cnt *  cos(rad) );
        x = e.getX() - dx;
        y = e.getY() + dy;

    if ( collideStarsWithT(x, y) ){
        break;
    }

    if( b.match(x, y) ) {
        b.destroy();
        addSpace(x, y);
        printMsg1( (char *)"destroy Base" );
        break;
    }

    if ( collideKingonsWithT(x, y) ){
        break;
    }

   
    if( matchBorder(x,y) ) {
        printMsg3( (char *)"out of renge");
        break;
    }

        if( ! e.match(x, y) ) {
            move(x,y);
            addch(CHAR_TORPEDO);
        }

        mSleep(500); // 0.5 sec

} // while

    printReport();

    return RET_T_LAUNCHED ;
}


/**
 *  stateoyWithE
 */
void Quadrant::stateWithE()
{
    int damege =genRand(100, 180);
    minusShield(damege);
    printMsg1( (char *)"destroy Klingon" );

    move(MSG_X2, 2);
    printw("damege: %d", damege);

    printReport();
}


/**
 *  stateFail
 */
void Quadrant::stateFail()
{
        int damege  = genRand(50, 120);
        minusShield(damege);
        printMsg1( (char *)"Not enough shields");

        move(MSG_X2, 2);
        printw("damege: %d", damege);

        printReport();
    return;
}


/**
 *  addSpace
 */
void Quadrant::addSpace(int x, int y)
{
    move(x, y);
    addch(CHAR_SPACE);
}


/**
 *  minusEnergy
 */
void Quadrant::minusEnergy(int num)
{
    energy -= num;
    if(energy < 0) {
        energy = 0;
    }
    return;
}


/**
 *  minusShield
 */
void Quadrant::minusShield(int num)
{
    shields -= num;
    if(shields < 0) {
        shields = 0;
    }
    return;
}


/**
 *  minusTorpedo
 */
void Quadrant::minusTorpedo(int num)
{
    torpedos -= num;
    if(torpedos < 0) {
        torpedos = 0;
    }
    return;
}


/**
 *  minusDays
 */
void Quadrant::minusDays(int num)
{
     dates -= num;
    if( dates < 0) {
         dates = 0;
    }
    return;
}


/**
 * doShield
 */
void Quadrant::doShield(int num)
{

    if(energy > num){
                shields += num;
                energy -= num;
                printReport();
        } else {
                printMsg3( (char *)"insufficient Energy");
        }

        return;
}


/**
 * procTorpedo
 */
int Quadrant::checkTorpedo()
{

     if( ! isTubeAvailable ) {
        return RET_T_BROKEN;
    }

    if (torpedos == 0) {
        return RET_T_EMPTY;
    }
 
    return RET_T_NORMAL;

}


