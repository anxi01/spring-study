package Board.boardspring.service;

import Board.boardspring.dto.BoardDTO;
import Board.boardspring.entity.BoardEntity;
import Board.boardspring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// DTO -> Entity (Entity Class에서 변환)
// Entity -> DTO (DTO Class에서 변환)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // Service -> Repository DI

    public void save(BoardDTO boardDTO){
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); //save는 entity 타입으로.. Repository는 Entity이기 때문에 DTO를 Entity로 넘겨야한다.
    }
}
