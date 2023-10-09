INSERT INTO [Employees] VALUES
(N'Developer', N'Project Manager', N'0889641810', CAST(N'2023-09-15' AS Date), 70000000, CAST(N'2023-09-20T19:13:14.837' AS DateTime), 0, NULL, 0, null, 0, 1, NULL),
(N'Developer', N'FE', N'0123456789', CAST(N'2023-09-15' AS Date), 10000000, CAST(N'2023-09-20T19:13:14.837' AS DateTime), 1, NULL, 0, null, 0, 2, NULL),
(N'Developer', N'BE', N'0987654321', CAST(N'2023-09-17' AS Date), 8500000, CAST(N'2023-09-24T19:13:14.837' AS DateTime), 1, NULL, 0, null, 0, 3, NULL),
(N'Tester', N'Automation', N'0123987456', CAST(N'2023-09-18' AS Date), 9000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 1, NULL, 0,  CAST(N'2023-10-06T21:36:32.043' AS DateTime), 3, 4, NULL),
(N'Developer', N'JavaDev', N'0789654123', CAST(N'2023-09-18' AS Date), 12000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 1, NULL, 0, null, 0, 5, NULL),
(N'Developer', N'WebApp', N'0147258369', CAST(N'2023-09-18' AS Date), 15000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 1,  CAST(N'2023-09-25T21:36:32.043' AS DateTime), 3, null, 0, 6, NULL),
(N'Developer', N'C# Intern', N'0369258147', CAST(N'2023-09-18' AS Date), 3000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 2, NULL, 0, null, 0, 7, NULL)



select * from Employees