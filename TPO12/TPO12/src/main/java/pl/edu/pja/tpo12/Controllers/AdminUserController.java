package pl.edu.pja.tpo12.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo12.Repositories.UserRoleRepository;
import pl.edu.pja.tpo12.Services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminUserController {

    private final UserService userService;
    private final UserRoleRepository roleRepository;

    public AdminUserController(UserService userService, UserRoleRepository roleRepo) {
        this.userService = userService;
        this.roleRepository = roleRepo;
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "admin-users";
    }

    @PostMapping("/users/{id}/roles")
    public String updateUserRoles(@PathVariable Long id,
                                  @RequestParam(required = false) List<String> roles) {
        Set<String> roleSet = roles != null ? new HashSet<>(roles) : Set.of();
        userService.updateRoles(id, roleSet);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleRepository.findAll());
        return "admin-user-detail";
    }

    @PostMapping("/users/{id}/reset-password")
    public String resetUserPassword(@PathVariable Long id,
                                    @RequestParam String newPassword) {
        userService.resetPassword(id, newPassword);
        return "redirect:/admin/users";
    }
}
