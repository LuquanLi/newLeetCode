class Solution79 {
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) return false;

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if(wordSearchHelper(i, j, board, word)) return true;
            }
        }

        return false;
    }

    private boolean wordSearchHelper(int i, int j, char[][] board, String word) {
        if (word.isEmpty()) return true; // reach the end
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false; // out of range

        char temp = board[i][j];
        if (temp != word.charAt(0)) return false;
        board[i][j] = '.'; // mark

        boolean result = wordSearchHelper(i + 1, j, board, word.substring(1))
                || wordSearchHelper(i - 1, j, board, word.substring(1))
                || wordSearchHelper(i, j + 1, board, word.substring(1))
                || wordSearchHelper(i, j - 1, board, word.substring(1));

        board[i][j] = temp;

        return result;
    }
}
