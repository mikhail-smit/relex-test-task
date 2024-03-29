package mikhail.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mikhail.task.dto.AuthDTO;
import mikhail.task.dto.JwtDTO;
import mikhail.task.services.RoleService;
import mikhail.task.services.UserService;
import mikhail.task.services.WorkerDetailsService;
import mikhail.task.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user authorization.
 * Also, owner can add ROLE_ADMIN to employees here.
 */
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Methods for authentication and role assignment")
public class AuthController {

    private final AuthenticationManager authManager;
    private final WorkerDetailsService workerDetailsService;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping
    @Operation(summary = "User authenticate")
    public ResponseEntity<JwtDTO> auth(@RequestBody AuthDTO auth) {
        UserDetails userDetails = (UserDetails) authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        auth.getEmail(),
                        auth.getPassword()
                )).getPrincipal();

        return ResponseEntity.ok(new JwtDTO(jwtUtils.getToken(userDetails)));
    }

    /**
     * User with {id} became admin
     */
    @PostMapping("/admin/{id}")
    @Operation(summary = "Admin assignment by Id")
    public void makeUserAdmin(@PathVariable int id) {
        userService.addRole(id, roleService.getByName("ROLE_ADMIN"));
    }
}
