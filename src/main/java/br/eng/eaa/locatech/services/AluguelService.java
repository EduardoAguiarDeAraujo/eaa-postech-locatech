package br.eng.eaa.locatech.services;

import br.eng.eaa.locatech.dto.AluguelRequestDTO;
import br.eng.eaa.locatech.entities.Aluguel;
import br.eng.eaa.locatech.exceptions.ResourceNotFoundException;
import br.eng.eaa.locatech.repositories.AluguelRepository;
import br.eng.eaa.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAluguel(int page, int size){
        int offset = (page - 1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById (Long id){
        return Optional.ofNullable(this.aluguelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDTO aluguelDTO){
        var aluguel = calculaAluguel(aluguelDTO);
        var save = aluguelRepository.save(aluguel);
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguel.getId());
    }

    public void updateAluguel (Aluguel pessoa, Long id){
        var update = aluguelRepository.update(pessoa, id);
        if (update == 0){
            throw new RuntimeException("Aluguel não encontrado");
        }
    }

    public void deleteAluguel(@PathVariable("id") Long id){
        var delete = aluguelRepository.delete(id);
        if (delete == 0){
            throw new RuntimeException("Aluguel não encontrado");
        }
    }

    private Aluguel calculaAluguel(AluguelRequestDTO aluguelRequestDTO){
        var veiculo = veiculoRepository.findById(aluguelRequestDTO.veiculoId()).orElseThrow(() -> new RuntimeException(("Veículo não encontrado")));
        var quantidadeDias = BigDecimal.valueOf(aluguelRequestDTO.dataFim().getDayOfYear() - aluguelRequestDTO.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDias);
        return new Aluguel(aluguelRequestDTO, valor);
    }


}
