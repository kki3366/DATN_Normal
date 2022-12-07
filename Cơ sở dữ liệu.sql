USE [master]
GO
/****** Object:  Database [Normal_DATN]    Script Date: 12/7/2022 12:10:25 PM ******/
CREATE DATABASE [Normal_DATN]


IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Normal_DATN].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Normal_DATN] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Normal_DATN] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Normal_DATN] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Normal_DATN] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Normal_DATN] SET ARITHABORT OFF 
GO
ALTER DATABASE [Normal_DATN] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Normal_DATN] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Normal_DATN] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Normal_DATN] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Normal_DATN] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Normal_DATN] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Normal_DATN] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Normal_DATN] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Normal_DATN] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Normal_DATN] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Normal_DATN] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Normal_DATN] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Normal_DATN] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Normal_DATN] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Normal_DATN] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Normal_DATN] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Normal_DATN] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Normal_DATN] SET RECOVERY FULL 
GO
ALTER DATABASE [Normal_DATN] SET  MULTI_USER 
GO
ALTER DATABASE [Normal_DATN] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Normal_DATN] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Normal_DATN] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Normal_DATN] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Normal_DATN] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Normal_DATN] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Normal_DATN] SET QUERY_STORE = OFF
GO
USE [Normal_DATN]
GO
/****** Object:  Table [dbo].[Carts]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[Cartid] [bigint] IDENTITY(1,1) NOT NULL,
	[Userid] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Price] [float] NOT NULL,
	[Quanlity] [int] NOT NULL,
	[Img] [nvarchar](50) NOT NULL,
	[idProduct] [bigint] NOT NULL,
 CONSTRAINT [PK_Carts] PRIMARY KEY CLUSTERED 
(
	[Cartid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Categoryid] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Categoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Contact]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contact](
	[Contactid] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Userid] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Date] [date] NOT NULL,
	[Status] [bit] NOT NULL,
	[Subject] [nvarchar](50) NOT NULL,
	[Contents] [nvarchar](max) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orderdetails]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orderdetails](
	[Orderdetailid] [bigint] IDENTITY(1,1) NOT NULL,
	[Orderid] [bigint] NOT NULL,
	[name_cate] [nvarchar](50) NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Price] [float] NOT NULL,
	[Quanlity] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Orderdetailid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Orderid] [bigint] IDENTITY(1,1) NOT NULL,
	[Userid] [nvarchar](50) NOT NULL,
	[Date] [datetime] NOT NULL,
	[Telephone] [nvarchar](15) NOT NULL,
	[Address] [nvarchar](150) NOT NULL,
	[Amount] [float] NOT NULL,
	[Description] [nvarchar](max) NULL,
	[Status] [nvarchar](15) NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Orderid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Productid] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Price] [float] NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[Date] [date] NOT NULL,
	[Available] [bit] NOT NULL,
	[Categoryid] [bigint] NOT NULL,
	[Subcategoryid] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Description] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subcategory]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subcategory](
	[Subcategoryid] [int] NOT NULL,
	[Name] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_Subcategory] PRIMARY KEY CLUSTERED 
(
	[Subcategoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12/7/2022 12:10:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Userid] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](100) NOT NULL,
	[FullName] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](15) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Activated] [bit] NOT NULL,
	[Admin] [bit] NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Userid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (1, N'Casio')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (2, N'Rolex')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (3, N'CitiZen')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (6, N'Orient')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Contact] ON 

INSERT [dbo].[Contact] ([Contactid], [Name], [Userid], [Email], [Date], [Status], [Subject], [Contents]) VALUES (2, N'123', N'Trần Thành Trung', N'123@gmail.com', CAST(N'2022-11-23' AS Date), 0, N'Về kinh doanh', N'Shop nên bán thêm nhiều hãng đồng hồ')
INSERT [dbo].[Contact] ([Contactid], [Name], [Userid], [Email], [Date], [Status], [Subject], [Contents]) VALUES (3, N'Dương Gia Bảo', N'HoLy', N'duonggiabaobao87@gmail.com', CAST(N'2022-12-05' AS Date), 1, N'Vấn đề về shop', N'Shop nên có kiếm theo giá')
SET IDENTITY_INSERT [dbo].[Contact] OFF
GO
SET IDENTITY_INSERT [dbo].[Orderdetails] ON 

INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (1, 7, N'Casio', N'55oJI7MV233GpA2pyfEj', N'CASIO MTP-1381D-1AVDF – NAM – QUARTZ (PIN)', 1710000, 2)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (2, 8, N'CitiZen', N'ym1PkxdoK0FpLM30r1cF', N'CITIZEN NAM – QUARTZ (PIN) – DÂY KIM LOẠI (BI1050-56L)', 3285000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (3, 9, N'CitiZen', N'jXO9keL4q0kK1R4kBYeA', N'CITIZEN AK5000-54A – NAM – QUARTZ (PIN)', 3985000, 2)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (4, 10, N'Casio', N'55oJI7MV233GpA2pyfEj', N'CASIO MTP-1381D-1AVDF – NAM – QUARTZ (PIN)', 1710000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (5, 10, N'CitiZen', N'LnHXhMGdOegXugUcVs6O', N'CITIZEN NỮ – ECO-DRIVE (NĂNG LƯỢNG ÁNH SÁNG) – DÂY KIM LOẠI (EP5930-51A)', 5960000, 2)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (6, 11, N'Orient', N'JeAO1eSE5rY9d3TrbLm1', N'ORIENT CABALLERO FAG00004D0 – NAM – AUTOMATIC (TỰ ĐỘNG)', 8050000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (7, 11, N'Orient', N'fWjjESA5s6qAZDjuw3Zs', N'ORIENT RF-QA0005L10B – NỮ – QUARTZ (PIN) – DÂY DA', 3350000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (8, 12, N'Casio', N'55oJI7MV233GpA2pyfEj', N'CASIO MTP-1381D-1AVDF – NAM – QUARTZ (PIN)', 1710000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (9, 12, N'Casio', N'4q8j484AZxtDTBRCN3q0', N'DOXA D174TWH – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI', 18720000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (10006, 10011, N'Casio', N'KqcqwfyuLjvPVu380VXy', N'Casio MTP-V004L-1B2UDF – Nam – Quartz (Pin) – Dây Da – Mặt Số 41.5mm', 803000, 1)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (10007, 10012, N'Orient', N'0XfV4Mw4oGBGfxvOqRJp', N'ORIENT BAMBINO FAC00005W0 – NAM – AUTOMATIC (TỰ ĐỘNG) – MẶT SỐ 40MM, KÍNH CỨNG CONG, TRỮ CÓT 40 GIỜ', 7510000, 2)
INSERT [dbo].[Orderdetails] ([Orderdetailid], [Orderid], [name_cate], [Image], [Name], [Price], [Quanlity]) VALUES (10008, 10012, N'Orient', N'E4cnXtjRVIJvGe9IhS9h', N'ORIENT NAM – QUARTZ (PIN) – KÍNH SAPPHIRE – DÂY DA (FGW0100AW0)', 4160000, 1)
SET IDENTITY_INSERT [dbo].[Orderdetails] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (7, N'HoLy2', CAST(N'2022-12-06T07:10:50.240' AS DateTime), N'0947268364', N'188 Lê Trọng Tấn, Hòa Phát, Cẩm Lệ, Đà Nẵng', 3450000, N'', N'Đã giao')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (8, N'HoLy2', CAST(N'2022-12-06T08:21:30.123' AS DateTime), N'0947268364', N'Đường Quang Trung, Phường 11, Quận Gò Vấp, TP. HCM', 3315000, N'', N'Đã giao')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (9, N'HoLy', CAST(N'2022-12-06T12:13:11.840' AS DateTime), N'0927373737', N'Định Bình , Cà Mau', 8000000, N'', N'Đã giao')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (10, N'BaoDG22', CAST(N'2022-12-06T14:13:11.840' AS DateTime), N'0947268364', N'Định Bình, Cà Mau', 13660000, N'', N'Đã giao')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (11, N'Trungtt', CAST(N'2022-12-06T12:15:11.840' AS DateTime), N'0817680525', N'333 Nguyễn Văn Linh, Ninh Kiều, An Khánh, Cần Thơ', 11430000, N'', N'Đã giao')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (12, N'HoLy', CAST(N'2022-12-07T12:13:11.840' AS DateTime), N'0927373737', N'196 ấp 4, xã Tắc Vân , thành phố Cà Mau', 20460000, N'', N'Đã giao')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (10011, N'HoLy2', CAST(N'2022-12-07T11:33:52.570' AS DateTime), N'0947268364', N'188 Lê Trọng Tấn, Hòa Phát, Cẩm Lệ, Đà Nẵng', 833000, N'', N'Đã đặt')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (10012, N'HoLy2', CAST(N'2022-12-07T10:14:55.840' AS DateTime), N'0947268364', N'An Khánh,Ninh Kiều, Cần Thơ', 19210000, N'', N'Đã đặt')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (9, N'CASIO MTP-1130A-7ARDF – NAM – QUARTZ (PIN)', 1036000, N'gf4QTR8iG7dnOVjO864C', CAST(N'2022-12-06' AS Date), 1, 1, 1, 99, N'Đồng hồ Casio MTP-1130A-7ARDF có vỏ và dây đeo kim loại màu bạc sáng bóng, kim chỉ và vạch số thanh mãnh nổi bật trên nền số màu trắng trang nhã, đem đến phong cách sang trọng lịch lãm cho phái mạnh.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (10, N'CASIO EFV-550L-1AVUDF – NAM – QUARTZ (PIN)', 3529000, N'FpyMcVfolLI8klK2pf2o', CAST(N'2022-12-04' AS Date), 1, 1, 1, 100, N'Mẫu Casio EFV-550L-1AVUDF mang đến cho phái mạnh vẻ ngoài lịch lãm nhưng cũng không kém phần trẻ trung đặc trưng thuộc dòng Edifice với kiểu dáng đồng hồ 6 kim đi kèm tính năng đo thời gian Chronograph.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (11, N'CASIO MTP-1381D-1AVDF – NAM – QUARTZ (PIN)', 1710000, N'55oJI7MV233GpA2pyfEj', CAST(N'2022-12-05' AS Date), 1, 1, 1, 98, N'Đồng hồ Casio MTP-1381D-1AVDF có vỏ và dây đeo kim loại phủ bạc sáng bóng, nền số màu đen mạnh mẽ với kim chỉ và vạch số được phủ phản quang nổi bật, lịch thứ vị trí 12h và lịch ngày vị trí 6h.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (12, N'CASIO MTP-1302D-7A1VDF – NAM – QUARTZ (PIN)', 1347000, N'TthiZMuWuO2IRa5fnI0x', CAST(N'2022-12-04' AS Date), 1, 1, 1, 100, N'Đồng hồ Casio MTP-1302D-7A1VDF có kiểu dáng truyền thống với mặt số tròn, niềng được tạo khía nổi bật quanh nền trắng mặt số, kim chỉ và vạch số được mạ bạc trên nền số.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (14, N'CASIO MTP-1302D-7BVDF – NAM – QUARTZ (PIN)', 1357000, N'exg3VTEYh9RIa7IgbCSZ', CAST(N'2022-12-03' AS Date), 1, 1, 1, 100, N'Đồng hồ Casio MTP-1302D-7BVDF với niềng kim loại được tạo khía tinh tế, quanh nền trắng mặt số, kim chỉ phủ phản quang và chữ số được phủ màu đen nổi bật.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (15, N'CASIO NỮ – QUARTZ (PIN) – DÂY KIM LOẠI (LTP-V004G-7BUDF)', 1322000, N'MPIR04DhtXrdHBmwh2jm', CAST(N'2022-12-04' AS Date), 1, 1, 2, 100, N'Đồng hồ thời trang nữ Casio LTP-V004G-7BUDF với tổng thể kim loại được bao phủ nền vàng thời trang sang trọng, mặt nền đồng hồ trắng kèm theo chức năng lịch ngày.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (16, N'CASIO NỮ – QUARTZ (PIN) – DÂY KIM LOẠI (LTP-V005G-9AUDF)', 1135000, N'D3f3Odg7oRXn0dFurLBw', CAST(N'2022-12-03' AS Date), 1, 1, 2, 100, N'Đồng hồ sang trọng Casio LTP-V005G-9AUDF mạ vàng, với thiết kế kiểu dáng thanh lịch nữ tính xinh xắn, mặt nền vàng kết hợp nổi bật với chữ số gạch đen.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (17, N'CASIO NỮ – QUARTZ (PIN) – DÂY KIM LOẠI (LTP-1183A-7ADF)', 1140000, N'4Chj5bnbWelr3NbGX3Uk', CAST(N'2022-12-04' AS Date), 1, 1, 2, 100, N'Đồng hồ nữ Casio LTP-1183A-7ADF thanh lịch tinh tế, mặt đồng hồ có nền trắng cùng chữ số lớn, kiểu dáng 3 kim đơn giản cùng 1 lịch ngày.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (18, N'CASIO LTP-1215A-1A2DF – NỮ – QUARTZ (PIN)', 1088000, N'hgOBnG0jLDeDyUXWQntt', CAST(N'2022-12-03' AS Date), 1, 1, 2, 100, N'Đồng hồ thời trang nữ Casio LTP-1215A-1A2DF với mặt số nhỏ nữ tính, kim chỉ và gạch số được phủ bạc phản quang nổi bật trên nền đen quyến rũ, dây đeo kim loại thanh mảnh đem lại vẻ đẹp sang trọng cho phái nữ.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (19, N'CASIO LTP-1302SG-7AVDF – NỮ – QUARTZ (PIN)', 1917000, N'WBdu7Y6uhV02RZgZ9hmZ', CAST(N'2022-12-04' AS Date), 1, 1, 2, 100, N'Đồng hồ Casio LTP-1302SG-7AVDF với niềng được bo tròn nữ tính, bên trong mặt số với nền trắng cùng điểm nhấn là kim chỉ và vạch chỉ giờ được mạ vàng nổi bật, dây đeo kim loại với 2 màu vàng trắng tinh tế.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (20, N'CASIO LTP-1335D-7AVDF – NỮ – QUARTZ (PIN)', 1503000, N'5Rnkv7CeFVvgsSXxjp0S', CAST(N'2022-12-03' AS Date), 1, 1, 2, 99, N'Đồng hồ thời trang Casio LTP-1335D-7AVDF với mặt đồng hồ tròn cổ điển, vỏ và dây đeo kim loại bằng chất liệu thép không gỉ mạ bạc sang trọng, nền số màu trắng.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (21, N'CITIZEN BI5072-01A – NAM – QUARTZ (PIN)', 3685000, N'fDX5RmPMQ7tuIE3nsavm', CAST(N'2022-12-02' AS Date), 1, 3, 1, 100, N'Ẩn chứa dưới vẻ ngoài giản dị của mẫu Citizen BI5072-01A với mẫu dây da lịch lãm tông màu nâu, các chi tiết vạch số tạo hình mỏng chứa đựng sự tinh tế sang trọng khi được bao phủ tông màu vàng nổi bật.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (22, N'CITIZEN NAM – QUARTZ (PIN) – DÂY KIM LOẠI (BI1050-56L)', 3285000, N'ym1PkxdoK0FpLM30r1cF', CAST(N'2022-12-05' AS Date), 1, 3, 1, 99, N'Mẫu đồng hồ Citizen BI1050-56L được thiết kế tinh tế với chất liệu thép không gỉ, mặt đồng hồ có nền xanh 2 tầng, có chữ số vạch lớn, và còn có 1 lịch ngày.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (23, N'CITIZEN C7 NH8394-70H – NAM – AUTOMATIC (TỰ ĐỘNG)', 9117000, N'xf9QVnbTXTBR3lDzhqpu', CAST(N'2022-12-03' AS Date), 1, 3, 1, 100, N'Mẫu Citizen C7 NH8394-70H phiên bản dây đeo demi thời trang phối cùng các chi tiết kim chỉ cùng cọc vạch số mạ vàng hồng sang trọng trên nền mặt số 40mm với họa tiết trải tia nhẹ.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (24, N'CITIZEN AK5000-54A – NAM – QUARTZ (PIN)', 3985000, N'jXO9keL4q0kK1R4kBYeA', CAST(N'2022-12-05' AS Date), 1, 3, 1, 98, N'Mẫu Citizen AK5000-54A phiên bản tính năng lịch trăng sao nổi bật trên nền mặt số size 42mm với họa tiết trải tia kiểu dáng trẻ trung.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (25, N'CITIZEN BM7370-89E – NAM – KÍNH SAPPHIRE', 8000000, N'PtKdbCyah67u9DqLZHNM', CAST(N'2022-12-01' AS Date), 1, 3, 1, 100, N'Mẫu Citizen BM7370-89E ấn tượng một vẻ ngoài mạnh mẽ với tổng thể vỏ máy cùng dây đeo bằng kim loại bao phủ tông màu bạc sang trọng hiện khi đồng hồ được trang bị công nghệ Eco-Drive (Năng Lượng Ánh Sáng).')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (26, N'CITIZEN NJ0080-50A – NAM – KÍNH SAPPHIRE', 11185000, N'ysmNkwEJGyEKEHgE2eil', CAST(N'2022-12-05' AS Date), 1, 3, 1, 100, N'Đồng hồ nam Citizen NJ0080-50A kích thước mặt số dày dặn với trải nghiệm bộ máy cơ dành cho phái nam, nổi bật với kim chỉ kiểu dáng mỏng tinh tế được phối tông màu xanh trẻ trung trên nền mặt số trắng trang nhã.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (27, N'CITIZEN EM0502-86P – NỮ – ECO-DRIVE (NĂNG LƯỢNG ÁNH SÁNG)', 6090000, N'SXhgzYa1vHPYxuH82YzK', CAST(N'2022-12-04' AS Date), 1, 3, 2, 100, N'Mẫu đồng hồ nữ Citizen EM0502-86P các vạch số được thiết kế gia công với kiểu dáng thanh mảnh nữ tính, ấn tượng với sự kết hợp giữa vỏ máy cùng dây đeo dạng lưới bằng kim loại mạ vàng khoác lên vẻ sang trọng.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (28, N'CITIZEN NỮ – ECO-DRIVE (NĂNG LƯỢNG ÁNH SÁNG) – DÂY KIM LOẠI (FE6014-59A)', 5520000, N'dT559EG6SUOzyNavNMPv', CAST(N'2022-12-05' AS Date), 1, 3, 2, 100, N'Đồng hồ Citizen FE6014-59A nữ thời trang sang trọng, mặt đồng hồ tròn màu trắng viền vàng , chữ số màu vàng, dây đeo demi thép không gỉ, 3 kim và 1 lịch ngày.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (29, N'CITIZEN NỮ – ECO-DRIVE (NĂNG LƯỢNG ÁNH SÁNG) – DÂY KIM LOẠI (EW1360-56A)', 6360000, N'MuLgcHAdZlkTya862B0y', CAST(N'2022-12-02' AS Date), 1, 3, 2, 100, N'Đồng hồ thuộc dòng Eco-Drive Citizen EW1360-56A, thiết kế độc đáo với mặt đồng hồ nền trắng, chữ số rõ ràng, vỏ và dây được làm bằng thép không gỉ, 3 kim chỉ, còn có 1 lịch ngày.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (30, N'CITIZEN NỮ – ECO-DRIVE (NĂNG LƯỢNG ÁNH SÁNG) – KÍNH SAPPHIRE – DÂY KIM LOẠI (EP5781-50A)', 6250000, N'c8bbHFczJRabQhg1kmSJ', CAST(N'2022-12-05' AS Date), 1, 3, 2, 100, N'Đồng hồ nữ Eco-Drive Citizen EP5781-50A, với thiết kế thời trang đơn giản tinh tế, mặt kính Sapphire chống trầy, 2 kim chỉ.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (31, N'CITIZEN NỮ – ECO-DRIVE (NĂNG LƯỢNG ÁNH SÁNG) – DÂY KIM LOẠI (EP5930-51A)', 5960000, N'LnHXhMGdOegXugUcVs6O', CAST(N'2022-12-04' AS Date), 1, 3, 2, 98, N'Đồng hồ Citizen EP5930-51A với kiểu dáng thiết kế tinh tế, mặt đồng hồ tròn nhỏ nhẹ nhàng, chất lượng thép không gỉ cao cấp, chữ số học đơn giản cùng 3 kim chỉ.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (32, N'CITIZEN EJ6082-51E – NỮ – QUARTZ (PIN) – DÂY KIM LOẠI', 3410000, N'53KzqYC1H0Ga5OsjSazc', CAST(N'2022-12-05' AS Date), 1, 3, 2, 100, N'Đồng hồ Citizen EJ6082-51E với mặt số tròn nhỏ nhắn, nền số màu đen quyến rũ kết nối cùng dây đeo kim loại mạ vàng tinh tế, kim chỉ và vạch số thanh mảnh nữ tính nổi bật.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (33, N'ORIENT NAM – QUARTZ (PIN) – KÍNH SAPPHIRE – DÂY DA (FGW0100AW0)', 4160000, N'E4cnXtjRVIJvGe9IhS9h', CAST(N'2022-12-02' AS Date), 1, 6, 1, 99, N'Đồng hồ dây da Orient FGW0100AW0 dành cho nam giới với mặt đồng hồ nền trắng 2 tầng, điểm nhấn nổi bật với phiên bản mặt kính Sapphire size 38mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (34, N'ORIENT FUG1R003W6 – NAM – QUARTZ (PIN) – DÂY DA', 4610000, N'DtHqAh0ug02UAtiRDRQR', CAST(N'2022-12-05' AS Date), 1, 6, 1, 100, N'Đồng hồ Orient FUG1R003W6 có mặt số tròn lớn với viền mỏng tinh tế bao quanh nền số màu trắng trang nhã, kim chỉ và vạch số mạ bạc được phủ phản quang tinh tế nổi bật, còn có lịch ngày lịch thứ ở vị trí 3h tiện dụng.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (35, N'ORIENT BAMBINO FAC00005W0 – NAM – AUTOMATIC (TỰ ĐỘNG) – MẶT SỐ 40MM, KÍNH CỨNG CONG, TRỮ CÓT 40 GIỜ', 7510000, N'0XfV4Mw4oGBGfxvOqRJp', CAST(N'2022-12-01' AS Date), 1, 6, 1, 98, N'Đồng hồ nam Orient FAC00005W0 với kiều dáng nam tính theo phong cách cổ điển, vỏ máy bằng thép không gỉ màu bạc, kết hợp cùng dây đeo da màu nâu tạo nên phong cách lịch lãm cho phái mạnh.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (36, N'ORIENT BAMBINO FAC08003A0 – NAM – AUTOMATIC (TỰ ĐỘNG) – MẶT SỐ 42MM, KÍNH CỨNG CONG, TRỮ CÓT 40 GIỜ', 7510000, N'7RtrzYnGOCEfZh5gFUes', CAST(N'2022-12-05' AS Date), 1, 6, 1, 100, N'Đồng hồ nam Orient FAC08003A0 thiết kế với phong cách cổ điển, kim chỉ và vạch số được phủ đồng trên nền màu xám mạnh mẽ, kết hợp với dây đeo bằng da màu nâu tạo nên vẻ lịch lãm cho phái mạnh.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (49, N'ORIENT CABALLERO FAG00002W0 – NAM – AUTOMATIC (TỰ ĐỘNG)', 8690000, N'byPmPHYzfd34KdNbxnAY', CAST(N'2022-12-05' AS Date), 1, 6, 1, 100, N'Mẫu đồng hồ nam Orient FAG00002W0 với phong cách cổ điển khi kết hợp giữa vỏ máy bằng kim loại mạ vàng ánh lên sự sang trọng phối cùng bộ dây đeo bằng da tông màu nâu lịch lãm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (57, N'ORIENT CABALLERO FAG00004D0 – NAM – AUTOMATIC (TỰ ĐỘNG)', 8050000, N'JeAO1eSE5rY9d3TrbLm1', CAST(N'2022-12-05' AS Date), 1, 6, 1, 99, N'Mẫu Orient FAG00004D0 mang lại cho phái mạnh một phong cách lịch lãm nổi bật trên nền mặt số xanh tông màu trẻ trung với ô chân kính phô ra 1 phần trải nghiệm của bộ máy cơ với kiểu thiết kế độc đáo đầy nam tính.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (60, N'ORIENT BAMBINO RA-AC0008S10B – NỮ – AUTOMATIC (TỰ ĐỘNG)', 9500000, N'4YQ3r7oPj6fgBSHHt1Z4', CAST(N'2022-12-05' AS Date), 1, 6, 2, 100, N'Mẫu Orient RA-AC0008S10B vàng hồng thời trang với mẫu dây đeo demi cùng các chi tiết vạch số kim chỉ được tạo nét mỏng trẻ trung trên nền mặt trắng size 36mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (61, N'ORIENT RF-QA0004B10B – NỮ – QUARTZ (PIN) – DÂY DA', 3350000, N'SD4uukcm3z3JmjNgLm6o', CAST(N'2022-12-05' AS Date), 1, 6, 2, 100, N'Mẫu Orient RF-QA0004B10B thiết kế mỏng trẻ trung với phần vỏ máy pin chỉ 6mm, mặt số đơn giản size 30mm với kiểu dáng 3 kim.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (62, N'ORIENT RF-QA0002B10B – NỮ – QUARTZ (PIN) – DÂY DA', 3890000, N'DGNTCNcysVNYv66EsmlE', CAST(N'2022-12-02' AS Date), 1, 6, 2, 100, N'Mẫu Orient RF-QA0002B10B thiết kế đơn giản 3 kim cùng các chi tiết vạch số tạo nét mỏng mạ vàng sang trọng trẻ trung trên mặt số size 30mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (63, N'ORIENT RF-QA0008S10B – NỮ – QUARTZ (PIN) – DÂY DA', 3350000, N'RjUVrKwqEalqMEQjIEqk', CAST(N'2022-12-05' AS Date), 1, 6, 2, 100, N'Mẫu Orient RF-QA0008S10B phiên bản cọc sốla mã tạo dáng mỏng cách tân tên mặt số size 30mm thời trang thanh lịch phối cùng bộ dây da có vân.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (64, N'ORIENT RF-QA0005L10B – NỮ – QUARTZ (PIN) – DÂY DA', 3350000, N'fWjjESA5s6qAZDjuw3Zs', CAST(N'2022-12-05' AS Date), 1, 6, 2, 99, N'Mẫu Orient RF-QA0005L10B đơn giản trẻ trung kiểu dáng 3 kim 1 lịch trên mặt số size 30mm, vỏ máy pin thiết kế mỏng nổi bật chỉ 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (65, N'ORIENT RF-QA0001S10B – NỮ – QUARTZ (PIN) – DÂY DA', 3890000, N'hhA7wFC70FOjcD1Ma1BW', CAST(N'2022-12-05' AS Date), 1, 6, 2, 100, N'Mẫu Orient RF-QA0001S10B sang trọng không kém cạnh trẻ trung, vỏ máy mạ tone màu vàng hồng cùng với thiết kế mỏng nữ tính kiểu máy pin chỉ 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (66, N'SR SG8885.1101AT – NAM – KÍNH SAPPHIRE – AUTOMATIC (TỰ ĐỘNG) – DÂY KIM LOẠI – MẶT SỐ 40MM', 3900000, N'YGWTvnrA7hU6tYNd1SK4', CAST(N'2022-12-03' AS Date), 1, 2, 1, 100, N'Mẫu SR SG8885.1101AT phiên bản nắp lưng bằng kính với thiết kế độc đáo lộ ra bộ máy cơ, các chi tiết cọc vạch số tạo hình lưỡi kiếm được bạc với vẻ ngoài sang trọng.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (67, N'SR SG1056.4602TE – NAM – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY DA – MẶT SỐ 39MM', 1950000, N'Lo2QtUALq1fB580atoHx', CAST(N'2022-12-05' AS Date), 1, 2, 1, 100, N'Mẫu SR SG1056.4602TE phiên bản mặt kính chất liệu Sapphire, nổi bật với thiết kế họa tiết Guilloche tạo nên vẻ thời trang nam tính trên nền mặt số trắng size 40mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (68, N'SR SG1074.1201TE – NAM – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 40MM', 2000000, N'GSqQQB7zixYXFP4Dw5PS', CAST(N'2022-12-04' AS Date), 1, 2, 1, 100, N'Mẫu SR SG1074.1201TE phiên bản các chi tiết kim chỉ cùng với nền cọc số la mã tạo hình mỏng trẻ trung trên nền mặt số size 40mm với thiết kế họa tiết Guiloche.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (69, N'SR SG1085.1402 – NAM – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 40MM', 2650000, N'U6Ci7vPOhbBiqISBcP1L', CAST(N'2022-12-05' AS Date), 1, 2, 1, 100, N'Mẫu SR SG1085.1402 dây đeo kim loại phiên bản dây lưới mạ tone màu vàng nhạt phong cách thời trang đi kèm lối thiết kế trẻ trung với phần vỏ máy pin siêu mỏng chỉ dày 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (70, N'SR SG1085.1102 – NAM – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 40MM', 2450000, N'HROSaLTdfYJHtrdKvZ1H', CAST(N'2022-12-04' AS Date), 1, 2, 1, 100, N'Mẫu SR SG1085.1102 dây đeo kim loại phiên bản dây lưới bạc thời trang đi kèm với kiểu thiết kế trẻ trung phần vỏ máy pin siêu mỏng chỉ dày 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (71, N'SR SG2087.1101 – NAM – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 39MM', 2690000, N'50fgdXruro6PMfIphWk8', CAST(N'2022-12-05' AS Date), 1, 2, 1, 100, N'Mẫu SR SG2087.1101 dây đeo kim loại phiên bản dây lưới bạc thời trang kết hợp cùng với thiết kế mỏng của phần vỏ máy pin chỉ dày 7mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (72, N'SR SL80071.1402CF – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 30MM', 2450000, N'RrThRuM7eMutpNS4CuMn', CAST(N'2022-12-05' AS Date), 1, 2, 2, 100, N'Mẫu SR SL80071.1402CF phiên bản mạ vàng sang trọng với phần dây vỏ mạ tone màu vàng nhạt phong cách thời trang, điểm nhấn nổi bật với phần vỏ máy pin được thiết kế siêu mỏng với độ dày chỉ 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (73, N'SR SL80081.1401CF – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 30MM', 2450000, N'vUjVNwkkbuIOlAgP0ZCP', CAST(N'2022-12-04' AS Date), 1, 2, 2, 100, N'Mẫu SR SL80081.1401CF phiên bản mạ vàng sang trọng với phần dây vỏ mạ tone màu vàng nhạt phong cách thời trang, điểm nhấn nổi bật với phần vỏ máy pin được thiết kế siêu mỏng với độ dày chỉ 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (74, N'SR SL80081.1402CF – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI – MẶT SỐ 30MM', 2450000, N'bwiD0Rbedkhv4sAc4VpV', CAST(N'2022-12-05' AS Date), 1, 2, 2, 100, N'Mẫu SR SL80081.1402CF phiên bản mạ vàng sang trọng với phần dây vỏ mạ tone màu vàng nhạt phong cách thời trang, điểm nhấn nổi bật với phần vỏ máy pin được thiết kế siêu mỏng với độ dày chỉ 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (75, N'SR SL80070.4601CF – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY DA – MẶT SỐ 30MM', 2150000, N'2XSKsew1B6xAEB97Yffx', CAST(N'2022-12-05' AS Date), 1, 2, 2, 100, N'Mẫu SR SL80070.4601CF dây da phiên bản da tạo hình vân nổi bật lên vẻ ngoài thanh lịch, điểm nhấn nổi bật với phần vỏ máy pin được thiết kế kiểu dáng siêu mỏng chỉ dày 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (76, N'SR SL80070.4602CF – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY DA – MẶT SỐ 30MM', 2150000, N'nEgoro0YvmBJRVboOJyo', CAST(N'2022-12-04' AS Date), 1, 2, 2, 100, N'Mẫu SR SL80070.4602CF dây da phiên bản da tạo hình vân nổi được phối tone màu nâu đỏ, điểm nhấn nổi bật với phần vỏ máy pin được thiết kế kiểu dáng siêu mỏng chỉ dày 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (77, N'SR SL80060.4601CF – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY DA – MẶT SỐ 30MM', 2150000, N'cxG1tDV0CfxwOTdMXpmO', CAST(N'2022-12-05' AS Date), 1, 2, 2, 100, N'Mẫu SR SL80060.4601CF dây da phiên bản da tạo hình vân nổi bật lên vẻ ngoài thanh lịch, điểm nhấn nổi bật với phần vỏ máy pin được thiết kế kiểu dáng siêu mỏng chỉ dày 6mm.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (78, N'Casio MTP-V004L-1B2UDF – Nam – Quartz (Pin) – Dây Da – Mặt Số 41.5mm', 803000, N'KqcqwfyuLjvPVu380VXy', CAST(N'2022-12-06' AS Date), 1, 1, 1, 99, N'Mẫu Casio MTP-V004L-1B2UDF mặt số đen size 41mm thiết kế đơn giản trẻ trung 3 kim, phối cùng bộ dây da nâu phiên bản da trơn thời trang.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (81, N'CASIO ECB-900DB-1BDR – NAM – SOLAR (NĂNG LƯỢNG ÁNH SÁNG) – DÂY KIM LOẠI', 6909000, N'IPRHGkDk10tJcmfGC5P5', CAST(N'2022-12-06' AS Date), 1, 1, 1, 100, N'Mẫu Casio ECB-900DB-1BDR tính năng vượt trội pin được trang bị công nghệ Solar (Năng lượng ánh sáng), Edifice phiên bản đặc biệt mặt số kim chỉ kết hợp ô số điện tử.')
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description]) VALUES (82, N'DOXA D174TWH – NỮ – KÍNH SAPPHIRE – QUARTZ (PIN) – DÂY KIM LOẠI', 18720000, N'4q8j484AZxtDTBRCN3q0', CAST(N'2022-12-06' AS Date), 1, 1, 2, 99, N'Mẫu Doxa D174TWH phiên bản đính 8 viên kim cương trên nền mặt số trắng size 29mm có họa tiết Guilloché thời trang sang trọng cho phái đẹp.')
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
INSERT [dbo].[Subcategory] ([Subcategoryid], [Name]) VALUES (1, N'Nam       ')
INSERT [dbo].[Subcategory] ([Subcategoryid], [Name]) VALUES (2, N'Nữ        ')
GO
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'BaoDG', N'$2a$10$V45lqu9K8tjWweAVE6GfV.C.8CFg9FJjYkxqGJyWWwKhSBcPnL5Da', N'Dương Gia Bảo', N'0912345678', N'baodgpc01660@fpt.edu.vn', 1, 1)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'BaoDG22', N'$2a$10$hG429h0qe0QesTYe7kqWmONaDOkNUMA1YRH0mgwMbUmGp59B/ctZa', N'bao', N'0343545455', N'holy01@gmail.com', 1, 0)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'HoLy', N'$2a$10$.me31xemWy1mEQoSlm7RCOV1ffBiigynY253p7dOWG7NrDQVo.0GW', N'HoLy Dương', N'0927373737', N'holygb0206@gmail.com', 1, 0)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'HoLy2', N'$2a$10$8lHsM5rbD5N6/Y0KYiJYPOT1YS6nVeIojtt2iG0mnEpyh/ZmHYtNu', N'HoLy Dương', N'0947268364', N'duonggiabaobao87@gmail.com', 1, 1)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'Trần Thành Trung', N'184a501a3f3', N'Trần Thành Trung', N'NULL', N'trungttpc01815@fpt.edu.vn', 1, 0)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'Trung Tran', N'184a8dc1834', N'Trung Tran', N'NULL', N'tranthanhtrung31122002@gmail.com', 1, 0)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'Trungtt', N'$2a$10$dEcGAgkkYpRYmhKwvFFABeI0oaA9yTNJ0Vj3t1swcpJoO9lj1WUnC', N'123', N'0817680525', N'tranthanhtrung@gmail.com', 1, 1)
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_Carts_Products] FOREIGN KEY([idProduct])
REFERENCES [dbo].[Products] ([Productid])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_Carts_Products]
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK721667qhda2osq1rlp4hmya6g] FOREIGN KEY([Userid])
REFERENCES [dbo].[Users] ([Userid])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK721667qhda2osq1rlp4hmya6g]
GO
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_ContactUs_Users] FOREIGN KEY([Userid])
REFERENCES [dbo].[Users] ([Userid])
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_ContactUs_Users]
GO
ALTER TABLE [dbo].[Orderdetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders1] FOREIGN KEY([Orderid])
REFERENCES [dbo].[Orders] ([Orderid])
GO
ALTER TABLE [dbo].[Orderdetails] CHECK CONSTRAINT [FK_OrderDetails_Orders1]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users1] FOREIGN KEY([Userid])
REFERENCES [dbo].[Users] ([Userid])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users1]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Subcategory] FOREIGN KEY([Subcategoryid])
REFERENCES [dbo].[Subcategory] ([Subcategoryid])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Subcategory]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK75yb5rxb7ny2vkl0aqy20slvh] FOREIGN KEY([Categoryid])
REFERENCES [dbo].[Category] ([Categoryid])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK75yb5rxb7ny2vkl0aqy20slvh]
GO
USE [master]
GO
ALTER DATABASE [Normal_DATN] SET  READ_WRITE 
GO
