package com.courses.management.homework;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.courses.management.common.CommonService;
import com.courses.management.course.Course;
import com.courses.management.course.CourseRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Profile("aws")
public class HomeworkAWSService implements HomeworkService {
    private static final Logger LOG = LogManager.getLogger(HomeworkAWSService.class);
    private HomeworkRepository homeworkRepository;
    private CourseRepository courseRepository;
    private CommonService commonService;

    @Autowired
    public HomeworkAWSService(HomeworkRepository homeworkRepository, CourseRepository courseRepository,
                              CommonService commonService) {
        this.homeworkRepository = homeworkRepository;
        this.courseRepository = courseRepository;
        this.commonService = commonService;
    }

    @Override
    public void uploadFile(List<FileItem> items, Integer courseId) {
        LOG.debug(String.format("uploadFile: courseId=%d", courseId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException(String.format("Course with id = %s not found", courseId)));
        Homework homework = null;
        try {
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    homework = createHomework(course, item);
                    validateIfFileExists(homework.getTitle());
                    homeworkRepository.save(homework);
                    uploadToAWS(homework, item);
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

    private void uploadToAWS(Homework homework, FileItem item) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(item.getSize());
        commonService.getS3Client().putObject(commonService.getS3BucketName(), homework.getPath(),
                item.getInputStream(), objectMetadata);
    }

    private Homework createHomework(Course course, FileItem item) {
        LOG.debug(String.format("createHomework: courseId=%d, fileName=%s", course.getId(), item.getName()));
        Homework homework = new Homework();
        homework.setCourse(course);
        String title = new File(item.getName()).getName();
        String path = String.format("%s%s%s", course.getTitle(), "/", title);
        homework.setTitle(title);
        homework.setPath(path);
        return homework;
    }

    private void validateIfFileExists(String title) {
        if (commonService.getS3Client().doesObjectExist(commonService.getS3BucketName(), title)) {
            throw new RuntimeException(String.format("Homework with title %s already exist", title));
        }
    }

    @Override
    public Homework getHomework(Integer id) {
        return null;
    }
}
