package Board.boardspring.controller.reponse;

import Board.boardspring.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentResponse {

    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContents;

    @Builder(access = AccessLevel.PRIVATE)
    private CommentResponse(Long id, Long boardId, String commentWriter, String commentContents) {
        this.id = id;
        this.boardId = boardId;
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
    }

    public static List<CommentResponse> fromList(List<CommentDTO> list) {
        return list.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    public static CommentResponse from(CommentDTO entity) {
        return CommentResponse.builder()
                .commentContents(entity.getCommentContents())
                .commentWriter(entity.getCommentWriter())
                .id(entity.getId())
                .boardId(entity.getBoardId())
                .build();
    }

}
