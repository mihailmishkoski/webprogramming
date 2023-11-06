package mk.ukim.finki.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.Author;
import mk.ukim.finki.lab.service.AuthorService;
import mk.ukim.finki.lab.service.BookService;
import mk.ukim.finki.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/author")
public class АuthorServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;

    private final BookService bookService;

    public АuthorServlet(AuthorService authorService, SpringTemplateEngine springTemplateEngine, BookService bookService)  {
        this.authorService = authorService;
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author authorId = authorService.findById(Long.parseLong(req.getParameter("authorId"))).orElse(null);
        req.getSession().setAttribute("chosenAuthor", authorId);
        req.getSession().setAttribute("currentBook",bookService.findBookByIsbn((String)req.getSession().getAttribute("currentIsbn")));
        bookService.addAuthorToBook(authorId.getId(), (String) req.getSession().getAttribute("currentIsbn"));
        resp.sendRedirect("/bookdetails");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);


        WebContext context =  new WebContext(webExchange);
        context.setVariable("authors", authorService.listAuthors());
        springTemplateEngine.process(
                "authorList.html",
                context,
                resp.getWriter()
        );
    }
}
