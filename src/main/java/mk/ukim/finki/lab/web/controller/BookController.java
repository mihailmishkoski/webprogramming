package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.model.BookStore;
import mk.ukim.finki.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab.service.AuthorService;
import mk.ukim.finki.lab.service.BookService;
import mk.ukim.finki.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@SessionAttributes({"book", "author"})
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;
    public BookController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }
    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String searchedBookName, Model model) {
        if (searchedBookName != null) {
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

    @PostMapping("/selectBook")
    public String selectBook(@RequestParam String bookIsbn, Model model) {
        Book book = bookService.findBookByIsbn(bookIsbn);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.listAuthors());
            return "authorList";
        } else {
            return "redirect:/books";
        }
    }
    @GetMapping("/selectBook")
    public String selectBookGet(@RequestParam String bookIsbn, Model model) {
            model.addAttribute("authors", authorService.listAuthors());
            return "authorList";
    }
    @PostMapping("/selectBook/selectAuthor")
    public String selectAuthor(@RequestParam Long authorId, Model model) {
        Book book = (Book) model.getAttribute("book");
        Author author = authorService.findById(authorId).orElse(null);
        if (book != null && author != null) {
            model.addAttribute("author", author);
            book.authors.add(author);
            model.addAttribute("book", book);
            return "bookDetails";
        } else {

            return "redirect:/books";
        }
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
                           @RequestParam String storeId,
                          Model model) {
//        Book newBook = new Book(isbn, title, genre, year, new ArrayList<>());
//        newBook.bookStore = bookStoreService.findAll().stream().filter(b -> b.getName().equals(storeId)).findFirst().orElse(null);
//        bookService.listBooks().add(newBook);
        bookService.saveBook(isbn,title,genre,year,storeId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId)
    {
        Book b = bookService.listBooks().stream().filter(bs->bs.getIsbn().matches(bookId)).findFirst().orElse(null);
       bookService.listBooks().remove(b);
       return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable String bookId,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam String storeId)
    {
        Book book = bookService.findBookByIsbn(bookId);
        book.title = title;
        book.isbn = isbn;
        book.genre = genre;
        book.year = year;
        book.bookStore = bookStoreService.findAll().stream().filter(bs->bs.getId().matches(storeId)).findFirst().orElse(null);
        return "redirect:/books";
    }

    @RequestMapping(value = "/getEditForm/{bookId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String getEditBookForm(@PathVariable String bookId,
                                  @RequestParam(required = false) String bookIdFromController,
                                  Model model) {
        Book book;

        if (bookId != null) {
            book = bookService.findBookByIsbn(bookId);
        } else {
            book = bookService.findBookByIsbn(bookIdFromController);
        }

        if (book != null) {
            model.addAttribute("storeOfTheBook", book.bookStore);
            model.addAttribute("bookStores", bookStoreService.findAll());
            model.addAttribute("book", book);
            return "editBook";
        }

        return "redirect:/books";
    }
    @GetMapping("/getEditForm/bookId")
    public String getEditBookForm(@PathVariable String bookId)
    {
        Book book = bookService.listBooks().stream().filter(b -> b.getIsbn().matches(bookId)).findFirst().orElse(null);

        if (book != null) {
            return "redirect:books/getEditForm/" + bookId;
        }

        return "redirect:/books";
        //treba dopolnitelno da frla nekoja greska, ne znam dali exception ili treba message da e pojavi.
    }

}