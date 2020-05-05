package com.courses.management.homework;

import com.courses.management.course.Course;
import com.courses.management.course.CourseRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
public class Homeworks {
    private static final Logger LOG = LogManager.getLogger(Homeworks.class);
    private HomeworkRepository homeworkRepository;
    private CourseRepository courseRepository;
    private String folderPath;

    @Autowired
    public Homeworks(HomeworkRepository homeworkRepository, CourseRepository courseRepository) {
        this.homeworkRepository = homeworkRepository;
        this.courseRepository = courseRepository;
    }

    public void uploadFile(List<FileItem> items, Integer courseId) {
        LOG.debug(String.format("uploadFile: courseId=%d", courseId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException(String.format("Course with id = %s not found", courseId)));
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
            LOG.error(String.format("uploadFile: courseId=%d", courseId), e);
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
        LOG.debug(String.format("createHomework: courseId=%d, fileName=%s", course.getId(), item.getName()));
        Homework homework = new Homework();
        homework.setCourse(course);
        String title = new File(item.getName()).getName();
        String path = String.format("%s%s%s%s%s", folderPath, File.separator, course.getTitle(), File.separator, title);
        homework.setTitle(title);
        homework.setPath(path);
        return homework;
    }

    public Homework getHomework(Integer id) {
        LOG.debug(String.format("getHomework: id=%d", id));
        return  homeworkRepository.findById(id).orElse(new Homework());
    }

    @Value("folder_path")
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
}
