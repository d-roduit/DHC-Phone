package applications.Contacts.models;

/**
 *
 * <b>ContactModel is the class which represents a contact in your phone directory.</b>
 *
 * @author Henrick Neads
 *
 */

public class Contact implements Comparable<Contact> {

    @Override
    public int compareTo(Contact contact) {
        return this.lastName.compareTo(contact.lastName);
    }

    /**
     * First name of the contact.It can be modified.
     *
     * @see applications.Contacts.models.Contact#setFirstName(String)
     * @see applications.Contacts.models.Contact#getFirstName()
     *
     */
    private String firstName;

    /**
     * Last name of the contact. It can be modified.
     *
     * @see applications.Contacts.models.Contact#setLastName(String)
     * @see applications.Contacts.models.Contact#getLastName()
     *
     */
    private String lastName;

    /**
     * City where the contact lives. It can be modified.
     *
     * @see applications.Contacts.models.Contact#setCity(String)
     * @see applications.Contacts.models.Contact#getCity()
     *
     */
    private String city;

    /**
     * Phone number of the contact. It can be modified.
     *
     * @see applications.Contacts.models.Contact#setEmail(String)
     * @see applications.Contacts.models.Contact#getPhoneNumber()
     */
    private String phoneNumber;

    /**
     * Email of the contact. It can be modified.
     *
     * @see applications.Contacts.models.Contact#setEmail(String)
     * @see applications.Contacts.models.Contact#getEmail()
     *
     */
    private String email;

    /**
     * Path of the Photo of the contact. it can be modified.
     *
     * @see applications.Contacts.models.Contact#setPhotoPath(String)
     * @see applications.Contacts.models.Contact#getPhotoPath()
     */

    private String photoPath;

    /**
     * The empty constructor is used by the object mapper so it can read the information from a Json file containing
     * the information about the contacts.
     *
     */

    public Contact(){}

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
     * @see applications.Contacts.models.Contact#firstName
     * @see applications.Contacts.models.Contact#lastName
     * @see applications.Contacts.models.Contact#city
     * @see applications.Contacts.models.Contact#phoneNumber
     * @see applications.Contacts.models.Contact#email
     */

    public Contact(String firstName, String lastName, String city, String phoneNumber, String email,String photoPath) {
        setFirstName(firstName);
        setLastName(lastName);
        setCity(city);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setPhotoPath(photoPath);
    }

    /**
     * Returns the First name of the contact as a string.
     *
     * @return first name of contact.
     */

    public String getFirstName() {
        return this.firstName;
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
        return this.lastName;
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
        return this.city;
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
        return this.phoneNumber;
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
        return this.email;
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

    /**
     * Returns the path of the picture of the contact.
     *
     * @return photoPath
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * Update the path of the picture of the contact.
     *
     * @param photoPath
     *      the new path of the picture of the contact.
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}