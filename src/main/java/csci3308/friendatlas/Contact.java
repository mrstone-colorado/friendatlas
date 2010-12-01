package csci3308.friendatlas;

import javax.swing.text.html.HTML;

/**
 * Created by IntelliJ IDEA.
 * User: correaj
 * Date: Nov 27, 2010
 * Time: 8:24:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class Contact {

    public String name = "Test Name";
    public String firstName;
    public String lastName;
    private String address;
    private String city;
    private String state;
    private int zipCode;

    public Contact()
    {
        firstName = null;
    }

    public Contact( String f, String l, String ad, String c, String s, int z)
    {
        firstName = f;
        lastName = l;
        address = ad;
        city = c;
        state = s;
        zipCode = z;
    }

    public void setValues ( String first, String last, String add, String cit, String st, int zip )
    {
        firstName = first;
        lastName = last;
        address = add;
        city = cit;
        state = st;
        zipCode = zip;
    } // end void   L

    public String getName(){
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public int getZip()
    {
        return zipCode;
    }
}