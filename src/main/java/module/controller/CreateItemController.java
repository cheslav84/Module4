package module.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import module.model.entity.Item;
import module.model.service.ItemService;
import module.model.util.ItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/start")
public class CreateItemController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CreateItemController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.trace("doPost");
        try {
            ItemFactory factory = new ItemFactory();
            Item item = factory.makeItem();
            ItemService service = new ItemService();
            service.saveItem(item);
        } catch (Exception e) {
            String errorMessage = "It is impossible to create new item for now. Try again later.";
            req.getSession().setAttribute("errorMessage", errorMessage);
            LOG.error(errorMessage);
        }
        resp.sendRedirect("index");
    }

}
