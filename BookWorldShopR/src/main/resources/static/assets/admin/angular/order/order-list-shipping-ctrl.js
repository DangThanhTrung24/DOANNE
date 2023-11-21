angular.module("order-shipping-app", ["order-shipping-app.controllers", "datatables"]);
angular
  .module("order-shipping-app.controllers", [])
  .controller(
    "order-shipping-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      
      // hiển thị danh sách đang chờ giao lên form (admin)
      $scope.initialize = function () {
        $http.get("/rest/order/shipping").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	// hiển thị modal chi tiết đơn hàng
      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/order/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        $("#modalDetail").modal("show");
      };

	 // hiển thị thông báo xác nhận đã giao hay chưa
      $scope.formShipped = {};
      $scope.showModalShipped = function (item) {
        $scope.formShipped = item;
        $("#modalShipped").modal("show");
      };

	// xác nhận đơn hàng đã giao
      $scope.shipped = function () {
        $http
          .put(`/rest/order/shipped/` + $scope.formShipped.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formShipped.id
            );
            $scope.items.splice(index, 1);
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã giao thành công đơn hàng này!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            console.log(error);
          });
      };


//	  hiển thị thông bao hủy đơn hàng hay khong
      $scope.formCancel = {};
      $scope.showModal = function (item) {
        $scope.formCancel = item;
        $("#modal").modal("show");
      };

	  // hủy đơn hàng 
      $scope.cancel = function () {
        $http
          .put(`/rest/order/cancel/` + $scope.formCancel.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formCancel.id
            );
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã hủy đơn hàng thành công!";
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
