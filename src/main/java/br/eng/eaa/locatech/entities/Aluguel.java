package br.eng.eaa.locatech.entities;

import br.eng.eaa.locatech.dto.AluguelRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Aluguel {
    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private String veiculoModelo;
    private String pessoaCpf;
    private String pessoaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public Aluguel() {
    }

    public Aluguel(Long id, Long pessoaId, Long veiculoId, String veiculoModelo, String pessoaCpf, String pessoaNome, LocalDate dataInicio, LocalDate dataFim, BigDecimal valorTotal) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.veiculoId = veiculoId;
        this.veiculoModelo = veiculoModelo;
        this.pessoaCpf = pessoaCpf;
        this.pessoaNome = pessoaNome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
    }

    public Aluguel(AluguelRequestDTO alugueltDTO, BigDecimal valor) {
        this.pessoaId = alugueltDTO.pessoaId();
        this.veiculoId = alugueltDTO.veiculoId();
        this.dataInicio = alugueltDTO.dataInicio();
        this.dataFim = alugueltDTO.dataFim();
        this.valorTotal = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getVeiculoModelo() {
        return veiculoModelo;
    }

    public void setVeiculoModelo(String veiculoModelo) {
        this.veiculoModelo = veiculoModelo;
    }

    public String getPessoaCpf() {
        return pessoaCpf;
    }

    public void setPessoaCpf(String pessoaCpf) {
        this.pessoaCpf = pessoaCpf;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
