package org.alishev.course;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        MusicPlayer musicPlayer = annotationConfigApplicationContext.getBean("musicPlayer", MusicPlayer.class);

        System.out.println(musicPlayer.playMusic());

        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        annotationConfigApplicationContext.close();

    }
}
