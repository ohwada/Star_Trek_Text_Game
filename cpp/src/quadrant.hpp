/**#pragma once

 * quadrant.hpp
 * 2020-05-01 K.OHWADA
 */

#ifndef  BOARD_H
#define BOARD_H

#include <vector>
 #include "object.hpp"

// number per klingon
const int PER_DATES =  25;
const int PER_ENERGY = 140;
const int PER_SHIELDS = 80;
const int PER_TORPEDOS = 7;

// return code
const char RET_WIN = 1;
const char RET_LOSE = 2;
const char RET_QUIT= 3;

// moveE
const int RET_MOVE = 4;
const int RET_DOCKED = 5;
const char RET_INVALID = 6;

// checkTorpedo
const char RET_T_NORMAL = 7;
const char RET_T_EMPTY = 8;
const char RET_T_BROKEN = 9;

// moveTorpedo
const char RET_T_LAUNCHED = 10;
const char RET_T_FAILD = 11;

/**
 * class Quadrant
 */
class  Quadrant
{
public:
     Quadrant();
    ~ Quadrant();
    void start(int num_k);
    void draw();
    int judgeWinLose(bool isMsg);
    int moveE(int ch);
    void randomMoveK();
    void counterAttackK( bool isMsg );
    int moveTorpedo(int deg);
    void procDock();
    void doShield(int num);
    int checkTorpedo();

private:
    void init();
    void initE();
    void initB();
    void initKlingons(int num_k);
    void initStars(int num_s);
    bool matchKlingons(int x, int y);
    bool nearKlingons(int x, int y);
    bool matchStars(int x, int y);
    bool nearStars(int x, int y);
    bool collideKingonsWithE(int x, int y);
    bool collideKingonsWithT(int x, int y);
    bool collideKingonWithE(int num, int x, int y);
    void eraseKilingon(int num);
    bool collideStarsWithT(int x, int y);
    void eraseStar(int num);
    bool near_e_border(int x, int y);
    bool near_e_b_border(int x, int y);
    bool near_e_b_k_border(int x, int y);
    bool near_e_b_k_s_border(int x, int y);
    bool near_for_move_k(int id, int x, int y);
    bool near_not_myself_klingons(int id, int x, int y);
    void drawObject();
    void printReport();
    void clearReport();
    void stateWithE();
    void stateFail();
    void counterAttackPos(int x, int y, bool isMsg );
    void minusEnergy(int num);
    void minusShield(int num);
    void minusTorpedo(int num);
    void minusDays(int num);
    void addSpace(int x, int y);

    Object e;
    Object b;
   std::vector<Object> klingons; 
    std::vector<Object> stars; 
    int energy;
    int shields;
    int torpedos;
    int  dates;
    bool isTubeAvailable;

};

#endif 
