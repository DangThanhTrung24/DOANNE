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
      
      // hiển thị tất cả bình luận
      $scope.initialize = function () {
        $http.get("/rest/comment/pending").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // hiển thị thông tin chi tiết của bình luận
      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
		   // Gửi HTTP GET request đến endpoint "/rest/comment/pending/" với tham số là id của comment
        $http.get("/rest/comment/pending/" + detail.id).then((resp) => {
			// Gán dữ liệu nhận được vào biến $scope.formDetail để hiển thị trong modal
          $scope.formDetail = resp.data;
        });
        // Hiển thị modal chi tiết
        $("#modalDetail").modal("show");
      };

	 // hiển thị thông báo duyệt hay không
      $scope.formApprove = {};
      $scope.showModalApprove = function (item) {
        $scope.formApprove = item;
        $("#modalApprove").modal("show");
      };

	  // duyệt comment
      $scope.approve = function () {
		  // Gửi HTTP PUT request đến endpoint "/rest/comment/form/approve/" với tham số là id của comment cần duyệt
        $http
          .put(`/rest/comment/form/approve/` + $scope.formApprove.id)
          .then((resp) => {
			  // Xác định index của comment trong danh sách và loại bỏ nó
            var index = $scope.items.findIndex((p) => p.id == $scope.formApprove.id);
            $scope.items.splice(index, 1);
            
            // Hiển thị thông báo thành công trong modal
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã duyệt thành công bình luận này!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            console.log(error);
          });
      };

	  // hiển thị thông báo có xóa hay không
      $scope.formDelete = {};
      $scope.showModal = function (item) {
        $scope.formDelete = item;
        $("#modal").modal("show");
      };

	// xóa comment 
      $scope.delete = function () {
		  // Gửi HTTP DELETE request đến endpoint "/rest/comment/form/approve/" với tham số là id của comment cần duyệt
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
        DTColumnDefBuilder.newColumnDef(5).notSortable(),  //Trong trường hợp này, cột thứ 6 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true)  //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true); // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
