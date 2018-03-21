package com.sonhoai.sonho.tiktak;

/**
 * Created by sonho on 3/21/2018.
 */

public class Record {
    private Move move;
    private int score;

    public Record(Move move, int score) {
        this.move = move;
        this.score = score;
    }
    public Record(){
    }
    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
