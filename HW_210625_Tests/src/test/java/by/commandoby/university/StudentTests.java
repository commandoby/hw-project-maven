package by.commandoby.university;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static by.commandoby.university.Sex.*;

public class StudentTests {
    private static Student student1;
    private static Student student2;
    private static Student student3;
    private static List<Student> actual;
    private static List<Student> actualBySex;

    @BeforeClass
    public static void setUp() {
        student1 = new Student("Ivan", 17, MALE);
        student2 = new Student("Anna", 19, FEMALE);
        student3 = new Student("Polina", 18, FEMALE);

        actual = new ArrayList<>();
        actual.add(student1);
        actual.add(student2);
        actual.add(student3);

        actualBySex = new ArrayList<>();
        actualBySex.add(student1);
    }

    @Test
    public void checkAllStudentsReturned() {
        List<Student> expected = Student.getAllStudents();
        Assert.assertEquals(actual, expected, "Some error message");
    }

    @Test
    public void checkAllStudentsReturned_NotNull() {
        List<Student> expected = Student.getAllStudents();
        Assert.assertNotNull(expected);
    }

    @Test
    public void checkGetAllUsers_MALE() {
        List<Student> expected = Student.getAllStudentsBySex(MALE);
        Assert.assertEquals(actualBySex, expected);
    }

    @Test
    public void checkGetAllUsers_FEMALE_NotNull() {
        List<Student> expected = Student.getAllStudentsBySex(FEMALE);
        Assert.assertNotNull(expected);
    }

    @Test
    public void checkGetStudentsCount() {
        int expected = Student.getStudentsCount();
        Assert.assertEquals(3, expected);
    }

    @Test
    public void checkGetStudentsCount_MALE() {
        int expected = Student.getStudentsCountWitSex(MALE);
        Assert.assertEquals(1, expected);
    }

    @Test
    public void checkGetSumOfAllStudentsAge() {
        int expected = Student.getSumOfAllStudentsAge();
        Assert.assertEquals(54, expected);
    }

    @Test
    public void checkGetSumOfAllStudentsAge_Positive() {
        boolean expected = Student.getSumOfAllStudentsAge() > 0;
        Assert.assertTrue(expected);
    }

    @Test
    public void checkGetSumOfAllStudentsAge_FEMALE() {
        int expected = Student.getSumOfAllStudentsAgeBySex(FEMALE);
        Assert.assertEquals(37, expected);
    }

    @Test
    public void checkGetAverageAgeOfAllUsers() {
        int expected = Student.getAverageAgeOfAllUsers();
        Assert.assertEquals(18, expected);
    }

    @Test
    public void checkGetAverageAgeOfAllUsers_MALE() {
        int expected = Student.getAverageAgeOfAllUsersBySex(MALE);
        Assert.assertEquals(17, expected);
    }

    @Test
    public void checkGetAverageAgeOfAllUsers_FEMALE_Positive() {
        boolean expected = Student.getAverageAgeOfAllUsersBySex(FEMALE) > 0;
        Assert.assertTrue(expected);
    }

    @Test
    public void checkToString_NotNull() {
        String expected = student1.toString();
        Assert.assertNotNull(expected);
    }

    @Test
    public void checkEqualsFalse() {
        boolean expected = student1.equals(student2);
        Assert.assertFalse(expected);
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("All tests are finished!");
    }
}
