using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp6 {
  public class Program {
    public static void Main(string[] args) {
      Window window = new Window(800, 600);

      window.OnResize += ResizeListener1;
      window.OnResize += (newWidth, newHeight) => {
        Console.WriteLine($"Lambda Listener heard the event and the new window size is {newWidth} x {newHeight}");
      };

      window.Resize(1024, 768);
    }

    private static void ResizeListener1(int newWidth, int newHeight) {
      Console.WriteLine($"Listener 1 heard the event and the new window size is {newWidth} x {newHeight}");
    }
  }
}
