package br.eng.eaa.locatech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @Schema(description = "ID da pessoa", example = "1")
        @NotNull(message = "ID do veículo não pode ser nulo")
        Long pessoaId,

        @Schema(description = "ID do veículo", example = "1")
        @NotNull(message = "ID da pessoa não pode ser nulo")
        Long veiculoId,

        @NotNull(message = "Data de início não pode ser nula")
        LocalDate dataInicio,

        @NotNull(message = "Data de fim não pode ser nula")
        LocalDate dataFim
) {
    public AluguelRequestDTO {
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("Data de início não pode ser posterior à data de fim");
        }
    }
}
