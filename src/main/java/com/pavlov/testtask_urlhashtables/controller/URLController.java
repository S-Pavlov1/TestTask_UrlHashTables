package com.pavlov.testtask_urlhashtables.controller;

import com.pavlov.testtask_urlhashtables.service.URLService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class URLController {

    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/letter")
    public String getURLInfo (Model model) {

        return "letter";
    }

}
