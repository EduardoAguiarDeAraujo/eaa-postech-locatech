package br.eng.eaa.locatech.controller;

import br.eng.eaa.locatech.dto.AluguelRequestDTO;
import br.eng.eaa.locatech.entities.Aluguel;
import br.eng.eaa.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/alugueis")
@Tag(name = "Alugueis", description = "Endpoints para gerenciamento de alugueis")
public class AluguelControllerV2 {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final AluguelService aluguelService;

    public AluguelControllerV2(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }


    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("Foi acessado o endpoint de alugueis /alugueis");
        var pessoas = this.aluguelService.findAllAluguel(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(@PathVariable("id") Long id) {
        logger.info("/alugueis/" + id);
        var pessoa = aluguelService.findAluguelById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping(produces = "application/vnd.locatech.v2+json")
    public ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDTO aluguel){
        logger.info("POST -> /alugueis");
        aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PostMapping(produces = "application/vnd.locatech.v1+json")
    public ResponseEntity<Void> saveAluguelV1(@Valid @RequestBody AluguelRequestDTO aluguel){
        logger.info("POST -> /alugueis");
        aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@RequestBody Aluguel aluguel, @PathVariable("id") Long id ){
        logger.info("PUT -> /alugueis/" + id);
        aluguelService.updateAluguel(aluguel, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable("id") Long id){
        logger.info("DELETE -> /alugueis/" +id);
        aluguelService.deleteAluguel(id);
        return ResponseEntity.ok().build();
    }


}
