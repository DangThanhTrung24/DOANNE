angular.module("shop-app", ["shop-app.controllers", "datatables"]);
angular
  .module("shop-app.controllers", [])
  .controller(
    "shop-ctrl",
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
      // hiển thị tất cả danh sách thông tin shop
      $scope.initialize = function () {
        $http.get("/rest/shop").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // hiển thị thông báo xóa hay không
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

	  // thực hiện xóa thông tin shop
      $scope.delete = function () {
		  // Gửi một yêu cầu DELETE đến URL "/rest/shop/" cộng với giá trị của thuộc tính id của đối tượng $scope.form
        $http
          .delete(`/rest/shop/` + $scope.form.id)
          .then((resp) => {
			  // Tìm vị trí của phần tử có id tương ứng trong mảng $scope.items
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            // Xóa phần tử tại vị trí đã tìm được từ mảng $scope.items
            $scope.items.splice(index, 1);
			
			// Cập nhật đối tượng $scope.info với trạng thái thành công và thông báo
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa thông tin thành công!";
            // Hiển thị một modal có ID là "modalInfo"
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
			  $scope.info.status = false;
            $scope.info.alert = "Cảnh Báo!";
            $scope.info.content =
              "Bạn không được phép xóa thông tin shop khi đang sử dụng!";
            $("#modalInfo").modal("show");
            console.log(error);
          });
      };


	  // sử dụng thông tin shop được chọn
      $scope.active = function (item) {
        //var temp = angular.copy($scope.form);
        // Gửi một yêu cầu PUT đến URL "/rest/shop/form/active/" cộng với giá trị id của mục, với dữ liệu của chính mục đó
        $http
          .put("/rest/shop/form/active/" + item.id, item)
          .then((resp) => {
			  // Cập nhật đối tượng $scope.info với trạng thái thành công và thông báo
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã cập nhật thông tin thành công!";
            // Hiển thị một modal có ID là "modalInfo"
            $("#modalInfo").modal("show");
            // Gọi hàm initialize để thực hiện các công việc khởi tạo sau khi cập nhật thông tin
            $scope.initialize();
          })
          .catch((error) => {
            alert("Lỗi thêm sản phẩm");
            console.log(error);
          });
      };

	  // chuyển hướng tới trang thông tin chi tiết của shop để cập nhật
      $scope.update = function (item) {
        var path = "/admin/shop/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
