package libraryProject.model;

import libraryProject.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private Long memberId;
    private String name;
    private int age;
    private Gender gender;
    private Book currentlyReading;
    private List<Book> finishedBooks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Book getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(Book currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public List<Book> getFinishedBooks() {
        return finishedBooks;
    }

    public void setFinishedBooks(List<Book> finishedBooks) {
        this.finishedBooks = finishedBooks;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "LibraryMember: " +
                "\n memberId = " + memberId +
                "\n name = " + name+"\n\n" ;
//                "\n age = " + age +
//                "\n gender = " + gender +
//                "\n currentlyReading = " + currentlyReading +
//                "\n finishedBooks = " + finishedBooks+"\n\n";
    }
}
