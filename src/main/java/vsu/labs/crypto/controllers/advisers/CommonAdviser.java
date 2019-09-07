package vsu.labs.crypto.controllers.advisers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.exceptions.LogicException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonAdviser {
    private static final Logger log = LoggerFactory.getLogger(CommonAdviser.class);

    private static final String DEFAULT_ERROR_MESSAGE = "При обработке произошла непредвиденная ошибка.";

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response handleException(HttpServletRequest request, Exception exception) {
        log.warn("An exception occurred in {}, during the processing of URL mapping '{}':", getClass().getName(), request.getRequestURI(), exception);
        return Response.fail(DEFAULT_ERROR_MESSAGE);
    }

    @ResponseBody
    @ExceptionHandler(LogicException.class)
    public Response handleLogicException(HttpServletRequest request, LogicException exception) {
        log.warn("An exception occurred in {}, during the processing of URL mapping '{}':", getClass().getName(), request.getRequestURI(), exception);
        return Response.fail(exception.getMessage());
    }
}
