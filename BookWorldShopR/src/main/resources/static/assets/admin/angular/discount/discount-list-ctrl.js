angular.module("discount-app", ["discount-app.controllers", "datatables"]);
angular
  .module("discount-app.controllers", [])
  .controller(
    "discount-ctrl",
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
      // hiển thị tất cả mã giảm giá lên trang
      $scope.initialize = function () {
        $http.get("/rest/discount").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	 // hiển thị thông báo có muốn xóa mã giảm giá hay không
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

		
	  // xóa mã giảm giá	
      $scope.delete = function () {
		  // Gửi HTTP DELETE request đến endpoint "/rest/discount/" với tham số là id của mã giảm giá cần xóa
        $http
          .delete(`/rest/discount/` + $scope.form.id)
          .then((resp) => {
			  // Xác định index của mã giảm giá trong danh sách $scope.items
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            // Xóa mã giảm giá khỏi danh sách $scope.items
            $scope.items.splice(index, 1);

			// Hiển thị thông báo thành công và mở modal
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa mã thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {
            console.log(error);
          });
      };

		
	  // chuyển href sang trang update	
      $scope.update = function (item) {
        var path = "/admin/discount/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(7).notSortable(),  // //Trong trường hợp này, cột thứ 8 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true)  //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true); // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
