/*insert into public.users
(first_name, last_name,balance, bank_account, email, password)
values
    ('Tony','Stark',10000,'AvengerBank01','tony.stark@avenger.com','ImIronMan_1'),
    ('Steve','Rogers',850,'AvengerBank05','steve.rogers@avenger.com','TheAmericanState'),
    ('Natasha','Romanoff',1578,'AvengerBank11','blackwidow@avenger.com','bruceMyLove'),
    ('Bruce','Banner',1000,'AvengerBank40','bruce.banner@avenger.com','researching_99%'),
    ('Eddar','Stark',98500,'TheDragonBank01','eddar.stark@got.com','wardOfTheNorth'),
    ('Arya','Stark',75,'TheDragonBank05','arya.stark@got.com','SansaBestSister'),
    ('Tyrion','Lannister',5000.45,'LannisterBank01','thedwarf@got.com','nosexnopower'),
    ('John','Doe',0,'withoutAccount','john.doe@test.com','password'),
    ('James','Bond',100,'bank','acec8e8383afd6b9c3dcc2e6672f7ed632f15a907825f4c8a8547d665fe1b740','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

insert into public.bank_transaction
(user_id,description, value)
values
    (1,'need liquidity',-200),
    (1,'send awaking money',500),
    (3,'present for the baby birth',-75),
    (7,'for my girl friends',7500);

insert into public.user_transaction
(debtor_user_id, creditor_user_id, value,description)
values

    (1,2,100,'sushis of the Friday'),
    (7,5,100,'price of an alliance'),
    (2,1,5,'Beer at the pub'),
    (2,4,100,'natasha and bruce baby'),
    (7,5,100,'steak house'),
    (2,1,52,'sushis of the Friday'),
    (3,1,20,'box of humility'),
    (7,5,20,'pizzas and beers'),
    (3,2,20,'gift birthday'),
    (4,2,52,'sushis of the Monday'),
    (4,2,100,'sushis of the Monday'),
    (6,5,52,'horror movie of Sunday'),
    (4,1,52,'save the world'),
    (5,6,500,'sword course'),
    (1,2,52,'sushis of the Friday'),
    (5,7,1000,'price of silence'),
    (5,6,52,'dragon eggs'),
    (1,3,50,'gif of green baby'),
    (6,5,30,'pizzas'),
    (6,7,1000,'renting of army'),
    (4,3,1000,'alliance weeding'),
    (7,6,500,'new jewel'),
    (3,4,1000,'weeding preparation'),
    (2,1,45,'sushis of the Friday'),
    (7,3,100,'gif for the redhead baby');

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
*/