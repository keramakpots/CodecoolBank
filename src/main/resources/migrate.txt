PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
DROP TABLE IF EXISTS AccountTypes;
CREATE TABLE `AccountTypes` (
	`AccountTypeID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT
);
INSERT INTO "AccountTypes" VALUES(1,'Settlement account','Normal basic account');
INSERT INTO "AccountTypes" VALUES(2,'Saving account','');
INSERT INTO "AccountTypes" VALUES(3,'Premium account','for bussines only');
DROP TABLE IF EXISTS TransactionTypes;
CREATE TABLE `TransactionTypes` (
	`TransactionTypeID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT
);
INSERT INTO "TransactionTypes" VALUES(1,'Domestic transfer','Normal transfer');
INSERT INTO "TransactionTypes" VALUES(2,'Foreign transfer','Send money abroad');
INSERT INTO "TransactionTypes" VALUES(3,'Transfer to the tax office',NULL);
INSERT INTO "TransactionTypes" VALUES(4,'Instant transfer','');
INSERT INTO "TransactionTypes" VALUES(5,'Cash Withdraw',NULL);
DROP TABLE IF EXISTS AccountStatuses;
CREATE TABLE `AccountStatuses` (
	`AccountStatusID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT
);
INSERT INTO "AccountStatuses" VALUES(1,'active','Normal status');
INSERT INTO "AccountStatuses" VALUES(2,'disabled',NULL);
INSERT INTO "AccountStatuses" VALUES(3,'banned','Account taken by the bailiff');
DROP TABLE IF EXISTS CardStatuses;
CREATE TABLE `CardStatuses` (
	`CardStatusID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT
);
INSERT INTO "CardStatuses" VALUES(1,'active','normal status');
INSERT INTO "CardStatuses" VALUES(2,'disabled','disabled by client');
INSERT INTO "CardStatuses" VALUES(3,'blocked','');
INSERT INTO "CardStatuses" VALUES(4,'afterDate','');
DROP TABLE IF EXISTS CardTypes;
CREATE TABLE "CardTypes" (
	`CardTypeID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT NOT NULL
);
INSERT INTO "CardTypes" VALUES(1,'Gold','MasterCard');
INSERT INTO "CardTypes" VALUES(2,'Silver','Visa');
INSERT INTO "CardTypes" VALUES(3,'Basic','VisaElectron');
DROP TABLE IF EXISTS TransactionStatuses;
CREATE TABLE `TransactionStatuses` (
	`TransactionStatusID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT
);
INSERT INTO "TransactionStatuses" VALUES(1,'Done','Transaction done');
INSERT INTO "TransactionStatuses" VALUES(2,'Planned','Transaction Planed');
INSERT INTO "TransactionStatuses" VALUES(3,'Failed','Something gone wrong');
DROP TABLE IF EXISTS Customers;
CREATE TABLE "Customers" (
	`CustomerID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`FirstName`	TEXT NOT NULL,
	`LastName`	TEXT NOT NULL,
	`Login`	TEXT NOT NULL,
	`Password`	TEXT NOT NULL,
	`CreateDate`	TEXT NOT NULL,
	`IsActive`	INTEGER NOT NULL,
	`LastLogin`	TEXT
);
INSERT INTO "Customers" VALUES(1,'Adam','Małysz','adam_malysz','81dc9bdb52d04dc20036dbd8313ed055','2017-05-02',1,'2017-05-02');
INSERT INTO "Customers" VALUES(2,'Jan','Nowak','jan123','81dc9bdb52d04dc20036dbd8313ed055','2017-05-02',1,'2017-05-02');
INSERT INTO "Customers" VALUES(3,'Jadwiga ','Milecka','jadzia','81dc9bdb52d04dc20036dbd8313ed055','2017-05-02',1,'2017-05-02');
INSERT INTO "Customers" VALUES(4,'Robert','Putas','roberto','81dc9bdb52d04dc20036dbd8313ed055','2017-05-02',0,'2017-05-02');
INSERT INTO "Customers" VALUES(5,'Anna','Kowalska','anka2000','81dc9bdb52d04dc20036dbd8313ed055','2017-05-02',1,'2017-05-02');
DROP TABLE IF EXISTS Transactions;
CREATE TABLE "Transactions" (
	`TransactionID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`DateOfTransaction`	TEXT NOT NULL,
	`TransactionTypeID`	INTEGER NOT NULL,
	`Value`	INTEGER NOT NULL,
	`Description`	INTEGER NOT NULL,
	`TransactionStatusID`	INTEGER NOT NULL,
	`SourceAcountID`	INTEGER NOT NULL,
	`SourceCardID`	INTEGER,
	`DestinationAccountID`	INTEGER NOT NULL
);
INSERT INTO "Transactions" VALUES(1,'30-05-2017',2,100,'Przelew',0,1,'',2);
INSERT INTO "Transactions" VALUES(2,'24-05-2017',1,200,'Przelew',0,1,NULL,2);
INSERT INTO "Transactions" VALUES(3,'26-04-2017',3,300,'Przekaz',0,1,1,'');
DROP TABLE IF EXISTS Cards;
CREATE TABLE "Cards" (
	`CardID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`CardNumber`	TEXT NOT NULL,
	`CardTypeID`	INTEGER NOT NULL,
	`Validity`	TEXT NOT NULL,
	`Buying limit`	INTEGER NOT NULL,
	`CashWithdrawLimit`	INTEGER NOT NULL,
	`Limit`	INTEGER,
	`AccountID`	INTEGER NOT NULL,
	`CardStatusID`	INTEGER NOT NULL
);
INSERT INTO "Cards" VALUES(1,'5293 7993 7965 7002',3,'06-2018',500,1000,2000,1,1);
INSERT INTO "Cards" VALUES(2,'5300 7071 1217 2107',2,'03-2019',10000,10000,'',2,1);
INSERT INTO "Cards" VALUES(3,'4520 7433 9760 7407',1,'02-2018',800,1000,3000,3,1);
DROP TABLE IF EXISTS Accounts;
CREATE TABLE "Accounts" (
	`AccountID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`CustomerID`	INTEGER NOT NULL,
	`Number`	TEXT NOT NULL,
	`AccountTypeID`	BLOB NOT NULL,
	`AccountStatusID`	INTEGER NOT NULL,
	`OpenDate`	TEXT NOT NULL,
	`Balance`	INTEGER NOT NULL,
	`DebitLine`	INTEGER,
	`Interest`	INTEGER NOT NULL
);
INSERT INTO "Accounts" VALUES(1,3,'68 9348 1023 8136 4745 2775 5194','1',1,'2017-05-02',2000,500,3);
INSERT INTO "Accounts" VALUES(2,1,'89 9523 1037 3412 5746 9331 3189','2',1,'2017-05-02',7800,500,'2,5');
INSERT INTO "Accounts" VALUES(3,2,'89 9523 1037 3412 5746 ','2',1,'2017-05-02',6000,1000,1);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('CardTypes',3);
INSERT INTO "sqlite_sequence" VALUES('AccountTypes',3);
INSERT INTO "sqlite_sequence" VALUES('AccountStatuses',3);
INSERT INTO "sqlite_sequence" VALUES('CardStatuses',5);
INSERT INTO "sqlite_sequence" VALUES('TransactionStatuses',3);
INSERT INTO "sqlite_sequence" VALUES('TransactionTypes',5);
INSERT INTO "sqlite_sequence" VALUES('Customers',5);
INSERT INTO "sqlite_sequence" VALUES('Transactions',3);
INSERT INTO "sqlite_sequence" VALUES('Cards',3);
INSERT INTO "sqlite_sequence" VALUES('Accounts',3);
COMMIT;
