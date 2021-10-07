# -*- coding: utf-8 -*-
"""
Created on Wed Aug 16 22:43:00 2017

@author: BS_Laptop
"""
# 파일 위치 : C:\Users\apfhd\Anaconda3\Lib

## hive connector -->> 
#import os
#import sys
import jpype
from konlpy import jvm
import jaydebeapi as jp
import pandas.io.sql as pd_sql
#
#def setting_connector():
#    global args
#    # JAVA_HOME이 설정되어 있지 않을 경우
#    if("JAVA_HOME" not in os.environ):
#        os.environ["JAVA_HOME"] = "C:/Program Files/Java/jdk1.8.0_144"#자바 경로
#         
#    # MySql jdbc 파일 경로 및 class 경로 설정
#    MySql_JDBC_Driver = 'C:/Program Files/MySQL/mysql-connector-java-5.1.42/mysql-connector-java-5.1.42-bin.jar'
#    jar = r'C:/Program Files/MySQL/mysql-connector-java-5.1.42/mysql-connector-java-5.1.42-bin.jar'
#    args = '-Djava.class.path=%s' % jar
#     
#    # 환경 변수 출력
#    print('Python Version : ', sys.version)
#    print('JAVA_HOME : ', os.environ["JAVA_HOME"])
#    print('JDBC_Driver Path : ', MySql_JDBC_Driver)
#    print('Jpype Default JVM Path : ', jpype.getDefaultJVMPath())

def start_jvm():
    # java class path 설정
    if not jpype.isJVMStarted():
        print("not started")
        #jpype.startJVM(jpype.getDefaultJVMPath(), args)
        jvm.init_jvm(None)
        print("started")
        
def _select(sql):
    df=""
    try:
        conn = jp.connect('com.mysql.jdbc.Driver',
          'jdbc:mysql://chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com:3306/animal',
          ['******','*******']
           )
        # Select Query 실행
        # sql="select * from user"
        pd_sql.execute(sql, conn)
        df = pd_sql.read_sql(sql, conn, index_col = None)

    finally:
        conn.close()##연결종료
    return df
        
def _executeOne(sql):
    try:
        conn = jp.connect('com.mysql.jdbc.Driver',
                      'jdbc:mysql://chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com:3306/animal',
                      ['******','*******']
                       )
        pd_sql.execute(sql, conn)

    finally:
        conn.close()##연결종료
    
def _insertAll(datalist):
    try:
        conn = jp.connect('com.mysql.jdbc.Driver',
                      'jdbc:mysql://chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com:3306/animal',
                      ['******','*******']
                       )
        
        # Insert Query 실행
        deletesql="delete from animal"
        pd_sql.execute(deletesql, conn)
        addbatchlength = '?,'*(len(datalist[0])-1)+'?'
        sql="insert into animal.animal values ("+addbatchlength+")"
#        sql = sql+""
        #datalist=a
        #sql="update user set userId='123123123'"
#        data=[('python1','python1','123'),('rfgg34','123','123'),('sdgsd','23646d','123')]
        curs_mysql=conn.cursor()
        curs_mysql.executemany(sql,datalist)
    finally:
        conn.close()##연결종료
    
#def _updateQuery(sql):
#    try:
#        conn = jp.connect('com.mysql.jdbc.Driver',
#                      'jdbc:mysql://chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com:3306/animal',
#                      ['*****','******']
#                       )
#        sql="update animal set 'test.resultcode'=?,"
#        sql=sql+"'resultmsg'=?, 'age'=?, 'careaddr'=?,"
#        sql=sql+"'carenm'=?, 'caretel'=?, 'chargenm'=?,"
#        sql=sql+"'colorcd'=?, 'desertionno'=?, 'filename'=?,"
#        sql=sql+"'happendt'=?, 'happenplace'=?, 'kindcd'=?,"
#        sql=sql+"'neuteryn'=?, 'noticeedt'=?, 'noticeno'=?," 
#        sql=sql+"'noticesdt'=?, 'officetel'=?, 'orgnm'=?,"
#        sql=sql+"'popfile'=?, 'processstate'=?, 'sexcd'=?,"
#        sql=sql+"'specialmark'=?, 'weight'=?, 'numofrows'=?,"
#        sql=sql+"'pageno'=?, 'totalcount'=?, 'deathType'=?,"
#        sql=sql+"'killType'=?, 'returnType'=?, 'bringType'=? "
#        sql=sql+"where 'desertionno'=?"
#    finally:
#    

if __name__=='__main__':
#    setting_connector()
    start_jvm()
    
    
