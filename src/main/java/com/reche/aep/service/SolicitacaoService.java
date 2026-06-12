package com.reche.aep.service;

import com.reche.aep.model.Solicitacao;
import com.reche.aep.model.StatusUpdate;
import com.reche.aep.repository.SolicitacaoRepository;
import com.reche.aep.repository.StatusUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private StatusUpdateRepository statusUpdateRepository;

    public Solicitacao criarSolicitacao(Solicitacao solicitacao) {
        solicitacao.setProtocolo("PROT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return solicitacaoRepository.save(solicitacao);
    }

    public List<Solicitacao> listarTodas() {
        return solicitacaoRepository.findAll();
    }

    public Optional<Solicitacao> buscarPorProtocolo(String protocolo) {
        return solicitacaoRepository.findByProtocolo(protocolo);
    }

    public Solicitacao atualizarStatus(String protocolo, String novoStatus, String comentario) {
        Solicitacao solicitacao = solicitacaoRepository.findByProtocolo(protocolo)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        solicitacao.setStatus(novoStatus);

        StatusUpdate update = new StatusUpdate(solicitacao, novoStatus, comentario);
        statusUpdateRepository.save(update);

        return solicitacaoRepository.save(solicitacao);
    }
}
