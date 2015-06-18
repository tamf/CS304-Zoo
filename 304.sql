drop table employee cascade constraints;
drop table zookeepers cascade constraints;
drop table guides cascade constraints;
drop table vet cascade constraints;
drop table section cascade constraints;
drop table workin cascade constraints;
drop table enclosurehas cascade constraints;
drop table animallivein cascade constraints;
drop table checkup cascade constraints;
drop table visitor cascade constraints;
drop table visits cascade constraints;
drop table item cascade constraints;
drop table purchase cascade constraints;
drop table tourdirected cascade constraints;
drop table souvenir cascade constraints;
drop table food cascade constraints;
drop table feeds cascade constraints;
drop table addto cascade constraints;
drop table zoobudget cascade constraints;
drop table expensededucts cascade constraints;
drop table getsupplies cascade constraints;

create table employee(
	sin number(9,0) primary key,
	salary number(7,2),
	name varchar(30)
);

create table zookeepers(
	sin number(9,0) primary key,
	foreign key(sin) references employee
);

create table guides(
	sin number(9,0) primary key,
	numberoftours integer,
	foreign key(sin) references employee
);

create table vet(
	sin number(9,0) primary key,
	school varchar(50),
	foreign key(sin) references employee
);

create table section(
	sectionno integer primary key,
	theme varchar(30)
);

create table workin(
	sin number(9,0) not null,
	sectionno integer not null,
	primary key(sin,sectionno),
	foreign key(sectionno) references section,
	foreign key(sin) references employee
);
	
create table enclosurehas(
	holdingtype varchar(30),
	numberofanimals integer,
	sectionno integer not null,
	primary key(holdingtype,sectionno),
	foreign key(sectionno) references section
);	

create table animallivein(
	type varchar(20),
	name varchar(20),
	sex char(1),
	sincedate date,
	holdingtype varchar(30) not null,
	sectionno integer not null,
	primary key(type,name),
	foreign key(holdingtype,sectionno) references enclosurehas
);

create table checkup(
	date_checkup date,
	sin number(9,0) not null,
	type varchar(20) not null,
	name varchar(20) not null,
	primary key(sin,type,name),
	foreign key(sin) references employee,
	foreign key(type,name) references animallivein
);

create table visitor(
	visitorno integer primary key,
	name varchar(30)
);

create table visits(
	sectionno integer not null,
	visitorno integer not null,
	primary key(sectionno, visitorno),
	foreign key(sectionno) references section,
	foreign key(visitorno) references visitor
);

create table item(
	itemid integer,
	itemname varchar(30),
	price number(9,2),
	qtyinstock integer,
	primary key(itemid,itemname)
);

create table purchase(
	itemid integer not null,
	itemname varchar(30) not null,
	date_purchase date,
	visitorno integer not null,
	qty integer,
	receiptno integer,
	totalamount number(9,2),
	primary key(itemid,visitorno,itemname,receiptno),
	foreign key(itemid,itemname) references item,
	foreign key(visitorno) references visitor
);

create table tourdirected(
	itemid integer not null,
	itemname varchar(30) not null,
	capacity integer,
	sin number(9,0) not null,
	primary key(itemid,itemname),
	foreign key(itemid,itemname) references item,
	foreign key(sin) references employee
);

create table food(
	itemid integer not null,
	itemname varchar(30) not null,
	primary key(itemid,itemname),
	foreign key(itemid,itemname) references item
);

create table souvenir(
	itemid integer not null,
	itemname varchar(30) not null,
	primary key(itemid,itemname),
	foreign key(itemid,itemname) references item
);

create table feeds(
	itemid integer not null,
	itemname varchar(30) not null,
	type varchar(20) not null,
	name varchar(20) not null,
	sin number(9,0) not null,
	datefed date,
	primary key(itemid,itemname,type,name,sin),
	foreign key(itemid,itemname) references item,
	foreign key(type,name) references animallivein,
	foreign key(sin) references employee
);

create table zoobudget(
	recordid integer primary key,
	currbalance number(10,2)
);

create table expensededucts(
	orderid integer primary key,
	recordid integer,
	totalcost number(10,2),
	date_expense date,
	foreign key(recordid) references zoobudget
);
	
create table addto(
	itemid integer not null,
	itemname varchar(30) not null,
	visitorno integer not null,
	receiptno integer,
	recordid integer,
	primary key(itemid,itemname,receiptno),
	foreign key(recordid) references zoobudget,
	foreign key(itemid,visitorno,itemname,receiptno) references purchase
);

create table getsupplies(
	orderid integer not null,
	itemid integer not null,
	itemname varchar(30) not null,
	qty integer,
	primary key(orderid,itemid,itemname),
	foreign key(orderid) references expensededucts,
	foreign key(itemid,itemname) references item
);

insert into employee values(123456789, 30000.00, 'Joe Schmoe');
insert into employee values(060839453, 35000.00, 'Pete Blake');
insert into employee values(351565322, 42000.00, 'Susan Nguyen');
insert into employee values(301221823, 51000.00, 'Noah Park');
insert into employee values(318548912, 26000.00, 'John Ko');
insert into employee values(550156548, 42000.00, 'Ricky Pierce');
insert into employee values(320874981, 35000.00, 'Scott Mendez');
insert into employee values(556784565, 25000.00, 'Denise Ray');
insert into employee values(567354612, 28000.00, 'Lisa Bowman');
insert into employee values(455798411, 50000.00, 'John Ko');
insert into employee values(573284895, 37000.00, 'Linda Richardson');
insert into employee values(578875478, 40000.00, 'William Zala');
insert into employee values(115987938, 25000.00, 'Randy Washington');
insert into employee values(462156489, 27000.00, 'Emily Johnson');
insert into employee values(099354543, 30000.00, 'Emily Wong');

insert into zookeepers values(318548912);
insert into zookeepers values(556784565);
insert into zookeepers values(567354612);
insert into zookeepers values(462156489);
insert into zookeepers values(115987938);
insert into zookeepers values(099354543);
insert into zookeepers values(060839453);

insert into guides values(578875478, 0);
insert into guides values(573284895, 0);
insert into guides values(320874981, 0);
insert into guides values(123456789, 0);
insert into guides values(550156548, 0);

insert into vet values(301221823, 'UBC');
insert into vet values(351565322, 'SFU');
insert into vet values(455798411, 'Harvard');

insert into section values(1, 'African Safari');
insert into section values(2, 'Rainforest Adventure');
insert into section values(3, 'Land of Ice');
insert into section values(4, 'Urban Jungle');
insert into section values(5, 'Aquatic Wonderland');

insert into workin values(318548912, 1);
insert into workin values(556784565, 3);
insert into workin values(567354612, 2);
insert into workin values(462156489, 4);
insert into workin values(115987938, 5);
insert into workin values(060839453, 1);
insert into workin values(099354543, 2);
insert into workin values(123456789, 1);
insert into workin values(320874981, 2);
insert into workin values(573284895, 3);
insert into workin values(578875478, 4);
insert into workin values(550156548, 5);

insert into enclosurehas values('Lions', 3, 1);
insert into enclosurehas values('Zebras', 4, 1);
insert into enclosurehas values('Elephants', 2, 1);
insert into enclosurehas values('Anacondas', 1, 2);
insert into enclosurehas values('Sloths', 2, 2);
insert into enclosurehas values('Lemurs', 3, 2);
insert into enclosurehas values('Parrots', 1, 2);
insert into enclosurehas values('Arctic Wolves', 2, 3);
insert into enclosurehas values('Polar Bears', 3, 3);
insert into enclosurehas values('Penguins', 5, 3);
insert into enclosurehas values('Ocelots', 3, 4);
insert into enclosurehas values('Orangutans', 1, 4);
insert into enclosurehas values('Tigers', 3, 4);
insert into enclosurehas values('Sea Turtles', 1, 5);
insert into enclosurehas values('Sea Otters', 2, 5);
insert into enclosurehas values('Sharks', 3, 5);
insert into enclosurehas values('Dolphins', 3, 5);

insert into animallivein values('Lion', 'Leo', 'M', '2015-01-01', 'Lions', 1);
insert into animallivein values('Lion', 'Bella', 'F', '2015-01-01', 'Lions', 1);
insert into animallivein values('Lion', 'Lucy', 'F', '2011-04-05', 'Lions', 1);
insert into animallivein values('Zebra', 'Molly', 'F', '2015-01-01', 'Zebras', 1);
insert into animallivein values('Zebra', 'Max', 'M', '2011-04-05', 'Zebras', 1);
insert into animallivein values('Zebra', 'Daisy', 'F', '2008-09-02', 'Zebras', 1);
insert into animallivein values('Zebra', 'Charlie', 'M', '2008-09-02', 'Zebras', 1);
insert into animallivein values('Elephant', 'Maggie', 'F', '2015-01-01', 'Elephants', 1);
insert into animallivein values('Elephant', 'Rocky', 'M', '2009-03-12', 'Elephants', 1);
insert into animallivein values('Anaconda', 'Sophie', 'F', '2011-04-05', 'Anacondas', 2);
insert into animallivein values('Sloth', 'Jake', 'M', '2015-01-01', 'Sloths', 2);
insert into animallivein values('Sloth', 'Jack', 'M', '2012-01-06', 'Sloths', 2);
insert into animallivein values('Lemur', 'Toby', 'M', '2009-03-12', 'Lemurs', 2);
insert into animallivein values('Lemur', 'Sadie', 'F', '2011-07-11', 'Lemurs', 2);
insert into animallivein values('Lemur', 'Chloe', 'F', '2011-04-05', 'Lemurs', 2);
insert into animallivein values('Parrot', 'Cody', 'M', '2014-03-12', 'Parrots', 2);
insert into animallivein values('Arctic Wolf', 'Buster', 'M', '2013-12-03', 'Arctic Wolves', 3);
insert into animallivein values('Arctic Wolf', 'Bailey', 'F', '2015-01-01', 'Arctic Wolves', 3);
insert into animallivein values('Polar Bear', 'Cooper', 'M', '2009-03-12', 'Polar Bears', 3);
insert into animallivein values('Polar Bear', 'Lola', 'F', '2011-07-11', 'Polar Bears', 3);
insert into animallivein values('Polar Bear', 'Zoe', 'F', '2015-04-01', 'Polar Bears', 3);
insert into animallivein values('Penguin', 'Riley', 'M', '2015-01-01', 'Penguins', 3);
insert into animallivein values('Penguin', 'Abby', 'F', '2011-07-11', 'Penguins', 3);
insert into animallivein values('Penguin', 'Tucker', 'M', '2013-12-03', 'Penguins', 3);
insert into animallivein values('Penguin', 'Ginger', 'F', '2015-01-01', 'Penguins', 3);
insert into animallivein values('Penguin', 'Murphy', 'M', '2012-07-06', 'Penguins', 3);
insert into animallivein values('Ocelot', 'Roxy', 'F', '2015-01-01', 'Ocelots', 4);
insert into animallivein values('Ocelot', 'Oliver', 'M', '2011-07-11', 'Ocelots', 4);
insert into animallivein values('Ocelot', 'Sam', 'M', '2012-01-06', 'Ocelots', 4);
insert into animallivein values('Orangutan', 'Gracie', 'F', '2015-04-01', 'Orangutans', 4);
insert into animallivein values('Tiger', 'Oscar', 'M', '2015-01-01', 'Tigers', 4);
insert into animallivein values('Tiger', 'Coco', 'F', '2012-07-06', 'Tigers', 4);
insert into animallivein values('Tiger', 'Teddy', 'M', '2013-12-03', 'Tigers', 4);
insert into animallivein values('Sea Turtle', 'Sammy', 'M', '2015-01-07', 'Sea Turtles', 5);
insert into animallivein values('Sea Otter', 'Sasha', 'F', '2011-07-11', 'Sea Turtles', 5);
insert into animallivein values('Sea Otter', 'Shadow', 'M', '2015-01-05', 'Sea Turtles', 5);
insert into animallivein values('Shark', 'Zeus', 'M', '2012-01-06', 'Sharks', 5);
insert into animallivein values('Shark', 'Lily', 'F', '2012-07-06', 'Sharks', 5);
insert into animallivein values('Shark', 'Samson', 'M', '2013-12-03', 'Sharks', 5);
insert into animallivein values('Dolphin', 'Angel', 'F', '2012-07-06', 'Dolphins', 5);
insert into animallivein values('Dolphin', 'Emma', 'F', '2015-01-01', 'Dolphins', 5);
insert into animallivein values('Dolphin', 'Rudy', 'M', '2012-01-06', 'Dolphins', 5);

insert into checkup values('2013-04-11', 301221823, 'Sea Otter', 'Sasha');
insert into checkup values('2011-09-09', 301221823, 'Dolphin', 'Emma');
insert into checkup values('2014-07-07', 351565322, 'Zebra', 'Daisy');
insert into checkup values('2015-02-07', 351565322, 'Ocelot', 'Sam');
insert into checkup values('2014-08-01', 351565322, 'Tiger', 'Teddy');
insert into checkup values('2015-12-08', 455798411, 'Anaconda', 'Sophie');
insert into checkup values('2012-08-03', 455798411, 'Sloth', 'Jack');
insert into checkup values('2012-03-01', 301221823, 'Anaconda', 'Sophie');

insert into visitor values(1, 'Hazel');
insert into visitor values(2, 'Fabian');
insert into visitor values(3, 'Wenny');
insert into visitor values(4, 'Kieran');
insert into visitor values(5, 'Michael');

insert into visits values(1, 1);
insert into visits values(1, 2);
insert into visits values(2, 5);
insert into visits values(2, 1);
insert into visits values(3, 5);
insert into visits values(3, 1);
insert into visits values(4, 3);
insert into visits values(4, 2);
insert into visits values(4, 1);
insert into visits values(5, 4);
insert into visits values(5, 1);

insert into item values(10, 'Hot Dog', 5.75, 200);
insert into item values(11, 'Hamburger', 7.50, 150);
insert into item values(12, 'Fish and Chips', 10.00, 100);
insert into item values(13, 'African Safari Tour', 19.99, 10);
insert into item values(14, 'Rainforest Adventure Tour', 19.99, 15);
insert into item values(15, 'Land of Ice Tour', 19.99, 10);
insert into item values(16, 'Urban Jungle Tour', 19.99, 15);
insert into item values(17, 'Aquatic Wonderland Tour', 19.99, 0);
insert into item values(18, 'Tiger Toy', 29.99, 20);
insert into item values(19, 'Dolphin Toy', 29.99, 0);
insert into item values(20, 'Animal Feed', null, 20);

insert into purchase values(10, 'Hot Dog', '2015-01-01', 1, 1, 1, 5.75);
insert into purchase values(14, 'Rainforest Adventure Tour', '2015-03-31', 2, 1, 2, 19.99);
insert into purchase values(11, 'Hamburger' , '2015-03-31', 2, 2, 3, 15.00);
insert into purchase values(18, 'Tiger Toy', '2015-03-31', 3, 1, 4, 29.99);
insert into purchase values(16, 'Urban Jungle Tour', '2015-06-01', 4, 2, 5, 39.98);
insert into purchase values(16, 'Urban Jungle Tour', '2015-06-01', 4, 2, 6, 39.98);

insert into tourdirected values(13, 'African Safari Tour', 10, 123456789);
insert into tourdirected values(14, 'Rainforest Adventure Tour', 15, 320874981);
insert into tourdirected values(15, 'Land of Ice Tour', 10, 573284895);
insert into tourdirected values(16, 'Urban Jungle Tour', 15, 578875478);
insert into tourdirected values(17, 'Aquatic Wonderland Tour', 15, 550156548);

insert into food values(10, 'Hot Dog');
insert into food values(11, 'Hamburger');
insert into food values(12, 'Fish and Chips');

insert into souvenir values(18, 'Tiger Toy');
insert into souvenir values(19, 'Dolphin Toy');

insert into feeds values(20, 'Animal Feed', 'Zebra', 'Molly', 318548912, '2015-01-01');
insert into feeds values(20, 'Animal Feed', 'Polar Bear', 'Zoe', 556784565, '2015-01-01');
insert into feeds values(20, 'Animal Feed', 'Sea Otter', 'Shadow', 115987938, '2015-01-01');
insert into feeds values(20, 'Animal Feed', 'Tiger', 'Coco', 462156489, '2015-01-01');
insert into feeds values(20, 'Animal Feed', 'Lemur', 'Toby', 567354612, '2015-01-01');

insert into zoobudget values(10000, 50000.00);
insert into zoobudget values(10001, 50005.75);
insert into zoobudget values(10002, 50025.74);
insert into zoobudget values(10003, 50040.74);
insert into zoobudget values(10004, 50070.73);
insert into zoobudget values(10005, 50110.71);
insert into zoobudget values(10006, 50150.69);
insert into zoobudget values(10007, 49863.19);
insert into zoobudget values(10008, 49563.29);
insert into zoobudget values(10009, 49063.29);
insert into zoobudget values(10010, 48063.29);
insert into zoobudget values(10011, 47063.29);

insert into expensededucts values(20001, 10007, 287.50, '2015-06-07');
insert into expensededucts values(20002, 10008, 299.90, '2015-06-07');
insert into expensededucts values(20003, 10009, 500.00, '2015-06-07');

insert into addto values(10, 'Hot Dog', 1, 1, 10001);
insert into addto values(14, 'Rainforest Adventure Tour', 2, 2, 10002);
insert into addto values(11, 'Hamburger', 2, 3, 10003);
insert into addto values(18, 'Tiger Toy', 3, 4, 10004);
insert into addto values(16, 'Urban Jungle Tour', 4, 5, 10005);
insert into addto values(16, 'Urban Jungle Tour', 4, 6, 10006);

insert into getsupplies values(20001, 10, 'Hot Dog', 100);
insert into getsupplies values(20002, 19, 'Dolphin Toy', 20);
insert into getsupplies values(20003, 20, 'Animal Feed', 100);


commit;

