package com.infra.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Info", description = "Informations sur l'application")
public class InfoController {

    @Value("${app.name:infra-demo}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @GetMapping("/info")
    @Operation(summary = "Retourne le nom et la version de l'application")
    public Map<String, String> getInfo() {
        return Map.of(
            "appName", appName,
            "version", appVersion,
            "status", "UP"
        );
    }
}
