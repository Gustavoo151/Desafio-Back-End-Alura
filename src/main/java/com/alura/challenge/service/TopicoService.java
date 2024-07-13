package com.alura.challenge.service;


import com.alura.challenge.model.Topico;
import com.alura.challenge.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico criar(Topico topico) {
        return topicoRepository.save(topico);
    }

    public void deletar(Long id) {
        topicoRepository.deleteById(id);
    }

    public Topico atualizar(Long id, Topico topicoAtualizado) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = optional.get();
            topico.setTitulo(topicoAtualizado.getTitulo());
            topico.setMensagem(topicoAtualizado.getMensagem());
            return topicoRepository.save(topico);
        }
        return null;
    }
}
