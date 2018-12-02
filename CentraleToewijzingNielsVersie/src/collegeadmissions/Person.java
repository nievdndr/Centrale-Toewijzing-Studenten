
package collegeadmissions;

/**
 *
 * @author arthu
 */
public class Person {
    private int rijksregisternummer;
    private String naam;
    private String voornaam;
    private String straatnaam;
    private int huisnummer;
    private String gemeente;
    private int postcode;

    public Person(int rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
        this.naam = "Onbekend";
        this.straatnaam = "Onbekend";
        this.huisnummer = 0;
        this.gemeente = "Onbekend";
        this.postcode = 0;
    }

    
    public Person(int rijksregisternummer, String naam, String straatnaam, int huisnummer, String gemeente, int postcode) {
        this.rijksregisternummer = rijksregisternummer;
        this.naam = naam;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public int getRijksregisternummer() {
        return rijksregisternummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public String getGemeente() {
        return gemeente;
    }

    public int getPostcode() {
        return postcode;
    }
    
    public String toString(){
        return "Rijksregisternummer: " + this.rijksregisternummer
        + "\nNaam: " + this.naam
        + "\nVoornaam: " + this.voornaam
        + "\nStraatnaam: " + this.straatnaam
        + "\nHuisnummer: " + this.huisnummer
        + "\nGemeente: " + this.gemeente
        + "\nPostcode: " + this.postcode;
    }
    
    public boolean equals (Person otherPerson){
        return this.rijksregisternummer == otherPerson.getRijksregisternummer();
    }
    
}
