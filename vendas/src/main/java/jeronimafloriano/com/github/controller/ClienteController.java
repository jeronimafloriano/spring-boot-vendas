package jeronimafloriano.com.github.controller;

import jeronimafloriano.com.github.domain.entity.Cliente;
import jeronimafloriano.com.github.domain.repository.ClienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(value = {"/hello/{nome}", "/api/hello"}, method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente){
        return "Hello " + nomeCliente;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deletar(@PathVariable Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
