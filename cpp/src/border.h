#pragma once
/**
 * border.h
 * 2020-05-01 K.OHWADA
 */

#ifndef  BORDER_H
#define BORDER_H

#define TOP 0
#define BOTTOM 17
#define LEFT 0
#define RIGHT 25

bool matchBorder(int x, int y);
bool nearBorder(int x, int y);
int drawBorder();
void printMsg1( char *msg);
void printMsg2( char *msg);
void printMsg3( char *msg);
void printMsg4( char *msg);
void clearMsg1();
void clearMsg2();
void clearMsg3();
void clearMsg4();
void clearMsgArea();
void clearLine(int x, int y, int max);
int randX();
int randY();
int genRand(int min, int max);
bool isOnceInTimes(int max);
void mSleep(int milliseconds);

#endif 