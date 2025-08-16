package com.rt.prototype.Entities;

import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.*;

class NotePropertyTest {

    @Property
    void titleShouldBeSetCorrectly(@ForAll String title) {
        Note note = new Note();
        note.setTitle(title);
        assertEquals(title, note.getTitle());
    }

    @Property
    void contentShouldBeSetCorrectly(@ForAll String content) {
        Note note = new Note();
        note.setContent(content);
        assertEquals(content, note.getContent());
    }

    @Property
    void categoryShouldBeSetCorrectly(@ForAll String category) {
        Note note = new Note();
        note.setCategory(category);
        assertEquals(category, note.getCategory());
    }

    @Property
    void tagsShouldBeSetCorrectly(@ForAll String tags) {
        Note note = new Note();
        note.setTags(tags);
        assertEquals(tags, note.getTags());
    }

    @Property
    void tagsSplitByCommaShouldContainAllTags(@ForAll String tags) {
        Note note = new Note();
        note.setTags(tags);
        String[] splitTags = note.getTags().split(",");
        for (String tag : splitTags) {
            assertTrue(tags.contains(tag));
        }
    }

    @Property
    void idShouldBeSetAndRetrievedCorrectly(@ForAll long id) {
        Note note = new Note();
        note.setId(id);
        assertEquals(id, note.getId());
    }
}
