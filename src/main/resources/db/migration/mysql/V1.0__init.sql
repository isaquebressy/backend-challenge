CREATE TABLE `buy_order` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `confirmation_date` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg8ri2iddqfw37mnuufmtpnn1w` (`store_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `quantity` bigint(20) NOT NULL,
  `unit_price` decimal(19,2) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i8dbi9yfyq6dhslyf6p3v9eh` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `payment_date` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdxu27b5cydlhkrsx88ookdntp` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

