package module.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import module.model.dto.ItemDTO;
import module.model.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/stats/*")
public class ItemStatisticsController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ItemStatisticsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("doGet");
        ItemService service = new ItemService();
        String id = getId(req);
        try {
            Optional<ItemDTO> item = service.findItemById(id);
            item.ifPresent((value) -> req.setAttribute("item", value));
        } catch (Exception e) {
            req.setAttribute("errorMessage", "There is no such item. Try another item please.");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/item-statistics.jsp").forward(req, resp);
    }

    private String getId(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(requestURI.lastIndexOf('/') + 1);
    }

}
