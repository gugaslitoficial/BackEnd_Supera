package br.com.banco.repository;

import br.com.banco.models.Transferencia;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransferenciaRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public List<Transferencia> pesquisarTransferenciaPorFiltro(String dataInicio, String dataFim, String nomeOperador){

        LocalDateTime dataInicioEmData = null;
        LocalDateTime dataFimEmData = null;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transferencia> criteriaQuery = criteriaBuilder.createQuery(Transferencia.class);

        Root<Transferencia> transferenciaRoot = criteriaQuery.from(Transferencia.class);
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.isNoneBlank(dataInicio)){

            dataInicioEmData =  LocalDate.parse(dataInicio,DateTimeFormatter.ofPattern("dd/MM/yyyy")).atTime(00,00,00);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(transferenciaRoot.get("dataTransferencia"), dataInicioEmData));
        }

        if(StringUtils.isNoneBlank(dataFim)){
            dataFimEmData =  LocalDate.parse(dataFim,DateTimeFormatter.ofPattern("dd/MM/yyyy")).atTime(23,59,59);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(transferenciaRoot.get("dataTransferencia"), dataFimEmData));
        }

        if(StringUtils.isNoneBlank(nomeOperador)){
            predicates.add(criteriaBuilder.equal(transferenciaRoot.get("nomeOperadorTransacao"), nomeOperador));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
