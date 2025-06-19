import java.util.Scanner;

public class MainClass {

    private static int n;

    private static char[][] board;

    private static int moves = 0;

    private static char currentPlayer = 'X';

    private static boolean matchWon = false;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the board(nxn): ");
        n = in.nextInt();
        board = new char[n][n];
        initializeBoard(n);
        printBoard(n);

        while(moves<n*n && !matchWon){
            System.out.println("Please enter the rows and columns : ");
            int row = in.nextInt();
            int col = in.nextInt();
            if(isValidMove(row, col)){
                if(currentPlayer == 'X'){
                    board[row][col] = 'X';
                    moves++;
                } else {
                    board[row][col] = 'O';
                    moves++;
                }

                if(checkWin(row, col)){
                    matchWon = true;
                    System.out.println("Match Won by : "+ currentPlayer);
                    return;
                }

            } else {
                System.out.println("Invalid Move ! Retry ...");
                continue;
            }
            currentPlayer = currentPlayer == 'X'? 'O' : 'X';
            printBoard(n);
        }
        if(!matchWon){
            System.out.println("Match Drawn");
            printBoard(n);
        }
        in.close();

    }

    private static boolean checkDia(){
        boolean dia1 = true;
        boolean dia2 = true;
        for(int i = 0; i < n; i++){
            if(board[i][i] != currentPlayer){
                dia1 = false;
                break;
            }
        }
        for(int i = 0; i < n; i++){
            if(board[i][n - i - 1] != currentPlayer){
                dia2 = false;
                break;
            }
        }
        return dia1 || dia2;
    }

    private static boolean checkRow(int row){
        for(int i = 0; i < n; i++){
            if(board[row][i] != currentPlayer){
                return false;
            }
        }
        return true;
    }

    private static boolean checkCol(int col){
        for(int i = 0; i < n; i++){
            if(board[i][col] != currentPlayer){
                return false;
            }
        }
        return true;
    }

    private static boolean checkWin(int row, int col){
        return checkRow(row) || checkCol(col) || checkDia();
    }
    private static boolean isValidMove(int row, int col){
        if(row >= 0 && row < n && col >= 0 && col < n && board[row][col] == '-'){
            return true;
        }
        return false;
    }

    private static void initializeBoard(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
