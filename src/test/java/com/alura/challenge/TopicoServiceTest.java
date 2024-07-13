package com.alura.challenge;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alura.challenge.model.Topico;
import com.alura.challenge.repository.TopicoRepository;
import com.alura.challenge.service.TopicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TopicoServiceTest {

    @InjectMocks
    private TopicoService topicoService;

    @Mock
    private TopicoRepository topicoRepository;

    private Topico topico;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topico = new Topico();
        topico.setId(1L);
        topico.setTitulo("Título");
        topico.setMensagem("Mensagem");
    }

    @Test
    public void testListar() {
        when(topicoRepository.findAll()).thenReturn(Arrays.asList(topico));

        List<Topico> topicos = topicoService.listar();

        assertEquals(1, topicos.size());
        verify(topicoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        when(topicoRepository.findById(1L)).thenReturn(Optional.of(topico));

        Optional<Topico> resultado = topicoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(topico, resultado.get());
    }

    @Test
    public void testCriar() {
        when(topicoRepository.save(topico)).thenReturn(topico);

        Topico resultado = topicoService.criar(topico);

        assertNotNull(resultado);
        assertEquals(topico, resultado);
    }

    @Test
    public void testDeletar() {
        topicoService.deletar(1L);
        verify(topicoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAtualizar() {
        Topico atualizado = new Topico();
        atualizado.setTitulo("Novo Título");
        atualizado.setMensagem("Nova Mensagem");

        when(topicoRepository.findById(1L)).thenReturn(Optional.of(topico));
        when(topicoRepository.save(any(Topico.class))).thenReturn(atualizado);

        Topico resultado = topicoService.atualizar(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("Novo Título", resultado.getTitulo());
        assertEquals("Nova Mensagem", resultado.getMensagem());
    }
}
