package com.sonhoai.sonho.tiktak;

import android.util.Log;

public class Negamax {

    public Record negamax(ChessBoard chessBoard,int currentDept, int maxDept, int alpha, int beta){
        Move bestMove = null;
        int bestScore;

        if(chessBoard.isGameOver() || currentDept == maxDept){
            Log.i("EVALUATE",String.valueOf(chessBoard.evaluate()));
            return  new Record(null,chessBoard.evaluate());
        }

        bestScore = Integer.MIN_VALUE;
        for (Move move:chessBoard.getMove()){
            ChessBoard newChess = new ChessBoard(chessBoard.getContext(),chessBoard.getBitmapWidth(),chessBoard.getBitmapHeight(),chessBoard.getColQty(),chessBoard.getRowQty());

            newChess.setBoard(chessBoard.getNewBoard());
            newChess.setPlayer(chessBoard.getPlayer());
            newChess.makeMove(move);

            Record record = negamax(
                    newChess,
                    currentDept++,
                    maxDept,
                    -beta,
                    -Math.max(alpha,bestScore)
            );

            int currentScore = - record.getScore();//do dang ktra o thang khac,

            if(currentScore > bestScore){
                bestScore = currentScore;
                bestMove = move;
                if(bestScore >= beta || currentScore <= alpha){
                    new Record(bestMove,bestScore);
                }
            }
        }
        return new Record(bestMove,bestScore);
    }
}
