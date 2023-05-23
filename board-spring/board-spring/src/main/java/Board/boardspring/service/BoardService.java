package Board.boardspring.service;

import Board.boardspring.dto.BoardDTO;
import Board.boardspring.entity.Board;
import Board.boardspring.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity (Entity Class에서 변환)
// Entity -> DTO (DTO Class에서 변환)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // Service -> Repository DI

    public void save(BoardDTO boardDTO){
        Board board = Board.toSaveEntity(boardDTO);
        boardRepository.save(board); //save는 entity 타입으로.. Repository는 Entity이기 때문에 DTO를 Entity로 넘겨야한다.
    }

    public List<BoardDTO> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        // Entity 객체를 Dto로 옮겨담기
        for(Board board : boardList){
            boardDTOList.add(BoardDTO.toBoardDTO(board));
        }
        return boardDTOList;
    }

    @Transactional //@Transactional은 jpa가 영속성 관리해줌. // 별도 추가된 메서드에 추가
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            Board board = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(board);
            return boardDTO;
        }
        return null;
    }


    public BoardDTO update(BoardDTO boardDTO) {
        Board board = Board.toUpdateEntity(boardDTO);
        boardRepository.save(board);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
