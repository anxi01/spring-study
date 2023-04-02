package Board.boardspring.repository;

import Board.boardspring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity Class, pk 자료형>
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    
}
