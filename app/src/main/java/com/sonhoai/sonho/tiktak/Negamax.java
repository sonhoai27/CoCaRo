package com.sonhoai.sonho.tiktak;

import com.sonhoai.sonho.tiktak.model.Move;
import com.sonhoai.sonho.tiktak.model.Record;

public class Negamax {

    public Record negamax(ChessBoard chessBoard, int currentDept, int maxDept, int alpha, int beta){
        Move bestMove = null;
        int bestScore;

        if(chessBoard.isGameOver() || currentDept >= maxDept){
            return  new Record(null,chessBoard.evaluate());
        }

        bestScore = Integer.MIN_VALUE;
        for (Move move:chessBoard.getMove()){
            ChessBoard newChess = new ChessBoard(
                    chessBoard.getContext(),
                    chessBoard.getBitmapWidth(),
                    chessBoard.getBitmapHeight(),
                    chessBoard.getColQty(),
                    chessBoard.getRowQty()
            );

            newChess.setBoard(chessBoard.getNewBoard());
            newChess.setPlayer(chessBoard.getPlayer());

            int a, b;
            if (alpha == Integer.MIN_VALUE) {
                b = Integer.MAX_VALUE;
            } else if (alpha == Integer.MAX_VALUE){
                b = Integer.MIN_VALUE;
            } else {
                b = -alpha;
            }

            if (beta == Integer.MIN_VALUE) {
                a = Integer.MAX_VALUE;
            } else if (beta == Integer.MAX_VALUE) {
                a = Integer.MIN_VALUE;
            } else {
                a = -beta;
            }
            newChess.makeMove(move);
            Record record = negamax(
                    newChess,
                    currentDept + 1,
                    maxDept,
                    a,
                    b
            );

            int currentScore = - record.getScore();//do dang ktra o thang khac,

            if(currentScore > bestScore){
                bestScore = currentScore;
                bestMove = move;
            }
            alpha = Math.max(alpha, currentScore);

            if (alpha >= beta) {
                return new Record(bestMove,bestScore);
            }
        }
        return new Record(bestMove,bestScore);
    }
}
