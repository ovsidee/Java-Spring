package pl.edu.pja.tpo11.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.DTO.LinkUpdateDTO;
import pl.edu.pja.tpo11.Services.LinkDTOMapper;
import pl.edu.pja.tpo11.Services.LinkService;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/links", produces = "application/json")
public class LinkControllerAPI {

    public final LinkService service;

    public LinkControllerAPI(LinkService service) {
        this.service = service;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleValidation(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));
    }

    @PostMapping
    public ResponseEntity<LinkDTO> create(@Valid @RequestBody LinkDTO linkDTO,
                                          HttpServletRequest request) {
        var link = service.create(linkDTO);

        if (link == null)
            return ResponseEntity.badRequest().build();

        var dto = LinkDTOMapper.toDTO(link);

        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        String fullUrl = baseUrl + "/api/links/" + dto.id;

        return ResponseEntity
                .created(URI.create(fullUrl))
                .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDTO> read(@PathVariable String id) {
        if (id == null || id.isBlank())
            return ResponseEntity.badRequest().build();

        return service.findById(id)
                .map(LinkDTOMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @Valid @RequestBody LinkUpdateDTO linkUpdateDTO) {
        try {
            boolean updated = service.update(id, linkUpdateDTO);

            if (!updated)
                return ResponseEntity.notFound().build();

            return ResponseEntity.noContent().build();

        } catch (ResponseStatusException statusGotten) {
            if (statusGotten.getStatusCode() == HttpStatus.FORBIDDEN) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .header("reason", statusGotten.getReason())
                        .build();
            }
            return ResponseEntity.status(statusGotten.getStatusCode()).build();
        }
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
