$(document).ready(function () {});

angular.module("employee-app", ["employee-app.controllers", "datatables"]);
angular
  .module("employee-app.controllers", [])
  .controller(
    "employee-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];
      $scope.form = {};
      $scope.info = {};
      
      // hiển thị tất cả các nhân viên lên trang 
      $scope.initialize = function () {
        $http.get("/rest/employees").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	 // hiển thị thông báo
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

	  // xóa nhân viên 
      $scope.delete = function () {
		 // Gửi HTTP DELETE request đến endpoint "/rest/employees/" kèm theo id của người dùng để xóa tài khoản.
        $http
          .delete(`/rest/employees/` + $scope.form.user.id)
          .then((resp) => {
			  // Nếu xóa thành công
            var index = $scope.items.findIndex(
              (p) => p.user.id == $scope.form.user.id
            );
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa tài khoản thành công!";
            $("#modalInfo").modal("show");

            //alert("Xoá sản phẩm thành công!");
          })
          .catch((error) => {
			  // Nếu xóa thất bại
            $scope.info.status = false;
            $scope.info.alert = "Cảnh Báo!";
            $scope.info.content =
              "Bạn không được phép xóa tài khoản đang đăng nhập!";
            $("#modalInfo").modal("show");
            //alert("Lỗi xoá sản phẩm")
            console.log(error);
          });
      };

	  // không cho phép cập nhật tài khoản đang ưnhaap
      $scope.update = function (item) {
		  // Kiểm tra xem tài khoản cần cập nhật có phải là tài khoản đang đăng nhập không
        if (item.user.email == $("#username").val()) {
			// Nếu là tài khoản đang đăng nhập, hiển thị cảnh báo và ngăn chặn chuyển hướng
          $scope.info.status = false;
          $scope.info.alert = "Cảnh Báo!";
          $scope.info.content =
            "Bạn không được phép cập nhật tài khoản đang đăng nhập!";
          $("#modalInfo").modal("show");
        } else {
			// Nếu không phải là tài khoản đang đăng nhập, xây dựng đường dẫn và chuyển hướng đến trang cập nhật thông tin tài khoản
          var path = "/admin/employees/update/" + item.user.id;
          $("a").attr("href", path);
        }
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(8).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
