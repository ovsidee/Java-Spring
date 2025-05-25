package pl.edu.pja.tpo10.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/links", produces = "application/json")
public class LinkController {

    @PostMapping
    public void addLink() {

    }

}
