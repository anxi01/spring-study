package Board.boardspring.service;

import Board.boardspring.controller.request.CommentRequest;
import Board.boardspring.dto.BoardDTO;
import Board.boardspring.dto.CommentDTO;
import Board.boardspring.entity.BoardEntity;
import Board.boardspring.entity.CommentEntity;
import Board.boardspring.entity.LowCommentEntity;
import Board.boardspring.repository.BoardRepository;
import Board.boardspring.repository.CommentRepository;
import Board.boardspring.repository.LowCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final LowCommentRepository lowCommentRepository;

    public List<CommentDTO> findAll(Long boardId) {
        final List<CommentEntity> list = commentRepository.findAllByBoardId(boardId);
        return CommentDTO.fromList(list);
    }

    public void save(CommentRequest request) {
        // 게시물 번호와 댓글이 달린 게시물의 번호가 같을 때 저장한다.
        final Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(request.getBoardId());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            //CommentEntity commentEntity = CommentEntity.toSaveEntity(request, boardEntity);
            CommentEntity commentEntity = new CommentEntity();
            LowCommentEntity lowCommentEntity = LowCommentEntity.toAddEntity(request, commentEntity);
            commentEntity = CommentEntity.toSaveEntity(lowCommentEntity, boardEntity);
            commentRepository.save(commentEntity);
        }
    }
}
