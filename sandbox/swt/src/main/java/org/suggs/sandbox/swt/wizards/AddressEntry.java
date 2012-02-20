/*
 * AddressEntry.java created on 22 Oct 2008 07:23:50 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.wizards;

/**
 * Address Entry bean object
 * 
 * @author suggitpe
 * @version 1.0 22 Oct 2008
 */
public class AddressEntry {

    private String firstName;
    private String lastName;
    private String emailAddress;

    /**
     * Constructs a new instance.
     */
    public AddressEntry() {}

    /**
     * Constructs a new instance.
     * 
     * @param aFirstName
     * @param aLastName
     * @param aEmailAddress
     */
    public AddressEntry( String aFirstName, String aLastName, String aEmailAddress ) {
        firstName = aFirstName;
        lastName = aLastName;
        emailAddress = aEmailAddress;
    }

    /**
     * Returns the value of firstName.
     * 
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName field to the specified value.
     * 
     * @param aFirstName
     *            The firstName to set.
     */
    public void setFirstName( String aFirstName ) {
        firstName = aFirstName;
    }

    /**
     * Returns the value of lastName.
     * 
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName field to the specified value.
     * 
     * @param aLastName
     *            The lastName to set.
     */
    public void setLastName( String aLastName ) {
        lastName = aLastName;
    }

    /**
     * Returns the value of emailAddress.
     * 
     * @return Returns the emailAddress.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the emailAddress field to the specified value.
     * 
     * @param aEmailAddress
     *            The emailAddress to set.
     */
    public void setEmailAddress( String aEmailAddress ) {
        emailAddress = aEmailAddress;
    }

}
