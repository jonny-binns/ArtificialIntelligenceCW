package com.AICoursework;

import java.util.*;

public class Caves {
    int number = 0;
    int xCoOrd = 0;
    int yCoOrd = 0;
    //stores a list of cave numbers a cave is connected to
    ArrayList<Integer> connectedCaves = new ArrayList<Integer>();
    //stores the min distance of the cave from the start cave
    long length = 0;
    //this is set when the shortest distance from the start node is found
    boolean isLengthPerm = false;

    //override toString to make parsing each cave object easier
    @Override
    public String toString()
    {
        return "Cave Number = " + number + " X = " + xCoOrd + " Y = " + yCoOrd + " Connected Caves = " + connectedCaves + " Length = " + length + " Is Length Perminant = " + isLengthPerm;
    }
}

