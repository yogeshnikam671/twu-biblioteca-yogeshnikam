package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Librarian librarian = new Librarian();
        Library library = new Library(librarian);
        Greeter greeter = new Greeter();

        greeter.greet();
        System.out.println();

        Menu menu = new Menu();

        while (true) {
            menu.display();

            Scanner input = new Scanner(System.in);

            String option = input.next();
            while (!menu.isValidOption(option)) {
                System.out.println("Please select a valid option");
                option = input.next();
            }

            process(option, library);
        }
    }

    private static void process(String option, Library library) {
        int selectedOption = Integer.parseInt(option);
        switch (selectedOption) {
            case 1:
                library.showBooks();
                break;

            case 2:
                System.out.println("Enter Title, Author and Year Of Publication, respectively");
                Book book = Book.getInputtedBook();
                library.checkOut(book);
                break;

            case 3:
                System.out.println("Enter Title, Author and Year Of Publication, respectively");
                book = Book.getInputtedBook();
                library.returnBack(book);
                break;

            case 4:
                System.exit(0);
        }
        System.out.print("\n\n");
    }
}
