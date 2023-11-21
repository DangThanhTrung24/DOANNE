function checkForm(){
	// Khởi tạo biến kiểm tra và biến xác nhận việc gửi form
    var check = 0;
    var submit = false;
    // Lấy giá trị từ trường Họ và tên
    var fullName = $("#fullName").val();
    if(fullName == ""){
		// Nếu trường Họ và tên trống, hiển thị thông báo lỗi và thay đổi giao diện
        $("#errorFullName").text("Họ và tên không được bỏ trống!");
        $("#fullName").css("border-color", "#dc3545"); 
        $("#lblFullName").css("color", "#dc3545"); 
        check = 0;
    }
    else{
		// Nếu trường Họ và tên không trống, xóa thông báo lỗi và khôi phục giao diện
        $("#errorFullName").text("");
        $("#fullName").removeAttr("style");
        $("#lblFullName").css("color", "black"); 
        check++;
    }

	// Tương tự như trường Họ và tên, kiểm tra và xác nhận trường Số điện thoại, Địa chỉ nhận hàng, Tỉnh/thành, Quận/huyện, Phường/xã
    // Mỗi trường có quy trình kiểm tra tương tự
    var phone = $("#phone").val();
    if(phone == ""){
        $("#errorPhone").text("Số điện thoại không được bỏ trống!");
        $("#phone").css("border-color", "#dc3545"); 
        $("#lblPhone").css("color", "#dc3545"); 
        check = 0;
    }
    else{
        if (isVietnamesePhoneNumber(phone) == false) {
            $("#errorPhone").text("Số điện thoại không đúng định dạng!");
            $("#phone").css("border-color", "#dc3545"); 
            $("#lblPhone").css("color", "#dc3545"); 
            check = 0;
        }
        else{
            $("#errorPhone").text("");
            $("#phone").removeAttr("style");
            $("#lblPhone").css("color", "black"); 
            check++;
        }    
    }

    var detail = $("#detail").val();
    if(detail == ""){
        $("#errorDetail").text("Địa chỉ nhận hàng không được bỏ trống!");
        $("#detail").css("border-color", "#dc3545"); 
        $("#lblDetail").css("color", "#dc3545"); 
        check = 0;
    }
    else{
        if(detail.length < 10 || detail.length > 200){
            $("#errorDetail").text("Địa chỉ nhận hàng phải từ 10 đến 200 ký tự!");
            $("#detail").css("border-color", "#dc3545"); 
            $("#lblDetail").css("color", "#dc3545"); 
            check = 0;
        }
        else{
            $("#errorDetail").text("");
            $("#detail").removeAttr("style");
            $("#lblDetail").css("color", "black"); 
            check++;
        }       
    }

    var province = $("#province").val();
    if(province == ""){
        $("#errorProvince").text("Vui lòng chọn tỉnh/thành!");
        $("#province").css("border-color", "#dc3545"); 
        $("#lblProvince").css("color", "#dc3545"); 
        check = 0;
    }
    else{
        $("#errorProvince").text("");
        $("#province").removeAttr("style");
        $("#lblProvince").css("color", "black"); 
        check++;
    }

    var district = $("#district").val();
    if(district == ""){
        $("#errorDistrict").text("Vui lòng chọn quận/huyện!");
        $("#district").css("border-color", "#dc3545"); 
        $("#lblDistrict").css("color", "#dc3545"); 
        check = 0;
    }
    else{
        $("#errorDistrict").text("");
        $("#district").removeAttr("style");
        $("#lblDistrict").css("color", "black"); 
        check++;
    }

    var ward = $("#ward").val();
    if(ward == ""){
        $("#errorWard").text("Vui lòng chọn phường/xã!");
        $("#ward").css("border-color", "#dc3545"); 
        $("#lblWard").css("color", "#dc3545"); 
        check = 0;
    }
    else{
        $("#errorWard").text("");
        $("#ward").removeAttr("style");
        $("#lblWard").css("color", "black"); 
        check++;
    }
	// Kiểm tra tổng số điểm kiểm tra, nếu bằng số lượng trường trong form, đặt biến xác nhận gửi form là true
    if(check == 6){
        submit = true;
    }
	// Trả về giá trị biến xác nhận gửi form
    return submit;
}

// Sử dụng biểu thức chính quy để so sánh chuỗi số điện thoại với mẫu đúng
function isVietnamesePhoneNumber(number) {
	return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

var app = angular.module("address-add-app", []);

app.controller("address-add-ctrl", function ($scope, $http) {
  $scope.province = [];
  $scope.district = [];
  $scope.ward = [];
  $scope.form = {};
  
  $scope.initialize = function () {
    $http.get("/rest/province").then((resp) => {
      $scope.province = resp.data;
    });
  };

  $scope.initialize();

  $("#province").change(function () {
    var id = $("#province").val();
    $http.get("/rest/district/" + id).then((resp) => {
      $scope.district = resp.data;
    });
  });

  $("#district").change(function () {
    var idProvince = $("#province").val();
    var idDistrict = $("#district").val();
    $http.get("/rest/ward/" + idProvince + "/" + idDistrict).then((resp) => {
      $scope.ward = resp.data;
    });
  });

// Định nghĩa hàm thêm địa chỉ mới
  $scope.addAddress = function () {
	  // Kiểm tra dữ liệu nhập liệu trước khi thực hiện thêm địa chỉ
    if(checkForm()){
        $scope.form.province = $("#province").val();
        $scope.form.district = $("#district").val();
        $scope.form.ward = $("#ward").val();
        // Tạo một bản sao của đối tượng form để tránh ảnh hưởng đến đối tượng gốc
        var item = angular.copy($scope.form);
        // Gửi yêu cầu POST đến địa chỉ /rest/address/form với dữ liệu là đối tượng item
        $http.post(`/rest/address/form`, item).then((resp) => {   
			// Sau khi thêm địa chỉ thành công, đặt lại giá trị của form và hiển thị một cảnh báo        
            $scope.form = {};
            $("#alert").show();
        });
    }
  }
});

