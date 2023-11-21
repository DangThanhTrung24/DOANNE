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
      // hiển thị danh sách nhà cung cấp
      $scope.initialize = function () {
        $http.get("/rest/manufactures").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // hiện thị thông báo xóa hoặc không
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

	  // xóa thương hiệu
      $scope.delete = function () {
        $http
          .delete(`/rest/manufactures/` + $scope.form.id)
          .then((resp) => {
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa thương hiệu thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {
            console.log(error);
          });
      };

	  // chuyển sang trang update
      $scope.update = function (item) {
        var path = "/admin/manufactures/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(2).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
