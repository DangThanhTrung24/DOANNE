angular.module("order-cancel-app", ["order-cancel-app.controllers", "datatables"]);
angular
  .module("order-cancel-app.controllers", [])
  .controller(
    "order-cancel-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      
      // hiển thị tất cả các đơn hàng đã hủy lên form
      $scope.initialize = function () {
        $http.get("/rest/order/cancel").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // hiển thị chi tiết đơn hàng đã hủy
      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/order/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        // hiển thị modal chi tiết đơn hàng
        $("#modalDetail").modal("show");
      };


	  // hiển thị thông báo muốn xóa đơn hàng hay không
      $scope.formDelete = {};
      $scope.showModal = function (item) {
        $scope.formDelete = item;
        $("#modal").modal("show");
      };


	  // xóa đơn hàng
      $scope.delete = function () {
        $http
          .delete(`/rest/order/delete/` + $scope.formDelete.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formDelete.id
            );
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa vĩnh viễn đơn hàng thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {          
          });
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(6).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
