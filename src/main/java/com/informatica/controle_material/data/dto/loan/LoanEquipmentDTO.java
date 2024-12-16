package com.informatica.controle_material.data.dto.loan;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.informatica.controle_material.data.dto.equipment.EquipmentDTO;

public record LoanEquipmentDTO(
    @NotNull(message = "Informe a quantidade do item") Integer loanAmount,

    @NotNull(message = "Informe a data do empréstimo") String loanDate,

    @NotNull(message = "Informe o nome do receptor") String receiverName,

    @NotNull(message = "Informe a missão") String mission,

    @NotNull(message = "A lista de equipamentos não pode ser nula") @Size(min = 1, message = "A lista de equipamentos deve conter pelo menos um equipamento") List<EquipmentDTO> equipments) {
}
