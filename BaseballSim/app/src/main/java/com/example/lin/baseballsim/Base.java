package com.example.lin.baseballsim;

/**
 * Created by Lin on 6/22/2017.
 */

public class Base {
    final int baseNumber;
    public boolean runnerUp;
    public String baseName;
    public int runnerNumber;

    public Base(int baseNum) {
        runnerUp = false;
        baseNumber = baseNum;
        runnerNumber = 0;

        if (baseNumber == 3) baseName = "Home";
        else if (baseNumber == 0) baseName = "First";
        else if (baseNumber == 1) baseName = "Second";
        else if (baseNumber == 2) baseName = "Third";

    }

}
