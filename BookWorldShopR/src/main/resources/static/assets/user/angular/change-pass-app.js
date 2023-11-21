// Định nghĩa hàm kiểm tra và xác nhận mật khẩu
function checkForm(password){
    var check = 0;
    var submit = false;
    // Lấy giá trị của trường mật khẩu cũ từ phần tử HTML có id là "oldPass"
    var oldPass = $("#oldPass").val();
    if(oldPass == ""){
		// Nếu trường mật khẩu cũ trống, hiển thị thông báo lỗi và làm đỏ border và màu của trường
        $("#errorOldPass").text("Mật khẩu cũ không được bỏ trống!");
        $("#oldPass").css("border-color", "#dc3545"); 
        $("#lblOldPass").css("color", "#dc3545");
        check = 0;
    }
    else{
		// Nếu trường mật khẩu cũ có giá trị
        if(oldPass != password){
			// Nếu mật khẩu cũ không khớp với mật khẩu đã cho, hiển thị thông báo lỗi và làm đỏ border và màu của trường
            $("#errorOldPass").text("Mật khẩu cũ không khớp!");
            $("#oldPass").css("border-color", "#dc3545"); 
            $("#lblOldPass").css("color", "#dc3545");
            check = 0;
        }
        else{
			 // Nếu mật khẩu cũ khớp, ẩn thông báo lỗi, xóa border đỏ và trả lại màu mặc định của trường
            $("#errorOldPass").text("");
            $("#oldPass").removeAttr("style");
            $("#lblOldPass").css("color", "black"); 
        }     
        // Tăng biến check lên 1
        check++;
    }
	// Tiếp tục kiểm tra và xác nhận trường mật khẩu mới và xác nhận mật khẩu
    // ... (Tương tự như phần kiểm tra mật khẩu cũ)
    
    var newPass = $("#newPass").val();
    if(newPass == ""){
        $("#errorNewPass").text("Mật khẩu mới không được bỏ trống!");
        $("#newPass").css("border-color", "#dc3545"); 
        $("#lblNewPass").css("color", "#dc3545");
        check = 0;
    }
    else{     
        if(newPass.length < 6){
            $("#errorNewPass").text("Mật khẩu ít nhất 6 ký tự!");
            $("#newPass").css("border-color", "#dc3545"); 
            $("#lblNewPass").css("color", "#dc3545");
            check = 0;
        }
        else{
            if(newPass == password){
                $("#errorNewPass").text("Mật khẩu mới không được giống với mật khẩu cũ!");
                $("#newPass").css("border-color", "#dc3545"); 
                $("#lblNewPass").css("color", "#dc3545");
                check = 0;
            }
            else{
                $("#errorNewPass").text("");
                $("#newPass").removeAttr("style");
                $("#lblNewPass").css("color", "black");         
                check++;
            }          
        }      
    }

    var confirmPass = $("#confirmPass").val();
    if(confirmPass == ""){
        $("#errorConfirmPass").text("Xác nhận mật khẩu không được bỏ trống!");
        $("#confirmPass").css("border-color", "#dc3545"); 
        $("#lblConfirmPass").css("color", "#dc3545");
        check = 0;
    }
    else{     
        if(confirmPass != newPass){
            $("#errorConfirmPass").text("Xác nhận mật khẩu không khớp!");
            $("#confirmPass").css("border-color", "#dc3545"); 
            $("#lblConfirmPass").css("color", "#dc3545");
            check = 0;
        }
        else{
            $("#errorConfirmPass").text("");
            $("#confirmPass").removeAttr("style");
            $("#lblConfirmPass").css("color", "black");         
            check++;
        }
        
    }
	// Kiểm tra xem tất cả các điều kiện kiểm tra đã được đáp ứng (check = 3) hay không
    if(check == 3){
		// Nếu đáp ứng, đặt biến submit thành true để cho phép việc submit biểu mẫu
        submit = true;
    }

    return submit;
}

var app = angular.module("change-pass-app", []);

app.controller("change-pass-ctrl", function ($scope, $http) {
	$scope.form = {};
    $scope.formPass = {};
    // Định nghĩa hàm khởi tạo
	$scope.initialize = function () {
		// Gửi yêu cầu HTTP GET đến endpoint "/rest/user/account"
    $http.get("/rest/user/account").then((resp) => {
		// Khi yêu cầu thành công, nhận dữ liệu từ phản hồi (resp)
        // Gán dữ liệu nhận được cho $scope.form
      $scope.form = resp.data;
      // Chuyển đổi dữ liệu của trường "birthday" thành đối tượng ngày (Date object)
      $scope.form.birthday = new Date($scope.form.birthday);
	  console.log(resp.data);
    });
  };

  $scope.initialize();

// Định nghĩa hàm cập nhật mật khẩu
  $scope.update = function(){
	  // Kiểm tra dữ liệu trong biểu mẫu trước khi gửi yêu cầu
	  if(checkForm($scope.form.password)){
		  // Sao chép dữ liệu từ $scope.form để tránh sửa đổi dữ liệu trực tiếp trên $scope.form
        var item = angular.copy($scope.form);
        // Gửi yêu cầu HTTP PUT đến endpoint "/rest/user/account/change-password"
        $http.put(`/rest/user/account/change-password`, item).then(resp => {
			// Hiển thị thông báo thành công
            $("#notification").removeClass("alert-danger");
   		    $("#notification").addClass("alert-success");
            $("#notification").html(
                "<i class='fa fa-check-circle'></i> Update password successfully!"
            );
            $("#notification").show();
            // Tự động làm mới trang sau 3 giây
            setTimeout(function() {
                location.reload();
            }, 3000);
            
        }).catch(error => {
            alert("Lỗi cập nhật mật khẩu")             
        }); 
	  }
  }
})