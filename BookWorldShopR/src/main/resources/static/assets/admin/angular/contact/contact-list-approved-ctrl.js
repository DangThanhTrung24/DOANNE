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
      // hiển thị tất cả đánh giá đã duyệt
      $scope.initialize = function () {
        $http.get("/rest/contact/approved").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      // thông tin chi tiết của đánh giá
      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/contact/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        // hiển thị bảng chi tiết
        $("#modalDetail").modal("show");
      };

      // hiển thị thông báo xóa hay không
      $scope.formDelete = {};
      $scope.showModal = function (item) {
        $scope.formDelete = item;
        $("#modal").modal("show");
      };

	  // xóa đánh giá 
      $scope.delete = function () {
        $http
          .delete(`/rest/contact/form/delete/` + $scope.formDelete.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formDelete.id
            );
            $scope.items.splice(index, 1);

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
