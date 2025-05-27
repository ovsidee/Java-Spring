package pl.edu.pja.tpo11.Controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.tpo11.Services.LinkService;

@RestController()
@RequestMapping(path = "/api/red", produces = "application/json")
public class RedirectionController {

    public final LinkService linkService;

    public RedirectionController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirect(@PathVariable String id) {
        return linkService.findById(id)
                .map(link -> {
                    linkService.incrementVisitCount(link);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(java.net.URI.create(link.getTargetUrl()));
                    return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}