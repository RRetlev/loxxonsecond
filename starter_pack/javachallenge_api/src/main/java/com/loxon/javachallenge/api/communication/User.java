package com.loxon.javachallenge.api.communication;

import lombok.Data;


/**
 * Represents a user that can join games and play by issuing commands to send their armies from planet to planet.
 */
@Data
public class User implements Comparable<User> {

    private Long    userID;
    private String  userName;
    private Long    gameID;
    private boolean connected;
    private Integer highScore;
    private Integer lastScore;

    public User() {
    }

    public User( Long userID, String userName, Long gameID ) {
        this.userID = userID;
        this.userName = userName;
        this.gameID = gameID;
    }

    /**
     * Disconnects the user from the game.
     */
    public void disconnect() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID( final Long userID ) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( final String userName ) {
        this.userName = userName;
    }

    public Long getGameID() {
        return gameID;
    }

    public void setGameID( final Long gameID ) {
        this.gameID = gameID;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected( final boolean connected ) {
        this.connected = connected;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore( final Integer highScore ) {
        this.highScore = highScore;
    }

    public Integer getLastScore() {
        return lastScore;
    }

    public void setLastScore( final Integer lastScore ) {
        this.lastScore = lastScore;
    }

    @Override
    public int compareTo( User other ) {
        return userID.compareTo(other.userID);
    }
}
