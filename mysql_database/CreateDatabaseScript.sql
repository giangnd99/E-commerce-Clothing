	DROP
	DATABASE IF EXISTS storedb;

	CREATE
	DATABASE storedb;

	USE
	storedb;

	CREATE TABLE roles 
    (
		role_id 	INT(11) PRIMARY KEY AUTO_INCREMENT,
        name 		VARCHAR(64)  NOT NULL,
        description VARCHAR(255) NOT NULL
    );


	CREATE TABLE users
	(
		user_id   INT(11) NOT NULL AUTO_INCREMENT,
		email     VARCHAR(64)  NOT NULL,
		password  VARCHAR(255) NOT NULL,
		full_name VARCHAR(64)  NOT NULL,
		birthday  date null,
		phone     varchar(11) null,
		gender    boolean null,
		role_id      INT(11) NOT NULL,
		PRIMARY KEY (user_id),
		UNIQUE KEY email_UNIQUE (email),
        CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles (role_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	);

	CREATE TABLE categorys
	(
		category_id INT(11) NOT NULL AUTO_INCREMENT,
		name        VARCHAR(64) NOT NULL,
		PRIMARY KEY (category_id)
	);

	CREATE TABLE products
	(
		product_id       INT(11) NOT NULL AUTO_INCREMENT,
		name             VARCHAR(128) NOT NULL,
		description      MEDIUMTEXT NULL,
		image            nvarchar(255) NULL,
		price            FLOAT        NOT NULL,
		publish_date     DATE         NOT NULL,
		last_update_time DATETIME     NOT NULL,
		category_id      INT(11) NOT NULL,
		PRIMARY KEY (product_id),
		UNIQUE KEY title_UNIQUE (name),
		CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categorys (category_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	);

	CREATE TABLE reviews
	(
		review_id   INT(11) NOT NULL AUTO_INCREMENT,
		product_id  INT(11) NOT NULL,
		user_id INT(11) NOT NULL,
		rating      INT(11) NOT NULL,
		headline    VARCHAR(128) NOT NULL,
		comment     VARCHAR(500) NOT NULL,
		review_time DATETIME     NOT NULL,
		PRIMARY KEY (review_id),
		CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products (product_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_customer FOREIGN KEY (user_id) REFERENCES users (user_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	);

	CREATE TABLE orders
	(
		order_id        int(11) NOT NULL AUTO_INCREMENT,
		user_id     int(11) NOT NULL,
		order_date      datetime     NOT NULL,
		r_firstname     varchar(30)  NOT NULL,
		r_lastname      varchar(30)  DEFAULT NULL,
		r_phone         varchar(15)  NOT NULL,
		r_city          varchar(32)  DEFAULT NULL,
		r_state         varchar(45)  DEFAULT NULL,
		payment_method  varchar(20)  NOT NULL,
		shipping_fee    float        DEFAULT NULL,
		subtotal        float        DEFAULT NULL,
		total           float        NOT NULL,
		status          varchar(20)  NOT NULL,
		PRIMARY KEY (order_id),
		UNIQUE KEY order_id_UNIQUE (order_id),
		KEY             customer_fk_2_idx (user_id),
		CONSTRAINT customer_fk_2 FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
	) ;

	CREATE TABLE order_detail
	(
		id         INT(11) primary key auto_increment,
		order_id   INT(11) NOT NULL,
		product_id INT(11) NOT NULL,
		quantity   INT(11) NOT NULL,
		subtotal   FLOAT NOT NULL,
		CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id),
		CONSTRAINT fk_order_product FOREIGN KEY (product_id) REFERENCES products (product_id)
	) ;


	INSERT INTO storedb.categorys (category_id, name) VALUES (1, 'top');
	INSERT INTO storedb.categorys (category_id, name) VALUES (2, 'bottom');
	INSERT INTO storedb.categorys (category_id, name) VALUES (3, 'outerwear');
	INSERT INTO storedb.categorys (category_id, name) VALUES (4, 'accessories');

	INSERT INTO products (name, description, image, price, publish_date, last_update_time, category_id)
	VALUES
	-- TOP
	('SWE Basic Tee',  'Áo thun cotton 100%, thoáng mát và thoải mái.', 'swe_basic_tee.jpg', 250000, '2024-01-10', NOW(), 1),
	('SWE Oversized Tee',  'Áo thun form rộng, phù hợp streetwear.', 'swe_oversized_tee.jpg', 290000, '2024-02-01', NOW(), 1),
	('SWE Polo Shirt',  'Áo polo cao cấp, thiết kế đơn giản nhưng tinh tế.', 'swe_polo_shirt.jpg', 320000, '2024-01-20', NOW(), 1),
	('SWE Tank Top',  'Áo tank top thể thao, thoáng mát khi vận động.', 'swe_tank_top.jpg', 270000, '2024-01-15', NOW(), 1),
	('SWE Crop Top',  'Áo crop top dành cho nữ, chất vải co giãn.', 'swe_crop_top.jpg', 280000, '2024-01-25', NOW(), 1),

	-- BOTTOM
	('SWE Cargo Pants',  'Quần cargo túi hộp, chất liệu kaki cao cấp.', 'swe_cargo_pants.jpg', 450000, '2024-02-05', NOW(), 2),
	('SWE Jogger Pants',  'Quần jogger thoải mái, phù hợp streetwear.', 'swe_jogger_pants.jpg', 420000, '2024-01-30', NOW(), 2),
	('SWE Denim Jeans',  'Quần jeans basic, phù hợp nhiều phong cách.', 'swe_denim_jeans.jpg', 500000, '2024-01-12', NOW(), 2),
	('SWE Shorts',  'Quần short nam nữ, thích hợp mùa hè.', 'swe_shorts.jpg', 350000, '2024-02-08', NOW(), 2),
	('SWE Sweatpants',  'Quần nỉ, phù hợp mặc ở nhà và tập luyện.', 'swe_sweatpants.jpg', 390000, '2024-01-28', NOW(), 2),

	-- OUTERWEAR
	('SWE Hoodie',  'Hoodie unisex, vải nỉ dày dặn và ấm áp.', 'swe_hoodie.jpg', 550000, '2024-02-10', NOW(), 3),
	('SWE Zipper Hoodie',  'Áo khoác nỉ có khóa kéo tiện lợi.', 'swe_zipper_hoodie.jpg', 580000, '2024-02-02', NOW(), 3),
	('SWE Bomber Jacket',  'Áo khoác bomber phong cách streetwear.', 'swe_bomber_jacket.jpg', 690000, '2024-01-18', NOW(), 3),
	('SWE Varsity Jacket',  'Áo khoác varsity phối màu đẹp.', 'swe_varsity_jacket.jpg', 750000, '2024-01-22', NOW(), 3),
	('SWE Windbreaker',  'Áo gió chống nước, thích hợp đi phượt.', 'swe_windbreaker.jpg', 600000, '2024-01-14', NOW(), 3),

	-- ACCESSORIES
	('SWE Cap',  'Mũ lưỡi trai phong cách streetwear.', 'swe_cap.jpg', 250000, '2024-02-04', NOW(), 4),
	('SWE Beanie',  'Mũ len giữ ấm, phong cách tối giản.', 'swe_beanie.jpg', 270000, '2024-01-29', NOW(), 4),
	('SWE Tote Bag',  'Túi tote vải canvas, bền bỉ và tiện dụng.', 'swe_tote_bag.jpg', 300000, '2024-01-26', NOW(), 4),
	('SWE Backpack',  'Balo rộng rãi, phù hợp đi học và đi làm.', 'swe_backpack.jpg', 550000, '2024-02-09', NOW(), 4),
	('SWE Socks',  'Vớ cổ cao chất liệu cotton co giãn.', 'swe_socks.jpg', 120000, '2024-01-19', NOW(), 4),
	('SWE Belt',  'Thắt lưng vải, phong cách trẻ trung.', 'swe_belt.jpg', 180000, '2024-02-06', NOW(), 4),
	('SWE Crossbody Bag',  'Túi đeo chéo gọn nhẹ, phù hợp streetwear.', 'swe_crossbody_bag.jpg', 320000, '2024-01-17', NOW(), 4),
	('SWE Bracelet',  'Vòng tay da cao cấp, phong cách minimal.', 'swe_bracelet.jpg', 200000, '2024-01-23', NOW(), 4);
