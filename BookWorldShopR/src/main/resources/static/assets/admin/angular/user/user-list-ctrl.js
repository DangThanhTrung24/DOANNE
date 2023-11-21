$(document).ready(function () {});

angular.module("user-app", ["user-app.controllers", "datatables"]);
angular
  .module("user-app.controllers", [])
  .controller(
    "user-ctrl",
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
      
      // hiện thị danh sách người dùng lên form
      $scope.initialize = function () {
        $http.get("/rest/user/list").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

	  // hiển thị modal thông báo có xóa hay không
      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

	  // xóa người dùng
      $scope.delete = function () {
        $http
          .delete(`/rest/user/` + $scope.form.id)
          .then((resp) => {
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa tài khoản người dùng thành công!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            alert("Lỗi xoá người dùng");
            console.log(error);
          });
      };

	// khi ấn vào update sẽ chuyển hướng
      $scope.update = function (item) {
        var path = "/admin/user/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
