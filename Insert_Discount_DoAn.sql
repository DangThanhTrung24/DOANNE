﻿INSERT INTO Discount VALUES
(N'Giảm giá 20.000đ cho đơn hàng trên 100.000đ', N'TDTNTG', 20000, 5, CAST(N'2023-09-23' AS Date), CAST(N'2023-10-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, CAST(N'2023-09-22T21:41:24.543' AS DateTime), 1),
(N'Giảm giá 30.000đ cho đơn hàng trên 150.000đ', N'NNDB', 30000, 3, CAST(N'2023-09-23' AS Date), CAST(N'2023-10-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, CAST(N'2023-09-22T21:41:24.543' AS DateTime), 1),
(N'Giảm 50k cho đơn hàng từ 0đ', N'DCMNVCL', 50000, 4, CAST(N'2023-09-23' AS Date), CAST(N'2023-10-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, NULL, 0)

select * from Discount 