package org.example.service;

import org.example.model.TodoEntity;
import org.example.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Mock객체 사용하기 위한 어노테이션
class TodoServiceTest {

    /**
     * Mock을 사용하는 이유
     * 1. 외부시스템에 의존하지 않고 자체 시스템에서 작동해야 함
     * (네트워크나 DB가 연결이 안된다고 해서 테스트가 안되면 안됨)
     * 
     * 2. 실제 DB를 사용하면 서비스에서 사용중인 데이터가 수정, 삭제될 수 있기 때문
     */
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        Mockito.when(todoRepository.save(ArgumentMatchers.any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    void searchById() {
    }
}