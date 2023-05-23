package Board.boardspring.controller.request;

import lombok.Getter;

@Getter
public class CommentRequest {
    private Long id;
    private Long boardId;
    private Long commentId;
    private String commentWriter;
    private String commentContents;
}
