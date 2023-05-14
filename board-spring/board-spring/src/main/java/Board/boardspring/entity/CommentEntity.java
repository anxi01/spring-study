package Board.boardspring.entity;

import Board.boardspring.controller.request.CommentRequest;
import Board.boardspring.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 500)
    private String commentContents;


    // board : comment = 1 : N 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardEntityiId")
    private BoardEntity boardEntity;

    @Column(name = "boardEntityiId", insertable = false, updatable = false)
    private Long boardId;


    public static CommentEntity toSaveEntity(CommentRequest request, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(request.getCommentWriter());
        commentEntity.setCommentContents(request.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
