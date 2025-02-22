package org.alishev.course;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

public class RockMusic implements Music {
    private List<String> songRockList = List.of("Bohemian Rhapsody", "Stairway to Heaven", "Smell Like Тeen Spirit");


    public void setSongRockList(List<String> songRockList) {
        this.songRockList = songRockList;
    }
    @Override
    public String getSong() {
        Random random = new Random();
        int randomIndex = random.nextInt(songRockList.size());
        return songRockList.get(randomIndex);
    }

    @Override
    public String toString() {
        return "Rock music";
    }
}
