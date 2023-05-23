package Board.boardspring.service;

import Board.boardspring.controller.request.CommentRequest;
import Board.boardspring.dto.CommentDTO;
import Board.boardspring.entity.Board;
import Board.boardspring.entity.Comment;
import Board.boardspring.repository.BoardRepository;
import Board.boardspring.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public List<CommentDTO> findAll(Long boardId) {
        final List<Comment> list = commentRepository.findAllByBoardId(boardId);
        return CommentDTO.fromList(list);
    }

    public void save(CommentRequest request) {
        // 게시물 번호와 댓글이 달린 게시물의 번호가 같을 때 저장한다.
        final Optional<Board> optionalBoardEntity = boardRepository.findById(request.getBoardId());
        if(optionalBoardEntity.isPresent()){
            final Board board = optionalBoardEntity.get();

            // Optional<Comment> parentComment = commentRepository.findById(request.getParentId()).get();
            // null일수도 있다.

            // Optional<Comment> parentComment = commentRepository.findById(request.getParentId()).orElseGet(() -> new Comment());
            // null이면 new Comment()을 가져온다.

            parentCommentExistValid(request);
            
            final Comment comment = Comment.of(request, board);
            commentRepository.save(comment);
        }
    }

    private void parentCommentExistValid(CommentRequest request) {
        if (request.isChildComment()) {
            commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 ParentId 입니다."));
        }
    }
}
