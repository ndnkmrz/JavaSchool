package com.gamershop.admin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public String handleSQLExceptionHandler(SQLException ex, RedirectAttributes redirectAttributes){
        String message;
        if (ex.getMessage().contains("Duplicate entry")){
            message = "This user already exist";
        }
        else {
            message = "Unknown error";
        }
        redirectAttributes.addFlashAttribute("errormessage", message);
        return "redirect:/users";
    }
}
