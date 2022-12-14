package jeronimafloriano.com.github.controller;

import jeronimafloriano.com.github.domain.entity.Produto;
import jeronimafloriano.com.github.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping("/{id}")
    public Produto getById(@PathVariable Integer id){
        return produtosRepository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping("/findAll")
    public List<Produto> findAll(){
        return produtosRepository.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody @Valid Produto produto){
        return produtosRepository.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Produto produto){
        produtosRepository.findById(id)
                .map(produtoEncontrado -> {
                    produto.setId(produtoEncontrado.getId());
                    produtosRepository.save(produto);
                    return produto;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        produtosRepository.findById(id)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return produto;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/filtra")
    public List<Produto> filtroProdutos(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtosRepository.findAll(example);
    }

}
