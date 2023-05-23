package Board.boardspring.entity;

import Board.boardspring.controller.request.CommentRequest;
import Board.boardspring.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 500)
    private String commentContents;

    // board : comment = 1 : N 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardEntity boardEntity;

    @Column(name = "boardId", insertable = false, updatable = false)
    private Long boardId;

    @OneToMany(mappedBy = "commentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LowCommentEntity> lowCommentEntityList = new ArrayList<>();

    public static CommentEntity toSaveEntity(LowCommentEntity lowCommentEntity, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(lowCommentEntity.getCommentWriter());
        commentEntity.setCommentContents(lowCommentEntity.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
