package com.rt.prototype.Services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rt.prototype.Entities.Note;
import com.rt.prototype.Repositories.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    // Constructor injection (preferred way in Spring)
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // --- CRUD ---

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note existingNote = getNoteById(id); // ensures it exists

        existingNote.setTitle(noteDetails.getTitle());
        existingNote.setContent(noteDetails.getContent());
        existingNote.setCategory(noteDetails.getCategory());
        existingNote.setTags(noteDetails.getTags());
        existingNote.setAuthor(noteDetails.getAuthor());

        return noteRepository.save(existingNote);
    }

    public void deleteNoteById(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }

    // --- Search methods (examples) ---

    public List<Note> findByAuthorContaining(String author) {
        return noteRepository.findByAuthorContaining(author);
    }

    public List<Note> findByTitleContaining(String title) {
        return noteRepository.findByTitleContaining(title);
    }

    public List<Note> findByContentContaining(String content) {
        return noteRepository.findByContentContaining(content);
    }

    public List<Note> findByCategory(String category) {
        return noteRepository.findByCategory(category);
    }

    public List<Note> findByTagsContaining(String tag) {
        return noteRepository.findByTagsContaining(tag);
    }

    // Example of a more complex search
    public List<Note> findByAuthorAndCategoryAndTagsAndTitleAndContent(
            Optional<String> author,
            Optional<String> category,
            Optional<String> tag,
            Optional<String> title,
            Optional<String> content) {

        // Convert Optionals to nullable Strings with proper handling
        String authorValue = author.orElse("");
        String categoryValue = category.orElse("");
        String tagValue = tag.orElse("");
        String titleValue = title.orElse("");
        String contentValue = content.orElse("");
        System.out.println("Searching with parameters:");
        System.out.println("Author: " + authorValue);
        System.out.println("Category: " + categoryValue);
        System.out.println("Tag: " + tagValue);
        System.out.println("Title: " + titleValue);
        System.out.println("Content: " + contentValue);

        return noteRepository
                .findByAuthorContainingAndCategoryContainingAndTagsContainingAndTitleContainingAndContentContaining(
                        authorValue,
                        categoryValue,
                        tagValue,
                        titleValue,
                        contentValue);
    }
}