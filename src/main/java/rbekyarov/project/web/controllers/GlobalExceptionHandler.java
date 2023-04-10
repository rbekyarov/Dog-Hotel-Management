package rbekyarov.project.web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleSqlException(Throwable e){
        ModelAndView modelAndView = new ModelAndView("error.html");

        Throwable throwable = e;

        while (throwable.getCause() != null){
            throwable = throwable.getCause();
        }

        modelAndView.addObject("message",throwable.getMessage());

        return modelAndView;
    }
}
