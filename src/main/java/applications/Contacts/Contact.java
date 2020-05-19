package applications.Contacts;

    /** Contact est la classe représentant un personne dans le répértoir du téléphone
     *
     * Un contact est représenté par les informations suivantes:
     *
     * un prénom
     * un nom
     * une ville
     * un numéro de telephone
     * un email
     *
     * Un contact pourra être modifier.
     *
     */

    public class Contact {

        private String firstName;
        private String lastName;
        private String city;
        private String phoneNumber;
        private String email;

        public Contact (){}

        public Contact(String firstName, String lastName, String city, String phoneNumber, String email) {
            SetFirstName(firstName);
            setLastName(lastName);
            setCity(city);
            setPhoneNumber(phoneNumber);
            setEmail(email);
        }

        public String getFirstName() {
            return firstName;
        }

        public void SetFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void modifiyContact(String prenom, String nom){

        }

}
