package applications.Notes.models;

import java.util.ArrayList;
import java.util.List;

public class FolderModel {

    private String folderTitle;
    private String folderCreationDate;
    private List<NoteModel> notes;

    public FolderModel() {

    }

    public FolderModel(String folderTitle, String folderCreationDate, List<NoteModel> noteModels) {
        this.folderTitle = folderTitle;
        this.folderCreationDate = folderCreationDate;
        this.notes = noteModels;
    }

    public NoteModel addNote(String noteTitle) {
        System.out.println("Add note clicked");
        NoteModel note = new NoteModel(noteTitle, "", "");
        notes.add(note);

        return note;
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

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> noteModels) {
        this.notes = noteModels;
    }
}
