package com.trungdt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.CommentDao;
import com.trungdt.dao.ProductDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Comment;
import com.trungdt.entity.Product;
import com.trungdt.entity.User;
import com.trungdt.model.CommentModel;
import com.trungdt.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ProductDao productDao;

	// danh sách bình luận của từng sản phẩm
	@Override
	public List<Comment> getListCommentByProductId(Integer id) {
		return commentDao.getListCommentByProductId(id);
	}

	// tạo bình luận cho sản phẩm
	@Override
	public CommentModel createComment(CommentModel commentModel) {
		// lấy người dùng đang nhập hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// tìm người dùng qua email
		User temp = userDao.findUserByEmail(username);
		// thông tin sản phẩm được bình luận
		Product product = productDao.findById(commentModel.getProductId()).get();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		// khỏi tạo đối tuognwj comment
		Comment comment = new Comment();

		comment.setContent(commentModel.getContent());
		comment.setStar(commentModel.getStar());
		comment.setDate(strDate);
		comment.setProduct(product);
		comment.setUser(temp);
		comment.setStatus("0");
		// lưu vào db
		commentDao.save(comment);

		return commentModel;
	}

	// tất cả bình luận (admin)
	@Override
	public List<Comment> getListCommentPending() {
		return commentDao.getListCommentPending();
	}

	// thông tin bình luận chi tiết (admin)
	@Override
	public Comment getCommentByCommentId(Integer id) {
		return commentDao.getCommentByCommentId(id);
	}

	// duyệt comment (admin)
	@Override
	public void approveComment(Integer id) {
		// tìm comment set status thành 1
		Comment comment = commentDao.findById(id).get();
		comment.setStatus("1");
		commentDao.save(comment);
	}

	// xóa comment (admin)
	@Override
	public void delete(Integer id) {
		Comment comment = commentDao.findById(id).get();
		commentDao.delete(comment);
	}

	// tất cả comment đã duyệt (admin)
	@Override
	public List<Comment> getListCommentChecked() {
		return commentDao.getListCommentChecked();
	}

	// đếm lượt comment của sản phẩm
	@Override
	public int getCountCommentByProductNameSearch(String nameSearch) {
		return commentDao.getCountCommentByProductNameSearch(nameSearch);
	}

	//Trung bình cộng số sao của sản phẩm
	@Override
	public int getAllStarCommentByProductNameSearch(String nameSearch) {
		int result = 0;
		int totalStar = 0;
		// Lấy danh sách các số sao từ CSDL thông qua đối tượng commentDao
		List<Integer> listStar = commentDao.getAllStarCommentByProductNameSearch(nameSearch);
		// Kiểm tra nếu danh sách trả về không rỗng
		if(listStar.isEmpty() == false) {
			// Duyệt qua danh sách và tính tổng số sao
			for(int i = 0; i < listStar.size(); i++) {
				totalStar = totalStar + listStar.get(i);
				System.out.println(totalStar);
			}
			// Tính số sao trung bình, làm tròn về số nguyên gần nhất
			result = Math.round(totalStar / (listStar.size()));
		}
		return result;
	}

}
