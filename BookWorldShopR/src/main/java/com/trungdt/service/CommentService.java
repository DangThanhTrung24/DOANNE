package com.trungdt.service;

import java.util.List;

import com.trungdt.entity.Comment;
import com.trungdt.model.CommentModel;

public interface CommentService {

	// danh sách bình luận của sản phẩm
	List<Comment> getListCommentByProductId(Integer id);

	// tạo bình luận cho sản phẩm
	CommentModel createComment(CommentModel commentModel);

	// danh sách bình luận chưa duyệt(admin)
	List<Comment> getListCommentPending();

	// thông tin bình luận chi tiết (admin)
	Comment getCommentByCommentId(Integer id);

	// duyệt comment (admin)
	void approveComment(Integer id);

	// xóa comment (admin)
	void delete(Integer id);

	// comment đã duyệt (admin)
	List<Comment> getListCommentChecked();

	// đếm lượt comment của sản phẩm
	int getCountCommentByProductNameSearch(String nameSearch);

	// trung bình sao của sản phẩm
	int getAllStarCommentByProductNameSearch(String nameSearch);

}
