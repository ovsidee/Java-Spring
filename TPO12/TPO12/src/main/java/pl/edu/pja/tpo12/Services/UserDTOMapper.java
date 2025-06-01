package pl.edu.pja.tpo12.Services;

import pl.edu.pja.tpo12.Models.AppUser;
import pl.edu.pja.tpo12.Models.DTO.UserDTO;

import java.util.stream.Collectors;

public class UserDTOMapper {

    private UserDTOMapper() { }

    public static UserDTO map(AppUser user) {
        return new UserDTO(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> "ROLE_" + role.getName())
                        .collect(Collectors.toSet()));
    }
}
