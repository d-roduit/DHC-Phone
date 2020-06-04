package applications.Notes.models;

public class NoteModel {

    private String noteTitle;
    private String noteCreationDate;
    private String content;
    private String [] contacts;
    private String [] pictures;

    public NoteModel() {

    }

    public NoteModel(String noteTitle, String noteCreationDate, String content, String [] contacts, String [] pictures) {
        setTitle(noteTitle);
        setCreationDate(noteCreationDate);
        setNote(content);
        setContacts(contacts);
        setPictures(pictures);
    }

    public void modifyNote() {

    }

    public void addPicture() {

    }

    public void linkToContact() {

    }

    public String getTitle() {
        return noteTitle;
    }

    public void setTitle(String title) {
        this.noteTitle = title;
    }

    public String getCreationDate() {
        return noteCreationDate;
    }

    public void setCreationDate(String creationDate) {
        this.noteCreationDate = creationDate;
    }

    public String getNote() {
        return content;
    }

    public void setNote(String note) {
        this.content = note;
    }

    public String[] getContacts() {
        return contacts;
    }

    public void setContacts(String[] contacts) {
        this.contacts = contacts;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }
}
