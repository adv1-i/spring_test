package org.alishev.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MusicPlayer {

    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;
    private List<Music> genres;
    private Music music;

    public MusicPlayer(List<Music> genres) {
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public List<Music> getGenres() {
        return genres;
    }

    public String playMusic() {
        Random random = new Random();
        return "Playing " + genres.get(random.nextInt(genres.size())).getSong() +
                "\n" + "Громкость: " + this.volume;
    }
}
