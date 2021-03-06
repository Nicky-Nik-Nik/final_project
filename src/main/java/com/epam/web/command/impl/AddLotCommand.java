package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
import com.epam.web.service.LotService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.impl.LotServiceImpl;
import com.epam.web.util.JspPath;
import com.epam.web.util.RequestParameter;
import com.epam.web.util.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * The command that creates a new Lot and allowed only to seller
 *
 * @author Nikita Yakovlev
 */
public class AddLotCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static LotService service = LotServiceImpl.getInstance();
    private static final String COMMAND_TO_REDIRECT = "/controller?command=to_lots";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        CommandResult result = CommandResult.createRedirectCommandResult(COMMAND_TO_REDIRECT);
        try {
            if (user != null && user.getUserRole() == UserRole.SELLER) {
                String name = request.getParameter(RequestParameter.NAME);
                String description = request.getParameter(RequestParameter.DESCRIPTION);
                String startBid = request.getParameter(RequestParameter.BID);
                String startTimeString = request.getParameter(RequestParameter.START_TIME);
                String finishTimeString = request.getParameter(RequestParameter.END_TIME);
                Timestamp startTime = null;
                Timestamp finishTime = null;
                if (!startTimeString.isEmpty()) {
                    startTime = Timestamp.valueOf(startTimeString.replace("T", " ") + ":00");
                }
                if (!finishTimeString.isEmpty()) {
                    finishTime = Timestamp.valueOf(finishTimeString.replace("T", " ") + ":00");
                }
                List<String> images = (List<String>) request.getAttribute(RequestParameter.IMAGE_PATH);
                service.createNewLot(name, description, startBid, startTime, finishTime, user.getId(), images);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestParameter.ERROR, e.getMessage());
            result = CommandResult.createForwardCommandResult(JspPath.LOT_EDIT);
        }
        return result;
    }
}
