USE [master]
GO
/****** Object:  Database [Normal_DATN]    Script Date: 11/22/2022 1:38:25 PM ******/
CREATE DATABASE [Normal_DATN]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Normal_DATN', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Normal_DATN.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Normal_DATN_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Normal_DATN_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Normal_DATN] SET COMPATIBILITY_LEVEL = 150
GO
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
/****** Object:  Table [dbo].[Carts]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[Cartid] [bigint] IDENTITY(1,1) NOT NULL,
	[Userid] [nvarchar](20) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
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
/****** Object:  Table [dbo].[Category]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Categoryid] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Categoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[Commentid] [nvarchar](36) NOT NULL,
	[Id_product] [bigint] NULL,
	[Id_user] [nvarchar](20) NULL,
	[Contents] [nvarchar](1000) NULL,
	[Date] [date] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[Commentid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Orderdetailid] [int] IDENTITY(1,1) NOT NULL,
	[Orderid] [int] NOT NULL,
	[Productid] [bigint] NOT NULL,
	[Image] [nvarchar](30) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[Quanlity] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Orderdetailid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Orderid] [int] IDENTITY(1,1) NOT NULL,
	[Userid] [nvarchar](20) NULL,
	[Date] [datetime] NULL,
	[Telephone] [nvarchar](15) NULL,
	[Address] [nvarchar](100) NULL,
	[Amount] [float] NULL,
	[Description] [nvarchar](max) NULL,
	[Status] [nvarchar](15) NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Orderid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Productid] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[Date] [date] NOT NULL,
	[Available] [bit] NOT NULL,
	[Categoryid] [bigint] NOT NULL,
	[Subcategoryid] [int] NULL,
	[Quantity] [int] NOT NULL,
	[Description] [nvarchar](max) NOT NULL,
	[Discount] [float] NULL,
	[Viewcount] [int] NULL,
	[Special] [bit] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subcategory]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subcategory](
	[Subcategoryid] [int] NOT NULL,
	[Name] [nchar](10) NULL,
 CONSTRAINT [PK_Subcategory] PRIMARY KEY CLUSTERED 
(
	[Subcategoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 11/22/2022 1:38:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Userid] [nvarchar](20) NOT NULL,
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
SET IDENTITY_INSERT [dbo].[Carts] ON 

INSERT [dbo].[Carts] ([Cartid], [Userid], [Name], [Price], [Quanlity], [Img], [idProduct]) VALUES (2, N'BaoDG', N'Casio Nam W-218H-1AVDF ', 520000, 10, N'4.jpg', 3)
INSERT [dbo].[Carts] ([Cartid], [Userid], [Name], [Price], [Quanlity], [Img], [idProduct]) VALUES (3, N'BaoDG', N'Casio Nam W-218H-5AVDF', 120000, 8, N'3.jpg', 6)
INSERT [dbo].[Carts] ([Cartid], [Userid], [Name], [Price], [Quanlity], [Img], [idProduct]) VALUES (30023, N'HoLy', N'Casio Nam W-218H-1AVDF ', 520000, 2, N'4.jpg', 3)
SET IDENTITY_INSERT [dbo].[Carts] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (1, N'Casio')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (2, N'Rolex')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (3, N'CitiZen')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (6, N'Ori')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (7, N'ORI2')
INSERT [dbo].[Category] ([Categoryid], [Name]) VALUES (8, N'ỎI4')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetails] ON 

INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (1, 1004, 1, N'1.jpg', N'Casio Nam AE-1200WHD', 500000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (2, 1004, 3, N'4.jpg', N'Casio Nam W-218H-1AVDF ', 520000, 2)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (3, 1004, 6, N'3.jpg', N'Casio Nam W-218H-5AVDF', 120000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (4, 1004, 7, N'4.jpg', N'Casio Nam W-218H-6AVDF', 120000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (5, 1005, 1, N'1.jpg', N'Casio Nam AE-1200WHD', 500000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (6, 1005, 3, N'4.jpg', N'Casio Nam W-218H-1AVDF ', 520000, 2)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (7, 1005, 6, N'3.jpg', N'Casio Nam W-218H-5AVDF', 120000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (8, 1005, 7, N'4.jpg', N'Casio Nam W-218H-6AVDF', 120000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (1002, 2002, 3, N'4.jpg', N'Casio Nam W-218H-1AVDF ', 520000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (1003, 2002, 1, N'1.jpg', N'Casio Nam AE-1200WHD', 500000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (1004, 2002, 6, N'3.jpg', N'Casio Nam W-218H-5AVDF', 120000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (1005, 3003, 3, N'4.jpg', N'Casio Nam W-218H-1AVDF ', 520000, 1)
INSERT [dbo].[OrderDetails] ([Orderdetailid], [Orderid], [Productid], [Image], [Name], [Price], [Quanlity]) VALUES (1006, 3003, 6, N'3.jpg', N'Casio Nam W-218H-5AVDF', 120000, 1)
SET IDENTITY_INSERT [dbo].[OrderDetails] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (1004, N'HoLy', CAST(N'2022-10-31T20:17:22.557' AS DateTime), N'0989144730', N'Gò Vấp, TP Hồ Chí Minh', 1780000, N'Con cú cẹc cjec', N'Cancelled')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (1005, N'HoLy', CAST(N'2022-10-31T20:30:55.510' AS DateTime), N'0989144730', N'Cần Thơ', 1780000, N'aaaaaaassssssasa', N'Cancelled')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (2002, N'HoLy', CAST(N'2022-11-06T17:44:09.923' AS DateTime), N'0927373737', N'CT', 1140000, N'aaaaaaaaaaaaaa', N'Ordering')
INSERT [dbo].[Orders] ([Orderid], [Userid], [Date], [Telephone], [Address], [Amount], [Description], [Status]) VALUES (3003, N'HoLy2', CAST(N'2022-11-21T00:00:00.000' AS DateTime), N'0947268364', N'Định Bình Cà Mau', 670000, N'Xa vãi ò', N'Ordering')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description], [Discount], [Viewcount], [Special]) VALUES (1, N'Casio Nam AE-1200WHD', 500000, N'1.jpg', CAST(N'2022-10-10' AS Date), 1, 1, 1, 100, N'ĐẸP', 0, 0, 0)
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description], [Discount], [Viewcount], [Special]) VALUES (3, N'Casio Nam W-218H-1AVDF ', 520000, N'4.jpg', CAST(N'2021-03-02' AS Date), 1, 1, 1, 100, N'ĐẸP', 0, 0, 0)
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description], [Discount], [Viewcount], [Special]) VALUES (6, N'Casio Nam W-218H-5AVDF', 120000, N'3.jpg', CAST(N'2022-04-11' AS Date), 1, 1, 1, 100, N'ĐẸP', 0, 0, 0)
INSERT [dbo].[Products] ([Productid], [Name], [Price], [Image], [Date], [Available], [Categoryid], [Subcategoryid], [Quantity], [Description], [Discount], [Viewcount], [Special]) VALUES (7, N'Casio Nam W-218H-6AVDF', 120000, N'4.jpg', CAST(N'2022-05-15' AS Date), 1, 1, 1, 100, N'ĐẸP', 0, 0, 0)
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
INSERT [dbo].[Subcategory] ([Subcategoryid], [Name]) VALUES (1, N'Nam       ')
INSERT [dbo].[Subcategory] ([Subcategoryid], [Name]) VALUES (2, N'Nữ        ')
GO
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'BaoDG', N'$2a$10$jA9Uhc54d.6HHBrqFdv/ReweKU1oq6AZAR0MmxaOUpJzVgZwjLQYu', N'Dương Gia Bảo', N'0919119119', N'baodgpc01660@fpt.edu.vn', 0, 1)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'BaoDG22', N'$2a$10$6p69wIHYZCOp94YbXdPCO.eQfM4bpzyN4/tbickhvZrQ9tTOk6lxS', N'bao', N'034354545', N'holy01@gmail.com', 1, 0)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'HoLy', N'$2a$10$ARKp6nqzWqdhXbkbZRr09uHvoyJRa.tsU4iMnQkC1qk16Fl9raCJ2', N'HoLy Dương', N'0927373737', N'holygb0206@gmail.com', 0, 0)
INSERT [dbo].[Users] ([Userid], [Password], [FullName], [Phone], [Email], [Activated], [Admin]) VALUES (N'HoLy2', N'$2a$10$dOvF5Pj2Adp/h14zmXBIe.ziBUfJG8i81PBi0Xe0QqA4leFa.JA6m', N'HoLy Dương', N'0947268364', N'duonggiabaobao87@gmail.com', 0, 1)
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
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Products1] FOREIGN KEY([Id_product])
REFERENCES [dbo].[Products] ([Productid])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Products1]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Users] FOREIGN KEY([Id_user])
REFERENCES [dbo].[Users] ([Userid])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Users]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders1] FOREIGN KEY([Orderid])
REFERENCES [dbo].[Orders] ([Orderid])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders1]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([Productid])
REFERENCES [dbo].[Products] ([Productid])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
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
