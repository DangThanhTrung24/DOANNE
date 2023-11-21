var checkFullName = 0;
var checkEmail = 0;

$(document).ready(function () {
	// xem email có tồn tại không
  $.get("/rest/user", function (data, status) {
	  // Khởi tạo một mảng tạm thời để lưu trữ địa chỉ email từ dữ liệu nhận được
    var temp = [];
    // Lặp qua các thuộc tính của đối tượng data
    for (const property in data) {
		// Thêm địa chỉ email vào mảng tạm thời
      temp[temp.length] = String(`${data[property].email}`);
    }
    // Lưu trữ danh sách địa chỉ email vào localStorage, các địa chỉ được nối với nhau bằng dấu phẩy
    localStorage.setItem("user", temp.join(", "));
  });


// kiểm tra email đã nhập chưa
  $("#email").keyup(function () {
    var email = this.value;
    if (email == "") {
      $("#email").addClass("is-invalid");
      $("#showErrorEmail").text("Vui lòng nhập Email!");
      checkEmail = 10;
    } else {
      if (isValidEmail(email) == false) {
        $("#email").addClass("is-invalid");
        $("#showErrorEmail").text("Email không đúng định dạng!");
        checkEmail = 10;
      } else {
		  // kiểm tra email có tồn tại chưa
        var user = localStorage.getItem("user").split(", ");
        if (user.includes(email)) {
          $("#email").addClass("is-invalid");
          $("#showErrorEmail").text("Email đã tồn tại!");
          checkEmail = 10;
        } else {
          $("#email").removeClass("is-invalid");
          $("#showErrorEmail").text("");
          checkEmail = 1;
        }
      }
    }
    handlerButtonSave();
  });

// kiểm tra tên đã nhập chưa
  $("#fullName").keyup(function () {
    var fullName = this.value;
    if (fullName == "") {
      $("#fullName").addClass("is-invalid");
      $("#showErrorFullName").text("Vui lòng nhập họ và tên!");
      checkFullName = 10;
    } else {
      var length = fullName.length;
      var minLength = $("#fullName").attr("data-minlength");
      var maxLength = $("#fullName").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#fullName").addClass("is-invalid");
        $("#showErrorFullName").text("Nhập giá trị từ 5 đến 50 ký tự!");
        checkFullName = 10;
      } else {
        $("#fullName").removeClass("is-invalid");
        $("#showErrorFullName").text("");
        checkFullName = 1;
      }
    }
    handlerButtonSave();
  });
});

// form đã đúng hết chưa
function checkForm() {
  $("#email").keyup();
  $("#fullName").keyup();
  return handlerButtonSave();
}

// check false sẽ vô hiệu hóa nút lưu
function handlerButtonSave() {
  var check = false;
  if (checkEmail !== 10 && checkFullName !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("user-form-app", []);

app.controller("user-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};

  $scope.form.gender = "0";
  $scope.form.news = "0";

// thêm người dùng mới 
  $scope.save = function () {
    if (checkForm()) {
      $scope.form.birthday = String($("#birthday").val());
      var item = angular.copy($scope.form);
      $http
        .post(`/rest/user/form`, item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content =
            "Bạn đã thêm mới thành công một tài khoản người dùng!";
          $("#modalSuccess").modal("show");
          var path = "/admin/user/form";
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi thêm sản phẩm");
          console.log(error);
        });
    }
  };
});

function isVietnamesePhoneNumber(number) {
  return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

// định dạng email
function isValidEmail(email) {
  return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}

function formatDate(date) {
  var d = new Date(date),
    month = "" + (d.getMonth() + 1),
    day = "" + d.getDate(),
    year = d.getFullYear();

  if (month.length < 2) month = "0" + month;
  if (day.length < 2) day = "0" + day;

  return [year, month, day].join("-");
}
