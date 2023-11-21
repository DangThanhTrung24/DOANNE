function checkForm(){
    var submit = false; // Khởi tạo biến submit với giá trị mặc định là false.
    // Lấy giá trị từ trường nhập liệu "Họ và tên".
    var fullName = $("#fullName").val();
    if(fullName == ""){
		// Nếu trường "Họ và tên" rỗng, hiển thị thông báo lỗi và đặt biến submit là false.
        $("#errorFullName").text("Họ và tên không được bỏ trống!");
        $("#fullName").css("border-color", "#dc3545"); 
        $("#lblFullName").css("color", "#dc3545"); 
        submit = false;
    }
    else{
		// Nếu trường "Họ và tên" không rỗng, xóa thông báo lỗi và đặt biến submit là true.
        $("#errorFullName").text("");
        $("#fullName").removeAttr("style");
        $("#lblFullName").css("color", "black"); 
        submit = true;
    }
	return submit;
}

var app = angular.module("information-app", []);

app.controller("information-ctrl", function ($scope, $http) {
	$scope.form = {}; // Khởi tạo một đối tượng trống để lưu trữ dữ liệu biểu mẫu
	// Hàm để khởi tạo dữ liệu khi controller được tải
	$scope.initialize = function () { // Gửi yêu cầu GET đến "/rest/user/account"
    $http.get("/rest/user/account").then((resp) => {
		// Trong trường hợp có phản hồi thành công, gán dữ liệu cho $scope.form
      $scope.form = resp.data;
      // Chuyển đổi chuỗi ngày sinh thành đối tượng Date để xử lý dễ dàng hơn
      $scope.form.birthday = new Date($scope.form.birthday);
	  console.log(resp.data);
    });
  };


  $scope.initialize();

  $scope.update = function(){
	  // Kiểm tra hợp lệ của dữ liệu biểu mẫu bằng hàm checkForm
	  if(checkForm()){
		  // Chuyển đổi ngày sinh thành chuỗi và gán cho $scope.form.birthday
        $scope.form.birthday = String($("#birthday").val());   
        // Tạo một bản sao của đối tượng $scope.form để tránh ảnh hưởng trực tiếp đến dữ liệu trong $scope
        var item = angular.copy($scope.form);
        // Gửi yêu cầu PUT đến "/rest/user/account/update" để cập nhật thông tin người dùng
        $http.put(`/rest/user/account/update`, item).then(resp => {
			// Trong trường hợp phản hồi thành công, làm mới trang để hiển thị thông tin đã cập nhật
            location.reload();
        }).catch(error => {
            alert("Lỗi cập nhật")             
        }); 
	  }
  }
})