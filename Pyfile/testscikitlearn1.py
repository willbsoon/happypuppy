# -*- coding: utf-8 -*-
"""
Created on Sun Aug 13 18:55:34 2017

@author: BS_Laptop
1.모든 유기견 데이터를  01로 만들어 리스트로 저장, 리스트 맨뒤에 
타입값 집어넣기
2.유기견 데이터를 total 리스트에 담기
3.데이터프레임으로 생성하기-drop으로 종료상태값 삭제하기.
4.컬럼값으로 전체단어리스트 넣기
5. 트레이닝 셋 생성
6. 테스트셋 생성
등등....
"""

import csv;
from konlpy.tag import Twitter;
from sklearn.neural_network import MLPClassifier
import pandas as pd
import numpy as np
twitter = Twitter();

df=[];
with open("c:/bigdata/aDog_Seoul.csv",'r') as csvfile:
    dogreader = csv.reader(csvfile)
    for row in dogreader:
        df.append(row);
        #csv파일에서 행을 읽어 전체 데이터를 리스트에 저장

data=[];
for i in df[:4000]: #test  관련해서 4000개만 일단 실행.
    data.append(i[20]+','+i[22]);
    #각 행의 종료상태와 특징 column을 가져와 리스트에 저장

resultt=[];
print(resultt)
for rere in data[1:] :
    resultt.append(twitter.nouns("u'"+rere+"'"));
############################################################# type을 하나로 줬을때
result_word=[]## 전제 인덱스, 나중에 컬럼으로 사용할 것.

for i in resultt:
    if (i[0]=='종료'):
        result_word.extend(i[2:])#상태정보를 제외한 특징들만 담음.    
result_word=list(set(result_word));
result_word.sort()

result_word.append('type')##종료상태 저장
#2-자연사,3-안락사,4-반환,5-입양

totalTrainList=[];##득징을 01로 변환
for i in resultt[:3000]:#트레인 3000개까지만
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
        for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-1]=2;
            if(a=='안락사'):
                convert01[-1]=3;
            if(a=='반환'):
                convert01[-1]=4;
            if(a=='입양'):
                convert01[-1]=5;
    totalTrainList.append(convert01);
#전체 단어중에서 종료, 상태 등을 제외시켜야 함. 데이터프레임 만들고 drop으로 없애자

x=pd.DataFrame(totalTrainList[:],columns=result_word[:] )
train_x=x.drop(['type'],axis=1)

#단어중에 들어간 상태정보를 제외시키기
y=pd.DataFrame(totalTrainList[:],columns=result_word[:])
train_y=y.drop(result_word[:-1],axis=1)

totalTestList=[];##득징을 01로 변환
for i in resultt[3001:3300]:#테스트 300개까지만
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
        for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-1]=2;
            if(a=='안락사'):
                convert01[-1]=3;
            if(a=='반환'):
                convert01[-1]=4;
            if(a=='입양'):
                convert01[-1]=5;
    totalTestList.append(convert01);
    
x=pd.DataFrame(totalTestList[:],columns=result_word[:] )
test_x=x.drop(['type'],axis=1)

#단어중에 들어간 상태정보를 제외시키기
y=pd.DataFrame(totalTestList[:],columns=result_word[:])
test_y=y.drop(result_word[:-1],axis=1)


###### 머신러닝
mlp = MLPClassifier(hidden_layer_sizes=(50,30))
mlp.fit(train_x,train_y)
print("Training score : %s" %mlp.score(train_x,train_y))

##
a = mlp.predict(test_x.loc[22])
b=test_y.loc[22]
print(a)







###################################################################type을 여러개로 했을
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
for i in resultt[:3000]:#트레인 3000개까지만
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
for i in resultt[3001:3300]:#테스트 300개까지만
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


##########################머신러닝 확률 보기  #### 타입 하나
mlp = MLPClassifier(hidden_layer_sizes=(50,30))
mlp.fit(train_x,train_y)
print("Training score : %s" %mlp.score(train_x,train_y))



#############가우지안 나이브베이지 ###이건 아님  타입하나
from sklearn.naive_bayes import GaussianNB
gnb = GaussianNB()
y_prediction=gnb.fit(train_x, train_y).predict(train_x)
print(y_prediction)
len(y_prediction)
print(train_y,y_prediction)


#################베르누이 나이브베이지  타입한개
from sklearn.naive_bayes import BernoulliNB
##from sklearn.neural_network import MLPClassifier
clf_bern = BernoulliNB().fit(train_x,train_y)
clf_bern.classes_
clf_bern.class_count_
np.exp(clf_bern.class_log_prior_)##퍼센트인듯? 전체중에 차지하는거
fc = clf_bern.feature_count_##1705개는 전체 워드수, 5개는 타입별로 단어가 몇번 나왔는지
fc/np.repeat(clf_bern.class_count_[:,np.newaxis],len(train_x.columns.values),axis=1)
#각 종류별로 전체 횟수로 단어가 나온 횟수를 나눈거. 
theta = np.exp(clf_bern.feature_log_prob_)
#단어별로 종료상태에 영향이 있는 것 확률로 표현한듯

new_x=np.array(test_x.loc[33])###테스트 값
clf_bern.predict_proba([new_x])
p=((theta**new_x)*(1-theta)).prod(axis=1)*np.exp(clf_bern.class_log_prior_)
pp = p/p.sum()*100
statePercentage=[]###리스트에 담아서 표현하기
for i in pp:
    a=round(i,4)#백분위로 표현
    statePercentage.append(a)
print("percentage : ",statePercentage)
print("real value : ",test_y.loc[33])



####################### random forest 방식.  타입 한개로 해야한다......
from sklearn.ensemble import RandomForestClassifier
forest = RandomForestClassifier(n_estimators=1000,random_state=0)
forest.fit(train_x,train_y)

pred = forest.predict(test_x)
confusion_matrix = pd.crosstab(test_y,pred,rownames=['type'],
                               colnames=['predicted'],margins=True)
print(confusion_matrix)


###################################### tensorflow 사용해보기  타입여러개
import tensorflow as tf
#mnist 나오면 totalTrain 쓰기
x = tf.placeholder(tf.float32,[None,len(train_x.columns.values)])
y_ = tf.placeholder(tf.float32,[None,len(train_y.columns.values)])
w = tf.Variable(tf.zeros([len(train_x.columns.values),len(train_y.columns.values)]))
b = tf.Variable(tf.zeros([len(train_y.columns.values)]))
y = tf.nn.softmax(tf.matmul(x,w)+b)


cross_entropy=tf.reduce_mean(-tf.reduce_sum(y_ * tf.log(y),
                                            reduction_indices=[1]))
train_step=tf.train.GradientDescentOptimizer(0.5).minimize(cross_entropy)


init = tf.initialize_all_variables()
sess = tf.Session()
sess.run(init)
##여기까지는 되는거



##왜 안되지????
type(train_x)
import numpy as np
x
ta = np.array(train_x[:]) * 1.0
ta
tb = np.array(train_y[:]) * 1.0
tb
train_y

print(sess.run(train_step, feed_dict={x:[ta[4]],y_:[tb[4]]}))
ta[0]
sess.run(y, feed_dict={x:[ta[0]],y_:[tb[0]]})


########################################학습시킨후에 다시 해보기
import numpy as np
ta = np.array(train_x[:]) * 1.0
tb = np.array(train_y[:]) * 1.0

ta2 = np.array(test_x[:])*1.0
tb2 = np.array(test_y[:])*1.0
for i in range(3000):
    sess.run(train_step, feed_dict={x:ta,y_:tb})
#이것이 겁나 오래걸림

correct_prediction=tf.equal(tf.argmax(y,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
print(sess.run(accuracy, feed_dict={x:ta,y_:tb}))
print(sess.run(accuracy, feed_dict={x:[ta[15]],y_:[tb[15]]}))
sess.close()
##특정 데이터만   sess.run(train_step, feed_dict={x:[ta[0]],y_:[tb[0]]})
############################# -> 학습모델 pickle 에 넣을수 있음.






















