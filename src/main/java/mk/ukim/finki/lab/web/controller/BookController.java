package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab.service.AuthorService;
import mk.ukim.finki.lab.service.BookService;
import mk.ukim.finki.lab.service.BookStoreService;
import mk.ukim.finki.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Controller
@SessionAttributes({"book", "author"})
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    private final ReviewService reviewService;
    public BookController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService,ReviewService reviewService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
    }
    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String searchedBookName, Model model) {
        if (searchedBookName != null && !searchedBookName.isEmpty()) {
            Book book = bookService.listBooks().stream()
                    .filter(b -> b.getTitle().equals(searchedBookName))
                    .findFirst()
                    .orElse(null);
            model.addAttribute("books", book);
        } else {
            model.addAttribute("books", bookService.listBooks());
        }
        return "listBooks";
    }
    @GetMapping("/selectAuthor")
    public String selectBookGet(Model model) {
        model.addAttribute("authors", authorService.listAuthors());
        return "authorList";
    }
    @PostMapping("/selectAuthor")
    public String selectBook(@RequestParam String bookIsbn, Model model) {
        Book book = bookService.findBookByIsbn(bookIsbn);//here is not getting the book and throws exception, please help me how to fix this
        System.out.println("sea ke ispecati:");
        System.out.println(book);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.listAuthors());
            return "authorList";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/selectAuthor/bookDetails")
    public String selectAuthor(@RequestParam Long authorId, Model model) {
        Book book = (Book) model.getAttribute("book");
        System.out.println(book);
        Author author = authorService.findById(authorId).orElse(null);
        if (book != null && author != null) {
            model.addAttribute("author", author);
            model.addAttribute("book", book);
            book.authors.add(author);
            bookService.addAuthorToBook(author,book);
            return "bookDetails";
        } else {

            return "redirect:/books";
        }
    }
    @GetMapping("selectAuthor/getAuthorPage")
    public String getNewAuthorPage()
    {
        return "addNewAuthor";
    }
    @PostMapping("selectAuthor/saveAuthor")
    public String saveNewAuthor(@RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String biography,
                                @RequestParam LocalDate dateOfBirth,
                                @RequestParam Long identification)
    {
        authorService.saveAuthor(identification,name,surname,biography,dateOfBirth);
        return "redirect:/books";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam(required = false) String searchedBookName, Model model) {
        model.addAttribute("bookStores",bookStoreService.findAll());
        return "addBook";
    }

    @PostMapping("/add/save")
    public String saveBook(@RequestParam String title,
                          @RequestParam String isbn,
                          @RequestParam String genre,
                          @RequestParam Integer year,
                           @RequestParam Long storeId) {
        bookService.saveBook(isbn,title,genre,year,storeId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId)
    {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable String bookId,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long storeId)
    {

        bookService.editBook(bookId,isbn,title,genre,year,storeId);
        return "redirect:/books";
    }

    @RequestMapping(value = "/getEditForm/{bookId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String getEditBookForm(@PathVariable String bookId,
                                  Model model) {
        Book book;

        if (bookId != null) {
            book = bookService.findBookByIsbn(bookId);
        } else {
            return "redirect:/books";
        }

        if (book != null) {
            //model.addAttribute("storeOfTheBook", book.bookStore);
            System.out.println(book.bookStore);
            model.addAttribute("bookStores", bookStoreService.findAll());
            model.addAttribute("book", book);
            return "editBook";
        }
        return "redirect:/books";
    }
    @GetMapping("/addReview")
    public String getReviewPage(@RequestParam String bookIsbn, Model model) {
        model.addAttribute("book", bookService.findBookByIsbn(bookIsbn));
        return "addReview";
    }
    @PostMapping("/saveReview")
    public String saveReview(@RequestParam String bookId,
                             @RequestParam Integer score,
                             @RequestParam String description,
                             @RequestParam LocalDateTime timestamp) {
        Book book = bookService.findBookByIsbn(bookId);
        reviewService.saveReview(score,description,book,timestamp);
        return "redirect:/books";
    }
}