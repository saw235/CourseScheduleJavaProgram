package Form.Classes;

/**
 * Author: Saw Xue Zheng
 * Description: Definition of class Schedule
 *              Performs various logic behind scheduling, perform checking if the course is valid, is in conflict etc...
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
        
        //if no course, can't have conflict
        if (course_list.isEmpty())
        {
            return false;
        }
                
        //for each course
        //check if there is overlap
        for (Course c : course_list)
        {
            //if not this case
            //                 t1 -------t2
            //                                  t3-----t4
            //-----------------------------------------------
            //or this case
            //                 t1 -------t2
            //      t3-----t4
            //-----------------------------------------------
            if (!((course.getStartTime() < c.getStartTime() && (course.getEndTime() < c.getStartTime())) || course.getStartTime() > c.getEndTime() && course.getEndTime() > c.getEndTime()))
            {
                if ((course.getEndTime() == c.getStartTime()) || (course.getStartTime() == c.getEndTime())) 
                        {
                            continue;//ignores boundary cases
                        }
                else
                {return true;} //then there is a conflict
            }

           //if exactly overlapping
            if ((course.getStartTime() == c.getStartTime()) && (course.getEndTime() == c.getEndTime()))
            {
                return true; //there is a conflict
            }
            
        }
        
        //anything else other than these 2 cases is not overlapping    
        //there is no conflict otherwise
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
