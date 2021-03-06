USE [Assigment3_LeAnhNguyen]
GO
/****** Object:  Table [dbo].[carTBL]    Script Date: 3/23/2021 2:54:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carTBL](
	[carID] [nchar](20) NOT NULL,
	[carName] [nchar](50) NULL,
	[color] [nchar](20) NULL,
	[year] [nchar](10) NULL,
	[category] [nchar](20) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[image] [nchar](1000) NULL,
	[quantitySave] [int] NULL,
	[biensoxe] [nchar](10) NOT NULL,
 CONSTRAINT [PK_carTBL] PRIMARY KEY CLUSTERED 
(
	[biensoxe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[discountTBL]    Script Date: 3/23/2021 2:54:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[discountTBL](
	[codeDiscount] [int] NOT NULL,
	[startDay] [nchar](50) NULL,
	[expiryDay] [nchar](50) NULL,
 CONSTRAINT [PK_discountTBL] PRIMARY KEY CLUSTERED 
(
	[codeDiscount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderDetailsTBL]    Script Date: 3/23/2021 2:54:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderDetailsTBL](
	[orderID] [int] NOT NULL,
	[carID] [nchar](20) NOT NULL,
	[carName] [nchar](100) NULL,
	[rentalDate] [nchar](100) NULL,
	[returnDate] [nchar](100) NULL,
	[status] [nchar](10) NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_orderDetailsTBL] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC,
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderTBL]    Script Date: 3/23/2021 2:54:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderTBL](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [nchar](20) NULL,
	[createDate] [nchar](100) NULL,
	[payment] [nchar](30) NULL,
	[totalPrice] [float] NULL,
	[status] [nchar](10) NULL,
	[codeDiscount] [int] NULL,
	[checkOrder] [int] NULL,
 CONSTRAINT [PK_orderTBL] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rentalHistoryTBL]    Script Date: 3/23/2021 2:54:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rentalHistoryTBL](
	[idHistory] [int] IDENTITY(1,1) NOT NULL,
	[userID] [nchar](20) NULL,
	[dateOrder] [nchar](50) NULL,
	[totalPrice] [float] NULL,
	[payment] [nchar](20) NULL,
	[orderID] [int] NULL,
	[status] [nchar](20) NULL,
 CONSTRAINT [PK_rentalHistoryTBL] PRIMARY KEY CLUSTERED 
(
	[idHistory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[userAccountTBL]    Script Date: 3/23/2021 2:54:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[userAccountTBL](
	[userID] [nchar](20) NOT NULL,
	[userPassword] [nchar](50) NULL,
	[phone] [nchar](20) NULL,
	[userName] [nchar](20) NULL,
	[address] [nchar](100) NULL,
	[createDate] [nchar](50) NULL,
	[status] [nchar](10) NULL,
	[role] [nchar](10) NULL,
 CONSTRAINT [PK_useraccountTBL] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E001                ', N'McLaren P1                                        ', N'red                 ', N'2019      ', N'Chervolet           ', 319, 10, N'https://i.ndh.vn/2012/09/30/4563533-1348969080.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ', 10, N'lan1      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E010                ', N'Pagani Huayra BC                                  ', N'black               ', N'2021      ', N'Lamborghini         ', 650, 10, N'http://files.insidercarnews.com/files/2016/01/Pagani-Huayra-BC-rendering.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ', 10, N'lan10     ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E002                ', N' Zenvo TS1 GT                                     ', N'blue                ', N'2020      ', N'Chervolet           ', 150, 10, N'https://wallpaperaccess.com/thumb/4474957.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ', 10, N'lan2      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E003                ', N'Koenigsegg One                                    ', N'white               ', N'2018      ', N'Chervolet           ', 450, 10, N'https://xe360.vn/cache/images/content/b2fd6cd47dea4c4a26d10519d03a8127_w600_h400_cp.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ', 10, N'lan3      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E004                ', N'McLaren Speedtail                                 ', N'siver               ', N'2021      ', N'Chervolet           ', 330, 10, N'https://gulfbusiness.com/wp-content/uploads/2018/11/mclaren-speedtail-2.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ', 10, N'lan4      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E005                ', N'Lamborghini Sesto                                 ', N'black               ', N'2021      ', N'Lamborghini         ', 500, 10, N'https://i.pinimg.com/736x/63/74/01/637401a5b4f2207018a5fad496ba8149.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ', 10, N'lan5      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E006                ', N'Aston Martin Vulcan                               ', N'black               ', N'2016      ', N'Cherovlet           ', 340, 10, N'https://d2pa5gi5n2e1an.cloudfront.net/ph/images/article/Astonmartin_Vulcan/am-5.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ', 10, N'lan6      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E007                ', N'Aston Martin Valkyrie                             ', N'blue                ', N'2013      ', N'Lamborghini         ', 700, 10, N'https://photo-cms-anninhthudo.zadn.vn/w600/Uploaded/2021/189/2019_03_12/1/dd_9.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ', 10, N'lan7      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E008                ', N'Koenigsegg Jesko                                  ', N'white               ', N'2020      ', N'Lamborghini         ', 320, 10, N'https://muaxegiatot.vn/wp-content/uploads/2019/03/Gia-xe-KOENIGSEGG-JESGia-xe-KO-Muaxegiatot-vn.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ', 10, N'lan8      ')
INSERT [dbo].[carTBL] ([carID], [carName], [color], [year], [category], [price], [quantity], [image], [quantitySave], [biensoxe]) VALUES (N'E009                ', N'Lykan Hypersport                                  ', N'white               ', N'2021      ', N'Lamborghini         ', 410, 10, N'https://cdn.thecoolist.com/wp-content/uploads/2013/02/W-Motors-Lykan-Hypersport-7.jpg                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ', 10, N'lan9      ')
GO
INSERT [dbo].[discountTBL] ([codeDiscount], [startDay], [expiryDay]) VALUES (113, N'2021-03-5                                         ', N'2021-03-10                                        ')
GO
INSERT [dbo].[orderDetailsTBL] ([orderID], [carID], [carName], [rentalDate], [returnDate], [status], [quantity]) VALUES (608160, N'E004                ', N'McLaren Speedtail                                                                                   ', N'2021-03-24                                                                                          ', N'2021-03-25                                                                                          ', N'active    ', 3)
INSERT [dbo].[orderDetailsTBL] ([orderID], [carID], [carName], [rentalDate], [returnDate], [status], [quantity]) VALUES (608163, N'E004                ', N'McLaren Speedtail                                                                                   ', N'2021-03-26                                                                                          ', N'2021-03-27                                                                                          ', N'active    ', 1)
GO
SET IDENTITY_INSERT [dbo].[orderTBL] ON 

INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608159, N'nguyen              ', N'2021-03-13                                                                                          ', N'Cash                          ', 1510, N'active    ', 113, 686249)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608160, N'nguyen              ', N'2021-03-13                                                                                          ', N'Cash                          ', 1520, N'active    ', 113, 319291)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608161, N'1                   ', N'2021-03-13                                                                                          ', N'Cash                          ', 170, N'active    ', 113, 754498)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608162, N'1                   ', N'2021-03-13                                                                                          ', N'Cash                          ', 760, N'inactive  ', 113, 286267)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608163, N'1                   ', N'2021-03-13                                                                                          ', N'Cash                          ', 900, N'active    ', 113, 871927)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608164, N'1                   ', N'2021-03-13                                                                                          ', N'Cash                          ', 890, N'active    ', 113, 795794)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608165, N'1                   ', N'2021-03-13                                                                                          ', N'Cash                          ', 967, N'active    ', 113, 801148)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608166, N'1                   ', N'2021-03-13                                                                                          ', N'Cash                          ', 1360, N'active    ', 113, 906800)
INSERT [dbo].[orderTBL] ([orderID], [userID], [createDate], [payment], [totalPrice], [status], [codeDiscount], [checkOrder]) VALUES (608167, N'nguyen              ', N'2021-03-16                                                                                          ', N'Cash                          ', 180, N'active    ', 113, 598842)
SET IDENTITY_INSERT [dbo].[orderTBL] OFF
GO
SET IDENTITY_INSERT [dbo].[rentalHistoryTBL] ON 

INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (66, N'nguyen              ', N'2021-03-11                                        ', 1000, N'Cash                ', 608145, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (67, N'nguyen              ', N'2021-03-11                                        ', 360, N'Cash                ', 608146, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (68, N'nguyen              ', N'2021-03-11                                        ', 2510, N'Cash                ', 608147, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (69, N'nguyen              ', N'2021-03-11                                        ', 1510, N'Cash                ', 608148, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (70, N'nguyen              ', N'2021-03-11                                        ', 2060, N'Cash                ', 608149, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (71, N'nguyen              ', N'2021-03-11                                        ', 1240, N'Cash                ', 608150, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (72, N'nguyen              ', N'2021-03-11                                        ', 760, N'Cash                ', 608151, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (73, N'nguyen              ', N'2021-03-11                                        ', 1605, N'Cash                ', 608152, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (74, N'nguyen              ', N'2021-03-12                                        ', 1710, N'Cash                ', 608153, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (75, N'nguyen              ', N'2021-03-12                                        ', 380, N'Cash                ', 608154, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (76, N'nguyen              ', N'2021-03-12                                        ', 1660, N'Cash                ', 608155, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (77, N'nguyen              ', N'2021-03-12                                        ', 3510, N'Cash                ', 608156, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (78, N'nguyen              ', N'2021-03-12                                        ', 1240, N'Cash                ', 608157, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (79, N'nguyen              ', N'2021-03-12                                        ', 1510, N'Cash                ', 608158, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (80, N'nguyen              ', N'2021-03-13                                        ', 1520, N'Cash                ', 608160, N'active              ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (81, N'1                   ', N'2021-03-13                                        ', 170, N'Cash                ', 608161, N'active              ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (82, N'1                   ', N'2021-03-13                                        ', 760, N'Cash                ', 608162, N'inactive            ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (83, N'1                   ', N'2021-03-13                                        ', 900, N'Cash                ', 608163, N'active              ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (84, N'1                   ', N'2021-03-13                                        ', 890, N'Cash                ', 608164, N'active              ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (85, N'1                   ', N'2021-03-13                                        ', 967, N'Cash                ', 608165, N'active              ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (86, N'1                   ', N'2021-03-13                                        ', 1360, N'Cash                ', 608166, N'active              ')
INSERT [dbo].[rentalHistoryTBL] ([idHistory], [userID], [dateOrder], [totalPrice], [payment], [orderID], [status]) VALUES (87, N'nguyen              ', N'2021-03-16                                        ', 180, N'Cash                ', 608167, N'active              ')
SET IDENTITY_INSERT [dbo].[rentalHistoryTBL] OFF
GO
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'1                   ', N'1                                                 ', N'1111111111          ', N'1                   ', N'1                                                                                                   ', N'03-13-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'admin               ', N'123                                               ', N'0977879837          ', N'admin               ', N'QuyNhon                                                                                             ', N'1/21/2021                                         ', N'active    ', N'admin     ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Bao                 ', N'123                                               ', N'0977879837          ', N'Gia Bao             ', N'Binh Dinh                                                                                           ', N'03-03-2021                                        ', N'new       ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Chi                 ', N'123                                               ', N'0977879837          ', N'ThuyCHi             ', N'Binh Dinh                                                                                           ', N'03-04-2021                                        ', N'new       ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'E001                ', N'123                                               ', N'0977879837          ', N'asd                 ', N'Binh Dinh                                                                                           ', N'03-03-2021                                        ', N'new       ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'E003                ', N'123                                               ', N'0977879837          ', N'Nam                 ', N'Binh Dinh                                                                                           ', N'03-04-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'E1000               ', N'123                                               ', N'0977879837          ', N'Nam Coa             ', N'Binh Dinh                                                                                           ', N'03-04-2021                                        ', N'new       ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'E500                ', N'123                                               ', N'0977879837          ', N'asd                 ', N'Binh Dinh                                                                                           ', N'03-04-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Hung                ', N'123                                               ', N'0977879837          ', N'Quoc Hung           ', N'Binh Dinh                                                                                           ', N'03-03-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Mai                 ', N'123                                               ', N'0977879837          ', N'Mai Dao             ', N'Binh Dinh                                                                                           ', N'03-04-2021                                        ', N'new       ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'mom                 ', N'123                                               ', N'0977879837          ', N'CongNam             ', N'Binh Dinh                                                                                           ', N'03-04-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'nam                 ', N'123                                               ', N'0977879837          ', N'LeHoaiNam           ', N'Ha Noi                                                                                              ', N'02-26-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'nguyen              ', N'123                                               ', N'0921576507          ', N'leanhnguyen         ', N'319TranPhu                                                                                          ', N'2/23/2021                                         ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Nhi                 ', N'123                                               ', N'0977879837          ', N'Thanh Nhi           ', N'Binh Dinh                                                                                           ', N'03-09-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'sa                  ', N'123                                               ', N'0977879837          ', N'ChauSa              ', N'Binh Dinh                                                                                           ', N'03-07-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Thanh               ', N'123                                               ', N'0977879837          ', N'Xi Thanh            ', N'Tran Phu                                                                                            ', N'03-07-2021                                        ', N'active    ', N'customer  ')
INSERT [dbo].[userAccountTBL] ([userID], [userPassword], [phone], [userName], [address], [createDate], [status], [role]) VALUES (N'Y                   ', N'123                                               ', N'0977879837          ', N'Bui Nhu Y           ', N'Binh Dinh                                                                                           ', N'03-09-2021                                        ', N'active    ', N'customer  ')
GO
ALTER TABLE [dbo].[orderTBL]  WITH CHECK ADD  CONSTRAINT [FK_orderTBL_discountTBL] FOREIGN KEY([codeDiscount])
REFERENCES [dbo].[discountTBL] ([codeDiscount])
GO
ALTER TABLE [dbo].[orderTBL] CHECK CONSTRAINT [FK_orderTBL_discountTBL]
GO
ALTER TABLE [dbo].[orderTBL]  WITH CHECK ADD  CONSTRAINT [FK_orderTBL_userAccountTBL] FOREIGN KEY([userID])
REFERENCES [dbo].[userAccountTBL] ([userID])
GO
ALTER TABLE [dbo].[orderTBL] CHECK CONSTRAINT [FK_orderTBL_userAccountTBL]
GO
