
package collegeadmissions;

import java.util.List;
/**
 * This class accepts a List<Student> and List<College>, and allows
 * for the Student-Optimal Deferred Acceptance and College-Optimal 
 * Deferred Acceptance algorithms to be executed on the inputs
 * 
 * @author Michael Levet
 * @date 01/10/2016
 */

public class MatchMaker {
    private List<Student> students;
    private List<College> colleges;
     
    public MatchMaker(List<Student> students, List<College> colleges){
        this.students = students;
        this.colleges = colleges;
    }
     
    public void sodaMakeMatches(){
        boolean newProposalMade = false;
         
        do{newProposalMade = false;
            for(Student s : students){
                if(s.canMakeProposal()){
                   newProposalMade = s.makeProposals();
                }
            }
        }while(newProposalMade);
    }
     
    
}
