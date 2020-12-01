package com.search.controller;

import com.search.algorithms.TableSearch;
import com.search.dto.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

    private final TableSearch tableSearch;

    @Autowired
    public DefaultController(TableSearch tableSearch) {
        this.tableSearch = tableSearch;
    }

    @PostMapping(value = "/a")
    public @ResponseBody Map<String, Object>
    findWayA(@RequestBody Graph graph) {
        return tableSearch.getWay(graph);
    }

}