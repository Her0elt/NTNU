import torch
import pandas as pd
import matplotlib.pyplot as plt
import torch.nn.functional as F
import torch.nn as nn

train = pd.read_csv('data/day_length_weight.csv', dtype='float')
train_y = train.pop('day')
train_x = torch.tensor(train.to_numpy(), dtype=torch.float)
train_y = torch.tensor(train_y.to_numpy(), dtype=torch.float).reshape(-1,1)


model= nn.Linear(train_x.shape[1], train_y.shape[1])

opt = torch.optim.SGD(model.parameters(), lr=1e-4)

loss_fn = F.mse_loss

def fit(num_epochs, model, loss_fn, opt):
    for _ in range(num_epochs):
            pred = model(train_x)
            loss = loss_fn(pred,train_y)
            loss.backward()
            opt.step()
            opt.zero_grad()
    W, b =  model.parameters()
    print("W = %s, b = %s, loss = %s" % (W.data, b.data, loss_fn(model(train_x), train_y)))


fit(100000, model, loss_fn, opt)

xt =train_x.t()[0]
yt =train_x.t()[1]

fig = plt.figure('Linear regression 3d')
ax = fig.add_subplot(projection='3d', title="Model for predicting days lived by weight and length")
# Plot
x = torch.linspace(int(torch.min(xt).item()), int(torch.max(xt).item()), 1000) 
z = torch.linspace(int(torch.min(yt).item()), int(torch.max(xt).item()), 1000) 
ax.scatter(x.numpy(),  train_y.numpy(), z.numpy(),label='$(x^{(i)},y^{(i)}, z^{(i)})$')
ax.scatter(x.numpy(), model.f(train_x).detach().numpy(),z.numpy() , label='$\\hat y = f(x) = xW+b$', color="orange")
ax.legend()
plt.show()