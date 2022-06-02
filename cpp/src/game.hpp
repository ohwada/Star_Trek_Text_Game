#pragma once
/**
 * game.hpp
 * 2020-05-01 K.OHWADA
 */

#ifndef  GAME_H
#define GAME_H


 #include "quadrant.hpp"


/**
 * class Game
 */
class  Game
{
public:
     Game();
    ~ Game();
    int playGame(int num_k);

private:
    Quadrant quadrant;
    void init();
    int judgeWinLose();
    void moveE(int ch);
    void randomMoveK();
    void moveTorpedo(int num);
    void counterAtack();
    void procComand();
    void printComandMode();
    void procShield();
    void procTorpedo();
    void procZ();
    void reDraw();
    void inpuNum(int ch);
   bool isMove;
    bool isCommand;
    bool isCmdShield;
    bool isCmdTorpedo;
    bool isTorpedoDraw;
    bool isDocked;

};

#endif 
