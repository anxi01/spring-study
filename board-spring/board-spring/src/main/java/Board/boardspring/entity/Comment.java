package Board.boardspring.entity;

import Board.boardspring.controller.request.CommentRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column(length = 500)
    private String contents;

    // board : comment = 1 : N 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @Column(name = "boardId", insertable = false, updatable = false)
    private Long boardId;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Comment> child = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Comment(String writer, String contents, Board board, Long boardId, Long parentId, List<Comment> child) {
        this.writer = writer;
        this.contents = contents;
        this.board = board;
        this.boardId = boardId;
        this.parentId = parentId;
        this.child = child;
    }

    // 메소드 이름이 from -> 단일 파라미터를 받는다
    // 메소드 이름이 of -> 여러개의 파라미터를 받는다.
    public static Comment of(CommentRequest request, Board board) {
        return Comment.builder()
                .writer(request.getWriter())
                .parentId(request.getParentId())
                .contents(request.getContents())
                .board(board)
                .build();
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
