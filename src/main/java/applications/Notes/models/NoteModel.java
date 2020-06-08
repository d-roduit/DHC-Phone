package applications.Notes.models;

public class NoteModel {

    private String noteTitle;
    private String noteCreationDate;
    private String content;
//    private String [] contacts;
//    private String [] pictures;

    public NoteModel() {

    }

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

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String title) {
        this.noteTitle = title;
    }

    public String getNoteCreationDate() {
        return noteCreationDate;
    }

    public void setNoteCreationDate(String creationDate) {
        this.noteCreationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

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
