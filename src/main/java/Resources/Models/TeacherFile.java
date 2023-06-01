package Resources.Models;

import java.io.File;

public class TeacherFile {
    private int teacher_id;
    private File file;

    public TeacherFile(int teacher_id) {
        this.teacher_id = teacher_id;
        this.file = null;
    }
    public void setFile(File file) {        this.file = file;    }

    public int getTeacher_id() {        return teacher_id;    }
    public File getFile() {        return file;    }
}
