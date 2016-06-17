package com.juanocampo.test.androidtest.model;

/**
 * Created by juanocampo on 6/14/16.
 */
public class Feed {

    private final Author author;
    private final Entry entry;
    private final Label updated;
    private final Label rights;
    private final Label title;
    private final Label icon;

    public Feed(Author author, Entry entry, Label updated, Label rights, Label title, Label icon) {
        this.author = author;
        this.entry = entry;
        this.updated = updated;
        this.rights = rights;
        this.title = title;
        this.icon = icon;
    }

    public Author getAuthor() {
        return author;
    }

    public Entry getEntry() {
        return entry;
    }

    public Label getUpdated() {
        return updated;
    }

    public Label getRights() {
        return rights;
    }

    public Label getTitle() {
        return title;
    }

    public Label getIcon() {
        return icon;
    }
}
