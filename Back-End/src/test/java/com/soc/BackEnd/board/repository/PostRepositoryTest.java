package com.soc.backend.board.repository;

import com.soc.backend.board.dto.GetPostsPageRes;
import com.soc.backend.board.entity.Board;
import com.soc.backend.board.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("Board로 Post 조회 테스트")
    @Test
    public void findByBoardTest(){
        //given
        Board testBoard = Board.builder()
                .korName("testBoard")
                .category("testCategory")
                .build();

        boardRepository.save(testBoard);

        Post post1 = Post.builder()
                .title("post1")
                .board(testBoard)
                .contents("test")
                .build();

        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("post2")
                .board(testBoard)
                .contents("test")
                .build();

        postRepository.save(post2);

        Post post3 = Post.builder()
                .title("post3")
                .board(testBoard)
                .contents("test")
                .build();

        postRepository.save(post3);

        //when
        Pageable pageable = Pageable.unpaged();
        Page<GetPostsPageRes> allByBoard = postRepository.findAllByBoard(pageable, testBoard);
        int contentCnt = allByBoard.getSize();
        int pageCnt = allByBoard.getTotalPages();


        //then
        Assertions.assertThat(contentCnt).isEqualTo(3);
        Assertions.assertThat(pageCnt).isEqualTo(1);


     }

}