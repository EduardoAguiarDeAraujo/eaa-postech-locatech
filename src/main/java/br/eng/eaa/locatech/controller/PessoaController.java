package br.eng.eaa.locatech.controller;

import br.eng.eaa.locatech.entities.Pessoa;
import br.eng.eaa.locatech.services.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de pessoas")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("Foi acessado o endpoint de pessoas /pessoas");
        var pessoas = this.pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findVeiculo(@PathVariable("id") Long id) {
        logger.info("/pessoas/" + id);
        var pessoa = pessoaService.findVeiculoById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(@RequestBody Pessoa pessoa){
        logger.info("POST -> /pessoas");
        pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@RequestBody Pessoa pessoa, @PathVariable("id") Long id ){
        logger.info("PUT -> /pessoas/" + id);
        pessoaService.updatePessoa(pessoa, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id){
        logger.info("DELETE -> /pessoas/" +id);
        pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }

}
