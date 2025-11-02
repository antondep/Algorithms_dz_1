import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("experiment_results.csv")

plt.figure()
plt.plot(df["DataSize"], df["TotalOps"], marker="o")
plt.title("Кількість виконаних операцій за 10 секунд")
plt.xlabel("Розмір бази (кількість студентів)")
plt.ylabel("Кількість операцій")
plt.grid(True)
plt.savefig("ops_performance.png")

plt.figure()
plt.plot(df["DataSize"], df["MemoryMB"], marker="s", color="orange")
plt.title("Використана пам’ять")
plt.xlabel("Розмір бази (кількість студентів)")
plt.ylabel("Пам’ять (МБ)")
plt.grid(True)
plt.savefig("memory_usage.png")

plt.show()