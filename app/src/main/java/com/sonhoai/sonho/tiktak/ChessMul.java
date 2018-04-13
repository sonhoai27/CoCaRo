package com.sonhoai.sonho.tiktak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChessMul {
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private int[][] board;//cac buoc đã đi -1 là chưa đi, 0 la nguoi choi, 1 la may
    private int player;//nguoi choi nào
    private Context context;
    private int bitmapWidth, bitmapHeight, colQty,rowQty;
    private List<Line> lines;
    private int need = 3;//so luong win
    private Move tempMove;
    private int winner = -1;

    //gọi nhiều lần
    private Bitmap playerA, playerB;

    public ChessMul(Context context, int bitmapWidth, int bitmapHeight, int colQty, int rowQty) {
        this.context = context;
        this.bitmapWidth = bitmapWidth;
        this.bitmapHeight = bitmapHeight;
        this.colQty = colQty;
        this.rowQty = rowQty;
    }

    //lam cac thao tac khoi tao, reset lại giá trị của các phương thức
    public void init(){
        tempMove = null;
        lines = new ArrayList<>();
        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        board = new int[rowQty][colQty];
        for(int i = 0; i<rowQty; i++){
            for(int j = 0; j < colQty;j++){
                board[i][j] = -1;//-1 là chưa đi
            }
        }

        player = 0;
        paint.setStrokeWidth(2);
        int celWidth = bitmapWidth/colQty;
        int celHeight = bitmapHeight/rowQty;
        for(int i = 0; i <= colQty; i++){
            lines.add(new Line(celWidth*i, 0, celWidth*i, bitmapHeight));
        }
        for(int i = 0; i <= rowQty; i++){
            lines.add(new Line(0, i*celHeight, bitmapWidth, i*celHeight));
        }
    }

    public Bitmap drawBoard(){
        for(int i = 0; i < lines.size(); i++){
            canvas.drawLine(
                    lines.get(i).getX1(),
                    lines.get(i).getY1(),
                    lines.get(i).getX2(),
                    lines.get(i).getY2(),
                    paint
            );
        }
        playerA = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_player_a);
        playerB = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_player_b);

        return bitmap;
    }


    public boolean onTouch(final View view, MotionEvent motionEvent){
        final int colIndex = (int) (motionEvent.getX() / (view.getWidth() / colQty));
        final int rowIndex = (int) (motionEvent.getY() / (view.getHeight() / rowQty));

        if (winner == 0 || winner == 1) {
            return true;
        }
        if(board[rowIndex][colIndex] != -1){
            return true;//co nguoi di roi
        }

        board[rowIndex][colIndex] = player;
        onDrawBoard(colIndex,rowIndex,view);
        makeMove(new Move(rowIndex,colIndex));
        view.invalidate();

        if(isGameOver()){
            if (winner == 1) {
                Toast.makeText(context, "Ban thua roi", Toast.LENGTH_LONG).show();
            } else if (winner == 0) {
                Toast.makeText(context, "Ban thang roi", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Ban hoa", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return true;
    }
    public void onDrawBoard(int colIndex, int rowIndex, View view){
        int cellWidth = view.getWidth()/colQty;
        int cellHeight = view.getHeight()/rowQty;
        int padding = 50;
        if(player == 0){
            canvas.drawBitmap(
                    playerA,
                    new Rect(0,0,playerA.getWidth(), playerA.getHeight()),
                    new Rect(colIndex*cellWidth+padding,rowIndex*cellHeight+padding,(colIndex+1)*cellWidth -padding, (rowIndex+1)*cellHeight -padding),
                    paint);
            // player = 1;
        }else {
            canvas.drawBitmap(
                    playerB,
                    new Rect(0,0,playerB.getWidth(), playerB.getHeight()),
                    new Rect(colIndex*cellWidth,rowIndex*cellHeight,(colIndex+1)*cellWidth, (rowIndex+1)*cellHeight),
                    paint);
            //  player = 0;
        }
    }
    //ktra coi có hết game chưa
    public boolean isGameOver(){
        if(checkWin()){
            return true;
        }
        int count = 0;
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (board[i][j] == -1) count++;
            }
        }
        if (count == 0){
            winner = -1;
            return true;//trò chơi kết thúc
        }
        //chưa thắng hoặc còn vị trí để đi=>game chưa kết thúc
        return false;
    }
    private boolean checkWin() {
//        if(board[0][0]==player && board[0][1]==player && board[0][2]==player)
//            return true;
//
//        if(board[1][0]==player && board[1][1]==player && board[1][2]==player)
//            return true;
//
//        if(board[2][0]==player && board[2][1]==player && board[2][2]==player)
//            return true;
//
//        if(board[0][0]==player && board[1][0]==player && board[2][0]==player)
//            return true;
//
//        if(board[0][1]==player && board[1][1]==player && board[2][1]==player)
//            return true;
//
//        if(board[0][2]==player && board[1][2]==player && board[2][2]==player)
//            return true;
//
//        if(board[0][0]==player && board[1][1]==player && board[2][2]==player)
//            return true;
//
//        if(board[0][2]==player && board[1][1]==player && board[2][0]==player)
//            return true;
        if(tempMove == null) return false;
        if (checkWindiagonalLeftBottom(tempMove.getRowIndex(),tempMove.getColIndex())
                || checkWindiagonalRightBottom(tempMove.getRowIndex(),tempMove.getColIndex())
                || checkWinHorizontal(tempMove.getRowIndex())
                || checkWinVerical(tempMove.getColIndex())
                ){
            return true;
        }
        return false;

    }
    //duyệt qua từng bước đi, để có một bàn cờ mới
    public List<Move> getMove() {
        //tạo mới 1 danh sách, duyệt qua từng vị trí, nếu -1 còn vị trí đi
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (board[i][j] == -1) moves.add(new Move(i, j));//có thể đi dc
            }
        }
        return moves;
    }

    //ghi nhận nước đi.gán nước đi đó là player nào.
    public void makeMove(Move move) {
        tempMove = move;
        board[move.getRowIndex()][move.getColIndex()] = player;
        player = (player + 1) % 2;//hoan đổi người chơi, 1 qua 0, hoặc 0 qua 1

    }

    //dánh giá bàn cở, trở về điểm tương ứng v player, boss thắng là 1, boss thua là -1, hòa là 0
    public int evaluate() {
        if(winner == -1){
            return 0;
        }else if(winner == player){
            return 1;
        }else {
            return -1;
        }
    }
    public int[][] getNewBoard(){
        int[][] newBoard = new int[rowQty][colQty];
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    public int getCurrentDept(){
        int count = 0;
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (board[i][j] == -1) count++;
            }
        }
        return count;
    }


    public boolean checkWinHorizontal(int row){
        int dem = 0;
        for (int i = 1; i < rowQty; i++) {
            if (board[row][i] != board[row][i-1]) {
                dem = 0;
            }else if(board[row][i] != -1){
                dem++;
            }
            if (dem == need) {
                winner = board[row][i];
                return true;
            }
        }

        return false;
    }
    private boolean checkWinVerical(int col){
        int dem = 0;
        for (int i = 1; i < rowQty; i++) {
            if (board[i][col] != board[i-1][col]) {
                dem = 0;
            }else if(board[i][col] != -1){
                dem++;
            }
            if (dem == need) {
                winner = board[i][col];
                return true;
            }
        }

        return false;
    }
    private boolean checkWindiagonalLeftBottom(int row, int col){
//        int a = 0, b = 0,count  = 0, i = 1;
//        if(row > col){
//            a = row - col;
//            b = 0;
//        }else {
//            a = 0;
//            b = col - row;
//        }
//        while (a +i < col && b+i < rowQty){
//            if(a +i < rowQty && b+i < rowQty){
//                if(board[a+i][b+i] != board[a][b]){
//                    count = 0;
//                }else if(board[a+i][b+i] != -1){
//                    count++;
//                }
//                if(count == need) {
//                    winner = board[a+i][i+1];
//                    return true;
//                }
//            }
//            i++;
//        }
//        return false;
        int rowStart, colStart;
        int i = 0;
        int count = 0;

        if (row > col) {
            rowStart = row - col;
            colStart = 0;
        } else {
            rowStart = 0;
            colStart = col - row;
        }

        while (rowStart + i + 1 < colQty && colStart + i + 1 < rowQty) {
            if (board[rowStart + i][colStart + i] == board[rowStart + i + 1][colStart + i + 1] && board[rowStart + i][colStart + i] != -1) {
                count++;

                if (count == need) {
                    winner = board[rowStart + i][colStart + i];
                    return true;
                }
            } else {
                count = 0;
            }
            i++;
        }

        return false;
    }
    private boolean checkWindiagonalRightBottom(int row, int col){
        int rowStart, colStart;
        int i = 0;
        int count = 0;

        if (row + col < colQty - 1) {
            colStart = row + col;
            rowStart = 0;
        } else {
            colStart = colQty - 1;
            rowStart = col + row - (colQty - 1);
        }

        while (colStart - i - 1 >= 0 && rowStart + i + 1 < colQty) {
            if (board[rowStart + i][colStart - i] == board[rowStart + i + 1][colStart - i - 1] && board[rowStart + i][colStart - i] != -1) {
                count++;

                if (count == need) {
                    winner = board[rowStart + i][colStart - i];
                    return true;
                }
            } else {
                count = 0;
            }

            i++;
        }
        return false;
    }
}