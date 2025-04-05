package br.eng.eaa.locatech.repositories;

import br.eng.eaa.locatech.entities.Aluguel;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AluguelRepositoryImp implements AluguelRepository{

    private final JdbcClient jdbcClient;

    public AluguelRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    //id, pessoaId, veiculoId, veiculoModelo, pessoaCpf, pessoaNome, dataInicio, dataFim, valorTotal
    @Override
    public Optional<Aluguel> findById(Long id) {
        return this.jdbcClient
                .sql("Select a.id, a.pessoa_id, a.veiculo_id, a.data_inicio, a.data_fim, a.valor_total, " +
                        "p.nome as pessoa_nome, p.cpf as pessoa_cpf, v.modelo as veiculo, v.placa as veiculo_placa " +
                        "From alugueis a " +
                        "Inner Join pessoas p On a.pessoa_id = p.id " +
                        "Inner Join veiculos v On a.veiculo_id = v.id " +
                        "Where a.id = :id")
                .param("id", id)
                .query(Aluguel.class)
                .optional();
    }

    @Override
    public List<Aluguel> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("Select a.id, a.pessoa_id, a.veiculo_id, a.data_inicio, a.data_fim, a.valor_total, " +
                        "p.nome as pessoa_nome, p.cpf as pessoa_cpf, v.modelo as veiculoModelo, v.placa as veiculo_placa " +
                        "From alugueis a " +
                        "Inner Join pessoas p On a.pessoa_id = p.id " +
                        "Inner Join veiculos v On a.veiculo_id = v.id " +
                        "Limit :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Aluguel.class)
                .list();
    }

    @Override
    public Integer save(Aluguel aluguel) {
        return this.jdbcClient
                .sql("Insert into alugueis (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total) values (:pessoa_id, :veiculo_id, :data_inicio, :data_fim, :valor_total)")
                .param("pessoa_id", aluguel.getPessoaId())
                .param("veiculo_id", aluguel.getVeiculoId())
                .param("data_inicio", aluguel.getDataInicio())
                .param("data_fim", aluguel.getDataFim())
                .param("valor_total", aluguel.getValorTotal())
                .update();}

    @Override
    public Integer update(Aluguel aluguel, Long id) {
        return this.jdbcClient
                .sql("Update alugueis set pessoa_id = :pessoa_id, veiculo_id = :veiculo_id, data_inicio = :data_inicio, data_fim = :data_fim, valor_total = :valor_total where id = :id")
                .param("pessoa_id", aluguel.getPessoaId())
                .param("veiculo_id", aluguel.getVeiculoId())
                .param("data_inicio", aluguel.getDataInicio())
                .param("data_fim", aluguel.getDataFim())
                .param("valor_total", aluguel.getValorTotal())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("Delete from alugueis where id = :id")
                .param("id", id)
                .update();
    }
}


