package uz.jvh.telegramfrontend.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jvh.telegramfrontend.dto.request.LoginDTO;
import uz.jvh.telegramfrontend.dto.request.UserCreateDto;
import uz.jvh.telegramfrontend.model.UserEntity;
import uz.jvh.telegramfrontend.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String registerPage(@RequestParam(value = "message", required = false) String message, Model model) {
        if(message != null) {
            model.addAttribute("message", "Something went wrong");
        }
//        model.addAttribute("message", message);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "message", required = false) String message, Model model) {
        if(message != null) {
            model.addAttribute("error", "You have to verify first\n" +
                "Invalid username or password!");
    }
        return "login";
    }

    @GetMapping("/menu")
    public String menuPage() {
        return "menu";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute UserCreateDto user) {
        userService.save(user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDto) {
        userService.signIn(loginDto);
        return "menu";
    }

}
