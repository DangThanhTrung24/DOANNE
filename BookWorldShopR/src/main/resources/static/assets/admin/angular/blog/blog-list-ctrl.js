angular.module("blog-app", ["blog-app.controllers", "datatables"]);
angular
  .module("blog-app.controllers", [])
  .controller(
    "blog-ctrl",
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
      
      // hiển thị blog
      $scope.initialize = function () {
        $http.get("/rest/blog").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // show bảng xác nhận xóa
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

	  // xóa blog
      $scope.delete = function () {
		  // Gửi HTTP DELETE request đến endpoint "/rest/blog/" với ID của bài đăng cần xóa
        $http
          .delete(`/rest/blog/` + $scope.form.id)
          .then((resp) => {
			  // Nếu request thành công, xử lý kết quả trả về từ server
        // Xác định index của đối tượng cần xóa trong mảng $scope.items
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            // Xóa đối tượng khỏi mảng $scope.items
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

	  // chuyển qua trang cập nhật
      $scope.update = function (item) {
		  // Xây dựng đường dẫn cho liên kết dựa trên ID của đối tượng
        var path = "/admin/blogs/update/" + item.id;
        // Sử dụng jQuery để cập nhật thuộc tính href của các thẻ a trong tài liệu
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(4).notSortable(), //Trong trường hợp này, cột thứ 5 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true) //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true); // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
