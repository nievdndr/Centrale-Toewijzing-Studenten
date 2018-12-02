package collegeadmissions;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class models a Student in the College-Admissions Problem. The Student
 * class is designed to allow instances to propose in the Student-Optimal
 * Deferred Acceptance algorithm, or respond to College proposals in the
 * College-Optimal Deferred Acceptance algorithm.
 *
 * @author Michael Levet
 * @date 01/10/2016
 */
public class Student extends Person {

    private ArrayList<College> preferences;
    private College match;
    private int telefoonnummer;
    private Status status;
    private int toewijzingsAanvraagNummer;

    public Student(int rijksregisternummer) {
        super(rijksregisternummer);
        this.preferences = new ArrayList<College>();
        this.status = Status.Ontwerp;
        Random rand = new Random();
        toewijzingsAanvraagNummer = rand.nextInt(10000)+ 1;//willekeurig nummer tussen 1 en 10000
    }

    
    /**
     * @param name The name of this Student
     */
    public Student(int telefoonnummer, int rijksregisternummer, String naam, String straatnaam, int huisnummer, String gemeente, int postcode) {
        super(rijksregisternummer, naam, straatnaam, huisnummer, gemeente, postcode);
        this.telefoonnummer = telefoonnummer;
        this.preferences = new ArrayList<College>();
        this.status = Status.Ontwerp;
        Random rand = new Random();
        toewijzingsAanvraagNummer = rand.nextInt(10000)+ 1;//willekeurig nummer tussen 1 en 10000
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getToewijzingsAanvraagNummer() {
        return toewijzingsAanvraagNummer;
    }

    public void setPreferences(ArrayList<College> preferences) {
        this.preferences = preferences;
    }
    
    
    

    /**
     *
     * @param c The College to insert
     * @return true if c was successfully inserted, false if c was present in
     * the preference List
     */
    public boolean insertLeastPreferredCollege(College c) {
        if (c == null || this.preferences.contains(c)) {
            return false;
        }

        return this.preferences.add(c);
    }

    /**
     *
     * @param c The College to insert
     * @param preferenceRanking The position in the preference List to insert c
     * @return true if c was successfully inserted, false if c was already
     * present in the preference List
     */
    public boolean insertCollege(College c, int preferenceRanking) {
        if (c == null || this.preferences.contains(c)) {
            return false;
        }

        if (preferenceRanking > this.preferences.size()) {
            return this.preferences.add(c);
        }

        this.preferences.add(preferenceRanking, c);
        return true;
    }

    /**
     * Determines whether this Student is unmatched and has Colleges to which it
     * has not proposed and with which it is willing to match.
     *
     * @return true if this Student can make proposals, false otherwise.
     */
    public boolean canMakeProposal() {
        return this.match == null && this.preferences.size() > 0;
    }

    /**
     * This method is used in the Student-Optimal Deferred Acceptance algorithm.
     * The Student proceeds by selecting its most preferred College to which it
     * has not already proposed, and makes a proposal. The method terminates
     * when the Student is either matched or runs out of Colleges to which it
     * can propose.
     *
     * @return true if a College accepts a proposal from this Student, false
     * otherwise
     */
    public boolean makeProposals() {
        College temp = null;

        do {
            temp = this.preferences.remove(0);
            System.out.println(super.getNaam() + " proposed to " + temp.getName());
            if (temp.acceptProposal(this)) {
                this.match = temp;
                System.out.println(temp.getName() + " accepted the proposal from " + super.getNaam());
                return true;
            }

            System.out.println(temp.getName() + " rejected the proposal from " + super.getNaam());

        } while (temp != null && this.preferences.size() > 0);

        return false;
    }

    /**
     * This method unmatches this Student from its current mate. We use this
     * method in the College-Optimal Deferred Acceptance algorithm.
     */
    public void unmatch() {
        this.match = null;
    }

    /**
     * @return College this Student's current mate
     */
    public College getMatch() {
        return this.match;
    }

    @Override
    public boolean equals(Object otherObject) {
        boolean isEqual = false;
        if ((otherObject != null) && (otherObject instanceof Student)) {
            Student otherStudent = (Student) otherObject;
            isEqual = this.getRijksregisternummer() == otherStudent.getRijksregisternummer();//alternatief voor if-statement
        }
        return isEqual;
    }

    /**
     * @return String A String representation of this Student
     */
    @Override
    public String toString() {
        return "Rijksregisternummer: " + this.getRijksregisternummer()
                + "\nNaam: " + super.getNaam()
                + "\nVoornaam: " + super.getVoornaam()
                + "\nStraatnaam: " + super.getStraatnaam()
                + "\nHuisnummer: " + super.getHuisnummer()
                + "\nGemeente: " + super.getGemeente()
                + "\nPostcode: " + super.getGemeente()
                + "\nTelefoonnummer: " + this.telefoonnummer
                + "\nToegewezen school: " + this.match.getName()
                + "\nStatus: " + this.status
                + "\nToewijzingsaanvraagnummer: " + this.toewijzingsAanvraagNummer;
    }

}
