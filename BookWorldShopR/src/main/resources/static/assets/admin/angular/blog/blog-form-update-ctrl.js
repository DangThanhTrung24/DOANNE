// khởi tạo biến
var checkTitle = 0;
var checkImage = 0;
var checkContent = 0;
var checkUploadDate = 0;
var checkBanner = 0;
var checkActive = 0;
var checkNameSearch = 0;
var checkDescription = 0;

$(document).ready(function () {
	// Bắt đầu theo dõi sự kiện keyup trên phần tử có id là "titleBlog"
  $("#titleBlog").keyup(function () {
	  // Lấy giá trị của phần tử "titleBlog" sau mỗi lần nhấn phím và gán vào biến titleBlog
    var titleBlog = this.value;
    // Kiểm tra nếu titleBlog rỗng, thực hiện các hành động khi có lỗi
    if (titleBlog == "") {
      $("#titleBlog").addClass("is-invalid");
      $("#showErrorTitleBlog").text("Vui lòng nhập mã tiêu đề Blogs!");
      // Gán giá trị 10 cho biến checkTitle
      checkTitle = 10;
    } else {
	 // Nếu titleBlog không rỗng, thực hiện kiểm tra và xử lý khi không có lỗi
      var length = titleBlog.length;
      var minLength = $("#titleBlog").attr("data-minlength");
      var maxLength = $("#titleBlog").attr("data-maxlength");
	  // Kiểm tra độ dài của titleBlog
      if (length < minLength || length > maxLength) {
		  // Nếu không nằm trong khoảng giới hạn, thực hiện các hành động khi có lỗi
        $("#titleBlog").addClass("is-invalid");
        $("#showErrorTitleBlog").text("Nhập giá trị từ 5 đến 200 ký tự!");
        checkTitle = 10;
      } else {
		// Nếu nằm trong khoảng giới hạn, thực hiện các hành động khi không có lỗi
        // Xóa lớp "is-invalid" khỏi phần tử "titleBlog", nếu có  
        $("#titleBlog").removeClass("is-invalid");
        // Đặt thông báo lỗi là chuỗi rỗng
        $("#showErrorTitleBlog").text("");
        // Gán giá trị 1 cho biến checkTitle
        checkTitle = 1;
      }
    }
    // Gọi hàm handlerButtonSave để xử lý việc kích hoạt hoặc vô hiệu hóa nút lưu
    handlerButtonSave();
  });

  $("#nameSearch").keyup(function () {
    var nameSearch = this.value;
    if (nameSearch == "") {
      $("#nameSearch").addClass("is-invalid");
      $("#showErrorNameSearch").text("Vui lòng nhập tên tìm kiếm sản phẩm!");
      checkNameSearch = 10;
    } else {
      var length = nameSearch.length;
      var minLength = $("#nameSearch").attr("data-minlength");
      var maxLength = $("#nameSearch").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameSearch").addClass("is-invalid");
        $("#showErrorNameSearch").text("Nhập giá trị từ 5 đến 50 ký tự!");
        checkNameSearch = 10;
      } else {
        $("#nameSearch").removeClass("is-invalid");
        $("#showErrorNameSearch").text("");
        checkNameSearch = 1;
      }
    }
    handlerButtonSave();
  });
  
  $("#chooseImage").change(function () {
    var image = this.value;
    if (image == "") {
      $("#valueImage").addClass("is-invalid");
      $("#showErrorImage").text("Vui lòng chọn logo!");
      checkImage = 10;
    } else {
      angular.element(this).scope().imageImageChanged(this.files);
      $("#valueImage").removeClass("is-invalid");
      $("#showErrorImage").text("");
      checkImage = 1;
    }
    handlerButtonSave();
  });
  
  $("#chooseBanner").change(function () {
    var banner = this.value;
    if (banner == "") {
      $("#valueImageBanner").addClass("is-invalid");
      $("#showErrorImageBanner").text("Vui lòng chọn banner!");
      checkBanner = 10;
    } else {
      angular.element(this).scope().imageImageBanner(this.files);
      $("#valueImageBanner").removeClass("is-invalid");
      $("#showErrorImageBanner").text("");
      checkBanner = 1;
    }
    handlerButtonSave();
  });
  
  $("#uploadDate").change(function() {
		var uploaddate = this.value;
		if (uploaddate == "") {
			$("#uploadDate").addClass("is-invalid");
			$("#showErrorUploadDate").text("Vui lòng chọn ngày cập nhật trang!");
			checkUploadDate = 10;
		}
		else {
			$("#uploadDate").removeClass("is-invalid");
			$("#showErrorUploadDate").text("");
			checkUploadDate = 1;
		}
		handlerButtonSave();
	});

    $("#content").keyup(function () {
    var content = this.value;
    if (content == "") {
      $("#content").addClass("is-invalid");
      $("#showErrorContent").text("Vui lòng nhập nội dung blog!");
      checkContent = 10;
    } else {
      var length = content.length;
      var minLength = $("#content").attr("data-minlength");
      var maxLength = $("#content").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#content").addClass("is-invalid");
        $("#showErrorContent").text("Nội dung phải từ 200-10000 ký tự!");
        checkContent = 10;
      } else {
        $("#content").removeClass("is-invalid");
        $("#showErrorContent").text("");
        checkContent = 1;
      }
    }
    handlerButtonSave();
  });

  $("#active").change(function() {
		var active = this.value;
		if (active == "") {
			$("#active").addClass("is-invalid");
			$("#showErrorActive").text("Vui lòng chọn trạng thái!");
			checkActive = 10;
		}
		else {
			$("#active").removeClass("is-invalid");
			$("#showErrorActive").text("");
			checkActive = 1;
		}
		handlerButtonSave();
	});

  $("#description").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#description").addClass("is-invalid");
      $("#showErrorDescription").text("Vui lòng nhập mô tả blog!");
      checkDescription = 10;
    } else {
      var length = description.length;
      var minLength = $("#description").attr("data-minlength");
      var maxLength = $("#description").attr("data-maxlength");
      
      if (length < minLength || length > maxLength) {
        $("#description").addClass("is-invalid");
        $("#showErrorDescription").text("Mô tả phải từ 50 đến 255 ký tự!");
        checkDescription = 10;
      } else {
        $("#description").removeClass("is-invalid");
        $("#showErrorDescription").text("");
        checkDescription = 1;
      }
    }
    handlerButtonSave();
  });
});

// kiểm tra form đúng điều kiện chưa
function checkForm() {
  $("#titleBlog").keyup();
  //$("#chooseImage").change();
  $("#content").keyup();
  //$("#chooseBanner").change();
  $("#uploadDate").change();
  $("#active").change();
  $("#nameSearch").keyup();
  $("#description").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false; // Biến check được khởi tạo với giá trị mặc định là false
  // Kiểm tra điều kiện cho từng trường và nếu tất cả đều hợp lệ, thay đổi giá trị của biến check và kích hoạt nút "Lưu"
  if (checkTitle !== 10 && 
      checkImage !== 10 &&
      checkContent !== 10 &&
      checkUploadDate !== 10 &&
 	    checkBanner !== 10 &&
      checkActive !== 10 && 
      checkNameSearch !== 10 &&
      checkDescription !== 10) {
    check = true;
    $("#btnUpdate").prop("disabled", false);
  } else {
    check = false;
    $("#btnUpdate").prop("disabled", true);
  }
  return check;
}
	

var app = angular.module("blog-form-app", []);

app.controller("blog-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  $scope.load = function () {
	  // Gửi HTTP GET request đến endpoint "/rest/blog/form/" với tham số là giá trị của phần tử có id là "demo"
    $http
      .get("/rest/blog/form/" + $("#demo").val())
      .then((resp) => {
		  // Hàm then được gọi khi request thành công
      // resp.data chứa dữ liệu nhận được từ server (thường là một đối tượng JSON)
        $scope.form = resp.data;
        // Set giá trị cho phần tử có id là "uploadDate" bằng giá trị của thuộc tính "uploadDay" trong đối tượng resp.data
        $("#uploadDate").val($scope.form.uploadDay);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  // Gọi hàm load ngay khi controller được khởi tạo
  $scope.load();


  // hàm update blog
  $scope.update = function () {
	  // Kiểm tra xem form có hợp lệ không
    if (checkForm()) {
		// Lấy giá trị ngày upload từ phần tử có id là "uploaddate" và gán vào thuộc tính "uploadday" của đối tượng $scope.form
    $scope.form.uploadday = String($("#uploaddate").val());
    // Lấy giá trị của phần tử có id là "demo" và gán vào thuộc tính "id" của đối tượng $scope.form
      $scope.form.id = $("#demo").val(); 
      // Tạo một bản sao của đối tượng $scope.form để tránh ảnh hưởng đến đối tượng gốc
      var item = angular.copy($scope.form);
      // Gửi HTTP PUT request đến endpoint "/rest/blog/form/" với tham số là giá trị của phần tử có id là "demo"
      $http
        .put("/rest/blog/form/" + $("#demo").val(), item)
        .then((resp) => {
			// Hàm then được gọi khi request thành công
        // resp chứa dữ liệu nhận được từ server (thường là một đối tượng JSON)
        // Hiển thị thông báo thành công và mở modal
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật danh mục thành công!";
          $("#modalSuccess").modal("show");
          
          // Xây dựng đường dẫn cho liên kết và in ra console
          var path = "/admin/blogs/update/" + $scope.form.id;
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi update blog");
          console.log(error);
        });
    }
  };
  
  

  // upload image
  $scope.imageImageChanged = function (files) {
	  // Tạo đối tượng FormData để chứa dữ liệu hình ảnh
    var data = new FormData();
    // Gắn hình ảnh vào FormData với key là "file"
    data.append("file", files[0]);
    // Gửi HTTP POST request đến endpoint "/rest/upload/imageBlog" để tải lên hình ảnh
    $http
      .post("/rest/upload/imageBlog", data, {
		  // Cấu hình để không chuyển đổi dữ liệu và sử dụng header mặc định cho FormData
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
		  // Hàm then được gọi khi request thành công
      // resp chứa dữ liệu nhận được từ server (thường là một đối tượng JSON)

      // Gán tên hình ảnh mới vào thuộc tính "image" của đối tượng $scope.form
        $scope.form.image = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
  
  //upload banner

$scope.imageImageBanner = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageBlog", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.banner = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
  
});
