package applications.Notes.controllers;

import applications.Notes.models.NoteModel;
import applications.Notes.views.NoteView;

public class NoteController {

    private NoteView noteView;
    private NoteModel noteModel;

    public NoteController(NoteView noteView, NoteModel noteModel) {

        this.noteView = noteView;
        this.noteModel = noteModel;

    }
}
