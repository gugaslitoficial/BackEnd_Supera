package br.com.banco.service;

import br.com.banco.models.Transferencia;
import br.com.banco.repository.TransferenciaRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransferenciaService {


    @Autowired
    private TransferenciaRepositoryCustom transferenciaRepositoryCustom;

    public List<Transferencia> pesquisar(String dataInicio, String dataFim, String nomeOperador){

        log.info("Iniciando pesquisa com os parametros dataInicio:{} , dataFim:{},nomeOperado:{}",dataInicio,dataFim,nomeOperador);

        var transferenciasEncontradas =  transferenciaRepositoryCustom.pesquisarTransferenciaPorFiltro(dataInicio,dataFim,nomeOperador);

        log.info("Iniciando pesquisa com os parametros dataInicio:{} , dataFim:{},nomeOperado:{} acho um total de {}",dataInicio,dataFim,nomeOperador,transferenciasEncontradas.size());

        return transferenciasEncontradas;
    }
}
