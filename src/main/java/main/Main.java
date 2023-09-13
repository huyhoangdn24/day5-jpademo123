package main;

import configuration.JPAConfig;
import entity.BookDetailsEntity;
import entity.BookEntity;
import entity.CategoryEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.BookRepository;
import repository.CategoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static BookRepository bookRepository = context.getBean(BookRepository.class);
    static CategoryRepository categoryRepository = (CategoryRepository) context.getBean("categoryRepository");

    public static void main(String[] args) {
        createNewBookEntryWithNewCategory();

        createNewBookEntry();

        findByAuthor("Roger");
        findByNameAndAuthor("linux", "Roger");
        findByNameOrAuthor("linux","Roger");
        findByPriceLessThan(80);
        findByBookDetailsIsbn("ISIBF1219321");
        findByNameContaining("Nu");
//        createNewBook();
//        readBooks();
//        readBookById(1);
//        updateBook();
//        deleteBooksByName(bookName);
    }

    public static void findByAuthor(String author) {
        List<BookEntity> bookEntityList = bookRepository.findByAuthor(author);
        if (bookEntityList !=null) {
            System.out.println("\n Find" + bookEntityList.size() + "book which author = " + author);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameAndAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameAndAuthor(name,author);
        if (bookEntityList !=null) {
            System.out.println("\n Find" + bookEntityList.size() + "book which name = " + name + "and author =" + author);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameOrAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameOrAuthor(name,author);
        if (bookEntityList !=null) {
            System.out.println("\n Find" + bookEntityList.size() + "book which name = " + name + "and author =" + author);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByPriceLessThan(double price) {
        List<BookEntity> bookEntityList = bookRepository.findByBookDetailsPriceLessThan(price);
        if (bookEntityList !=null) {
            System.out.println("\n Find" + bookEntityList.size() + "book price less than" + price);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameContaining(String name) {
        List<BookEntity> bookEntityList = bookRepository.findByNameContaining(name);
        if (bookEntityList !=null) {
            System.out.println("\n Find" + bookEntityList.size() + "book which containing name = " + name);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findAllUsingQuery() {
        List<BookEntity> bookEntityList = (List<BookEntity>) bookRepository.findAll();
        if (bookEntityList !=null) {
            System.out.println("\n Find" + bookEntityList.size() + "books" );
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByBookDetailsIsbn(String isbn) {
        BookEntity bookEntity = bookRepository.findByBookDetailsIsbn(isbn);
        if (bookEntity !=null) {
            System.out.println("\n Find book which isbn = " + isbn);
                System.out.println(bookEntity.toString());
        }
    }

    public static void createNewBookEntry() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    public static void createNewBookEntryWithNewCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity = createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    private static CategoryEntity createNewCatorgy() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("IT");
        categoryEntity.setDescription("IT books");
        return categoryEntity;
    }

    private static BookEntity createNewBook(){
        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn("ISIBF1219323");
        bookDetailsEntity.setNumberPage(23);
        bookDetailsEntity.setPrice(65);
        bookDetailsEntity.setPublishDate(LocalDate.now());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("JAVA A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setBookDetails (bookDetailsEntity);
        bookDetailsEntity.setBook(bookEntity);

        return bookEntity;
    }

    public static void findBookNameStartWithUsingQuery (String name) {
        List<BookEntity> bookEntityList = bookRepository.getBookNameStartWith(name);
        if (bookEntityList !=null) {
            System.out.println("\nFind" + bookEntityList.size() + "books");
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findBookPriceGreaterThanUsingQuery (int price) {
        List<BookEntity> bookEntityList = bookRepository.getBookPriceGreaterThan(price);
        if (bookEntityList !=null) {
            System.out.println("\nFind" + bookEntityList.size() + "books");
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }
}


//    private static void createNewBook() {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setName("Java A-Z");
//        bookEntity.setAuthor("Roger");
//        bookEntity.setCategory("IT books");
//        bookEntity.setIsbn("ISIBF1323242");
//        bookEntity.setNumberPage(234);
//        bookEntity.setPrice(20.5);
//        bookEntity.setPublishDate(LocalDate.parse("2016-08-25"));
//
//        bookRepository.save(bookEntity);
//
//        System.out.println("A new book saved successfully, book ID = " + bookEntity.getId());
//    }
//
//    private static void readBooks() {
//        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
//        System.out.println("Found " + bookList.size() + " book(s) in the table book");
//        System.out.println("They are: ");
//        for (BookEntity book : bookList) {
//            System.out.println(book.toString());
//        }
//    }
//
//    private static void readBookById(long bookId) {
//        Optional<BookEntity> optionalBook = bookRepository.findById((int) bookId);
//        if (optionalBook.isPresent()) {
//            BookEntity bookEntity = optionalBook.get();
//            System.out.println("Found a book with book ID = " + bookEntity.getId());
//        } else {
//            System.out.println("Not found any book with book ID = " + bookId);
//        }
//    }
//
//    private static void updateBook() {
//        Optional<BookEntity> optionalBook = bookRepository.findById(1);
//        if (optionalBook.isPresent()) {
//            BookEntity bookEntity = optionalBook.get();
//            System.out.println("Book data before updating");
//            System.out.println(bookEntity.toString());
//
//            bookEntity.setAuthor("Jame");
//            bookEntity.setNumberPage(199);
//            bookEntity.setPrice(201);
//            bookRepository.save(bookEntity);
//
//            System.out.println("Book data after updating");
//            System.out.println(bookEntity.toString());
//        } else {
//            System.out.println("Not found any book with book ID = 1");
//        }
//    }
//
//    private static void deleteBooksByName(String bookName) {
//        List<BookEntity> booksToDelete = bookRepository.findByName(bookName);
//        if (!booksToDelete.isEmpty()) {
//            for (BookEntity bookEntity : booksToDelete) {
//                bookRepository.delete(bookEntity);
//                System.out.println("Book with name '" + bookName + "' deleted successfully.");
//            }
//        } else {
//            System.out.println("No books found with name '" + bookName + "'.");
//        }
//    }
//}

