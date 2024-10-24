package com.informatica.controle_material.data.dto.equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateEquipmentDTO(
    @NotBlank(message = "Uma condição do equipamento deve ser informada") String condition,
    @NotNull(message = "Informe se o equipamento está em carga") Boolean isInCharge) {

}
