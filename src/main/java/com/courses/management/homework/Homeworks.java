package com.courses.management.homework;

import com.courses.management.common.PropertiesUtil;
import com.courses.management.course.Course;
import com.courses.management.course.CourseRepository;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class Homeworks {
    private HomeworkRepository homeworkRepository;
    private CourseRepository courseRepository;

    public Homeworks(HomeworkRepository homeworkRepository, CourseRepository courseRepository) {
        this.homeworkRepository = homeworkRepository;
        this.courseRepository = courseRepository;
    }

    public void uploadFile(List<FileItem> items, Integer courseId) {
        Course course = courseRepository.findById(courseId).get();
        if (Objects.isNull(course)) {
            throw new RuntimeException(String.format("Course with id = %s not found", courseId));
        }
        Homework homework = null;
        try {
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    homework = createHomework(course, item);
                    File file = new File(homework.getPath());
                    validateIfFileExists(file, homework.getTitle());
                    homeworkRepository.save(homework);
                    item.write(file);
                }
            }
        } catch (Exception e) {
            if (Objects.nonNull(homework) && homework.getId() != 0) {
                homeworkRepository.delete(homework);
            }
            throw new RuntimeException("Error when loading file " + e.getMessage());
        }
    }

    private void validateIfFileExists(File file, String title) {
        if (file.exists()) {
            throw new RuntimeException(String.format("Homework with title %s already exist", title));
        }
    }

    private Homework createHomework(Course course, FileItem item) {
        Homework homework = new Homework();
        homework.setCourse(course);
        String title = new File(item.getName()).getName();
        String path = PropertiesUtil.getFolderPath() + File.separator + course.getTitle() + File.separator + title;
        homework.setTitle(title);
        homework.setPath(path);
        return homework;
    }

    public Homework getHomework(Integer id) {
        return  homeworkRepository.findById(id).get();
    }
}
