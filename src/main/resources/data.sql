insert into users
(user_id,first_name, last_name,balance, bank_account, email, password)
values

        --tony.stark@avenger.com - test
    (nextval('user_sequence'),'Tony','Stark',10000,'AvengerBank01',
     '9e2430e04409299c6c914694868f9183e2c4f86c062da1f46a638569fd0d7e63',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

       --steve.rogers@avenger.com - test
    (nextval('user_sequence'),'Steve','Rogers',850,'AvengerBank05',
     '6ed8d7d63eecc39eead57bbb048911344c6d8bbbb46b434cba452c1b7f5a9b85',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

       --blackwidow@avenger.com - test
    (nextval('user_sequence'),'Natasha','Romanoff',1578,'AvengerBank11',
     '5ba160d0fa2b8b1fa1c87c3fcd291ed11322b2c47af113336433c5dab253925b',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

       --bruce.banner@avenger.com - test
    (nextval('user_sequence'),'Bruce','Banner',1000,'AvengerBank40',
     '9747e66630ebf6b8f0826b2a2cc8bdc882e8f00a3a18b3689700f50c01f7d6f6',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

       --eddar.stark@got.com - test
    (nextval('user_sequence'),'Eddar','Stark',98500,'TheDragonBank01',
     'ebbf4a4dfb8bbe1e1df37c28e71ebcdee8c7d33baa0b1d29796a34e9ddb98935',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

       --arya.stark@got.com - test
    (nextval('user_sequence'),'Arya','Stark',75,'TheDragonBank05',
     '4e594192c70286feb4ee6c85def9bdb4768e70a9dfe69e51d7259524dc9f5a3f',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

        --thedwarf@got.com - test
    (nextval('user_sequence'),'Tyrion','Lannister',5000.45,'LannisterBank01',
     'bde08da37f8d2478ab262b500b73ecfb0a0a1af028c36aa3fb25bfa790325338',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'),

       --john.doe@test.com
    (nextval('user_sequence'),'John','Doe',0,'withoutAccount',
     'fdf6851a58d1bee8f9f8ed1e51d4cda809128a1e0091afc9ef04f627f16893da',
     '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

insert into commission
(id, value)
values
    (nextval('commission_id_seq'),1),
    (nextval('commission_id_seq'),2.5),
    (nextval('commission_id_seq'),0.5),
    (nextval('commission_id_seq'),0.5),
    (nextval('commission_id_seq'),0.38),
    (nextval('commission_id_seq'),0.54),
    (nextval('commission_id_seq'),37.5),
    (nextval('commission_id_seq'),0.5),
    (nextval('commission_id_seq'),0.5),
    (nextval('commission_id_seq'),0.26),
    (nextval('commission_id_seq'),0.1),
    (nextval('commission_id_seq'),0.1),
    (nextval('commission_id_seq'),0.1),
    (nextval('commission_id_seq'),0.26),
    (nextval('commission_id_seq'),0.5),
    (nextval('commission_id_seq'),0.26),
    (nextval('commission_id_seq'),0.26),
    (nextval('commission_id_seq'),2.5),
    (nextval('commission_id_seq'),0.26),
    (nextval('commission_id_seq'),5),
    (nextval('commission_id_seq'),0.26),
    (nextval('commission_id_seq'),0.25),
    (nextval('commission_id_seq'),0.15),
    (nextval('commission_id_seq'),5),
    (nextval('commission_id_seq'),5),
    (nextval('commission_id_seq'),2.5),
    (nextval('commission_id_seq'),5),
    (nextval('commission_id_seq'),0.23),
    (nextval('commission_id_seq'),0.5);


insert into bank_transaction
(transaction_id,debtor_user_id,description, value, commission_id)
values
    (nextval('bank_transaction_transaction_id_seq'),100,'need liquidity',-200,1),
    (nextval('bank_transaction_transaction_id_seq'),100,'send awaking money',500,2),
    (nextval('bank_transaction_transaction_id_seq'),106,'present for the baby birth',-75,5),
    (nextval('bank_transaction_transaction_id_seq'),112,'for my girl friends',7500,7);

insert into user_transaction
(transaction_id,debtor_user_id, creditor_user_id, value,description,commission_id)
values

    (nextval('user_transaction_transaction_id_seq'),100,102,100,'sushis of the Friday',3),
    (nextval('user_transaction_transaction_id_seq'),112,108,100,'price of an alliance',4),
    (nextval('user_transaction_transaction_id_seq'),102,100,108,'Beer at the pub',6),
    (nextval('user_transaction_transaction_id_seq'),102,106,100,'natasha and bruce baby',8),
    (nextval('user_transaction_transaction_id_seq'),112,108,100,'steak house',9),
    (nextval('user_transaction_transaction_id_seq'),102,100,52,'sushis of the Friday',10),
    (nextval('user_transaction_transaction_id_seq'),104,100,20,'box of humility',11),
    (nextval('user_transaction_transaction_id_seq'),112,108,20,'pizzas and beers',12),
    (nextval('user_transaction_transaction_id_seq'),104,102,20,'gift birthday',13),
    (nextval('user_transaction_transaction_id_seq'),106,102,52,'sushis of the Monday',14),
    (nextval('user_transaction_transaction_id_seq'),106,102,100,'sushis of the Monday',15),
    (nextval('user_transaction_transaction_id_seq'),110,108,52,'horror movie of Sunday',16),
    (nextval('user_transaction_transaction_id_seq'),106,100,52,'save the world',17),
    (nextval('user_transaction_transaction_id_seq'),108,110,500,'sword course',18),
    (nextval('user_transaction_transaction_id_seq'),100,102,52,'sushis of the Friday',19),
    (nextval('user_transaction_transaction_id_seq'),108,112,1000,'price of silence',20),
    (nextval('user_transaction_transaction_id_seq'),108,110,52,'dragon eggs',21),
    (nextval('user_transaction_transaction_id_seq'),100,104,50,'gif of green baby',22),
    (nextval('user_transaction_transaction_id_seq'),110,108,30,'pizzas',23),
    (nextval('user_transaction_transaction_id_seq'),110,112,1000,'renting of army',24),
    (nextval('user_transaction_transaction_id_seq'),106,104,1000,'alliance weeding',25),
    (nextval('user_transaction_transaction_id_seq'),112,110,500,'new jewel',26),
    (nextval('user_transaction_transaction_id_seq'),104,106,1000,'weeding preparation',27),
    (nextval('user_transaction_transaction_id_seq'),102,100,45,'sushis of the Friday',28),
    (nextval('user_transaction_transaction_id_seq'),112,104,100,'gif for the redhead baby',29);

insert into users_friends
(user_user_id, friends_user_id)
values
    (100,102),
    (100,104),
    (102,100),
    (102,106),
    (104,100),
    (104,102),
    (104,106),
    (106,100),
    (106,102),
    (106,104),
    (108,110),
    (108,112),
    (110,108),
    (110,112),
    (112,108),
    (112,110),
    (112,104);

