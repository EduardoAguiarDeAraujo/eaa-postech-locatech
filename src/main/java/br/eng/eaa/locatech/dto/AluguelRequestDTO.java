package br.eng.eaa.locatech.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @NotNull(message = "ID do veículo não pode ser nulo")
        Long pessoaId,
        @NotNull(message = "ID da pessoa não pode ser nulo")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim) {
    public AluguelRequestDTO {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Data de início e fim não podem ser nulas");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("Data de início não pode ser posterior à data de fim");
        }
    }
}
