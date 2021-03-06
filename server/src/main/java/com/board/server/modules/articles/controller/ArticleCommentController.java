package com.board.server.modules.articles.controller;

import com.board.server.common.PagingResponseDto;
import com.board.server.modules.articles.model.ArticleCommentRequestDto;
import com.board.server.modules.articles.model.ArticleCommentResponseDto;
import com.board.server.modules.articles.service.ArticleCommentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/articles/{articleId}/article-comments")
    public ResponseEntity<ArticleCommentResponseDto> addComment(
        @PathVariable Long articleId,
        @RequestBody @Valid ArticleCommentRequestDto.Post articleCommentRequestDto) {

        return ResponseEntity.ok(
            articleCommentService.addComment(articleId, articleCommentRequestDto));
    }

    @GetMapping("/articles/{articleId}/article-comments")
    public ResponseEntity<PagingResponseDto> getComments(
        @PathVariable Long articleId,
        @RequestParam(value = "page", required = false) Integer page) {
        return ResponseEntity.ok(articleCommentService.getComments(articleId, page));
    }

    @PutMapping("/articles/{articleId}/article-comments/{articleCommentId}")
    public ResponseEntity<ArticleCommentResponseDto> updateComment(
        @PathVariable("articleId") Long articleId,
        @PathVariable("articleCommentId") Long articleCommentId,
        @RequestBody @Valid ArticleCommentRequestDto.Put articleCommentRequestDto) {

        return ResponseEntity.ok(
            articleCommentService.updateComment(
                articleId,
                articleCommentId,
                articleCommentRequestDto));
    }

    @DeleteMapping("/articles/{articleId}/article-comments/{articleCommentId}")
    public ResponseEntity<ArticleCommentResponseDto> deleteComment(
        @PathVariable("articleId") Long articleId,
        @PathVariable("articleCommentId") Long articleCommentId) {

        return ResponseEntity.ok(
            articleCommentService.deleteComment(articleId, articleCommentId));
    }
}
