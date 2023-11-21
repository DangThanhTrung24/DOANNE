package com.trungdt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungdt.entity.Comment;
import com.trungdt.model.CommentModel;
import com.trungdt.service.CommentService;

/**
 * Class cung cap cac dich vu rest api cho bang comment
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comment")
public class CommentRestController {
	@Autowired
	CommentService commentService;
	// hiển thị các bình luận của sản phẩm
	@GetMapping("/form/product/{id}")
	public List<Comment> getListCommentByProductId(@PathVariable("id") Integer id) {
		return commentService.getListCommentByProductId(id);
	}
	
	// tạo bình luận
	@PostMapping("/form")
	public CommentModel create(@RequestBody CommentModel commentModel) {
		return commentService.createComment(commentModel);
	}
	
	// hiển thị tất cả bình luận chưa duyệt(admin)
	@GetMapping("/pending")
	public List<Comment> getListCommentPending(){
		return commentService.getListCommentPending();
	}
	
	// hiển thị bình luận chi tiết (admin)
	@GetMapping("/pending/{id}")
	public Comment getCommentByCommentId(@PathVariable("id") Integer id) {
		return commentService.getCommentByCommentId(id);
	}
	
	// duyệt comment (admin)
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		commentService.approveComment(id);
	}
	
	// xóa comment (admin)
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		commentService.delete(id);
	}
	
	// hiển thị comment đã duyệt (admin)
	@GetMapping("/approved")
	public List<Comment> getListCommentChecked(){
		return commentService.getListCommentChecked();
	}
}
