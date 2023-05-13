package Board.boardspring.controller;

import Board.boardspring.common.ApiResponse;
import Board.boardspring.dto.BoardDTO;
import Board.boardspring.dto.CommentDTO;
import Board.boardspring.service.BoardService;
import Board.boardspring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자를 작성하지 않고도 생성자 주입을 할 수 있다.
@RequestMapping("/board-api") /** board 이하의 /주소를 매핑해서 호출 */
public class BoardApiController {

    // spring 특징 ioc, di, aop

    private final BoardService boardService; // Controller -> Service DI
    private final CommentService commentService;


    // restful api 규칙을 통한 통신
    @GetMapping
    public ApiResponse<List<BoardDTO>> findAll(){
        return ApiResponse.ok(boardService.findAll());
    }

    // restful api 규칙을 통한 통신
    @PostMapping
    public ApiResponse<BoardDTO> save(@RequestBody BoardDTO boardDTO){
        boardService.save(boardDTO);
        return ApiResponse.ok(boardDTO);
    }

    @GetMapping
    public ApiResponse<List<CommentDTO>> findAll(){
        return ApiResponse.ok(commentService.findAll());
    }

    // restful api 규칙을 통한 통신
    @PostMapping
    public ApiResponse<CommentDTO> save(@RequestBody CommentDTO commentDTO){
        commentService.save(commentDTO);
        return ApiResponse.ok(commentDTO);
    }

}
