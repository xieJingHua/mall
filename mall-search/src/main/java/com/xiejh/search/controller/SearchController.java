package com.xiejh.search.controller;

import com.xiejh.search.service.MallSearchService;
import com.xiejh.search.vo.SearchParam;
import com.xiejh.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiejh
 * @Date 2020/11/9 20:30
 **/
@Controller
public class SearchController {

    @Autowired
    private MallSearchService mallSearchService;

    @GetMapping("/search.html")
    public String listPage(SearchParam  param, Model model){
        SearchResult  result =mallSearchService.search(param);
        model.addAttribute("result", result);
        return "search";
    }
}
