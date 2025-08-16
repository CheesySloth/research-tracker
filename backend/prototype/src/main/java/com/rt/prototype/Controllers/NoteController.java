package com.rt.prototype.Controllers;

import org.springframework.web.bind.annotation.*;

import com.rt.prototype.Entities.Note;
import com.rt.prototype.Services.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // --- CRUD ---

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        return noteService.updateNote(id, noteDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }

    // --- Search endpoints (examples) ---

    @GetMapping("/search/author")
    public List<Note> getNotesByAuthor(@RequestParam String author) {
        return noteService.findByAuthor(author);
    }

    @GetMapping("/search/title")
    public List<Note> searchByTitle(@RequestParam String title) {
        return noteService.findByTitleContaining(title);
    }

    @GetMapping("/search/content")
    public List<Note> searchByContent(@RequestParam String content) {
        return noteService.findByContentContaining(content);
    }

    @GetMapping("/search/category")
    public List<Note> searchByCategory(@RequestParam String category) {
        return noteService.findByCategory(category);
    }

    @GetMapping("/search/tags")
    public List<Note> searchByTags(@RequestParam String tag) {
        return noteService.findByTagsContaining(tag);
    }

    // Complex search example
    @GetMapping("/search/advanced")
    public List<Note> advancedSearch(
            @RequestParam String author,
            @RequestParam String category,
            @RequestParam String tag,
            @RequestParam String title,
            @RequestParam String content) {
        return noteService.findByAuthorAndCategoryAndTagsAndTitleAndContent(
                author, category, tag, title, content);
    }
}