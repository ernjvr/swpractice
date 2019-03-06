package org.swpractice.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForwardController {
//    this causes infinite circular forwarding
//leaving it for now
//    @RequestMapping("/{path:[^\\.]+}/**")
//    @RequestMapping("/**/{path:[^\\\\.]+}")
//    public String forward(HttpServletRequest request) {
//        String path = request.getRequestURI();
//        System.out.println("PATH: " + path);
//        if (request.getRequestURI().startsWith("/api")) {
//            return "forward:" + path;
//        }
//        return "forward:/";
//    }
}
