package pl.edu.pja.tpo10.Services;

import pl.edu.pja.tpo10.Models.DTO.LinkDTO;
import pl.edu.pja.tpo10.Models.Link;

public class LinkDTOMapper {

    public static LinkDTO toDTO(Link link) {
        LinkDTO dto = new LinkDTO();
        dto.id = link.getId();
        dto.name = link.getName();
        dto.targetUrl = link.getTargetUrl();
        dto.visits = link.getVisits();
        dto.redirectUrl = "http://localhost:8080/api/red/" + link.getId();
        return dto;
    }

    public static Link fromDTO(LinkDTO dto, String id) {
        return new Link(id, dto.name, dto.targetUrl, dto.password);
    }
}