function like() {
	// Xóa class "dislike" từ phần tử có id là "favorite".
  $("#favorite").removeClass("dislike");
  // Thiết lập thuộc tính "title" của phần tử có id là "favorite" thành "Yêu thích"
  $("#favorite").prop("title", "Yêu thích");
}

function disLike() {
	// thêm class "dislike" từ phần tử có id là "favorite".
  $("#favorite").addClass("dislike");
  // Thiết lập thuộc tính "title" của phần tử có id là "favorite" thành "bỏ thích"
  $("#favorite").prop("title", "Bỏ thích");
}

function addFavorite(productId) {
	// Sử dụng AngularJS để tìm phần tử có id là "content" trong DOM
	// Truy cập scope của phần tử DOM "content"
	// Gọi hàm "postFavorite" trong scope với tham số là "productId"
  angular.element($("#content")).scope().postFavorite(productId);
}

function addComment(productId) {
	// Sử dụng jQuery để lấy phần tử có ID là "content" trong DOM, mà chứa controller AngularJS của ứng dụng.
	 // Truy cập phạm vi (scope) của controller AngularJS.
	 // Gọi phương thức postComment(productId) trong controller và truyền productId làm đối số.
  angular.element($("#content")).scope().postComment(productId);
}

var app = angular.module("favorite-app", []);

app.controller("favorite-ctrl", function ($scope, $http) {
  // Xu ly favorite
  $scope.items = {};

  $scope.form = [];

// hiển thị đã like hay chưa
  $scope.load = function () {
	  // Gửi yêu cầu HTTP GET đến đường dẫn '/rest/favorite/' kèm theo giá trị của "productId".
    $http
      .get("/rest/favorite/" + $("#productId").val())
      // Khi yêu cầu hoàn thành thành công, xử lý dữ liệu trả về.
      .then((resp) => {
		  // Gán dữ liệu trả về từ yêu cầu GET vào biến $scope.form.
        $scope.form = resp.data;
        if (typeof $scope.form.id === "undefined") {
			// Kiểm tra nếu thuộc tính 'id' không được xác định trong dữ liệu trả về.
          like();
          // Nếu 'id' không được xác định, gọi hàm "like".
        } else {
			 // Nếu 'id' đã xác định, gọi hàm "disLike".
          disLike();
        }
      })
      .catch((error) => {});
  };
  $scope.load();

// tạo favorite
  $scope.postFavorite = function (productId) {
	  // Sử dụng $http service của AngularJS để thực hiện một POST request đến đường dẫn "/rest/favorite/add/" + productId.
    $http.post(`/rest/favorite/add/` + productId).then((resp) => {
		// Sau khi request hoàn thành, dữ liệu trả về được lưu trong biến "resp".
		// Gán giá trị của "resp.data" vào biến "$scope.items".
      $scope.items = resp.data;
      // Kiểm tra nếu thuộc tính "id" của "$scope.items" không được xác định (undefined).
      if (typeof $scope.items.id === "undefined") {
		   // Nếu không xác định "id", thực hiện điều hướng (redirect) đến trang "/login".
        location.href = "/login";
      } else {
		  // Nếu "id" đã xác định, thực hiện kiểm tra để xác định xem phản hồi là "like" hoặc "dislike".
        if ($scope.items.id != 0) {
			// Nếu "id" khác 0, thực hiện hàm "disLike" để thay đổi giao diện thành "dislike".
          disLike();
        } else {
			// Nếu "id" = 0, thực hiện hàm "Like" để thay đổi giao diện thành "like".
          like();
        }
      }
    });
  };
  // Xu ly favorite

  // Xu ly comment
  // hiển thị các bài bình luận
  // Khởi tạo biến $scope.itemComments và $scope.listComment để lưu trữ dữ liệu về bình luận.
  $scope.itemComments = {};
  $scope.listComment = [];
  $scope.loadComment = function () {
	  // Sử dụng $http.get() để gửi yêu cầu GET đến máy chủ để lấy dữ liệu bình luận cho sản phẩm.
    $http
      .get("/rest/comment/form/product/" + $("#productId").val())
      .then((resp) => {
		  // Dữ liệu bình luận trả về từ máy chủ được gán cho biến $scope.listComment.
        $scope.listComment = resp.data;
        console.log($scope.listComment);
        // Kiểm tra nếu không có bình luận nào, ẩn các phần tử HTML tương ứng và hiển thị thông báo "No Comment".
        if ($scope.listComment.length === 0) {
          $("#review").hide();
          $("#noComment").show();
          $("#pagination").hide();
        } else { // Ngược lại, nếu có bình luận, hiển thị phần tử HTML "review" và "pagination".
          $("#review").show();
          $("#noComment").hide();
          $("#pagination").show();
        }
      })
      .catch((error) => {});
  };
  $scope.loadComment(); // Gọi hàm loadComment để tải dữ liệu ban đầu khi trang web được tải.

  // hien thi sao mau vang
  $scope.starYellow = function (num) {
    var data = new Array(num); // Khai báo một mảng có độ dài num.
    for (var i = 0; i < data.length; i++) { // Lặp qua mảng data.
      data[i] = i; // Gán giá trị i vào từng phần tử của mảng data
    }
    return data; // Trả về mảng data đã tạo.
  };

  //hien thi sao mau xam
  $scope.starGrey = function (num) {
    var data = new Array(5 - num); // Khai báo một mảng có độ dài là 5 - num.
    for (var i = 0; i < data.length; i++) { // Lặp qua mảng data.
      data[i] = i; // Gán giá trị i vào từng phần tử của mảng data.
    }
    return data; // Trả về mảng data đã tạo.
  };

  $scope.formComment = {}; // Khởi tạo đối tượng để lưu trữ dữ liệu của comment.
  $scope.postComment = function (productId) {
    var content = $("#input-review").val(); // Lấy nội dung comment từ phần tử có ID "input-review".
    var rating = $('input[name="rating"]:checked').val();  // Lấy giá trị số sao đánh giá được chọn.
    if (content == "") { // Kiểm tra nếu nội dung comment trống.
      $("#notification").removeClass("alert-success"); // Loại bỏ lớp CSS "alert-success" cho phần tử có ID "notification".
      $("#notification").addClass("alert-danger"); // Thêm lớp CSS "alert-danger" cho phần tử có ID "notification"
      $("#notification").html( // Đặt nội dung HTML của phần tử "notification" để hiển thị thông báo lỗi.
        "<i class='fa fa-info-circle'></i> Vui lòng nhập nội dung đánh giá!"
      );
      $("#notification").show(); // Hiển thị phần tử có ID "notification".
    } else {
      if (typeof rating === "undefined") { // Kiểm tra nếu chưa chọn số sao đánh giá.
        $("#notification").removeClass("alert-success"); // Loại bỏ lớp CSS "alert-success" cho phần tử có ID "notification".
        $("#notification").addClass("alert-danger"); // Thêm lớp CSS "alert-danger" cho phần tử có ID "notification".
        $("#notification").html( // Đặt nội dung HTML của phần tử "notification" để hiển thị thông báo lỗi.
          "<i class='fa fa-info-circle'></i> Vui lòng chọn số sao đánh giá!"
        );
        $("#notification").show(); // Hiển thị phần tử có ID "notification".
      }
      else{ // Nếu đã có nội dung và số sao đánh giá.
        $("#notification").removeClass("alert-danger"); // Loại bỏ lớp CSS "alert-danger" cho phần tử có ID "notification"
        $("#notification").addClass("alert-success"); // Thêm lớp CSS "alert-success" cho phần tử có ID "notification".
        $("#notification").html( // Đặt nội dung HTML của phần tử "notification" để hiển thị thông báo thành công.
          "<i class='fa fa-check-circle'></i> Cám ơn, vì phản hồi của bạn. Nó đã được gửi cho quản trị viên web để phê duyệt!"
        );
        $("#notification").show(); // Hiển thị phần tử có ID "notification".
        
        $scope.formComment.productId = productId; // Gán giá trị productId vào thuộc tính productId của đối tượng formComment.
        $scope.formComment.content = content; // Gán nội dung comment vào thuộc tính content của đối tượng formComment.
        $scope.formComment.star = rating; // Gán số sao đánh giá vào thuộc tính star của đối tượng formComment.
        
        $http.post(`/rest/comment/form`, $scope.formComment).then((resp) => { // Gửi dữ liệu comment lên server thông qua HTTP POST request.
          console.log(resp.data);  
        });

        $("#input-review").val(""); // Xóa nội dung trong ô nhập review.
        $('input[name="rating"]').removeAttr('checked'); // Bỏ chọn các radio buttons đánh giá.
      }
    }
  };

  $scope.pager = {
    page: 0,
    size: 3.0,
    get items(){
        var start = this.page * this.size;
        return $scope.listComment.slice(start, start + this.size);
        
    },
    get count() {
        return Math.ceil(1.0 * $scope.listComment.length / this.size);
    },
    first(){
      this.page = 0;
    },
    prev(){
      this.page--;
      if(this.page < 0){
        this.last();
      }
      
    },
    next(){
      this.page++;
      if(this.page >= this.count){
        this.first();
      }
    },
    last(){
      this.page = this.count -1;
    }
}

  // Xy ly comment
});
