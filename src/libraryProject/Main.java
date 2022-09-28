package libraryProject;

import libraryProject.dao.Dao;
import libraryProject.enums.Gender;
import libraryProject.model.Book;
import libraryProject.model.Library;
import libraryProject.model.LibraryMember;
import libraryProject.service.impl.LibraryServiceImpl;

import java.nio.channels.ScatteringByteChannel;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);
    public static void main(String[] args) {
        Dao dao = new Dao(new Library());

        LibraryServiceImpl libraryService = new LibraryServiceImpl(dao);
        while(true){
            buttons();
            System.out.println("\nChoose the option:");
            String input = scannerS.nextLine();
            switch (input){
         case "1" -> {
             LibraryMember member = new LibraryMember();
             System.out.println("What is your ID:");
             member.setMemberId(scannerN.nextLong());
             System.out.println("What is your name:");
             member.setName(scannerS.nextLine());

             System.out.println("What is your gender, choose 1 (male) or 2 (female):");
             int gender = scannerN.nextInt();
             if(gender == 1){
                 member.setGender(Gender.MALE);
             }
             else if (gender == 2) {
                 member.setGender(Gender.FEMALE);
             }
             libraryService.addLibraryMember(member);
         }
         case "2" -> {
             System.out.println(libraryService.getLibraryMembers());
         }
         case "3" -> {
             try {
             LibraryMember libraryMemberById = libraryService.findLibraryMemberById(scannerN.nextLong());
             if (libraryMemberById!=null) {
                 System.out.println("Name:"+libraryMemberById.getName());
                 System.out.println("ID:"+libraryMemberById.getMemberId()+"\n");
                 if(libraryMemberById.getCurrentlyReading()!=null)
                 System.out.println(libraryMemberById.getCurrentlyReading());
                 else {
                     System.out.println("No book");
                 }
//                 System.out.println(libraryMemberById.getCurrentlyReading());
                 System.out.println("The amount of finished books:"+libraryMemberById.getFinishedBooks().size()+"\n");
                 System.out.println(libraryMemberById.getFinishedBooks());
             }}catch (NoSuchElementException a){
                 System.out.println("No person with this id in database");
             }
         }
         case "4" -> {
             libraryService.deleteLibraryMemberByID(+scannerN.nextLong());
         }
         case "5" -> {
             try {
             Book book = new Book();
             System.out.println("The id of a book:");
             book.setBookId(scannerN.nextLong());
             System.out.println("The name of a book");
             book.setTitle(scannerS.nextLine());
             System.out.println("The author of the book");
             book.setAuthor(scannerS.nextLine());
             System.out.println("The year of a production");
             book.setYearOfProduction(scannerN.nextInt());
             libraryService.addBookToLibrary(book);
         }catch (InputMismatchException e){
                 System.out.println("you need enter a number");
             }
         }
         case "6" -> {
             System.out.println(libraryService.getLibraryBooks());
         }
         case "7" -> {
             System.out.println(libraryService.findLibraryBookById(scannerN.nextLong()));
         }
         case "8" -> {
             libraryService.deleteLibraryBookByID(scannerN.nextLong());
         }
         case "9" -> {
             libraryService.addBookToMember();
         }
         case "10" -> {
             libraryService.removeBookFromReading();
         }
            }
            if (input.equals("x")) {
                break;
            }
        }
    }

    public static void  buttons(){
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 1, чтобы добавить нового участника в библиотеку.");
        System.out.println("Нажмите 2, чтобы увидеть всех участников библиотеки.");
        System.out.println("Нажмите 3, чтобы найти участника по ID и увидеть данные участника, читаемая книга и прочитанные.");
        System.out.println("Нажмите 4, чтобы удалить участника по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 5, чтобы добавить книгу в библиотеку.");
        System.out.println("Нажмите 6, чтобы увидеть все книги в библиотеке.");
        System.out.println("Нажмите 7, чтобы найти книгу по ID.");
        System.out.println("Нажмите 8, чтобы удалить книгу по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 9, чтобы ввести memberId участника и bookId книги, добавить в читаемые");
        System.out.println("Нажмите 10, чтобы ввести memberId участника и bookId книги, добавить в прочитанные");
        System.out.println("Нажмите x, чтобы завершить программу.");
    }
}
