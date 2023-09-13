package entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;
    @ManyToOne
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private BookDetailsEntity bookDetails;

//    @Column(name = "category")
//    private String category;
//
//    @Column(name = "isbn")
//    private String isbn;
//
//    @Column(name = "price")
//    private double price;
//
//    @Column(name = "numberPage")
//    private int numberPage;
//
//    @Column(name = "publishDate")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate publishDate;

    public BookEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getNumberPage() {
//        return numberPage;
//    }
//
//    public void setNumberPage(int numberPage) {
//        this.numberPage = numberPage;
//    }
//
//    public LocalDate getPublishDate() {
//        return publishDate;
//    }
//
//    public void setPublishDate(LocalDate publishDate) {
//        this.publishDate = publishDate;
//    }
//    @Override
//    public String toString(){
//        return "BookEnity{" + "id=" +id + ", name='" + name + '\'' + ", author='" + '\'' + ",category='" + '\'' + ", isbn='" + isbn + '\'' + ", numberOfPage=" + numberPage + ", publishDate=" + publishDate +'}';
//    }
//}
    @Override
    public String toString() {
        return "BookEnity{" + "id=" + id + ", name='" + name + '\'' + ", author='" + '\'' + ",category='" + '\'' + ", bookDetails=" + bookDetails + '}';
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setBookDetails(BookDetailsEntity bookDetails) {
        this.bookDetails = bookDetails;
    }

    public BookDetailsEntity getBookDetails() {
        return bookDetails;
    }
}