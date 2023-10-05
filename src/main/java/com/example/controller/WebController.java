//
package com.example.controller;
//
//import com.example.model.User;
//import com.example.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class WebController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/form")
//    public String form(User user) {
//        return "form";
//    }
//
//    @PostMapping("/form")
//    public String formSubmit(@ModelAttribute User user, Model model) {
//        userRepository.save(user);
//        model.addAttribute("user", user);
//        return "result";
//    }
//}
//

//import org.springframework.http.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.client.RestTemplate;
//import java.util.Collections;
//
//@Controller
//public class WebController {
//
//    @RequestMapping(value = "/trigger", method = RequestMethod.POST)
//    public String triggerJenkins() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Setup Basic Authentication header
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth("hitengoyal", "jenkins@100");
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//
//        // Make the GET request
//        String url = "http://127.0.0.1:8080/job/Dashboard/build?token=AuthToken";
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        
//        // You can add some logging here to show the response status, or handle it as you need
//        System.out.println("Response Status Code: " + response.getStatusCode());
//
//        return "redirect:/";
//    }
//}

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/trigger", method = RequestMethod.POST)
    public String triggerJenkins(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        // Setup Basic Authentication header
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("hitengoyal", "jenkins@100");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Make the GET request
        String url = "http://127.0.0.1:8080/job/Dashboard/build?token=AuthToken";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Set the confirmation message based on the response
        if (response.getStatusCode() == HttpStatus.CREATED) {
            model.addAttribute("confirmation", "Successfully triggered the Jenkins job!");
        } else {
            model.addAttribute("confirmation", "Failed to trigger the Jenkins job.");
        }

        return "index";
    }
}

