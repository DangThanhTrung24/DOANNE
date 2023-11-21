angular.module("nav1-app", ["nav1-app.controllers", "datatables"]);
angular
  .module("nav1-app.controllers", [])
  .controller(
    "nav1-ctrl",
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
      
      // hiển thị tất cả menu 1 lên form
      $scope.initialize = function () {
        $http.get("/rest/nav1").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // hiển thị thông báo muốn xóa hay không
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

      // xóa một menu 1 khỏi web
      $scope.delete = function () {
        $http
          .delete(`/rest/nav1/` + $scope.form.id)
          .then((resp) => {
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa thương hiệu thành công!";
            $("#modalInfo").modal("show");

            //alert("Xoá sản phẩm thành công!");
          })
          .catch((error) => {
            console.log(error);
          });
      };

	  // khi ấn update sẽ chuyển qua đường form update
      $scope.update = function (item) {
        var path = "/admin/nav1/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(3).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
