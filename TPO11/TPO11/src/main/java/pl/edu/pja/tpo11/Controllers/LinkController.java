package pl.edu.pja.tpo11.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.Link;
import pl.edu.pja.tpo11.Services.LinkDTOMapper;
import pl.edu.pja.tpo11.Services.LinkService;

import java.net.URI;

@RestController
@RequestMapping(path = "api/links", produces = "application/json")
public class LinkController {

    public final LinkService service;

    public LinkController(LinkService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LinkDTO> create(@RequestBody LinkDTO linkDTO,
                                          HttpServletRequest request) {
        var link = service.create(linkDTO);
        var dto = LinkDTOMapper.toDTO(link);

        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        String fullUrl = baseUrl + "/api/links/" + dto.id;

        return ResponseEntity
                .created(URI.create(fullUrl))
                .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDTO> read(@PathVariable String id) {
        return service.findById(id)
                .map(LinkDTOMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @RequestBody LinkDTO linkToUpdateDTO) {
        var linkFoundedOptional = service.findById(id);
        if (linkFoundedOptional.isEmpty())
            return ResponseEntity.notFound().build();

        var link = linkFoundedOptional.get();
        if (link.getPassword() != null && !link.getPassword().isBlank())
            if (linkToUpdateDTO.password == null || !link.getPassword().equals(linkToUpdateDTO.password))
                return ResponseEntity.status(403).header("reason", "wrong password").build();

        if (linkToUpdateDTO.name != null)
            link.setName(linkToUpdateDTO.name);

        if (linkToUpdateDTO.targetUrl != null)
            link.setTargetUrl(linkToUpdateDTO.targetUrl);

        service.save(link);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id,
                                       @RequestParam(name = "pass", required = false) String password) {
        var optionalLink = service.findById(id);

        if (optionalLink.isEmpty())
            return ResponseEntity.noContent().build();

        var link = optionalLink.get();

        if (link.getPassword() != null && !link.getPassword().isBlank())
            if (password == null || !link.getPassword().equals(password))
                return ResponseEntity.status(403).header("reason", "wrong password").build();

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
