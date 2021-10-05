package com.soc.backend.board.service;

import com.soc.backend.account.Account;
import com.soc.backend.board.dto.CreateParentRippleReq;
import com.soc.backend.board.dto.GetRippleRes;
import com.soc.backend.board.entity.Post;
import com.soc.backend.board.entity.Ripple;
import com.soc.backend.board.repository.PostRepository;
import com.soc.backend.board.repository.RippleRepository;
import com.soc.backend.config.response.exception.CustomException;
import com.soc.backend.config.response.exception.CustomExceptionStatus;
import com.soc.backend.config.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.soc.backend.config.enums.Status.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RippleService {

    private final RippleRepository rippleRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long createParentRipple(CustomUserDetails customUserDetails, CreateParentRippleReq createParentRippleReq) {
        Account account = customUserDetails.getAccount();
        Post post = postRepository.findByStatusAndPostId(VALID, createParentRippleReq.getPostId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_POST));
        Ripple ripple = new Ripple(account, post, createParentRippleReq);
        Ripple save = rippleRepository.save(ripple);
        return save.getRippleId();
    }

    public List<GetRippleRes> getParentRipplesByPost(Long postId) {
        Post post = postRepository.findByStatusAndPostId(VALID, postId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_POST));
        return rippleRepository.getAllByPost(post);
    }

    @Transactional
    public void deleteRippleByRippleId(Long rippleId) {
        Ripple ripple = rippleRepository.findByRippleIdAndStatus(rippleId, VALID)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_EXIST_RIPPLE));
        rippleRepository.delete(ripple);
    }
}
