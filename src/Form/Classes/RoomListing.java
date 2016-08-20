package Form.Classes;


import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class RoomListing implements Serializable{

    private ArrayList<Room> roomlist = new ArrayList<Room>();
    private ArrayList<Course> courselist = new ArrayList<Course>();

    public RoomListing(int n_Room) {
        //initialize roomlist
        for (int i = 0; i < n_Room; i++) {
            roomlist.add(new Room(i));
        }

    }

    //needs more
    public void removeRoom(int id) {
        //check if room id is valid
        int index = this.isValidRoom(id);
        //if not valid id return error message no such room
        if (index == -1) {
            System.out.println("Room does not exist!");
        } else if (index >= 0) //if valid
        {

            //check if room schedule is empty
            if (roomlist.get(index).getSchedule().isEmpty()) {
                //if empty then delete
                roomlist.remove(index);
                System.out.println("Room removed!");
            } else {
                //else throw exception room is scheduled cannot delete
                System.out.println("Room is schedule for something, cannot be removed!");
            }

        }

    }

    public void addRoom(int id) {
        if (id > -1) {
            //check if room is already in roomlist
            int index = this.isValidRoom(id);
            if (index == -1) //if room does not exist then add room into list with empty schedule
            {
                roomlist.add(new Room(id));
            } //if it is then throw exception with message room is already in the list
            else {
                System.out.println("Room already exist!");
            }
        } else {
            System.out.println("Invalid room id!");
        }

    }

    public ArrayList<Room> getRoomList() {
    return roomlist;
    }

    public void addCourses(Course course, int room_id) {
        //check if room is valid
        int index = isValidRoom(room_id);
        if (index >= 0) {
            //if valid then get the index of the room
            if (roomlist.get(index).addCourses(course)) //if addCourse is successful
            {
            
                courselist.add(course);
            } //track it in courselist
        } else {
            //if not valid output error message
            throw new IllegalArgumentException("Room is invalid. Unable to add course.");
        }

    }

    public int isValidRoom(int room_id) {
        //room is valid if it the room id is in the roomlist
        for (Room r : roomlist) {
            if (r.getNum() == room_id) {
                return roomlist.indexOf(r);
            } //return true if it is in the ArrayList

        }

        return -1; //if it is not in the ArrayList return -1

    }

    public void printRoomSchedule(int room_id) {
        //check if room is valid
        int index = isValidRoom(room_id);
        if (index >= 0) {
            //if valid then print schedule
            roomlist.get(index).printSchedule();
        } else {
            //if not valid output error message
            System.out.println("Room is invalid. Unable to Print Schedule.");
        }
    }

    public void printAvailableRoom() {
        System.out.println("------------------------------------------");
        System.out.println("List of Available Rooms: ");

        for (Room r : roomlist) {
            System.out.println(r);
        }

        System.out.println("------------------------------------------");
    }

    //Print all the courses in the timeslot
    //O(n^2)
    public void printCoursesInTimeSlot(int starttime, int endtime) {
        //create a list to store the courses
        ArrayList<Course> list = new ArrayList<Course>();

        //for each room r in the roomlist
        for (Room r : roomlist) {
            //check their schedule and get all the courses in the timeslot 
            Course c = r.getSchedule().getCourseInTime(starttime, endtime);
            if (c != null) {
                list.add(c);
            }

        }

        int count = 1;
        System.out.println("------------------------------------------");
        System.out.println("List of course in this timeslot: " + starttime + " to " + endtime);
        for (Course c : list) {
            System.out.println(Integer.toString(count) + ") " + c);
            count++;
        }
        System.out.println("------------------------------------------");
    }

    public void printAvailableCourses() {

        int count = 1;
        System.out.println("------------------------------------------");
        System.out.println("List of Available Courses: ");
        for (Course c : courselist) {
            System.out.println(c);
            count++;
        }
        System.out.println("------------------------------------------");
    }

    public Course getCourse(String course_name) {
        for (Course c : courselist) {
            if (c.getCourseName().equals(course_name)) {
                return c;
            }
        }

        return null;
    }

    public Course getCourse2(int index)
    {
        return courselist.get(index);
    }
    
    
    public void removeCourse2(int course_index)
    {
        Course c = courselist.get(course_index);
        
        if (c.getStudentList().isEmpty())
        {
            
            int roomnum = c.getRoomNum();
            int room_index = this.isValidRoom(roomnum);
            roomlist.get(room_index).getSchedule().removeCourse(c);
            courselist.remove(course_index);
            System.out.println("Course " + c.getCourseName() + " removed!");
        }
        else
        {
            System.out.println("Someone is scheduled in " + c.getCourseName() + ". Unable to remove course.");
        }
    }
    public void removeCourse(String course_name) {
        //check if course_name is valid
       // if (isValidCourse(course_name)) {

            int index = -1;
            int roomnum = -1;
            for (Course c : courselist) {
                if (c.getCourseName().equals(course_name)) {
                    //check if student list is empty
                    if (c.getStudentList().isEmpty()) {
                        index = courselist.indexOf(c);
                        roomnum = c.getRoomNum();
                    } else {
                        System.out.println("Someone is scheduled in " + c.getCourseName() + ". Unable to remove course.");
                    }

                }
            }

            if (index != -1) {
                //remove course from room schedule
                int room_index = this.isValidRoom(roomnum);
                roomlist.get(room_index).getSchedule().removeCourse(courselist.get(index));

                //remove course
                courselist.remove(index);

                System.out.println("Course " + course_name + " removed!");
            }
         else {
            throw new IllegalArgumentException("Course doesn't exist!");
        }
    }

    public void removeAllCourse() {
        ArrayList<String> c_names = new ArrayList<String>();
        for (Course c : courselist) {
            c_names.add(c.getCourseName());

        }

        for (String s : c_names) {
            removeCourse(s);
        }
    }

    public void printAllRoomSchedule() {
        for (Room r : roomlist) {
            r.printSchedule();
        }
    }

    public boolean isValidCourse(String c_name) {
        for (Course c : courselist) {
            if (c.getCourseName().equals(c_name)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Course> getCourseList() {
        return courselist;
    }
}
