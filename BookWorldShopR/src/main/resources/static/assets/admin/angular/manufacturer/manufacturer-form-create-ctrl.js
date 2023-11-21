var checkNameManufacturer = 0;
var checkLogo = 0;
var checkBanner = 0;
var checkDescribe = 0;
$(document).ready(function () {
	// kiểm tra xem tên thuognw hiệu nahapj chưa
  $("#nameManufacturer").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameManufacturer").addClass("is-invalid");
      $("#showErrorNameManufacturer").text("Vui lòng nhập tên thương hiệu!");
      checkNameManufacturer = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameManufacturer").attr("data-minlength");
      var maxLength = $("#nameManufacturer").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameManufacturer").addClass("is-invalid");
        $("#showErrorNameManufacturer").text("Nhập giá trị từ 3 đến 50 ký tự!");
        checkNameManufacturer = 10;
      } else {
        $("#nameManufacturer").removeClass("is-invalid");
        $("#showErrorNameManufacturer").text("");
        checkNameManufacturer = 1;
      }
    }
    handlerButtonSave();
  });

// kiểm tra xem logo đã chọn chưa
  $("#chooseLogo").change(function () {
    var logo = this.value;
    if (logo == "") {
      $("#valueLogo").addClass("is-invalid");
      $("#showErrorLogo").text("Vui lòng chọn logo đại diện!");
      checkLogo = 10;
    } else {
      angular.element(this).scope().imageLogoChanged(this.files);
      $("#valueLogo").removeClass("is-invalid");
      $("#showErrorLogo").text("");
      checkLogo = 1;
    }
    handlerButtonSave();
  });

// kiểm tra xem banner đã chọn được
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

// kieier tra xem mổ tả đã nhập chưa
  $("#describe").keyup(function () {
    var describe = this.value;
    if (describe == "") {
      $("#describe").addClass("is-invalid");
      $("#showErrorDescribe").text("Vui lòng nhập mô tả thương hiệu!");
      checkDescribe = 10;
    } else {
      var length = describe.length;
      var minLength = $("#describe").attr("data-minlength");
      var maxLength = $("#describe").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#describe").addClass("is-invalid");
        $("#showErrorDescribe").text("Nhập giá trị từ 5 đến 500 ký tự!");
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

// kiểm tra form đã nhập hết chưa
function checkForm() {
  $("#nameManufacturer").keyup();
  $("#chooseLogo").change();
  $("#chooseBanner").change();
  $("#describe").keyup();
  return handlerButtonSave();
}

// kiểm tra chưa đúng thì vô hiệu hóa nút lưu
function handlerButtonSave() {
  var check = false;
  if (
    checkNameManufacturer !== 10 &&
    checkLogo !== 10 &&
    checkBanner !== 10 &&
    checkDescribe !== 10
  ) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("manufacturer-form-app", []);

app.controller("manufacturer-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  // thêm thương hiệu
  $scope.save = function () {
	  // kiểm tra xem đã đúng điều kiện hết chưa
    if (checkForm()) {
		// // Nếu biểu mẫu hợp lệ, tạo một bản sao của dữ liệu biểu mẫu
      var item = angular.copy($scope.form);
      // Sử dụng dịch vụ $http để gửi một yêu cầu POST đến URL đã chỉ định ("/rest/manufactures/form") với dữ liệu biểu mẫu
      $http.post(`/rest/manufactures/form`, item).then((resp) => {
		  // Nếu yêu cầu POST thành công, cập nhật đối tượng $scope.info với trạng thái thành công và thông báo
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một thương hiệu!";
        $("#modalSuccess").modal("show");
        
        // Đặt giá trị của biến 'path' là "/admin/shop/form"
        var path = "/admin/manufactures/form";
        // Cập nhật thuộc tính href của tất cả các phần tử anchor (a) với giá trị đã chỉ định
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
