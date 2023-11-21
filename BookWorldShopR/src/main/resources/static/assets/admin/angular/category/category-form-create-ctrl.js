var checkNameCategory = 0;
var checkLogo = 0;
var checkBanner = 0;
var checkDescribe = 0;
var checkNameSearch;
$(document).ready(function () {
	// kiểm tra vilidator form
  $("#nameCategory").keyup(function () {
	  // Lấy giá trị từ ô nhập liệu "nameCategory"
    var name = this.value;
     // Kiểm tra nếu giá trị rỗng
    if (name == "") {
		// Thêm lớp "is-invalid" để hiển thị viền đỏ (đánh dấu lỗi)
      $("#nameCategory").addClass("is-invalid");
      // Hiển thị thông báo lỗi
      $("#showErrorNameCategory").text("Vui lòng nhập tên danh mục!");
      // Thiết lập checkNameCategory thành 10 để chỉ đánh dấu rằng có lỗi
      checkNameCategory = 10;
    } else {
		// Kiểm tra độ dài của giá trị
      var length = name.length;
      var minLength = $("#nameCategory").attr("data-minlength");
      var maxLength = $("#nameCategory").attr("data-maxlength");
		// Nếu độ dài nằm ngoài khoảng từ minLength đến maxLength
      if (length < minLength || length > maxLength) {
		  // Thêm lớp "is-invalid" để hiển thị viền đỏ (đánh dấu lỗi)
        $("#nameCategory").addClass("is-invalid");
        $("#showErrorNameCategory").text("Nhập giá trị từ 5 đến 30 ký tự!");
        checkNameCategory = 10;
      } else {
		  // Ngược lại, loại bỏ lớp "is-invalid" và đặt checkNameCategory thành 1 để chỉ đánh dấu là không có lỗi
        $("#nameCategory").removeClass("is-invalid");
        $("#showErrorNameCategory").text("");
        checkNameCategory = 1;
      }
    }
    // Gọi hàm handlerButtonSave để kiểm tra và cập nhật trạng thái của nút lưu
    handlerButtonSave();
  });

  $("#nameSearch").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameSearch").addClass("is-invalid");
      $("#showErrorNameSearch").text("Vui lòng nhập tên tìm kiếm!");
      checkNameSearch = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameSearch").attr("data-minlength");
      var maxLength = $("#nameSearch").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameSearch").addClass("is-invalid");
        $("#showErrorNameSearch").text("Nhập giá trị từ 5 đến 30 ký tự!");
        checkNameSearch = 10;
      } else {
        $("#nameSearch").removeClass("is-invalid");
        $("#showErrorNameSearch").text("");
        checkNameSearch = 1;
      }
    }
    handlerButtonSave();
  });

  $("#chooseLogo").change(function () {
	  // Lấy giá trị của đường dẫn tập tin đã chọn
    var logo = this.value;
    // Kiểm tra nếu giá trị rỗng (người dùng chưa chọn file)
    if (logo == "") {
		// Thêm lớp "is-invalid" để hiển thị viền đỏ (đánh dấu lỗi)
      $("#valueLogo").addClass("is-invalid");
      $("#showErrorLogo").text("Vui lòng chọn logo đại diện!");
      checkLogo = 10;
    } else {
		// Nếu người dùng đã chọn file, gọi hàm imageLogoChanged để xử lý thay đổi ảnh logo
      angular.element(this).scope().imageLogoChanged(this.files);
      $("#valueLogo").removeClass("is-invalid");
      $("#showErrorLogo").text("");
      checkLogo = 1;
    }
    handlerButtonSave();
  });

  $("#chooseBanner").change(function () {
    var banner = this.value;
    if (banner == "") {
      $("#valueBanner").addClass("is-invalid");
      $("#showErrorBanner").text("Vui lòng chọn logo đại diện!");
      checkBanner = 10;
    } else {
      angular.element(this).scope().imageBannerChanged(this.files);
      $("#valueBanner").removeClass("is-invalid");
      $("#showErrorBanner").text("");
      checkBanner = 1;
    }
    handlerButtonSave();
  });

  $("#describe").keyup(function () {
    var describe = this.value;
    if (describe == "") {
      $("#describe").addClass("is-invalid");
      $("#showErrorDescribe").text("Vui lòng nhập mô tả danh mục!");
      checkDescribe = 10;
    } else {
      var length = describe.length;
      var minLength = $("#describe").attr("data-minlength");
      var maxLength = $("#describe").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#describe").addClass("is-invalid");
        $("#showErrorDescribe").text("Nhập giá trị từ 7 đến 500 ký tự!");
        checkDescribe = 10;
      } else {
        $("#describe").removeClass("is-invalid");
        $("#showErrorDescribe").text("");
        checkDescribe = 1;
      }
    }
    handlerButtonSave();
  });
});

function checkForm() {
  $("#nameCategory").keyup();
  $("#chooseLogo").change();
  $("#chooseBanner").change();
  $("#describe").keyup();
  $("#nameSearch").keyup();
  return handlerButtonSave();
}

// hiển thị nút luuw
function handlerButtonSave() {
  var check = false;
  if (
    checkNameCategory !== 10 &&
    checkLogo !== 10 &&
    checkBanner !== 10 &&
    checkDescribe !== 10 &&
    checkNameSearch !== 10
  ) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("category-form-app", []);

app.controller("category-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  // lưu loại danh mục
  $scope.save = function () {
	  // Kiểm tra xem form có hợp lệ không
    if (checkForm()) {
		// Tạo một bản sao của đối tượng $scope.form để tránh ảnh hưởng đến đối tượng gốc
      var item = angular.copy($scope.form);
      // Gửi HTTP POST request đến endpoint "/rest/categories/form" với dữ liệu là đối tượng $scope.form
      $http.post(`/rest/categories/form`, item).then((resp) => {
		  // Hàm then được gọi khi request thành công
            // resp chứa dữ liệu nhận được từ server (thường là một đối tượng JSON)
            
            // Hiển thị thông báo thành công và mở modal
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một danh mục!";
        $("#modalSuccess").modal("show");
        // Xây dựng đường dẫn cho liên kết và in ra console
        var path = "/admin/categories/form";
        $("a").attr("href", path);
        console.log(resp);
      });
    }
  };
  
  // upload logo
  $scope.imageLogoChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/logo", data, {
		   // Cấu hình để không chuyển đổi dữ liệu và sử dụng header mặc định cho FormData
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.logo = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload banner
  $scope.imageBannerChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/banner", data, {
		   // Cấu hình để không chuyển đổi dữ liệu và sử dụng header mặc định cho FormData
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
