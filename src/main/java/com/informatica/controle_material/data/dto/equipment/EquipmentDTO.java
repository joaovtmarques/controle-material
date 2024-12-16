package com.informatica.controle_material.data.dto.equipment;

import jakarta.validation.constraints.NotNull;

public record EquipmentDTO(
    @NotNull(message = "Informe o nome do equipamento") String name,

    @NotNull(message = "Informe a quantidade do equipamento") Integer amount,

    @NotNull(message = "Informe o número de série do equipamento") String serialNumber,

    @NotNull(message = "Informe se o equipamento é temporário") Boolean isTemporary) {
}
