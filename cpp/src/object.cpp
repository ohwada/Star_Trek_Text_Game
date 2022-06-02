/**
 * objrct.cpp
 * 2020-05-01 K.OHWADA
 */

#include <curses.h>
 #include "object.hpp"

const char SPACE = ' ';

/**
 * constractor
 */
Object::Object() {
    id = 0;
    x  = 0;
    y = 0;
    alive = true;
    ch = SPACE;
}

/**
 * destractor
 */
Object::~Object() {
    // none
}

/**
 * init
 */
void Object::init(int x1, int y1) {
        x  = x1;
        y = y1;
}


/**
 * setId
 */
void Object::setId(int id1)
{
    id = id1;
}


/**
 * setChar
 */
void Object::setChar(char ch1)
{
    ch = ch1;
}


/**
 * getX
 */
int Object::getX()
{
    return x;
}


/**
 * getY
 */
int Object::getY()
{
    return y;
}


/**
 * get Alive
 */
bool Object::getAlive()
{
    return alive;
}


/**
 * getId
 */
int Object::getId()
{
    return id;
}


/**
 * draw
 */
void Object::draw()
{

    if (alive) {
        move(x, y);
        addch(ch);
    }
}


/**
 * matchId
 */
bool  Object::matchId(int id1)
{
    if ( id1 == id ){
        return true;
    }
    return false;
}


/**
 * match
 */
bool  Object::match(int x1, int y1)
{
    if ( (x1 == x) &&(y1 == y) ){
        return true;
    }
    return false;
}


/**
 * near
 */
bool Object::near(int x1, int y1)
{

    if ( (x1 == x) &&(y1 == y) ){
        return true;
    }
    if ( ( (x1+1) == x) && (y1 == y) ) {
        // down
        return true;
    }
    if ( ( ( x1 -1) == x) &&(y1 == y) ) {
        // up
        return true;
    }
    if ( (x1 == x) &&( (y1+1) == y) ){
        // right
        return true;
    }
    if ( (x1 == x) &&( (y1-1) == y) ){
        // left
        return true;
    }
      if ( ( (x1+1) == x) &&( (y1+1) == y) ){
        // right up
        return true;
        } else if ( ( (x1-1) == x) &&( (y1+1) == y) ){
        // right down
        return true;
        }
        if ( ( (x1+1) == x) &&( (y1-1) == y) ){
         // left up
            return true;
        }
        if ( ( (x1-1) == x) &&( (y1-1) == y) ){
            // left down
            return true;
        } 

        return false;
    }


/**
 * destroy
 */
void Object::destroy()
{
    move(x, y);
    addch(SPACE);
    x =0;
    y = 0;
    alive = false;
}


/**
 * moveTo
 */
int Object::moveTo(int x1, int y1)
{
    if( match(x1, y1) ){
        return 1;
    }

    move(x, y);
    addch(SPACE);
    x = x1;
    y = y1;
    draw();
    return 0;
}


/**
 * printInfo
 */
void Object::printInfo()
{
    printw("x= %d, y= %d, alive= %d", x, y, alive);
}
