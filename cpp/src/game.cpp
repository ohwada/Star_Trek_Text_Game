/**
 * game.cpp
 * 2020-05-01 K.OHWADA
 */

#include <curses.h>
#include "game.hpp"
 #include "quadrant.hpp"
#include  "border.h"
 #include "input_num.h"


const char KEY_ESC = 27;
const char KEY_SPACE = ' ';
const char KEY_T = 't';
const char KEY_S = 's';
const char KEY_Z = 'z';

/**
 * constractor
 */
Game::Game() 
{
    // none
}


/**
 * destractor
 */
Game::~Game() {
    // none
}


/**
 * init
 */
void Game::init() 
{
    isMove = true;
    isCommand = false;
    isCmdShield = false;
     isCmdTorpedo = false;
    isTorpedoDraw = false;
    isDocked = false;
}


/**
 * playGame
 */
int Game::playGame(int num_k) 
{
    init();

    quadrant.start(num_k);

	timeout(-1);
	// timeout(1);

int ch;

while(1)
{
	ch = getch();     

if(ch == KEY_ESC){
    return RET_QUIT;
}

 
switch(ch)
{
    case KEY_UP:
        moveE(ch);
        break;
    case KEY_DOWN:
        moveE(ch);
        break;
    case KEY_RIGHT:
        moveE(ch);
        break;
    case KEY_LEFT:
        moveE(ch);
        break;
    case KEY_SPACE:
        if( ! isCommand ){
            procComand();
        }
        break;
    case KEY_S:
        if(isCommand){
            procShield();
        }
        break;
        case KEY_T :
        if(isCommand){
            procTorpedo();
        }
        break;
        case KEY_Z:
        procZ();
        break;
} // switch

    randomMoveK();
    counterAtack();

// put before judgeWinLose
// fire Torpedoe and maybe destroy Klingon
    inpuNum(ch);

   int ret = judgeWinLose();
    if(ret != 0){
        return ret;
    }

    mSleep(500); // 0.5 sec
} // while

    return 0;
}


/**
 * judgeWinLose
 */
 int Game::judgeWinLose()
{

// whether to display a warning message
// the reason to need,  same as counterAtack()
    bool isMsg = ( ! isCmdShield && ! isCmdTorpedo );

    return quadrant.judgeWinLose(isMsg);
}


/**
 * moveE
 */
 void Game::moveE(int ch)
{

    if( ! isMove ){
        return;
    }

    int ret = quadrant.moveE(ch);
    if( ret == RET_DOCKED ){
        isDocked = true;
         isMove = false;
    }

}


/**
 * randomMoveK
 */
 void Game::randomMoveK()
{
    if( isMove ){
        quadrant.randomMoveK();
    }
}


/**
 * randomMoveK
 */
 void Game::counterAtack()
{

// when waiting to enter numerical value
// if a message is displayed 
// the cursor position will change.
// so deter this
    bool isMsg = ( ! isCmdShield && ! isCmdTorpedo );
  
// protect Enterprise during the dock
// Do not receive counterattack
       if( ! isDocked){
         quadrant.counterAttackK( isMsg );
        }
}


/**
 * procComand
 */
void Game::procComand()
{
   isCommand = true;
    isMove = false;
    isTorpedoDraw = false;
    printComandMode();
    printMsg2( (char *)"[s] Shied");
    printMsg3( (char *)"[t] Torpedo");
    printMsg4( (char *)"[z] release mode");

}


 /**
 * printComandMode
 */
void Game::printComandMode()
{
    clearMsgArea();
    printMsg1( (char *)"Command Mode");
}


/**
 * :procShield
 */
    void Game::procShield()
{
    if( isTorpedoDraw ) {
        reDraw();
        isTorpedoDraw = false;
    }else{
        clearMsg2();
    } 

    isCmdShield = true;
    printComandMode();
    printMsg2( (char *)"energy> ");

    return;
}


/**
 * procTorpedo
 */
    void Game::procTorpedo()
{

      quadrant.randomMoveK();

    if( isTorpedoDraw ) {
        reDraw();
        isTorpedoDraw = false;
    }else{
        clearMsg2();
    } 

    int ret = quadrant.checkTorpedo();
 
            printComandMode();

    if ( ret == RET_T_NORMAL ) {
            isCmdTorpedo = true;
            printMsg2( (char *)"angle>");
    } else if ( ret == RET_T_EMPTY ) {
            printMsg2( (char *)"no Torpedo");
    } else if ( ret == RET_T_BROKEN) {
            printMsg2( (char *)"Launcher is out of order");
            printMsg3( (char *)"Repair at the Base");
    } 

    return;
}

/**
 *  inpuNum
 */
    void Game::inpuNum(int ch)
{
      if( ! isCommand  ) {
        return;
    }

    int num = input_num(ch);

    if(num == -1 ) {
        return;
    }

       if( isCmdShield  ) {
           isCmdShield  = false;
          quadrant.doShield(num);

       } else if(isCmdTorpedo ){
            moveTorpedo(num);
    }
    
    return;
}


/**
 *  moveTorpedo
 */
void Game::moveTorpedo(int num)
{
    isCmdTorpedo = false;  

    int ret = quadrant.moveTorpedo(num);
    if(ret ==  RET_T_LAUNCHED ) {                
        isTorpedoDraw = true;
    } else if(ret ==  RET_T_FAILD ) {
        printMsg3( (char *)"failed to launch");
        } else if(ret ==  RET_INVALID ) {
            printMsg3( (char *)"invalid value");
        }

    return;
}


/**
 *  procZ
 */
void Game::procZ()
{
    if(isCommand) {
            isCommand = false;
            isMove = true;
            if( isTorpedoDraw ) {
                reDraw();
                isTorpedoDraw = false;
            }    
    } else if(isDocked) {
            isDocked = false;
            isMove= true;
            clearMsg1();
            clearMsg2();
    }

    return;
}


/**
 *  reDraw
 */
void Game::reDraw()
{
    clear();
    quadrant.draw();
    isTorpedoDraw = false; 
}
       