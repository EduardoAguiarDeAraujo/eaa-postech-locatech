package br.eng.eaa.locatech.entities;

import java.math.BigDecimal;

public class Veiculo {

        //marca, modelo, placa, ano, cor, valor_diaria
        private Long id;
        private String marca;
        private String modelo;
        private String placa;
        private String ano;
        private String cor;
        private BigDecimal valorDiaria;

        public Veiculo() {
        }
        public Veiculo(Long id, String marca, String modelo, String placa, String ano, String cor, BigDecimal valorDiaria) {
            this.id = id;
            this.marca = marca;
            this.modelo = modelo;
            this.placa = placa;
            this.ano = ano;
            this.cor = cor;
            this.valorDiaria = valorDiaria;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
