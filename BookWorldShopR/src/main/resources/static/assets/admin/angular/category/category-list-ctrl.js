angular.module("category-app", ["category-app.controllers", "datatables"]);
angular
  .module("category-app.controllers", [])
  .controller(
    "category-ctrl",
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
      // load tất cả danh mục lên trang
      $scope.initialize = function () {
        $http.get("/rest/categories").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	 // hieent hị modal thông báo xóa
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

	  // hàm xóa danh mục
      $scope.delete = function () {
		   // Gửi HTTP DELETE request đến endpoint "/rest/categories/" với tham số là id của danh mục cần xóa
        $http
          .delete(`/rest/categories/` + $scope.form.id)
          .then((resp) => {
			  // Hàm then được gọi khi request thành công
        // resp chứa dữ liệu nhận được từ server (thường là một đối tượng JSON)
        
        // Tìm index của danh mục cần xóa trong mảng $scope.items và loại bỏ nó khỏi mảng
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            $scope.items.splice(index, 1);
			// Hiển thị thông báo thành công trong modal
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa danh mục thành công!";
            $("#modalInfo").modal("show");

            //alert("Xoá sản phẩm thành công!");
          })
          .catch((error) => {
            console.log(error);
          });
      };

		// chuyển qua trang update 
      $scope.update = function (item) {
        var path = "/admin/categories/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(), //Trong trường hợp này, cột thứ 6 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true) //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true); // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
