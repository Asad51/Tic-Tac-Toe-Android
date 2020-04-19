package com.github.asad51.tictactoe;

public class Constant {
    public final static String TAG = "TIC_TAC_TOE";

    public final static class Shape {
        public final static int DEFAULT = -1;
        public final static int O = 0;
        public final static int X = 1;
    }

    public final static class Color{
        public final static String O = "#F2EBD3";
        public final static String X = "#545454";
    }

    public final static class Player {
        public final static int O = 0;
        public final static int X = 1;
    }

    public final static class Winner {
        public final static int O = 0;
        public final static int X = 1;
        public final static int DRAW = 2;
    }
}
