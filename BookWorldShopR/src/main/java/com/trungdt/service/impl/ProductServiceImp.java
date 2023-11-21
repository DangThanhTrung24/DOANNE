package com.trungdt.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trungdt.dao.CategoryDao;
import com.trungdt.dao.ManufacturerDao;
import com.trungdt.dao.ProductDao;
import com.trungdt.dao.UserDao;
import com.trungdt.entity.Category;
import com.trungdt.entity.Manufacturer;
import com.trungdt.entity.Product;
import com.trungdt.entity.User;
import com.trungdt.model.ProductModel;
import com.trungdt.model.ShowProduct;
import com.trungdt.service.CommentService;
import com.trungdt.service.ProductService;

/**
 * Class trien khai theo interface UserService, Thao tac voi Class UserDao de
 * thuc hien cac tac vu tuong ung
 */
@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ManufacturerDao manufacturerDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	CommentService commentService;

	@PersistenceContext
	private EntityManager em;

	
	// thêm một sản phẩm mới (admin)
	@Override
	public ProductModel createProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Product product = new Product();
		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setImage4(productModel.getImage4());
		product.setImage5(productModel.getImage5());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setCreateday(timestamp.toString());
		product.setPersoncreate(temp.getId());
		product.setSales(productModel.getSales());

		Manufacturer manufacturer = manufacturerDao.findById(productModel.getManuId()).get(); // lấy ra id được chọn
		Category category = categoryDao.findById(productModel.getCateId()).get(); // lấy ra id được chọn

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productDao.save(product);

		return productModel;
	}

	// danh sách tất cả sản phẩm
	@Override
	public List<Product> findAll() {
		return productDao.getListProduct();
	}

	// xóa sản phẩm được chọn (admin)
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Product product = productDao.findById(id).get();
		product.setDeleteday(timestamp.toString());
		product.setPersondelete(temp.getId());
		productDao.save(product);
	}

	// cập nhật thông tin sản phẩm
	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Product product = productDao.findById(productModel.getId()).get();

		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setImage4(productModel.getImage4());
		product.setImage5(productModel.getImage5());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setUpdateday(timestamp.toString());
		product.setPersonupdate(temp.getId());
		product.setSales(productModel.getSales());

		Manufacturer manufacturer = manufacturerDao.findById(productModel.getManuId()).get();
		Category category = categoryDao.findById(productModel.getCateId()).get();

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productDao.save(product);
		return productModel;
	}

	// thông tin của một sản phẩm dựa trên id
	@Override
	public ProductModel getOneProductById(Integer id) {
		Product product = productDao.findById(id).get();
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setCode(product.getCode());
		productModel.setName(product.getName());
		productModel.setPrice(product.getPrice());
		productModel.setQuality(product.getQuality());
		productModel.setImage1(product.getImage1());
		productModel.setImage2(product.getImage2());
		productModel.setImage3(product.getImage3());
		productModel.setImage4(product.getImage4());
		productModel.setImage5(product.getImage5());
		productModel.setNameSearch(product.getNamesearch());
		productModel.setActive(product.isActive());
		productModel.setManuId(product.getManufacturer().getId());
		productModel.setCateId(product.getCategory().getId());
		productModel.setDescription(product.getDescription());
		productModel.setSpecification(product.getSpecification());
		productModel.setSales(product.getSales());
		return productModel;
	}

	// lấy 12 sản phẩm mới nhất
	@Override
	public List<Product> getListLatestProduct() {
		return productDao.getListLatestProduct();
	}

	// 14 sản phẩm nhiều view nhất
	@Override
	public List<Product> getListViewsProduct() {
		return productDao.getListViewsProduct();
	}

	@Override
	public Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable) {
		return productDao.getListProductByNameSearch(nameSearch, pageable);
	}

	@Override
	public List<Product> getDemo(String nameSearch) {
		return productDao.getListDemo(nameSearch);
	}

	@Override
	public Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable) {
		return productDao.getListProductByPrice(nameSearch, minPrice, maxPrice, pageable);
	}

	// lọc sản phẩm
	@Override
	public Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manuF, String sort,
			Pageable pageable, String status, String name, String category) {
		CriteriaBuilder cb = em.getCriteriaBuilder();  // Sử dụng CriteriaBuilder để tạo các điều kiện truy vấn
		CriteriaQuery<Product> cq = cb.createQuery(Product.class); // Tạo truy vấn dựa trên lớp Product
		Root<Product> from = cq.from(Product.class);// Lấy thực thể gốc từ lớp Product

		 // Điều kiện cho trạng thái sản phẩm (danh-sach, tim-kiem, uu-dai)
		Predicate preStatus;

		 // Nếu trạng thái là "products", lọc theo tên sản phẩm
		if (status.equals("products")) {
			preStatus = cb.like(from.get("category").get("Namesearch"), "%" + nameSearch + "%");
		} else if (status.equals("search")) {
			Predicate preCategory = null;
			if (name.length() != 0) {
				name = "%" + name + "%";
			}
			// Nếu trạng thái là "search", lọc theo tên sản phẩm và danh mục
			preStatus = cb.like(from.get("name"), name);
			if (category.length() != 0) {
				preCategory = cb.like(from.get("category").get("Namesearch"), "%" + category + "%");
				preStatus = cb.and(preStatus, preCategory);
			}
		} else {
			 // Nếu trạng thái là "uu-dai", không áp dụng điều kiện lọc (điều kiện rỗng)
			preStatus = cb.conjunction(); // Điều kiện rỗng (không áp dụng điều kiện)
		}

		 // Điều kiện cho trạng thái sản phẩm là "active" và không bị xóa
		Predicate preActive = cb.equal(from.get("active"), 1);
		Predicate preDeleteDay = cb.isNull(from.get("Deleteday"));

		int check = 0;
		Predicate prePrice = null;
		Predicate preManu = null;

		if (price != null) {
			 // Lọc theo mức giá
			int min = 0;
			int max = 999999999;
			if (price.equals("1")) {
				max = 150000;
			} else if (price.equals("2")) {
				min = 150000;
				max = 300000;
			} else if (price.equals("3")) {
				min = 300000;
				max = 500000;
			} else if (price.equals("4")) {
				min = 500000;
				max = 700000;
			} else {
				min = 700000;
			}
			check = 1;
			prePrice = cb.between(from.get("price"), min, max);
		}

		if (manuF != null) {
			// Lọc theo nhà sản xuất
			preManu = cb.equal(from.get("manufacturer").get("id"), Integer.parseInt(manuF));
			if (check == 1) {
				check = 3;
			} else {
				check = 2;
			}
		}

		// Áp dụng điều kiện lọc vào truy vấn, tùy thuộc vào giá trị của biến 'check'.
		if (check == 1) {
			cq.where(prePrice, preActive, preDeleteDay, preStatus);
		} else if (check == 2) {
			cq.where(preManu, preActive, preDeleteDay, preStatus);
		} else if (check == 3) {
			cq.where(prePrice, preManu, preActive, preDeleteDay, preStatus);
		} else {
			cq.where(preActive, preDeleteDay, preStatus);
		}

		if (sort != null) {
			// Sắp xếp sản phẩm theo tùy chọn của người dùng
			if (sort.equals("0")) {
				cq.orderBy(cb.desc(from.get("Createday")));
			}
			if (sort.equals("1")) {
				cq.orderBy(cb.asc(from.get("name")));
			}
			if (sort.equals("2")) {
				cq.orderBy(cb.desc(from.get("name")));
			}
			if (sort.equals("3")) {
				cq.orderBy(cb.asc(from.get("price")));
			}
			if (sort.equals("4")) {
				cq.orderBy(cb.desc(from.get("price")));
			}
		} else {
			// Mặc định sắp xếp theo ngày tạo giảm dần
			cq.orderBy(cb.desc(from.get("Createday")));
		}

		// Tạo truy vấn dựa trên truy vấn Criteria.
		TypedQuery<Product> q = em.createQuery(cq);

		// Lấy danh sách sản phẩm thỏa mãn điều kiện
		List<Product> countAllItems = q.getResultList();

		// Xác định vị trí bắt đầu của trang dữ liệu.
		q.setFirstResult(Math.toIntExact(pageable.getOffset()));
		// Xác định số lượng sản phẩm trên mỗi trang.
		q.setMaxResults(pageable.getPageSize());

		// Lấy danh sách sản phẩm thỏa mãn các điều kiện lọc.
		List<Product> getAllItems = q.getResultList();

		// Danh sách sản phẩm để hiển thị.
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		// Duyệt qua danh sách sản phẩm và thêm thông tin đánh giá (totalStar) cho mỗi sản phẩm.
		for (Product product : getAllItems) {
			ShowProduct showProduct = new ShowProduct();
			// đếm sao của sản phẩm
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		// Tạo một trang mới dựa trên danh sách sản phẩm, thông tin phân trang và tổng số sản phẩm thỏa mãn các điều kiện lọc.
		Page<ShowProduct> page = new PageImpl<ShowProduct>(listProduct, pageable, countAllItems.size());

		return page;
	}

	// lấy sản phẩm dựa trên tên sản phẩm
	@Override
	public Product getProductByNameSearch(String nameSearch) {
		return productDao.getProductByNameSearch(nameSearch);
	}

	// sản phẩm cùng loại
	@Override
	public List<Product> getListProductRelated(int id) {
		return productDao.getListProductRelated(id);
	}

	// cập nhật lượt view khi xem chi tiết sản phẩm
	@Override
	public void updateView(String nameSearch) {
		// sản phẩm được chọn và update lượt view
		Product product = productDao.getProductByNameSearch(nameSearch);
		int view = product.getViews();
		product.setViews(view + 1);
		productDao.save(product);
	}

	// update số lượng khi đặt hàng 
	@Override
	public void updateQuality(Product product) {
		productDao.save(product);
	}

	// lấy 5 sản phẩm đang sale
	@Override
	public List<Product> getListProductSales() {
		return productDao.getListProductSales();
	}

}
