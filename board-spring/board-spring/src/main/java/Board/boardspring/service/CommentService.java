package Board.boardspring.service;

import Board.boardspring.dto.CommentDTO;
import Board.boardspring.entity.BoardEntity;
import Board.boardspring.entity.CommentEntity;
import Board.boardspring.repository.BoardRepository;
import Board.boardspring.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    public void save(CommentDTO commentDTO) {
        // 게시물 번호와 댓글이 달린 게시물의 번호가 같을 때 저장한다.
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getId());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            commentRepository.save(commentEntity);
        }
    }
}
