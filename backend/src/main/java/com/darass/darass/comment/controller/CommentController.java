package com.darass.darass.comment.controller;

import com.darass.darass.auth.oauth.domain.AuthenticationPrincipal;
import com.darass.darass.comment.controller.dto.CommentCreateRequest;
import com.darass.darass.comment.controller.dto.CommentResponse;
import com.darass.darass.comment.controller.dto.CommentUpdateRequest;
import com.darass.darass.comment.service.CommentService;
import com.darass.darass.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> save(@AuthenticationPrincipal User user,
        @RequestBody CommentCreateRequest commentRequest) {
        CommentResponse commentResponse = commentService.save(user, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> read(@RequestParam("url") String url) {
        List<CommentResponse> commentResponses = commentService.findAllComments(url);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable("id") Long id, @RequestBody
        CommentUpdateRequest request) {
        commentService.updateContent(id, request.getContent());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}