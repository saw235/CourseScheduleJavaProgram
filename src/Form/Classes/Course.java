package Form.Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.io.*;

public class Course implements Serializable{
    
    private String name;
    private int starttime; //goes from 0000 to 2400
    private int endtime; // default is starttime + 1 hour
    private int room;
    private ArrayList<Student> std_list;
    
    private final int duration = 100;
    
    
    
    public Course(String name, int starttime, ArrayList<Student> std_list, int room_num)
    {
        this.name = name;
        
        if (starttime%100 <= 59 && starttime/100 >= 9 && starttime/100 <= 17)
        {
            this.starttime = starttime;
        }
        else //time is not in valid format
        {
            //throw exception
            throw new IllegalArgumentException("Time should be between 900 and 1700! (9 to 5 pm)");
        }
        
        //by default every course is 1 hour long
        this.endtime = this.starttime + duration;
        
        
        this.std_list = std_list;
        
        this.room = room_num;
        
        for (Student s : std_list)
        {
            s.addCoursetoSchedule(this);
        }
    }
    
    @Override
    public String toString()
    {
        return "Course name: " + name  + " Time: " + Integer.toString(starttime) + " to " + Integer.toString(endtime)+ " Room: " + Integer.toString(room) + " StudentSize: " + std_list.size();
    }
    
    
    public void setRoom(int room_num){this.room = room_num;}
    public int getStartTime(){return starttime;}
    public int getEndTime(){return endtime;}
    public String getCourseName(){return this.name;}
    public int getRoomNum(){return this.room;}
    public ArrayList<Student> getStudentList(){return this.std_list;}
    
    public void printStudentList()
    {
        int count = 1;
        System.out.println("------------------------------------------");
        System.out.println(this);
        System.out.println("List of students in this course: ");
        for (Student s : this.std_list)
        {
            System.out.println(Integer.toString(count)+ ") " +s);
            count++;
        }
        System.out.println("------------------------------------------");
    }
    
}
