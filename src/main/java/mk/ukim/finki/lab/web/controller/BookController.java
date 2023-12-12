package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.model.Review;
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
import java.util.*;

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
    public String selectBook(@RequestParam Long bookId, Model model) {
        Book book = bookService.findBookById(bookId);
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
        Author author = authorService.findById(authorId).orElse(null);

        if (book != null && author != null) {
            // Fetch the authors collection within the same transaction
            List<Author> authors = bookService.getAuthorsForBook(book.getId());

            model.addAttribute("author", author);
            model.addAttribute("book", book);
            book.authors = authors;  // Set the fetched authors to the book
            bookService.addAuthorToBook(author, book);

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
                                @RequestParam LocalDate dateOfBirth)
    {
        authorService.saveAuthor(name,surname,biography,dateOfBirth);
        return "redirect:/books/selectAuthor";
    }

    @PostMapping("/add")
    public String addBook(Model model) {
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
    public String deleteBook(@PathVariable Long bookId)
    {

        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
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
    public String getEditBookForm(@PathVariable Long bookId,
                                  Model model) {
        Book book;

        if (bookId != null) {
            book = bookService.findBookById(bookId);
        } else {
            return "redirect:/books";
        }

        if (book != null) {
            model.addAttribute("bookStores", bookStoreService.findAll());
            model.addAttribute("book", book);
            return "editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/addReview/{bookId}")
    public String getReviewPage(Model model, @PathVariable Long bookId) {
        Book book = bookService.findBookById(bookId);
        model.addAttribute("book", book);
        return "addReview";
    }
    @GetMapping("/addReview/{bookId}")
    public String getReviewByKeyword(Model model, @PathVariable Long bookId) {
        Book book = bookService.findBookById(bookId);
        Collections.sort(book.getReviews(), Comparator.comparingInt(Review::getScore));
        model.addAttribute("book", book);
        return "addReview";
    }

    @PostMapping("/saveReview")
    public String saveReview(@RequestParam Long bookId,
                             @RequestParam Integer score,
                             @RequestParam String description,
                             @RequestParam LocalDateTime timestamp) {
        Book book = bookService.findBookById(bookId);
        reviewService.saveReview(score,description,book,timestamp);
        return "redirect:/books";
    }
}