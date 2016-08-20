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
import java.io.*;
        
public class Room implements Serializable {
    
    private int num;
    
    private Schedule schedule;
	
    @Override
    public String toString()
    {
        return "Room " + Integer.toString(num);
    }
    
    public Room(int num)
    {
        this.num = num;
        schedule = new Schedule();
    }
    
    public boolean addCourses(Course course)
    {   
        boolean success = this.schedule.addCourse(course);
                
	if (success)
        {
            System.out.println(course.getCourseName() + " is successfully added to the room!");
        }
        else 
        {
            System.out.println(course.getCourseName() + " cannot be added. There is a conflict in the schedule.");
        }
        
        return success;
    }
    
    public Schedule getSchedule()
    {
        return schedule;
    }
    
    public void printSchedule()
    {
        System.out.println("------------------------------------------");
        System.out.println(this);
        System.out.println("List of Course in this Room: ");
        schedule.printSchedule();
        System.out.println("------------------------------------------");
    }
    
    public int getNum(){return num;}
}
