package Board.boardspring.entity;

import Board.boardspring.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** DB의 테이블 역할을 하는 클래스 .. JPA에서 자주 사용 */
@Entity
@Getter
@Setter
//@Table(name = "board_table")
public class BoardEntity {
    @Id // pk 지정. 필수
    @GeneratedValue // id값 증가
    private long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    /** DTO 값을 Entity로 저장하는 메서드 */
    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        // boardDTO에서 DTO 값을 get해서 Entity에 set한다.
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents((boardDTO.getBoardContents()));
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
