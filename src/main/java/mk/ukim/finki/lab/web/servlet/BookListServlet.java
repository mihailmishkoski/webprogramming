package mk.ukim.finki.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.Book;
import mk.ukim.finki.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/listBooks")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    private final BookService bookService;
    public BookListServlet(SpringTemplateEngine springTemplateEngine,BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);

        context.setVariable("books", bookService.listBooks());
        springTemplateEngine.process(
                "listBooks.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String isbn = req.getParameter("bookIsbn");
        req.getSession().setAttribute("currentIsbn", isbn);
        resp.sendRedirect("/author");


    }
}
