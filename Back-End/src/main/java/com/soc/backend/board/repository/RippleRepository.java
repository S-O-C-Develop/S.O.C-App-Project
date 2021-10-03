package com.soc.backend.board.repository;

import com.soc.backend.board.dto.GetRippleRes;
import com.soc.backend.board.entity.Post;
import com.soc.backend.board.entity.Ripple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RippleRepository extends JpaRepository<Ripple, Long> {

    @Query(
            "SELECT r FROM Ripple  r " +
                    "JOIN FETCH r.account a " +
                    "JOIN FETCH  r.post p " +
                    "LEFT JOIN FETCH r.rippleList rl " +
                    "WHERE (r.status ='VALID' AND p = :post) " +
                    "ORDER BY r.updatedAt DESC "
    )
    List<GetRippleRes> getAllByPost(Post post);

}
