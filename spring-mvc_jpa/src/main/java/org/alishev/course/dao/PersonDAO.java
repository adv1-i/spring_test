package org.alishev.course.dao;

import org.alishev.course.model.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        // получаем сессию
        Session session = entityManager.unwrap(Session.class);

        // 1 запрос
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//
//        // N запросов
//        for (Person person : people) {
//            System.out.println("Person: " + person.getName() + "has: " + person.getItems());
//        }

        // Solution
        // SQL: A LEFT JOIN B -> Результирующая объединенная таблица
        // FETCH указываем, чтобы сказать, что мы хотим получить значения строк
        // и загрузить в оперативную память

        Set<Person> people = new HashSet<Person>(session.createQuery("select p from Person p LEFT JOIN FETCH p.items")
                .getResultList());

        for (Person person : people) {
            System.out.println("Person: " + person.getName() + "has: " + person.getItems());
        }
    }

}


