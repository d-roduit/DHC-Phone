package applications.Notes.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>FolderModel is the class that represents a folder with its properties and its methods.</b>
 *
 * @author Cathy Gay
 */
public class FolderModel {

    /**
     * The folder title.
     */
    private String folderTitle;

    /**
     * The folder creation date.
     */
    private String folderCreationDate;

    /**
     * The folder notes list.
     *
     * @see List
     * @see NoteModel
     */
    private List<NoteModel> notes;

    /**
     * FolderModel empty constructor.
     */
    public FolderModel() {

    }

    /**
     * FolderModel constructor.
     *
     * @param folderTitle
     *              The folder title.
     * @param folderCreationDate
     *              The folder creation date.
     * @param noteModels
     *              The folder list of NoteModel.
     */
    public FolderModel(String folderTitle, String folderCreationDate, List<NoteModel> noteModels) {
        this.folderTitle = folderTitle;
        this.folderCreationDate = folderCreationDate;
        this.notes = noteModels;
    }

    /**
     * Returns a new note.
     * <p>
     *     The new note is created with a title, a creation date that is the current date, and en empty content.
     *     The new note is then added to the list of NoteModel.
     * </p>
     *
     * @param noteTitle
     *              The note title.
     * @return The new NoteModel.
     *
     * @see NoteModel
     * @see Date
     * @see SimpleDateFormat
     */
    public NoteModel addNote(String noteTitle) {
        System.out.println("Add note clicked");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        NoteModel note = new NoteModel(noteTitle, dateFormat.format(date), "", "");
        notes.add(note);

        return note;
    }

    /**
     * Delete a note.
     * <p>
     *     The note is removed from the list of NoteModel.
     * </p>
     *
     * @param noteModel
     *              The NoteModel that should be deleted.
     *
     * @see NoteModel
     */
    public void deleteNote(NoteModel noteModel) {
        System.out.println("Delete note clicked : " + noteModel.getNoteTitle());
        notes.remove(noteModel);
    }

    public void modifyFolder() {

    }

    /**
     * Returns the folder title.
     *
     * @return The folder title.
     */
    public String getFolderTitle() {
        return folderTitle;
    }

    /**
     * Sets the folder title.
     *
     * @param folderTitle
     *              The folder title.
     */
    public void setFolderTitle(String folderTitle) {
        this.folderTitle = folderTitle;
    }

    /**
     * Returns the folder creation date.
     *
     * @return The folder creation date.
     */
    public String getFolderCreationDate() {
        return folderCreationDate;
    }

    /**
     * Sets the folder creation date.
     *
     * @param folderCreationDate
     *              The folder creation date.
     */
    public void setFolderCreationDate(String folderCreationDate) {
        this.folderCreationDate = folderCreationDate;
    }

    /**
     * Returns the list of NoteModel.
     *
     * @return The list of NoteModel.
     */
    public List<NoteModel> getNotes() {
        return notes;
    }

    /**
     * Sets the list of NoteModel.
     *
     * @param noteModels
     *              The list of NoteModel.
     */
    public void setNotes(List<NoteModel> noteModels) {
        this.notes = noteModels;
    }
}
