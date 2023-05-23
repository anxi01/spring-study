package Board.boardspring.dto;

import Board.boardspring.entity.Board;
import lombok.*;

/** DTO(Data Transfer Object) : 데이터를 객체에게 전달 */
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;

    public static BoardDTO toBoardDTO(Board board){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setBoardWriter(board.getBoardWriter());
        boardDTO.setBoardPass(board.getBoardPass());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContents(board.getBoardContents());
        boardDTO.setBoardHits(board.getBoardHits());
        return boardDTO;
    }
}
