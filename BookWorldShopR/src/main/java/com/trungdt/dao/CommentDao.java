package com.trungdt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trungdt.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{
	// chọn các bình luận đã được duyệt của từng sản phẩm
	@Query("SELECT c FROM Comment c WHERE c.status = 1 AND c.product.id = :uid")
	List<Comment> getListCommentByProductId(@Param("uid") Integer id);
	
	// chọn tất cả bình luận chưa duyệt(admin)
	@Query("SELECT c FROM Comment c WHERE c.status = 0")
	List<Comment> getListCommentPending();
	
	// chọn tất cả bình luận đã duyệt (admin)
	@Query("SELECT c FROM Comment c WHERE c.status = 1")
	List<Comment> getListCommentChecked();
	
	// chọn bình luận dựa vào id
	@Query("SELECT c FROM Comment c WHERE c.id = :uid")
	Comment getCommentByCommentId(@Param("uid") Integer id);
	
	// đếm tất cả comment đã được duyệt
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.product.Namesearch = :unameSearch AND c.status = 1")
	int getCountCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
	
	//đếm số sao đánh giá tất cả người của từng sản phẩm 
	@Query("SELECT c.star FROM Comment c WHERE c.product.Namesearch = :unameSearch AND c.status = 1")
	List<Integer> getAllStarCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
}
