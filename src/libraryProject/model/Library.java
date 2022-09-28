package libraryProject.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<LibraryMember> libraryMembers;

    public Library() {
        this.books = new ArrayList<>();
        this.libraryMembers = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<LibraryMember> getLibraryMembers() {
        return libraryMembers;
    }

    public void setLibraryMembers(List<LibraryMember> libraryMembers) {
        this.libraryMembers = libraryMembers;
    }
}
