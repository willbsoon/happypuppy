# -*- coding: utf-8 -*-
"""
Created on Sat Aug 19 07:55:00 2017

@author: BS_Laptop
"""

## MachineLearning
import conn_py_to_hive as hive
import conn_py_to_mysql as mysql
import pandas as pd
import numpy as np
from konlpy.tag import Twitter

#acc_by_inc = pd.DataFrame(columns=('prediction','recall','f1-score','support'))
##데이터양에 따른 정확도 리스트
##################################### 하이브 데이터 가져오기
hive.setting_connector()
hive.start_jvm()
hive._excuteQuery("show tables")
df = hive._excuteQuery("select * from traincsv")
twitter = Twitter()
data = df['traincsv.processstate']+df['traincsv.specialmark']




##############################################################
#
#
#import csv;
#from konlpy.tag import Twitter;
#from sklearn.neural_network import MLPClassifier
#import pandas as pd
#import numpy as np
#twitter = Twitter();
#
#list1=[];
#with open("c:/bigdata/aDog_Seoul.csv",'r') as csvfile:
#    dogreader = csv.reader(csvfile)
#    for row in dogreader:
#        list1.append(row);
#        #csv파일에서 행을 읽어 전체 데이터를 리스트에 저장
#
#dataList=[];
#for i in list1[1:10000]: #test  관련해서 4000개만 일단 실행.
#    dataList.append(i[20]+','+i[22]);
#    #각 행의 종료상태와 특징 column을 가져와 리스트에 저장
########################################################################


##for number_test in range(300,6000,300):
#dataList=[]
#for i in data[:1000]:
##    print(i)
##    print(type(i))
#    dataList.append(i)


#import random
#
#for number_test in range(300,6000,300):
#    dataList=[]   
#    for i in range(number_test):
#        dataList.append(data[random.randint(1,11000)])

############################################ 명사 추출 및 컬럼 배열 생성
resultt=[];
for rere in data :
    resultt.append(twitter.nouns("u'"+rere+"'"));


result_word=[]## 전제 인덱스, 나중에 컬럼으로 사용할 것.
for i in resultt:
    if (i[0]=='종료'):
        result_word.extend(i[2:])#상태정보를 제외한 나머지를 담음.    
result_word=list(set(result_word));
result_word.sort()

result_word.extend(['deathType','killType','returnType','bringType'])##종료상태 저장
#####타입을 4개 추가
#2-자연사,3-안락사,4-반환,5-입양

totalTrainList=[];##득징을 01로 변환
for i in resultt[:5000]:#트레인 3000개까지만
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

totalTestList=[];##득징을 01로 변환
for i in resultt[5001:]:#테스트 300개까지만
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

##################################################
##################################################
##################################################
##################################################

acc_by_inc_single = pd.DataFrame(columns=('times','accuracy'))


import tensorflow as tf

x = tf.placeholder(tf.float32,[None,len(train_x.columns.values)])
y_ = tf.placeholder(tf.float32,[None,4])
w = tf.Variable(tf.zeros([len(train_x.columns.values),4]))
b = tf.Variable(tf.zeros([4]))
y = tf.nn.softmax(tf.matmul(x,w)+b)

cost=tf.reduce_mean(-tf.reduce_sum(y_* tf.log(y), reduction_indices=1))
optimizer = tf.train.GradientDescentOptimizer(0.01).minimize(cost)

init = tf.initialize_all_variables()
sess = tf.Session()
sess.run(init)

for step in range(2000):
    sess.run(optimizer, feed_dict={x:train_x, y_:train_y})
    if step % 20==0:
        print(step, sess.run(cost, feed_dict = {x:train_x, y_:train_y}))
        ############정확도 체크        
        correct_prediction=tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
        accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

        #print(sess.run(accuracy, feed_dict={x:[ta[84]],y_:[tb[84]]}))
        #특정 데이터의 정확도를 알려고 할때
        acc=[int(step),sess.run(accuracy, feed_dict={x:test_x,y_:test_y})]
        print("accuracy : ",acc)
        acc_by_inc_single.loc[len(acc_by_inc_single)]=acc
print(acc_by_inc_single)
        
sess.run(w), sess.run(b)

a=sess.run(y, feed_dict={x:[test_x.loc[1]]})
a[0][1]

sess.close()

###################
import matplotlib.pylab as plt
x_axis=list(acc_by_inc_single['times'])
line_acc_single = list(acc_by_inc_single['accuracy'])

plt.figure(figsize=(10,7),dpi=100)
plt.plot(x_axis,line_acc_single,label='DNN_SingleLayer')
## 다층dnn 적용
line_acc_multi = list(acc_by_inc_multi['accuracy'])
plt.plot(x_axis,line_acc_multi,label='DNN_MultiLayer')
##
plt.legend(loc=1)
plt.axis([0,2100,0,1])
plt.ylabel("accuracy")
plt.xlabel("times")
plt.show()














