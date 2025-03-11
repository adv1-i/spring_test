package org.example;

import org.example.model.Item;
import org.example.model.Person;
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

            Person person = new Person("Test cascade person", 20);

            person.addItem(new Item("item 1"));
            person.addItem(new Item("item 2"));
            person.addItem(new Item("item 3"));

            session.persist(person);

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
