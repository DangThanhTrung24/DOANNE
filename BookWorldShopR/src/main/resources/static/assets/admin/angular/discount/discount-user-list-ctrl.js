var checkDiscount = 0;

$("#checkClick").prop("disabled", true);

$(document).ready(function () {
	// discount chưa chọn thì trả về 10 ngược lại thì 1
  $("#discount").change(function () {
    var discount = this.value;
    if (discount == 0) {
      checkDiscount = 10;
    } else {
      checkDiscount = 1;
    }
    handlerButtonSave();
  });
});

// kiểm tra form đã chọn chưa
function checkForm() {
  $("#discount").change();
  return handlerButtonSave();
}

// nếu khác 10 thì không hiển thị nút gửi
function handlerButtonSave() {
  var check = false;
  if (checkDiscount !== 10) {
    check = true;
    $("#checkClick").prop("disabled", false);
  } else {
    check = false;
    $("#checkClick").prop("disabled", true);
  }
  return check;
}


angular.module("user-app", ["user-app.controllers", "datatables"]);
angular
  .module("user-app.controllers", [])
  .controller(
    "user-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];
      $scope.form = {};
      // hiển thị người dùng đã đăng kí trang web
      $scope.initialize = function () {
        $http.get("/rest/discount/user/list").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

		
	 // hiển thị thông báo	
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

		
	  // hiển thị tất cả mã giảm giá còn hạn trong thanh thông báo
      $scope.itemDiscount = [];
      $scope.loadDiscount = function () {
        $http.get("/rest/discount/available").then((resp) => {
          $scope.itemDiscount = resp.data;
          console.log($scope.itemDiscount);
        });
      };
      $scope.loadDiscount();

		
	  // gửi mã giảm giá vào mail của người đã đăng kí web	
      $scope.sendCodeDiscount = function () {
        if(checkForm()){
			// Lấy giá trị mã giảm giá từ phần tử có id là "discount"
            var code = $("#discount").val();
            // Gửi HTTP POST request đến endpoint "/rest/discount/user/list/" + code
        // với dữ liệu từ đối tượng $scope.form
            $http.post("/rest/discount/user/list/" + code, $scope.form).then(resp => {	
				// Hiển thị modal thông báo thành công			
				$("#modalInfo").modal("show");
				
				// Xây dựng đường dẫn cho liên kết
				var path = "/admin/discount/user/list";
				$("a").attr("href", path);
				console.log(resp);
            }).catch(error => {
                alert("Lỗi gửi mã")             
                console.log(error);
            });
        }
      }

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(),  //Trong trường hợp này, cột thứ 6 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true)  //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true);  // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
