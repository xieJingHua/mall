package com.xiejh.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiejh
 * @Date 2020/11/9 20:30
 **/
@Controller
public class SearchController {

    @GetMapping("/search.html")
    public String listPage(){
        return "search";
    }
}
