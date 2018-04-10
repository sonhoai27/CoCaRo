package com.sonhoai.sonho.tiktak;

public class Negamax {

    public Record negamax(ChessBoard chessBoard, int player,int currentDept, int maxDept, int alpha, int beta){
        Move bestMove = null;
        int bestScore;

        if(chessBoard.isGameOver() || currentDept == maxDept){
            return  new Record(null,chessBoard.evaluate());
        }

        bestScore = Integer.MIN_VALUE;
        for (Move move:chessBoard.getMove()){
            ChessBoard newChess = new ChessBoard(chessBoard.getContext(),chessBoard.getBitmapWidth(),chessBoard.getBitmapHeight(),chessBoard.getColQty(),chessBoard.getRowQty());
            newChess.setBoard(chessBoard.getNewBoard());
            newChess.setPlayer(chessBoard.getPlayer());

            newChess.makeMove(move);

            Record  record = negamax(
                    newChess,player,currentDept,maxDept,-beta,-Math.max(alpha,bestScore)
            );

            int currentScore = - record.getScore();

            if(currentScore > bestScore){
                bestScore = currentScore;
                bestMove = move;

                if(bestScore >= beta){
                    new Record(bestMove,bestScore);
                }
            }
        }
        return new Record(bestMove,bestScore);
    }
}
