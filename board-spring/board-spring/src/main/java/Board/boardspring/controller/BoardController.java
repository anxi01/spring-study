package Board.boardspring.controller;

import Board.boardspring.dto.BoardDTO;
import Board.boardspring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // 생성자를 작성하지 않고도 생성자 주입을 할 수 있다.
@RequestMapping("/board") /** board 이하의 /주소를 매핑해서 호출 */
public class BoardController {

    private final BoardService boardService; // Controller -> Service DI

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){  //ModelAttribute -> save.html의 name과 BoardDTO 객체와 매핑해줌
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }
}
