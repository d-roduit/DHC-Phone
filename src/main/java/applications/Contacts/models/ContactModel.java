package applications.Contacts.models;

/**
 *
 * <b>ContactModel is the class which represents a contact in your phone directory.</b>
 *
 * @author Henrick Neads
 *
 */

public class ContactModel {

    /**
     * First name of the contact.It can be modified.
     *
     * @see applications.Contacts.Contact#setFirstName(String)
     * @see applications.Contacts.Contact#getFirstName()
     *
     */
    private String firstName;

    /**
     * Last name of the contact. It can be modified.
     *
     * @see applications.Contacts.Contact#setLastName(String)
     * @see applications.Contacts.Contact#getLastName()
     *
     */
    private String lastName;

    /**
     * City where the contact lives. It can be modified.
     *
     * @see applications.Contacts.Contact#setCity(String)
     * @see applications.Contacts.Contact#getCity()
     *
     */
    private String city;

    /**
     * Phone number of the contact. It can be modified.
     *
     * @see applications.Contacts.Contact#setEmail(String)
     * @see applications.Contacts.Contact#getPhoneNumber()
     */
    private String phoneNumber;

    /**
     * Email of the contact. It can be modified.
     *
     * @see applications.Contacts.Contact#setEmail(String)
     * @see applications.Contacts.Contact#getEmail()
     *
     */
    private String email;

    /**
     * The empty constructor is used by the object mapper so it can read the information from a Json file containing
     * the information about the contacts.
     *
     */

    public ContactModel(){}

    /**
     * ContactModel Constructor
     *
     * <p>When the contact is created, the getter and setter will be used in order to
     * collect the information regarding the parameters below in the file containing the contact information</p>
     *
     *
     * @param firstName
     *          First name of the contact
     * @param lastName
     *          Last name of the contact
     * @param city
     *          City of the contact
     * @param phoneNumber
     *          Phone number of the contact
     * @param email
     *          Email of the contact
     *
     * @see applications.Contacts.models.ContactModel#firstName
     * @see applications.Contacts.models.ContactModel#lastName
     * @see applications.Contacts.models.ContactModel#city
     * @see applications.Contacts.models.ContactModel#phoneNumber
     * @see applications.Contacts.models.ContactModel#email
     */

    public ContactModel(String firstName, String lastName, String city, String phoneNumber, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setCity(city);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    /**
     * Returns the First name of the contact as a string.
     *
     * @return first name of contact.
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * updates the first name of the contact.
     *
     * @param firstName
     *      the new first name of the contact.
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the contact as a string.
     *
     * @return last name of contact.
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Updates the last name of the contact.
     *
     * @param lastName
     *      the new last name of the contact.
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the city where the contact lives as a string.
     *
     * @return city of the contact.
     */

    public String getCity() {
        return city;
    }

    /**
     * Updates the city of the contact
     *
     * @param city
     *      The new city where the contact lives.
     */

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the phone number of the contact as a string.
     *
     * @return phone number of contact.
     */

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the phone number of the contact.
     *
     * @param phoneNumber
     *      The new phone number of the contact.
     */

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email of the contact as a string.
     *
     * @return email of contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the email of the contact.
     *
     * @param email
     *      The new email of the contact.
     */

    public void setEmail(String email) {
        this.email = email;
    }

}