package Board.boardspring.repository;

import Board.boardspring.entity.LowCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LowCommentRepository extends JpaRepository<Long, LowCommentEntity> {

}
