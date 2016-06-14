package com.kevinkotowski.eighth;

import com.eighthlight.kkotowski.ttt.TttApi;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class _TttApiTest {
    @Test
    public void initApiGetDefaultStatus() throws Exception {
        TttApi tttApi = new TttApi();

        String status = tttApi.getStatus();
        assertTrue( status.contains("active:false") );
        assertTrue( status.contains("winner:NONE") );
        assertTrue( status.contains("page:menu") );
    }

    @Test
    public void startGameGetStatus() throws Exception {
        TttApi tttApi = new TttApi();
        tttApi.postStart();

        String status = tttApi.getStatus();
        assertTrue( status.contains("active:true") );
        assertTrue( status.contains("winner:NONE") );
        assertTrue( status.contains("page:board") );
    }

    @Test
    public void getBoard() throws Exception {
        TttApi tttApi = new TttApi();
        tttApi.postStart();

        String content = tttApi.getBoard();
        assertTrue( content.contains("turn:PLAYER1") );
        assertTrue( content.contains("turn_player_name:Homer") );
        assertTrue( content.contains("move_reco:X") );
        assertTrue( content.contains("board:0,0,0,0,0,0,0,0,0") );
    }

    @Test
    public void postMenuSelection() throws Exception {
        TttApi tttApi = new TttApi();
        tttApi.postStart();

        tttApi.postMove("8");
        assertTrue( tttApi.getBoard().contains("0,0,0,0,0,0,0,0,1") );
    }

    @Test
    public void getEndgame() throws Exception {
        TttApi tttApi = new TttApi();
        tttApi.postStart();
        tttApi.postMove("0");
        tttApi.postMove("4");
        tttApi.postMove("1");
        tttApi.postMove("6");
        tttApi.postMove("2");

        String status = tttApi.getStatus();
        assertTrue( status.contains("active:true") );
        assertTrue( status.contains("winner:PLAYER1") );
        assertTrue( status.contains("page:endgame") );

        String content = tttApi.getEndgame();
        assertTrue( content.contains("board:1,1,1,0,2,0,2,0,0") );
        assertTrue( status.contains("winner:PLAYER1") );
    }
}
