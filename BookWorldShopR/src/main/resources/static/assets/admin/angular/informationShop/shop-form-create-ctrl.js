// khởi tạo biến để kiểm tra 
var checkName = 0;
var checkTime = 0;
var checkPhone = 0;
var checkFax = 0;
var checkEmail = 0;
var checkLogo = 0;
var checkLogoFooter = 0;
var checkAddress = 0;

$(document).ready(function () {
	// kiểm tra xem tên đã nhập chưa
  $("#name").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#name").addClass("is-invalid");
      $("#showErrorName").text("Vui lòng nhập tên cửa hàng!");
      checkName = 10;
    } else {
      var length = name.length;
      var minLength = $("#name").attr("data-minlength");
      var maxLength = $("#name").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#name").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 6 đến 30 ký tự!");
        checkName = 10;
      } else {
        $("#name").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkName = 1;
      }
    }
    handlerButtonSave();
  });

// kiểm tra xem thời gian mở cửa nhập chưa
  $("#time").keyup(function () {
    var time = this.value;
    if (time == "") {
      $("#time").addClass("is-invalid");
      $("#showErrorTime").text("Vui lòng nhập thời gian mở cửa!");
      checkTime = 10;
    } else {
      var length = time.length;
      var minLength = $("#time").attr("data-minlength");
      var maxLength = $("#time").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#time").addClass("is-invalid");
        $("#showErrorTime").text("Nhập giá trị từ 15 đến 25 ký tự!");
        checkTime = 10;
      } else {
        $("#time").removeClass("is-invalid");
        $("#showErrorTime").text("");
        checkTime = 1;
      }
    }
    handlerButtonSave();
  });

// kiêm tra số điện thoại nhập chưa
  $("#phone").keyup(function () {
    var phone = this.value;
    if (phone == "") {
      $("#phone").addClass("is-invalid");
      $("#showErrorPhone").text("Vui lòng nhập số điện thoại!");
      checkPhone = 10;
    } else {
		// kiểm tra sdt đúng định dạng chưa
      if (isVietnamesePhoneNumber(phone) == false) {
        $("#phone").addClass("is-invalid");
        $("#showErrorPhone").text("Số điện thoại không đúng định dạng!");
        checkPhone = 10;
      } else {
        $("#phone").removeClass("is-invalid");
        $("#showErrorPhone").text("");
        checkPhone = 1;
      }
    }
    handlerButtonSave();
  });

// kiểm tra số fax nhập chưa
  $("#fax").keyup(function () {
    var fax = this.value;
    if (fax == "") {
      $("#fax").addClass("is-invalid");
      $("#showErrorFax").text("Vui lòng nhập số fax!");
      checkFax = 10;
    } else {
		// nhập số fax đúng định dạng
      if (isVietnamesePhoneNumber(fax) == false) {
        $("#fax").addClass("is-invalid");
        $("#showErrorFax").text("Fax không đúng định dạng!");
        checkFax = 10;
      } else {
        $("#fax").removeClass("is-invalid");
        $("#showErrorFax").text("");
        checkFax = 1;
      }
    }
    handlerButtonSave();
  });

// kiểm tra email của shop nhập chưa
  $("#email").keyup(function () {
    var email = this.value;
    if (email == "") {
      $("#email").addClass("is-invalid");
      $("#showErrorEmail").text("Vui lòng nhập Email!");
      checkEmail = 10;
    } else {
		// kiểm tra email đúng định dạng chưa
      if (isValidEmail(email) == false) {
        $("#email").addClass("is-invalid");
        $("#showErrorEmail").text("Email không đúng định dạng!");
        checkEmail = 10;
      } else {
        $("#email").removeClass("is-invalid");
        $("#showErrorEmail").text("");
        checkEmail = 1;
      }
    }
    handlerButtonSave();
  });

// logo được chọn chưa
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

// logo footer được chọn chưa
  $("#chooseLogoFooter").change(function () {
    var logoFooter = this.value;
    if (logoFooter == "") {
      $("#valueLogoFooter").addClass("is-invalid");
      $("#showErrorLogoFooter").text("Vui lòng chọn logo footer đại diện!");
      checkLogoFooter = 10;
    } else {
      angular.element(this).scope().imageFooterChanged(this.files);
      $("#valueLogoFooter").removeClass("is-invalid");
      $("#showErrorLogoFooter").text("");
      checkLogoFooter = 1;
    }
    handlerButtonSave();
  });

// địa chỉ được nhập chưa
  $("#address").keyup(function () {
    var address = this.value;
    if (address == "") {
      $("#address").addClass("is-invalid");
      $("#showErrorAddress").text("Vui lòng nhập địa chỉ cửa hàng!");
      checkAddress = 10;
    } else {
      var length = address.length;
      var minLength = $("#address").attr("data-minlength");
      var maxLength = $("#address").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#address").addClass("is-invalid");
        $("#showErrorAddress").text("Nhập giá trị từ 20 đến 100 ký tự!");
        checkAddress = 10;
      } else {
        $("#address").removeClass("is-invalid");
        $("#showErrorAddress").text("");
        checkAddress = 1;
      }
    }
    handlerButtonSave();
  });
});

// kiểm tra xem dữ liệu nhập đúng hết chưa
function checkForm() {
  $("#name").keyup();
  $("#time").keyup();
  $("#phone").keyup();
  $("#fax").keyup();
  $("#email").keyup();
  $("#chooseLogo").change();
  $("#chooseLogoFooter").change();
  $("#address").keyup();
  return handlerButtonSave();
}

// nếu không đúng thì vô hiệu hóa nút lưu
function handlerButtonSave() {
  var check = false;
  if (
    checkName !== 10 &&
    checkTime !== 10 &&
    checkPhone !== 10 &&
    checkFax !== 10 &&
    checkEmail !== 10 &&
    checkLogo !== 10 &&
    checkLogoFooter !== 10 &&
    checkAddress !== 10
  ) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("shop-form-app", []);

app.controller("shop-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  
  // lưu thông tin của hàng
  $scope.save = function () {
	  // Kiểm tra xem biểu mẫu có hợp lệ không bằng cách gọi hàm checkForm()
    if (checkForm()) {
		// Nếu biểu mẫu hợp lệ, tạo một bản sao của dữ liệu biểu mẫu
      var item = angular.copy($scope.form);
      // Sử dụng dịch vụ $http để gửi một yêu cầu POST đến URL đã chỉ định ("/rest/shop/form") với dữ liệu biểu mẫu
      $http.post(`/rest/shop/form`, item).then((resp) => {
		  // Nếu yêu cầu POST thành công, cập nhật đối tượng $scope.info với trạng thái thành công và thông báo
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một thông tin cửa hàng!";
        // Hiển thị một modal có ID là "modalSuccess"
        $("#modalSuccess").modal("show");
        
        // Đặt giá trị của biến 'path' là "/admin/shop/form"
        var path = "/admin/shop/form";
        // Cập nhật thuộc tính href của tất cả các phần tử anchor (a) với giá trị đã chỉ định
        $("a").attr("href", path);
        console.log(resp);
      });
    }
  };

   // upload logo
   $scope.imageLogoChanged = function (files) {
	   // Tạo một đối tượng FormData để chứa dữ liệu hình ảnh được chọn
    var data = new FormData();
    data.append("file", files[0]);
    // Gửi yêu cầu POST đến URL "/rest/upload/logo" với dữ liệu hình ảnh
    $http
      .post("/rest/upload/logo", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
		  // Nếu yêu cầu POST thành công, cập nhật dữ liệu biểu mẫu với tên của hình ảnh được trả về từ server
        $scope.form.logo = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload banner
  $scope.imageFooterChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/logo", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.logoFooter = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
});

function isVietnamesePhoneNumber(number) {
  return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

function isValidEmail(email) {
  return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}
