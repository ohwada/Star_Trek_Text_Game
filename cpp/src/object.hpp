#pragma once
/**
 * objrct.hpp
 * 2020-05-01 K.OHWADA
 */


#ifndef  OBJECT_H
#define OBJECT_H

/**
 * class Object
 */
class Object
{
public:
    Object();
    ~Object();
    void init(int x1, int y1);
    void setId(int id1);
    void setChar(char ch1);
    int getX();
    int getY();
    bool getAlive();
    int getId();
    bool  match(int x1, int y1);
    bool near(int x1, int y1);
    bool  matchId(int id1);
    void destroy();
    void draw();
    int moveTo(int x, int y);
    void printInfo();

private:
    int id;
     int x;
    int y;
    bool alive;
    char ch;
};

#endif 
