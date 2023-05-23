package Board.boardspring.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentRequest {

    private final Long id;
    private final Long boardId;
    private final long parentId;
    private final String writer;
    private final String contents;

    public boolean isChildComment() {
        return parentId != 0;
    }

}
