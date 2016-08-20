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

public class Schedule implements Serializable{
    
    private ArrayList<Course> course_list;
    
    public Schedule()
    {
        course_list = new ArrayList<Course>();
    }
    

    
    public boolean isConflict(Course course)
    {
        
        
        if (course_list.isEmpty())
        {
            return false;
        }
                
        //for each course
        //check if there is no overlap
        for (Course c : course_list)
        {
            //two cases to check
            //case 1: 
            //                      t1 -------t2
            //         t3---t4
            //------------------------------------------------
            if (!((course.getStartTime() < c.getStartTime() && (course.getEndTime() < c.getStartTime())) || course.getStartTime() > c.getEndTime() && course.getEndTime() > c.getEndTime()))
            {
                if ((course.getEndTime() == c.getStartTime()) || (course.getStartTime() == c.getEndTime()))
                        {
                            continue;
                        }
                else
                {return true;}
            }
            //case 2:
            //                 t1 -------t2
            //                                  t3-----t4
            //-----------------------------------------------
           
            if ((course.getStartTime() == c.getStartTime()) && (course.getEndTime() == c.getEndTime()))
            {
                return true;
            }
            
        }
        
        //anything else other than these 2 cases is overlapping     
        //there is a conflict otherwise
        return false;
    }
    
    //Add courses to schedule
    public boolean addCourse(Course course)
    {
        if (!isConflict(course))
        {
            course_list.add(course);
            return true;
        }
        else return false;
    }
    
    public void removeCourse(Course course)
    {
        course_list.remove(course);
    }
    
    //return the CourseList in the current schedule
    public ArrayList<Course> getCourseInSchedule(){return course_list;}
   
    public boolean isEmpty()
    {
        return course_list.isEmpty();
    }
    
    public void printSchedule()
    {
        if (course_list.isEmpty())
        {
            System.out.println("Nothing is scheduled!");
        }
        else
        {
            int count = 1;
            for (Course c : course_list)
            {
                System.out.println(Integer.toString(count)+ ") " +c);
                count++;
            }
        }
    }
    
    //Search for any course in the time slot
    public Course getCourseInTime(int starttime, int endtime)
    {
        //for each course in the list
        for (Course c :course_list)
        {
            //if it is in the time slot
            if (((c.getStartTime()>= starttime && c.getStartTime() <= endtime)|| (c.getEndTime()>= starttime && c.getEndTime() <= endtime)) && !((c.getStartTime() == endtime)) && !(c.getEndTime() == starttime))
            {
                return c; //return the course
            }
        }
        //else return nothing
        return null;
    }
}
