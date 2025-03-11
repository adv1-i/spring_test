package org.example;

import org.example.model.Principal;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrincipalSchoolTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Получите любую школу, а затем получите ее директора
//            School school = session.get(School.class, 1);
//            System.out.println(school.getPrincipal().getName());


            // Создайте нового директора и новую школу и свяжите эти сущности.
            // Principal principal = new Principal("Anton", 55);
            // School school = new School(777, principal);
            // principal.setSchool(school);
            // session.persist(principal);

            // Смените директора у существующей школы.
            School school = session.get(School.class, 2);
            Principal principal = new Principal("Test", 33);

            session.remove(school.getPrincipal());

            school.setPrincipal(principal);
            principal.setSchool(school);

            session.persist(principal);


            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
