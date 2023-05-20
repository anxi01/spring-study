package Board.boardspring.controller;

import Board.boardspring.common.ApiResponse;
import Board.boardspring.controller.reponse.CommentResponse;
import Board.boardspring.controller.request.CommentRequest;
import Board.boardspring.dto.CommentDTO;
import Board.boardspring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자를 작성하지 않고도 생성자 주입을 할 수 있다.
@RequestMapping("/board-api/comments") /** board 이하의 /주소를 매핑해서 호출 */
public class CommentApiController {

    // spring 특징 ioc, di, aop
    private final CommentService commentService;

    // 동일 path는 메소드 타입으로 구별을 한다.
    // method: GET, POST, PUT, DELETE, PATCH

    @GetMapping("/{boardId}")
    public ApiResponse<List<CommentResponse>> findCommentAll(@PathVariable Long boardId){
        return ApiResponse.ok(CommentResponse.fromList(commentService.findAll(boardId)));
    }

    // restful api 규칙을 통한 통신
    @PostMapping
    public ApiResponse<String> commentSave(@RequestBody CommentRequest request){
        commentService.save(request);
        return ApiResponse.ok();
    }

    @PostMapping("/{boardId}/{id}")
    public ApiResponse<String> commentAdd(@RequestBody CommentRequest request){
        commentService.addComment(request);
        return ApiResponse.ok();
    }
}
