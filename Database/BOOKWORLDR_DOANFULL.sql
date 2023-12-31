USE [DoAnTotNghiep]
GO
/****** Object:  Table [dbo].[Address]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[FullName] [nvarchar](100) NOT NULL,
	[Phone] [varchar](10) NOT NULL,
	[Province] [nvarchar](50) NOT NULL,
	[District] [nvarchar](50) NOT NULL,
	[Ward] [nvarchar](50) NOT NULL,
	[Detail] [nvarchar](200) NOT NULL,
	[User_Id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Blogs]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blogs](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[Logo] [nvarchar](100) NOT NULL,
	[UploadDay] [date] NOT NULL,
	[Banner] [nvarchar](255) NOT NULL,
	[Active] [bit] NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
	[NameSearch] [varchar](50) NULL,
	[Description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Logo] [varchar](100) NOT NULL,
	[Banner] [varchar](100) NOT NULL,
	[Description] [nvarchar](500) NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
	[NameSearch] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comments](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Content] [nvarchar](255) NOT NULL,
	[Star] [int] NOT NULL,
	[User_Id] [int] NOT NULL,
	[Product_Id] [int] NOT NULL,
	[Date] [date] NOT NULL,
	[Status] [char](1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Contacts]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contacts](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[Date] [date] NOT NULL,
	[Status] [char](1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Code] [varchar](10) NOT NULL,
	[Price] [int] NOT NULL,
	[Quality] [int] NOT NULL,
	[ApplyDay] [date] NOT NULL,
	[Expiration] [date] NOT NULL,
	[MoneyLimit] [int] NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employees]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employees](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Department] [nvarchar](50) NOT NULL,
	[Position] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](10) NOT NULL,
	[StartDay] [date] NOT NULL,
	[Salary] [int] NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
	[User_Id] [int] NOT NULL,
	[Image] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Favorites]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorites](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[User_Id] [int] NOT NULL,
	[Product_Id] [int] NOT NULL,
	[Date] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InformationShop]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InformationShop](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](20) NOT NULL,
	[Active] [bit] NULL,
	[TimeOpen] [nvarchar](50) NOT NULL,
	[Logo] [varchar](100) NOT NULL,
	[Phone] [varchar](15) NOT NULL,
	[Fax] [varchar](15) NOT NULL,
	[Email] [varchar](25) NOT NULL,
	[LogoFooter] [varchar](100) NOT NULL,
	[Address] [nvarchar](100) NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Manufactures]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Manufactures](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Logo] [varchar](100) NOT NULL,
	[Banner] [varchar](100) NOT NULL,
	[Description] [nvarchar](500) NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuOne]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuOne](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[NameSearch] [nvarchar](50) NOT NULL,
	[Cate_Id] [int] NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MenuTwo]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MenuTwo](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[NameSearch] [nvarchar](50) NOT NULL,
	[Menu1_Id] [int] NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](6) NOT NULL,
	[Product_Id] [int] NOT NULL,
	[Address_Id] [int] NOT NULL,
	[Discount_Id] [int] NULL,
	[Quality] [int] NOT NULL,
	[Date] [date] NOT NULL,
	[Method] [char](1) NOT NULL,
	[Status] [char](1) NOT NULL,
	[Comment] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [nvarchar](20) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[Price] [int] NOT NULL,
	[Quality] [int] NOT NULL,
	[Views] [int] NULL,
	[Description] [nvarchar](max) NOT NULL,
	[Specification] [nvarchar](max) NOT NULL,
	[Image1] [nvarchar](100) NOT NULL,
	[Image2] [nvarchar](100) NOT NULL,
	[Image3] [nvarchar](100) NOT NULL,
	[Image4] [nvarchar](100) NOT NULL,
	[Image5] [nvarchar](100) NOT NULL,
	[Active] [bit] NOT NULL,
	[Manu_Id] [int] NOT NULL,
	[Cate_Id] [int] NOT NULL,
	[NameSearch] [varchar](50) NOT NULL,
	[CreateDay] [datetime] NULL,
	[PersonCreate] [int] NOT NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
	[UpdateDay] [datetime] NULL,
	[PersonUpdate] [int] NULL,
	[Sales] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User_Role]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_Role](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[User_Id] [int] NOT NULL,
	[Role_Id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 11/21/2023 2:52:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Password] [varchar](125) NOT NULL,
	[FullName] [nvarchar](125) NOT NULL,
	[Sex] [bit] NULL,
	[Birthday] [date] NULL,
	[Subscribe] [bit] NULL,
	[CreateDay] [datetime] NULL,
	[DeleteDay] [datetime] NULL,
	[PersonDelete] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (1, N'Đặng Thành Trung', N'0889641810', N'Hồ Chí Minh', N'Tân Bình', N'15', N'600 Phạm Văn Bạch, phường 15, quận Tân Bình, TP.Hồ Chí Minh', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (2, N'Nguyễn Đại Lộc', N'0889641811', N'Hồ Chí Minh', N'Gò Vấp', N'14', N'100 Lê Đức Thọ, phường 14, quận Gò Vấp, TP.Hồ Chí Minh', 2)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (3, N'Vũ Nhật Tân', N'0889641812', N'Hồ Chí Minh', N'Quận 3', N'2', N'123 Văn Cao, phường 2, quận 3, TP.Hồ Chí Minh', 3)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (4, N'Nguyễn Trần Nhật Anh', N'0889641813', N'Hồ Chí Minh', N'Thủ Đức', N'Hiệp Bình Phước', N'123 quốc lộ 13, phường Hiệp Bình Phước, quận Thủ Đức, TP.Hồ Chí Minh', 4)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (5, N'Nguyễn Quang Sáng', N'0889641814', N'Hồ Chí Minh', N'Tân Bình', N'P15', N'100 Phạm Văn Bạch, phường 15, quận Tân Bình, TP.Hồ Chí Minh', 5)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (6, N'Phạm Thị Nở', N'0889641815', N'Hồ Chí Minh', N'Tân Bình', N'15', N'Tân Sơn, phường 15, quận Tân Bình, TP.Hồ Chí Minh', 6)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (7, N'Nguyễn Chí Phèo', N'0889641816', N'Hồ Chí Minh', N'Tân Bình', N'15', N'', 7)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (8, N'Nguyễn Chí Phèo', N'0889641816', N'Hồ Chí Minh', N'Tân Bình', N'15', N'', 7)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (9, N'Trung Đặng Thành', N'0889641810', N'hồ chí minh', N'quận gò vấp', N'phường 14', N'549 lê văn thọ', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (11, N'Thành Trung', N'0889641810', N'Hà Nội', N'Thanh Xuân', N'3', N'600 Phạm Văn Đồng', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (12, N'Trung Đặng Thành', N'0889641810', N'Hồ chí Minh', N'Gò Vấp', N'12', N'600 Thống nhất, phường 12, quận Gò Vấp, TP.Hồ Chí Minh', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (13, N'Trung Đặng Thành', N'0889641810', N'Hồ chí Minh', N'Gò Vấp', N'5', N'600 Thống nhất, phường 12, quận Gò Vấp, TP.Hồ Chí Minh', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (14, N'trung', N'0889641810', N'lâm đồng', N'bảo lộc', N'lộc tiến', N'77 Phan chu trinh, phường lộc tiến, bảo lộc, lâm đồng', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (15, N'Trung Đặng Thành', N'0889641810', N'lâm đồng', N'bảo lộc', N'lộc tiến', N'1 Phan chu trinh, phường lộc tiến, bảo lộc, lâm đồng', 1)
INSERT [dbo].[Address] ([Id], [FullName], [Phone], [Province], [District], [Ward], [Detail], [User_Id]) VALUES (16, N'Trung Đặng Thành', N'0889641810', N'lâm đồng', N'bảo lộc', N'lộc tiến', N'1 Phan chu trinh, phường lộc tiến, bảo lộc, lâm đồng', 1)
SET IDENTITY_INSERT [dbo].[Address] OFF
GO
SET IDENTITY_INSERT [dbo].[Blogs] ON 

INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (1, N'Mauro F. Guillén – Người dự báo xu hướng thế giới tương lai', N'<h3>Khái quát sự nghiệp của Mauro F. Guillen</h3>
                                <br>
                                <span>
                                    <b>“Mauro F. Guillen (1964) là nhà xã hội học, nhà kinh tế chính trị, nhà giáo dục quản lý người Tây Ban Nha/ Mỹ.
                                        Mauro F. Guillén là Giáo sư về quản lý Quốc tế giảng dạy Chương trình quản lý nâng cao hàng đầu của Trường Wharton. 
										Bên cạnh đó, ông là Giám đốc Trường kinh doanh Cambridge Judge và thành viên của Queen’s College của Đại học Cambridge..</b>
                                </span>
                                <br>
                                <br>
                                <span>
                                    Ông là một chuyên gia về xu hướng thị trường toàn cầu, là diễn giả và nhà tư vấn được săn đón trên toàn thế giới. 
									Tại Yale, Mauro hoạt động như một nhà xã hội học, kinh tế học. 
									Ông dùng phương pháp định lượng và xác định các cơ hội hứa hẹn cũng như xu hướng dựa trên giao điểm của sự phát triển nhân khẩu học, kinh tế và công nghệ.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Nghiên cứu của Mauro Guillen mang lại nhiều sự khác biệt như làm sáng tỏ xu hướng từ các ví dụ cụ thể trong kinh doanh, chính trị và cuộc sống hàng ngày. 
									Với thuật ngữ dễ hiểu, ông cho thấy người ta có thể dự báo chính xác các xu hướng bằng cách theo dõi trẻ sơ sinh một cách có hệ thống hay tiền tệ trong tương lai…
                                </span>
                                <br>
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2022/06/Mauro-F.-Guillen.jpg" alt="xu-huong-the-gioi-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: Instagram @songnhudoisong</i></span>
                                    </div>
                                </div>
                                <h3>Thành công trong sự nghiệp tác giả</h3>
                                <br>
                                <span>
                                    Các nghiên cứu này đã mang lại cho Mauro nhiều giải thưởng từ Viện nghiên cứu cao cấp Princeton, 
									Học viện Quản Lý, Hiệp hội xã hội học Hoa Kỳ, Hiệp hội Lịch sử Khoa học xã hội…
									Gần đây, Mauro Guillen dành phần lớn thời gian nghiên cứu và giảng dạy của mình cho chủ đề toàn cầu hóa và 
									sự trỗi dậy của các thị trường đa quốc gia mới nổi.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Phương pháp của Guillen được biên tập và đăng trên nhiều tạp chí từ New York Times, 
									Wall Street Journal, The Economist, đến Financial Times. 
									Đồng thời, Mauro cũng trở thành nhân vật thường xuyên xuất hiện trên truyền hình như NPR’s Marketplace, 
									Radio Times, CNBC, Bloomberg TV, CNN, BBC Radio…
                                </span>
                                <br>
                                <br>
                                <span>
                                    Các khóa học trực tuyến của Mauro F. Guillen trên Coursera và edX thu hút hơn 100k 
									người tham gia từ khắp nơi trên thế giới trong đó có rất nhiều nhà điều hành.
                                </span>
								
								<br>
                                <br>
								<h3>Sách của Mauro F. Guillen</h3>
                              
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2022/06/sach-2030-nhung-xu-huong-lon-se-dinh-hinh-the-gioi-tuong-lai.jpg" alt="xu-huong-the-gioi-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: Instagram @iam_neih</i></span>
                                    </div>
                                </div>
                                
                                <br>
                                <span>
                                    “Mauro là tác giả của hàng chục cuốn sách và hơn 30 bài báo học thuật. Cuốn sách 2030: 
									Những Xu Hướng Lớn Sẽ Định Hình Thế Giới Tương Lai của ông được dịch ra nhiều thứ tiếng trên thế giới. 
									Hiện tại, sách đứng trong top 10 sách kinh tế bán chạy nhất của TIKI với bản dịch Việt ngữ.”
                                </span>
                                <br>
                                <br>
                                <span>
                                   Ngoài ra, hai cuốn sách mới nổi gần đây mà ông là đồng tác giả còn có:
								   Global Turning Points: Understanding the Challenges for Business in the Twenty-First Century Và Emerging Markets Rule: 
								   Growth Strategies of the New Global Giants.
                                </span>', N'xu-huong-the-gioi-blog-logo.jpg', CAST(N'2023-10-22' AS Date), N'xu-huong-the-gioi-blog-banner.jpg', 1, CAST(N'2023-09-20T08:53:04.853' AS DateTime), 1, NULL, 0, NULL, 0, N'xu-huong-the-gioi', N'Cuốn sách <b> 2030 Những Xu Hướng Lớn Sẽ Định Hình Thế Giới Tương Lai </b> bày ra cho chúng ta một bức tranh toàn cảnh về sự thay đổi. Thế giới không còn phân chia gọn gàng giữa các nền kinh tế thịnh vượng hay lạc hậu nữa.')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (2, N'Review sách Kinh Doanh Nhỏ, Thu Lợi Lớn', N'<h3>Kinh Doanh Nhỏ, Thu Lợi Lớn </h3>
                                <br>
                                <span>
                                    <b>“– Công thức thành công của những triệu phú thầm lặng. 
									Họ không khoe mẽ, không phô trương, có lẽ cũng ít ai biết đến họ. 
									Nhưng họ vẫn luôn tồn tại giữa chúng ta và là những gã giàu có thực thụ. 
									Họ chính là những người lao động bằng đôi bàn tay trắng nhưng họ có một công cuộc kinh doanh rất thành công. 
									Cùng tìm hiểu triết lý kinh doanh đơn giản mà sâu sắc này qua cuốn sách Kinh Doanh Nhỏ Thu Lợi Lớn bạn nhé!"</b>
                                </span>
                                <br>
                                <br>
                                <span>
                                    Những tỷ phú thầm lặng, âm thầm tồn tại giữa dòng đời như bao con người lao động chân chính khác. 
									Họ không tạo dư luận, không tranh cãi, không gây chú ý, họ là những con người lao động tay chân hoàn hảo. 
									Họ là những ông chủ, bà chủ của những sạp hàng ở chợ đêm, quán ăn đường phố hay những gánh hàng rong.
                                </span>
                                <br>
                                <br>
                                                   
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/10/sach-kinh-doanh-nho-thu-loi-lon.jpg" alt="kinh-doanh-nho-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @mi.reader</i></span>
                                    </div>
                                </div>
                                <h3>Tại sao tôi nên đọc cuốn sách này?</h3>
                                <br>
                                <span>
                                    Nếu như cuốn sách trước, tác giả Rusly Abdullah tập trung chia sẻ những câu chuyện thành công của những triệu phú vỉa hè. 
									Thì ở cuốn sách này, ông đi sâu nghiên cứu và chiêm nghiệm triết lý kinh doanh của bộ phận doanh nhân âm thầm này.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Từ đó, tác giả gửi gắm đến bạn đọc, những người trẻ, những ai đang phấn đấu trên con đường khởi nghiệp những thông điệp ý nghĩa. 
									Khi mới bắt đầu khởi sự, hãy dành toàn bộ tâm huyết của mình ra làm việc. Cũng đừng vội mở mang quá lớn để rồi thất bại đau đớn. 
									Bạn có thể khởi sự rất nhỏ, chỉ với một cửa hàng, nhưng nếu vận hành tốt, doanh thu ổn định. 
									Bạn hoàn toàn có thể trở mình thành một triệu phú lúc nào không hay.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Kinh Doanh Nhỏ, Thu Lợi Lớn – một cuốn sách ý nghĩa dành cho những bạn trẻ đam mê khởi nghiệp. 
									Nó soi đường cho những ai còn đang băn khoăn, vốn ít quá thì kinh doanh cái gì. 
									Vậy thì cứ “kinh doanh nhỏ thôi mà thu lợi lại lớn”.
                                </span>
								
								<br>
                                <br>
								
                              
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/10/kinh-doanh-nho-thu-loi-lon.jpg" alt="kinh-doanh-nho-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @journeywithbooks7</i></span>
                                    </div>
                                </div>
                                <h3>Lời kết</h3>
                                <br>
                                <span>
                                    Kinh Doanh Nhỏ, Thu Lợi Lớn – Không cần quá rình rang, phô trương. Bạn có thể âm thầm để tạo dựng đế chế kinh doanh đồ sộ của riêng mình. 
									Đừng bao giờ xem thường doanh thu khấm khá của những hàng quán vỉa hè tấp nập khách hàng. Bởi bạn không thể biết được họ có phải là một triệu phú, tỷ phú âm thầm hay không. 
									Thứ bạn nên làm là quan sát và học hỏi. Biết đâu, một ngày, bạn vận dụng tốt những triết lý này vào việc kinh doanh của mình và thực sự bước vào hàng ngũ triệu phú, tỷ phú?
                                </span>', N'kinh-doanh-nho-blog-logo.jpg', CAST(N'2023-10-22' AS Date), N'kinh-doanh-nho-blog-banner.jpg', 1, CAST(N'2022-12-11T10:09:13.437' AS DateTime), 1, NULL, 0, NULL, 0, N'kinh-doanh-nho', N'Cuốn sách <b> Kinh Doanh Nhỏ Thu Lợi Lớn </b>-Mọi người luôn tán dương những dự án đầu tư khổng lồ, những thành tích đầy danh tiếng Nhưng ít ai biết được ở những góc phố, những gánh hàng rong cũng tồn tại những tỉ phú thầm lặng.')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (3, N'Review Nghệ Thuật Tinh Tế Của Việc “Đếch” Quan Tâm', N'<h3>Nghệ Thuật Tinh Tế Của Việc “Đếch” Quan Tâm </h3>
                                <br>
                                <span>
                                    <b>Một tản văn kiểu rao giảng, nói về những chủ đề và suy nghĩ mà tác giả muốn truyền tải theo lối nói chuyện trào phúng nhẹ nhàng, 
									không quá gắt gao nhưng nó sẽ “vả thẳng mặt” với những ai “lạc quan tếu” hay “thích nhắm mắt và tưởng tượng”.</b>
                                </span>
                                <br>
                                <br>
                                <span>
                                    Với bạn trẻ độ tuổi 22 – 27, giai đoạn bạn phải đối mặt với hàng tỷ thứ xung quanh và quá nhiều mối bận tâm không đâu, 
									chạy theo những thứ đâu đâu rồi tự tìm khổ sở. 
									Chính bản thân tác giả cũng từng như vậy nên cuốn sách này rất chân thực, có thể dùng được.
                                </span>
                                <br>
                                <br>
                                <h4>Trích dẫn từ sách:</h4>
								<br>
								<span>
								“Bạn học cách kiếm tiền tốt nhất bởi vì bạn cảm thấy bản thân mình chưa làm ra đủ tiền. 
								Bạn đứng trước gương và nhắc đi nhắc lại những câu khẳng định rằng mình xinh đẹp bởi vì bạn cảm thấy bản thân mình chưa đủ xinh đẹp. 
								Bạn làm theo những lời khuyên về hẹn hò và duy trì mối quan hệ bởi vì bạn cảm thấy bản thân mình chưa đủ đáng yêu và được yêu. 
								Bạn cố gắng thực hiện các bài tập tưởng tượng vớ vẩn về việc trở nên thành công hơn bởi vì bạn cảm thấy mình chưa đủ thành công.”
								</span>
								<br>
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/05/nghe-thuat-tinh-te-cua-viec-dech-quan-tam.jpg" alt="dech-quan-tam-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @nhanambooks</i></span>
                                    </div>
                                </div>
                                <h3>Bây giờ cùng xem sách nói gì nào!</h3>
                                <br>
                                <span>
                                    Chấp nhận sự hiện diện của không hoàn thiện: Bởi vì tất cả chúng ta không ai là hoàn thiện cả, 
									nếu bạn ép buộc bản thân phải hoàn thiện thì thật phi lí và bạn nên cải thiện nó một cách dần dần.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Những rắc rối dễ thương: Kiểu như cách bạn phải nâng cục tạ nặng trịch mỗi ngày, đâu chỉ 1 cái, phải 2,3,4,5… n cái, 
									nhưng rồi ngày nọ bạn nhận ra cơ bắp của mình cũng to ra. 
									Vậy đấy, giải quyết những rắc rối là cách để làm bạn trở nên tốt hơn.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Nói “KHÔNG” cũng là nghệ thuật đếch quan tâm: 
									Đừng ngại nói KHÔNG với ai đó vì đơn giản là bạn không muốn mà thôi, 
									không sợ mất lòng khi nói không đúng lúc.
                                </span>
								
								<br>
                                <br>
								
                              
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/05/nghe-thuat-tinh-te-cua-viec-dech-quan-tam-pdf.jpg" alt="dech-quan-tam-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @journeywithbooks7</i></span>
                                    </div>
                                </div>
                                <h3>BẤT NGỜ NHỎ:</h3>
                                <br>
                                <span>
                                    Đọc cuốn sách này rồi, xin lưu ý rằng, bạn hoàn toàn có thể bỏ ngoài tai những lời lẽ xấu xa của kẻ khác nhưng cũng xin đừng biến thành kẻ tự phụ. 
									Kiểu như “À, kệ cha chúng mày, tao đếch thèm quan tâm, lũ ngu dốt, bố mày perfect lắm”.
                                </span>', N'dech-quan-tam-blog-logo.jpg', CAST(N'2023-10-22' AS Date), N'dech-quan-tam-blog-banner.jpg', 0, CAST(N'2022-12-11T10:09:13.437' AS DateTime), 1, NULL, 0, CAST(N'2023-11-15T10:51:48.623' AS DateTime), 1, N'dech-quan-tam', N'Mất một quãng thời gian tìm việc không thành công, Mark mặc kệ đời, bỏ tất cả và đi lòng vòng thế giới để hoàn thành việc viết sách về tư vấn tình cảm.')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (4, N'Review Sách Lối Sống Tối Giản Thời Công Nghệ Số', N'<h3>Sách Lối Sống Tối Giản Thời Công Nghệ Số </h3>
                                <br>
                                <span>
                                    Hầu hết chúng ta đã và đang trở thành con nghiện internet và dành nhiều thời gian lang thang trên mạng xã hội mỗi ngày. 
									Có thể bạn cũng như tôi ý thức được sự kiệt sức của cơ thể khi mãi chạy theo sức hút của những thứ lòe loẹt 
									bóng bẩy liên tục lôi kéo sự chú ý và ảnh hưởng đến tâm trạng chúng ta trên thế giới ảo. 
									Nhưng chúng ta không biết làm sao dừng lại hoặc ứng biến tốt hơn. 
									Cuốn sách <b> Lối Sống Tối Giản Thời Công Nghệ Số </b> có thể hữu ích dành cho bạn trong tình trạng này!
                                </span>
                                <br>
                                <br>
                                
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/06/loi-song-toi-gian-thoi-cong-nghe-so.jpg" alt="loi-song-don-gian-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @nhanambooks</i></span>
                                    </div>
                                </div>
                                <h3>Tóm tắt nội dung Lối Sống Tối Giản Thời Công Nghệ Số</h3>
                                <br>
                                <span>
                                    Lối Sống Tối Giản Thời Công Nghệ Số là cuốn sách gọn gàng chỉ có 2 phần và 7 chương sách nhưng 
									tác giả Cal Newport sẽ khiến bạn phải giật mình khi biết được những ảnh hưởng tiêu cực của hoạt động trực tuyến đối với sức khỏe tâm lý. 
									Nếu lạm dụng đến mức nghiện ngập công nghệ số, 
									chắc chắn sẽ có lúc tâm trạng của bạn trở nên cực đoan với những căng thẳng và kiệt quệ về mặt tinh thần.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Chưa hết, cuốn sách này cũng chỉ cho bạn cách làm thế nào tối giản hóa 
									cuộc sống giữa bão táp công nghệ số và bạn có thể nhận ra vấn đề không phải do công nghệ mà do chính chúng ta – người dùng công nghệ. 
									Có vài nguyên tắc đơn giản để giúp bạn sống tích cực hơn giữa những thiết bị mê hoặc mỗi ngày.
                                </span>
                                <br>
                                <br>
                                
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/06/sach-loi-song-toi-gian-thoi-cong-nghe-so.jpg" alt="loi-song-don-gian-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @journeywithbooks7</i></span>
                                    </div>
                                </div>
                                <h3>Lời kết:</h3>
                                <br>
                                <span>
                                    Nếu sớm kiểm soát được tình trạng lạm dụng công nghệ số ngày càng trở nên nghiêm trọng, 
									thì bạn hãy làm ngay và luôn đi để luôn có một sức khỏe thể chất và tinh thần tốt đẹp nhất, sống đời an yên. 
									Đọc cuốn sách hấp dẫn này để có bí quyết sống giản dị cho riêng mình bạn nhé!
                                </span>', N'loi-song-don-gian-blog-logo.jpg', CAST(N'2023-10-22' AS Date), N'loi-song-don-gian-blog-banner.jpg', 1, CAST(N'2022-12-11T10:09:13.437' AS DateTime), 1, NULL, 0, NULL, 0, N'loi-song-don-gian', N'Cal Newport là Phó giáo sư khoa học máy tính tại Đại học Georgetown đồng thời cũng là tác giả trẻ với 6 cuốn sách được nhiều độc giả yêu thích.')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (5, N'Review sách Tôi Là Zlatan Ibrahimovic', N'<h3>Tóm tắt nội dung sách Tôi là Zlatan Ibrahimovic</h3>
                                <br>
                                <span>
                                    Tôi là Zlatan Ibrahimovic là cuốn hồi ký bùng nổ được giới phê bình đánh giá cao của cầu thủ ngôi sao đội tuyển Inter Milan. 
									Một trong những tài năng bóng đá gây tranh cãi nhất thế giới theo kiểu 
									“Tại sao phải là Fiat khi bạn có thể là Ferrari?”
                                </span>
                                <br>
                                <br>
                                <span>
                                    Nội dung sách kể về hành trình từ nghèo khó của một người nhập cư Thụy Điển vươn đến đỉnh cao là một thiên tài bóng đá. 
									Những câu chuyện cảm động nhưng không hề bi đát, giọng văn khá hài hước là điểm nhấn khác biệt cho cuốn tự truyện này.
                                </span>
                                <br>
                                <br>
                                <h4>Trích dẫn từ sách:</h4>
								<br>
								<span>
								Tôi là Zlatan Ibrahimovic tất nhiên không thể thiếu những tên tuổi như Guardiola, Messi, 
								Jose Mourinho…cũng như Milan, Barcelona, PSG và Manchester United. Đọc cuốn hồi ký này, 
								bạn sẽ được du ngoạn đến một nơi gọi là 
								“Hành tinh Zlatan” – nơi mà không bao giờ có một khoảnh khắc buồn tẻ nào.
								</span>
								<br>
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2022/05/toi-la-ibrahimovic.jpg" alt="zlatan-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @ibra</i></span>
                                    </div>
                                </div>
                                <h3>Cuộc đời Ibrahimovic qua nội dung sách</h3>
                                <br>
                                <span>
                                    Tôi là Zlatan Ibrahimovic là cuốn sách bán chạy ngay vài giờ sau khi xuất bản, phát hành rộng rãi ở 15 quốc gia. 
									Với sự tham gia chấp bút bởi nhà văn David Lagercrantz, cuốn sách mang hơi hướng văn học, 
									được đánh giá là cuốn tự truyện sống động nhất viết bởi một cầu thủ bóng đá. 
									Điểm qua một vài nội dung trong sách:
                                </span>
                                <br>
                                <br>
                                <ul>
                                    <li>Phần 1: Đứa trẻ hư trong ngôi trường Barca</li>
									<li>Phần 2: Trò “dìm hàng” của Messi</li>
									<li>Phần 3: “Cơn điên bùng phát”</li>
									<li>Phần 4: Đứa trẻ ngỗ ngược</li>
									<li>Phần 5: Tên trộm Zlatan</li>
									<li>Phần 6: Tuổi thơ dữ dội</li>
									<li>Phần 7: Xung đột với bố</li>
									<li>…</li>
									<li>Phần 15: Cơ duyên với Ajax Amsterdam</li>
									<li>Phần 16: Kết bạn với Van Basten & chạm trán với Van Gaal</li>
									<li>Phần 17: Kết duyên với Mafia</li>
									<li>...</li>
									<li>Phần 76: Thủ lĩnh Ibra</li>
									<li>Phần 77: Tiếng Vaffanculo của Ibra</li>
									<li>Phần 78: Lời cầu nguyện và Scudetto sau 7 năm</li>
									<li>Phần 79: Những ký ức</li>
                                </ul>
                                <br>
                                <br>
                                
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2022/05/sach-ve-ibrahimovic.jpg" alt="zlatan-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @journeywithbooks7</i></span>
                                    </div>
                                </div>
                                <h3>BẤT NGỜ NHỎ:</h3>
                                <br>
                                <span>
                                    Tôi Là Zlatan Ibrahimovic là cuốn tự truyện hấp dẫn lột tả chân thực đời sống phía sau của một ngôi sao bóng đá sáng giá. 
									Một câu chuyện truyền cảm hứng mạnh mẽ về 
									đam mê và nhiệt huyết không chỉ cho sân cỏ mà còn nhiều lĩnh vực khác trong cuộc sống.
                                </span>', N'zlatan-blog-logo.jpg', CAST(N'2023-10-22' AS Date), N'zlatan-blog-banner.jpg', 1, CAST(N'2023-09-20T08:53:04.853' AS DateTime), 1, NULL, 0, NULL, 0, N'zlatan', N'Zlatan Ibrahimovic (3/10/1981) là một trong những tiền đạo xuât sắc nhất của bóng đá Tên tuổi anh được làm nên qua hàng loạt công trạng tại các câu lạc bộ lớn với lối đá hiệu quả và kỹ năng dứt điểm thượng thừa.')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (6, N'Kevin Mitnick – Tội Phạm hay Người hùng công nghệ1?', N'<h3>Kevin Mitnick (6/8/1963)</h3>
                                <br>
                                <span>
                                    <b>là hacker nổi tiếng nhất thế kỷ 20. Tác giả của cuốn sách Nghệ Thuật Ẩn Mình là người có hành trình trải nghiệm rất thú vị với tài năng xuất chúng của mình.  
									Mời bạn đọc cùng lật lại lịch sử phạm tội ly kỳ của Mitnick trước khi anh hoàn lương qua bài viết sau.</b>
                                </span>
								<br>
                                <br>
                                <h4>Tuổi thơ và đam mê lạc lối của Mitnick</h4>
                                <br>
                               
                                <span>
                                    Kevin sinh ra trong gia đình công nhân tại thung lũng San Fernando, California, Mỹ. Từ nhỏ anh đã có niềm đam mê với tin học, 
									có lẽ một phần vì quãng thời gian tuổi thơ không êm đẹp nên đam mê ấy đã rẽ lối dẫn anh đến con đường trở thành một hacker.
                                </span>
                                <br>
								<br>
								<span>
								Không phải điều để tự hào nhưng thật là không dễ để trở thành một tên tội phạm bị truy nã tầm cỡ. 
								Bắt đầu từ việc qua mặt hệ thống bán vé xe bus, gọi điện thoại, ăn trộm sách…để sử dụng miễn phí, 
								Mitnick đã nhận án phạt treo từ tuổi chưa thành niên.
								</span>
								<br>
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2022/05/truy-na-kevin-mitnick.jpg" alt="kevin-mitnick-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @kevinmitnick</i></span>
                                    </div>
                                </div>
                                <h3>Kevin Mitnick Hacking</h3>
                                <br>
                                <span>
                                    Cảm giác phạm tội này có thể thấy trong Kevin Mitnick có phần ám ảnh rối loạn tâm thần, 
									bị kích thích bởi chứng nghiện chinh phục máy tính. Anh trở thành tội phạm máy tính (hacker) 
									bị truy nã bởi FBI những năm 1992. Mặc dù vậy, trong thời gian này, 
									Mitnick vẫn tiếp tục hack hệ thống máy tính của hàng loạt công ty lớn và các trường đại học. 
									Với khả năng thay hình đổi dạng bởi anh có thể ăn cắp thông tin cá nhân của bất cứ ai để làm giấy tờ 
									tùy thân giả cho mình.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Ngày 15/2/1995, tại North Carolina, hacker khét tiếng Kevin Mitnick chính thức bị bắt. 
									Với hàng loạt tội danh gây thiệt hại, anh lãnh án 6 năm và được trả tự do vào ngày 21/1/2000.
                                </span>
                               
                                <br>                          
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2022/05/nghe-thuat-an-minh.jpg" alt="kevin-mitnick-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @kevin</i></span>
                                    </div>
                                </div>
                                <h3>Sách của Kevin Mitnick</h3>
                                <br>
                                <span>
                                    Cuốn sách The Art Of Invisibility (Nghệ Thuật Ẩn Mình) là một trong những cống hiến mới xuất bản năm 2017 của Mitnick dành cho độc giả. 
									Xuất phát từ tài nghệ đã từng đột nhập máy tính cá nhân để lấy thông tin của vô số người làm giấy tờ tùy thân giả cho mình. 
									Mitnick chỉ ra biện pháp để bất cứ một ai có thể tự bảo vệ, che giấu dữ liệu cá nhân.
                                </span>', N'kevin-mitnick-blog-logo.jpg', CAST(N'2023-11-14' AS Date), N'kevin-mitnick-blog-banner.jpg', 1, CAST(N'2023-09-20T08:53:04.853' AS DateTime), 1, NULL, 0, CAST(N'2023-11-15T12:02:41.033' AS DateTime), 1, N'nghe-thuat-an-minh', N'Hy vọng bài viết đã phần nào cung cấp được thông tin cho độc giả về câu hỏi Kevin Mitnick là ai?truyền cảm hứng cũng như nhắc nhở chúng ta về việc tự bảo vệ mình trước công nghệ với vô số hacker trong thời đại mới.')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (7, N'Review sách 100 việc nên làm trước tuổi 20', N'<h3>100 việc nên làm trước tuổi 20  </h3>
                                <br>
                                <span>
                                    <b>là cuốn sách gối đầu giường nên có của bất cứ cô gái trẻ nào đương độ xuân thì bởi nó nói về kế hoạch tương lai, 
									những dự định và những thứ sẽ giúp bạn trở thành một người phụ nữ trưởng thành, xinh đẹp, được nhiều người ngưỡng mộ chỉ 
									trong vòng mười năm tiếp đến. Bạn đã sẵn sàng tiếp cận những bí quyết được sẻ chia bởi tác giả 2.1/2 
									Bạn Tốt trong cuốn sách này?</b>
                                </span>
                                <br>
                                <br>
                                
                                
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/07/100-viec-nen-lam-truoc-tuoi-20.jpg" alt="tuoi20-blog-image1" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @nhanambooks</i></span>
                                    </div>
                                </div>
                                <h3>Nội dung chính</h3>
                                <br>
                                <span>
                                    Tuổi 20 là bước đệm quan trọng, chuyển tiếp từ giai đoạn niên thiếu dại khờ thành người lớn chín chắn trưởng thành, 
									đây cũng là cột mốc quan trọng để mở ra con đường tương lai dành cho mỗi cô gái nhỏ.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Thật may mắn nếu bạn đang đọc cuốn sách này còn đang ở độ tuổi mười tám đôi mươi, bạn sẽ dễ dàng chạm tay đến 
									thành công dễ hơn và sớm hơn so với bạn bè đồng trang lứa khi tóm gọn những bí quyết được sẻ chia trong cuốn sách này.
                                </span>
                                <br>
                                <br>
                                <span>
                                    Cũng giống như mua bảo hiểm hay tham gia gửi tiết kiệm ở ngân hàng, khi bước vào cuộc chơi càng sớm 
									bạn sẽ tích góp được nhiều và sớm hơn người khác, cơ hội trở mình và gia tăng thu nhập cũng cao hơn rất nhiều. Với cuộc sống tương lai, 
									ngay từ năm 20 hãy hoạch định rõ ràng và chạy đúng con đường, cái đích thành công sẽ không cách bạn bao xa nữa.
                                </span>
								
								<br>
                                <br>
								
                              
                                <br>
                                <div class="image">
                                    <div class="thumbnail" style="border: none; text-align: center;">
                                        <img src="https://eccthai.com/wp-content/uploads/2021/07/co-gai-tuoi-20.png" alt="tuoi20-blog-image2" border="0">
                                        <span style="color: #777777;"><i>Ảnh: @journeywithbooks7</i></span>
                                    </div>
                                </div>
                                <h3>Lời kết</h3>
                                <br>
                                <span>
                                    Dù đã hay đang bước qua tuổi 20, cuốn sách này cũng sẽ là liều thuốc hạnh phúc dành cho mọi cô gái, 
									là bạn đồng hành giúp các nàng sớm tìm được phiên bản hạnh phúc nhất của chính mình trong mọi hoàn cảnh.
                                </span>', N'tuoi20-blog-logo.png', CAST(N'2023-09-21' AS Date), N'tuoi20-blog-banner.png', 0, CAST(N'2023-09-21T08:53:04.853' AS DateTime), 1, NULL, 0, NULL, 0, N'dech-quan-tam', N'Bạn Tốt là tác giả của những cuốn sách như Xóa bỏ định kiến, Tôi có trí nhớ kém, Nói nhiều không bằng nói đúng, Không phải thiếu may mắn, Chỉ là chưa cố gắng; 99 điều đúc rút từ trí tuệ nhân loại người trẻ cần biết; 100 việc nên làm trước tuổi 20;…')
INSERT [dbo].[Blogs] ([Id], [Title], [Content], [Logo], [UploadDay], [Banner], [Active], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch], [Description]) VALUES (8, N'hahaha', N'tôi là trungnefajglkd;aaaaaa', N'tuoi20-blog-banner.png', CAST(N'2023-11-15' AS Date), N'tuoi20-blog-logo.png', 1, CAST(N'2023-11-15T12:06:39.563' AS DateTime), 1, CAST(N'2023-11-15T13:03:03.600' AS DateTime), 1, CAST(N'2023-11-15T12:17:32.633' AS DateTime), 1, N'hahaha', N'tooi laf trung en')
SET IDENTITY_INSERT [dbo].[Blogs] OFF
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (1, N'Pháp Lý', N'9-20x20.png', N'banner-3.jpg', N'Nơi tự hào là địa chỉ uy tín và chất lượng hàng đầu dành cho những ai đam mê tìm hiểu tra cứu các văn bản  luật, pháp lý và mong muốn tìm kiếm những cuốn sách về lĩnh vực luật - văn bản luật cùng các tác giả nổi tiếng trong lĩnh vực pháp lý.', CAST(N'2021-09-20T19:09:20.993' AS DateTime), 1, NULL, 0, CAST(N'2021-11-12T14:59:21.427' AS DateTime), 1, N'phap-ly')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (2, N'Kinh Tế', N'5-20x20.png', N'az-viet-nam-banner.jpg', N'Nền kinh tế trên toàn thế giới đang bị tác động mạnh mẽ bởi cuộc cách mạng Công nghiệp 4.0. Nhiều khái niệm, định luật, lĩnh vực không còn phù hợp đã bị đào thải nhanh chóng để bắt kịp với sự vận động liên tục của nhân loại.', CAST(N'2022-09-20T19:09:20.993' AS DateTime), 1, NULL, 0, CAST(N'2022-11-12T14:59:21.427' AS DateTime), 1, N'kinh-te')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (3, N'Văn Hóa Xã Hội', N'2-20x20.png', N'banner-3.jpg', N'Khả năng sáng tạo của con người là vô hạn, chúng ta hoàn toàn có thể tạo nên những trải nghiệm cho chính mình bằng sức mạnh niềm tin, của suy nghĩ và cảm xúc. Chúng ta có thể lựa chọn niềm tin và từ đó làm thay đổi thực tế cuộc sống của mình, tạo nên tất cả những gì mà mình hằng mơ ước.', CAST(N'2022-09-21T20:18:06.043' AS DateTime), 1, NULL, 0, CAST(N'2022-10-12T19:56:49.137' AS DateTime), 1, N'van-hoa-xa-hoi')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (4, N'Văn Học Nghệ Thuật', N'6-20x20.png', N'banner-3.jpg', N'Văn học nghệ thuật (còn gọi là văn học tinh hoa) là dòng văn học cao cấp, do tầng lớp tri thức của xã hội sáng tạo nên với nhu cầu được khám phá nội tâm và đời sống tinh thần của con người, giúp người đọc hiểu được những phức cảm tâm lý sâu sắc của con người, như khát vọng được sống, được tự do, hoặc những mặc cảm tội...', CAST(N'2023-09-21T20:19:40.737' AS DateTime), 1, NULL, 0, CAST(N'2023-10-12T19:57:36.573' AS DateTime), 1, N'van-hoc-nghe-thuat')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (5, N'Giáo Trình', N'11-20x20.png', N'banner-3.jpg', N'Giáo trình là hệ thống chương trình giảng dạy của một môn học. Nó là tài liệu học tập hoặc giảng dạy được thiết kế và biên soạn dựa trên cơ sở chương trình môn học. Mục đích để làm tài liệu giảng dạy chính thức cho giáo viên, hoặc / và làm tài liệu học tập chính thức cho học sinh, sinh viên.', CAST(N'2023-07-21T20:22:23.947' AS DateTime), 1, NULL, 0, CAST(N'2023-10-12T19:57:48.607' AS DateTime), 1, N'giao-trinh')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (6, N'Tiểu Thuyết', N'4-20x20.png', N'alpha-book-banner.jpg', N'Tiểu thuyết là một thể loại văn xuôi có hư cấu, thông qua nhân vật, hoàn cảnh, sự việc để phản ánh bức tranh xã hội rộng lớn và những vấn đề của cuộc sống con người, biểu hiện tính chất tường thuật, tính chất kể chuyện bằng ngôn ngữ văn xuôi theo những chủ đề xác định.', CAST(N'2023-08-21T20:24:22.287' AS DateTime), 1, NULL, 0, CAST(N'2023-10-20T22:27:04.633' AS DateTime), 1, N'light-novel')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (7, N'Tâm Linh', N'10-20x20.png', N'banner-3.jpg', N'Sách Tâm Linh là một thể loại sách có liên quan đến tâm lý, tình cảm và hành động của con người. Ngoài ra tìm đọc một cuốn sách tâm linh hay còn giúp cho chúng ta hiểu thêm về bản thân mình và các mối quan hệ xã hội xung quanh. Từ đây giúp chúng ta giải quyết được các vấn đề trong cuộc sống. ', CAST(N'2022-08-22T06:02:54.557' AS DateTime), 1, NULL, 0, CAST(N'2023-09-22T10:01:44.787' AS DateTime), 1, N'tam-linh')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (8, N'Thiếu Nhi', N'10-20x20.png', N'nxb-kim-dong-banner.jpg', N'Một nhà văn viết về văn học thiếu nhi định nghĩa nó là "tất cả các cuốn sách viết cho trẻ em, ngoại trừ các tác phẩm như truyện tranh, truyện cười, sách hoạt hình và các tác phẩm phi hư cấu không được đọc từ trước ra sau, như từ điển, bách khoa toàn thư và các tài liệu tham khảo khác."', CAST(N'2023-08-22T06:03:43.933' AS DateTime), 1, NULL, 0, CAST(N'2023-10-29T22:27:50.997' AS DateTime), 1, N'thieu-nhi')
INSERT [dbo].[Categories] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [NameSearch]) VALUES (9, N'haha1', N'Alarm.png', N'Add.png', N'đây là danh mục test', CAST(N'2023-11-15T13:26:10.633' AS DateTime), 1, CAST(N'2023-11-15T13:57:03.477' AS DateTime), 1, CAST(N'2023-11-15T13:37:22.260' AS DateTime), 1, N'haha')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Comments] ON 

INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (1, N'Sách hay lắm nè', 5, 1, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (2, N'Chất lượng sản phẩm tuyển vời', 5, 2, 15, CAST(N'2023-09-25' AS Date), N'0')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (3, N'Đóng gói sản phẩm chắc chắn', 5, 3, 17, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (4, N'Hàng đẹp giá hợp lý', 5, 4, 2, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (5, N'Mới nhận hàng chưa đọc', 5, 5, 6, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (6, N'Bìa hơi chóc', 4, 6, 9, CAST(N'2023-09-25' AS Date), N'0')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (7, N'Lần sau sẽ ủng hộ', 5, 7, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (8, N'Sách hay lắm nè', 5, 2, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (9, N'Sách hay lắm nè', 4, 3, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (10, N'Sách hay lắm nè', 4, 4, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (11, N'Sách hay lắm nè', 4, 5, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (12, N'Sách hay lắm nè', 4, 6, 8, CAST(N'2023-09-25' AS Date), N'1')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (15, N'haha', 5, 1, 3, CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (1015, N'haha', 4, 1, 2, CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (1016, N'hihi', 3, 1, 3, CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (1017, N'hề hề', 4, 1, 8, CAST(N'2023-11-11' AS Date), N'0')
INSERT [dbo].[Comments] ([Id], [Content], [Star], [User_Id], [Product_Id], [Date], [Status]) VALUES (1018, N'hề hề', 3, 1, 1, CAST(N'2023-11-13' AS Date), N'1')
SET IDENTITY_INSERT [dbo].[Comments] OFF
GO
SET IDENTITY_INSERT [dbo].[Contacts] ON 

INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (1, N'Quang Sáng', N'sangne@gmail.com', N'web bán sách ok nè', CAST(N'2023-09-22' AS Date), N'1')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (2, N'Đại Lộc', N'locngao@gmail.com', N'được của nó', CAST(N'2023-09-22' AS Date), N'1')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (3, N'Nhật Tân', N'tanlo@gmail.com', N'tam ổn', CAST(N'2023-09-22' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (4, N'Nguyễn Văn Tèo', N'teonv@gmail.com', N'cũng bình thường', CAST(N'2023-09-22' AS Date), N'1')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (5, N'Phạm Thị Nở', N'nopt@gmail.com', N'kha lam abc', CAST(N'2023-09-22' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (6, N'Nhật Anh', N'anhngu@gmail.com', N'ban cai gì đây', CAST(N'2023-09-22' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (7, N'trung', N'trungdtps23794@fpt.edu.vn', N'ádfsad', CAST(N'2023-11-01' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (8, N'tôi tên là', N'thanhtrung@gmail.com', N'haha', CAST(N'2023-11-01' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (9, N'àdgadg', N'thanhtrung@gmail.com', N'adsfg', CAST(N'2023-11-01' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (10, N'àdgfgfgdh', N'thanhtrung@gmail.com', N'àdgads', CAST(N'2023-11-01' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (11, N'trung', N'trungdtps23794@fpt.edu.vn', N'ádgasdgas', CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (12, N'tôi tên là', N'thanhtrung@gmail.com', N'ádgasd', CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (13, N'tôi tên là', N'theedmbestof@gmail.com', N'àdga', CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (1007, N'trung', N'trungdtps23794@fpt.edu.vn', N'ádfashgf', CAST(N'2023-11-02' AS Date), N'0')
INSERT [dbo].[Contacts] ([Id], [Name], [Email], [Content], [Date], [Status]) VALUES (1008, N'trung', N'hihi@gmail.com', N'hahaha', CAST(N'2023-11-18' AS Date), N'0')
SET IDENTITY_INSERT [dbo].[Contacts] OFF
GO
SET IDENTITY_INSERT [dbo].[Discount] ON 

INSERT [dbo].[Discount] ([Id], [Name], [Code], [Price], [Quality], [ApplyDay], [Expiration], [MoneyLimit], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (1, N'Giảm giá 20.000đ cho đơn hàng trên 100.000đ', N'TDTNTG', 20000, 5, CAST(N'2023-09-23' AS Date), CAST(N'2023-10-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, CAST(N'2023-09-22T21:41:24.543' AS DateTime), 1)
INSERT [dbo].[Discount] ([Id], [Name], [Code], [Price], [Quality], [ApplyDay], [Expiration], [MoneyLimit], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (2, N'Giảm giá 30.000đ cho đơn hàng trên 150.000đ', N'NNDB', 30000, 3, CAST(N'2023-09-23' AS Date), CAST(N'2023-10-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, CAST(N'2023-09-22T21:41:24.543' AS DateTime), 1)
INSERT [dbo].[Discount] ([Id], [Name], [Code], [Price], [Quality], [ApplyDay], [Expiration], [MoneyLimit], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (3, N'Giảm 50k cho đơn hàng từ 0đ', N'DCMNVCL', 50000, 4, CAST(N'2023-09-23' AS Date), CAST(N'2023-10-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[Discount] ([Id], [Name], [Code], [Price], [Quality], [ApplyDay], [Expiration], [MoneyLimit], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (4, N'Giảm giá 100.000đ cho đơn hàng trên 300.000đ', N'HAHAHA', 100000, 5, CAST(N'2023-10-23' AS Date), CAST(N'2023-11-23' AS Date), 100000, CAST(N'2023-09-21T19:25:20.550' AS DateTime), 1, NULL, 0, NULL, 1)
INSERT [dbo].[Discount] ([Id], [Name], [Code], [Price], [Quality], [ApplyDay], [Expiration], [MoneyLimit], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (5, N'Giảm giá test', N'CTKTVN', 20000, 3, CAST(N'2023-11-15' AS Date), CAST(N'2023-12-13' AS Date), 1000, CAST(N'2023-11-15T15:49:15.540' AS DateTime), 1, CAST(N'2023-11-15T16:24:39.587' AS DateTime), 1, CAST(N'2023-11-15T16:08:06.187' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Discount] OFF
GO
SET IDENTITY_INSERT [dbo].[Employees] ON 

INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (1, N'Developer', N'Project Manager', N'0889641810', CAST(N'2023-09-15' AS Date), 70000000, CAST(N'2023-09-20T19:13:14.837' AS DateTime), 0, NULL, 0, NULL, 0, 1, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (2, N'Developer', N'FontEnd', N'0312456789', CAST(N'2023-09-15' AS Date), 10000000, CAST(N'2023-09-20T19:13:14.837' AS DateTime), 1, NULL, 0, CAST(N'2023-11-15T18:18:13.543' AS DateTime), 1, 2, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (3, N'Developer', N'Backend', N'0987654321', CAST(N'2023-09-17' AS Date), 8500000, CAST(N'2023-09-24T19:13:14.837' AS DateTime), 1, NULL, 0, CAST(N'2023-11-15T18:18:59.207' AS DateTime), 1, 3, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (4, N'Tester', N'Automation', N'0312456789', CAST(N'2023-09-18' AS Date), 9000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 1, CAST(N'2023-11-20T18:56:05.720' AS DateTime), 1, CAST(N'2023-11-15T18:17:43.993' AS DateTime), 1, 4, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (5, N'Developer', N'JavaDev', N'0789654123', CAST(N'2023-09-18' AS Date), 12000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 1, NULL, 0, CAST(N'2023-11-15T18:18:39.487' AS DateTime), 1, 5, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (6, N'Developer', N'WebApp', N'0399999999', CAST(N'2023-09-18' AS Date), 15000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 1, CAST(N'2023-11-16T22:49:41.193' AS DateTime), 5, CAST(N'2023-11-15T18:18:26.207' AS DateTime), 1, 6, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (7, N'Developer', N'C# Intern', N'0369258147', CAST(N'2023-09-18' AS Date), 3000000, CAST(N'2023-09-21T19:13:14.837' AS DateTime), 2, NULL, 0, NULL, 0, 7, NULL)
INSERT [dbo].[Employees] ([Id], [Department], [Position], [Phone], [StartDay], [Salary], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [User_Id], [Image]) VALUES (8, N'phòng test', N'test luôn', N'0889641810', CAST(N'2023-11-14' AS Date), 2000000, CAST(N'2023-11-15T17:35:07.577' AS DateTime), 1, CAST(N'2023-11-15T18:17:24.653' AS DateTime), 1, NULL, 0, 1008, NULL)
SET IDENTITY_INSERT [dbo].[Employees] OFF
GO
SET IDENTITY_INSERT [dbo].[Favorites] ON 

INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (2, 2, 5, CAST(N'2023-09-24' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (3, 3, 7, CAST(N'2023-09-24' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (4, 4, 12, CAST(N'2023-09-24' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (5, 5, 13, CAST(N'2023-09-24' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (6, 6, 19, CAST(N'2023-09-24' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (7, 7, 21, CAST(N'2023-09-24' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (56, 1, 5, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (57, 1, 17, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (58, 1, 2, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (59, 1, 15, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (60, 1, 14, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (61, 3, 1, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (62, 2, 1, CAST(N'2023-11-14' AS Date))
INSERT [dbo].[Favorites] ([Id], [User_Id], [Product_Id], [Date]) VALUES (65, 1, 1, CAST(N'2023-11-19' AS Date))
SET IDENTITY_INSERT [dbo].[Favorites] OFF
GO
SET IDENTITY_INSERT [dbo].[InformationShop] ON 

INSERT [dbo].[InformationShop] ([Id], [Name], [Active], [TimeOpen], [Logo], [Phone], [Fax], [Email], [LogoFooter], [Address], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (1, N'BWR Shop', 1, N'7:00 AM đến 10:00 PM', N'logo.jpg', N'0889641810', N'0987654321', N'cskh@hotro.bwr.vn', N'logo.jpg', N'Công Viên Phần Mềm Quang Trung', CAST(N'2023-09-21T15:58:27.223' AS DateTime), 1, NULL, 0, CAST(N'2023-11-15T18:30:55.390' AS DateTime), 1)
INSERT [dbo].[InformationShop] ([Id], [Name], [Active], [TimeOpen], [Logo], [Phone], [Fax], [Email], [LogoFooter], [Address], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (2, N'BookHaha', 0, N'11:00 AM - 11:00 PM', N'Add.png', N'0889641810', N'0912345678', N'bookhaha@gmail.com', N'Alarm.png', N'Phạm Văn Bạch Tân bình', CAST(N'2023-11-15T18:47:24.357' AS DateTime), 1, CAST(N'2023-11-15T19:51:28.880' AS DateTime), 1, NULL, 0)
INSERT [dbo].[InformationShop] ([Id], [Name], [Active], [TimeOpen], [Logo], [Phone], [Fax], [Email], [LogoFooter], [Address], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (3, N'ShopHahaha', 0, N'11:00AM đến 11:00 PM', N'Alarm.png', N'0889641810', N'0987654321', N'shop@gmail.com', N'Game controller.png', N'Phạm Văn Bạchfasdfds', CAST(N'2023-11-15T20:02:23.590' AS DateTime), 1, CAST(N'2023-11-15T20:04:07.140' AS DateTime), 1, NULL, 0)
INSERT [dbo].[InformationShop] ([Id], [Name], [Active], [TimeOpen], [Logo], [Phone], [Fax], [Email], [LogoFooter], [Address], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (4, N'hahahaha', 0, N'Phạm Văn Bạchagdfgfdagf', N'Alarm.png', N'0987654321', N'0987654321', N'haha@gmail.com', N'Add.png', N'Phạm Văn Bạchagdfgfdagfdgad', CAST(N'2023-11-15T20:08:17.957' AS DateTime), 1, CAST(N'2023-11-15T20:08:32.253' AS DateTime), 1, NULL, 0)
INSERT [dbo].[InformationShop] ([Id], [Name], [Active], [TimeOpen], [Logo], [Phone], [Fax], [Email], [LogoFooter], [Address], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (5, N'HahahaShop', 0, N'1111111111111111', N'Add.png', N'0889641810', N'0987654321', N'hahashop@gmail.com', N'Game controller.png', N'Phạm Văn Bạchbbbbbbb', CAST(N'2023-11-15T20:23:05.870' AS DateTime), 1, NULL, 0, NULL, 0)
SET IDENTITY_INSERT [dbo].[InformationShop] OFF
GO
SET IDENTITY_INSERT [dbo].[Manufactures] ON 

INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (1, N'NXB Kim Đồng', N'nxb-kim-dong.png', N'nxb-kim-dong-banner.jpg', N'Nhà xuất bản Kim Đồng trực thuộc Trung ương Đoàn TNCS Hồ Chí Minh là Nhà xuất bản tổng hợp có chức năng xuất bản sách và văn hóa phẩm phục vụ thiếu nhi và các bậc phụ huynh trong cả nước, quảng bá và giới thiệu văn hóa Việt Nam ra thế giới. Nhà xuất bản có nhiệm vụ tổ chức bản thảo, biên soạn, biên dịch, xuất bản và phát hành các xuất bản phẩm có nội dung: giáo dục truyền thống dân tộc, giáo dục về tri thức, kiến thức… trên các lĩnh vực văn học, nghệ thuật, khoa học', CAST(N'2023-09-21T20:08:21.840' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (2, N'Thái Hà', N'thai-ha.jpg', N'thai-ha-banner.jpg', N'Thái Hà Books mong muốn mang tri thức trong những cuốn sách ở khắp nơi trên thế giới đến với độc giả Việt Nam. Những cuốn sách có giá trị, có tính ứng dụng và thực tiễn cao của Thái Hà Books sẽ làm cho cuộc sống tươi đẹp hơn, phong phú hơn. Trong tương lai, với xu thế hội nhập toàn cầu, Thái Hà Books mong muốn trở thành con chim đầu đàn đi ra biển lớn.', CAST(N'2023-09-21T20:10:50.670' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (3, N'FIRST NEWS', N'first-new.jpg', N'first-new-banner.png', N'Được thành lập vào ngày 10/12/1994 với tên gọi ban đầu là “Ban Biên Dịch First News”, trải qua 25 năm luôn nỗ lực và sáng tạo, đến nay First News – Trí Việt đã khẳng định được vị thế cũng như uy tín của mình trong lòng bạn đọc, không những bởi sự phong phú của những dòng sách giá trị trên thế giới mà First News đã nhạy bén chuyển ngữ phục vụ người Việt Nam, mà còn vì chất lượng cũng như sự chỉn chu trong từng quyển sách mà First News mong muốn mang đến cho bạn đọc.', CAST(N'2023-09-22T05:32:29.543' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (4, N'AZ Việt Nam', N'az-viet-nam.jpg', N'az-viet-nam-banner.jpg', N'Khởi nguồn của AZ Việt Nam ngày nay là Công ty TNHH Trung tâm Sách và Thiết bị Thư viện, với hoạt động đầu tiên là cung cấp sách, trang thiết bị, văn hóa phẩm cho các thư viện và trung tâm sách trên cả nước. Từ cuối năm 2010 đến nay, chúng tôi đã không ngừng nỗ lực khai phá và phát triển các dòng sản phẩm mới với 8 thương hiệu uy tín.', CAST(N'2023-09-22T05:35:32.713' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (5, N'IPM', N'ipm.jpg', N'ipm-sach-banner.jpg', N'Công ty Cổ phần Xuất bản và Truyền thông IPM được thành lập tháng 02 năm 2004 bới một nhóm sáng lập viên trẻ tuổi, nhiệt tình và năng động và có nhiều năm kinh nghiệm hoạt động trong lĩnh vực xuất bản Việt Nam. Ngay sau khi Việt Nam chính thức tham gia công ước Berne (Công ước quốc tế về bảo hộ các tác phẩm văn học nghệ thuật) vào ngày 24/10/2004, IPM là một trong số ít các công ty Việt Nam đi tiên phong trong việc tìm kiếm, khai thác và mua bản quyền sách nước ngoài.', CAST(N'2023-09-22T05:36:28.887' AS DateTime), 2, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (6, N'Amak Books', N'amak-book.png', N'amak-book-banner.png', N'Amak Books, hiện đã trở thành một nhãn sách độc lập thuộc về công ty X.Y.Z. Chúng tôi tự hào là một trong những người tiên phong thương hiệu trong lĩnh vực chính sách bản quyền tại Việt Nam. Qua nhiều năm, với những nỗ lực và sự đóng góp của đội ngũ nhân viên của chúng tôi, sự năng động và nhiệt tình của các cộng tác viên, AMAK BOOKS đã trở thành một thương hiệu sách đáng tin cậy với danh tiếng tốt cho tất cả các đối tác bên trong và ngoài nước.', CAST(N'2023-09-22T05:59:01.767' AS DateTime), 4, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (7, N'NXB Trẻ', N'nxb-tre.jpg', N'nxb-tre-banner.jpg', N'Sau gần năm năm hoạt động với nhiều nỗ lực và hiệu quả, ngày 3-2-1986, NXB Măng Non được UBND TP.HCM ký quyết định đổi tên thành NXB Trẻ với chức năng và nhiệm vụ được mở rộng là đơn vị xuất bản dành cho thanh thiếu nhi thành phố và khu vực Nam bộ.', CAST(N'2023-09-22T05:59:54.977' AS DateTime), 3, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (8, N'Alpha Books', N'alpha-book.png', N'alpha-book-banner.jpg', N'Công ty cổ phần Sách Alpha (Alpha Books Joint-Stock Company – gọi tắt là Alpha Books) do một nhóm trí thức trẻ thành lập ở Hà Nội năm 2005 với niềm tin: Tri thức là Sức mạnh. Thông qua việc giới thiệu các tác phẩm có giá trị của thế giới, Alpha Books mong muốn trở thành nhịp cầu nối nguồn tri thức nhân loại với dân tộc Việt Nam.', CAST(N'2023-09-22T06:01:33.803' AS DateTime), 2, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (9, N'SkyBooks', N'sky-book.png', N'sky-book-banner.jpg', N'Thương hiệu sách Skybooks là địa chỉ tin cậy cho các tác giả trẻ Việt Nam gửi gắm tác phẩm của mình. Với khẩu hiệu “Tôi trẻ, tôi đam mê”, chúng tôi luôn nỗ lực để mang đến cho độc giả những tác phẩm chất lượng từ các tác giả trẻ nổi bật cũng như giúp đỡ, tạo điều kiện cho các cây bút trẻ được đông đảo độc giả biết đến. Hãy để Skybooks trở thành người đưa đứa con tinh thần của bạn đến với công chúng!', CAST(N'2023-09-22T06:01:33.803' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (10, N'Nhã Nam', N'nha-nam.jpg', N'nha-nam-banner.png', N'Tháng 2 năm 2005, Nhã Nam, tên đầy đủ là Công ty Cổ phần Văn hóa và Truyền thông Nhã Nam đã gia nhập thị trường sách. Tác phẩm "Balzac và cô bé thợ may Trung hoa" của Đới Tư Kiệt là cuốn sách đầu tiên được những người sáng lập Nhã Nam xuất bản cả trước khi công ty ra đời. Ngay lập tức từ cuốn sách đầu tiên, độc giả đã dành sự quan tâm và yêu mến cho một thương hiệu sách mới mẻ mang trong mình khát vọng góp phần tạo lập diện mạo mới cho xuất bản văn học tại Việt Nam.', CAST(N'2023-09-22T06:01:33.803' AS DateTime), 4, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (11, N'NXB Tổng Hợp', N'nxb-tong-hop.jpg', N'nxb-tong-hop-banner.png', N'Nhà xuất bản Tổng hợp thành phố Hồ Chí Minh là cơ quan chính trị – tư tưởng – văn hóa của Đảng bộ và nhân dân thành phố Hồ Chí Minh được thành lập từ năm 1977 xuất bản hàng ngàn tựa sách gồm nhiều thể loại về chính trị, lịch sử, văn hóa, kinh tế, pháp luật, khoa học - kỹ thuật, từ điển, ngoại ngữ', CAST(N'2023-09-22T06:01:33.803' AS DateTime), 5, NULL, 0, NULL, 0)
INSERT [dbo].[Manufactures] ([Id], [Name], [Logo], [Banner], [Description], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (12, N'adfgadsfgdsga111', N'0000000705-giao-duc-hoc-tap-hoc-sinh-kien-thuc-hoc-nhom-tai-hinh-png-187.png', N'0000000705-giao-duc-hoc-tap-hoc-sinh-kien-thuc-hoc-nhom-tai-hinh-png-187.png', N'ádgdasgdfagfadg', CAST(N'2023-11-15T20:27:36.233' AS DateTime), 1, CAST(N'2023-11-15T21:14:00.447' AS DateTime), 1, CAST(N'2023-11-15T21:07:07.347' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Manufactures] OFF
GO
SET IDENTITY_INSERT [dbo].[MenuOne] ON 

INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (1, N'Tài liệu', N'tai-lieu', 5, CAST(N'2023-09-27T13:34:44.253' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (2, N'Chính trị', N'chinh-tri', 1, CAST(N'2023-09-23T16:02:58.560' AS DateTime), 3, NULL, 0, CAST(N'2023-09-23T16:34:20.823' AS DateTime), 3)
INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (3, N'Pháp Luật', N'phap-luat', 1, CAST(N'2023-09-27T13:36:23.300' AS DateTime), 2, NULL, 0, NULL, 0)
INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (4, N'Quản Trị - Lãnh Đạo', N'quan-tri-lanh-dao', 2, CAST(N'2023-09-27T13:48:34.030' AS DateTime), 4, NULL, 0, NULL, 0)
INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (5, N'Marketing - Bán Hàng', N'marketing-ban-hang', 2, CAST(N'2023-09-27T13:49:03.473' AS DateTime), 3, NULL, 0, NULL, 0)
INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (6, N'Ngoại ngữ', N'ngoai-ngu', 5, CAST(N'2023-09-25T14:43:01.383' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[MenuOne] ([Id], [Name], [NameSearch], [Cate_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (7, N'hahaa1', N'ha-haa', 1, CAST(N'2023-11-15T21:31:34.927' AS DateTime), 1, CAST(N'2023-11-15T21:32:27.550' AS DateTime), 1, CAST(N'2023-11-15T21:32:11.040' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[MenuOne] OFF
GO
SET IDENTITY_INSERT [dbo].[MenuTwo] ON 

INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (1, N'Tiếng Anh', N'tieng-anh', 6, CAST(N'2023-09-27T08:57:16.547' AS DateTime), 1, NULL, 0, CAST(N'2023-09-27T09:50:05.843' AS DateTime), 1)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (2, N'Tiếng Trung', N'tieng-trung', 6, CAST(N'2023-09-27T13:51:41.380' AS DateTime), 3, NULL, 0, NULL, 0)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (3, N'Tiếng Hàn', N'tieng-han', 6, CAST(N'2023-09-27T13:51:57.833' AS DateTime), 3, NULL, 0, CAST(N'2023-11-25T10:24:57.327' AS DateTime), 1)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (4, N'Tiếng Nhật', N'tieng-nhat', 6, CAST(N'2023-09-27T13:52:57.890' AS DateTime), 2, NULL, 0, NULL, 0)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (5, N'Khoa học chính trị', N'khoa-hoc-chinh-tri', 2, CAST(N'2023-09-27T13:54:08.687' AS DateTime), 4, NULL, 0, NULL, 0)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (6, N'Xã hội chính trị', N'xa-hoi-chinh-tri', 2, CAST(N'2023-09-27T13:54:29.927' AS DateTime), 5, NULL, 0, NULL, 0)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (7, N'Chức năng quản trị', N'chuc-nang-quan-tri', 4, CAST(N'2023-09-27T13:55:09.010' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (8, N'Vai trò quản trị', N'vai-tro-quan-tri', 4, CAST(N'2023-09-27T13:55:41.077' AS DateTime), 1, NULL, 0, NULL, 0)
INSERT [dbo].[MenuTwo] ([Id], [Name], [NameSearch], [Menu1_Id], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate]) VALUES (9, N'haha11', N'hahaha', 1, CAST(N'2023-11-15T21:54:27.927' AS DateTime), 1, CAST(N'2023-11-15T21:55:27.410' AS DateTime), 1, CAST(N'2023-11-15T21:55:01.330' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[MenuTwo] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (1, N'111863', 7, 2, NULL, 1, CAST(N'2023-09-25' AS Date), N'1', N'1', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (2, N'565067', 10, 3, 1, 3, CAST(N'2023-09-25' AS Date), N'1', N'1', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (3, N'129524', 13, 4, NULL, 5, CAST(N'2023-09-25' AS Date), N'1', N'2', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (5, N'957074', 21, 6, NULL, 3, CAST(N'2023-09-25' AS Date), N'0', N'2', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (6, N'245535', 2, 7, 3, 2, CAST(N'2023-09-25' AS Date), N'0', N'3', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (7, N'111853', 7, 1, NULL, 1, CAST(N'2023-09-25' AS Date), N'1', N'2', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (8, N'326129', 2, 1, NULL, 2, CAST(N'2023-11-13' AS Date), N'1', N'1', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (9, N'785169', 5, 1, NULL, 1, CAST(N'2023-11-13' AS Date), N'1', N'3', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (10, N'148464', 2, 11, NULL, 1, CAST(N'2023-11-13' AS Date), N'0', N'3', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (11, N'660212', 7, 1, NULL, 1, CAST(N'2023-11-13' AS Date), N'1', N'0', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (12, N'879250', 5, 1, NULL, 1, CAST(N'2023-11-13' AS Date), N'1', N'0', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (13, N'272807', 18, 14, NULL, 1, CAST(N'2023-11-13' AS Date), N'0', N'2', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (14, N'747504', 21, 1, NULL, 1, CAST(N'2023-11-14' AS Date), N'1', N'3', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (15, N'712178', 8, 1, NULL, 1, CAST(N'2023-11-15' AS Date), N'1', N'2', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (16, N'371533', 3, 1, NULL, 1, CAST(N'2023-11-16' AS Date), N'1', N'0', N'')
INSERT [dbo].[Orders] ([Id], [Code], [Product_Id], [Address_Id], [Discount_Id], [Quality], [Date], [Method], [Status], [Comment]) VALUES (17, N'602221', 22, 12, NULL, 1, CAST(N'2023-11-18' AS Date), N'0', N'2', N'hahaha')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (1, N'STCCCT', N'Sự Tích Chú Cuội Cung Trăng', 27000, 35, 83, N'Ngày xửa ngày xưa, có một chàng trai tên là Cuội. Một ngày nọ, khi vào rừng hái củi, anh phát hiện ra một loại cây thần kỳ có thể hồi sinh người chết. Nhờ cây thuốc quý mà Cuội đã cứu được nhiều mạng sống. Nhưng những cái cây thần kỳ bắt đầu mang đến cả vận may và bất hạnh cho cuộc đời Cuội.
<br>
<span>Hãy cùng tìm hiểu chuyện gì đã xảy ra và làm thế nào mà Cuội lại ở lại Mặt Trăng nhé!</span>
<br>
<span>Bộ truyện dân gian Việt Nam bao gồm một số truyện dân gian Việt Nam nổi tiếng và tiêu biểu nhất. Các câu chuyện được kể lại bằng tiếng Anh và minh họa bởi các họa sĩ nổi tiếng, giúp người đọc hiểu thêm về văn hóa Việt Nam như bánh gạo truyền thống, Tết Nguyên đán, truyền thuyết Hồ Gươm...</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Nhà xuất bản Kim Đồng</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Tạ Huy Long, Hồng Hà</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>Kim Đồng</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2023</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>100</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>27 x 19 x 0.2 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>32</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>12+</td>
											</tr>
										</tbody>
									</table>', N'stccct_1.jpg', N'stccct_2.jpg', N'stccct_3.jpg', N'stccct_4.jpg', N'stccct_5.jpg', 1, 1, 8, N'su-tich-chu-cuoi-cung-trang', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (2, N'NHDNLT', N'Nam Hải Dị Nhân Liệt Truyện', 425000, 3, 88, N'“Hào kiệt anh tài là khí tinh anh của một nước; cho nên nước nào cũng có, mà thời nào cũng có. Lớn thì gây dựng nên thời thế, tô điểm cho non sông; nhỏ thì lập nên công nghiệp, để danh tiếng về sau; cũng là làm gương cho người đời cả.” – Phan Kế Bính, trích Lời tựa cho cuốn <b>Nam Hải Dị Nhân Liệt Truyện</b>
<br>
<span>Xuyên suốt chiều dài 4.000 năm lịch sử, nước Việt Nam nổi lên biết bao người tài đức, danh tiếng; nhưng bởi vì sự tích xa xôi mà không rõ, hoặc vì sử sách biên sót mà không tường. Trong đó, chỉ có những tấm gương nổi bật được ghi vào sử, hoặc chép vào kí tái của các tư gia mới được lưu truyền lại cho đời sau.</span>
<br>
<span><b>Nam Hải Dị Nhân Liệt Truyện</b> tập hợp những câu chuyện kể về các “dị nhân” nước Nam, những người mà tên tuổi của họ gắn liền với điều khác thường (có hình dáng bất thường, có tài lạ, có sự tích huyền bí, kì quái...). Dưới ngòi bút của Phan Kế Bính, các câu chuyện về họ hoà quyện giữa những chi tiết chính sử lẫn những yếu tố dã sử, tạo cho cuốn sách một không khí vừa chân thực, vừa li kì, hấp dẫn nhưng cũng không thiếu chuyện hoang đường, mê tín, vốn là một trong những đặc thù của văn hoá dân gian.</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Nhà xuất bản Kim Đồng</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Phan Kế Bính, Lê Văn Phúc, Tạ Huy Long</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>Kim Đồng</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>900</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>32 x 24 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>288</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Cứng</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>14+</td>
											</tr>
										</tbody>
									</table>', N'nhdn_1.jpg', N'nhdn_2.jpg', N'nhdn_3.jpg', N'nhdn_4.jpg', N'nhdn_5.jpg', 1, 1, 8, N'nam-hai-di-nhan-liet-truyen', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (3, N'BHLKH', N'Bắc Hành Lược Ký', 106000, 7, 94, N'Bắc hành lược ký là một “hồi ký chính trị” của Trường Phái hầu Lê Quýnh mà trọng điểm là mười năm ông bị cầm tù trong nhiều nhà ngục tại Trung Hoa sau khi theo vua Lê Chiêu Thống chạy sang phương Bắc lưu vong. Có thể xem nó như một phong vũ biểu đo lường gió mưa, thăng trầm của thời cuộc, phản ánh những lên xuống trong bang giao Thanh-Việt từ đời Quang Trung sang đời Cảnh Thịnh và sau cùng là đời Gia Long.
<br>
<span>Bản dịch mới lần này dựa theo bản Hán văn của tạp chí Nam Phong, có đối chiếu, bổ túc, tham khảo các bản in trong Việt Nam Hán văn tiểu thuyết tùng san và lưu trữ tại Viện Hán Nôm Hà Nội, cùng những tài liệu, văn thư của triều Thanh trong cùng thời điểm, nhằm giúp bạn đọc có cái nhìn đa chiều, sáng tỏ hơn khi nghiên cứu về giai đoạn lịch sử từ cuối triều Lê đến đầu triều Nguyễn.</span>
<br>
<span></span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Nhã Nam</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Lê Quýnh</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Hội Nhà Văn</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2020</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>400</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>14 x 20.5 x 1.7</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>376</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'bhlk_1.jpg', N'bhlk_2.jpg', N'bhlk_3.jpg', N'bhlk_4.jpg', N'bhlk_5.jpg', 1, 10, 1, N'bac-hanh-luoc-ky', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-11-16T13:11:48.463' AS DateTime), 1, 150000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (4, N'GPBBMG', N'Giành Phiếu Bầu Bằng Mọi Giá', 193800, 115, 80, N'Cuốn sách trình bày ngắn gọn về lịch sử các kỳ bầu cử tổng thống Mỹ, bắt đầu từ năm 1789 và kết thúc ở cuộc bầu cử năm 2012 với cuộc đối đầu giữa Barack Obama và Mitt Rommey.
<br>
<span>Bố cục sách được chia thành nhiều phần nhỏ, độc lập. Mỗi phần là một cuộc bầu cử: bắt đầu bằng một trích dẫn hay, rồi trình bày bối cảnh lịch sử, điểm qua về các ứng viên tranh cử, trình bày ngắn gọn về diễn biến cuộc bầu cử.</span>
<br>
<span>Bên cạnh những thông tin cần thiết giúp độc giả có ý niệm rõ ràng về bối cảnh lịch sử, Giành phiếu bầu bằng mọi giá tập trung vào “những mưu hèn kế bẩn” trong các chiến dịch vận động, các chiến dịch truyền thông và những biến cố làm xoay chuyển tình thế của mỗi kỳ bầu cử.</span>
<br>
<span>Cuốn sách có thể đọc xuôi, đọc ngược, đọc ngẫu nhiên từng phần, mà người đọc vẫn hiểu nội dung, cũng như sẽ cảm thấy thích thú bởi những tình tiết “giật gân” được trình bày ngắn gọn.</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Nhã Nam</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Joseph Cummins</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Thế Giới</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2021</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>600</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>20.5 x 14.5 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>480</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'gpbbmg_1.jpg', N'gpbbmg_2.jpg', N'gpbbmg_3.jpg', N'gpbbmg_4.jpg', N'gpbbmg_5.jpg', 1, 10, 1, N'gianh-phieu-bau-bang-moi-gia', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (5, N'PLDCVNNPQ', N'Pháp Luật Đại Cương Và Nhà Nước Pháp Quyền', 255000, 104, 97, N'
<span>Sách này không viết cho những người trong nghề, mà chỉ dành cho những người ngoài nghề hoặc những ai không đủ thời gian theo ngành luật học nhưng tự thấy không thể thiếu khái niệm cơ bản về pháp luật trong kiến thức của mình. Bảy phần đầu trong sách với nội dung đại cương có thể giúp người đọc hiểu dễ dàng hơn khi đọc các văn kiện lập pháp hoặc lập quy hiện hành, và có một phần nào so sánh với khái niệm hay thuật ngữ về pháp luật của các nước phát triển.</span>
<br>
<span>Có thể xem nội dung của cuốn sách này như phần nhập môn của chương trình tự học luật, hoặc cũng có thể xem là một phần đóng góp khiêm tốn vào việc thực hiện chủ trương xây dựng kiến thức pháp luật phổ thông cho người dân.</span>
<br>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>NXB Tổng Hợp TPHCM</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Luật Sư Triệu Quốc Mạnh</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Tổng Hợp</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>790</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>24 x 16 x 2.1 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>540</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'pldcvnnpq_1.jpg', N'pldcvnnpq_2.jpg', N'pldcvnnpq_3.jpg', N'pldcvnnpq_4.jpg', N'pldcvnnpq_5.jpg', 1, 11, 1, N'phap-luat-dai-cuong-va-nha-nuoc-phap-quyen', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (6, N'DNCTK21TB19', N'Doanh Nghiệp Của Thế Kỷ 21 (Tái Bản 2019)', 85000, 94, 72, N'
<span>Một quyển sách khác của tác giả bộ sách nổi tiếng Dạy con làm giàu. Trong cuốn sách này, Robert T. Kiyosaki sẽ chỉ ra cho bạn đọc thấy lý do tại sao mình cần phải gây dựng doanh nghiệp riêng của mình và chính xác đó là doanh nghiệp gì.</span>
<br>
<span>Nhưng đây không phải là việc thay đổi loại hình doanh nghiệp mình đang triển khai mà đó là việc thay đổi chính bản thân. Tác giả còn cho bạn đọc biết cách thức tìm kiếm những gì mình cần để phát triển doanh nghiệp hoàn hảo, nhưng để doanh nghiệp của mình phát triển thì mình cũng sẽ phải phát triển theo.</span>
<br>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Robert T Kiyosaki, John Fleming, Kim Kiyosaki</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2019</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>280</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>13 x 20.5</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>260</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'dnctk21_1.jpg', N'dnctk21_2.jpg', N'dnctk21_3.jpg', N'dnctk21_4.jpg', N'dnctk21_5.jpg', 1, 7, 2, N'doanh-nghiep-cua-the-ki-21', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (7, N'SDTDTKDTB23', N'Sơ Đồ Tư Duy Trong Kinh Doanh (Tái Bản 2023)', 250000, 44, 92, N'
<span>Sơ đồ Tư duy là công cụ sáng tạo, tổ chức và tư duy hiệu quả nhất trong thời đại chúng ta. Nó sẽ cải thiện đáng kể mọi khía cạnh trong hoạt động kinh doanh của bạn. Trong cuốn sách mới đầy hấp dẫn này, Tony Buzan, tác giả hàng đầu cũng như có sách bán chạy nhất về não bộ và học tập, sẽ chia sẻ với bạn những kỹ thuật lập Sơ đồ Tư duy mang tính cách mạng của ông để khai phóng năng lực của bộ não đồng thời làm thay đổi vĩnh viễn lề lối lẫn hiệu suất kinh doanh của bạn.</span>
<br>
<span>Trong cuốn Sơ đồ Tư duy trong Kinh doanh, Tony, cùng Chris Giffiths, đồng sáng lập và giám đốc điều hành công ty Buzan Online, sẽ hướng dẫn bạn cách phát triển những khả năng siêu việt trong tất cả các lĩnh vực kinh doanh quan trọng - từ quản lý dự án, bán hàng, đến thuật lãnh đạo.</span>
<br>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Cty Nhân Trí Việt</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Tony Buzan, Chris Griffiths</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Tổng Hợp</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>518</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>23 x 15 x 1.6 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>339</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'sdtdtkd_1.jpg', N'sdtdtkd_2.jpg', N'sdtdtkd_3.jpg', N'sdtdtkd_4.jpg', N'sdtdtkd_5.jpg', 1, 11, 2, N'so-do-tu-duy-trong-kinh-doanh', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 320000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (8, N'DTNTDP', N'D. Trump - Nghệ Thuật Đàm Phán (Tái Bản 2020)', 95000, 31, 186, N'
<span>Quyển sách cho chúng ta thấy cách Trump làm việc mỗi ngày - cách ông điều hành công việc kinh doanh và cuộc sống - cũng như cách ông trò chuyện với bạn bè và gia đình, làm ăn với đối thủ, mua thành công những sòng bạc hàng đầu ở thành phố Atlantic, thay đổi bộ mặt của những cao ốc ở thành phố New York… và xây dựng những tòa nhà chọc trời trên thế giới.</span>
<br>
<span>Quyển sách đi sâu vào đầu óc của một doanh nhân xuất sắc và khám phá một cách khoa học chưa từng thấy về cách đàm phán một thương vụ thành công. Đây là một cuốn sách thú vị về đàm phán và kinh doanh – và là một cuốn sách nên đọc cho bất kỳ ai quan tâm đến đầu tư, bất động sản và thành công.</span>
<br>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Donald J Trump, Tony Schartz</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2020</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>350</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>20 x 14 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>328</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'ntdp_1.jpg', N'ntdp_2.jpg', N'ntdp_3.jpg', N'ntdp_4.jpg', N'ntdp_5.jpg', 1, 7, 2, N'd.trump-nghe-thuat-dam-phan', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 135000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (9, N'DTNVH', N'Đám Trẻ Nhiễu Văn Hóa', 101000, 46, 95, N'
<span>Sinh ra ở Moscow bởi bố mẹ người Việt Nam nhưng lại tin rằng mình có phần nào đó là người Xô viết, chuyển về Hà Nội khi lên 3 và vào học trường Quốc tế Pháp dưới sự ủng hộ của ông bà, 15 tuổi rời Việt Nam sang Mỹ để học tại một trường nội trú ở tiểu bang Connecticut, câu hỏi mà Ngọc Nguyễn, cũng như những đứa trẻ nhiễu văn hóa khác, cảm thấy khó trả lời nhất là "Bạn đến từ đâu?”.</span>
<br>
<span>Một món quà gửi tặng những người đã và đang là một đứa trẻ nhiều văn hóa, những bậc phụ huynh của chúng, những người sẽ-sống-cuộc-đời-expat và một "nơi trú ẩn” cho tất cả những ai từng cảm thấy lạc lõng trong thế giới không ngừng đổi thay.</span>
<br>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Nhã Nam</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Ngọc (Bi) Nguyễn</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>Nhã Nam</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>250</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>20.5 x 14 x 0.5 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>272</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'dtnvh_1.jpg', N'dtnvh_2.jpg', N'dtnvh_3.jpg', N'dtnvh_4.jpg', N'dtnvh_5.jpg', 1, 10, 3, N'dam-tre-nhieu-van-hoa', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (10, N'VHTNVN', N'Văn Hóa Tín Ngưỡng Việt Nam', 85000, 16, 31, N'
<span>Tác phẩm Văn Hóa Tín Ngưỡng Việt Nam của tác giả Nguyễn Hạnh là tác phẩm biên khảo về tín ngưỡng của người Việt khởi từ thời dựng nước với khái niệm thờ: Trời, đất,  tổ tiên. Những niềm tin dân gian của ông bà ta xưa vẫn còn lưu truyền đến ngày nay thông qua kho tàng ca dao.Tiếp đến là sự giao thoa văn hóa với 4 tôn giáo lớn ở Việt Nam là Nho, Phật, Lão, Công Giáo.</span>
<br>
<span>Chính sự giao thoa giữa tín ngưỡng dân gian Việt Nam và 4 tôn giáo lớn này đã hình thành nên nhiều tôn giáo bản địa như Cao Đài, Hòa Hảo, Hòa Đồng Tôn Giáo. Sự giao thoa, tiếp thu và chọn lọc đó đã làm nên một đặc sắc trong chiều kích tâm linh đó là các tôn giáo du nhập phải được điều chỉnh và chứa đựng được yếu tố tín ngưỡng của người Việt. Chính những ảnh hưởng giao thoa này góp phần tạo nên bản sắc văn hóa, tín ngưỡng của người Việt xuyên suốt từ thuở dựng nước đến nay.</span>
<br>
<span>Tác phẩm Văn Hóa Tín Ngưỡng Việt Nam sẽ góp phần làm phong phú thêm Tủ sách Triết học phương Đông của NXB Trẻ và là quyển sách cần thiết cho độc giả tìm hiểu về cội nguồn tín ngưỡng của người Việt.</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Nguyễn Hạnh</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2020</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>250</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>15.5 x 23 x 1.1</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>232</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'vhtnvn_1.jpg', N'vhtnvn_2.jpg', N'vhtnvn_3.jpg', N'vhtnvn_4.jpg', N'vhtnvn_5.jpg', 1, 7, 3, N'van-hoa-tin-nguong-viet-nam', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (11, N'CKRCNVT', N'Chuyện Kể Rằng Có Nàng Và Tôi', 61000, 26, 23, N'
<span>Đối với những người trẻ được sống như ý không phải lúc nào cũng dễ dàng, đặc biệt với những người đã phải trải qua một quãng thời gian khó khăn rồi mới có thể tìm được con người thật của mình, là chính mình.</span>
<br>
<span>Những câu chuyện tình của họ có nhiều dang dở vì những mặc cảm, rào cản, khao khát được làm điều mình muốn, gắn bó với người mình yêu thương cả đời là các mong ước nhỏ trong lòng. Để rồi khi không thể giãi bày cùng ai, họ mang những điều thầm kín thổi vào những vần thơ nơi chỉ có những “câu chuyện về nàng và tôi”.</span>
<br>
<span></span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>SkyBooks</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Nhiều Tác Giả</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>SkyBooks</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>200</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>17 x 11 cm x 0.8</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>152</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'ckrcnvt_1.jpg', N'ckrcnvt_2.jpg', N'ckrcnvt_3.jpg', N'ckrcnvt_4.jpg', N'ckrcnvt_5.jpg', 1, 9, 4, N'chuyen-ke-rang-co-nang-va-toi', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (12, N'COADDME', N'Cảm Ơn Anh Đã Đánh Mất Em', 84000, 46, 61, N'
<span>“Hãy đọc để một ngày nào đó, nếu chúng ta gặp lại, ta vẫn muốn được chào nhau một câu, hỏi han nhau vài lời. Và cảm ơn vì năm ấy người đã xuất hiện, cùng nhau dệt nên một đoạn ký ức thật đẹp.”</span>
<br>
<span>“Cảm ơn anh đã đánh mất em” sẽ giúp bạn đi qua tổn thương để dạy trái tim mạnh mẽ hơn, để sự tổn thương của nhiều lần sau đó cũng không đáng sợ đến mức khiến cho người ta đau đến tan lòng nát dạ hay không thể bước tiếp được nữa.</span>
<br>
<span>Bạn đã bao giờ yêu một người hết lòng và ước mong sống vui vẻ với nhau trọn đời, nhưng rồi nhận ra tình yêu phức tạp quá, đôi khi khiến bản thân trở nên sợ hãi mất hết niềm tin?</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>AZ Việt Nam</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Trí</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>Văn Học</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>300</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>20.5 x 13 x 1 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>256</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'coaddme_1.jpg', N'coaddme_2.jpg', N'coaddme_3.jpg', N'coaddme_4.jpg', N'coaddme_5.jpg', 1, 4, 4, N'cam-on-anh-da-danh-mat-em', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 124000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (13, N'TCCCVDTG', N'Tài Chính Có Cứu Vãn Được Thế Giới?', 106000, 78, 253, N'
<span>Tài chính có cứu vãn được thế giới bàn về tài chính dưới góc nhìn vĩ mô, trong con mắt của người từng lãnh đạo một trong những quỹ tài chính lớn nhất thế giới, một nhân vật kỳ cựu, từng có ảnh hưởng quan trọng không chỉ trong giới tài chính mà còn cả với các quốc gia, cựu giám đốc điều hành của Ngân hàng Thế giới (World Bank).</span>
<br>
<span>Sách thể hiện cách ông nhìn nhận, đánh giá và phân tích cuộc khủng hoảng tài chính 2007-2008, bài học rút ra và những gì cần làm để tài chính có thể phục vụ tốt nhất cho nhân loại.</span>
<br>
<span>Với ông, tài chính là một công cụ, nếu trong tay người sử dụng tốt, nó sẽ trở thành công cụ tốt có khả năng tạo ra những biến đổi phi thường. Và ông đề xuất những cách thức để sử dụng hiệu quả công cụ tài chính đó - Now is the time to regain control over money to serve the common good (trích).</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Bertrand Badré</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Trẻ</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2019</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>300</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>15.5 x 23 cm x 1.5</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>293</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'tcccvdtg_1.jpg', N'tcccvdtg_2.jpg', N'tcccvdtg_3.jpg', N'tcccvdtg_4.jpg', N'tcccvdtg_5.jpg', 1, 7, 5, N'tai-chinh-co-cuu-van-duoc-the-gioi', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 296000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (14, N'VIRS', N'Vic Ielts Reading Start', 203000, 111, 264, N'
<span>Cuốn sách bao quát toàn bộ những kiến thức căn bản nhất về bài thi IELTS Reading bằng phương pháp tiếp cận đơn giản và thực tế với người học, dù bạn mới bắt đầu làm quen với IELTS hay đang có kế hoạch ôn luyện để bứt phá band điểm trong thời gian ngắn.</span>
<br>
<span>Cung cấp thêm cho người học nhiều thông tin bổ ích khác: thông tin đầy đủ về kỳ thi IELTS nói chung và bài thi IELTS Reading nói riêng, gợi ý lộ trình và phương pháp ôn tập hiệu quả, bổ sung từ điển bằng hình ảnh đối với một số chủ đề có tính chuyên môn như môi trường, cơ thể người, động vật, thực vật</span>
<br>
<span>Người mới bắt đầu làm quen với IELTS, vừa củng cố kiến thức cơ bản vừa nhắm tới mục tiêu chinh phục bài thi IETLS trong thời gian ngắn</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Alpha Books</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Cherie Park Siwonschool</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Dân Trí</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2022</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>495</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>24 x 16 x 1.7 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>360</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'virs_1.jpg', N'virs_2.jpg', N'virs_3.jpg', N'virs_4.jpg', N'virs_5.jpg', 1, 8, 5, N'vic-ielts-reading-start', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (15, N'THUDHNTHN', N'Tiếng Hàn Ứng Dụng Học Nhanh, Thực Hành Ngay', 143000, 21, 51, N'
<span>Visang Education là tổ chức giáo dục và xuất bản hàng đầu Hàn Quốc. Visang chuyên xuất bản sách giáo khoa, các chương trình học trực tuyến và trực tiếp dành cho đối tượng phổ thông và người đi làm, thu hút hàng chục triệu học viên tại Hàn Quốc.</span>
<br>
<span>Một trong những thương hiệu nổi tiếng của Visang là MasterKorean và MasterTOPIK được nhiều học viên Việt Nam biết đến là chương trình đào tạo số 1 về tiếng Hàn, luyện thi TOPIK (chứng chỉ đánh giá năng lực tiếng Hàn) được Chính phủ Hàn Quốc công nhận.</span>
<br>
<span>Bộ sách được chia thành 2 trình độ: sơ cấp và trung cấp, mỗi trình độ gồm 2 cuốn với nhiều chủ đề đa dạng cùng hình ảnh sinh động, gắn liền với cuộc sống thường nhật.</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Alpha Books</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Kim Mi Sook, Park So Yeon</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Dân Trí</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2021</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>560</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>26 x 19 x 1.5 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>344</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'thudhnthn_1.jpg', N'thudhnthn_2.jpg', N'thudhnthn_3.jpg', N'thudhnthn_4.jpg', N'thudhnthn_5.jpg', 1, 8, 5, N'tieng-han-ung-dung-hoc-nhanh-thuc-hanh-nhanh', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 217000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (16, N'TNKK', N'Tiếng Nhật Không Khó', 83000, 59, 150, N'
<span> Bộ sách được người Nhật ví như “ Cuốn sách giáo khoa” dễ học nhất của họ. Dù không biết về tiếng Nhật, bạn cũng sẽ cảm thấy rất dễ hiểu và thân thiện. Nội dung của bộ sách có tính ứng dụng cao, chia thành nhiều cấp độ. </span>
<br>
<span>Học tiếng Nhật thật vui vẻ cũng là tinh thần của bộ giáo trình này. Bạn sẽ nhận thấy những đoạn hội thoại đều mang nét dí dỏm và tạo một sự hứng khởi nhẹ nhàng. Những bức tranh minh họa đậm chất manga Nhật. Đây chính là cách thiết kế giáo trình thông minh của người Nhật để có thể đưa văn hóa của họ đến khắp mọi nơi trên thế giới.</span>
<br>
<span>Với mục tiêu nâng cao khả năng nghe, nói. Giúp cho học viên có thể tự tin giao tiếp với người bạn xứ. DoxaBooks đã mã hóa bộ giáo trình Tiếng Nhật Không Khó nếu sử dụng kèm với bút chấm đọc thông minh DoxaBooks để bạn có thể nghe những cuộc đàm thoại với ngữ điệu chuẩn của người bản xứ, học cách phát âm đúng từng từ như người Nhật</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Firsts New</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Masateru Takatsu</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Tổng Hợp TPHCM</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2017</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>301</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>23 x 17 x 0.7 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>134</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'tnkk_1.jpg', N'tnkk_2.jpg', N'tnkk_3.jpg', N'tnkk_4.jpg', N'tnkk_5.jpg', 1, 3, 5, N'tieng-nhat-khong-kho', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 101000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (17, N'TVTTTD', N'3000 Từ Vựng Tiếng Trung Thông Dụng', 102000, 68, 219, N'
<span>Với các bạn học Tiếng Trung, từ vựng từ lâu đã trở thành một phần vô cùng quan trọng luôn được mọi người ưu tiên để có thể hướng tới giao tiếp thành thạo một cách nhanh nhất. Từ vựng chính là cầu nối giúp người học hiểu và hoàn thiện kỹ năng giao tiếp của bản thân cũng như dễ dàng hơn trong việc tiếp cận và nắm bắt văn hóa Trung Hoa.</span>
<br>
<span>Thực tế có rất nhiều người đã và đang học Tiếng Trung gặp khó khăn trong việc ghi nhớ từ vựng vì số lượng từ vựng quá nhiều và các phương pháp học từ vựng hiện tại không đáp ứng được nhu cầu học tập của mọi người.</span>
<br>
<span>Đây là một trong những cuốn sách mà bất kỳ bạn nào muốn học và sử dụng thành thao tiếng Trung đều nên sở hữu bởi nó sẽ là trợ thủ đắc lực giúp bạn hoàn thiện quá trình rèn luyện giao tiếp cũng như luyện thi HSK tại nhà một cách nhanh chóng.</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>IPM</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Kaixin</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>IPM</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2021</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>450</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>15 x 10.5 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>422</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'tvtttd_1.jpg', N'tvtttd_2.jpg', N'tvtttd_3.jpg', N'tvtttd_4.jpg', N'tvtttd_5.jpg', 1, 5, 5, N'tu-vung-tieng-trung-thong-dung', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (18, N'TT', N'Tử Thần', 67000, 7, 14, N'
<span>Non nớt, kì bí tựa như một ngôi sao được thai nghén hàng trăm triệu năm và vừa được ra đời trong cơn đau đẻ dữ dội của Vũ trụ. Bởi vì nguồn gốc của Vũ trụ là một điều bí ẩn, nên nguồn gốc của hắn cũng vậy.</span>
<br>
<span>Hắn không phải là vật chất, không hẳn là tinh thần, có phải chăng là ảo giác, là tưởng tượng? Hay là một tạo vật kinh dị do lịch sử tạo ra?</span>
<br>
<span>Chính hắn cũng không biết hắn từ đâu mà được sinh ra.</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Thái Hà</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Acloud</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Hà Nội</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2019</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>200</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>13 x 20.5 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>186</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'tt_1.jpg', N'tt_2.jpg', N'tt_3.jpg', N'tt_4.jpg', N'tt_5.jpg', 1, 2, 6, N'tu-than', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 82000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (19, N'VCL', N'Vùng Cách Ly', 64000, 19, 23, N'
<span>Vùng cách ly đưa người đọc vào bên trong Bệnh viện Việt Pháp, sống trong “Vùng cách ly”, sống với những số phận lo âu trong lòng Hà Nội những năm tháng kinh hoàng đó, rồi mở rộng đến những vùng đất khác, những số phận khác, những vấn đề khác. Để cùng khám phá ra rằng mỗi cuộc đời đều chịu một sự cách ly, đều luôn chờ đợi sẽ xảy ra một điều gì đó không lường trước được, rằng trong mỗi con người đều có một đốm lửa thiện, đôi khi chỉ còn le lói dưới đống tro tàn mà những lỗi lầm trên đường đời chất lên ngày càng dày, nhưng đốm lửa ấy vẫn chờ đến một thời điểm, một hoàn cảnh, một ngưỡng chín muồi của lương tâm để lại bùng lên.</span>
<br>
<span></span>
<br>
<span></span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Thái Hà</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Lorenzo Angeloni</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>NXB Lao Động</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>05-2017</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>380</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>15.5 x 24</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>378</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'vcl_1.jpg', N'vcl_2.jpg', N'vcl_3.jpg', N'vcl_4.jpg', N'vcl_5.jpg', 1, 2, 6, N'vung-cach-ly', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 98000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (20, N'THVHTL', N'Tìm Hiểu Văn Hóa Tâm Linh', 39000, 39, 46, N'
<span>Nhiều dân tộc trên thế giới rất quan tâm đến văn hóa tâm linh! Việt Nam ta cũng là một trong số ấy, các tục lệ, nghi thức, lễ bái thờ cúng, tu sửa nhà thờ họ, chùa đền miếu mạo; tu sửa nghĩa trang liệt sĩ, mồ mả ông bà quyến tộc được người dân nhiệt tâm tham gia và gìn giữ bao đời nay như một nét truyền thống văn hóa.</span>
<br>
<span>Điều này cũng thu hút nhiều học giả tận phương Tây sang xứ mình để tìm hiểu văn hóa tâm linh. </span>
<br>
<span>Nhiều vị thức giả trong nước, nhất là các vị cao tuổi rất thiết tha đến với văn hóa tâm linh, tìm hiểu “thế giới bên kia”! Vậy phải biết tìm hiểu cuộc hành trình sắp đến của mình chứ? Mình rồi sẽ đi về đâu? Ăn uống ngủ nghỉ chỗ đó thế nào? Bạn bè tri kỷ, hội đoàn sẽ ra sao?... Nếu những tìm hiểu ấy là đúng thì thật tuyệt phúc! Nếu không đúng thì mình cũng an lòng, không ân hận gì nữa!</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Amak Books</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Trung Nghĩa</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>Hồng Đức</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>03/2015</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>350</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>14.5 x 20.5</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>224</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'thvhtl_1.jpg', N'thvhtl_1.jpg', N'thvhtl_1.jpg', N'thvhtl_1.jpg', N'thvhtl_1.jpg', 1, 6, 7, N'tim-hieu-van-hoa-tam-linh', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 76000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (21, N'LMLH', N'Lò Mổ Linh Hồn', 169000, 50, 328, N'
<span>Cuốn sách cũng xoáy sâu vào câu hỏi “Điều gì khiến chúng ta cứ vấn vương về những kẻ giết người, các thành viên giáo phái và những kẻ ăn thịt đồng loại ngay cả khi chúng khiến chúng ta khiếp sợ”? Chúng ta thấy những câu chuyện về chúng vô cùng hấp dẫn và đáng sợ, bởi chúng là tấm gương phản chiếu những thất bại của xã hội và cả những nỗi kinh hoàng mà con người chúng ta có thể gieo rắc? RedHanded thẳng thắn bác bỏ câu chuyện lỗi thời về những kẻ giết người bị coi quái vật và rằng một nạn nhân trở thành nạn nhân chỉ vì họ đã “đến sai nơi, vào sai thời điểm”.</span>
<br>
<span>Thay vào đó, nó mổ xẻ câu chuyện về những kẻ giết người theo hướng thách thức nhận thức của chúng ta, từ đó đặt ra những câu hỏi hóc búa về xã hội, giới tính, nghèo đói, văn hóa và thậm chí cả nền chính trị của chúng ta.</span>
<br>
<span>Động lực nào thôi thúc một kẻ giết người xuống tay tàn độc với đồng loại?</span>', N'<table class="table table-bordered">
										<thead>
											<tr>
												<td colspan="2"><b>THÔNG TIN SẢN PHẨM</b></td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Tên nhà cung cấp:</td>
												<td>Amak Books</td>
											</tr>
											<tr>
												<td>Tác giả:</td>
												<td>Hannah Maguire, Suruthi Bala</td>
											</tr>
											<tr>
												<td>NXB</td>
												<td>Thanh Niên</td>
											</tr>
											<tr>
												<td>Năm XB:</td>
												<td>2023</td>
											</tr>
											<tr>
												<td>Trọng lượng:</td>
												<td>430</td>
											</tr>
											<tr>
												<td>Kích thước bao bì:</td>
												<td>20.5 x 13 x 2.1 cm</td>
											</tr>
											<tr>
												<td>Số trang:</td>
												<td>436</td>
											</tr>
											<tr>
												<td>Hình thức:</td>
												<td>Bìa Mềm</td>
											</tr>
											<tr>
												<td>Độ tuổi:</td>
												<td>16+</td>
											</tr>
										</tbody>
									</table>', N'lmlh_1.jpg', N'lmlh_2.jpg', N'lmlh_3.jpg', N'lmlh_4.jpg', N'lmlh_5.jpg', 1, 6, 7, N'lo-mo-linh-hon', CAST(N'2022-10-10T17:30:25.843' AS DateTime), 1, NULL, 0, CAST(N'2023-08-06T20:37:16.043' AS DateTime), 1, 0)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (22, N'KPVD', N'1000 Phát Minh & Khám Phá Vĩ Đại', 424150, 47, 20, N'<div id="product_tabs_description_contents">
  <div id="desc_content" class="std">
    <p style="text-align: justify;"><strong>1000 Ph&aacute;t Minh &amp; Kh&aacute;m Ph&aacute; Vĩ Đại</strong></p>

    <p style="text-align: justify;">Ba triệu năm s&aacute;ng tạo v&agrave; t&ograve; m&ograve; đ&atilde; tạo ra
      h&agrave;ng chục ngh&igrave;n s&aacute;ng chế v&agrave; ph&aacute;t minh.</p>

    <p style="text-align: justify;">Những s&aacute;ng chế v&agrave; ph&aacute;t minh từng đ&aacute;p ứng được c&aacute;c
      nhu cầu cơ bản của con người - từ nhu cầu sinh tồn đến nhu cầu hiểu biết - đ&atilde; giữ một vai tr&ograve; lớn
      lao trong việc định h&igrave;nh thế giới của ch&uacute;ng.</p>

    <p style="text-align: justify;">Thường rất kh&oacute; ph&acirc;n biệt ranh giới giữa s&aacute;ng chế v&agrave;
      ph&aacute;t minh, v&agrave; kh&ocirc;ng c&oacute; c&aacute;i n&agrave;o trong số đ&oacute; tạo ra trong một sớm
      một chiều.</p>

    <p style="text-align: justify;">C&oacute; thể ch&uacute;ng ta sẽ sớm c&oacute; đủ hiểu biết để kiểm so&aacute;t
      ch&iacute;nh cỗ m&aacute;y cuộc sống v&agrave; điều n&agrave;y sẽ khiến tương lai c&oacute; nhiều thay đổi.</p>
    <div class="clear"></div>
  </div>
</div>', N'<div class="product_view_content-title">Thông tin sản phẩm</div>
<div class="product_view_tab_content_ad">
  <div class="product_view_tab_content_additional">
    <table class="data-table table-additional">
      <col width="25%" />
      <col />
      <tbody>
        <tr>
          <th class="table-label">
            Mã hàng </th>
          <td class="data_sku">
            8794069302930 </td>
        </tr>
        <tr>
          <th class="table-label">
            Tên Nhà Cung Cấp </th>
          <td class="data_supplier">
            <a class="xem-chi-tiet" href="zen-books?fhs_campaign=ATTRIBUTE_PRODUCT">ZenBooks</a>
          </td>
        </tr>
        <tr>
          <th class="table-label">
            Tác giả </th>
          <td class="data_author">
            Roger Bridgman </td>
        </tr>
        <tr>
          <th class="table-label">
            Người Dịch </th>
          <td class="data_translator">
            Vũ Thái Hả, Lê Thị Thanh Thảo </td>
        </tr>
        <tr>
          <th class="table-label">
            NXB </th>
          <td class="data_publisher">
            Dân Trí </td>
        </tr>
        <tr>
          <th class="table-label">
            Năm XB </th>
          <td class="data_publish_year">
            2022 </td>
        </tr>
        <tr>
          <th class="table-label">
            Trọng lượng (gr) </th>
          <td class="data_weight">
            1244 </td>
        </tr>
        <tr>
          <th class="table-label">
            Kích Thước Bao Bì </th>
          <td class="data_size">
            28.5 x 21.5 x 2 cm </td>
        </tr>
        <tr>
          <th class="table-label">
            Số trang </th>
          <td class="data_qty_of_page">
            154 </td>
        </tr>
        <tr>
          <th class="table-label">
            Hình thức </th>
          <td class="data_book_layout">
            Bìa Cứng </td>
        </tr>


        <tr>
          <th class="table-label">Sản phẩm bán chạy nhất</th>
          <td>
            <a style="font-size: 14px; color: #2489F4;"
              href="https://www.fahasa.com/sach-trong-nuoc/thieu-nhi/kien-thuc-bach-khoa/kien-thuc-kho-hoc-tu-nhien.html?order=num_orders_month">Top
              100 sản phẩm Kiến Thức Khoa Học - Tự Nhiên bán chạy của tháng</a>
          </td>
        </tr>


        <tr>
          <td colspan="2" style="padding: 0px;">
            <div>Giá sản phẩm trên Fahasa.com đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm,
              hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như Phụ phí đóng gói, phí vận chuyển,
              phụ phí hàng cồng kềnh,...</div>
            <div style="color: #C92127;">Chính sách khuyến mãi trên BookWorldR.com không áp dụng cho Hệ thống Nhà sách
              BookWorldR trên toàn quốc</div>
          </td>
        </tr>
      </tbody>
    </table>
  </div></div>', N'kpvd_1.jpg', N'kpvd_2.jpg', N'kpvd_3.jpg', N'kpvd_4.jpg', N'kpvd_5.jpg', 1, 5, 3, N'kham-pha-vi-dai', CAST(N'2023-11-16T13:45:05.287' AS DateTime), 1, NULL, 0, CAST(N'2023-11-16T13:56:19.547' AS DateTime), 1, 550000)
INSERT [dbo].[Products] ([Id], [Code], [Name], [Price], [Quality], [Views], [Description], [Specification], [Image1], [Image2], [Image3], [Image4], [Image5], [Active], [Manu_Id], [Cate_Id], [NameSearch], [CreateDay], [PersonCreate], [DeleteDay], [PersonDelete], [UpdateDay], [PersonUpdate], [Sales]) VALUES (23, N'masp', N'tensp', 20000, 22, 2, N'tttttttttttttttttttttttttttttttttttttttttttttttttttt', N'ttttttttttttttttt', N'kpvd_1.jpg', N'kpvd_2.jpg', N'kpvd_3.jpg', N'kpvd_4.jpg', N'kpvd_5.jpg', 0, 1, 5, N'tim-kiem', CAST(N'2023-11-16T14:22:06.427' AS DateTime), 1, CAST(N'2023-11-16T14:44:16.997' AS DateTime), 1, CAST(N'2023-11-16T14:39:59.193' AS DateTime), 1, 0)
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([Id], [Name]) VALUES (1, N'ROLE_USER')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (2, N'ROLE_ADMIN')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (3, N'ROLE_DIRECTOR')
SET IDENTITY_INSERT [dbo].[Roles] OFF
GO
SET IDENTITY_INSERT [dbo].[User_Role] ON 

INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (1, 1, 3)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (2, 2, 2)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (3, 3, 2)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (4, 4, 2)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (5, 5, 2)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (6, 6, 2)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (7, 7, 1)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (8, 8, 1)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (9, 9, 1)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (10, 10, 1)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (11, 11, 1)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (12, 12, 1)
INSERT [dbo].[User_Role] ([Id], [User_Id], [Role_Id]) VALUES (1008, 1008, 2)
SET IDENTITY_INSERT [dbo].[User_Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (1, N'trungdt@gmail.com', N'123456', N'Đặng Thành Trung', 0, CAST(N'2003-09-24' AS Date), 1, CAST(N'2023-09-20T11:52:28.310' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (2, N'loc@gmail.com', N'123456', N'Nguyễn Đại Lộc', 1, CAST(N'2003-06-15' AS Date), 0, CAST(N'2023-09-21T11:52:28.310' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (3, N'tan@gmail.com', N'123456', N'Vũ Nhật Tân', 0, CAST(N'2003-09-27' AS Date), 0, CAST(N'2023-09-22T11:52:28.310' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (4, N'anh@gmail.com', N'123456', N'Nguyễn Trần Nhật Anh', 1, CAST(N'2003-08-10' AS Date), 0, CAST(N'2023-09-23T11:52:28.310' AS DateTime), NULL, 1)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (5, N'sang@gmail.com', N'123456', N'Nguyễn Quang Sáng', 0, CAST(N'2003-05-01' AS Date), 0, CAST(N'2023-09-24T11:52:28.310' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (6, N'no@gmail.com', N'123456', N'Phạm Thị Nở', 0, CAST(N'2003-04-11' AS Date), 0, CAST(N'2023-09-11T11:52:28.310' AS DateTime), NULL, 5)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (7, N'trungdtps23794@fpt.edu.vn', N'123456', N'Nguyễn Chí Phèo', 1, CAST(N'2000-01-02' AS Date), 1, CAST(N'2023-09-12T11:52:28.310' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (8, N'haha@gmail.com', N'123456', N'DangThanhTrung', 0, NULL, 0, CAST(N'2023-11-01T16:17:17.893' AS DateTime), CAST(N'2023-11-16T13:07:36.467' AS DateTime), 1)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (9, N'haha@gmail.com', N'1234567', N'DangThanhTrung', 0, NULL, 0, CAST(N'2023-11-02T10:46:15.813' AS DateTime), CAST(N'2023-11-16T13:07:54.330' AS DateTime), 1)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (10, N'haha@gmail.com', N'1234567', N'DangThanhTrung', 1, CAST(N'1900-01-01' AS Date), 0, CAST(N'2023-11-02T10:47:29.137' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (11, N'haha@gmail.com', N'1234567', N'DangThanhTrung', 0, NULL, 0, CAST(N'2023-11-02T10:49:01.300' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (12, N'haha@gmail.com', N'1234567', N'DangThanhTrung', 0, NULL, 1, CAST(N'2023-11-02T10:52:54.123' AS DateTime), NULL, 0)
INSERT [dbo].[Users] ([Id], [Email], [Password], [FullName], [Sex], [Birthday], [Subscribe], [CreateDay], [DeleteDay], [PersonDelete]) VALUES (1008, N'test@gmail.com', N'1234567', N'test tên', 0, NULL, 0, CAST(N'2023-11-15T17:35:07.577' AS DateTime), CAST(N'2023-11-15T18:17:24.653' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
ALTER TABLE [dbo].[Address]  WITH CHECK ADD FOREIGN KEY([User_Id])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD FOREIGN KEY([Product_Id])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD FOREIGN KEY([User_Id])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Employees]  WITH CHECK ADD FOREIGN KEY([User_Id])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD FOREIGN KEY([Product_Id])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD FOREIGN KEY([User_Id])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[MenuOne]  WITH CHECK ADD FOREIGN KEY([Cate_Id])
REFERENCES [dbo].[Categories] ([Id])
GO
ALTER TABLE [dbo].[MenuTwo]  WITH CHECK ADD FOREIGN KEY([Menu1_Id])
REFERENCES [dbo].[MenuOne] ([Id])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([Address_Id])
REFERENCES [dbo].[Address] ([Id])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([Discount_Id])
REFERENCES [dbo].[Discount] ([Id])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([Product_Id])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD FOREIGN KEY([Cate_Id])
REFERENCES [dbo].[Categories] ([Id])
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD FOREIGN KEY([Manu_Id])
REFERENCES [dbo].[Manufactures] ([Id])
GO
ALTER TABLE [dbo].[User_Role]  WITH CHECK ADD FOREIGN KEY([Role_Id])
REFERENCES [dbo].[Roles] ([Id])
GO
ALTER TABLE [dbo].[User_Role]  WITH CHECK ADD FOREIGN KEY([User_Id])
REFERENCES [dbo].[Users] ([Id])
GO
