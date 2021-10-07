# -*- coding: utf-8 -*-
"""
Created on Sun Aug 20 07:10:58 2017

@author: BS_Laptop
"""

##logistic regression
import statsmodels.api as sm ##statsmodels 회귀 모델 사용시 임포
import statsmodels.formula.api as smf
from sklearn.datasets import make_classification
import matplotlib.pylab as plt ## 시각화에 필요
from sklearn.neural_network import MLPClassifier as mlp
X0, y = make_classification(n_features=1, n_redundant=0, n_informative=1, n_clusters_per_class=1, random_state=4)
X = sm.add_constant(X0)


from sklearn.linear_model import LogisticRegression

model = LogisticRegression().fit(X0, y)

xx = np.linspace(-3, 3, 100)
sigm = 1.0/(1 + np.exp(-model.coef_[0][0]*xx - model.intercept_[0]))
plt.plot(xx, sigm)
plt.scatter(X0, y, marker='o', s=100)
plt.scatter(X0, model.predict(X0), marker='x', c=y, s=200, lw=2, alpha=0.5, cmap=mpl.cm.jet)
plt.xlim(-3, 3)
plt.show()



























