package module.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import module.model.dto.StatisticsDTO;
import module.model.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/stats")
public class GeneralStatisticsController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeneralStatisticsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("doGet");
        ItemService service = new ItemService();
        try {
            Optional<StatisticsDTO> statistics = service.getStatistics();
            statistics.ifPresent((value) -> req.setAttribute("generalStatistics", value));
        } catch (Exception e) {
            req.setAttribute("errorMessage", "There is no such item. Try another item please.");
        }
        req.getRequestDispatcher("WEB-INF/jsp/general-statistics.jsp").forward(req, resp);
    }
}
