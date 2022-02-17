using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp6 {
  public class Window {
    private int width;
    private int height;

    public event Action<int,int> OnResize;

    public Window(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public void Resize(int newWidth, int newHeight) {
      this.width = newWidth;
      this.height = newHeight;
      Console.WriteLine("Window has been resized");

      /// notify subscribers
      OnResize?.Invoke(newWidth, newHeight);
    }
  }
}
