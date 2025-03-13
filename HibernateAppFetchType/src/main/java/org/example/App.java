package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Получаем только объект Person (без товаров)
            Person person = session.get(Person.class, 1);

            session.getTransaction().commit();

            System.out.println("Сессия завершилась");

            // Открываем сессию и транзакцию еще раз
            // Для получения товаров пользователя
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Внутри второй транзакции");
            person = session.merge(person);
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println(person.getItems());

        } finally {
            sessionFactory.close();
        }
    }
}
