
/* TC O(n*n) SC (n*n)
things to note:
Change 2D matrix into 1D array
use flag but not even/odd condition for the traversal order/direction which changes for each row
*/
class Solution {
    public int snakesAndLadders(int[][] board) {
        Queue<Integer> q = new LinkedList<>();
        int n = board.length;
        int r = n - 1; // start from bottom left
        int c = 0;
        int[] arr = new int[n*n]; // build 1D array of matrix

        int Idx = 0;
        boolean flag = true; // to determine traversal direction
        while (Idx < arr.length) {
            if (board[r][c] == -1) {
                arr[Idx] = -1;
            } else {
                arr[Idx] = board[r][c] - 1; // destination square offset by 1
            }
            Idx++;
            if (flag) {
                c++;
                if (c == n) {
                    r--;
                    c--;
                    flag = false;
                }
            } else {
                c--;
                if (c == -1) {
                    r--;
                    c++;
                    flag = true;
                }
            }
        }

        // bfs
        int moves = 0; // dice roll
        q.add(0); // add 1st square
        arr[0] = -2;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur >= n * n - 1)
                    return moves;

                for (int j = 1; j < 7; j++) { // 6 possible destinations from curr
                    int newIdx = cur + j;
                    if (newIdx == n * n)
                        break;
                    if (arr[newIdx] != -2) { // if not visited
                        if (arr[newIdx] == -1) { // add new square to queue
                            q.add(newIdx);
                        } else { // add destination square to queue
                            q.add(arr[newIdx]);
                        }
                    }
                    arr[newIdx] = -2; // mark square as visited

                }

            }
            moves++;
        }

        return -1;
    }
}