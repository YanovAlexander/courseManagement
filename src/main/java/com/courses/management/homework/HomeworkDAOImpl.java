package com.courses.management.homework;

import com.courses.management.common.exceptions.SQLUserException;
import com.courses.management.config.HibernateDatabaseConnector;
import com.courses.management.course.CourseDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HomeworkDAOImpl implements HomeworkDAO {
    private final static Logger LOG = LogManager.getLogger(CourseDAOImpl.class);

    private JdbcTemplate template;

    @Autowired
    public HomeworkDAOImpl(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public void create(Homework homework) {
        LOG.debug(String.format("create: homework.title=%s " +
                "homework.path=%s" +
                "homework.course_id=%s", homework.getTitle(), homework.getPath(), homework.getCourse().getId()));
        try {
            template.update("INSERT INTO home_work(title, text, file_path, course_id) " +
                            "VALUES (?, ?, ?, ?)", homework.getTitle(), homework.getText(),
                    homework.getPath(), homework.getCourse().getId());
        } catch (Exception e) {
            LOG.error(String.format("create: homework.title=%s", homework.getTitle()), e);
            throw new SQLUserException("Error occurred when creating a homework");
        }
    }

    @Override
    public void update(Homework homework) {

    }

    @Override
    public void delete(int id) {
        LOG.debug(String.format("delete: homework.id=%s ", id));
        Transaction transaction = null;
        try (Session session = HibernateDatabaseConnector.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE Homework as hw where hw.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("delete: homework.id=%s", id), e);
            throw new SQLUserException("Error occurred when removing homework");
        }
    }

    @Override
    public Homework get(int id) {
        LOG.debug(String.format("get: homework.id=%s ", id));
        Transaction transaction = null;
        try (Session session = HibernateDatabaseConnector.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            return session.get(Homework.class, id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("get: homework.id=%s", id), e);
            throw new SQLUserException("Error occurred when retrieving a homework");
        }
    }

    @Override
    public List<Homework> getAll() {
        return null;
    }

    @Override
    public List<Homework> getAll(int courseId) {
        return null;
    }
}
