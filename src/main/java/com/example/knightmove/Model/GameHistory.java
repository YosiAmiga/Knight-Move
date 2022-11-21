package com.example.knightmove.Model;

public class GameHistory {
    private int gameID;
    private String nicknameForGameHistory;
    private int score;

    public GameHistory(int gameID, String nicknameForGameHistory, int score) {
        this.gameID = gameID;
        this.nicknameForGameHistory = nicknameForGameHistory;
        this.score = score;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getNicknameForGameHistory() {
        return nicknameForGameHistory;
    }

    public void setNicknameForGameHistory(String nicknameForGameHistory) {
        this.nicknameForGameHistory = nicknameForGameHistory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GameHistory{" +
                "gameID=" + gameID +
                ", nicknameForGameHistory='" + nicknameForGameHistory + '\'' +
                ", score=" + score +
                '}';
    }
}
