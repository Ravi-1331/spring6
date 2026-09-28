package com.ravi.SpringBootWeb1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @ModelAttribute("course")
    public String courseName(){
        return "Java";
    }

    @RequestMapping("/")
    public ModelAndView home(ModelAndView mv){
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("addAlien")
    public String addAlien(Alien alien){
        return "result";
    }

}
