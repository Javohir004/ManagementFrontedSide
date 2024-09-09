package uz.jvh.telegramfrontend.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public RedirectView handleApiException(
            ApiException e,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("message", e.getError());
        redirectAttributes.addAttribute("message", e.getError());
        String refer = request.getHeader("Referer");
        return new RedirectView(refer != null ? refer : "/error");    }



}
