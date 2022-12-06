package com.gamershop.admin.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(SQLException.class)
    public String handleSQLExceptionHandler(SQLException ex, RedirectAttributes redirectAttributes){
        String message;
        if (ex.getMessage().contains("user")){
            if (ex.getMessage().contains("Duplicate entry")){
                message = "This user already exist";
            }
            else {
                message = "Unknown error";
            }
            redirectAttributes.addFlashAttribute("errormessage", message);
            return "redirect:/users";
        }
        return "redirect:/index";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundExceptionHandler(UserNotFoundException ex, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errormessage", ex.getMessage());
        return "redirect:/users";
    }

}
