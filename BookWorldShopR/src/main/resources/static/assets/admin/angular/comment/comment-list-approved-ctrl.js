angular.module("comment-app", ["comment-app.controllers", "datatables"]);
angular
  .module("comment-app.controllers", [])
  .controller(
    "comment-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      // load tất cả comment đã duyệt
      $scope.initialize = function () {
        $http.get("/rest/comment/approved").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // thông tin chi tiết comment
      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/comment/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        // show modal chi tiết
        $("#modalDetail").modal("show");
      };
	
	
	  // hiển thị thông báo muốn xóa hay không 
      $scope.formDelete = {};
      $scope.showModal = function (item) {
        $scope.formDelete = item;
        $("#modal").modal("show");
      };

	  // xóa comment 
      $scope.delete = function () {
		   // Gửi HTTP DELETE request đến endpoint "/rest/comment/form/approve/" với tham số là id của comment đã duyệt
        $http
          .delete(`/rest/comment/form/delete/` + $scope.formDelete.id)
          .then((resp) => {
			  // Xác định index của comment trong danh sách và loại bỏ nó
            var index = $scope.items.findIndex((p) => p.id == $scope.formDelete.id);
            $scope.items.splice(index, 1);
			// Hiển thị thông báo thành công trong modal
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa bình luận thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {          
          });
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(), //Trong trường hợp này, cột thứ 6 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
         .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true)  //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true); // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
