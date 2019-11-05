# -*- coding: utf-8 -*-
"""
Created on Sun Sep 29 19:26:37 2019

@author: jerds
"""
import pymysql
import json
import numpy

class mysql_database():
    #initializes the class
    def __init__(self, host, user, password, dbname):
        self.host = host
        self.user = user
        self.password = password
        self.dbname = dbname
        self.db = None
        self.cursor = None
    
    
    #connects to the database
    def connect(self, host=None, user=None, password=None, dbname=None):
        if host is None:
            host = self.host
        if user is None:
            user = self.user
        if password is None:
            password = self.password
        if dbname is None:
            dbname = self.dbname

        self.db = pymysql.connect(host=host, user=user, passwd=password)
        self.cursor = self.db.cursor()
        
        #initializes the database (if it doesn't already exist)
        print(("Initializing '{}'...").format(dbname))
        self.cursor.execute("CREATE DATABASE IF NOT EXISTS " + dbname)
        self.db.commit()
        
        self.cursor.execute("USE " +dbname)
    
    
    #creates the table
    def create_table(self, query, tablename):  
        try:
            self.cursor.execute(query)
            self.db.commit()
            
        except Exception as e:
            print(e)
        
        sql.cursor.execute(("SHOW COLUMNS FROM {}").format(tablename))
        for col in sql.cursor.fetchall():
            print(col)
            
    #inserts data
    def insert_record(self, query, arguments):
        try:
            self.cursor.execute(query, arguments)
            self.db.commit()
            
        except Exception as e:
            print(e)
        
    
    #closes the database 
    def close(self):
        self.db.close()
        self.db = None
        self.cursor = None
        
        
        
        
####################################
#         Generation Methods
####################################
        
def initialize_tables(database):
	print("\n\nInitializing table 'user'...")
	database.create_table("""
                       CREATE TABLE user(
                         uid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         role CHAR(1) NOT NULL,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         email VARCHAR(30) NOT NULL,
                         username VARCHAR(30) NOT NULL,
                         password VARCHAR(50) NOT NULL,
                         phone VARCHAR(10) NOT NULL,
			 security_q_1 VARCHAR(50) NOT NULL,
			 security_q_1_a VARCHAR(50) NOT NULL,
			 security_q_2 VARCHAR(50) NOT NULL,
			 security_q_2_a VARCHAR(50) NOT NULL,
			 security_q_3 VARCHAR(50) NOT NULL,
			 security_q_3_a VARCHAR(50) NOT NULL,
                         statements JSON NOT NULL,
                         service_reqs JSON NOT NULL,
                         PRIMARY KEY (uid)
                       )
                       """, "user")
					   
	print("\n\nInitializing userOTP table")
	database.create_table("""
						CREATE TABLE userOTP( email VARCHAR(30) NOT NULL, otp INT, PRIMARY KEY (email))
						""", "userOTP")
    
	print("\n\nInitializing table 'apartment'...")
	database.create_table("""
                       CREATE TABLE apartment(
                         aid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         address VARCHAR(150) NOT NULL,
                         latitude FLOAT,
                         longitude FLOAT,
                         manager_id INT UNSIGNED NOT NULL,
						 tenant_id INT UNSIGNED,
                         bed_count SMALLINT UNSIGNED NOT NULL,
                         bath_count SMALLINT UNSIGNED NOT NULL,
						 rent DECIMAL(10,2) UNSIGNED NOT NULL,
						 pid INT UNSIGNED,
                         reviews JSON NOT NULL,
                         images JSON NOT NULL,
                         utilities JSON NOT NULL,
                         amenities JSON NOT NULL,
                         tours JSON NOT NULL,
                         PRIMARY KEY(aid),
                         FOREIGN KEY(manager_id) REFERENCES user(uid),
						 FOREIGN KEY(tenant_id) REFERENCES user(uid)
                       )
                     """, "apartment")
                   
	print("\n\nInitializing table 'posting'...")
	database.create_table("""
                   CREATE TABLE posting(
                     pid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     aid INT UNSIGNED NOT NULL,
                     start_availability DATE NOT NULL,
                     end_availability DATE,
                     PRIMARY KEY(pid),
                     FOREIGN KEY(aid) REFERENCES apartment(aid)
                   )
                 """, "posting")
				 
	database.cursor.execute("""
                       ALTER TABLE apartment
                       ADD FOREIGN KEY(pid) REFERENCES posting(pid)
                       """)	

	print("\n\n... finished!")

    
#fills the tables with some dummy data for testing operations    
def generate_dummies(database):
    print("Filling with dummy records:\n")
    
    #users (uid, role, first_name, last_name, email, username, password, phone)
    #NOTE: uid will auto-increment, so putting 0 should automatically move up the value
    users = [['t','John','Smith',"johnsmith@aol.com","jsmithy","gohoosiers","8125552134","Favorite Sports Team","IU","Favorite Food","Bananas","Favorite University", "IU",
              str(json.dumps([{'date': '2019-09-27 22:30:00', 'amount': 1600.00}])), '[]'
             ],
             ['m','Jane','Doe',"jdoerealty@gmail.com","jdoe","gottalovelotusfestival","8125552579","Favorite Sports Team","IU","Favorite Food","Bananas","Favorite University", "IU",
              str(json.dumps([{'date': '2019-09-25 18:30:00', 'amount': 1000.00}])), '[]'
             ],
             ['t','Jack','Reacher',"hoosierfan37@yahoo.com","reacher","reacheroverbond","8275553219","Favorite Sports Team","IU","Favorite Food","Bananas","Favorite University", "IU",
              str(json.dumps([{'date': '2019-09-22 12:30:00', 'amount': 1250.00}])), '[]'
             ]
            ]
    print("Users:")
    for user in users:
        print(user)
        sql.insert_record("""
                          INSERT INTO user(role, first_name, last_name, email, username, password, phone, security_q_1, security_q_1_a, security_q_2, security_q_2_a, security_q_3, security_q_3_a, statements, service_reqs)
                          VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                          """, tuple(user))
    
    #apartment (aid, address, latitude, longitude, manager_id, tenant_id, bed_count, bath_count, rent, pid, reviews)
    apartments = [[0,'107 S Indiana Ave, Bloomington, IN 47405',None,None,2,3,0,0,550.00,None, '{}', '{}', '{}', '{}', '{}'],
                  [0,'2822 E Bressingham Way, Bloomington, IN 47401',None,None,2,3,2,2.5,550.00,None,
                   str(json.dumps([{'uid':3,'rating': 4, 'review': 'This was a pretty cool place to live in when I was here for school.  The environment is pretty loud though, so may be best to avoid if you are not into the party scene.'}])),
                   str(json.dumps(['https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260'])),
                   str(json.dumps({'gas': 0, 'water': 0, 'electric': 0, 'internet': 0})),
                   str(json.dumps(['Pet Friendly!', 'Included Washer + Dryer'])),
                   str(json.dumps([{'uid':1,'start_time': '2019-10-28 09:30:00', 'end_time': '2019-10-28 11:00:00'}]))
                 ]]
    print("\nApartments:")
    for apartment in apartments:
        print(apartment)
        sql.insert_record("""
                          INSERT INTO apartment(aid, address, latitude, longitude, manager_id, tenant_id, bed_count, bath_count, rent, pid, reviews, images, utilities, amenities, tours)
                          VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                          """, tuple(apartment))
      
   
    # posting(pid, aid, start_availability, end_availability)
    postings = [[0,1,'2019-08-15','2020-05-27'],
                [0,2,'2019-08-15',None],
               ]
    print("\nPostings:")
    for posting in postings:
        print(posting)
        sql.insert_record("""
                          INSERT INTO posting(pid, aid, start_availability, end_availability)
                          VALUES (%s, %s, %s, %s)
                          """, tuple(posting))
    
    #apartment-to-posting(aid, pid)
    apartment_to_post = [['1','1'],
                    ['2','2'],
                    ]
    print("\nUpdating Apartment-to-Posting Relation:")
    for r2p in apartment_to_post:
        print(r2p)
        sql.insert_record("""
                          UPDATE apartment
                          SET
                              pid = %s
                          WHERE
                              aid = %s
                  """, tuple(r2p))
    
    
    print("\n... finished!")
    
    
    

if __name__ == "__main__":
    host='127.0.0.1'
    user='root'
    password='password'
    dbname ='rentmate'
    
    #phases
    drop = True  #Scorched-earth, drop the entire database
                   #useful for resetting any modifications made in the tables
    tables = True #True/False depending on if you want to initialize tables
                   #should only have to be done once
    dummies = True #True/False depending on if you want dummy records
    
    
    #burn it down
    if(drop):
        sql = mysql_database(host, user, password, dbname)
        sql.connect()
        sql.cursor.execute("DROP DATABASE " + dbname)
        sql.db.commit()
        sql.close()
    
    #initializes the database object
    print("Initializing database...")
    sql = mysql_database(host, user, password, dbname)
    sql.connect()
    
    #initialize the tables
    if(tables):
        initialize_tables(sql)
        
    #fills dummy records
    if(dummies):
        generate_dummies(sql)