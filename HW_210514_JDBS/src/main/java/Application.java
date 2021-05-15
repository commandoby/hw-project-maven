import java.util.List;

public class Application {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Ivan");
        student.setSurname("Ivanov");
        student.setCourse_name("Java Core SE");

        CRUDUtils.addStudent(student);
        List<Student> students = CRUDUtils.getStudentData();
        System.out.println(students);

        CRUDUtils.updateStudent(1, "Java EE");
        List<Student> updateStudents = CRUDUtils.getStudentData();
        System.out.println(updateStudents);

        CRUDUtils.deleteStudent(1);
        List<Student> withDeletedStudents = CRUDUtils.getStudentData();
        System.out.println(withDeletedStudents);
    }
}
