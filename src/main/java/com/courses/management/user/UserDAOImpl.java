package com.courses.management.user;

import com.courses.management.common.exceptions.SQLCourseException;
import com.courses.management.common.exceptions.SQLUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final static Logger LOG = LogManager.getLogger(UserDAOImpl.class);
    private static final String FIND_USER_BY_ID = "SELECT id, first_name, last_name, email, user_role, status " +
            "FROM users WHERE id=?;";
    private static final String FIND_USER_BY_EMAIL= "SELECT id, first_name, last_name, email, user_role, status " +
            "FROM users WHERE email=?;";
    private static final String FIND_ALL_USERS = "SELECT id, first_name, last_name, email, user_role, status " +
            "FROM users;";
    private static final String UPDATE_REMOVE_COURSE_AND_SET_STATUS = "UPDATE users SET course_id = null, status = ? " +
            "WHERE email = ?";
    private DataSource dataSource;
    private final static String INSERT = "INSERT INTO users(first_name, last_name, email, user_role, status) " +
            "VALUES(?, ?, ?, ?, ?);";
    private final static String UPDATE = "UPDATE users SET first_name=?, last_name=?, email=?, user_role=?," +
            " status=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM users WHERE id=?;";
    private static final String FIND_USERS_BY_COURSE_TITLE = "SELECT u.id, u.first_name, u.last_name, u.email, u.user_role, u.status " +
            "FROM users u " +
            "INNER JOIN course c ON c.id=u.course_id " +
            "WHERE c.title=?;";
    private static final String FIND_ALL_USERS_BY_STATUS = "SELECT id, first_name, last_name, email, user_role, status " +
            "FROM users WHERE status=?;";

    public UserDAOImpl(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public void create(User user) {
        LOG.debug(String.format("create: user.first_name=%s " +
                "user.last_name=%s" +
                "user.email=%s", user.getFirstName(), user.getLastName(), user.getEmail()));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserRole().name());
            statement.setString(5, user.getStatus().name());
            statement.execute();
        } catch (SQLException e) {
            LOG.error(String.format("create: user.email=%s", user.getEmail()), e);
            throw new SQLUserException("Error occurred when creating user");
        }
    }

    @Override
    public void update(User user) {
        LOG.debug(String.format("update: user.first_name=%s " +
                "user.last_name=%s" +
                "user.email=%s", user.getFirstName(), user.getLastName(), user.getEmail()));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserRole().name());
            statement.setString(5, user.getStatus().name());
            statement.setInt(6, user.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error(String.format("update: user.email=%s", user.getEmail()), e);
            throw new SQLUserException("Error occurred when updating user");
        }
    }

    @Override
    public void delete(int id) {
        LOG.debug(String.format("delete: user.id=%s ", id));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOG.error(String.format("delete: user.id=%s", id), e);
            throw new SQLUserException("Error occurred when removing user");
        }
    }

    @Override
    public User get(int id) {
        LOG.debug(String.format("get: user.id=%s ", id));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            return getUser(statement.executeQuery());
        } catch (SQLException e) {
            LOG.error(String.format("get: user.id=%s", id), e);
            throw new SQLUserException("Error occurred when retrieving user");
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return mapUserFromRS(rs);
        }
        return null;
    }

    private User mapUserFromRS(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setUserRole(UserRole.getUserRole(rs.getString("user_role")).get());
        user.setStatus(UserStatus.getUserStatus(rs.getString("status")).get());
        return user;
    }

    @Override
    public List<User> getAll() {
        LOG.debug("getAll: ");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {
            return getUserList(statement.executeQuery());
        } catch (SQLException e) {
            LOG.error("getAll: ", e);
            throw new SQLUserException("Error occurred when retrieving all user");
        }
    }

    private List<User> getUserList(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(mapUserFromRS(rs));
        }
        return users;
    }

    @Override
    public User get(String email) {
        LOG.debug(String.format("get: user.email=%s ", email));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            return getUser(statement.executeQuery());
        } catch (SQLException e) {
            LOG.error(String.format("get: user.email=%s", email), e);
            throw new SQLUserException("Error occurred when retrieving user");
        }
    }

    @Override
    public List<User> getUsersByCourse(String courseTitle) {
        LOG.debug(String.format("getUsersByCourse: course.title=%s", courseTitle));
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_COURSE_TITLE)) {
            statement.setString(1, courseTitle);
            return getUserList(statement.executeQuery());
        } catch (SQLException e) {
            LOG.error(String.format("getUsersByCourse: course.title=%s", courseTitle), e);
            throw new SQLUserException("Error occurred when retrieving users by course title");
        }
    }

    @Override
    public List<User> getAllByStatus(UserStatus userStatus) {
        LOG.debug(String.format("getAllByStatus: user.status=%s", userStatus.name()));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_BY_STATUS)) {
            statement.setString(1, userStatus.name());
            return getUserList(statement.executeQuery());
        } catch (SQLException e) {
            LOG.error(String.format("getAllByStatus: user.status=%s", userStatus.name()), e);
            throw new SQLUserException("Error occurred when retrieving users by status");
        }
    }

    @Override
    public void removeUserCourseAndSetStatus(String email, UserStatus status) {
        LOG.debug(String.format("removeUserCourseAndSetStatusNotActive: user.email=%s", email));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_REMOVE_COURSE_AND_SET_STATUS)) {
            statement.setString(1, status.name());
            statement.setString(2, email);
            statement.execute();
        } catch (SQLException e) {
            LOG.error(String.format("removeUserCourseAndSetStatusNotActive: user.email=%s", email), e);
            throw new SQLUserException("Error occurred when removing course from a user");
        }
    }
}
