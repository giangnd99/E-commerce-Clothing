-- Dữ liệu cho bảng category (3 bản ghi)
INSERT INTO category (name) VALUES
                                ('Áo thun'),
                                ('Quần jean'),
                                ('Giày thể thao');

-- Dữ liệu cho bảng users (10 bản ghi) Pass: 123
INSERT INTO users (avatar, birthday, email, full_name, gender, password, phone, role) VALUES
                                                                                          ('user1.jpg', '1995-04-15', 'hoang.an@gmail.com', 'Hoàng Văn An', 1, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0987123456', 1),
                                                                                          ('user2.jpg', '2000-08-20', 'pham.mai@gmail.com', 'Phạm Thị Mai', 0, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0914234567', 0),
                                                                                          ('user3.jpg', '1998-11-03', 'le.tuan@gmail.com', 'Lê Tuấn Kiệt', 1, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0978543210', 1),
                                                                                          ('user4.jpg', '1996-06-25', 'tran.hang@gmail.com', 'Trần Thị Hằng', 0, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0965321478', 0),
                                                                                          ('user5.jpg', '2002-01-10', 'nguyen.binh@gmail.com', 'Nguyễn Bình Minh', 1, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0945678912', 0),
                                                                                          ('user6.jpg', '1997-09-14', 'dang.hoa@gmail.com', 'Đặng Văn Hòa', 1, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0931234567', 1),
                                                                                          ('user7.jpg', '1999-12-30', 'vo.quyen@gmail.com', 'Võ Thị Quyên', 0, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0923344556', 0),
                                                                                          ('user8.jpg', '2001-03-22', 'bui.khang@gmail.com', 'Bùi Khánh Toàn', 1, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0912233445', 1),
                                                                                          ('user9.jpg', '1994-07-07', 'luu.minh@gmail.com', 'Lưu Minh Triết', 1, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0908765432', 1),
                                                                                          ('user10.jpg', '1995-02-18', 'hoang.lan@gmail.com', 'Hoàng Lan Anh', 0, '$2a$12$sHKYC7vrCZYmjR2qu8z32ObfskZQ2Yb/LLpMjitceCQLV3Q.Hrgz6', '0989988776', 0);


-- Dữ liệu cho bảng product (10 bản ghi)
INSERT INTO product (author, description, image, last_update_time, name, price, publish_date, quantity, size, category_id) VALUES
                                                                                                                               ('Adidas', 'Áo thun nam chất liệu cotton thoáng mát', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-24', 'Áo thun trắng nam', 299000, '2025-02-20', 100, 'L', 1),
                                                                                                                               ('Nike', 'Áo thun nữ phong cách basic', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-23', 'Áo thun đen nữ', 249000, '2025-02-18', 80, 'M', 1),
                                                                                                                               ('Levi', 'Quần jean nam form slimfit, chất liệu co giãn', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-21', 'Quần jean xanh nam', 699000, '2025-02-19', 50, '32', 2),
                                                                                                                               ('Zara', 'Quần jean nữ dáng skinny, tôn dáng', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-22', 'Quần jean đen nữ', 729000, '2025-02-20', 40, '28', 2),
                                                                                                                               ('Puma', 'Giày thể thao nam chống trơn trượt', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-24', 'Giày Adidas Ultraboost', 1800000, '2025-02-15', 30, '42', 3),
                                                                                                                               ('Converse', 'Giày sneaker nữ cá tính', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-23', 'Giày Nike Air Force 1', 2200000, '2025-02-18', 25, '38', 3),
                                                                                                                               ('Uniqlo', 'Áo thun mùa hè, thấm hút mồ hôi', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-20', 'Áo thun xanh dương', 259000, '2025-02-20', 120, 'XL', 1),
                                                                                                                               ('H&M', 'Quần jean nam phong cách Hàn Quốc', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-21', 'Quần jean rách gối', 750000, '2025-02-17', 35, '30', 2),
                                                                                                                               ('New Balance', 'Giày thể thao bền bỉ, phù hợp chạy bộ', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-24', 'Giày Puma Runner', 1650000, '2025-02-16', 20, '40', 3),
                                                                                                                               ('Streetwear', 'Áo thun form rộng, họa tiết nổi bật', 'https://res.cloudinary.com/dpbysnmcc/image/upload/df1d90cd-e1f8-4b36-8a50-4b0090b37905_ao-polo-icondenim-pattern-vertical-stripes-n-25__1__2b449346a8d3464f990e4c8dadd0d11c_master.jpg?_a=DAGAACAVZAA0', '2025-02-19', 'Áo thun họa tiết', 319000, '2025-02-14', 60, 'M', 1);


-- Dữ liệu cho bảng cart_item (10 bản ghi)
INSERT INTO cart_item (amount, price, quantity, product_id, user_id) VALUES
                                                                         (500000, 250000, 2, 1, 1),
                                                                         (230000, 230000, 1, 2, 2),
                                                                         (450000, 450000, 1, 3, 3),
                                                                         (940000, 470000, 2, 4, 4),
                                                                         (1200000, 1200000, 1, 5, 5),
                                                                         (1300000, 1300000, 1, 6, 6),
                                                                         (220000, 220000, 1, 7, 7),
                                                                         (500000, 500000, 1, 8, 8),
                                                                         (1100000, 1100000, 1, 9, 9),
                                                                         (520000, 260000, 2, 10, 10);
