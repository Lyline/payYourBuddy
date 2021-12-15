insert into public.users
(user_id,first_name, last_name,balance, bank_account, email, password)
values
    (1,'Tony','Stark',10000,'AvengerBank01','tony.stark@avenger.com','ImIronMan_1'),
    (2,'Steve','Rogers',850,'AvengerBank05','steve.rogers@avenger.com','TheAmericanState'),
    (3,'Natasha','Romanoff',1578,'AvengerBank11','blackwidow@avenger.com','bruceMyLove'),
    (4,'Bruce','Banner',1000,'AvengerBank40','bruce.banner@avenger.com','researching_99%'),
    (5,'Eddar','Stark',98500,'TheDragonBank01','eddar.stark@got.com','wardOfTheNorth'),
    (6,'Arya','Stark',75,'TheDragonBank05','arya.stark@got.com','SansaBestSister'),
    (7,'Tyrion','Lannister',5000.45,'LannisterBank01','thedwarf@got.com','nosexnopower'),
    (8,'John','Doe',0,'withoutAccount','john.doe@test.com','password');

insert into public.bank_transaction
(transaction_id,user_id,description, value)
values
    (1,1,'need liquidity',-200),
    (2,1,'send awaking money',500),
    (3,3,'present for the baby birth',-75),
    (4,7,'for my girl friends',7500);

insert into public.user_transaction
(transaction_id,debtor_user_id, creditor_user_id, value,description)
values

    (1,1,2,100,'sushis of the Friday'),
    (2,7,5,100,'price of an alliance'),
    (3,2,1,5,'Beer at the pub'),
    (4,2,4,100,'natasha and bruce baby'),
    (5,7,5,100,'steak house'),
    (6,2,1,52,'sushis of the Friday'),
    (7,3,1,20,'box of humility'),
    (8,7,5,20,'pizzas and beers'),
    (9,3,2,20,'gift birthday'),
    (10,4,2,52,'sushis of the Monday'),
    (11,4,2,100,'sushis of the Monday'),
    (12,6,5,52,'horror movie of Sunday'),
    (13,4,1,52,'save the world'),
    (14,5,6,500,'sword course'),
    (15,1,2,52,'sushis of the Friday'),
    (16,5,7,1000,'price of silence'),
    (17,5,6,52,'dragon eggs'),
    (18,1,3,50,'gif of green baby'),
    (19,6,5,30,'pizzas'),
    (20,6,7,1000,'renting of army'),
    (21,4,3,1000,'alliance weeding'),
    (22,7,6,500,'new jewel'),
    (23,3,4,1000,'weeding preparation'),
    (24,2,1,45,'sushis of the Friday'),
    (25,7,3,100,'gif for the redhead baby');

insert into public.users_friends
(user_user_id, friends_user_id)
values
    (1,2),
    (1,3),
    (2,1),
    (2,4),
    (3,1),
    (3,2),
    (3,4),
    (4,1),
    (4,2),
    (4,3),
    (5,6),
    (5,7),
    (6,5),
    (6,7),
    (7,5),
    (7,6),
    (7,3);
