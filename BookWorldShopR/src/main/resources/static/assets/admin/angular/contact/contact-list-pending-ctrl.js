angular.module("contact-app", ["contact-app.controllers", "datatables"]);
angular
  .module("contact-app.controllers", [])
  .controller(
    "contact-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      
      // load tất cả đánh giá chưa duyệt
      $scope.initialize = function () {
        $http.get("/rest/contact/pending").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();
	
	  // thông tin chi tiết đánh giá
      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
		  // Gửi HTTP GET request đến endpoint "/rest/contact/pending/" với tham số là id của contact
        $http.get("/rest/contact/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        // hiển thị modal để hiển thị thông tin 
        $("#modalDetail").modal("show");
      };

	  // hiển thị thông báo có duyệt hay không
      $scope.formApprove = {};
      $scope.showModalApprove = function (item) {
        $scope.formApprove = item;
        $("#modalApprove").modal("show");
      };
 	  
 	  // duyệt đánh giá
      $scope.approve = function () {
		  // Gửi HTTP PUT request đến endpoint "/rest/contact/form/approve/" với tham số là id của contact cần duyệt
        $http
          .put(`/rest/contact/form/approve/` + $scope.formApprove.id)
          .then((resp) => {
			  // Xác định index của comment trong danh sách và loại bỏ nó
            var index = $scope.items.findIndex((p) => p.id == $scope.formApprove.id);
            $scope.items.splice(index, 1);
            
            // Hiển thị thông báo thành công trong modal
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã duyệt thành công đánh giá này!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            console.log(error);
          });
      };
		
	  // hiển thị thông báo có xóa đánh giá hay không
      $scope.formDelete = {};
      $scope.showModal = function (item) {
        $scope.formDelete = item;
        $("#modal").modal("show");
      };

	  // xóa đánh giá
      $scope.delete = function () {
		  // Gửi HTTP DELETE request đến endpoint "/rest/contact/form/approve/" với tham số là id của contact cần duyệt
        $http
          .delete(`/rest/contact/form/delete/` + $scope.formDelete.id)
          .then((resp) => {
			  // Xác định index của comment trong danh sách và loại bỏ nó
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formDelete.id
            );
            $scope.items.splice(index, 1);

			// Hiển thị thông báo thành công trong modal
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa đánh giá thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {          
          });
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(4).notSortable(), //Trong trường hợp này, cột thứ 5 (đánh số từ 0) sẽ được định nghĩa là không thể sắp xếp.
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true) // Cho phép chia trang (paging) trong DataTables.
        .withOption("searching", true)  //Cho phép tính năng tìm kiếm trong DataTables.
        .withOption("info", true); // Hiển thị thông tin về số lượng bản ghi và trang hiện tại.
    }
  );
