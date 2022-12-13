package com.gamershop.customer.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CategoryNotFoundException.class)
    public String handleUserNotFoundExceptionHandler(CategoryNotFoundException ex, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errormessage", "Could not find this category");
        return "redirect:/";
    }

}
