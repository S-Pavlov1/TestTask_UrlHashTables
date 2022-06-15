package com.pavlov.testtask_urlhashtables.controller;

import com.pavlov.testtask_urlhashtables.service.URLService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class URLController {

    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/letter")
    public String getURLInfo (Model model) {
        model.addAttribute("name", "и.о. секретаря");
        model.addAttribute("deletedURLs", urlService.getDeletedURLs());
        model.addAttribute("newURLs", urlService.getNewURLs());
        model.addAttribute("changedURLs",urlService.getChangedURLs());
        return "letter";
    }

}
