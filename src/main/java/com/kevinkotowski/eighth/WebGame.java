package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class WebGame {
    TttApi gameApi;

    public WebGame(TttApi gameApi) {
        this.gameApi = gameApi;
    }

    public void start() {
        this.gameApi.postStart();
    }

    public void move(String move) {
        int position = Integer.parseInt(move) - 1;
        this.gameApi.postMove(Integer.toString(position));
    }

    public void quit() {
        this.gameApi.postExit();
    }

    public void restart() {
        this.gameApi.postStart();
    }

    public boolean isActive() {
        String active = WebScanByLine.get( this.gameApi.getStatus(), "active");
        return Boolean.parseBoolean(active);
    }

    public boolean isSquareAvailable(String square) {
        Integer position = Integer.parseInt(square) - 1;
        String[] board = this.getBoard();
        return (board[position].equals("0"));
    }

    public boolean isEndgame() {
        boolean endgame = false;
        if (!this.isActive()) {
            String winner = WebScanByLine.get(
                    this.gameApi.getStatus(), "winner");
            endgame = !winner.equals("NONE");
        }
        return endgame;
    }

    public String[] getBoard() {
        String board =
                WebScanByLine.get( this.gameApi.getBoard(),"board" );
        return board.split(",");
    }

    public String getSquare(String square) {
        Integer position = Integer.parseInt(square) - 1;
        String value = Integer.toString(position + 1);
        String[] board = this.getBoard();
        if (board[position].equals("1")) value = "X";
        if (board[position].equals("2")) value = "O";
        return value;
    }

    public String getMoveReco() {
        String name = WebScanByLine.get(
                this.gameApi.getBoard(), "move_reco");
        return name;
    }

    public String getTurnPlayerName() {
        String name = WebScanByLine.get(
                this.gameApi.getBoard(), "turn_player_name");
        return name;
    }

    public String getWinnerName() {
        String name = WebScanByLine.get(
                this.gameApi.getEndgame(), "winner_player_name");
        return name;
    }
}
