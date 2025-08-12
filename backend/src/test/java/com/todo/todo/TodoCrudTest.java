package com.todo.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoCrudTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    List<TodoItem> todoList = List.of(
            Utils.TodoItemFactory.createTodoItem("Comprar leite", "No supermercado"),
            Utils.TodoItemFactory.createTodoItem("Pagar contas", "Conta de luz e internet"),
            Utils.TodoItemFactory.createTodoItem("Estudar Java", "Fazer exercícios práticos")
    );


    @Test
    @WithMockUser()
    void createATodoList() throws Exception{

        TodoItem item = todoList.get(0);


        mockMvc.perform(post("/todo_list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoList.get(0)))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(item.getTitle()))
                .andExpect(jsonPath("$.description").value(item.getDescription()));
    }

    @Test
    @WithMockUser()
    void updateATodoList() throws Exception{
        TodoItem item = todoList.get(1);

        mockMvc.perform(put("/todo_list/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoList.get(1)))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(item.getTitle()))
                .andExpect(jsonPath("$.description").value(item.getDescription()));
    }

}
