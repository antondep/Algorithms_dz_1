import pandas as pd
import matplotlib.pyplot as plt
import os

df = pd.read_csv("experiment_results.csv")
df.columns = [c.strip() for c in df.columns]
col_map = {
    "DataSize": ["DataSize", "Size", "DatabaseSize", "StudentsCount"],
    "ContainerType": ["ContainerType", "Container", "Structure"],
    "TotalOps": ["TotalOps", "OpsCount", "Operations"],
    "MemoryMB": ["MemoryMB", "MemoryUsedMB", "Memory", "UsedMemory"],
}

rename_dict = {}
for target, possible_names in col_map.items():
    for name in possible_names:
        if name in df.columns:
            rename_dict[name] = target
            break

df = df.rename(columns=rename_dict)

required = {"DataSize", "ContainerType", "TotalOps", "MemoryMB"}
missing = required - set(df.columns)
if missing:
    raise ValueError(
        f"Missing required columns: {missing}. Found: {df.columns.tolist()}"
    )

df["DataSize"] = pd.to_numeric(df["DataSize"], errors="coerce")
df["TotalOps"] = pd.to_numeric(df["TotalOps"], errors="coerce")
df["MemoryMB"] = pd.to_numeric(df["MemoryMB"], errors="coerce")
df = df.sort_values(by="DataSize")

os.makedirs("graphs", exist_ok=True)
plt.figure(figsize=(8, 5))
for container, group in df.groupby("ContainerType"):
    plt.plot(group["DataSize"], group["TotalOps"], marker="o", label=container)
plt.title("Порівняння контейнерів — операції за 10 секунд")
plt.xlabel("Розмір бази (кількість студентів)")
plt.ylabel("Кількість операцій")
plt.legend()
plt.grid(True)
plt.savefig("graphs/all_containers_ops.png")
plt.close()

plt.figure(figsize=(8, 5))
for container, group in df.groupby("ContainerType"):
    plt.plot(group["DataSize"], group["MemoryMB"], marker="s", label=container)
plt.title("Порівняння контейнерів — використана пам’ять")
plt.xlabel("Розмір бази (кількість студентів)")
plt.ylabel("Пам’ять (МБ)")
plt.legend()
plt.grid(True)
plt.savefig("graphs/all_containers_memory.png")
plt.close()

print("All graphs saved in the 'graphs/' folder.")
