var statusChart = false;
var chart = "bar";
var chartShowing = "";
var myChartDay;
var myChartMonth;
var myChartYear;

function changeChart() {
	 // Đảo ngược trạng thái của biến statusChart (chỉ đơn giản là chuyển đổi giữa true và false)
  statusChart = !statusChart;
  // Dựa vào giá trị mới của statusChart, thiết lập loại biểu đồ (line hoặc bar) và cập nhật nội dung của các thẻ HTML
  if (statusChart) {
    chart = "line";
    $("#chartBar").html("BIỂU ĐỒ ĐƯỜNG");
    $("#chartLine").html("BIỂU ĐỒ CỘT");
  } else {
    chart = "bar";
    $("#chartBar").html("BIỂU ĐỒ CỘT");
    $("#chartLine").html("BIỂU ĐỒ ĐƯỜNG");
  }

// Tùy thuộc vào loại biểu đồ đang hiển thị (day, month, year), gọi hàm thống kê tương ứng
  if (chartShowing == "day") {
    angular.element("#chartLine").scope().statisticalByDay();
  }

  if (chartShowing == "month") {
    angular.element("#chartLine").scope().statisticalByMonth();
  }

  if (chartShowing == "year") {
    angular.element("#chartLine").scope().statisticalByYear();
  }
}

function loadChartDay() {
  chartShowing = "day"; // Đặt biến chartShowing là "day" để đánh dấu hiển thị biểu đồ theo ngày
  $("#bar-chart-grouped-day").show(); // Hiển thị biểu đồ theo ngày
  $("#bar-chart-grouped-month").hide(); // Ẩn biểu đồ theo tháng
  $("#bar-chart-grouped-year").hide(); // Ẩn biểu đồ theo năm
}

function loadChartMonth() {
  chartShowing = "month"; // Đặt biến chartShowing là "month" để đánh dấu hiển thị biểu đồ theo tháng
  $("#bar-chart-grouped-day").hide(); // Ẩn biểu đồ theo ngày
  $("#bar-chart-grouped-month").show(); // Hiển thị biểu đồ theo tháng
  $("#bar-chart-grouped-year").hide(); // Ẩn biểu đồ theo năm
}

function loadChartYear() {
  chartShowing = "year"; // Đặt biến chartShowing là "year" để đánh dấu hiển thị biểu đồ theo năm
  $("#bar-chart-grouped-day").hide(); // Ẩn biểu đồ theo ngày
  $("#bar-chart-grouped-month").hide(); // Ẩn biểu đồ theo tháng
  $("#bar-chart-grouped-year").show(); // Hiển thị biểu đồ theo năm
}


var app = angular.module("statistical-revenue-app", []);

app.controller("statistical-revenue-ctrl", function ($scope, $http) {
  $scope.items = [];
  
  // Gọi hàm loadChartDay để cập nhật trạng thái biểu đồ theo ngày
  $scope.statisticalByDay = function () {
    loadChartDay();
    // Lấy ngày hiện tại
    var date = new Date();
    // Gửi yêu cầu GET đến endpoint "/rest/statistical/revenue/day/" để lấy dữ liệu doanh thu theo ngày từ máy chủ
    $http.get("/rest/statistical/revenue/day/" + (date.getMonth() + 1) + "/" + date.getFullYear())
    .then((resp) => {
		// Gán dữ liệu trả về từ máy chủ vào $scope.items
        $scope.items = resp.data;
        
        // Tạo mảng arrDay và arrPrice để chứa dữ liệu cho biểu đồ
        var arrDay = [];
        var arrPrice = [];
        var length = resp.data.length;
        
        // Lặp qua dữ liệu để điền vào mảng arrDay và arrPrice
        for (var i = 0; i < length; i++) {
          arrDay[i] = i + 1;
          arrPrice[i] = resp.data[i].price;
        }
        
        // Tạo đối tượng dataMyBarDay để cấu hình dữ liệu cho biểu đồ cột
        var dataMyBarDay = {
          type: "bar",
          data: {
            labels: arrDay,
            datasets: [
              {
                label: "Triệu đồng",
                backgroundColor: "green",
                data: arrPrice,
              },
            ],
          },
          options: {
            title: {
              display: true,
              text:
                "Biểu đồ doanh thu " +
                length +
                " ngày của tháng " +
                (date.getMonth() + 1) +
                " năm " +
                date.getFullYear(),
            },
          },
        };

		// Lấy đối tượng CanvasRenderingContext2D từ phần tử có ID là "bar-chart-grouped-day"
        var ctx = document
          .getElementById("bar-chart-grouped-day")
          .getContext("2d");
          
        // Kiểm tra nếu biểu đồ đã được tạo trước đó, hủy nó để tạo lại
        if (myChartDay) {
          myChartDay.destroy();
        }

		// Thiết lập loại biểu đồ dựa trên giá trị của biến chart, sau đó tạo biểu đồ mới
        dataMyBarDay.type = chart;
        myChartDay = new Chart(ctx, dataMyBarDay);
      });
  };
  

  $scope.statisticalByMonth = function () {
	  // Gọi hàm loadChartMonth để cập nhật trạng thái biểu đồ theo tháng
    loadChartMonth();
    // Lấy ngày hiện tại
    var date = new Date();
    // Gửi yêu cầu GET đến endpoint "/rest/statistical/revenue/month/" để lấy dữ liệu doanh thu theo tháng từ máy chủ
    $http
      .get("/rest/statistical/revenue/month/" + date.getFullYear())
      .then((resp) => {
		  // Gán dữ liệu trả về từ máy chủ vào $scope.items
        $scope.items = resp.data;
        
        // Khởi tạo mảng arrYear và arrPrice để chứa dữ liệu cho biểu đồ
        var arrYear = [];
        var arrPrice = [];
        
        // Lặp qua dữ liệu để điền vào mảng arrYear và arrPrice từ dữ liệu trả về
        for (var i = 0; i < resp.data.length; i++) {
          arrYear[i] = i + 1;
          arrPrice[i] = resp.data[i].price;
        }
        
        // Tạo đối tượng dataMyChartMonth để cấu hình dữ liệu cho biểu đồ cột
        var dataMyChartMonth = {
          type: "bar",
          data: {
            labels: arrYear,
            datasets: [
              {
                label: "Triệu đồng",
                backgroundColor: "yellow",
                data: arrPrice,
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ doanh thu 12 tháng của năm " + date.getFullYear(),
            },
          },
        };

		// Lấy đối tượng CanvasRenderingContext2D từ phần tử có ID là "bar-chart-grouped-month"
        var ctx = document.getElementById("bar-chart-grouped-month").getContext("2d");
        // Kiểm tra nếu biểu đồ đã được tạo trước đó, hủy nó để tạo lại
        if (myChartMonth) {
          myChartMonth.destroy();
        }
		
		// Thiết lập loại biểu đồ dựa trên giá trị của biến chart, sau đó tạo biểu đồ mới
        dataMyChartMonth.type = chart;
        myChartMonth = new Chart(ctx, dataMyChartMonth);
      });
  };

  $scope.statisticalByYear = function () {
	  // Gọi hàm loadChartYear để cập nhật trạng thái biểu đồ theo năm
    loadChartYear();
     // Lấy ngày hiện tại
    var date = new Date();
    // Gửi yêu cầu GET đến endpoint "/rest/statistical/revenue/year/" để lấy dữ liệu doanh thu theo năm từ máy chủ
    $http
      .get("/rest/statistical/revenue/year/" + date.getFullYear())
      .then((resp) => {
		  // Gán dữ liệu trả về từ máy chủ vào $scope.items
        $scope.items = resp.data;
        // Lấy 3 năm gần nhất
      var arrYear = [];
      var arrPrice = [];
      var length = Math.min(3, resp.data.length); // Chọn số năm tối đa là 3 hoặc số năm có sẵn nếu nhỏ hơn 3

		// Lặp qua dữ liệu để điền vào mảng arrYear và arrPrice từ dữ liệu trả về
      for (var i = resp.data.length - length; i < resp.data.length; i++) {
        arrYear[i - (resp.data.length - length)] = resp.data[i].date;
        arrPrice[i - (resp.data.length - length)] = resp.data[i].price;
      }

	  // Tạo đối tượng dataMyChartYear để cấu hình dữ liệu cho biểu đồ cột
      var dataMyChartYear = {
        type: "bar",
        data: {
          labels: arrYear,
          datasets: [
            {
              label: "Triệu đồng",
              backgroundColor: "brown",
              data: arrPrice,
            },
          ],
        },
        options: {
          title: {
            display: true,
            text:
              "Biểu đồ doanh thu " +
              length +
              " năm gần nhất từ năm " +
              arrYear[0] +
              " đến năm " +
              arrYear[length - 1],
          },
        },
      };

		// Lấy đối tượng CanvasRenderingContext2D từ phần tử có ID là "bar-chart-grouped-year"
        var ctx = document
          .getElementById("bar-chart-grouped-year")
          .getContext("2d");
          
          // Kiểm tra nếu biểu đồ đã được tạo trước đó, hủy nó để tạo lại
        if (myChartYear) {
          myChartYear.destroy();
        }

		// Thiết lập loại biểu đồ dựa trên giá trị của biến chart, sau đó tạo biểu đồ mới
        dataMyChartYear.type = chart;
        myChartYear = new Chart(ctx, dataMyChartYear);
      });
  };

  $scope.statisticalByDay();
});
