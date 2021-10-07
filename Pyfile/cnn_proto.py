#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Aug 22 20:11:01 2017

@author: eduuser
"""

# 라이브러리 및 데이터를 불러옵니다.
import tensorflow as tf
import numpy as np
#import matplotlib.pyplot as plt
#import matplotlib.cm as cm
#import matplotlib
import os
#import glob
#from tensorflow.examples.tutorials.mnist import input_data
#mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

from PIL import Image
"""
클래스 순서 
0-치와와 1- 말티즈 2-패키니즈 3-시추 4- 요크셔테리어
5-슈나우져 6-골든리트리버 7-코커스페니얼 8-보더콜리
9-미니어쳐핀셔 10-퍼그 11-포메라니안 12-푸들
"""
total_cnt=0
train=[[],[]]
#for pa in os.walk('/home/eduuser/MLCNN/image/'):
#    print(pa)
for (path, dir, files) in os.walk('C:/Users/apfhd/Documents/py_connector/image'):
    for i in files:
#       file=path+'/'+i
        total_cnt=total_cnt+1
#    if path[-2:]=='_0':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[0]=1
#        train[1].extend([clazz for i in files ])
    if path[-2:]=='_1':
        train[0].extend([path+'/'+i for i in files])
        clazz=np.zeros(2)
        clazz[0]=1
        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_2':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[2]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_3':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[3]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_4':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[4]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_5':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[5]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_6':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(3)
#        clazz[1]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_7':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[7]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='_8':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[8]=1
#        train[1].extend([clazz for i in files ])
    if path[-2:]=='_9':
        train[0].extend([path+'/'+i for i in files])
        clazz=np.zeros(2)
        clazz[1]=1
        train[1].extend([clazz for i in files ])
#    if path[-2:]=='10':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[10]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='11':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[11]=1
#        train[1].extend([clazz for i in files ])
#    if path[-2:]=='12':
#        train[0].extend([path+'/'+i for i in files])
#        clazz=np.zeros(13)
#        clazz[12]=1
#        train[1].extend([clazz for i in files ])    
    
Resize_x=28
Resize_y=28
train_img=[[],[]]
for i in train[0]:
    im=Image.open(i)
    im=im.convert("RGB")
    im=im.resize((Resize_x,Resize_y))
    imgPixel=[]
    for ii in range(Resize_x):
        for jj in range(Resize_y):
            imgPixel.append(im.getpixel((ii,jj)))
    train_img[0].append(imgPixel)
for j in train[1]:
    train_img[1].append(j)

aaa=np.array(train_img[0])
#aaa=aaa.T
aaa=aaa.reshape(436,2352) #(사진 데이터 수,가로*세로*rgb)
train_img[0]=aaa/255 ## 라운드 함수 안먹음

#apple = '/home/eduuser/Pictures/apple_1.jpg'
#im=Image.open(apple)
#im=im.convert("RGB")
#Resize_x=28
#Resize_y=28
#im=im.resize((Resize_x,Resize_y))
#imgpixel=[]
#for i in range(Resize_x):
#    for ii in range(Resize_y):
#        imgpixel.append(im.getpixel((i,ii)))

#기본 형태[2307,784,3]
#t=tf.reshape(train_img[0],[-1])
x = tf.placeholder(tf.float32, [None, 2352])
#x = tf.placeholder(tf.float32, [None, 784])
#x = tf.placeholder_with_default(train_img[0],shape=(2307,784,3))
y_ = tf.placeholder(tf.float32, [None, 2])

#xt=[]
# CNN 모델에 맞게 입력 이미지의 형태를 변경합니다.
x_image = tf.reshape(x, [-1,28,28,3])
#x_image = tf.reshape(x[0], [-1])
#for i in range(len(train_img[0])):
#    x_image = tf.reshape(x[i], [-1])
#    xt.append(x_image)


# 가중치 및 편향의 초기값 설정을 위한 함수를 정의합니다.
def weight_variable(shape):
    initial = tf.truncated_normal(shape, stddev=0.1)
    return tf.Variable(initial)

def bias_variable(shape):
    initial = tf.constant(0,1, shape=shape)
    return tf.Variable(initial)

# 합성곱/패딩/풀링 적용을 위한 함수를 정의합니다. ###### 
def conv2d(x,W):
    return tf.nn.conv2d(x,W, strides=[1,1,1,1], padding='SAME')

def max_pool_2X2(x):
    return tf.nn.max_pool(x, ksize=[1,2,2,1], strides=[1,2,2,1], padding='SAME')

# 첫번째 합성곱 계층의 가중치 및 편향을 정의합니다. 
W_conv1 = weight_variable([5,5,3,32])
b_conv1 = bias_variable([32])

# ReLU 함수 및 풀링 계층을 정의합니다. ###### 
h_conv1 = tf.nn.relu(conv2d(x_image, W_conv1) + b_conv1)
h_pool1 = max_pool_2X2(h_conv1)

# 두번째 합성곱/ReLU/풀링 계층을 정의합니다. 
W_conv2 = weight_variable([5,5,32,64])
b_conv2 = bias_variable([64])

h_conv2 = tf.nn.relu(conv2d(h_pool1, W_conv2) + b_conv2)
h_pool2 = max_pool_2X2(h_conv2)
   
# Fully connected net을 정의합니다.
W_fc1 = weight_variable([7*7*64,1024])
b_fc1 = bias_variable([1024])

h_pool2_flat = tf.reshape(h_pool2, [-1, 7*7*64])
h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, W_fc1) + b_fc1)
 
# 드랍아웃을 정의합니다.
keep_prob = tf.placeholder("float")
h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)
 
# Softmax를 적용하여 최종 결과값을 정의합니다.
W_fc2 = weight_variable([1024,2])
b_fc2 = bias_variable([2])

y_conv=tf.nn.softmax(tf.matmul(h_fc1_drop, W_fc2) + b_fc2) 
 
# 모델 학습 및 테스트를 통해 정확도를 출력합니다.
cross_entropy = tf.reduce_mean(-tf.reduce_sum(y_ * tf.log(y_conv), reduction_indices=[1]))
train_step = tf.train.AdamOptimizer(1e-4).minimize(cross_entropy)
correct_prediction = tf.equal(tf.argmax(y_conv,1), tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

sess = tf.Session()
sess.run(tf.initialize_all_variables())
###################################### 학습모델 저장
saver = tf.train.Saver()

for i in range(101):
    batch = train_img
    #### 28*28=784, rgb *3 하면 총 길이는 2352
    #### 파일수 y
    if i%10 == 0:
        train_accuracy = sess.run(accuracy, 
                                  feed_dict={x:batch[0], y_: batch[1], keep_prob: 1.0})
        print("step %d, train accuracy %g"%(i, train_accuracy))
    sess.run(train_step, feed_dict={x: batch[0], y_: batch[1], keep_prob: 0.5})

print("test accuracy %g"% sess.run(accuracy,
                                   feed_dict={x: batch[0], y_: batch[1], keep_prob: 1.0}))

###########################################################학습모델 출력
save_path = saver.save(sess, "C:/Users/apfhd/Documents/py_connector/image/cnn_model.ckpt")
print ("Model saved in file: ", save_path)



img=Image.open('C:\\Users\\apfhd\\Pictures\\black.png')
img=img.convert("RGB")
img.resize((Resize_x,Resize_y))
imgPixelss=[]
for ii in range(Resize_x):
    for jj in range(Resize_y):
        imgPixelss.append(img.getpixel((ii,jj)))

aaa=np.array(imgPixelss)
#aaa=aaa.T
aaa=aaa.reshape(1,2352)
print(aaa)
aaa=aaa/255
         

zz = sess.run(y_conv, feed_dict={x:aaa, keep_prob: 1.0})
print(zz)
sess.close()










  