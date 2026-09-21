package org.example.task9.task2.email;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.task9.task2.email.dto.EmailRequestDTO;
import org.example.task9.task2.email.dto.EmailResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailResponseDTO> createEmail(@Valid @RequestBody EmailRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.createEmail(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailResponseDTO> updateEmail(
            @PathVariable Long id, @Valid @RequestBody EmailRequestDTO dto) {
        return ResponseEntity.ok(emailService.updateEmail(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDTO>> getAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }

    @GetMapping("/byName")
    public ResponseEntity<List<EmailResponseDTO>> getEmailsByName(@RequestParam String name) {
        return ResponseEntity.ok(emailService.getEmailsByName(name));
    }

    @GetMapping("/byNames")
    public ResponseEntity<List<EmailResponseDTO>> getEmailsByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(emailService.getEmailsByNames(names));
    }

    @GetMapping("/byContent")
    public ResponseEntity<EmailResponseDTO> getEmailByContent(@RequestParam String content) {
        return ResponseEntity.ok(emailService.getEmailByContent(content));
    }
}
