package com.courses.management.homework;

import com.courses.management.common.CommonService;
import com.courses.management.course.CourseRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

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

    }

    @Override
    public Homework getHomework(Integer id) {
        return null;
    }


}
