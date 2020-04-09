package com.courses.management.user;

import com.courses.management.common.exceptions.SQLUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {
    private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
//        LOG.debug(String.format("create: user.first_name=%s " +
//                "user.last_name=%s" +
//                "user.email=%s", user.getFirstName(), user.getLastName(), user.getEmail()));
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(INSERT)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getEmail());
//            statement.setString(4, user.getUserRole().name());
//            statement.setString(5, user.getStatus().name());
//            statement.execute();
//        } catch (SQLException e) {
//            LOG.error(String.format("create: user.email=%s", user.getEmail()), e);
//            throw new SQLUserException("Error occurred when creating user");
//        }
    }

    @Override
    public void update(User user) {
//        LOG.debug(String.format("update: user.first_name=%s " +
//                "user.last_name=%s" +
//                "user.email=%s", user.getFirstName(), user.getLastName(), user.getEmail()));
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getEmail());
//            statement.setString(4, user.getUserRole().name());
//            statement.setString(5, user.getStatus().name());
//
//            if (Objects.isNull(user.getCourse())) {
//                statement.setNull(6, Types.NULL);
//            } else {
//                statement.setInt(6, user.getCourse().getId());
//            }
//
//            statement.setInt(7, user.getId());
//            statement.execute();
//        } catch (SQLException e) {
//            LOG.error(String.format("update: user.email=%s", user.getEmail()), e);
//            throw new SQLUserException("Error occurred when updating user");
//        }
    }

    @Override
    public void delete(int id) {
//        LOG.debug(String.format("delete: user.id=%s ", id));
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(DELETE)) {
//            statement.setInt(1, id);
//            statement.execute();
//        } catch (SQLException e) {
//            LOG.error(String.format("delete: user.id=%s", id), e);
//            throw new SQLUserException("Error occurred when removing user");
//        }
    }

    @Override
    public User get(int id) {
//        LOG.debug(String.format("get: user.id=%s ", id));
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
//            statement.setInt(1, id);
//            return getUser(statement.executeQuery());
//        } catch (SQLException e) {
//            LOG.error(String.format("get: user.id=%s", id), e);
//            throw new SQLUserException("Error occurred when retrieving user");
//        }
        return null;
    }

    @Override
    public List<User> getAll() {
//        LOG.debug("getAll: ");
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {
//            return getUserList(statement.executeQuery());
//        } catch (SQLException e) {
//            LOG.error("getAll: ", e);
//            throw new SQLUserException("Error occurred when retrieving all user");
//        }
        return null;
    }

    @Override
    public User get(String email) {
//        LOG.debug(String.format("get: user.email=%s ", email));
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
//            statement.setString(1, email);
//            return getUser(statement.executeQuery());
//        } catch (SQLException e) {
//            LOG.error(String.format("get: user.email=%s", email), e);
//            throw new SQLUserException("Error occurred when retrieving user");
//        }
        return null;
    }

    @Override
    public List<User> getUsersByCourse(String courseTitle) {
//        LOG.debug(String.format("getUsersByCourse: course.title=%s", courseTitle));
//        try (Connection connection = dataSource.getConnection();
//            PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_COURSE_TITLE)) {
//            statement.setString(1, courseTitle);
//            return getUserList(statement.executeQuery());
//        } catch (SQLException e) {
//            LOG.error(String.format("getUsersByCourse: course.title=%s", courseTitle), e);
//            throw new SQLUserException("Error occurred when retrieving users by course title");
//        }
        return null;
    }

    @Override
    public List<User> getAllByStatus(UserStatus userStatus) {
//        LOG.debug(String.format("getAllByStatus: user.status=%s", userStatus.name()));
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_BY_STATUS)) {
//            statement.setString(1, userStatus.name());
//            return getUserList(statement.executeQuery());
//        } catch (SQLException e) {
//            LOG.error(String.format("getAllByStatus: user.status=%s", userStatus.name()), e);
//            throw new SQLUserException("Error occurred when retrieving users by status");
//        }
        return null;
    }
}
