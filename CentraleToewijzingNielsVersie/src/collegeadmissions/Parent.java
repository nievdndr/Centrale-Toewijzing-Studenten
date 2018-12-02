
package collegeadmissions;

/**
 *
 * @author arthu
 */

public class Parent extends Person{
    private String email;

    public Parent(String email, int rijksregisternummer, String naam, String straatnaam, int huisnummer, String gemeente, int postcode) {
        super(rijksregisternummer, naam, straatnaam, huisnummer, gemeente, postcode);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public boolean equals(Object otherObject){
        boolean isEqual = false;
        if ((otherObject != null) && (otherObject instanceof Parent)){
            Parent otherParent = (Parent) otherObject;
            isEqual = this.getRijksregisternummer() == otherParent.getRijksregisternummer();//alternatief voor if-statement
            }
        return isEqual;
    }
    
    @Override
    public String toString(){
        return "Rijksregisternummer: " + this.getRijksregisternummer()
        + "\nNaam: " + super.getNaam()
        + "\nVoornaam: " + super.getVoornaam()
        + "\nStraatnaam: " + super.getStraatnaam()
        + "\nHuisnummer: " + super.getHuisnummer()
        + "\nGemeente: " + super.getGemeente()
        + "\nPostcode: " + super.getGemeente()
        + "\nEmail: " + this.email;
    }
    
    
    
    
    
}
