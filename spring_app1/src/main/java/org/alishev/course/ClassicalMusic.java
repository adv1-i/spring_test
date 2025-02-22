package org.alishev.course;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassicalMusic implements Music {
    private List<String> songClassicalList = List.of("Времена года", "Весна", "Лето");

    public void setSongList(List<String> songList) {
        this.songClassicalList = songList;
    }

    @Override
    public String getSong() {
        Random random = new Random();
        int randomIndex = random.nextInt(songClassicalList.size());
        return songClassicalList.get(randomIndex);
    }

    @PostConstruct
    public void init() {
        System.out.println("Do init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Do my destroy");
    }

    @Override
    public String toString() {
        return "Classical Music";
    }
}
