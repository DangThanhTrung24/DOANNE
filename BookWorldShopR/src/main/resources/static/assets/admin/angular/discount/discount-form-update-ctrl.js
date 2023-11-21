var checkName = 0;
var checkCode = 0;
var checkPrice = 0;
var checkMoneyLimit = 0;
var checkQuality = 0;
var checkApplyDay = 0;
var checkExpiration = 0;

$(document).ready(function () {
	// kiểm tra tên mã giảm giá nhập chưa
  $("#name").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#name").addClass("is-invalid");
      $("#showErrorName").text("Vui lòng nhập tên mã!");
      checkNameManufacturer = 10;
    } else {
      var length = name.length;
      var minLength = $("#name").attr("data-minlength");
      var maxLength = $("#name").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#name").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 6 đến 50 ký tự!");
        checkNameManufacturer = 10;
      } else {
        $("#name").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkName = 1;
      }
    }
    handlerButtonSave();
  });

// kiểm tra mã giảm giá đã nhập chưa
  $("#code").keyup(function () {
    var code = this.value;
    if (code == "") {
      $("#code").addClass("is-invalid");
      $("#showErrorCode").text("Vui lòng nhập mã giảm giá!");
      checkCode = 10;
    } else {
      var length = code.length;
      var minLength = $("#code").attr("data-minlength");
      var maxLength = $("#code").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#code").addClass("is-invalid");
        $("#showErrorCode").text("Nhập giá trị từ 6 đến 10 ký tự!");
        checkCode = 10;
      } else {
        $("#code").removeClass("is-invalid");
        $("#showErrorCode").text("");
        checkCode = 1;
      }
    }
    handlerButtonSave();
  });

// nhập số tiền sẽ giảm chưa
  $("#price").keyup(function () {
    var price = this.value;
    if (price == 0) {
      $("#price").addClass("is-invalid");
      $("#showErrorPrice").text("Vui lòng nhập số tiền sẽ giảm!");
      checkPrice = 10;
    } else {
      if (price < 1000) {
        $("#price").addClass("is-invalid");
        $("#showErrorPrice").text("Giá phải lớn hơn 1 nghìn!");
        checkPrice = 10;
      } else {
        $("#price").removeClass("is-invalid");
        $("#showErrorPrice").text("");
        checkPrice = 1;
      }
    }
    handlerButtonSave();
  });

// nhập số tiền để áp dụng giảm chưa
  $("#moneyLimit").keyup(function () {
    var moneyLimit = this.value;
    if (moneyLimit == 0) {
      $("#moneyLimit").addClass("is-invalid");
      $("#showErrorMoneyLimit").text("Vui lòng nhập số tiền sẽ áp dụng!");
      checkMoneyLimit = 10;
    } else {
      if (moneyLimit < 1000) {
        $("#moneyLimit").addClass("is-invalid");
        $("#showErrorMoneyLimit").text("Số tiền phải lớn hơn 1 nghìn!");
        checkMoneyLimit = 10;
      } else {
        $("#moneyLimit").removeClass("is-invalid");
        $("#showErrorMoneyLimit").text("");
        checkMoneyLimit = 1;
      }
    }
    handlerButtonSave();
  });

// nhập số lượng mã giảm chưa
  $("#quality").keyup(function () {
    var quality = this.value;
    if (quality == 0) {
      $("#quality").addClass("is-invalid");
      $("#showErrorQuality").text("Vui lòng nhập số lượng mã!");
      checkQuality = 10;
    } else {
      $("#quality").removeClass("is-invalid");
      $("#showErrorQuality").text("");
      checkQuality = 1;
    }
    handlerButtonSave();
  });

// nhập ngày áp dụng chưa
  $("#applyDay").change(function() {
		var applyDay = this.value;
		if (applyDay == "") {
			$("#applyDay").addClass("is-invalid");
			$("#showErrorApplyDay").text("Vui lòng chọn ngày áp dụng!");
			checkApplyDay = 10;
		}
		else {
			$("#applyDay").removeClass("is-invalid");
			$("#showErrorApplyDay").text("");
			checkApplyDay = 1;
		}
		handlerButtonSave();
	});

// nhập ngày hết hạn chưa
  $("#expiration").change(function() {
		var expiration = this.value;
		if (expiration == "") {
			$("#expiration").addClass("is-invalid");
			$("#showErrorExpiration").text("Vui lòng chọn ngày hết hạn!");
			checkExpiration = 10;
		}
		else {
			$("#expiration").removeClass("is-invalid");
			$("#showErrorExpiration").text("");
			checkExpiration = 1;
		}
		handlerButtonSave();
	});
});

// kiểm tra có đúng hết chưa
function checkForm() {
  $("#name").keyup();
  $("#code").keyup();
  $("#price").keyup();
  $("#quality").keyup();
  $("#applyDay").change();
  $("#expiration").change();
  $("#moneyLimit").keyup();
  return handlerButtonSave();
}

// nút update có được hiện hay chưa
function handlerButtonSave() {
  var check = false;
  if (checkName !== 10 && 
      checkCode !== 10 && 
      checkPrice !== 0 &&
      checkQuality !== 10 &&
      checkApplyDay !== 10 &&
      checkExpiration !== 10 &&
      checkMoneyLimit !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("discount-form-app", []);

app.controller("discount-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  // hiển thị thông tin mã giảm giá được chọn
  $scope.load = function () {
	  // Gửi HTTP GET request đến endpoint "/rest/discount/form/" với tham số là giá trị của phần tử có id là "demo"
    $http
      .get("/rest/discount/form/" + $("#demo").val())
      .then((resp) => {
		  // Gán dữ liệu nhận được từ server vào đối tượng $scope.form
        $scope.form = resp.data;
        // Đặt giá trị của phần tử có id là "applyDay" và "expiration" tương ứng với giá trị của đối tượng $scope.form
        $("#applyDay").val($scope.form.applyDay);
        $("#expiration").val($scope.form.expiration);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  // load dữ liệu lên
  $scope.load();

  // cập nhật mã giảm giá
  $scope.update = function () {
	  // Kiểm tra xem form có hợp lệ không
    if (checkForm()) {
		// Lấy giá trị ngày áp dụng từ phần tử có id là "applyDay" và gán vào thuộc tính "applyDay" của đối tượng $scope.form
      $scope.form.applyDay = String($("#applyDay").val());
      // Lấy giá trị ngày hết hạn từ phần tử có id là "expiration" và gán vào thuộc tính "expiration" của đối tượng $scope.form
      $scope.form.expiration = String($("#expiration").val());
      // Lấy giá trị của phần tử có id là "demo" và gán vào thuộc tính "id" của đối tượng $scope.form
      $scope.form.id = $("#demo").val();
      // Tạo một bản sao của đối tượng $scope.form để tránh ảnh hưởng đến đối tượng gốc
      var item = angular.copy($scope.form);
      // Gửi HTTP PUT request đến endpoint "/rest/discount/form/" với tham số là giá trị của phần tử có id là "demo"
      $http
        .put("/rest/discount/form/" + $("#demo").val(), item)
        .then((resp) => {
			// Hiển thị thông báo thành công và mở modal
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật mã giảm giá thành công!";
          $("#modalSuccess").modal("show");
          
          // Xây dựng đường dẫn cho liên kết 
          var path = "/admin/discount/update/" + $scope.form.id;
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi thêm mã!");
          console.log(error);
        });
    }
  };

});
