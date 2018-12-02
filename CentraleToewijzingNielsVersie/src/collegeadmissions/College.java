
package collegeadmissions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
 
/**
 * This class models a College in the College-Admissions Problem.
 * The College class is designed to allow instances to propose in the
 * College-Optimal Deferred Acceptance algorithm, or respond to Student 
 * proposals in the Student-Optimal Deferred Acceptance algorithm.
 * 
 * @author Michael Levet
 * @date 01/10/2016
 */

public class College {
    private PriorityQueue<Student> matches;
    private ArrayList<Student> preferences;
    private String name;
    private int capacity;
    private String straatnaam;
    private int huisnummer;
    private String gemeente;
    private int postcode;
     
    /**
     * @param name The name of this College
     * @param capacity The number of Students this College can admit
     */
    public College(String name, int capacity){
        this.name = name;
        this.preferences = new ArrayList<Student>();
        this.capacity = capacity;
 
        //ranks Students based on their positions in the preferences List
        //this Comparator ensures that the PriorityQueue is stores Students
        //in order of increasing preference
        Comparator<Student> ranking = new Comparator<Student>(){
             
            public int compare(Student s1, Student s2){
                int indexOne = College.this.preferences.indexOf(s1);
                int indexTwo = College.this.preferences.indexOf(s2);
                 
                return indexTwo - indexOne;
            }
        };
         
        this.matches = new PriorityQueue(capacity, ranking);
         
    }

    public String getName() {
        return name;
    }
    
    
     
    /**
     * 
     * @param s The Student to insert 
     * @return true if s was successfully inserted, false if s was present in the preference List
     */
    public boolean insertLeastPreferredStudent(Student s){
        if(s == null || this.preferences.contains(s)){
            return false;
        }
         
        return this.preferences.add(s);
    }
     
    /**
     * 
     * @param s The Student to insert
     * @param preferenceRanking The order in the preferences List to insert s
     * @return true if s was successfully inserted, false if s was present in the preference List
     */
    public boolean insertStudent(Student s, int preferenceRanking){
        if(s == null || this.preferences.contains(s)){
            return false;
        }
         
        if(preferenceRanking >= this.preferences.size()){
            return this.preferences.add(s);
        }
         
        this.preferences.add(preferenceRanking, s);
        return true;
    }
     
    
     
     
    /**
     * This method is used in the Student-Optimal Deferred Acceptance algorithm,
     * allowing the College to process a Student's proposal. The College may accept
     * the proposal, unmatching its current mate if necessary; or reject the proposal.
     * 
     * @param other The Student proposing to this College
     * @return true iff this College accepted the proposal
     */
    public boolean acceptProposal(Student other){
        int index = this.preferences.indexOf(other);
        if(index == -1){
            return false;
        }
         
        if(this.matches.size() == this.capacity){
            int indexOther = this.preferences.indexOf(this.matches.peek());
            if(index < indexOther){
                Student revoked = this.matches.poll();
                System.out.println(this.name + " unmatched from " + revoked.getNaam());
                revoked.unmatch();
                this.matches.add(other);
                return true;
            }
             
            return false;
        }
         
        this.matches.add(other);
        return true;
    }
     
    /**
     * @return PriorityQueue<Student> The admitted Students for this College
     */
    public PriorityQueue<Student> getMatches(){ 
        return this.matches;
    }
     
    /**
     * @return A String representation of this College
     */
    @Override
    public String toString(){
        return "\nNaam: " + this.name
        + "\nStraatnaam: " + this.straatnaam
        + "\nHuisnummer: " + this.huisnummer
        + "\nGemeente: " + this.gemeente
        + "\nPostcode: " + this.postcode
        + "\nCapaciteit: " +this.capacity;
    }
    
}
