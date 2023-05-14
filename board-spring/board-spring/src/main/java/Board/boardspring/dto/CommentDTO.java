package Board.boardspring.dto;

import Board.boardspring.entity.CommentEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDTO {

    private Long id;

    private Long boardId;
    private String commentWriter;
    private String commentContents;

    @Builder(access = AccessLevel.PRIVATE)
    private CommentDTO(Long id, Long boardId, String commentWriter, String commentContents) {
        this.id = id;
        this.boardId = boardId;
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
    }

    public static List<CommentDTO> fromList(List<CommentEntity> list) {
        return list.stream()
                .map(CommentDTO::from)
                .collect(Collectors.toList());
    }

    public static CommentDTO from(CommentEntity entity) {
        return CommentDTO.builder()
                .commentContents(entity.getCommentContents())
                .commentWriter(entity.getCommentWriter())
                .id(entity.getId())
                .boardId(entity.getBoardId())
                .build();
    }

}
