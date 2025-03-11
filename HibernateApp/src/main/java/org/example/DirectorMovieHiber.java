package org.example;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DirectorMovieHiber {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // С помощью Hibernate получите любого режиссера, а затем получите список его фильмов.
//            Director director = session.get(Director.class, 2);
//            List<Movie> movies = director.getMovies();
//            System.out.println(movies);



            // Получите любой фильм, а затем получите его режиссера
//            Movie movie = session.get(Movie.class, 3);
//            System.out.println(movie.getDirector());



            // Добавьте еще один фильм для любого режиссера.
//            Director director = session.get(Director.class, 2);
//            Movie movie = new Movie(director, "Test film", 2003);
//            director.getMovies().add(movie);
//            session.persist(movie);


            //Создайте нового режиссера и новый фильм и свяжите эти сущности.
//            Director director = new Director("Ridley Scott", 1959);
//            Movie movie = new Movie(director, "Alien", 1985);
//            director.setMovies(List.of(movie));
//            session.persist(director);
//            session.persist(movie);


            // Смените режиссера у существующего фильма.
//            Director director = session.get(Director.class, 7);
//            Movie movie = session.get(Movie.class, 12);
//            movie.getDirector().getMovies().remove(movie);
//            movie.setDirector(director);
//            director.getMovies().add(movie);


            // Удалите фильм у любого режиссера.
//            Movie movie = session.get(Movie.class, 12);
//            Director director = movie.getDirector();
//            director.getMovies().remove(movie);
//            movie.setDirector(null);
//            session.remove(movie);


            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
