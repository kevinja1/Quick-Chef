INSERT INTO tbl_users(user_id, password, first_name, last_name, email, active) 
VALUES('jliew', 'admin', 'John', 'Liew', 'jliew@gmail.com', 1);

INSERT INTO tbl_roles(role, role_description)
VALUES('ADMIN', 'Adminstrative Role');

INSERT INTO tbl_roles(role, role_description)
VALUES('MEMBER', 'Member Role');

INSERT INTO tbl_users_roles(user_id, role_id)
VALUES('jliew', 2);

INSERT INTO tbl_address(address_1, city, state, zip)
VALUES('1115 Smith Manor Blvd.', 'West Orange', 'NJ', '07052');

INSERT INTO tbl_users_address(user_id, address_id)
VALUES('jliew', 1);

INSERT INTO tbl_users_pref(user_id, gluten_free_ind,low_calorie_ind, low_carb_ind, meat_ind, seafood_ind, vagetable_ind, num_meals_per_week, num_servings)  
VALUES('jliew', true, false, true, true, false, true, 3, 2);

INSERT INTO tbl_users_payment_info(user_id, billing_address_ind, cc_num, cc_cvc, cc_exp, name_on_card)
VALUES('jliew', false, '5354646578791125', '123', '11/23', 'John Liew');


INSERT INTO tbl_coverage(zip, location_name)
VALUES('07052', 'West Orange');

INSERT INTO tbl_coverage(zip, location_name)
VALUES('07054', 'Parsippany');

INSERT INTO tbl_coverage(zip, location_name)
VALUES('07866', 'Rockaway');

INSERT INTO tbl_coverage(zip, location_name)
VALUES('07834', 'Denville');

INSERT INTO tbl_coverage(zip, location_name)
VALUES('07960', 'Morristown');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Ginger Steak', 'Ginger Marinated Steak with Stir fry vegetables and Basmati rice', true, '850 calories', 'Italian', '/images/ginger_steak.png', '40-50 mins', 'Meat');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Chicken and Mashed Potatoes', 'Chicken and Mashed Potatoes with carrots', true, '730 calories', 'Italian', '/images/Chicken.png', '30-35 mins', 'Meat');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Seared Salmon', 'Seared Salmon and Vegetables', true, '650 calories', 'Italian', '/images/salmon.png', '45-55 mins', 'Semi-Vegetarian');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Chicken Lettuce Wraps', 'Chicken Lettuce Wraps with sauce', true, '540 calories', 'Asian', '/images/chickenlettuce_wraps.jpg', '20-30 mins', 'Meat');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Roasted Red Pepper Pasta', 'Pasta marinated with red pepper sauce', true, '730 calories', 'Italian', '/images/pepperpasta.jpg', '25-35 mins', 'Vegan');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Salmon Taco', 'Tacos with salmon and roasted vegetables', true, '650 calories', 'Mexican', '/images/salmon_taco.jpg', '20-30 mins', 'Semi-Vegetarian');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Black Bean Chilli', 'Mix of beans and vegetables', true, '500 calories', 'Mexican', '/images/black_chili.jpg', '30-40 mins', 'Vegan');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Tortilla Chicken', 'Chicken with bread', true, '650 calories', 'Mexican', '/images/tortilla_chicken.png', '35-45 mins', 'Meat');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Chipotle Ranch Chicken Salad', 'Vegetable salad with chicken', true, '430 calories', 'Mexican', '/images/chipotle_salad.jpg', '5-10 mins', 'Meat');


INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Japanese Sirloin Steak', 'Choice-cut steak with carrots', true, '650 calories', 'Asian', '/images/japanese_steak.jpg', '40-50 mins', 'Meat');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Pork Egg Roll in a Bowl', 'Pork mix with vegetables', true, '680 calories', 'Asian', '/images/pork_egg.jpg', '30-40 mins', 'Meat');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Brussels Sprout & Couscous Salad', 'Salad with a plethora vegetables', true, '820 calories', 'Italian', '/images/brussels.jpg', '40-50 mins', 'Vegan, Gluten-Free');

INSERT INTO tbl_recipe(name, description, availability, calorie, category, img_uri, time_to_prepare, health)
VALUES('Stir Fry Vegetable Rice', 'Rice with vegetables', true, '650 calories', 'Asian', '/images/stirfry_vegetable.jpg', '35-45 mins', 'Gluten-Free, Vegan');