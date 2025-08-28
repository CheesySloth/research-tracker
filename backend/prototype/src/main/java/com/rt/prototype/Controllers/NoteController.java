package com.rt.prototype.Controllers;

import org.springframework.web.bind.annotation.*;

import com.rt.prototype.Entities.Note;
import com.rt.prototype.Services.NoteService;

import java.util.List;
import java.util.Optional;

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
    public Note getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable("id") Long id, @RequestBody Note noteDetails) {
        return noteService.updateNote(id, noteDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable("id") Long id) {
        noteService.deleteNoteById(id);
    }

    // --- Search endpoints (examples) ---

    @GetMapping("/search/author")
    public List<Note> getNotesByAuthor(@RequestParam("author") String author) {
        return noteService.findByAuthorContaining(author);
    }

    @GetMapping("/search/title")
    public List<Note> searchByTitle(@RequestParam("title") String title) {
        return noteService.findByTitleContaining(title);
    }

    @GetMapping("/search/content")
    public List<Note> searchByContent(@RequestParam("content") String content) {
        return noteService.findByContentContaining(content);
    }

    @GetMapping("/search/category")
    public List<Note> searchByCategory(@RequestParam("category") String category) {
        return noteService.findByCategory(category);
    }

    @GetMapping("/search/tags")
    public List<Note> searchByTags(@RequestParam("tag") String tag) {
        return noteService.findByTagsContaining(tag);
    }

    // Complex search example
    @GetMapping("/search/advanced")
    public List<Note> advancedSearch(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "content", required = false) String content) {
        return noteService.findByAuthorAndCategoryAndTagsAndTitleAndContent(
                Optional.ofNullable(author),
                Optional.ofNullable(category),
                Optional.ofNullable(tag),
                Optional.ofNullable(title),
                Optional.ofNullable(content));
    }
}