package com.sonhoai.sonho.tiktak;

/**
 * Created by sonho on 3/21/2018.
 */

public class Minimax {
    Move bestMove=null;//
    int bestScore;//giá trị điểm tốt nhất

    public Record minimaxRecode(ChessBoard chessBoard, int player,int currentDept, int maxDept) {
        if(chessBoard.isGameOver() || currentDept==maxDept) {
            //tham số đầu, hết bàn cờ thì move sẽ none//dánh giá trên cơ cở của con boss//player này luôn bằng =1//dại diện cho con boss
            return new Record(null,chessBoard.evaluate(player));
        }
        if(chessBoard.getPlayer()==player){
            bestScore=Integer.MIN_VALUE;
        }else {
            bestScore=Integer.MIN_VALUE;
        }
        //duyệt qua tất cả các nước đi có thể đi trên bàn cờ
        for(Move move:chessBoard.getMove()){
            //tạo mới 1 bàn cờ
            ChessBoard newChess = new ChessBoard(chessBoard.getContext(),chessBoard.getBitmapWidth(), chessBoard.getBitmapHeight(),chessBoard.getColQty(),chessBoard.getRowQty());
            newChess.setBoard(chessBoard.getNewBoard());
            newChess.setPlayer(chessBoard.getPlayer());
            //dánh dấu nước đi
            newChess.makeMove(move);
            Record record = minimaxRecode(newChess,player,currentDept++,maxDept);
            //currentscore nằm trong record
            //lượt boss
            if(record.getScore() > bestScore){
                bestScore = record.getScore();
                bestMove = move;
                //lượt người chơi
            }else if(record.getScore() < bestScore){
                bestScore = record.getScore();
                bestMove = move;
            }
        }
        //gọi dệ quy
        //player là ai đánh
        return new Record(bestMove,bestScore);
    }
}
