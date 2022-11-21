package com.example.knightmove.Model;

import java.util.Objects;


public class Player {

    private String nickname;

    public Player(String nickname) {
        this.nickname = nickname;

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Player player = (Player) object;
        return java.util.Objects.equals(nickname, player.nickname);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), nickname);
    }

    public String toString() {
        return "Player nickname = " + nickname;
    }

}
