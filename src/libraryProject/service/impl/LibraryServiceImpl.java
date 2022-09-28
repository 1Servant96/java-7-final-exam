package libraryProject.service.impl;

import libraryProject.dao.Dao;
import libraryProject.enums.Gender;
import libraryProject.model.Book;
import libraryProject.model.LibraryMember;
import libraryProject.service.LibraryService;

import java.util.*;

public class LibraryServiceImpl implements LibraryService {
    private static final Scanner scannerN = new Scanner(System.in);
    private static final Scanner scannerS = new Scanner(System.in);
    private Dao dao;

    public LibraryServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<LibraryMember> getLibraryMembers() {
        return dao.getLibrary().getLibraryMembers();
    }

    @Override
    public void addLibraryMember(LibraryMember member) {
        dao.getLibrary().getLibraryMembers().add(member);
    }

    @Override
    public LibraryMember findLibraryMemberById(Long id) {

        return dao.getLibrary().getLibraryMembers().stream().filter(x->x.getMemberId().equals(id)).findFirst().get();
    }

    @Override
    public void deleteLibraryMemberByID(Long id) {
    LibraryMember libraryMember = dao.getLibrary().getLibraryMembers().stream().filter(x->x.getMemberId().equals(id)).findFirst().get();
    boolean libraryMembers = dao.getLibrary().getLibraryMembers().remove(libraryMember);
    }

    @Override
    public void addBookToLibrary(Book book) {

        this.getLibraryBooks().add(book);
    }

    @Override
    public List<Book> getLibraryBooks() {
        return dao.getLibrary().getBooks();
    }

    @Override
    public void deleteLibraryBookByID(Long id) {
       Book book = dao.getLibrary().getBooks().stream().filter(x->x.getBookId().equals(id)).findFirst().get();
       dao.getLibrary().getBooks().remove(book);
    }

    @Override
    public Book findLibraryBookById(Long id) {
        return dao.getLibrary().getBooks().stream().filter(x->x.getBookId().equals(id)).findFirst().get();
    }

    @Override
    public void addBookToMember() {
    System.out.println("Enter the id of member");
    long idOfMember = scannerN.nextLong();
    System.out.println("Enter the id of book");
    long idOfBook = scannerN.nextLong();

   Optional<LibraryMember> libraryMemberOptional = dao.getLibrary().getLibraryMembers().
           stream().filter(x->x.getMemberId()==idOfMember && x.getCurrentlyReading() == null).findFirst();

   Optional<Book> bookOptional = dao.getLibrary().getBooks().
                stream().filter(x->x.getBookId()==idOfBook && x.getCurrentHolder() == null).findFirst();


    if(libraryMemberOptional.isEmpty())
        System.out.println("Sorry, but the library member with ID " + idOfMember + " doesn't exist " +
                "or has current reading !");
    else {
        if(bookOptional.isEmpty())
            System.out.println("Sorry, but the library member with ID "+ idOfBook + " doesn't exist " +
                "or has current holder!");
        else {
            LibraryMember member = libraryMemberOptional.get();
            Book book = bookOptional.get();

            member.setCurrentlyReading(book);
            book.setCurrentHolder(member);

            deleteLibraryMemberByID(member.getMemberId());
            deleteLibraryBookByID(book.getBookId());

            addLibraryMember(member);
            addBookToLibrary(book);
        }
    }

    }

    @Override
    public void removeBookFromReading(){
        System.out.println("Enter the id of member");
        long idOfMember = scannerN.nextLong();
        System.out.println("Enter the id of book");
        long idOfBook = scannerN.nextLong();

        Optional<LibraryMember> libraryMemberOptional = dao.getLibrary().getLibraryMembers().
                stream().filter(x->x.getMemberId() == idOfMember && x.getCurrentlyReading()!= null).findFirst();

        Optional<Book> bookOptional = dao.getLibrary().getBooks().stream()
                .filter(x->x.getBookId() == idOfBook && x.getCurrentHolder() != null).findFirst();

        if(libraryMemberOptional.isEmpty())
            System.out.println("Sorry, but the library member with ID " + idOfMember
                    + " doesn't exist or doesn't have current reaading" );
        else{
            if(bookOptional.isEmpty())
                System.out.println("Sorry, but the library member with ID " + idOfBook + " doesn't exist " +
                        "or doesn't have current holder!);");
            else {

                LibraryMember member = libraryMemberOptional.get();
                Book book = bookOptional.get();

                member.getFinishedBooks().add(member.getCurrentlyReading());

                member.setCurrentlyReading(null);
                book.setCurrentHolder(null);

                deleteLibraryMemberByID(idOfMember);
                deleteLibraryBookByID(idOfBook);

                addLibraryMember(member);
                addBookToLibrary(book);

            }
        }
    }
}
