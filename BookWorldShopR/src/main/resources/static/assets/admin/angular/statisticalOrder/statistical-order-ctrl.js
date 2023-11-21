var statusChart = false;
var chart = "pie";
var chartShowing = "";
var myChartDay;
var myChartMonth;
var myChartYear;

// Tắt chức năng của nút có ID "btnFind"
$("#btnFind").prop("disabled", true);

// Hiển thị modal có ID "optionModal"
function showModal() {
  $("#optionModal").modal("show");
}

// Ẩn modal có ID "optionModal"
function hideModel() {
  $("#optionModal").modal("hide");
}

// Hàm thay đổi loại biểu đồ và cập nhật giao diện người dùng tương ứng
function changeChart() {
	// Đảo ngược trạng thái của biểu đồ
  statusChart = !statusChart;
  // Cập nhật loại biểu đồ và nội dung giao diện dựa trên trạng thái biểu đồ
  if (statusChart) {
    chart = "polarArea";
    $("#chartPie").html("BIỂU ĐỒ VÙNG CỰC");
    $("#chartPolar").html("BIỂU ĐỒ TRÒN");
  } else {
    chart = "pie";
    $("#chartPie").html("BIỂU ĐỒ TRÒN");
    $("#chartPolar").html("BIỂU ĐỒ VÙNG CỰC");
  }

  // Cập nhật biểu đồ dựa trên loại biểu đồ hiện tại và chế độ hiển thị
  if (chartShowing == "day") {
    angular.element("#chartPie").scope().statisticalByDay();
  }

  if (chartShowing == "month") {
    angular.element("#chartPie").scope().statisticalByMonth();
  }

  if (chartShowing == "year") {
    angular.element("#chartPie").scope().statisticalByYear();
  }

  if (chartShowing == "option") {
    angular.element("#chartPie").scope().statisticalByOption();
  }
}

// Thiết lập chế độ hiển thị biểu đồ là ngày và hiển thị biểu đồ tương ứng
function loadChartDay() {
  chartShowing = "day";
  $("#pie-chart-grouped-day").show();
  $("#pie-chart-grouped-month").hide();
  $("#pie-chart-grouped-year").hide();
  $("#pie-chart-grouped-option").hide();
}

// Thiết lập chế độ hiển thị biểu đồ là tháng và hiển thị biểu đồ tương ứng
function loadChartMonth() {
  chartShowing = "month";
  $("#pie-chart-grouped-day").hide();
  $("#pie-chart-grouped-month").show();
  $("#pie-chart-grouped-year").hide();
  $("#pie-chart-grouped-option").hide();
}

// Thiết lập chế độ hiển thị biểu đồ là năm và hiển thị biểu đồ tương ứng
function loadChartYear() {
  chartShowing = "year";
  $("#pie-chart-grouped-day").hide();
  $("#pie-chart-grouped-month").hide();
  $("#pie-chart-grouped-year").show();
  $("#pie-chart-grouped-option").hide();
}

// Thiết lập chế độ hiển thị biểu đồ là tùy chọn và ẩn các biểu đồ khác
function loadChartOption() {
  chartShowing = "option";
  $("#pie-chart-grouped-option").hide();
  $("#pie-chart-grouped-day").hide();
  $("#pie-chart-grouped-month").hide();
  $("#pie-chart-grouped-year").hide();
}

var app = angular.module("statistical-order-app", []);

app.controller("statistical-order-ctrl", function ($scope, $http) {
  $scope.items = [];
  
  // Khai báo hàm thực hiện việc thống kê đơn hàng theo ngày và hiển thị biểu đồ pie tương ứng
  $scope.statisticalByDay = function () {
	  // Gọi hàm để thiết lập chế độ hiển thị biểu đồ là ngày
    loadChartDay();
    
    // Tạo đối tượng Date để lấy thông tin ngày hiện tại
    var date = new Date();
	// Gửi yêu cầu GET đến API để lấy dữ liệu thống kê đơn hàng theo ngày
    $http
      .get(
        "/rest/statistical/order/day/" +
          date.getDate() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
      )
      .then((resp) => {
		  // Lưu dữ liệu thống kê từ API vào biến $scope.items
        $scope.items = resp.data;
        // Tạo mảng arrDay để chứa dữ liệu từ API
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;

		// Tạo đối tượng dataMyPieDay để cấu hình biểu đồ pie
        var dataMyPieDay = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo ngày",
            },
          },
        };
		// Lấy đối tượng Canvas để vẽ biểu đồ
        var ctx = document
          .getElementById("pie-chart-grouped-day")
          .getContext("2d");
        // Kiểm tra xem biểu đồ đã được tạo chưa, nếu có thì hủy để vẽ lại  
        if (myChartDay) {
          myChartDay.destroy();
        }
		// Cập nhật loại biểu đồ và tạo biểu đồ mới
        dataMyPieDay.type = chart;
        myChartDay = new Chart(ctx, dataMyPieDay);
      });
  };

// thông kê đơn hàng theo tháng
  $scope.statisticalByMonth = function () {
	  // Gọi hàm để thiết lập chế độ hiển thị biểu đồ là tháng
    loadChartMonth();
    // Tạo đối tượng Date để lấy thông tin tháng và năm hiện tại
    var date = new Date();
	// Gửi yêu cầu GET đến API để lấy dữ liệu thống kê đơn hàng theo tháng
    $http
      .get(
        "/rest/statistical/order/month/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
      )
      .then((resp) => {
		  // Lưu dữ liệu thống kê từ API vào biến $scope.items
        $scope.items = resp.data;
        // Tạo mảng arrDay để chứa dữ liệu từ API
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;
		// Tạo đối tượng dataMyPieMonth để cấu hình biểu đồ pie
        var dataMyPieMonth = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo tháng",
            },
          },
        };

        // Lấy đối tượng Canvas để vẽ biểu đồ
        var ctx = document
          .getElementById("pie-chart-grouped-month")
          .getContext("2d");
        
        // Kiểm tra xem biểu đồ đã được tạo chưa, nếu có thì hủy để vẽ lại
        if (myChartMonth) {
          myChartMonth.destroy();
        }

        // Cập nhật loại biểu đồ và tạo biểu đồ mới
        dataMyPieMonth.type = chart;
        myChartMonth = new Chart(ctx, dataMyPieMonth);
      });
  };


// thông kê đơn hàng theo năm
  $scope.statisticalByYear = function () {
	  // Gọi hàm để thiết lập chế độ hiển thị biểu đồ là năm
    loadChartYear();
    // Tạo đối tượng Date để lấy thông tin năm hiện tại
    var date = new Date();
	// Gửi yêu cầu GET đến API để lấy dữ liệu thống kê đơn hàng theo năm
    $http
      .get("/rest/statistical/order/year/" + date.getFullYear())
      .then((resp) => {
		  // Lưu dữ liệu thống kê từ API vào biến $scope.items
        $scope.items = resp.data;
        // Tạo mảng arrDay để chứa dữ liệu từ API
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;
		// Tạo đối tượng dataMyPieYear để cấu hình biểu đồ pie
        var dataMyPieYear = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo tháng",
            },
          },
        };

        // Lấy đối tượng Canvas để vẽ biểu đồ
        var ctx = document
          .getElementById("pie-chart-grouped-year")
          .getContext("2d");
        
        // Kiểm tra xem biểu đồ đã được tạo chưa, nếu có thì hủy để vẽ lại
        if (myChartYear) {
          myChartYear.destroy();
        }

        // Cập nhật loại biểu đồ và tạo biểu đồ mới
        dataMyPieYear.type = chart;
        myChartYear = new Chart(ctx, dataMyPieYear);
      });
  };

  $scope.itemYear = [];
  // hiển thị năm lên form modal
  $scope.loadYear = function () {
    $http.get("/rest/statisticalOrder/year").then((resp) => {
      $scope.itemYear = resp.data;
      console.log($scope.itemYear);
    });
  };


// thông kê theo tìm kiếm 
  $scope.statisticalByOption = function () {
	  // Lấy giá trị năm, tháng, và ngày từ các ô input trên giao diện
    var year = $("#year").val();
    var month = $("#month").val();
    var day = $("#day").val();
    // Gọi hàm ẩn modal chọn ngày/tháng/năm
	hideModel();
	// Gọi hàm để thiết lập chế độ hiển thị biểu đồ là option
	loadChartOption();
	// Gửi yêu cầu GET đến API để lấy dữ liệu thống kê đơn hàng theo ngày/tháng/năm được chọn
    $http.get("/rest/statistical/order/option/" + day + "/" + month + "/" + year).then((resp) => {
		 // Lưu dữ liệu thống kê từ API vào biến $scope.items
        $scope.items = resp.data;
        // Tạo mảng arrDay để chứa dữ liệu từ API
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;

		// Tạo đối tượng dataMyPieDay để cấu hình biểu đồ pie
        var dataMyPieDay = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo ngày",
            },
          },
        };

        // Lấy đối tượng Canvas để vẽ biểu đồ
        var ctx = document
          .getElementById("pie-chart-grouped-day")
          .getContext("2d");
        
        // Kiểm tra xem biểu đồ đã được tạo chưa, nếu có thì hủy để vẽ lại
        if (myChartDay) {
          myChartDay.destroy();
        }

        // Cập nhật loại biểu đồ và tạo biểu đồ mới
        dataMyPieDay.type = chart;
        myChartDay = new Chart(ctx, dataMyPieDay);
      });
  };
// Gọi hàm thống kê đơn hàng theo ngày mặc định khi trang được tải
  $scope.statisticalByDay();
  // Gọi hàm load danh sách năm khi trang được tải
  $scope.loadYear();
});



// Khi giá trị của dropdown "Năm" thay đổi
$("#year").change(function () {
	// Lấy giá trị năm được chọn
  var year = this.value;
   // Nếu năm đã được chọn
  if (year != 0) {
	  // Xóa danh sách tháng hiện có
    destroyMonth();
    // Tạo danh sách tháng mới
    loadMonth();
    // Bật nút "Tìm kiếm"
    $("#btnFind").prop("disabled", false);
  } else {
	  // Nếu năm không được chọn
    // Xóa danh sách tháng và ngày
    destroyMonth();
    destroyDay();
    $("#btnFind").prop("disabled", true);
  }
});

// Khi giá trị của dropdown "Tháng" thay đổi
$("#month").change(function () {
	// Lấy giá trị tháng được chọn
  var month = this.value;
  // Nếu tháng đã được chọn
  if (month != 0) {
	  // Xóa danh sách ngày hiện có
    destroyDay();
    // Tạo danh sách ngày mới
    loadDay();
  } else {
	  // Nếu tháng không được chọn
    // Xóa danh sách ngày
    destroyDay();
  }
});

// Hàm tạo và thêm option cho dropdown "Tháng"
function loadMonth() {
  for (var i = 1; i <= 12; i++) {
	  // Tạo option cho 12 tháng
    $("#month").append("<option value=" + i + ">" + i + "</option>");
  }
}

// Hàm xóa danh sách tháng hiện có
function destroyMonth() {
	// Xóa tất cả các option trong dropdown "Tháng"
  for (var i = 1; i <= 12; i++) {
    $("#month option[value='" + i + "']").remove();
  }
}

// Hàm tạo và thêm option cho dropdown "Ngày"
function loadDay() {
  // Lấy giá trị năm và tháng từ dropdowns tương ứng
  var year = $("#year").val();
  var month = $("#month").val();
  // Xác định số ngày tối đa trong tháng và năm đã chọn
  var maxDay = new Date(year, month, 0).getDate();

  // Tạo option cho số ngày trong tháng
  for (var i = 1; i <= maxDay; i++) {
    $("#day").append("<option value=" + i + ">" + i + "</option>");
  }
}

// Hàm xóa danh sách ngày hiện có
function destroyDay() {
  // Xóa tất cả các option trong dropdown "Ngày"
  for (var i = 1; i <= 31; i++) {
    $("#day option[value='" + i + "']").remove();
  }
}