package com.courses.management.user;

import com.courses.management.common.exceptions.SQLUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        LOG.debug(String.format("create: user.first_name=%s " +
                "user.last_name=%s" +
                "user.email=%s", user.getFirstName(), user.getLastName(), user.getEmail()));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("create: user.email=%s", user.getEmail()), e);
            throw new SQLUserException("Error occurred when creating user");
        }
    }

    @Override
    public void update(User user) {
        LOG.debug(String.format("update: user.first_name=%s " +
                "user.last_name=%s" +
                "user.email=%s", user.getFirstName(), user.getLastName(), user.getEmail()));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("update: user.email=%s", user.getEmail()), e);
            throw new SQLUserException("Error occurred when updating user");
        }
    }

    @Override
    public void delete(int id) {
        LOG.debug(String.format("delete: user.id=%s ", id));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE User as u where u.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("delete: user.id=%s", id), e);
            throw new SQLUserException("Error occurred when removing user");
        }
    }

    @Override
    public User get(int id) {
        LOG.debug(String.format("get: user.id=%s ", id));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            return session.get(User.class, id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("get: user.id=%s", id), e);
            throw new SQLUserException("Error occurred when retrieving user");
        }
    }

    @Override
    public List<User> getAll() {
        LOG.debug("getAll: ");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> rootElement = query.from(User.class);
            CriteriaQuery<User> all = query.select(rootElement);
            return session.createQuery(all).getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("getAll: ", e);
            throw new SQLUserException("Error occurred when retrieving all user");
        }
    }

    @Override
    public User get(String email) {
        LOG.debug(String.format("get: user.email=%s ", email));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = (User) session.createQuery("FROM User as u where u.email=:email")
                    .setParameter("email", email)
                    .uniqueResult();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("get: user.email=%s", email), e);
            throw new SQLUserException("Error occurred when retrieving user");
        }
    }

    @Override
    public List<User> getUsersByCourse(String courseTitle) {
        LOG.debug(String.format("getUsersByCourse: course.title=%s", courseTitle));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            List<User> users = session.createQuery("FROM User as u INNER JOIN u.course as c" +
                    " WHERE c.title=:title")
                    .setParameter("title", courseTitle)
                    .getResultList();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("getUsersByCourse: course.title=%s", courseTitle), e);
            throw new SQLUserException("Error occurred when retrieving users by course title");
        }
    }

    @Override
    public List<User> getAllByStatus(UserStatus userStatus) {
        LOG.debug(String.format("getAllByStatus: user.status=%s", userStatus.name()));
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User as u where u.status=:status")
                    .setParameter("status", userStatus)
                    .getResultList();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("getAllByStatus: user.status=%s", userStatus.name()), e);
            throw new SQLUserException("Error occurred when retrieving users by status");
        }
    }
}
