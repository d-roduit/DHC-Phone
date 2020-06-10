package applications.Notes.models;

/**
 * <b>NoteModel is the class that represents a note with its properties and its methods.</b>
 *
 * @author Cathy Gay
 */
public class NoteModel {

    /**
     * The note title.
     */
    private String noteTitle;

    /**
     * The note creation date.
     */
    private String noteCreationDate;

    /**
     * The note content.
     */
    private String content;
//    private String [] contacts;
//    private String [] pictures;

    /**
     * NoteModel empty constructor.
     */
    public NoteModel() {

    }

    /**
     * NoteModel constructor.
     *
     * @param noteTitle
     *              The note title.
     * @param noteCreationDate
     *              The note creation date.
     * @param content
     *              The note content.
     *
     * @see NoteModel#setNoteTitle(String)
     * @see NoteModel#setNoteCreationDate(String)
     * @see NoteModel#setContent(String)
     */
    public NoteModel(String noteTitle, String noteCreationDate, String content) {
        setNoteTitle(noteTitle);
        setNoteCreationDate(noteCreationDate);
        setContent(content);
//        setContacts(contacts);
//        setPictures(pictures);
    }

    public void modifyNote() {

    }

    public void addPicture() {

    }

    public void linkToContact() {

    }

    /**
     * Returns the note title.
     *
     * @return The note title.
     */
    public String getNoteTitle() {
        return noteTitle;
    }

    /**
     * Sets the note title.
     *
     * @param title
     *              The note title.
     */
    public void setNoteTitle(String title) {
        this.noteTitle = title;
    }

    /**
     * Returns the note creation date.
     *
     * @return The note creation date.
     */
    public String getNoteCreationDate() {
        return noteCreationDate;
    }

    /**
     * Sets the note creation date.
     *
     * @param creationDate
     *              The note creation date.
     */
    public void setNoteCreationDate(String creationDate) {
        this.noteCreationDate = creationDate;
    }

    /**
     * Returns the note content.
     *
     * @return The note content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the note content.
     *
     * @param note
     *              The note content.
     */
    public void setContent(String note) {
        this.content = note;
    }

//    public String[] getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(String[] contacts) {
//        this.contacts = contacts;
//    }
//
//    public String[] getPictures() {
//        return pictures;
//    }
//
//    public void setPictures(String[] pictures) {
//        this.pictures = pictures;
//    }
}
