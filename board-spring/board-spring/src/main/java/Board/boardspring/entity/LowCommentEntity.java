package Board.boardspring.entity;

import Board.boardspring.controller.request.CommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.stream.events.Comment;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class LowCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 500)
    private String commentContents;

    // board : comment = 1 : N 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId")
    private CommentEntity commentEntity;

    @Column(name = "commentId", insertable = false, updatable = false)
    private Long commentId;


    public static LowCommentEntity toAddEntity(CommentRequest request, CommentEntity commentEntity) {
        LowCommentEntity lowCommentEntity = new LowCommentEntity();
        lowCommentEntity.setCommentWriter(request.getCommentWriter());
        lowCommentEntity.setCommentContents(request.getCommentContents());
        lowCommentEntity.setCommentEntity(commentEntity);
        return lowCommentEntity;
    }
}
