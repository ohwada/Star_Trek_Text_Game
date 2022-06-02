/**
 * maincpp
 * 2020-05-01 K.OHWADA
 */


// g++  main.cpp game.cpp board.cpp object.cpp border.cpp input_num.cpp  -o  game  `pkg-config --cflags --libs ncurses`

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curses.h>

#include "game.hpp"
#include  "border.h"

void printTitle(int num_k);
void printWin();
void printLose();
void printQuit();
void printError(int h, int w, int height, int width);

const char KEY_ESC = 27;
 const char KEY_Y = 'y';
 const char KEY_N = 'n';

 const char TITLE1[] = "  STAR TREK GAME ";
const char TITLE2[] = "                      ,------*------ ";
const char TITLE3[] = "      ,-------------   '---  ------ ";
const char TITLE4[] = "       '-------- --'      / / ";
const char TITLE5[] = "       ,---' '-------/ /-- ";
const char TITLE6[] = "            '---------------- ";
 const char TITLE7[] = "You must destroy %d Klingons in %d Stardates"; 

 const char TITLE8[] = "Are You ready (y/n) "; 
 
 const char WIN[] = "YOUR WIN";
 
const char LOSE[] = "GAME OVER";

 const char TRY[] = "Tray Again (y/n) "; 

  const char ERR1[] = "Scrren Size is too small \n";
  const char ERR2[] = "scr %d x %d \n";
  const char ERR3[] = "require %d x %d \n";
  const char ANY[] = "Press Any Ket to exit";

int cx, cy;

/**
 * main
 */
int main(int argc, char *argv[])
{

    Game game;

    int h, w;

    int height  = BOTTOM+5;
    int width = RIGHT+30;

	initscr();
	getmaxyx(stdscr, h, w);

    cx = h/2;
    cy = w/2;

    curs_set(0); 
    noecho();            
    cbreak();            
    keypad(stdscr,TRUE);

    if ( (h <  height)||(w <  width) ){
        printError(h, w, height, width);
	    timeout(-1);
        getch();
        endwin();  
        fprintf( stderr, (char *)ERR1 );
        fprintf( stderr, (char *)ERR2, h, w);
        fprintf( stderr, (char *)ERR3, height, width);
        return EXIT_FAILURE;
    }

    int num_k = 3;
    printTitle(num_k);

	timeout(-1);
	int ch = getch();
    if( ch == KEY_N){
        endwin();  
          
        return EXIT_SUCCESS;
    }

while(1)
{
    int ret = game.playGame(num_k);
    if(ret == RET_WIN){
        printWin();
    }  else if (ret == RET_LOSE) {
       printLose();
     }  else if (ret == RET_QUIT) {
        printQuit();
    }

	timeout(-1);
	ch = getch();

    if(ch == KEY_Y){
        continue;
    } else if(ch == KEY_N){
        break;
    } else if(ch == KEY_ESC){
        break;
    }

 } // while

	timeout(-1);
	ch = getch();
	
    endwin();            
  return EXIT_SUCCESS;
}


/**
 * printTitle
 */
void printTitle(int num_k)
{
    int x = cx - 5;

    int y1 = cy - strlen(TITLE1)/2;

   int y2 = cy - strlen(TITLE2)/2;

    int y3 = cy - strlen(TITLE7)/2;
    
    int days = PER_DAYS *  num_k;

    clear();
    attron(A_BOLD);	
    move(x, y1);
    addstr(TITLE1);
attroff(A_BOLD);	
    move(x+2, y2);
    addstr(TITLE2);
    move(x+3, y2);
    addstr(TITLE3);
    move(x+4, y2);
    addstr(TITLE4);
    move(x+5, y2);
    addstr(TITLE5);
    move(x+6, y2);
    addstr(TITLE6);
    move(x+8, y3);
    printw(  (char *)TITLE7, num_k, days);
    move(x+10, y3);
    printw(TITLE8);
}


 /**
 * printWin
 */
void printWin()
{
    int x1 = BOTTOM / 2;

    int y1 = RIGHT / 2;

    int x2 = BOTTOM + 2;
   
    int y2 = cy - strlen(TRY)/2;

    clearMsgArea(); 
  
    attron(A_BOLD);	
    move(x1, y1);
    addstr(WIN);
    attroff(A_BOLD);	    
    move(x2, y2);
    addstr(TRY);
}


 /**
 * printLose
 */
void printLose()
{

    int x1 = BOTTOM / 2;

    int y1 = RIGHT / 2;

    int x2 = BOTTOM + 2;
   
    int y2 = cy - strlen(TRY)/2;

    clearMsgArea(); 

    attron(A_BOLD);	
    move(x1, y1);
    addstr(LOSE);
    attroff(A_BOLD);	    
    move(x2, y2);
    addstr(TRY);
}


 /**
 * printQuit
 */
void printQuit()
{

    int y = cy - strlen(TRY)/2;

    clear();
    move(cx, y);
    addstr(TRY);
}

 
 /**
 * printError
 */
void printError(int h, int w, int height, int width)
{
    int y = cy - strlen(ERR1)/2;

    move(cx, y);
    addstr(ERR1);
    move(cx+1, y);
    printw( (char *)ERR2, h, w );
    move(cx+2, y);
    printw( (char *)ERR3, height, width );
    move(cx+4, y);
    addstr(ANY);
}
