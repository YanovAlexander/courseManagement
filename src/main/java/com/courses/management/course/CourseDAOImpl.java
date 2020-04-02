package com.courses.management.course;

import com.courses.management.common.exceptions.SQLCourseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private final static Logger LOG = LogManager.getLogger(CourseDAOImpl.class);
    private final static String INSERT = "INSERT INTO course(title, status) " +
            "VALUES(?, ?);";
    private final static String FIND_COURSE_BY_TITLE =
            "SELECT id, title, status FROM course " +
                    "WHERE title = ?;";
    private final static String UPDATE_COURSE = "UPDATE course SET " +
            "title=?, status=? WHERE id=?";
    private static final String FIND_COURSE_BY_ID = "SELECT id, title, status FROM course " +
            "WHERE id = ?;";
    private static final String FIND_ALL_COURSES = "SELECT id, title, status FROM course";
    private DataSource dataSource;
    private SessionFactory sessionFactory;

    public CourseDAOImpl(DataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Course course) {
        LOG.debug(String.format("create: course.title=%s", course.getTitle()));

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getCourseStatus().getStatus());
            statement.execute();
        } catch (SQLException e) {
            LOG.error(String.format("create: course.title=%s", course.getTitle()), e);
            throw new SQLCourseException("Error occurred when saving a course");
        }
    }

    @Override
    public void update(Course course) {
        LOG.debug(String.format("update: course.title=%s, course.status=%s", course.getTitle(),
                course.getCourseStatus()));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COURSE)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getCourseStatus().getStatus());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(String.format("update: course.title=%s", course.getTitle()), e);
            throw new SQLCourseException("Error occurred when update a course");
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Course can't be deleted");
    }

    @Override
    public Course get(int id) {
        LOG.debug(String.format("get: course.id=%s", id));
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_COURSE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getCourse(resultSet);
        } catch (SQLException e) {
            LOG.error(String.format("get: course.id=%s", id), e);
            throw new SQLCourseException("Error occurred when find a course");
        }
    }

    @Override
    public List<Course> getAll() {
        LOG.debug("getAll");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_COURSES)) {
            ResultSet resultSet = statement.executeQuery();
            return getCourseList(resultSet);
        } catch (SQLException e) {
            LOG.error("getAll", e);
            throw new SQLCourseException("Error occurred when get all courses");
        }
    }

    @Override
    public Course get(String title) {
        Session session = null;
        Transaction transaction = null;
        Course course = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            course = (Course) session.createQuery("from Course c where c.title=:title")
                    .setParameter("title", title).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            LOG.error(String.format("get: course.title=%s", title), e);
            throw new SQLCourseException("Error occurred when find a course");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return course;
    }

    private Course getCourse(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return mapCourseFromRS(rs);
        }
        return null;
    }

    private Course mapCourseFromRS(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setTitle(rs.getString("title"));
        course.setCourseStatus(CourseStatus.getCourseStatus(rs.getString("status")).get());
        return course;
    }

    private List<Course> getCourseList(ResultSet rs) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(mapCourseFromRS(rs));
        }
        return courses;
    }
}
