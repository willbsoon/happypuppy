#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Aug 24 20:21:09 2017

@author: eduuser
"""
##  imagepedict


import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm
import matplotlib
import os
import sys
import glob
from PIL import Image
"""
클래스 순서 
1- 말티즈(흰색) 6-골든리트리버(갈색) 9-미니어쳐핀셔(검은색)
"""

Resize_x=28
Resize_y=28


x = tf.placeholder(tf.float32, [None, 2352])
y_ = tf.placeholder(tf.float32, [None, 2])

# CNN 모델에 맞게 입력 이미지의 형태를 변경합니다.
x_image = tf.reshape(x, [-1,28,28,3])

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
 

################################################ 분석 이미지 전처리
imgfile= sys.argv[1]


img=Image.open(imgfile)
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

#######################################################################
saver = tf.train.Saver()
init_op = tf.global_variables_initializer()

with tf.Session() as sess:
    sess.run(init_op)
    save_path = "C:/Users/BS_Laptop/dump/cnn_model.ckpt"
    saver.restore(sess, save_path)
    predictions = sess.run(y_conv, feed_dict={x:aaa, keep_prob: 1.0})
    resultImage = max(predictions)
    if (resultImage=='0'):
        print('white')
    else :
        print("black")
sess.close()



