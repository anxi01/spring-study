package Board.boardspring.controller;

import Board.boardspring.dto.BoardDTO;
import Board.boardspring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자를 작성하지 않고도 생성자 주입을 할 수 있다.
@RequestMapping("/board") /** board 이하의 /주소를 매핑해서 호출 */
public class BoardController {

    private final BoardService boardService; // Controller -> Service DI

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save") // 저장
    public String save(@ModelAttribute BoardDTO boardDTO){  //ModelAttribute -> save.html의 name과 BoardDTO 객체와 매핑해줌
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/") // 조회
    public String findAll(Model model){ // DB로부터 데이터를 가져올 땐 Model 객체를 사용
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        //경로상의 값을 가져올 때는 @PathVariable을 사용해서 가져온다.
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }
}
