package collegeadmissions;

import java.util.*;

/**
 *
 * @author Michael Levet
 * @date 01/10/2016
 */
public class CollegeAdmissions {

    public static void main(String[] args) {
        System.out.println("Executing the SODA Algorithm:");
        sodaMakeMatches();
        
    }
    
    public static void sodaMakeMatches() {
        ArrayList<Student> students = new ArrayList<Student>();//students(1,2,3,4,5)
        ArrayList<College> colleges = new ArrayList<College>();//students(A,B,C)
        
        College c0 = new College("A", 2);
        College c1 = new College("B", 1);
        College c2 = new College("C", 1);
        colleges.add(c0);
        colleges.add(c1);
        colleges.add(c2);
        
        for (int i = 0; i < 5; i++) {
            Student s = new Student((i + 1));//lees gegevens uit database en geef mee met constructor
            s.setStatus(Status.Voorlopig);
            students.add(s);
            
            c0.insertLeastPreferredStudent(s);//A prefers (1,2,3,4,5)
            c1.insertStudent(s, 0);//B prefers (5,4,3,2,1)
            
            for (College c : colleges) {
                s.insertLeastPreferredCollege(c);//1,2,3,4,5 prefers (A,B,C)
            }
        }
        //C prefers (1,2) 
        c2.insertLeastPreferredStudent(students.get(0));
        c2.insertLeastPreferredStudent(students.get(1));
        
        MatchMaker matchMaker = new MatchMaker(students, colleges);
        matchMaker.sodaMakeMatches();
        
        
        
        for (Student s : students) {
            s.setStatus(Status.Definitief);
            if (s.getMatch() != null)
                System.out.println(s.getNaam() + " is matched with " + s.getMatch().getName());
            else
                System.out.println(s.getNaam() + " is not matched with a college");
        }
        
        for (College c : colleges) {
            ArrayList<Student> matchesToList = new ArrayList<Student>(c.getMatches());
            ArrayList<String> nameMatchesToList = new ArrayList<String>();
            for (Student name: matchesToList){
                nameMatchesToList.add(name.getNaam());
            }
            System.out.println(c.getName() + " is matched with " + nameMatchesToList);
        }
    }
    
}
