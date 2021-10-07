# -*- coding: utf-8 -*-
"""
Created on Sun Aug 20 23:35:32 2017

@author: BS_Laptop
"""
##dnn -> softmax+relu

import conn_py_to_hive as hive
import conn_py_to_mysql as mysql
import pandas as pd
import numpy as np
from konlpy.tag import Twitter
import time
#import winsound

starttime = time.time() #시간 측정
##################################### 하이브 데이터 가져오기

hive.setting_connector()
hive.start_jvm()
hive._excuteQuery("show tables")
df = hive._excuteQuery("select * from traincsv limit 10000")
twitter = Twitter()
data = df['traincsv.processstate']+df['traincsv.specialmark']
#winsound.Beep(2200,100)
print("Hive data is loaded in Python")
             ################################################# 
             #################################################
resultt=[];
for rere in data[:5000] :
    resultt.append(twitter.nouns("u'"+rere+"'"));

result_word=[]## 전제 인덱스, 나중에 컬럼으로 사용할 것.
for i in resultt:
    if (i[0]=='종료'):
        result_word.extend(i[2:])#상태정보를 제외한 나머지를 담음.    

result_word=list(set(result_word));
result_word.sort()

result_word.extend(['deathType','killType','returnType','bringType'])##종료상태 저장
#####타입을 4개 추가 #자연사,안락사,반환,입양
print("4 Classes are added in Dataframe")
#winsound.Beep(1800,100)

totalTrainList=[];##득징을 01로 변환
for i in resultt[:]:
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
        for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-4]=1;
            if(a=='안락사'):
                convert01[-3]=1;
            if(a=='반환'):
                convert01[-2]=1;
            if(a=='입양'):
                convert01[-1]=1;
    totalTrainList.append(convert01);
#전체 단어중에서 종료, 상태 등을 제외시켜야 함. 데이터프레임 만들고 drop으로 없애자

x=pd.DataFrame(totalTrainList[:],columns=result_word[:] )
train_x=x.drop(['deathType','killType','returnType','bringType'],axis=1)

#단어중에 들어간 상태정보를 제외시키기
y=pd.DataFrame(totalTrainList[:],columns=result_word[:])
train_y=y.drop(result_word[:-4],axis=1)
print("Train Data is converted to 0 or 1")

totalTestList=[];##득징을 01로 변환
for i in resultt[:]:#테스트 300개까지만
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
       for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-4]=1;
            if(a=='안락사'):
                convert01[-3]=1;
            if(a=='반환'):
                convert01[-2]=1;
            if(a=='입양'):
                convert01[-1]=1;
    totalTestList.append(convert01);
    
x=pd.DataFrame(totalTestList[:],columns=result_word[:] )
test_x=x.drop(['deathType','killType','returnType','bringType'],axis=1)

#단어중에 들어간 상태정보를 제외시키기
y=pd.DataFrame(totalTestList[:],columns=result_word[:])
test_y=y.drop(result_word[:-4],axis=1)
print("Test Data is converted to 0 or 1")
#winsound.Beep(1800,100)

#################################################################
##테스트용 데이터 불러오기
df = hive._excuteQuery("select * from month3csv")
data = df['month3csv.processstate']+df['month3csv.specialmark']
########## 테스트 데이터 01 변환
print("Service data is loaded in python")
resultt=[];
for rere in data :
    resultt.append(twitter.nouns("u'"+rere+"'"));

#winsound.Beep(1800,100)

totalList=[];##득징을 01로 변환
for i in resultt[:]:#트레인 3000개까지만
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
        for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-4]=1;
            if(a=='안락사'):
                convert01[-3]=1;
            if(a=='반환'):
                convert01[-2]=1;
            if(a=='입양'):
                convert01[-1]=1;
    totalList.append(convert01);
#전체 단어중에서 종료, 상태 등을 제외시켜야 함. 데이터프레임 만들고 drop으로 없애자

x=pd.DataFrame(totalList[:],columns=result_word[:] )
real_x=x.drop(['deathType','killType','returnType','bringType'],axis=1)
print("Service data is converted to 0 or 1")

df['deathType']=df['killType']=df['returnType']=df['bringType'] = 0
#winsound.Beep(1800,100)  ## 상태 만들기

################################################## tensorflow는 맨 마지막에
################################################## 안그러면 타입에러뜸?

import tensorflow as tf
print("Tensorflow is started")
x = tf.placeholder(tf.float32,[None,len(train_x.columns.values)])
y_ = tf.placeholder(tf.float32,[None,len(train_y.columns.values)])

### 가중치와 라벨의 초기화
def weight_variable(shape):
    initial = tf.truncated_normal(shape, stddev=0.1)
    return tf.Variable(initial)
def bias_variable(shape):
    initial = tf.constant(0, 1, shape = shape)
    return tf.Variable(initial)

W_1 = weight_variable([len(train_x.columns.values),1000])
b_1 = bias_variable([1000])
h_1 = tf.nn.relu(tf.matmul(x,W_1)+b_1)

W_2 = weight_variable([1000,4])
b_2 = bias_variable([4])

y = tf.nn.softmax(tf.matmul(h_1,W_2)+b_2)

########################################################

#손실함수 및 추정방법 그리고 정확도 측정
cost=tf.reduce_mean(-tf.reduce_sum(y_* tf.log(y),
                                   reduction_indices=1))
optimizer = tf.train.AdamOptimizer(1e-4).minimize(cost)
############정확도 체크        
correct_prediction=tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
print("Variables are initialized")
init = tf.initialize_all_variables()
sess = tf.Session()
sess.run(init)
#winsound.Beep(1800,100)

print("MachineLearning is being Started")
for step in range(1001):
    sess.run(optimizer, feed_dict={x:train_x, y_:train_y})
    if step % 100==0:
        print(step, sess.run(cost, feed_dict = {x:train_x, y_:train_y}))
        print("accuracy : ",sess.run(accuracy, feed_dict={x:test_x,y_:test_y}))
print(sess.run(accuracy, feed_dict={x:test_x,y_:test_y}))
#winsound.Beep(1800,100)

##########################################################################
###################### 결과 출력 #########################################

print("Insert the output")
a=sess.run(y, feed_dict={x:real_x}).tolist()##결과값 나오기
for i in range(len(a)):
    df.ix[i,-4:]=a[i]
#winsound.Beep(2000,100)

###################################################
############ mysql 넣기 ############################
datalist=[]
for i in range(len(df)):
    datalist.append(df.loc[i].tolist()) ###행을 리스트로 변환

print("Insert into mysql")
mysql.setting_connector()
mysql.start_jvm()
mysql._insertAll(datalist)
print("insert success!!!")
endtime = time.time() - starttime
print("total time : ", endtime)
