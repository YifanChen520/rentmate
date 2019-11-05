# -*- coding: utf-8 -*-
"""
Created on Sun Sep 29 19:26:37 2019

@author: jerds
"""
import pymysql
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
                         PRIMARY KEY (uid)
                       )
                       """, "user")
    
    print("\n\nInitializing table 'apartment'...")
    database.create_table("""
                       CREATE TABLE apartment(
                         aid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         address VARCHAR(150) NOT NULL,
                         latitude FLOAT,
                         longitude FLOAT,
                         rooms JSON,
                         manager_id INT UNSIGNED NOT NULL,
                         bed_count SMALLINT UNSIGNED NOT NULL,
                         bath_count SMALLINT UNSIGNED NOT NULL,
                         PRIMARY KEY(aid),
                         FOREIGN KEY(manager_id) REFERENCES user(uid)
                       )
                     """, "apartment")
    
    print("\n\nInitializing table 'room'...")
    database.create_table("""
                   CREATE TABLE room(
                     rid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     aid INT UNSIGNED NOT NULL,
                     manager_id INT UNSIGNED NOT NULL,
                     tenant_id INT UNSIGNED,
                     pid INT UNSIGNED,
                     rent DECIMAL(10,2) UNSIGNED NOT NULL,
                     PRIMARY KEY(rid),
                     FOREIGN KEY(aid) REFERENCES apartment(aid),
                     FOREIGN KEY(manager_id) REFERENCES user(uid),
                     FOREIGN KEY(tenant_id) REFERENCES user(uid)
                   )
                 """, "room")
                   
    print("\n\nInitializing table 'posting'...")
    database.create_table("""
                   CREATE TABLE posting(
                     pid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     rid INT UNSIGNED NOT NULL,
                     start_availability DATE NOT NULL,
                     end_availability DATE,
                     PRIMARY KEY(pid),
                     FOREIGN KEY(rid) REFERENCES room(rid)
                   )
                 """, "posting")
            
    database.cursor.execute("""
                       ALTER TABLE room
                       ADD FOREIGN KEY(pid) REFERENCES posting(pid)
                       """)
    
    print("\n\nInitializing table 'images'...")
    database.create_table("""
                   CREATE TABLE images(
                     iid INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     rid INT UNSIGNED NOT NULL,
                     url VARCHAR(3000) NOT NULL,
                     PRIMARY KEY(iid),
                     FOREIGN KEY(rid) REFERENCES room(rid)
                   )
                 """, "images")
                   
    print("\n\nInitializing table 'visitations'...")
    database.create_table("""
                   CREATE TABLE visitations(
                     rid INT UNSIGNED NOT NULL,
                     visitor_id INT UNSIGNED,
                     date DATE NOT NULL,
                     start_time TIME NOT NULL,
                     end_time TIME NOT NULL,
                     FOREIGN KEY(rid) REFERENCES room(rid),
                     FOREIGN KEY(visitor_id) REFERENCES user(uid)
                   )
                 """, "visitations")
                   
    print("\n\nInitializing table 'stars'...")
    database.create_table("""
                   CREATE TABLE stars(
                     star TINYINT UNSIGNED NOT NULL,
                     PRIMARY KEY(star)
                   )
                 """, "stars")
    
    print("\n\nInitializing table 'ratings'...")
    database.create_table("""
                   CREATE TABLE ratings(
                     rid INT UNSIGNED NOT NULL,
                     uid INT UNSIGNED NOT NULL,
                     rating TINYINT UNSIGNED NOT NULL,
                     review VARCHAR(15000) NOT NULL,
                     FOREIGN KEY(rid) REFERENCES room(rid),
                     FOREIGN KEY(uid) REFERENCES user(uid),
                     FOREIGN KEY(rating) REFERENCES stars(star)
                   )
                 """, "ratings")
    
    print("\n\n... finished!")

    
#fills the tables with some dummy data for testing operations    
def generate_dummies(database):
    print("Filling with dummy records:\n")
    
    #users (uid, role, first_name, last_name, email, username, password, phone)
    #NOTE: uid will auto-increment, so putting 0 should automatically move up the value
    users = [['t','John','Smith',"johnsmith@aol.com","jsmithy","gohoosiers","8125552134"],
             ['m','Jane','Doe',"jdoerealty@gmail.com","jdoe","gottalovelotusfestival","8125552579"],
             ['t','Jack','Reacher',"hoosierfan37@yahoo.com","reacher","reacheroverbond","8275553219"]
            ]
    print("Users:")
    for user in users:
        print(user)
        sql.insert_record("""
                          INSERT INTO user(role, first_name, last_name, email, username, password, phone)
                          VALUES (%s, %s, %s, %s, %s, %s, %s)
                          """, tuple(user))
    
    #apartment (aid, address, latitude, longitude, rooms, manager_id, bed_count, bath_count)
    apartments = [['0','107 S Indiana Ave, Bloomington, IN 47405',None,None,'[]','2','0','0'],
                  ['0','2822 E Bressingham Way, Bloomington, IN 47401',None,None,'[]','2','2','2.5']
                 ]
    print("\nApartments:")
    for apartment in apartments:
        print(apartment)
        sql.insert_record("""
                          INSERT INTO apartment(aid, address, latitude, longitude, rooms, manager_id, bed_count, bath_count)
                          VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
                          """, tuple(apartment))
      
    # room(rid, aid, manager_id, tenant_id, pid, rent)
    rooms = [['0','2','2','3',None,'550.00'],
             ['0','2','2',None,None,'550.00'],
             ['0','1','2',None,None,'650.00'],
            ]
    print("\nRooms:")
    for room in rooms:
        print(room)
        sql.insert_record("""
                          INSERT INTO room(rid, aid, manager_id, tenant_id, pid, rent)
                          VALUES (%s, %s, %s, %s, %s, %s)
                          """, tuple(room))
        
    #room-to-apartment(rid, aid)
    room_to_appt = [['1','2'],
                    ['2','2'],
                    ['3','1']
                    ]
    print("\nUpdating Room-to-Apartment Relation:")
    for r2f in room_to_appt:
        print(r2f)
        sql.insert_record("""
                          UPDATE apartment
                          SET
                              rooms = JSON_ARRAY_APPEND(rooms, '$', %s)
                          WHERE
                              aid = %s
                  """, tuple(r2f))
    
    # posting(pid, rid, start_availability, end_availability)
    postings = [['0','2','2019-08-15','2020-05-27'],
                ['0','3','2019-08-15',None],
               ]
    print("\nPostings:")
    for posting in postings:
        print(posting)
        sql.insert_record("""
                          INSERT INTO posting(pid, rid, start_availability, end_availability)
                          VALUES (%s, %s, %s, %s)
                          """, tuple(posting))
    
    #room-to-posting(rid, pid)
    room_to_post = [['1','2'],
                    ['2','3'],
                    ]
    print("\nUpdating Room-to-Posting Relation:")
    for r2p in room_to_post:
        print(r2p)
        sql.insert_record("""
                          UPDATE room
                          SET
                              pid = %s
                          WHERE
                              rid = %s
                  """, tuple(r2p))
    
    # images(iid, rid, url)
    images = [['0','1','https://rps.indiana.edu/images/floorplans/HILLCREST_RoomPlan-1BDR.gif'],
              ['0','1','https://rps.indiana.edu/images/buildings/Hillcrest/HIL_two_br_bedroomandcloset.JPG'],
              ['0','1','https://rps.indiana.edu/images/buildings/Hillcrest/HIL_two_br_bedroom.JPG'],
              ['0','2','https://photos.zillowstatic.com/fp/a6453129d08cc9061b6cb1f307aecf62-uncropped_scaled_within_1536_1152.webp'],
              ['0','3','https://photos.zillowstatic.com/fp/a6453129d08cc9061b6cb1f307aecf62-uncropped_scaled_within_1536_1152.webp'],
             ]
    print("\nImages:")
    for image in images:
        print(image)
        sql.insert_record("""
                          INSERT INTO images(iid, rid, url)
                          VALUES (%s, %s, %s)
                          """, tuple(image))
        
    # visitations(rid, visitor_id, date, start_time, end_time)
    visitations = [['2','1','2019-10-05','09:00:00','09:30:00'],
                   ['3','1','2019-10-05','10:30:00','11:00:00'],
                   ['2',None,'2019-10-06','05:30:00','06:00:00'],
                   ]
    print("\nVisitations:")
    for visit in visitations:
        print(visit)
        sql.insert_record("""
                          INSERT INTO visitations(rid, visitor_id, date, start_time, end_time)
                          VALUES (%s, %s, %s, %s, %s)
                          """, tuple(visit))
    
    # stars(star)
    stars = [['1'],['2'],['3'],['4'],['5']]
    print("\nStars:")
    for star in stars:
        print(star)
        sql.insert_record("""
                          INSERT INTO stars(star)
                          VALUES (%s)
                          """, tuple(star))
    
    # ratings(rid, uid, rating, review)
    ratings = [['2','3','5','Manager is super chill.  Quiet neighborhood, big house, perfect distance for work and downtown.  What more could you want?']
              ]
    print("\nRatings:")
    for rate in ratings:
        print(rate)
        sql.insert_record("""
                          INSERT INTO ratings(rid, uid, rating, review)
                          VALUES (%s, %s, %s, %s)
                          """, tuple(rate))
        
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