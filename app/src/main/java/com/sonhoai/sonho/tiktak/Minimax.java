package com.sonhoai.sonho.tiktak;

/**
 * Created by sonho on 3/21/2018.
 */

public class Minimax {
    Move bestMove=null;
    int bestScore;

    public Record Minimax(int currentDept, int maxDept, ChessBoard chessBoard, int player) {
        if(chessBoard.isGameOver() || currentDept==maxDept) {
            return new Record(null,chessBoard.evaluate(player));
        }
        if(chessBoard.getPlayer()==player){
            bestScore=Integer.MIN_VALUE;
        }else {
            bestScore=Integer.MIN_VALUE;
        }

        for(Move move:chessBoard.getMove()){
            ChessBoard chessBoard1 = new ChessBoard(chessBoard.getContext(),chessBoard.getBitmapWidth(), chessBoard.getBitmapHeight(),chessBoard.getColQty(),chessBoard.getRowQty());
            chessBoard.makeMove(move);
        }
        return null;
    }
}
