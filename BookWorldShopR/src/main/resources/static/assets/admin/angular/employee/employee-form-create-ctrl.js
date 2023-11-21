// khởi tạo biến
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


// kiểm tra tên đã được nhập chưa
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

// kiểm tra phòng ban đã nhập chưa
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

// kiểm tra số điện thoại đã nhập chưa
	$("#phone").keyup(function() {
		var phone = this.value;
		if (phone == "") {
			$("#phone").addClass("is-invalid");
			$("#showErrorPhone").text("Vui lòng nhập số điện thoại!");
			checkPhone = 10;
		}
		else {
			// có đúng định dạng không
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

// kiểm tra ngày bắt đầu làm việc đã chọn chưa
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

// vị trí lmaf việc đã đucợ nhập chưa
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

// lương đã đucợ nhập chưa
	$("#salary").keyup(function() {
		var salary = this.value;
		if (salary == 0) {
			$("#salary").addClass("is-invalid");
			$("#showErrorSalary").text("Vui lòng nhập lương!");
			checkSalary = 10;
		}
		else {
			// lương phải hơn 1tr
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

// chọn chức vụ chưa
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

// kiểm tra email đã nhập chưa
	$("#email").keyup(function() {
		var email = this.value;
		if (email == "") {
			$("#email").addClass("is-invalid");
			$("#showErrorEmail").text("Vui lòng nhập Email!");
			checkEmail = 10;
		}
		else {
			// email phải đúng định dạng 
			if (isValidEmail(email) == false) {
				$("#email").addClass("is-invalid");
				$("#showErrorEmail").text("Email không đúng định dạng!");
				checkEmail = 10;
			}
			else {
				// kiểm tra email có tồn tại chưa
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

// kiểm tra xem được nhập hết chưa
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

// nếu kiểm tra đúng hết thì mở nút lưu không thì vô hiệu hóa
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
		$("#btnSave").prop('disabled', false);
	}
	else {
		check = false;
		$("#btnSave").prop('disabled', true);
	}
	return check;
}


var app = angular.module("employee-form-app", []);

app.controller("employee-form-ctrl", function($scope, $http) {
	$scope.form = {};
	$scope.info = {};

// lưu nhân viên vào db
	$scope.save = function() {
		// Kiểm tra xem form có hợp lệ không
		if (checkForm()) {		
			// Lấy giá trị ngày bắt đầu từ phần tử có id là "startDate" và gán vào thuộc tính "startday" của đối tượng $scope.form
			$scope.form.startday = String($("#startDate").val());
			// Tạo một bản sao của đối tượng $scope.form để tránh ảnh hưởng đến đối tượng gốc
			var item = angular.copy($scope.form);
			// Gửi HTTP POST request đến endpoint "/rest/form/employee" với dữ liệu là bản sao của $scope.form
            $http.post(`/rest/form/employee`, item).then(resp => {
				// Hiển thị thông báo thành công và mở modal
				$scope.info.status = true;
				$scope.info.content = "Bạn đã thêm mới thành công một tài khoản!";
				$("#modalSuccess").modal("show");
				
				// Xây dựng đường dẫn cho liên kết và in ra console
				var path = "/admin/employees/form";
				$("a").attr("href", path);
				console.log(resp);
            }).catch(error => {
                alert("Lỗi thêm sản phẩm")             
                console.log(error);
            });         
		}
	}
});

// định dạng số điện thoại việt 
function isVietnamesePhoneNumber(number) {
	return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}
// định dạng email
function isValidEmail(email) {
	return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}

// định dạng ngày
function formatDate(date) {
	// Lấy thông tin về tháng, ngày, năm từ đối tượng Date
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

// Nếu tháng hoặc ngày chỉ có một chữ số, thêm '0' vào trước để có định dạng chuẩn
    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;
	// Kết hợp tháng, ngày, năm thành chuỗi và trả về
    return [year, month, day].join('-');
}