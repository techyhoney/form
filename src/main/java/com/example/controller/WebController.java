//
package com.example.controller;
//
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

   @Autowired
   private UserRepository userRepository;

   @GetMapping("/form")
   public String form(User user) {
       return "form";
   }

   @PostMapping("/form")
   public String formSubmit(@ModelAttribute User user, Model model) {
       userRepository.save(user);
       model.addAttribute("user", user);
       return "result";
   }
}
