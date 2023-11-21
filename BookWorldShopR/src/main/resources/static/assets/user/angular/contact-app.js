
function addContact() {
	// Sử dụng AngularJS để tìm scope của phần tử có id là "content".
	// Gọi đến hàm postComment trong scope AngularJS.
  angular.element($("#content")).scope().postComment();
}

var app = angular.module("contact-app", []);

app.controller("contact-ctrl", function ($scope, $http) {
    // Xu ly comment
  $scope.itemComments = {};
  $scope.listComment = [];
  // Hàm loadComment được định nghĩa trong phạm vi của $scope để sử dụng trong view.
  $scope.loadComment = function () {
	  // Sử dụng AngularJS $http service để thực hiện HTTP GET request đến API endpoint /rest/contact.
    $http
      .get("/rest/contact")
      // Xử lý kết quả thành công của request. Hàm callback nhận tham số resp chứa dữ liệu trả về từ server.
      .then((resp) => {
		  // Gán dữ liệu trả về từ server vào biến $scope.listComment.
        // Giả định rằng server trả về một mảng các comment.
        $scope.listComment = resp.data;
        
      })
      // Xử lý lỗi nếu có bất kỳ lỗi nào xảy ra trong quá trình thực hiện request.
      // Trong trường hợp này, chỉ có một callback trống không làm gì cả.
      .catch((error) => {});
  };
  // Gọi hàm loadComment ngay sau khi định nghĩa nó để tải dữ liệu comment ngay khi controller được khởi tạo.
  $scope.loadComment();


  $scope.formComment = {};
  // Hàm postComment được định nghĩa trong phạm vi của $scope để sử dụng trong view.
  $scope.postComment = function () {
	  // Lấy giá trị từ các ô input trong form đánh giá.
    var content = $("#input-review").val();
    var name = $("#input-name").val();
    var email = $("#input-email").val();
    // Kiểm tra xem có dữ liệu nào bị bỏ trống không.
    if (content == "" || name == "" || email == "") {
		// Hiển thị thông báo lỗi nếu có dữ liệu bị bỏ trống.
      $("#notification").removeClass("alert-success");
      $("#notification").addClass("alert-danger");
      $("#notification").html(
        "<i class='fa fa-info-circle'></i> Vui lòng nhập đầy đủ nội dung đánh giá!"
      );
      $("#notification").show();
    } else{
		// Hiển thị thông báo thành công nếu không có dữ liệu nào bị bỏ trống.
        $("#notification").removeClass("alert-danger");
        $("#notification").addClass("alert-success");
        $("#notification").html(
          "<i class='fa fa-check-circle'></i> Cám ơn, vì phản hồi của bạn. Nó đã được gửi cho quản trị viên web để phê duyệt!"
        );
        $("#notification").show();
        // Gán giá trị từ các ô input vào các thuộc tính tương ứng của đối tượng $scope.formComment.
        $scope.formComment.content = content;
        $scope.formComment.name = name;
        $scope.formComment.email = email;
        // Sử dụng AngularJS $http service để thực hiện HTTP POST request đến API endpoint /rest/contact/form.
        $http.post(`/rest/contact/form`, $scope.formComment).then((resp) => {
           console.log(resp.data);  
         });
      }
  };

  $scope.pager = {
    page: 0, // Trang hiện tại, bắt đầu từ trang 0
    size: 1.0, // Kích thước trang, số lượng comment hiển thị trên mỗi trang
    // Getter function trả về danh sách comment của trang hiện tại
    get items(){
        var start = this.page * this.size;
        return $scope.listComment.slice(start, start + this.size);
        
    },
    // Getter function trả về tổng số trang
    get count() {
        return Math.ceil(1.0 * $scope.listComment.length / this.size);
    },
    // Phương thức chuyển đến trang đầu tiên
    first(){
      this.page = 0;
    },
     // Phương thức chuyển đến trang trước
    prev(){
      this.page--;
      if(this.page < 0){
        this.last(); // Nếu đang ở trang đầu, chuyển đến trang cuối cùng
      }
      
    },
    // Phương thức chuyển đến trang kế tiếp
    next(){
      this.page++;
      if(this.page >= this.count){
        this.first(); // Nếu đang ở trang cuối cùng, chuyển đến trang đầu tiên
      }
    },
    // Phương thức chuyển đến trang cuối cùng
    last(){
      this.page = this.count -1;
    }
}

  // Xy ly comment
});
