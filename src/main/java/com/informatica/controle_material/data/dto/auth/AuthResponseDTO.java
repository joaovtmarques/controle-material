package com.informatica.controle_material.data.dto.auth;

import com.informatica.controle_material.domain.model.User;

public record AuthResponseDTO(
        String token,
        User user) {

}
