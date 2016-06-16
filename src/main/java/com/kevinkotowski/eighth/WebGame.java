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

    public boolean isActive() {
        String active = WebScanByLine.get( this.gameApi.getStatus(), "active");
        return Boolean.parseBoolean(active);
    }

    public boolean isAvailable(String square) {
        Integer position = Integer.parseInt(square);
        String[] board = this.getBoard();
        return (board[position].equals("0"));
    }

    public boolean isEndgame() {
        String endgame = WebScanByLine.get( this.gameApi.getStatus(), "endgame");
        return Boolean.parseBoolean(endgame);
    }

    public String[] getBoard() {
        String board =
                WebScanByLine.get( this.gameApi.getBoard(),"board" );
        return board.split(",");
    }

    public String getSquare(String square) {
        Integer position = Integer.parseInt(square);
        String value = Integer.toString(position + 1);
        String[] board = this.getBoard();
        if (board[position].equals("1")) value = "X";
        if (board[position].equals("2")) value = "O";
        return value;
    }

    public String getTurnPlayerName() {
        String name = WebScanByLine.get(
                this.gameApi.getBoard(), "turn_player_name");
        return name;

    }
}
