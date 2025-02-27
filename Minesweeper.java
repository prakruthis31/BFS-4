class Solution {
    int[][] dir = new int[][] { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    int m;
    int n;

    public char[][] updateBoard(char[][] board, int[] click) {
        this.m = board.length;
        this.n = board[0].length;

        Queue<int[]> q = new LinkedList<>();
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int count = calcMines(board, x, y);
            if (count == 0) {
                
                for (int[] d : dir) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    if (nx >= 0 && ny >= 0 && nx < m && ny < n && board[nx][ny] == 'E') {
                        q.add(new int[] { nx, ny });
                        board[nx][ny] = 'B';
                    }
                }
            } else {
                board[x][y] = (char) (count + '0');
            }

        }

        return board;
    }

    private int calcMines(char[][] board, int x, int y) {
        int count = 0;
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && board[nx][ny] == 'M') {
                count++;
            }
        }

        return count;

    }
}