package com.anxi.webserviceproject.service.posts;

import com.anxi.webserviceproject.domain.posts.Posts;
import com.anxi.webserviceproject.domain.posts.PostsRepository;
import com.anxi.webserviceproject.web.dto.PostsListResponseDto;
import com.anxi.webserviceproject.web.dto.PostsResponseDto;
import com.anxi.webserviceproject.web.dto.PostsSaveRequestDto;
import com.anxi.webserviceproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. Id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. Id = " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 조회 속도 향상(읽기만 가능)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(posts -> new PostsListResponseDto(posts)) // posts(findALlDest()의 리턴) -> PostsListResponseDto로 변환
                .collect(Collectors.toList());                 // PostsListResponseDto -> list로 변환
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
