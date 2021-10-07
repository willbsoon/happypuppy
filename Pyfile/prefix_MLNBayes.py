# -*- coding: utf-8 -*-
"""
Created on Sat Aug 19 16:11:19 2017

@author: BS_Laptop
"""

####prefix ML


## MachineLearning
import conn_py_to_hive as hive
import conn_py_to_mysql as mysql
import pandas as pd
import numpy as np
from konlpy.tag import Twitter

acc_by_inc = pd.DataFrame(columns=('prediction','recall','f1-score','support'))
##데이터양에 따른 정확도 리스트
##################################### 하이브 데이터 가져오기
hive.setting_connector()
hive.start_jvm()
df = hive._excuteQuery("show tables")
df = hive._excuteQuery("select * from monthlycsv")
twitter = Twitter()
data = df['month6csv2.processstate']+df['month6csv2.specialmark']

#for number_test in range(300,6000,300):
dataList=[]
for i in data[3000:]:
#    print(i)
#    print(type(i))
    dataList.append(i)


#import random
#
#for number_test in range(300,6000,300):
#    dataList=[]   
#    for i in range(number_test):
#        dataList.append(data[random.randint(1,11000)])


resultt=[];
for rere in dataList :
    resultt.append(twitter.nouns("u'"+rere+"'"));



############################################################################

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
for i in df[:3000]: #test  관련해서 3000개만 일단 실행.
    data.append(i[20]+','+i[22]);
    #각 행의 종료상태와 특징 column을 가져와 리스트에 저장

resultt=[];
print(resultt)
for rere in data[1:] :
    resultt.append(twitter.pos("u'"+rere+"'",norm=True,stem=True));
##########################################################################


############################################ 명사 추출 및 컬럼 배열 생성
resultt[1][3][0]

result_word=[]## 전제 인덱스, 나중에 컬럼으로 사용할 것.

for i in range(len(resultt)):
    if (resultt[i][2][0]=='종료'):
        for ii in range(len(resultt[i])):
            if resultt[i][ii][1]!='Punctuation':
#                print(resultt[i][ii])
                result_word.append(resultt[i][ii])
for i in result_word:
    if i[0]=='u':
        result_word.pop(result_word.index(i))   
    elif i[0]=='종료':
        result_word.pop(result_word.index(i))   
    elif i[0]=='입양':
        result_word.pop(result_word.index(i))
    elif i[0]=='반환':
        result_word.pop(result_word.index(i))
    elif i[0]=='자연사':
        result_word.pop(result_word.index(i))
    elif i[0]=='안락사':
        result_word.pop(result_word.index(i))   
        
result_word_pos =['/'.join(i) for i in result_word]
tokens=[]
tokens = [d[0] for d in result_word ]#for t in d

import nltk
word = nltk.Text(tokens, name='NMSC')
print(word)
print(len(set(word.tokens)))

import pprint
from matplotlib import font_manager, rc
font_frame='c:/windows/fonts/gulim.ttc'
font_name=font_manager.FontProperties(fname=font_frame).get_name()
rc('font',family=font_name)
print(word.vocab().most_common(10))
word.plot(50)

nltk.download()
word.collocations()
###############################################################
#가중치 적용하기
    
#########################################################################
result_word=list(set(result_word));
result_word.sort()

result_word.append('type')##종료상태 저장
#1-자연사,2-안락사,3-반환,4-입양
###################################### 트레이닝 & 테스트 데이터셋 생성

totalTrainList=[];##득징을 01로 변환
for i in resultt[:]:
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
        for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-1]=1;
            if(a=='안락사'):
                convert01[-1]=2;
            if(a=='반환'):
                convert01[-1]=3;
            if(a=='입양'):
                convert01[-1]=4;
    totalTrainList.append(convert01);
#type 속성 없애기
x=pd.DataFrame(totalTrainList[:],columns=result_word[:] )
train_x=x.drop(['type'],axis=1)
#단어중에 들어간 상태정보를 제외시키기
y=pd.DataFrame(totalTrainList[:],columns=result_word[:])
train_y=y.drop(result_word[:-1],axis=1)

totalTestList=[];##득징을 01로 변환
for i in resultt[:]:
    convert01=np.zeros(len(result_word),np.int);
    for a in i:
        for b in range(len(result_word)):
            if (a==result_word[b]):
                convert01[b]=1;
            if(a=='자연사'):
                convert01[-1]=1;
            if(a=='안락사'):
                convert01[-1]=2;
            if(a=='반환'):
                convert01[-1]=3;
            if(a=='입양'):
                convert01[-1]=4;
    totalTestList.append(convert01);
#type 속성 없애기
x=pd.DataFrame(totalTestList[:],columns=result_word[:] )*1
test_x=x.drop(['type'],axis=1)
#단어중에 들어간 상태정보를 제외시키기
y=pd.DataFrame(totalTestList[:],columns=result_word[:])
test_y=y.drop(result_word[:-1],axis=1)

################################### 베르누이 나이브베이지 
from sklearn.naive_bayes import BernoulliNB
clf_bern = BernoulliNB().fit(train_x,train_y)
clf_bern.classes_
clf_bern.class_count_
np.exp(clf_bern.class_log_prior_)##퍼센트인듯? 전체중에 차지하는거
fc = clf_bern.feature_count_##1705개는 전체 워드수, 5개는 타입별로 단어가 몇번 나왔는지
fc/np.repeat(clf_bern.class_count_[:,np.newaxis],len(result_word)-1,axis=1)
#각 종류별로 전체 횟수로 단어가 나온 횟수를 나눈거. 
theta = np.exp(clf_bern.feature_log_prob_)
#단어별로 종료상태에 영향이 있는 것 확률로 표현한듯

###타입별 컬럼 생성 -> 결과값 넣을 컬럼.
#df['notice']=df['death']=df['kill']=df['return']=df['bring']=0
x['notice']=x['death']=x['kill']=x['return']=x['bring']=0


#test 하기
for i in range(len(data)-1):
    new_x=np.array(test_x.loc[i])###테스트 값
    clf_bern.predict_proba([new_x])
    p=((theta**new_x)*(1-theta)).prod(axis=1)*np.exp(clf_bern.class_log_prior_)
    pp = p/p.sum()*100
    statePercentage=[]###리스트에 담아서 표현하기
    for ii in pp:
        a=round(ii,4)#백분위로 표현
        statePercentage.append(a)
#        print("percentage : ",statePercentage)
#        print("real value : ",test_y.loc[i])
#1-자연사,2-안락사,3-반환,4-입양
    x.ix[i,-5:]=statePercentage[:]
    #확률값 데이터셋에 집어넣기
    
################## 예측값 리스트 만들기 ----> 분류성능 체크                                                      
predic_val=[]##예측값 리스트
for i in range(len(data)-1):
    newResult=[ii for ii in x.ix[i,-5:]]
    newidx=newResult.index(max(newResult))
    predic_val.append(newidx)

###############  test_y 실제 값 // predic_val 예측 값
from sklearn.metrics import *
target_names=['notice','death','kill','return','bring']
aaa=classification_report(test_y,predic_val,target_names=target_names)
print(aaa)

acc_=aaa.split('      ')### 스트링 제거하고 리스트 만들
acc=[]
for i in acc_[-4:]:
    if " " in i:
        i=i.split(" ")[1]
    if "\n" in i:
        i=i[:-1]
    acc.append(i)
## 성능 평가한 내용을 데이터프레임에 담아서 이것을 나중에 시각화하려함    
acc_by_inc.loc[len(acc_by_inc)]=acc
print(acc_by_inc)







df.columns.values
df.index


from sklearn.multiclass import OneVsRestClassifier
from sklearn.linear_model import LogisticRegression

model1 = LogisticRegression().fit(df.ix[:100,-5:],test_y.loc[:100])

model2 = OneVsRestClassifier(LogisticRegression()).fit(train_x,train_y)

import matplotlib.pylab as plt

ax1 = plt.subplot(211)
ttt1=pd.DataFrame(model1.decision_function(df.ix[101:150,-5:]))
ttt1.plot(ax=ax1)
ax2 = plt.subplot(212)
pd.DataFrame(model1.predict(df.ix[101:150,-5:]),columns=["prediction"]).plot(ax=ax2)
plt.show()




###############################################
mysql.setting_connector()
mysql.start_jvm()
df2 = mysql._executeQuery("select * from user")









############### 학습량에 따른 정확도 증가
############### 각 문자가 가지는 영향력
############### 















































