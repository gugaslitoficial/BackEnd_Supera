package br.com.banco.controller;

import br.com.banco.models.Transferencia;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferenciaController {


    @Autowired
    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController( TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    @ResponseBody
    public List<Transferencia> pesquisaTransferencias(@RequestParam(value = "dataInicio",required = false) String dataInicio, @RequestParam(value = "dataFim",required = false) String dataFim, @RequestParam(value = "nomeOperador",required = false) String nomeOperador){
      return this.transferenciaService.pesquisar(dataInicio,dataFim,nomeOperador);
    }

}
