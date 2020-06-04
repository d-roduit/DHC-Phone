package applications.Notes.models;

public class FolderModel {

    private String folderTitle;
    private String folderCreationDate;
//    private NoteModel[] notes;

    public FolderModel() {

    }

    public void addNote() {
        System.out.println("Add note clicked");
    }

    public void deleteNote() {

    }

    public void modifyFolder() {

    }

    public String getFolderTitle() {
        return folderTitle;
    }

    public void setFolderTitle(String folderTitle) {
        this.folderTitle = folderTitle;
    }

    public String getFolderCreationDate() {
        return folderCreationDate;
    }

    public void setFolderCreationDate(String folderCreationDate) {
        this.folderCreationDate = folderCreationDate;
    }
//
//    public NoteModel[] getNotes() {
//        return notes;
//    }
//
//    public void setNotes(NoteModel[] notes) {
//        this.notes = notes;
//    }
}
