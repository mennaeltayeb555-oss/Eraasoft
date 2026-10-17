package org.example.springsecurity.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/reports")
public class ReportController {
 @GetMapping @PreAuthorize("hasAuthority('REPORT_VIEW') or hasAuthority('ROLE_ADMIN')")
 public Map<String,Object> report(){return Map.of("message","Report endpoint placeholder","note","Replace with real report aggregation.");}
}
