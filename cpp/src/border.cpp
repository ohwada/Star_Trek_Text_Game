/**
 * border.cpp
 * 2020-05-01 K.OHWADA
 */

#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <curses.h>

#include  "border.h"

const char CHAR_TOP = '-';
const char CHAR_LEFT = '|';
const char SPACE = ' ';

/**
 * matchBorder
 */
bool matchBorder(int x, int y)
{
    if( (x == TOP)||(x == BOTTOM)||( y == LEFT)||(y == RIGHT) ){
        return true;
    }
    return false;
}


/**
 * nearBorder
 */
bool nearBorder(int x, int y)
{
if( matchBorder(x, y) ) {
        return true;
    } else if( (x == TOP+1)||(x == BOTTOM-1)||( y == LEFT+1)||(y == RIGHT-1) ){
        return true;
    }
    return false;
}


/**
 * drawBorder
 */
int drawBorder()
{
    int x, y;

    x = TOP;
    for(y=LEFT+1; y<RIGHT; y++){
        move(x,y);
        addch(CHAR_TOP );
    }

    x = BOTTOM;
    for(y=LEFT+1; y<RIGHT; y++){
        move(x,y);
        addch(CHAR_TOP);
    }

    y = RIGHT;
    for(x=TOP+1; x< BOTTOM; x++){
        move(x,y);
        addch(CHAR_LEFT);
    }

    y = LEFT;
    for(x=TOP+1; x< BOTTOM; x++){
        move(x,y);
        addch(CHAR_LEFT);
    }

    return 0;
}


/**
 * printMsg1
 */
void printMsg1( char *msg)
{
    clearMsg1();

    int x = BOTTOM +2;
    move(x, 2);
    addstr(msg);
    return;
}


/**
 * printMsg2
 */
void printMsg2( char *msg)
{
    clearMsg2();

    int x = BOTTOM +3;
    move(x, 2);
    addstr(msg);
    return;
}


/**
 * printMsg3
 */
void printMsg3( char *msg)
{
    clearMsg3();

    int x = BOTTOM +4;
   move(x, 2);
    addstr(msg);
    return;
}

/**
 * printMsg4
 */
void printMsg4( char *msg)
{
    clearMsg4();

    int x = BOTTOM +5; 
    move(x, 2);
    addstr(msg);
    return;
}

/**
 * clearMsg1
 */
void clearMsg1()
{
    clearLine( (BOTTOM +2), 0, 50);
}


/**
 * clearMsg2
 */
void clearMsg2()
{
    clearLine( (BOTTOM +3), 0, 50);
}


/**
 * clearMsg3
 */
void clearMsg3()
{
    clearLine( (BOTTOM +4), 0, 50);
}


/**
 * clearMsg4
 */
void clearMsg4()
{
    clearLine( (BOTTOM +5), 0, 100);
}


/**
 * clearMsgArea
 */
void clearMsgArea()
{
    clearMsg1();
    clearMsg2();
    clearMsg3();
    clearMsg4();
}

/**
 * clearLine
 */
void clearLine(int x, int y, int max)
{
    for(int i=y; i<max; i++){
        move(x, i);
        addch(SPACE);
    }
}


/**
 * randX
 */
int randX()
{
    return genRand(TOP +1, BOTTOM -1);
}


/**
 * randY
 */
int randY()
{
    return genRand(LEFT +1, RIGHT -1);
}


/**
 * genRand
 */
int genRand(int min, int max)
{

	static int flag;
	
	if (flag == 0) {
		srand((unsigned int)time(NULL));
        rand();
		flag = 1;
	}

    double d = (double)rand() /( (double)RAND_MAX + 1.0);
    int ret = (int)((max - min + 1)*d) + min;

    return ret;
}


 /**
 * isOnceInTimes
 */
bool isOnceInTimes(int max)
{
    int rnd = genRand(1, max);
    if(rnd == 2){
        return true;
    }
    return false;
}


/**
 * mSleep
 */
void mSleep(int milliseconds)
{
  struct timespec ts;
  ts.tv_sec = 0;
  ts.tv_nsec = milliseconds * 1000 * 1000;
  nanosleep(&ts, NULL);
}

