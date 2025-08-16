package com.rt.prototype.Repositories;

import org.springframework.stereotype.Repository;

import com.rt.prototype.Entities.Note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAuthor(String author);

    List<Note> findByTitle(String title);

    List<Note> findByContent(String content);

    List<Note> findByCategory(String category);

    List<Note> findByTagsContaining(String tag);

    List<Note> findByTitleContaining(String title);

    List<Note> findByContentContaining(String content);

    List<Note> findByAuthorAndTitle(String author, String title);

    List<Note> findByAuthorAndCategoryAndTagsContainingAndTitleContainingAndContentContaining(
            String author, String category, String tag, String title, String content);
}
