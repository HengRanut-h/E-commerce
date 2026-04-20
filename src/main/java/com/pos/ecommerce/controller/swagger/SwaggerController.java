package com.pos.ecommerce.controller.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class SwaggerController {
    @GetMapping(value = {"/api-docs","/api-doc"})
    public RedirectView apiDoc() {
        return new RedirectView("/swagger-ui/index.html");
    }
}