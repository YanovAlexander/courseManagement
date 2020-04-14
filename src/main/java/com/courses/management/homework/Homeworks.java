package com.courses.management.homework;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class Homeworks {
    public void uploadFile(FileItem item, String title, Integer courseId) {
        /*TODO retrieve course from the database
        Add homework to course entity
        Save course
        Save Homework on the disk
        * */
        try {
            if (!item.isFormField()) {
                String name = new File(item.getName()).getName();
                item.write(new File("C:\\Users\\Oleksandr_Yanov1\\Desktop\\GoIT\\files" + File.separator + name));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error when loading file " + e);
        }
    }

    public Homework getHomeworks(Integer id) {
        //homeworkDao.get(id)

        return null;
    }
}
