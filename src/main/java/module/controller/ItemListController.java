package module.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import module.model.dto.ItemIdAndDateDTO;
import module.model.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class ItemListController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ItemListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("doGet");
        ItemService service = new ItemService();
        List<ItemIdAndDateDTO> items = service.findAllItems();
        req.setAttribute("items", items);
        req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);
    }
}
