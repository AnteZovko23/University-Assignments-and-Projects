using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp6 {
  public class Singleton {
    private static readonly object myLock = new object();
    private static Singleton instance = null;

    public int number;

    private Singleton(int number) {
      this.number = 42;
    }

    ///  DOUBLE CHECKED locking pattern
    public static Singleton GetInstance() {
      if (instance == null) {  /// first check
        lock (myLock) { /// expensive line of code
          if (instance == null) /// second check
            instance = new Singleton(42);
        }
      }
      return instance;
    }

    public static void DelInstance() {
      instance = null;
    }
  }
}
