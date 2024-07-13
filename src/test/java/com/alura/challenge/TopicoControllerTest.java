package com.alura.challenge;

import com.alura.challenge.model.Topico;
import com.alura.challenge.repository.TopicoRepository;
import com.alura.challenge.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TopicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private Topico topico;

    @BeforeEach
    public void setUp() {
        topico = new Topico();
        topico.setTitulo("Título");
        topico.setMensagem("Mensagem");
        topicoRepository.save(topico);
    }

    @Test
    @WithMockUser
    public void testListar() throws Exception {
        mockMvc.perform(get("/topicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Título"));
    }

    @Test
    @WithMockUser
    public void testBuscarPorId() throws Exception {
        mockMvc.perform(get("/topicos/" + topico.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Título"));
    }

    @Test
    @WithMockUser
    public void testCriar() throws Exception {
        String novoTopicoJson = "{\"titulo\": \"Novo Título\", \"mensagem\": \"Nova Mensagem\"}";

        mockMvc.perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoTopicoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Novo Título"));
    }

    @Test
    @WithMockUser
    public void testAtualizar() throws Exception {
        String atualizarTopicoJson = "{\"titulo\": \"Título Atualizado\", \"mensagem\": \"Mensagem Atualizada\"}";

        mockMvc.perform(put("/topicos/" + topico.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizarTopicoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Título Atualizado"));
    }

    @Test
    @WithMockUser
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/topicos/" + topico.getId()))
                .andExpect(status().isNoContent());
    }
}
