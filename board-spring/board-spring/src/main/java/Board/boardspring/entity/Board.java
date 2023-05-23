package Board.boardspring.entity;

import Board.boardspring.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** DB의 테이블 역할을 하는 클래스 .. JPA에서 자주 사용 */
@Entity
@Getter
@Setter
@Table(name = "board")
public class Board {
    @Id // pk 지정. 필수
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    /** DTO 값을 Entity로 저장하는 메서드 */
    public static Board toSaveEntity(BoardDTO boardDTO){
        Board board = new Board();
        // boardDTO에서 DTO 값을 get해서 Entity에 set한다.
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardPass(boardDTO.getBoardPass());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents((boardDTO.getBoardContents()));
        board.setBoardHits(0);
        return board;
    }

    public static Board toUpdateEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setId(boardDTO.getId());
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardPass(boardDTO.getBoardPass());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents((boardDTO.getBoardContents()));
        board.setBoardHits(boardDTO.getBoardHits());
        return board;
    }
}
