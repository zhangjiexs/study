DROP database log_statics;
create database log_statics;
USE log_statics;
CREATE table IF NOT EXISTS user_visit_action(
	date string,
	user_id	bigint,
	session_id	string,
	page_id	bigint,
	action_time string,
	search_keyword string,
	click_category_id bigint,
	click_product_id bigint,
	order_category_ids string,
	order_product_ids string,
	pay_category_ids string,
	pay_product_ids string,
	city_id bigint
) row format delimited
fields terminated by "|";

CREATE table IF NOT EXISTS user_info(
    user_id bigint,
    username string,
    name string,
    age int,
    professional string,
    sex string,
    city string
) row format delimited
fields terminated by "|";

CREATE table IF NOT EXISTS product_info(
    product_id  bigint,
    product_name string,
    extend_info string
) row format delimited
fields terminated by "|";