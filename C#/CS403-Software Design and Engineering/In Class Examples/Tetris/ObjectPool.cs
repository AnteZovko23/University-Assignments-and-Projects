using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp6 {
  public class Pizzas {
    public string name;

    public Pizzas(string name) {
      this.name = name;
    }
    public Pizzas() {
      this.name = "Pizza";
    }
  }

  public class ObjectPool<T> where T : new() {
    private Queue<T> pool;
    private int maxSize;

    public ObjectPool(int size) {
      maxSize = size;
      pool = new Queue<T>(size);
      for (int i = 0; i < size; i++) {
        pool.Enqueue(new T());
      }
    }

    public T Acquire() {
      if (pool.Count == 0) {
        return default(T);
      }
      else {
        return pool.Dequeue();
      }
    }

    public void Release(T pizza) {
      if (pool.Count < maxSize) {
        pool.Enqueue(pizza);
      }
    }
  }
}
