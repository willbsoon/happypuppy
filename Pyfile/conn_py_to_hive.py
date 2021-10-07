#! /usr/bin/python
# -*- coding: utf-8 -*-
"""
Created on Wed Aug 16 22:43:00 2017

@author: BS_Laptop
"""
## hive connector -->> 
#import os
#import sys
import jpype
import jaydebeapi as jp
import pandas.io.sql as pd_sql
from konlpy import jvm
#
#def setting_connector():
#    global args
#    # JAVA_HOME이 설정되어 있지 않을 경우
#    if("JAVA_HOME" not in os.environ):
#        os.environ["JAVA_HOME"] = "C:/Program Files/Java/jdk1.8.0_144"#자바 경로
#         
#    # hive jdbc 파일 경로 및 class 경로 설정
#    Hive_JDBC_Driver ='C:/Users/BS_Laptop/Documents/eclipse data/webproject6/WebContent/WEB-INF/lib/hive-jdbc-1.2.2-standalone.jar'
#    jar = r'C:/Users/BS_Laptop/Documents/eclipse data/webproject6/WebContent/WEB-INF/lib/hive-jdbc-1.2.2-standalone.jar'
#    args = '-Djava.class.path=%s' % jar
#     
#    # 환경 변수 출력
#    print('Python Version : ', sys.version)
#    print('JAVA_HOME : ', os.environ["JAVA_HOME"])
#    print('JDBC_Driver Path : ', Hive_JDBC_Driver)
#    print('Jpype Default JVM Path : ', jpype.getDefaultJVMPath())

# 파일 위치 : C:\Users\apfhd\Anaconda3\Lib

def start_jvm():
    # java class path 설정
    if not jpype.isJVMStarted():
        print("not started")
        #jpype.startJVM(jpype.getDefaultJVMPath(), args)
        jvm.init_jvm(None)
        print("started")
        
def _excuteQuery(sql):
    df=""
    conn=""
    try:
        conn= jp.connect('org.apache.hive.jdbc.HiveDriver',
                               'jdbc:hive2://210.114.91.91:23395/forproject',
                               ['', '']
                               )
        #curs_hive = conn.cursor()
        # Select Query 실행
        pd_sql.execute(sql, conn)
        df = pd_sql.read_sql(sql, conn, index_col = None)
    finally:
        conn.close()##연결종료
    return df
        
def _updateQuery(sql):
    try:
        conn= jp.connect('org.apache.hive.jdbc.HiveDriver',
                               'jdbc:hive2://210.114.91.91:23395/forproject',
                               ['', '']
                               )
        #curs_hive = conn.cursor()
        # UPDATE Query 실행
        pd_sql.execute(sql, conn)
    finally:
        conn.close()##연결종료
    
    
if __name__=='__main__':
#    setting_connector()
    start_jvm()
#    _excuteQuery("show tables")
