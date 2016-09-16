package Form.Classes;

/*
Author : Saw Xue Zheng
Description:  CourseScheduling API()

 */
import java.util.ArrayList;
import java.io.*;

public class Student implements Serializable{
    
    private int id; 
    public static int id_count;
    private String f_name;
    private String l_name;
    private String ph_n;
    
    private Schedule s;
    
    public Student(String fname, String lname, String phn)
    {
        this.f_name = fname;
        this.l_name = lname;
        this.ph_n = phn;
        
        id = id_count;
        id_count++;
        s = new Schedule();
    }
    
    @Override
    public String toString()
    {
        return "Name: " + this.f_name + " " + this.l_name  +" ID: " + Integer.toString(this.id) + " Phone: " + this.ph_n;
    }
    
    public boolean addCoursetoSchedule(Course c)
    {
        if (!s.addCourse(c))
        {
            System.out.println("Time conflict! Failed to add course to student's schedule!");
            return false;
        }
        else return true;
        
    }
    
    public void removeCoursefromSchedule(Course c)
    {
        s.removeCourse(c);
    }
    
    public void printStudentSchedule()
    {
        System.out.println("------------------------------------------");
        System.out.println(this);
        System.out.println("List of Course Being Taken: ");
        s.printSchedule();
        System.out.println("------------------------------------------");
    }
    
    public Schedule getStudentSchedule()
    {
        return s;
    }
    
    public int getStudId()
    {
        return this.id;
    }
    
    public String getFirstName()
    {
        return this.f_name;
    }
    
    public String getLastName()
    {
        return this.l_name;
    }
    
    public String getPhoneNum()
    {
        return this.ph_n;
    }
}
