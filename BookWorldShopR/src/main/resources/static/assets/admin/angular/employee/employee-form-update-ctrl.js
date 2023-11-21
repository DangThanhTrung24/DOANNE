var checkFullName = 0;
var checkDepartment = 0;
var checkPhone = 0;
var checkStartDate = 0;
var checkPosition = 0;
var checkSalary = 0;
var checkRole = 0;
var checkEmail = 0;

$(document).ready(function() {
	// xem email có tồn tại không
	$.get("/rest/user", function(data, status) {
		// Tìm vị trí của đối tượng có địa chỉ email giống với giá trị của phần tử có id là "email"
		var index = data.findIndex(p => p.email == $("#email").val());
		// Xóa đối tượng tại vị trí đã tìm được
		data.splice(index, 1);
		// Khởi tạo một mảng tạm thời để lưu trữ địa chỉ email từ dữ liệu nhận được
		var temp = [];
		// Lặp qua mỗi đối tượng trong dữ liệu và thêm địa chỉ email vào mảng tạm thời
		for (const property in data) {
			temp[temp.length] = `${data[property].email}`;
		}
		// Lưu trữ danh sách các địa chỉ email vào localStorage
		localStorage.setItem("user", temp.join(", "));
	});

// kiểm tra tên nhập chưa
	$("#fullName").keyup(function() {
		var fullName = this.value;
		if (fullName == "") {
			$("#fullName").addClass("is-invalid");
			$("#showErrorFullName").text("Vui lòng nhập họ và tên!");
			checkFullName = 10;
		}
		else {
			var length = fullName.length;
			var minLength = $("#fullName").attr("data-minlength")
			var maxLength = $("#fullName").attr("data-maxlength")

			if ((length < minLength) || (length > maxLength)) {
				$("#fullName").addClass("is-invalid");
				$("#showErrorFullName").text("Nhập giá trị từ 6 đến 30 ký tự!");
				checkFullName = 10;
			}

			else {
				$("#fullName").removeClass("is-invalid");
				$("#showErrorFullName").text("");
				checkFullName = 1;
			}

		}
		handlerButtonSave();
	});

// kiểm tra phòng ban nhập chưa
	$("#department").keyup(function() {
		var fullName = this.value;
		if (fullName == "") {
			$("#department").addClass("is-invalid");
			$("#showErrorDepartment").text("Vui lòng nhập phòng ban!");
			checkDepartment = 10;
		}
		else {
			var length = fullName.length;
			var minLength = $("#department").attr("data-minlength")
			var maxLength = $("#department").attr("data-maxlength")

			if ((length < minLength) || (length > maxLength)) {
				$("#department").addClass("is-invalid");
				$("#showErrorDepartment").text("Nhập giá trị từ 6 đến 50 ký tự!");
				checkDepartment = 10;
			}

			else {
				$("#department").removeClass("is-invalid");
				$("#showErrorDepartment").text("");
				checkDepartment = 1;
			}

		}
		handlerButtonSave();
	});

// kiểm tra số điện thoại nhập chưa
	$("#phone").keyup(function() {
		var phone = this.value;
		if (phone == "") {
			$("#phone").addClass("is-invalid");
			$("#showErrorPhone").text("Vui lòng nhập số điện thoại!");
			checkPhone = 10;
		}
		else {
			// kiểm tra số điện thoại đúng định dạng chưa
			if (isVietnamesePhoneNumber(phone) == false) {
				$("#phone").addClass("is-invalid");
				$("#showErrorPhone").text("Số điện thoại không đúng định dạng!");
				checkPhone = 10;
			}

			else {
				$("#phone").removeClass("is-invalid");
				$("#showErrorPhone").text("");
				checkPhone = 1;
			}

		}
		handlerButtonSave();
	});

// kiểm tra ngày bắt đầu làm việc chọn chưa
	$("#startDate").change(function() {
		var startDate = this.value;
		if (startDate == "") {
			$("#startDate").addClass("is-invalid");
			$("#showErrorStartDate").text("Vui lòng chọn ngày bắt đầu làm việc!");
			checkStartDate = 10;
		}
		else {
			$("#startDate").removeClass("is-invalid");
			$("#showErrorStartDate").text("");
			checkStartDate = 1;
		}
		handlerButtonSave();
	});

// kiểm tra xem chức vụ chọn chưa
	$("#position").keyup(function() {
		var position = this.value;
		if (position == "") {
			$("#position").addClass("is-invalid");
			$("#showErrorPosition").text("Vui lòng nhập chức vụ!");
			checkPosition = 10;
		}
		else {
			var length = position.length;
			var minLength = $("#position").attr("data-minlength")
			var maxLength = $("#position").attr("data-maxlength")

			if ((length < minLength) || (length > maxLength)) {
				$("#position").addClass("is-invalid");
				$("#showErrorPosition").text("Nhập giá trị từ 6 đến 30 ký tự!");
				checkPosition = 10;
			}

			else {
				$("#position").removeClass("is-invalid");
				$("#showErrorPosition").text("");
				checkPosition = 1;
			}

		}
		handlerButtonSave();
	});

// kiểm tra xem lương chọn chưa
	$("#salary").keyup(function() {
		var salary = this.value;
		if (salary == 0) {
			$("#salary").addClass("is-invalid");
			$("#showErrorSalary").text("Vui lòng nhập lương!");
			checkSalary = 10;
		}
		else {
			if (salary < 1000000) {
				$("#salary").addClass("is-invalid");
				$("#showErrorSalary").text("Lương phải lớn hơn 1 triệu!");
				checkSalary = 10;
			}

			else {
				$("#salary").removeClass("is-invalid");
				$("#showErrorSalary").text("");
				checkSalary = 1;
			}

		}
		handlerButtonSave();
	});

// kiểm cha xem chứ vụ nhập chưa
	$("#role").change(function() {
		var role = this.value;
		if (role == "") {
			$("#role").addClass("is-invalid");
			$("#showErrorRole").text("Vui lòng chọn chức vụ!");
			checkRole = 10;
		}
		else {
			$("#role").removeClass("is-invalid");
			$("#showErrorRole").text("");
			checkRole = 1;
		}
		handlerButtonSave();
	});

// kiểm tra email nhập chưa
	$("#email").keyup(function() {
		var email = this.value;
		if (email == "") {
			$("#email").addClass("is-invalid");
			$("#showErrorEmail").text("Vui lòng nhập Email!");
			checkEmail = 10;
		}
		else {
			// email đúng định dạng chưa
			if (isValidEmail(email) == false) {
				$("#email").addClass("is-invalid");
				$("#showErrorEmail").text("Email không đúng định dạng!");
				checkEmail = 10;
			}
			else {
				// email đã tồn tại hay không
				var user = localStorage.getItem("user").split(", ");
				if (user.includes(email)) {
					$("#email").addClass("is-invalid");
					$("#showErrorEmail").text("Email đã tồn tại!");
					checkEmail = 10;

				}
				else {
					$("#email").removeClass("is-invalid");
					$("#showErrorEmail").text("");
					checkEmail = 1;
				}
			}
		}
		handlerButtonSave();
	});
});

// kiểm tra tất cả có dữ liệu hết chưa
function checkForm() {
	$("#fullName").keyup();
	$("#department").keyup();
	$("#phone").keyup();
	$("#startDate").change();
	$("#position").keyup();
	$("#salary").keyup();
	$("#role").change();
	$("#email").keyup();
	return handlerButtonSave();
}

// nếu kiểm tra chưa hợp lệ thì sẽ bị vô hiệu hóa nút lưu
function handlerButtonSave() {
	var check = false;
	if ((checkFullName !== 10)
		&& (checkDepartment !== 10)
		&& (checkPhone !== 10)
		&& (checkStartDate !== 10)
		&& (checkPosition !== 10)
		&& (checkSalary !== 10)
		&& (checkRole !== 10)
		&& (checkEmail !== 10)) {
		check = true;
		$("#btnUpdate").prop('disabled', false);
	}
	else {
		check = false;
		$("#btnUpdate").prop('disabled', true);
	}
	return check;
}


var app = angular.module("employee-form-app", []);

app.controller("employee-form-ctrl", function($scope, $http) {
	$scope.form = {};
	$scope.info = {};
	// load thông tin của nhân viên lên form
	$scope.load = function() {
		$http.get("/rest/form/employee/"+$("#demo").val()).then(resp => {
			$scope.form = resp.data; // Gán dữ liệu nhận được vào $scope.form
			// Đặt giá trị của phần tử có id là "startDate" bằng ngày bắt đầu từ $scope.form.startday
			$("#startDate").val($scope.form.startday);
			// Đặt giá trị của $scope.form.role thành một chuỗi để đảm bảo đúng kiểu dữ liệu cho select box
			$scope.form.role = String($scope.form.role);
		}).catch(error => {
			console.log(error);
		});
	}
	// Gọi hàm load ngay khi controller được khởi tạo
	$scope.load();

	// cập nhật thông tin nhân viên
	$scope.update = function() {
		if (checkForm()) {		
			// Đặt giá trị của $scope.form.startday thành một chuỗi để đảm bảo đúng kiểu dữ liệu
			$scope.form.startday = String($("#startDate").val());
			// Lấy giá trị của phần tử có id là "demo" và gán vào thuộc tính "id" của đối tượng $scope.form
			$scope.form.id = $("#demo").val();
			// Tạo một bản sao của đối tượng $scope.form để tránh ảnh hưởng đến đối tượng gốc
			var item = angular.copy($scope.form);
			// Gửi HTTP PUT request đến endpoint "/rest/form/employee/" kèm theo id của nhân viên
            $http.put("/rest/form/employee/"+$("#demo").val(), item).then(resp => {
				// Hiển thị thông báo thành công và mở modal
				$scope.info.status = true;
				$scope.info.content = "Bạn đã cập nhật tài khoản thành công!";
				$("#modalSuccess").modal("show");
				
				 // Xây dựng đường dẫn cho liên kết và in ra console
				var path = "/admin/employees/update/" + $scope.form.id;
				$("a").attr("href", path);
				console.log(resp);
            }).catch(error => {
                alert("Lỗi update nhân viên")             
                console.log(error);
            });         
		}
	}
});

// định dạng số điện thoại
function isVietnamesePhoneNumber(number) {
	return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

// định dạng email
function isValidEmail(email) {
	return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}